
package com.wangwei.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

/* *
 *类名：UtilDate
 *功能：自定义订单类
 *详细：工具类，可以用作获取系统日期、订单编号等
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class UtilDate {
	
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";

	public static final String dtShort2                 = "yyyy-MM-dd";

	/** 年月日(无下划线) yyyy年MM月dd日 */
	public static final String dtShort3                 = "yyyy年MM月dd日";
	
    
    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
	public synchronized static String getOrderNum(Date date){
		StringBuffer sb = new StringBuffer();
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		sb.append(df.format(date));
		Random random = new Random();
		for(int i=0;i < 8;i++){
			int randomNum = random.nextInt(10);
			sb.append(randomNum);
		}
		return sb.toString();
	}
	
	/**
	 * 格式化获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public  static String getDateFormatter(Date date){
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/**
	 * 格式化想要的年月日(精确到毫秒)，格式：yyyyMMdd
	 * @return
	 */
	public  static String getDateFormatToDate(Date date){
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 获取三个月前的时间，格式：Date
	 * @return
	 */
	public  static Date getDateThreeMonth(Date date){
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(date);//把当前时间赋给日历
		calendar.add(Calendar.MONTH, -3); //设置查询最开始的时间啊（三个月前，当前时间往前推三个月	）
		return calendar.getTime();
	}
	
	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * @return
	 */
	public static String getDate(Date date){
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}

	public static Date parseToDate(String dateStr,String type){
		DateFormat df = new SimpleDateFormat(type);
		try{
			return df.parse(dateStr);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将Unix时间戳转换成日期
	 * @param timestamp 时间戳
	 * @return String 日期字符串
	 */
	public static String unixTimestampToDate(long timestamp) {
		SimpleDateFormat sd = new SimpleDateFormat(simple);
		sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sd.format(new Date(timestamp));
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * @param date 需要转换的日期 yyyy-MM-dd HH:mm:ss
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(simple).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	public static String parseDateToString(Date date,String type){
		try{
			DateFormat df = new SimpleDateFormat(type);
			return df.format(date);
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static Long getTimeStampAfterTime(Date date,Integer field,Integer offset){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(field,offset);
		return cal.getTime().getTime();
	}


	//由出生日期获得年龄
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	public static String parseDateToDate(String dateStr,String fromType,String toType){
		DateFormat df = new SimpleDateFormat(fromType);
		DateFormat df2 = new SimpleDateFormat(toType);
		try {
			Date date = df.parse(dateStr);
			return df2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) throws  Exception{
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
//		String a = "19900830";
//		Date date = sdf.parse(a);
//		int days = getAge(date);
//		System.out.println(days);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = df.parse("2017-08-30 12:01:00");
//		System.out.println(df.format(new Date(getTimeStampAfterTime(date))));

//		String beginTime = "2017-08-28 12:00:00";
//		Long afterTwelveTime = UtilDate.getTimeStampAfterTime(UtilDate.parseToDate(beginTime,UtilDate.simple),Calendar.DAY_OF_YEAR,1);
//		System.out.println(df.format(new Date(afterTwelveTime)));
		String orderNum = getOrderNum(new Date());
		System.out.println(orderNum);
		System.out.println(orderNum.length());
	}
	
}
