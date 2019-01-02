package com.dly.exception.code;

public class BasicErrCodes {
    public static final String ECODE_SUCCESS = "000000";			// 成功的返回代码
    public static final String ECODE_FILE_NOTFOUND = "FILE01";	// 文件不存在

    public static final ProjectErrorCode JNDI_LOOKUP_ERROR 		= new ProjectErrorCode("100001", "取JNDI的上下文时错误");
    public static final ProjectErrorCodeTwoArgs TXN_LOAD_PARAM_ERROR 	= new ProjectErrorCodeTwoArgs("100003", "从{0}的方法{1}生成参数时错误");
    public static final ProjectErrorCodeOneArg TASK_UNKNOW_CMD 			= new ProjectErrorCodeOneArg("100006", "未知的流程处理命令:{0}");
    public static final ProjectErrorCodeTwoArgs TXN_EXEC_ERROR 			= new ProjectErrorCodeTwoArgs("100010", "执行命令{0}时错误:{1}");
    public static final ProjectErrorCodeOneArg TXN_EXEC_ERROR1 			= new ProjectErrorCodeOneArg("100010", "执行命令{0}时错误");
    public static final ProjectErrorCodeOneArg TXN_USERNAME_ERROR		= new ProjectErrorCodeOneArg("100011", "错误的用户名,{0}");
    public static final ProjectErrorCode TXN_USERNAME_ERROR1		= new ProjectErrorCode("100011", "错误的用户名,可能没有签到");
    public static final ProjectErrorCode TXN_INPUT_ERROR		= new ProjectErrorCode("100012", "输入参数错误");

    public static final ProjectErrorCodeOneArg FILE_NOTEXIST 			= new ProjectErrorCodeOneArg("FILE01", "文件[{0}]不存在");
    public static final ProjectErrorCodeOneArg FILE_OPEN_ERROR 			= new ProjectErrorCodeOneArg("FILE02", "打开文件[{0}]时错误");
    public static final ProjectErrorCodeOneArg FILE_OPEN_ERROR1 		= new ProjectErrorCodeOneArg("FILE02", "打开文件错误:{0}");
    public static final ProjectErrorCodeOneArg FILE_READERROR 			= new ProjectErrorCodeOneArg("FILE03", "读取文件[{0}]错误");
    public static final ProjectErrorCode FILE_READERROR2 			= new ProjectErrorCode("FILE03", "读取文件时错误");
    public static final ProjectErrorCodeOneArg FILE_WRITEERROR 			= new ProjectErrorCodeOneArg("FILE04", "写文件[{0}]错误");
    public static final ProjectErrorCodeTwoArgs FILE_WRITEERROR1 		= new ProjectErrorCodeTwoArgs("FILE04", "写文件[{0}]错误,编码集{1}错误");
    public static final ProjectErrorCode FILE_WRITEERROR2 		= new ProjectErrorCode("FILE04", "写文件时错误");
    public static final ProjectErrorCodeOneArg FILE_WRITEERROR3 		= new ProjectErrorCodeOneArg("FILE04", "写文件时错误:{0}");
    public static final ProjectErrorCode FILE_ISNULL 				= new ProjectErrorCode("FILE05", "文件名称是空");
    public static final ProjectErrorCode FILE_NAME_ISNULL			= new ProjectErrorCode("FILE05", "文件名称为空");
    public static final ProjectErrorCodeOneArg FILE_DOWNLOAD_ERROR 		= new ProjectErrorCodeOneArg("FILE06", "下载文件[{0}]错误");
    public static final ProjectErrorCodeOneArg FILE_DOWNLOAD_ERROR1 	= new ProjectErrorCodeOneArg("FILE06", "下载文件错误:{0}");
    public static final ProjectErrorCode FILE_DOWNLOAD_ERROR2 	= new ProjectErrorCode("FILE06", "下载文件错误");
    public static final ProjectErrorCode FILE_UPLOAD_ERROR 		= new ProjectErrorCode("FILE07", "取上传文件错误");
    public static final ProjectErrorCodeOneArg FILE_DELETE_ERROR 		= new ProjectErrorCodeOneArg("FILE08", "删除文件[{0}]时错误");
    public static final ProjectErrorCodeOneArg FILE_CREATE_ERROR 		= new ProjectErrorCodeOneArg("FILE09", "创建文件[{0}]时错误");
    public static final ProjectErrorCodeOneArg FILE_CLOSE_ERROR 		= new ProjectErrorCodeOneArg("FILE10", "关闭文件[{0}]错误");
    public static final ProjectErrorCodeOneArg FILE_ALREADY_EXIST 		= new ProjectErrorCodeOneArg("FILE11", "文件[{0}]已经存在");
    public static final ProjectErrorCodeOneArg FILE_CREATEPATH_ERROR 	= new ProjectErrorCodeOneArg("FILE12", "创建目录[{0}]时错误");
    public static final ProjectErrorCodeOneArg FILE_PATH_NOTFOUND		= new ProjectErrorCodeOneArg("FILE13", "目录[{0}]不存在");
    public static final ProjectErrorCodeTwoArgs FILE_RENAME_ERROR 		= new ProjectErrorCodeTwoArgs("FILE14", "修改文件{0}->{1}名称时错误");
    public static final ProjectErrorCode FILE_CREATE_EXCEL_ERROR 	= new ProjectErrorCode("FILE15", "生成EXCEL文档时错误");
    public static final ProjectErrorCodeOneArg FILE_CALC_CRC32_ERROR 	= new ProjectErrorCodeOneArg("FILE16", "计算文件{0}的CRC时错误");
    public static final ProjectErrorCodeOneArg FILE_CREATE_ZIP_ERROR 	= new ProjectErrorCodeOneArg("FILE18", "创建压缩文件[{0}]时错误");
    public static final ProjectErrorCode FILE_UPLOAD_READERROR	= new ProjectErrorCode("FILE19", "读取上传文件时错误");
    public static final ProjectErrorCodeOneArg FILE_ENGINE_NOTFOUND		= new ProjectErrorCodeOneArg("FILE66", "没有找到文件{0}的生成方式");
    public static final ProjectErrorCodeOneArg FILE_DOWNLOAD_LIMIT 		= new ProjectErrorCodeOneArg("FILE20", "没有下载文件[{0}]的权限");
    public static final ProjectErrorCode FILE_ZIP_READERROR		= new ProjectErrorCode("FILE21", "读取压缩文件时错误");


