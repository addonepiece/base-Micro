package com.dly.auth.service;

import com.dly.auth.emums.SmsType;
import com.dly.auth.constant.ErrMsgConstant;
import com.dly.auth.mapper.UserMapper;
import com.dly.auth.model.entity.User;
import com.dly.auth.model.filter.MailSendInfo;
import com.dly.auth.model.filter.RegisterData;
import com.dly.auth.model.filter.UserFilter;
import com.dly.auth.model.vo.UserVo;
import com.dly.auth.util.SendMailThread;
import com.dly.auth.util.SmsUtil;
import com.dly.exception.code.BasicErrCodes;
import com.dly.id.IDGenerator;
import com.dly.redis.RedisService;
import com.dly.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aliyuncs.exceptions.ClientException;

import java.text.ParseException;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

@Service
public class AccountServiceImpl implements AccountService {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 正则表达式：验证电子邮箱
     */
    public static final String REGEX_EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";

    /**
     * 邮箱验证码邮箱时长
     */
    private static final long VER_CODE_EXPIRE_TIME = 30;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SmsUtil smsUtil;

    @Autowired
    MailSendInfo mailSendInfo;

    @Autowired
    RedisService<String> redisService;

    public boolean checkAccount(String account) {
        boolean result = false;
        UserFilter userFilter = new UserFilter();
        userFilter.setAccount(account);
        List<UserVo> userVoList = userMapper.retrieve(userFilter);
        if (userVoList.size() == 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkPhone(String phone) {
        boolean result = false;
        UserFilter userFilter = new UserFilter();
        userFilter.setPhone(phone);
        List<UserVo> userVoList = userMapper.retrieve(userFilter);
        if (userVoList.size() == 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkEmail(String email) {
        boolean result = false;
        UserFilter userFilter = new UserFilter();
        userFilter.setEmail(email);
        List<UserVo> userVoList = userMapper.retrieve(userFilter);
        if (userVoList.size() == 0) {
            result = true;
        }
        return result;
    }

    @Override
    public String getMsgCode(RegisterData data, BiPredicate<String, String> biPredicate) throws ClientException {
        // 获取手机号码
        String telephone = data.getPhone();
        // 校验手机格式是否正确
        if (!biPredicate.test(REGEX_MOBILE, telephone)) {
            throw ErrMsgConstant.PARAM_ERROR.exception();
        }

        String verCodeType = data.getVerCodeType();
        // 发送短信验证码
        switch (verCodeType) {
            case "1":
                // 注册用
                smsUtil.sendsms(SmsType.REGISTER, telephone);
                break;
            case "2":
                // 重置密码用
                smsUtil.sendsms(SmsType.RESET_PWD, telephone);
                break;
            case "3":
                // 登录用
                smsUtil.sendsms(SmsType.LOGIN, telephone);
                break;
            default:
                break;
        }

        return null;
    }

    @Override
    public boolean validateMsgCode(String phone, String verCode, String VerCodeType, boolean isSingleTime) {
        SmsType smsType = getSmsType(VerCodeType);
        int code = smsUtil.validateMsgCode(phone, verCode, smsType, isSingleTime);
        boolean result = code == 0 ? true : false;
        if (code == 1) {
            throw ErrMsgConstant.PROMPT.exception("验证码超时请重新获取");
        }
        if (code == 2) {
            throw ErrMsgConstant.PROMPT.exception("验证码错误请重新输入或者重新获取");
        }
        return result;
    }

    @Override
    public String getCodeByEmail(RegisterData data, BiPredicate<String, String> biPredicate) throws ClientException {
        // 获取邮箱
        String email = data.getEmail();
        // 校验邮箱格式是否正确
        if (!biPredicate.test(REGEX_EMAIL, email)) {
            throw ErrMsgConstant.PARAM_ERROR.exception();
        }

        // 生成验证码
        String randstr = String.format("%04d", new Random().nextInt(10000));

        // 发送验证码到邮箱
        mailSendInfo.setSubject("标题");
        mailSendInfo.setToEmail(email);
        mailSendInfo.setContent("【签名】" + randstr + "（xx验证码，请勿泄漏）");
        // 发送邮件,单独启动一个线程
        SendMailThread sendMailThread = new SendMailThread(mailSendInfo);
        sendMailThread.start();

        // 将验证码放入redis
        String redisKey = email;
        redisService.setObject(email, randstr, String.class);
        // 设置验证码有效期
        redisService.setExpire(redisKey, VER_CODE_EXPIRE_TIME, TimeUnit.MINUTES);

        return null;
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public User registerUser(RegisterData data) {
        data.validFilter();
        checkPhone(data.getPhone());
        User user = getUser(data);
        SmsType smsType = getSmsType(data.getVerCodeType());
        int code = smsUtil.validateMsgCode(data.getPhone(), data.getVerCode(), smsType, data.isSingleTime());
        if (code == 0) {
            // 密码加密
            // user.setPassword(MD5.getMessageDigest(user.getPassword()));
            userMapper.create(user);
            return user;
        } else {
            throw ErrMsgConstant.PROMPT.exception("验证码错误请重新输入或者重新获取");
        }
    }

    @Override
    public User registerEmailUser(RegisterData data) {
        User user = getUser(data);

        mailSendInfo.setSubject("标题");
        mailSendInfo.setToEmail(user.getEmail());
        mailSendInfo.setContent("尊敬的用户" + ":<br/>请点<a href='http://47.99.211.9:1505/account/activeEmailUser?email="
                + user.getEmail() + "&code=" + DateUtil.longDateTime() + "'>【激活账号】</a><br/>激活您的账号，激活后将自动跳转至登录页面。谢谢！");

        // 发送邮件,单独启动一个线程
        SendMailThread sendMailThread = new SendMailThread(mailSendInfo);
        sendMailThread.start();

        user.setStatus("0");
        try {
            // user.setPassword(MD5.getMessageDigest(user.getPassword()));
            userMapper.create(user);
        } catch (Exception e) {
            String cause = e.getCause().toString();
            if (cause.contains("Duplicate entry")) {
                throw BasicErrCodes.DAO_INSERT_DUPLICATE.exception(e);
            } else {
                throw BasicErrCodes.DAO_INSERT_ERROR.exception(e);
            }
        }
        return user;
    }

    @Override
    public int activeEmailUser(Map<String, String> map) {
        String email = map.get("email");
        // 验证邮箱格式
        boolean result = Pattern.matches(REGEX_EMAIL, email);
        if (!result) {
            throw ErrMsgConstant.PROMPT.exception("邮箱格式不正确");
        }

        // 当前时间
        long now = new Date().getTime();
        // 激活邮件发送时间
        long sendDate;
        try {
            sendDate = DateUtil.currentStringToDate(map.get("code")).getTime();
            // 激活邮件过期判断
            if ((now - sendDate) - (1000 * 60 * 60 * 24) > 0) {
                throw ErrMsgConstant.PROMPT.exception("激活邮件已过期");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 激活用户
        return userMapper.activeEmailUser(email);
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public UserFilter resetPasswordByPhone(RegisterData data) {
        data.validFilter();

        UserFilter filter = new UserFilter();
        filter.setAccount(data.getAccount());
        filter.setPhone(data.getPhone());
        List<UserVo> userVoList = userMapper.retrieve(filter);
        if (userVoList != null && !(userVoList.size() > 0)) {
            throw ErrMsgConstant.PROMPT.exception("用户名错误！");
        }
        filter.setPassword(data.getPassword());
        SmsType smsType = getSmsType(data.getVerCodeType());
        int code = smsUtil.validateMsgCode(data.getPhone(), data.getVerCode(), smsType, data.isSingleTime());
//		int code = 0;
        if (code == 0) {
            userMapper.resetPasswordByPhone(filter);
            return filter;
        } else {
            throw ErrMsgConstant.PROMPT.exception("验证码错误请重新输入或者重新获取");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public UserFilter resetPasswordByEmail(RegisterData data) {
//		data.validFilter();

        if (checkEmail((data.getEmail()))) {
            throw ErrMsgConstant.PROMPT.exception("该邮箱尚未注册！请先注册。");
        }
        UserFilter filter = new UserFilter();
        filter.setAccount(data.getAccount());
        filter.setPassword(data.getPassword());
        filter.setEmail(data.getEmail());
        // 验证邮箱验证码
        int code = validateEmailCode(data.getEmail(), data.getVerCode(), true);
        if (code == 0) {
            userMapper.resetPasswordByEmail(filter);
            return filter;
        } else {
            throw ErrMsgConstant.PROMPT.exception("验证码错误请重新输入或者重新获取");
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public UserFilter changePassword(RegisterData data) {
        UserFilter filter = new UserFilter();
        filter.setAccount(data.getAccount());
        filter.setPassword(data.getPassword());
        List<UserVo> userVoList = userMapper.retrieve(filter);
        if (!(userVoList.size() > 0)) {
            throw ErrMsgConstant.PROMPT.exception("用户名或密码错误！");
        }
        filter.setNewPassword(data.getNewPassword());
        try {
            userMapper.changePassword(filter);
        } catch (Exception e) {
            throw ErrMsgConstant.PROMPT.exception("修改密码出错！");
        }
        return filter;
    }

    private User getUser(RegisterData data) {
        User user = new User();
        user.setAccount(data.getAccount());
        user.setCreateTime(DateUtil.longDateTime());
        user.setPassword(data.getPassword());
        user.setPhone(data.getPhone());
        user.setEmail(data.getEmail());
        user.setTerm(data.getTerm());
        user.setNickname(data.getNickname());
        user.setUuid(IDGenerator.UUID.generate());
        user.setStatus("1");
        return user;
    }

    private SmsType getSmsType(String VerCodeType) {
        SmsType smsType = null;
        switch (VerCodeType) {
            case "1":
                // 注册
                smsType = SmsType.REGISTER;
                break;
            case "2":
                // 重置密码
                smsType = SmsType.RESET_PWD;
                break;
            case "3":
                // 登录
                smsType = SmsType.LOGIN;
                break;

            default:
                break;
        }
        return smsType;
    }

    private int validateEmailCode(String email, String inputCode, boolean isSingleTime) {
        String redisKey = email;
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
