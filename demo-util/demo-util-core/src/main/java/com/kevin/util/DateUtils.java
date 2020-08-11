package com.kevin.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ZhangDM(Mingly)
 * @date 2012-8-14
 * @description：时间工具类
 */

public class DateUtils {

	/**
	 * @description 得到当天时间字符串
	 * @return
	 * @author ZhangDM(Mingly)
	 * @date 2012-8-14
	 */
	private static final String formatStr = "HH:mm";
    private static SimpleDateFormat sdf=new SimpleDateFormat(formatStr);

	public static String getCurrentDate() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}

	public static String formatDate(String format, Date date) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String getCurrTimeStr() {
		return new SimpleDateFormat("hhmmss").format(new Date());
	}

	/**
	 * @description 将时间转化为符合要求的字符串
	 * @param date
	 * @param dateFormat
	 * @return
	 * @author ZhangDM(Mingly)
	 * @date 2012-11-8
	 */
	public static String parseDateToStrByFormat(Date date, String dateFormat) {
		if (date != null && StringUtils.isNotBlank(dateFormat)) {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * @Title: getCurrentGapTime
	 * @Description: 描述方法的功能
	 * @param minutes
	 * @return Date
	 * @author yang_chen
	 * @date 2013-11-29 上午10:20:06
	 */
	public static Date getCurrentGapTime(int minutes) {
		Calendar afterTime = Calendar.getInstance();
		afterTime.add(Calendar.MINUTE, minutes);
		Date gapDate = (Date) afterTime.getTime();
		return gapDate;
	}

	public static Date StringToDateByFormat(String dateStr, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String getDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String date = sdf.format(new Date());
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDate(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDateTime() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			long time = sdf.parse(sdf.format(new Date())).getTime();
			return time + "";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getCurrentDateTime() {
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//			long time = sdf.parse(sdf.format(new Date())).getTime();
//			return time + "";
			return getDateTime(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDateTime(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTime() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			return sdf.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getTime(Date date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date strToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return df.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date strToDate3(String str) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			return df.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date strToDate2(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateToStr(Date date) {
		if(null == date) {
			return null ;
		}
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}
	
	public static String dateToStr2(Date date) {
		if(null == date) {
			return null ;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
	
	public static String dateToStr3(Date date) {
		if(null == date) {
			return null ;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 检查生日字符串是否合法
	 * 
	 * @param dateStr
	 *            生日字符串
	 * @param pattern
	 *            日期格式
	 * @return 布尔
	 */
	public static boolean isValidBirthDate(String dateStr, String pattern) {

		if (dateStr == null || dateStr.trim().length() == 0) {
			return true;
		}

		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setLenient(false);
		try {
			Date date = df.parse(dateStr);
			int year = date.getYear();
			if (year < 0 || year > new Date().getYear()) // 如果生日小于1900年或大于当前日，认为错误
			{
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static String getCurrDateTimeStr() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}

	/**
	 * @Title: getCurrentDate
	 * @Description: 获取当前系统时间
	 * @return date：当前系统时间Date类型
	 * @author Sonny
	 * @date 2014-11-21 上午10:36:13
	 */
	public static Date getCurrentSystemDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		String dd = format.format(d);
		Date date = null;
		try {
			date = format.parse(dd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static Date getCurrentFormatDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String dd = format.format(d);
		Date date = null;
		try {
			date = format.parse(dd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @Title:getTimeDifference()
	 * @deprecated:获取两个时间差
	 * @param starDate
	 * @param endDate
	 * @return
	 */
	public static long getTimeDifference(Date starDate, Date endDate) {		
		long diff = endDate.getTime() - starDate.getTime();		
		long diffSecond = diff / 1000;
		long diffMinutes = diff / (1000 * 60);
		long diffHour = diff / (1000 * 60 * 60);
		long diffDays = diff / (1000 * 60 * 60 * 24);
		return diffMinutes;
	}
	/**
	 * 获取token失效时间，默认为1个月	 * 
	 * @return String
	 */
	public static Date getValiDate() {
		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。 		
		cal.add(Calendar.MONTH, 1);	
		return cal.getTime();
	}
	
	/**
	 * 按秒 加减日期
	 * 
	 * @param date
	 *            ：日期
	 * @param num
	 *            ：要加减的秒
	 * @return：成功，则返回加减后的日期；失败，则返回null
	 */
	public static Date addSeconds(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.SECOND, num);
		return c.getTime();
	}
	
	
	/**
	 * 按年加减日期
	 * 
	 * @param date
	 *            ：日期
	 * @param num
	 *            ：要加减的年数
	 * @return：成功，则返回加减后的日期；失败，则返回null
	 */
	public static Date addYears(Date date, int num) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, num);
		return c.getTime();
	}
	
	/**
	 * 获取对应Date的日期字符串，格式为yyyy-MM-dd
	 * 
	 * @param date
	 *            源Date
	 * @return String
	 */
	public static String getPrettyDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * 
	 * @Title: isMonthEnd  
	 * @Description: 判断给定日期是否是月底 
	 * @param date
	 * @return
	 * @author shicongyang
	 * @date 2017年5月23日 下午9:24:08
	 */
	public static boolean isMonthEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.get(Calendar.DATE) == 1;
	}	
	
	public static Date strToDate1(String str){
		DateFormat df=new SimpleDateFormat("yyyyMMdd");
		try {
		return df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* 获取月份起始日期
	* @param date
	* @return
	* @throws ParseException
	*/
	public static String getMinMonthDate(String date) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(date));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * 
	 * @Title: getCurrentSystemSeconds  
	 * @Description: 获取当天时间的秒数 （24制）
	 * @return
	 * @author shicongyang
	 * @date 2017年6月16日 下午3:04:34
	 */
	public static int getCurrentSystemSeconds() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String dd = format.format(d);
		Date date = null;
		int totalSeconds = 0;
		try {
			date = format.parse(dd);
			int hh = date.getHours();
			int mm = date.getMinutes();
			int ss = date.getSeconds();
			totalSeconds = hh*60*60+mm*60+ss;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return totalSeconds;
	}
	
   
    private static boolean isInZone(long tStart,long tEnd,long t) throws ParseException {
        return tStart <= t && t <= tEnd;
    }

    private static long getLong(String timeStr) throws ParseException {
    	System.out.println(sdf.parse(timeStr).getTime());
        return sdf.parse(timeStr).getTime();
    }
	public static boolean compareTime(String startTime,String endTime){
		Date date=new Date();
		String nowTime=sdf.format(date);
		try {
			return isInZone(getLong(startTime), getLong(endTime), getLong(nowTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @Title:getTimeDifference()
	 * @deprecated:获取两个时间差(返回时间)
	 * @param starDate
	 * @param endDate
	 * @return
	 */
	public static long getTimeDiffHour(Date starDate, Date endDate) {		
		long diff = endDate.getTime() - starDate.getTime();		
		long diffSecond = diff / 1000;
		long diffMinutes = diff / (1000 * 60);
		long diffHour = diff / (1000 * 60 * 60);
		long diffDays = diff / (1000 * 60 * 60 * 24);
		return diffHour;
	}
	public static void main(String[] args) throws ParseException {
//		String a = "20170228"; 
////		Date strToDate = strToDate(a);
//		System.out.println(getMinMonthDate(a));
//		String tS = "09:00";
//        String tE = "24:00";
//       System.out.println(compareTime(tS, tE));
		//System.out.println(getTimeDiffHour(strToDate2("2017-08-14 11:11:11"), new Date()));
		
		String a = getDateAfter(new Date(), 30);
		System.out.println(a);
	}
	
	/**
	 * 
	* @Description: 获取n天前时间
	* @param d
	* @param day
	* @return
	* @return String (yyyyMMdd)
	* @throws  
	* @author: lxy
	* @date: 2019年2月20日 下午5:17:23
	 */
	public static String getDateBefore(Date d, int day) {
		Calendar no = Calendar.getInstance();
		no.setTime(d);
		no.set(Calendar.DATE, no.get(Calendar.DATE) - day);
		Date nDay = no.getTime();
		return dateToStr(nDay);
	}
	
	/**
	 * 
	* @Description: 获取n天后时间 
	* @param d
	* @param day
	* @return
	* @return String
	* @throws  
	* @author: lxy
	* @date: 2019年2月25日 上午10:04:05
	 */
	public static String getDateAfter(Date d, int day) {
		Calendar no = Calendar.getInstance();
		no.setTime(d);
		no.set(Calendar.DATE, no.get(Calendar.DATE) + day);
		Date nDay = no.getTime();
		return getDateTime(nDay);
	}
}