    public static final ProjectErrorCodeOneArg LOAD_CONFIGERROR 		= new ProjectErrorCodeOneArg("100050", "加载系统配置参数{0}时错误");
    public static final ProjectErrorCodeOneArg LOAD_DBCONF_ERROR 		= new ProjectErrorCodeOneArg("100051", "加载数据表配置文件时错误:{0}");
    public static final ProjectErrorCodeTwoArgs ACTION_DUPLICATE 		= new ProjectErrorCodeTwoArgs("100055", "交易码{0}在类{1}已经存在");
    public static final ProjectErrorCodeOneArg TXN_CONFIG_NOTFOUND		= new ProjectErrorCodeOneArg("100056", "交易{0}的配置信息不存在");
    public static final ProjectErrorCodeTwoArgs ACTION_DISABLED			= new ProjectErrorCodeTwoArgs("100058", "交易{0}已经被禁止:{1}");
    public static final ProjectErrorCodeTwoArgs ACTION_FORWARD_ERROR 	= new ProjectErrorCodeTwoArgs("100059", "交易{0}的导航页面{1}不存在");
    public static final ProjectErrorCode DAO_CONFIG_DUPLICATE 	= new ProjectErrorCode("100061", "DAO配置信息重复");
    public static final ProjectErrorCodeOneArg DAO_CONFIG_NOTFOUND		= new ProjectErrorCodeOneArg("100062", "数据表{0}的配置信息不存在");
    public static final ProjectErrorCodeOneArg DAO_SEQUENCE_ERROR		= new ProjectErrorCodeOneArg("100063", "获取Sequence[{0}]时错误");
    public static final ProjectErrorCodeOneArg DAO_AUTO_COLUMN_ERROR	= new ProjectErrorCodeOneArg("100064", "服务端生成的变量[{0}]没有定义转换函数");

    public static final ProjectErrorCode SCANNER_BRACKET_MISS = new ProjectErrorCode("SCN001", "括号不匹配");
    public static final ProjectErrorCode SCANNER_FROM_MISS 	= new ProjectErrorCode("SCN002", "没有找到[FROM]关键字");
    public static final ProjectErrorCode SCANNER_ORDER_BY_MISS= new ProjectErrorCode("SCN003", "[ORDER]关键字后没有找到[BY]关键字");
    public static final ProjectErrorCodeOneArg SCANNER_VARIABLE_MISS= new ProjectErrorCodeOneArg("SCN004", "变量[{0}]定义错误");
    public static final ProjectErrorCode EMPTY_VALUE_ERROR	= new ProjectErrorCode("SCN005", "生成SQL语句时空值错误");
    public static final ProjectErrorCode NO_SELECT_ERROR		= new ProjectErrorCode("SCN006", "SQL语句没有找到SELECT关键字");
    public static final ProjectErrorCode SQL_PARSER_ERROR		= new ProjectErrorCode("SCN007", "解析SQL语句错误");

