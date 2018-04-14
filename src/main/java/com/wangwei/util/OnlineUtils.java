package com.wangwei.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OnlineUtils {
	 /**
     * 将数字字符串转Decimal
     * @param number
     * @return
     */
    public static BigDecimal getDecimal(String number){
        BigDecimal decimal = NumberUtils.createBigDecimal(number);
        decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        return decimal;
    }
    /**
     * 分割字符串
     * @param str
     * @param result
     * @return
     */
    public static String parseReason(String str, String result) {
        String reason = StringUtils.substringBetween(result, str + ":", ",");
        if (StringUtils.isEmpty(reason)) {
            reason = StringUtils.substring(result, result.indexOf(str + ":") + str.length() + 1);
        }
        return reason;
    } 
    
    /**
     * 获取订单编号
     * 解决订单号相同的问题
     * @return
     */
    public synchronized static String getOrderId() { 
        //20007161742016120346246589
    	//20007161742016120324537890
    	//20007161742015112832272702
    	Date date = new Date();
    	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateTime = format.format(date);
        return JiaofaConstant.MASTERID + dateTime +RandomGenerator.generateNumber();
    
       
    }
}
