package com.wangwei.util;


import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 工具类
 */
public class WebTool {

    /**
     * 获取项目基URL
     * <br>例子:http://www.baidu.com/proj
     * <br>例子:http://www.baidu.com:8080/proj
     * @param req
     * @return url地址
     */
    public static String getBasePath(HttpServletRequest req){
        String path = req.getContextPath();
        int port = req.getServerPort();
        if(port == 80){
            return req.getScheme()+"://"+req.getServerName()+path;
        }
        return req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path;
    }

    /**
     * 获取classPath下资源文件
     * @param path
     * @return
     */
    public static File getResourceFile(String path){
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:" + path);
        } catch (FileNotFoundException e) {
            return null;
        }
        return file;
    }
    
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
     * 将数字字符串转Decimal
     * @param number
     * @return
     */
    public static String getDecimalStr(String number){
    	Double cny = Double.parseDouble(number);//2.0   
    	DecimalFormat df = new DecimalFormat("0.00");   
    	String  amount = df.format(cny);  
    	return amount;
    }

    public static Map<String, String> parseRequestParams(HttpServletRequest request) throws Exception {
        Map<String,String> params = new HashMap();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            params.put(name, new String(valueStr.getBytes("ISO-8859-1"), "utf-8"));
        }
        return params;
    }

    public static String getParamValueFromByte(HttpServletRequest request, String paramName) throws UnsupportedEncodingException {
        return new String(request.getParameter(paramName).getBytes("ISO-8859-1"),"UTF-8");
    }

    public static void main(String[] args) {
		System.out.println(getDecimalStr("2").toString());
	}
}