    public static final ProjectErrorCodeOneArg LIST_LOADFILE_ERROR		= new ProjectErrorCodeOneArg("100070", "加载代码文件{0}时错误");
    public static final ProjectErrorCode LIST_LOADMODULE_ERROR	= new ProjectErrorCode("100071", "从模块文件加载代码时错误");

    public static final ProjectErrorCodeOneArg CHARSET_ERROR 		= new ProjectErrorCodeOneArg("100201", "不支持编码集:{0}");
    public static final ProjectErrorCode KEY_DATA_ERROR 		= new ProjectErrorCode("100211", "密钥数据错误");
    public static final ProjectErrorCode KEY_FILE_ERROR 		= new ProjectErrorCode("100211", "密钥文件错误");
    public static final ProjectErrorCode KEY_PADDING_ERROR 	= new ProjectErrorCode("100212", "数据块长度或填充信息错误");
    public static final ProjectErrorCode NO_DES_ALGORITHM 	= new ProjectErrorCode("100213", "不支持DES算法");
    public static final ProjectErrorCode NO_MD5_ALGORITHM 	= new ProjectErrorCode("100214", "不支持MD5算法");
    public static final ProjectErrorCode ENCRYPT_KEY_ERROR 	= new ProjectErrorCode("100216", "加密错误:获取密钥错误");
    public static final ProjectErrorCode ENCRYPT_DATA_ERROR 	= new ProjectErrorCode("100216", "加密错误:加密数据错误");
    public static final ProjectErrorCode DECRYPT_KEY_ERROR 	= new ProjectErrorCode("100217", "解密错误:获取密钥错误");
    public static final ProjectErrorCode DECRYPT_DATA_ERROR 	= new ProjectErrorCode("100217", "解密错误:解密数据错误");
    public static final ProjectErrorCode GET_SIGN_ERROR 		= new ProjectErrorCode("100218", "生成签名时错误");
    public static final ProjectErrorCode CHECK_SIGN_ERROR 	= new ProjectErrorCode("100219", "检查签名数据错误:数据不一致");
    public static final ProjectErrorCode GET_MD5_ERROR 		= new ProjectErrorCode("100220", "计算消息摘要时错误");
    public static final ProjectErrorCode KEY_GEN_ERROR 		= new ProjectErrorCode("100221", "生成密钥错误");
    public static final ProjectErrorCode KEY_LOAD_ERROR 		= new ProjectErrorCode("100222", "获取密钥数据错误");
    public static final ProjectErrorCode RANDOM_CODE_ERROR 	= new ProjectErrorCode("100223", "验证码错误");
    public static final ProjectErrorCode LISCENE_CODE_ERROR 	= new ProjectErrorCode("100224", "注册码错误");
    public static final ProjectErrorCode LISCENE_EXPIRATION 	= new ProjectErrorCode("100225", "注册码已经过期");

    public static final ProjectErrorCodeOneArg VALID_VALUE_ISNULL 	= new ProjectErrorCodeOneArg("100401", "数据项{0}为空");
    public static final ProjectErrorCodeThreeArgs VALID_VALUE_TOLONG 	= new ProjectErrorCodeThreeArgs("100402", "数据项{0}的长度{1}超过最大长度{2}");
    public static final ProjectErrorCodeThreeArgs VALID_VALUE_LENGTH 	= new ProjectErrorCodeThreeArgs("100402", "数据项{0}的长度{1}必须是{2}");
    public static final ProjectErrorCodeOneArg VALID_FORMAT_ERROR 	= new ProjectErrorCodeOneArg("100403", "数据项{0}格式错误");
    public static final ProjectErrorCode DATE_MONTH_ERROR		= new ProjectErrorCode("100404", "日期的格式错误，月只能是[1~12]" );
    public static final ProjectErrorCode DATE_DAY_ERROR		= new ProjectErrorCode("100404", "日期的格式错误，日只能是[1~31]" );
    public static final ProjectErrorCode DATE_YEAR_ERROR 		= new ProjectErrorCode("100405", "日期的格式错误，年错误");
    public static final ProjectErrorCode DATE_FORMAT_ERROR 	= new ProjectErrorCode("100406", "日期的格式错误");
    public static final ProjectErrorCode FORMAT_MONEY_ERROR	= new ProjectErrorCode("100409", "金额的格式错误，只能精确到仟亿，小数点只能两位" );
    public static final ProjectErrorCodeOneArg FORMAT_CODE_ERROR	= new ProjectErrorCodeOneArg("10040a", "数据字典[{0}]的格式错误" );

