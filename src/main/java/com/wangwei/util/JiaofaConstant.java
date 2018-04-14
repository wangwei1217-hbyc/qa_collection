package com.wangwei.util;
/**
 * 参数常量类
*Title:
*Description: </p>
*Company:leway </p>
 */
public class JiaofaConstant {

    ////////////////////////////////////////////////////////////////
	/**
	 * 平安接口地址
	 */
	public static final String SERVICE_URL = "https://ebank.sdb.com.cn/khpayment/khpayment_alipay_whjf.do";
	
	public static final String AESKEY = "AliCityService28";
    /**
     * Session 常量 订单号
     */
    public static final String USER_ORDER = "user_order";

    /**
     * Session 常量 短信发送时间
     */
    public static final String DATE_TIME = "date_time";

    /**
     * Session 常量 用户openId
     */
    public static final String USER_OPENID = "user_openid";

    /**
     * Session 常量 决定书记录
     */
    public static final String USER_RECORD = "user_record";

    ////////////////////////////////////////////////////////////////

    /**
     * 平安支付商户号
     */
    public static final String MASTERID = "2000716174";
   // public static final String MASTERID = "WHJF000000";
    
    
    //测试环境商户号
   // public static final String MASTERID = "2000311146";

    /**
     * 支付平台号
     */
    public static final String PLANTID = "1000000006";

    /**
     * 人民币
     */
    public static final String RMB = "RMB";

    /**
     * 支付备注常量 决定书缴款
     */
    public static final String TYPE_1 = "JDSJK";

    /**
     * 支付备注常量 电子警察缴款
     */
    public static final String TYPE_2 = "DZJC";
    
    /**
     * 机动车业务
     */
    public static final String TYPE_3 = "JDCYW";
    /**
     * 驾驶证业务
     */
    public static final String TYPE_4 = "JSZYW";
    
    /**
     * 交通违章缴款
     * 楚联信接口
     */
    public static final String TYPE_5 = "JTWZJK";

    ////////////////////////////////////////////////////////////////

    /**
     * 收款机关类型
     */
    public static final String SKJGLX = "02";       //00 银行现金 01 网上银行 02 电商

    /**
     * 收款机关代码
     */
    public static final String SKJGDM = "EB4200000001";

    /**
     * 收款机关名称
     */
    public static final String SKJGMC = "支付宝平台";

    /**
     * 调用东软接口常量 执收单位名称
     */
    public static final String ZSDW_NAME = "武汉市公安交通管理局";

    /**
     * 调用东软接口常量 执收单位代码
     */
    public static final String ZSDW_CODE = "420100000000";

    /**
     * 调用东软接口常量 部门类型 交管局
     */
    public static final String DEPT_TYPE_1 = "J";

    /**
     * 调用东软接口常量 部门类型 出入境
     */
    public static final String DEPT_TYPE_2 = "C";

    /**
     * 调用东软接口常量 部门类型 户政处
     */
    public static final String DEPT_TYPE_3 = "H";
    
    /**
     * 支付类型
     * 1-银行卡；2-支付宝；3-微信
     */
    public static final String ZFLX = "2";
    
    /**
     * 业务类型 
     * 决定书缴款的填1，电子警察处理的填2
     * 东软李晓伟提供 
     */
    public static final String YWLX_JDSJK = "1";
    
    public static final String YWLX_DZJC = "2";
    
    //异步接口请求时间
    public static final int REQUEST_TIME =  60000;
    
    /**
     *  决定书记录
     */
    public static final String JDS_LIST = "jds_list";
    /**
     *  电子警察记录
     */
    public static final String DZJC_LIST = "dzjc_list";
    
    /**
     *  交通违章缴费记录
     */
    public static final String VIO_LIST = "VIO_LIST";
    
    
    /**
     *  规费缴费记录
     */
    public static final String JDC_LIST = "jdc_list";
    /**
     *  规费缴费记录
     */
    public static final String JSZ_LIST = "jsz_list";
    /**
     *  规费缴费记录
     */
    public static final String FEE_LIST = "fee_list";
    
    /**
     * 记录处理日志类型:决定书-1
     */
    public static final Integer FEE_TYPE_1 = 1;
    /**
     * 记录处理日志类型:电子警察-2
     */
    public static final Integer FEE_TYPE_2 = 2;
    
    /**
     *  记录处理日志类型:规费车管-3
     */
    public static final Integer LOG_TYPE_1 = 3;
    /**
     *  记录处理日志类型 :规费驾管-4
     */
    public static final Integer LOG_TYPE_2 = 4;
    
    /**
     *  记录处理日志类型 :本人非本车
     */
    public static final Integer LOG_TYPE_5 = 5;
    
    /**
     * 楚联信接口 -合并处理
     */
    public static final Integer LOG_TYPE_6 = 6;
    
    /**
     *  电子警察类型:1- 本人本车  2-本人非本车
     */
    public static final Integer DZJC_TYPE_1 = 1;
    
    public static final Integer DZJC_TYPE_2 = 2;
    
}
