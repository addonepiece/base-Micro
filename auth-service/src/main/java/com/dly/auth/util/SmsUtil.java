package com.dly.auth.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.dly.auth.emums.SmsType;
import com.dly.auth.constant.ErrMsgConstant;
import com.dly.auth.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * Description:短信发送工具类
 *
 * @author yxr
 */

@Slf4j
@Component
public class SmsUtil {
    @Value("${envzone}")
    protected String profileName;

    private static boolean disableSms = false;

    @PostConstruct
    void init() {
        if ("test".equals(profileName)) {
            disableSms = true;
        }
    }

    /**
     * 初始化ascClient需要的参数:短信API产品名称（短信产品名固定，无需修改）
     */
    static final String PRODUCT = "Dysmsapi";

    /**
     * 初始化ascClient需要的参数:短信API产品域名（接口地址固定，无需修改）
     */
    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    static final String RESPONSE_CODE_OK = "OK";
    static final String RESPONSE_CODE_BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";
    @Autowired
    RedisService<String> redisService;

    private static final String SIGN_NAME = "智慧园区";
    private static final String ACCESS_KEY_ID = "LTAIrOcWOvdz77FL";
    private static final String ACCESS_KEY_SECRET = "Vm9cTUl64kpboNutQBxiUAjCwLt6tq";
    /**
     * 验证码过期时间(分钟)
     */
    private static final long VER_CODE_EXPIRE_TIME = 30;
    private static final String REGISTER_TEMPLATE_CODE = "SMS_143718755";

    public void sendsms(SmsType smsType, String telephone) throws ClientException {

        if (disableSms) {
            return;
        }

        String randstr = "";
        // 验证码为${code}
        int randNum = new Random().nextInt(10000);
        randstr = String.format("%04d", randNum);

        // 设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        // 初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        // 组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        // 使用post提交
        request.setMethod(MethodType.POST);
        // 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(telephone);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(SIGN_NAME);

        if (SmsType.REGISTER.equals(smsType)) {
            // 必填:短信模板(验证码)-可在短信控制台中找到
            request.setTemplateCode(REGISTER_TEMPLATE_CODE);
        }

        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        if (SmsType.REGISTER.equals(smsType)) {
            request.setTemplateParam("{\"code\":\"" + randstr + "\"}");
        }

        // 请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals(RESPONSE_CODE_OK)) {
            // 请求成功
            String redisKey = smsType.name() + telephone;
            redisService.setObject(redisKey, randstr, String.class);
            // 设置验证码有效期
            redisService.setExpire(redisKey, VER_CODE_EXPIRE_TIME, TimeUnit.MINUTES);

            return;
        }

        if (sendSmsResponse.getCode() != null
                && sendSmsResponse.getCode().equals(RESPONSE_CODE_BUSINESS_LIMIT_CONTROL)) {
            throw ErrMsgConstant.PROMPT.exception("短信发送频繁");
        } else {
            throw ErrMsgConstant.PROMPT.exception("短信发送失败");
        }

    }

    public int validateMsgCode(String telephone, String inputCode, SmsType smsType, boolean isSingleTime) {
        if (disableSms) {
            return 0;
        }

        String redisKey = smsType.name() + telephone;
        String correctCode = redisService.getObject(redisKey, String.class);
        // 验证码超时
        if (correctCode == null || correctCode.trim().equals("")) {
            return 1;
        }
        if (!StringUtils.isEmpty(inputCode)) {
            boolean result = inputCode.equals(correctCode);
            if (result == true) {
                if (isSingleTime) {
                    redisService.delObject(redisKey);
                }
                return 0;
            } else {
                // 验证码错误
                return 2;
            }
        }
        return 2;
    }

}