    public static final ProjectErrorCode CONF_NOT_FOUND 		= new ProjectErrorCode("100501", "配置信息没有找到");
    public static final ProjectErrorCode MENU_NOT_INIT 		= new ProjectErrorCode("100502", "菜单文件没有初始化");
    public static final ProjectErrorCodeOneArg MENU_NODE_NOT_FOUND 	= new ProjectErrorCodeOneArg("100503", "菜单代码{0}没有找到");
    public static final ProjectErrorCode MENU_CONF_ERROR		= new ProjectErrorCode("100504", "菜单节点没有配置名称或ID");
    public static final ProjectErrorCodeOneArg MENU_INCLUDE_ERROR	= new ProjectErrorCodeOneArg("100505", "菜单节点{0}的部分include中没有file-name属性");

    public static final ProjectErrorCodeOneArg TXN_LOAD_PROXY_ERROR = new ProjectErrorCodeOneArg("101000", "加载交易代理类{0}时错误");
    public static final ProjectErrorCode EJB_NOTFOUND 		= new ProjectErrorCode("101001", "找不到EJB容器");
    public static final ProjectErrorCodeOneArg EJB_CONNECT_ERROR 	= new ProjectErrorCodeOneArg("101002", "连接EJB容器{0}时错误");
    public static final ProjectErrorCodeTwoArgs EJB_CALL_ERROR 		= new ProjectErrorCodeTwoArgs("101003", "调用EJB服务{0}:{1}时错误");
    public static final ProjectErrorCode EJB_REGISTER_ERROR 	= new ProjectErrorCode("101004", "注册EJB服务时错误");
    public static final ProjectErrorCodeOneArg BEAN_NOTFOUND 		= new ProjectErrorCodeOneArg("101011", "业务组件{0}不存在");
    public static final ProjectErrorCode BEAN_CALL_ERROR 		= new ProjectErrorCode("101013", "调用业务组件时错误");
    public static final ProjectErrorCodeOneArg BEAN_LOAD_ERROR 		= new ProjectErrorCodeOneArg("101014", "加载业务组件{0}时错误");
    public static final ProjectErrorCodeOneArg BEAN_TYPE_ERROR 		= new ProjectErrorCodeOneArg("101015", "业务组件类型{0}错误");
    public static final ProjectErrorCodeOneArg CLASS_TYPE_ERROR 	= new ProjectErrorCodeOneArg("101016", "服务类{0}的类型错误");
    public static final ProjectErrorCodeOneArg DAO_CLASS_TYPE_ERROR = new ProjectErrorCodeOneArg("101016", "DAO类{0}的类型错误");
    public static final ProjectErrorCode LOG_MONITOR_ERROR 	= new ProjectErrorCode("101017", "注册监控信息时错误");
    public static final ProjectErrorCode LOG_MONITOR_IS_OVER	= new ProjectErrorCode("101018", "监控队列最多支持8个并发用户");

    public static final ProjectErrorCodeTwoArgs BEAN_METHOD_NOTFOUND 	= new ProjectErrorCodeTwoArgs("101021", "业务组件{0}的方法{1}不存在");
    public static final ProjectErrorCodeOneArg BEAN_LOADMODULE_ERROR 	= new ProjectErrorCodeOneArg("101048", "加载服务模块{0}时错误");
    public static final ProjectErrorCodeOneArg BEAN_LOADCLASS_ERROR 	= new ProjectErrorCodeOneArg("101049", "加载类{0}时错误");
    public static final ProjectErrorCode BEAN_LOADCLASS_ERROR1 	= new ProjectErrorCode("101049", "加载模块中的类时错误");
    public static final ProjectErrorCodeOneArg TXN_GEN_METHOD_ERROR 	= new ProjectErrorCodeOneArg("101050", "生成交易的接口类{0}时错误");
    public static final ProjectErrorCode TXN_NO_STEP 				= new ProjectErrorCode("101051", "交易没有处理流程");
    public static final ProjectErrorCodeTwoArgs TXN_ENTRY_NOTFOUND		= new ProjectErrorCodeTwoArgs("101052", "没有找到交易[{0}:{1}]的处理函数" );


    public static final ProjectErrorCodeTwoArgs JAVA_METHOD_NOTFOUND 	= new ProjectErrorCodeTwoArgs("103101", "类{0}中没有找到方法{1}");
    public static final ProjectErrorCodeOneArg JAVA_CLASS_NOTFOUND 		= new ProjectErrorCodeOneArg("103102", "类{0}没有定义");
    public static final ProjectErrorCodeOneArg JAVA_CLASS_NOTINSTANCE 	= new ProjectErrorCodeOneArg("103103", "类{0}不能被实例化");
    public static final ProjectErrorCodeOneArg JAVA_ACCESS_LIMIT 		= new ProjectErrorCodeOneArg("103104", "类{0}没有存取权限");
    public static final ProjectErrorCodeTwoArgs JAVA_METHOD_LIMIT 		= new ProjectErrorCodeTwoArgs("103104", "没有权限访问类{0}的方法{1}");
    public static final ProjectErrorCodeTwoArgs JAVA_INVOCATE_EXCEPTION 	= new ProjectErrorCodeTwoArgs("103105", "调用{0}的方法{1}异常");
    public static final ProjectErrorCode JAVA_THREAD_INTERRUPTED 	= new ProjectErrorCode("103110", "JAVA线程被中断");
    public static final ProjectErrorCodeOneArg JAVA_OTHER_ERROR 		= new ProjectErrorCodeOneArg("103111", "调用JAVA服务时异常错误:{0}");
    public static final ProjectErrorCode JAVA_COMPILE_EXCEPTION 	= new ProjectErrorCode("103201", "编译组件时错误");
    public static final ProjectErrorCodeOneArg JAVA_COMPILE_ERROR		= new ProjectErrorCodeOneArg("103201", "编译文件{0}时错误");
    public static final ProjectErrorCode JAVA_PARSER_ERROR 		= new ProjectErrorCode("103202", "解析JAVA源文件时错误");
    public static final ProjectErrorCode JAVA_OUT_MEMORY 			= new ProjectErrorCode("103203", "内存溢出");
    public static final ProjectErrorCodeTwoArgs JAVA_OUT_MEMORY1 		= new ProjectErrorCodeTwoArgs("103203", "执行{0}方法{1}时内存溢出");
    public static final ProjectErrorCodeTwoArgs JAVA_PARAMETER_ERROR 	= new ProjectErrorCodeTwoArgs("103204", "生成{0}方法{1}的输入参数时错误");
    public static final ProjectErrorCode JAVA_FORMAT_ERROR		= new ProjectErrorCode("103205", "格式化类文件时错误");

    public static final ProjectErrorCodeOneArg SERIAL_BEAN_NOTFOUND 	= new ProjectErrorCodeOneArg("104101", "没有找到序列号{0}的函数");
    public static final ProjectErrorCode SERIAL_KEYCOLUMN_ISNULL 	= new ProjectErrorCode("104102", "没有指定分类字段名称");
    public static final ProjectErrorCode ECODE_CONFIG_ERROR		= new ProjectErrorCode("104111", "错误代码格式错误");

    public static final ProjectErrorCodeOneArg XML_PARSER_ERROR 		= new ProjectErrorCodeOneArg("105001", "解析XML文档{0}时错误");
    public static final ProjectErrorCodeOneArg XML_PARSER_ERROR2 	= new ProjectErrorCodeOneArg("105001", "解析XML文档时错误,{0}");
    public static final ProjectErrorCodeOneArg XML_CREATE_ERROR 	= new ProjectErrorCodeOneArg("105002", "生成XML文档{0}时错误");
    public static final ProjectErrorCodeOneArg XML_CREATE_ERROR2 	= new ProjectErrorCodeOneArg("105002", "生成XML文档时错误:{0}");
    public static final ProjectErrorCodeOneArg DTD_PARSER_ERROR 	= new ProjectErrorCodeOneArg("105003", "解析DTD文档{0}时错误");
    public static final ProjectErrorCodeOneArg XML_ELEMENT_NOFOUND 	= new ProjectErrorCodeOneArg("105101", "XML文件中节点{0}不存在");
    public static final ProjectErrorCodeOneArg XML_KEYNAME_NOFOUND 	= new ProjectErrorCodeOneArg("105102", "查找节点记录时，没有指定{0}的关键字字段");
    public static final ProjectErrorCode XML_OUTPUT_NOFOUND 	= new ProjectErrorCode("105103", "没有指定输出数据的节点");
    public static final ProjectErrorCode XML_ADD_PROPERTY_ERROR = new ProjectErrorCode("105103", "不在节点内，不能增加属性" );
    public static final ProjectErrorCode HTML_PARSER_ERROR1 	= new ProjectErrorCode("105201", "解析HTML内容时错误");

    public static final ProjectErrorCodeOneArg JSON_PARSER_ERROR 	= new ProjectErrorCodeOneArg("105051", "解析JSON文档{0}时错误");
    public static final ProjectErrorCodeOneArg JSON_PARSER_ERROR2 	= new ProjectErrorCodeOneArg("105051", "解析JSON文档时错误,{0}");
    public static final ProjectErrorCode JSON_PARSER_ERROR3 	= new ProjectErrorCode("105051", "解析JSON数据时错误");

    public static final ProjectErrorCode JTA_CONTEXT_ERROR 	= new ProjectErrorCode("102111", "生成JTA的上下文时错误");
    public static final ProjectErrorCodeOneArg JTA_JNDI_NOTFOUND 	= new ProjectErrorCodeOneArg("102112", "取JTA事务的JNDI{0}时错误");
    public static final ProjectErrorCode JTA_BEGIN_ERROR		= new ProjectErrorCode("102113", "启动JTA事务时错误");
    public static final ProjectErrorCode JTA_COMMIT_ERROR		= new ProjectErrorCode("102114", "处理JTA事务时错误");

    public static final ProjectErrorCodeOneArg EBD_OPEN_ERROR		= new ProjectErrorCodeOneArg("103114", "打开内嵌数据库{0}时错误");

    public static final ProjectErrorCodeOneArg INST_ADD_ERROR		= new ProjectErrorCodeOneArg("TASK81", "增加指令时错误:{0}");
    public static final ProjectErrorCode INST_ADD_INTERRUPT	= new ProjectErrorCode("TASK81", "增加指令时发生了中断");
    public static final ProjectErrorCodeOneArg INST_SAVE_ERROR		= new ProjectErrorCodeOneArg("TASK82", "保存指令到文件{0}时错误");
    public static final ProjectErrorCodeOneArg INST_RESTORE_ERROR	= new ProjectErrorCodeOneArg("TASK82", "从文件{0}恢复指令时错误");

    public static final ProjectErrorCodeOneArg CACHE_FILE_NOTFOUND	= new ProjectErrorCodeOneArg("CACH01", "缓存文件{0}不存在");
    public static final ProjectErrorCodeOneArg CACHE_SAVE_ERROR		= new ProjectErrorCodeOneArg("CACH02", "保存缓存数据{0}错误");
    public static final ProjectErrorCodeOneArg CACHE_READ_ERROR		= new ProjectErrorCodeOneArg("CACH03", "读取缓存数据{0}错误");

    public static final ProjectErrorCodeOneArg JMS_JNDI_ERROR 		= new ProjectErrorCodeOneArg("JMS001", "查找JMS的JNDI[{0}]时错误");
    public static final ProjectErrorCodeOneArg JMS_INIT_ERROR 		= new ProjectErrorCodeOneArg("JMS002", "初始化JMS[{0}]时错误");
    public static final ProjectErrorCodeOneArg JMS_SEND_ERROR 		= new ProjectErrorCodeOneArg("JMS003", "发送消息[{0}]时错误");
    public static final ProjectErrorCodeOneArg JMS_RECV_ERROR 		= new ProjectErrorCodeOneArg("JMS004", "接收消息[{0}]时错误");
    public static final ProjectErrorCodeOneArg JMS_NO_CONFIG 		= new ProjectErrorCodeOneArg("JMS005", "没有配置JMS[{0}]");
    public static final ProjectErrorCode JMS_START_ERROR 		= new ProjectErrorCode("JMS006", "启动JMS服务线程时错误");
    public static final ProjectErrorCode JMS_PROCESS_ERR 		= new ProjectErrorCode("JMS007", "处理异步消息时异常错误");


    // SMTP
    public static final ProjectErrorCode MAIL_SEND_ADDR_ERROR = new ProjectErrorCode( "MAIL01", "发件人地址格式错误" );
    public static final ProjectErrorCode MAIL_TO_ADDR_ERROR 	= new ProjectErrorCode( "MAIL02", "TO[收件人]邮件地址错误" );
    public static final ProjectErrorCode MAIL_CC_ADDR_ERROR 	= new ProjectErrorCode( "MAIL03", "CC[抄送人]人邮件地址错误" );
    public static final ProjectErrorCode MAIL_BCC_ADDR_ERROR 	= new ProjectErrorCode( "MAIL04", "BCC[暗送人]人邮件地址错误" );
    public static final ProjectErrorCode SET_SENDTIME_ERROR 	= new ProjectErrorCode( "MAIL05", "设置发送时间时错误" );
    public static final ProjectErrorCode SET_SUBJECT_ERROR 	= new ProjectErrorCode( "MAIL06", "设置邮件的主题错误" );
    public static final ProjectErrorCode SET_MAILBODY_ERROR 	= new ProjectErrorCode( "MAIL07", "设置邮件内容时错误" );
    public static final ProjectErrorCode SET_ATTACH_ERROR 	= new ProjectErrorCode( "MAIL08", "设置邮件的附件时错误" );
    public static final ProjectErrorCode SEND_MAIL_ERROR 		= new ProjectErrorCode( "MAIL09", "发送邮件时错误" );
    public static final ProjectErrorCode MAIL_NO_LISTENER 		= new ProjectErrorCode( "MAIL10", "没有定义接收邮件的动作" );

    // POP3
    public static final ProjectErrorCodeOneArg OPEN_POP3_ERROR 		= new ProjectErrorCodeOneArg( "MAIL11", "连接邮件服务器[{0}]错误" );
    public static final ProjectErrorCode RECV_MAIL_ERROR 		= new ProjectErrorCode( "MAIL12", "接收邮件时错误" );
    public static final ProjectErrorCode DEL_MAIL_ERROR 		= new ProjectErrorCode( "MAIL13", "删除邮件时错误" );
    public static final ProjectErrorCode GET_SUBJECT_ERROR	= new ProjectErrorCode( "MAIL14", "取邮件的标题错误" );
    public static final ProjectErrorCode GET_MAILBODY_ERROR	= new ProjectErrorCode( "MAIL15", "取邮件的正文时错误" );
    public static final ProjectErrorCode MAIL_FORMAT_ERROR 	= new ProjectErrorCode( "MAIL16", "未知的邮件正文格式" );
    public static final ProjectErrorCodeOneArg MAIL_FORMAT_ERROR1 	= new ProjectErrorCodeOneArg( "MAIL16", "未知的邮件正文格式:{0}" );

    // 邮件内容
    public static final ProjectErrorCode CREATE_MAILBODY_ERROR 	= new ProjectErrorCode( "MAIL30", "生成邮件的主体时错误" );
    public static final ProjectErrorCode PARSER_MAILBODY_ERROR 	= new ProjectErrorCode( "MAIL31", "解析邮件的主体时错误" );
    public static final ProjectErrorCodeOneArg ATTACHMENT_NOEXIST_ERROR = new ProjectErrorCodeOneArg( "MAIL32", "邮件的的附件[{0}]不存在" );

    // 身份证
    public static final ProjectErrorCode CHK_IDCARD_LENGTH 	= new ProjectErrorCode("card01", "身份证长度错误");
    public static final ProjectErrorCode CHK_IDCARD_YEAR 		= new ProjectErrorCode("card02", "身份证年份错误");
    public static final ProjectErrorCode CHK_IDCARD_MONTH 	= new ProjectErrorCode("card03", "身份证月份错误");
    public static final ProjectErrorCode CHK_IDCARD_DAY 		= new ProjectErrorCode("card04", "身份证日错误");
    public static final ProjectErrorCode CHK_IDCARD_CHECK 	= new ProjectErrorCode("card05", "身份证校验码错误");
    public static final ProjectErrorCode CHK_IDCARD_NUMBER18 	= new ProjectErrorCode("card06", "身份证前17位必须是数字");
    public static final ProjectErrorCode CHK_IDCARD_NUMBER15 	= new ProjectErrorCode("card07", "身份证必须全是数字");

    // 加密/解密
    public static final ProjectErrorCode ENCODE_ERROR 	= new ProjectErrorCode("crypt1", "加密错误");
    public static final ProjectErrorCode DECODE_ERROR 	= new ProjectErrorCode("crypt2", "解密错误");

    // 其他错误
    public static final ProjectErrorCode ONLY_DEVELOPMENT		= new ProjectErrorCode("999901", "只有开发环境才能执行");
    public static final ProjectErrorCode ONLY_RUNTIME			= new ProjectErrorCode("999902", "只有运行环境才能执行");
    public static final ProjectErrorCodeOneArg CALL_SERVICE_ERROR	= new ProjectErrorCodeOneArg("999903", "调用服务{0}时产生未知错误");
    public static final ProjectErrorCodeTwoArgs CALL_METHOD_ERROR	= new ProjectErrorCodeTwoArgs("999904", "调用{0}的方法{1}时产生未知错误");
    public static final ProjectErrorCode EXEC_OTHER_ERROR		= new ProjectErrorCode("999905", "执行程序时产生未知错误");
    public static final ProjectErrorCode TXN_OTHER_ERROR		= new ProjectErrorCode("999999", "未知的错误");

    public static final ProjectErrorCode REDIS_SERIALIZE_ERR = new ProjectErrorCode("JDS001", "对象序列化错误");
    public static final ProjectErrorCode REDIS_DESERIALIZE_ERR = new ProjectErrorCode("JDS002", "对象反序列化错误");

    public static final ProjectErrorCodeOneArg FILTER_IS_EMPTY 	= new ProjectErrorCodeOneArg("VLD001", "输入条件[{0}]不能空");
    public static final ProjectErrorCodeTwoArgs DATASET_TOO_LARGE 	= new ProjectErrorCodeTwoArgs("VLD002", "数据[{0}/{1}]过大，请分页");

    // 数据服务
    public static final ProjectErrorCode DSE_CORP_NULL 		= new ProjectErrorCode("ds0001", "公司编号不能空");
    public static final ProjectErrorCode DSE_MSGID_NULL 	= new ProjectErrorCode("ds0002", "接口编号不能空");
    public static final ProjectErrorCode KEY_FILE_NULL		= new ProjectErrorCode("ds0003", "密钥文件不存在");
    public static final ProjectErrorCodeOneArg KEY_FILE_NOT_EXIST	= new ProjectErrorCodeOneArg("ds0004", "密钥文件[{0}]不存在");
    public static final ProjectErrorCodeOneArg OBJECT_NOT_EXIST	= new ProjectErrorCodeOneArg("ds0005", "实体对象[{0}]没有配置");
    public static final ProjectErrorCode INIT_OBJECT_ERROR 	= new ProjectErrorCode( "ds0078", "初始化对象错误" );

    // EXCEL导入服务
    public static final ProjectErrorCode IMPORT_FILE_ERROR 		    = new ProjectErrorCode("FILE70", "不能导入空文件！");
    public static final ProjectErrorCodeOneArg IMPORT_FILEHEADERCOUNT_ERROR = new ProjectErrorCodeOneArg("FILE71", "文件工作表{0}列头数量不一致！");
    public static final ProjectErrorCodeTwoArgs IMPORT_FILEHEADER_ERROR 		= new ProjectErrorCodeTwoArgs("FILE72", "文件工作表{0}第{1}列名称不一致！");
    public static final ProjectErrorCode LOAD_XLS_DATA_ERROR 		   	= new ProjectErrorCode( "FILE73", "map转化时出现异常" );
    public static final ProjectErrorCodeTwoArgs XLS_DATA_EMPTY 			= new ProjectErrorCodeTwoArgs("FILE74", "第[{0}]行的[{1}]为空");
    public static final ProjectErrorCodeTwoArgs XLS_DATA_ERROR 			= new ProjectErrorCodeTwoArgs("FILE75", "第[{0}]行的[{1}]格式不对");

    // DAO
    public static final ProjectErrorCode DAO_UUID_EMPTY	 			= new ProjectErrorCode("DAO003", "UUID不能空");
    public static final ProjectErrorCode DAO_INSERT_DUPLICATE 		= new ProjectErrorCode("DAO005", "增加记录时重复");
    public static final ProjectErrorCode DAO_INSERT_ERROR	 		= new ProjectErrorCode("DAO006", "增加记录错误");

    //Elastic
    public static final ProjectErrorCode ELASTIC_CREATE_ERROR 		= new ProjectErrorCode("Elas1", "添加失败");
    public static final ProjectErrorCode ELASTIC_UPDATE_ERROR 		= new ProjectErrorCode("Elas2", "修改失败");
    public static final ProjectErrorCode ELASTIC_DELETE_ERROR 		= new ProjectErrorCode("Elas3", "删除失败");

    public static final ProjectErrorCodeOneArg NOT_JAR_FILE		 		= new ProjectErrorCodeOneArg("SCAN08", "不是JAR文件[{0}]");

    // 阿里云短信
    public static final ProjectErrorCode ALIMESSAGE_REQUEST_PARAMETERS_ERROR = new ProjectErrorCode("ALIMESSAGE01", "阿里云短信接口请求参数异常");
    public static final ProjectErrorCode ALIMESSAGE_VERCODE_FREQUENTLY = new ProjectErrorCode("ALIMESSAGE02", "短信请求频繁，请稍后再试");
    public static final ProjectErrorCode ALIMESSAGE_VERCODE_REQUEST_FAIL = new ProjectErrorCode("ALIMESSAGE04", "短信请求失败");

}