package com.huashitu.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @Title:工具类
 * @Desription:日期工具类
 * @Company:MDKG
 * @ClassName:DateHelper.java
 * @Author:Mengfh
 * @CreateDate:2013-6-7 下午3:28:08  
 * @UpdateUser:Mengfh  
 * @Version:0.1
 */
public class DateHelper {
	/**log4j日志对象*/
	private static Logger logger = Logger.getLogger(DateHelper.class);
	
	/**时间等于 0*/
	public static final int EQ = 0;
	/**时间大于 1*/
	public static final int GT = 1;
	/**时间小于 -1*/
	public static final int LT = -1;
	
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 将当前日期增加至23:59:59
	 * @Author:Mengfh
	 * @CreateDate:2013-6-7 下午3:28:44
	 * @param date 日期
	 * @return
	 */
	public static Date dateAddOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 将当前日期字符串增加至23:59:59
	 * @param dateStr 日期字符串
	 * @param pattem 格式
	 * @return
	 */
	public static String dateStrAddOneDay(String dateStr, String pattem) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseDate(pattem, dateStr));
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		return getDateStr(pattem,calendar.getTime());
	}
	
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 将当前日期减至00:00:00
	 * @Author:Mengfh
	 * @CreateDate:2013-6-7 下午3:28:59
	 * @param date 日期
	 * @return
	 */
	public static Date dateToStart(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, 0);
		calendar.add(Calendar.MINUTE, 0);
		calendar.add(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 当前输入时间减一天(当前输入时间时分秒变为00:00:00)
	 * @Author:Mengfh
	 * @CreateDate:2013-6-7 下午3:29:21
	 * @param date 日期
	 * @return
	 */
	public static Date dateBeforeOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, -12);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	 
	  return calendar.getTime();
	}
	/***
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 当前输入时间加一天(当前输入时间时分秒变为00:00:00)
	 * @Author:BEN
	 * @CreateDate:2013-7-30 下午12:29:57
	 * @param date
	 * @return
	 */
	public static Date dateBeStart(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	 
	  return calendar.getTime();
	}
	/***
	 * 
	 * @ClassName: DateHelper.java
	 * @Description:当前输入时间减一天(当前输入时间时分秒变为00:00:00)
	 * @Author:BEN
	 * @CreateDate:2013-7-30 上午11:37:04
	 * @param Str
	 * @return
	 */
	public static String dateBeforeOneDayStr(Date date){
		date = dateBeStart(  date);
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formate.format(date);
	}
	 
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 当前输入时间加一天(当前输入时间时分秒变为23:59:59)
	 * @Author:Mengfh
	 * @CreateDate:2013-6-7 下午3:30:01
	 * @param date
	 * @return
	 */
	public static Date dateafterOneDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
	  return calendar.getTime();
	}
	/***
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 当前输入时间加一天(当前输入时间时分秒变为23:59:59)
	 * @Author:BEN
	 * @CreateDate:2013-7-30 上午11:36:04
	 * @param String
	 * @return
	 */
	public static String dateafterOneDayString(Date date) {
		date =	dateafterOneDay( date);
		DateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formate.format(date);
	}
	 
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 获取系统当前时间(格式：yyyy-MM-dd)
	 * @Author:Mengfh
	 * @CreateDate:2013-6-7 下午3:31:57
	 * @return
	 */
	public static Date getSysDateByFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date formatSysDate = null;
		try {
			formatSysDate = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return formatSysDate;
	}
	
	/**
	 * 根据年的自然周获取本周的星期一的日期
	 * @param weekStr
	 * @return
	 */
	private static Date getMondayByWeek(String weekStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-ww");
		Date weekBeginDate = null;
		try {
			//获取自然周的第一天日期(即星期天的日期)
			weekBeginDate = sdf.parse(weekStr);
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		if(weekBeginDate != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(weekBeginDate);
			calendar.add(Calendar.DATE, +1); //把一周开始从星期天转移到星期一
			weekBeginDate = calendar.getTime();
		}
		return weekBeginDate;
	}
	public static Date getDateByMonthStart(Integer year , Integer month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1, 0, 0);
		return calendar.getTime();
	}
	public static Date getDateByMonthEnd(Integer year , Integer month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 0, 23, 59);
		return calendar.getTime();
	}
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 根据自然周返回该周的日期范围
	 * @Author:Mengfh
	 * @CreateDate:2013-6-7 下午3:32:03
	 * @param weekStr 自然周(格式：yyyy-ww)
	 * @return
	 */
	public static String getDateStrOfWeek(String weekStr) {
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		//根据年的自然周获取本周的星期一的日期
		Date weekBeginDate = getMondayByWeek(weekStr);
		String weekBeginDateStr = null;
		Date weekEndDate = null;
		String weekEndDateStr = null;
		if(weekBeginDate != null) {
			//获取自然周的第一天日期的字符串描述
			weekBeginDateStr = sdf2.format(weekBeginDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(weekBeginDate);
			calendar.add(Calendar.DATE, +6);
			//获取自然周的最后一天日期(即星期六的日期)
			weekEndDate = calendar.getTime();
			if(weekEndDate != null) {
				//获取自然周的最后一天日期的字符串描述
				weekEndDateStr = sdf2.format(weekEndDate);
			}               
		}
		String dateFormatStr = "";
		if(StringUtils.isNotBlank(weekBeginDateStr) && StringUtils.isNotBlank(weekEndDateStr)) {
			//获取自然周的日期范围的字符串
			dateFormatStr = weekBeginDateStr + "至" + weekEndDateStr;
		}
		return dateFormatStr;
	}
	
	/**
	 * 根据年的自然周获取本周所在月份的描述
	 * @param weekStr 自然周(格式：yyyy-ww)
	 * @return
	 */
	public static String getWeekOfMonthDesc(String weekStr) {
		//根据年的自然周获取本周的星期一的日期
		Date weekBeginDate = getMondayByWeek(weekStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月第WW周");
		return sdf.format(weekBeginDate);
	}
	
	/**
	 * 
	 * @ClassName: CfpModifyQuarzJob.java
	 * @Description: 获取当前月的上N个月
	 * @Author:Pengjingyu
	 * @CreateDate:2013-7-12 上午9:31:44
	 * @param severalMonthStr  前几个月 负数为前几个月
	 * @return
	 */
	 
	public static String getPreviousMonthStr(Date date,Integer severalMonthStr){
     	Calendar instance = Calendar.getInstance();
     	instance.setTime(date);
//     	instance.add(Calendar.YEAR, -1);
//     	instance.add(Calendar.MONTH, 1);
     	int i = instance.get(Calendar.MONTH);
    	instance.set(Calendar.MONTH, i-severalMonthStr);
    	Date time = instance.getTime();
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM");
    	return sd.format(time);
  	}
	/**
	 * 
	 * @ClassName: CfpModifyQuarzJob.java
	 * @Description: 获取当前天的上N天
	 * @Author:Pengjingyu
	 * @CreateDate:2013-7-12 上午9:31:44
	 * @param severalDayStr  前几天  负数为未来几天
	 * @return
	 */
	public static String getPreviousDayStr(Integer severalDayStr){
     	Calendar instance = Calendar.getInstance();
     	int i = instance.get(Calendar.DAY_OF_MONTH);
    	instance.set(Calendar.DAY_OF_MONTH, i-severalDayStr);
    	Date time = instance.getTime();
    	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    	return sd.format(time);
  	}
	
	/***
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 按格式！格式化字符串
	 * @Author:BEN
	 * @CreateDate:2013-7-4 下午2:38:36
	 * @param pattem
	 * @param date
	 * @return
	 */
	public static String getDateStr(String pattem,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattem);
		String   dateStr =  sdf.format(date) ;
		 return dateStr;
	}
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 按输入格式 格式化时间！返回时间格式
	 * @Author:BEN
	 * @CreateDate:2013-7-4 下午2:39:26
	 * @param pattem
	 * @param date
	 * @return
	 */
	public static Date formatDate(String pattem,Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattem);
		String   dateStr =  sdf.format(date) ;
		date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		 return date;
	}
	
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 按输入格式 格式化时间！返回时间格式
	 * @Author:BEN
	 * @CreateDate:2013-7-4 下午2:39:26
	 * @param pattem
	 * @param date
	 * @return
	 */
	public static Date parseDate(String pattem,String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattem);
		 Date date = null;
		try {
			 date =  sdf.parse(dateStr);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		 return date;
	}
	
	/**
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 根据指定的日期格式，获取两个日期范围内的所有日期
	 * @Author:Mengfh
	 * @CreateDate:2013-6-18 上午9:44:47
	 * @param beginDateStr 开始日期
	 * @param endDateStr 结束日期
	 * @param pattern 格式(yyyy-MM：按月统计，yyyy-MM-dd：按日统计)
	 * @return
	 */
	public static List<String> getDateListBetweenTwoDate(String beginDateStr, String endDateStr, String pattern) {
		List<String> dateList = new ArrayList<String>();
		if(StringUtils.isNotBlank(pattern) 
				&& ("yyyy-MM".equals(pattern) || "yyyy-MM-dd".equals(pattern))) {
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				Date beginDate = sdf.parse(beginDateStr);
				Date endDate = sdf.parse(endDateStr);
				while(beginDate.before(endDate) || beginDate.equals(endDate)) {
					dateList.add(sdf.format(beginDate)); //保存日期
					calendar.setTime(beginDate);
					if("yyyy-MM".equals(pattern)) {
						calendar.add(Calendar.MONTH, 1); //月份加1
					} else if("yyyy-MM-dd".equals(pattern)) {
						calendar.add(Calendar.DATE, 1); //日加1
					}
					beginDate = calendar.getTime();
				}
			} catch (ParseException e) {
				//e.printStackTrace();
			}
		}
		return dateList;
	}
	
	/***
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 获取当前时间减七天的时间
	 * @Author:BEN
	 * @CreateDate:2013-9-3 下午1:35:17
	 * @param now
	 * @return
	 * @throws ParseException 
	 */
	public static Date getBefor7Day(Date now ){
//		SimpleDateFormat initStartDate = new SimpleDateFormat("yyyy-MM-dd");
//		 String format = initStartDate.format(now);
//		 format=format+"00:00:00";
		SimpleDateFormat initStartDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		 
 		Calendar calendar =Calendar.getInstance();
		calendar.setTime(now);
	 	//System.out.println(initStartDate.format(now));
		calendar.set(Calendar.HOUR_OF_DAY, 0);
 		calendar.set(Calendar.MINUTE, 0);
 		calendar.set(Calendar.SECOND, 0);
 		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-6);
// 		calendar.set(Calendar.HOUR_OF_DAY, 0);
// 		calendar.set(Calendar.MINUTE, 0);
// 		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	 }

	/***
	 * 
	 * @ClassName: DateHelper.java
	 * @Description: 当前时间减一个月
	 * @Author:BEN
	 * @CreateDate:2013-9-18 下午2:17:34
	 * @param now
	 * @return
	 */
	public static Date getBefor1Month(Date now ){
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(new Date());
	 
		//System.out.println(format.format(now));
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-7);
		 //System.out.println(calendar.get(Calendar.MONTH));
		//System.out.println(format.format(calendar.getTime()));
		return calendar.getTime();
		
	}
	
	/**
	 * 将日期的时间移动到指定的时间间隔
	 * @param date 日期
	 * @param timeType 日期类型(与Calendar的日期类型绑定)
	 * @param space 需要移动的时间间隔(正数为时间向后推移，负数为时间向前推移)
	 * @return
	 */
	private static Date getDateByTimeTypeAndSpace(Date date, int timeType, int space) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(timeType, space);
			return calendar.getTime();
		}
		return null;
	}
 
	/**
	 * 将日期字符串的时间移动到指定的时间间隔
	 * @param dateStr 时间字符串
	 * @param timeType 日期类型(与Calendar的日期类型绑定)
	 * @param space 需要移动的时间间隔(正数为时间向后推移，负数为时间向前推移)
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getDateStrByTimeTypeAndSpace(String dateStr, int timeType, int space, String pattern) {
		if(StringUtils.isBlank(pattern)) {
			return "";
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			StringBuilder sb = new StringBuilder();
			sb.append("时间：").append(dateStr).append("在格式").append(pattern).append("中转换出错。");
			logger.error(sb.toString());
		}
		String resultDate = sdf.format(getDateByTimeTypeAndSpace(date, timeType, space));
		return resultDate;
	}
	
	/**
	 * 获取指定间隔后的日期字符串
	 * @param date 需要操作的日期
	 * @param timeType 日期类型(与Calendar的日期类型绑定)
	 * @param space 需要移动的时间间隔(正数为时间向后推移，负数为时间向前推移)
	 * @param pattern 格式化字符串
	 * @return
	 */
	public static String getDateStrByDefSpace(Date date, int timeType, int space, String pattern) {
		if(StringUtils.isBlank(pattern)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(getDateByDefSpace(date, timeType, space));
	}
	
	/**
	 * 获取指定间隔后的日期
	 * @param date 需要操作的日期
	 * @param timeType 日期类型(与Calendar的日期类型绑定)
	 * @param space 需要移动的时间间隔(正数为时间向后推移，负数为时间向前推移)
	 * @return
	 */
	public static Date getDateByDefSpace(Date date, int timeType, int space) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(timeType, space);
		return calendar.getTime();
	}
	/**
	 * 循环获取日期的前7天
	 * @param date
	 * @return
	 */
	public  static Date cycleGetBefore7(Date date){
		Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		  cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear-7 );
		  return cal.getTime();
	}
	
	/**
	 * 获取当前年份
	 * @return YYYY格式的年份
	 */
	public static String getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return String.valueOf(cal.get(Calendar.YEAR ));
	}
	
	/**
	 * 获取当前年份和月份
	 * @return YYYYMM格式的年份和月份
	 */
	public static String getCurrentYearAndMonth() {
		Calendar cal = Calendar.getInstance();
		String currentYear = String.valueOf(cal.get(Calendar.YEAR ));
		String currentMonth = String.valueOf(cal.get(Calendar.MONTH ) + 1);
		return currentYear + currentMonth;
	}
 
	/**
	 * 根据传入日期获取当月的第一天 00开始和最后一天的日期23:59点结束
	 * @param date 日期
	 * @return
	 */
	public static List<Date> getStartAndLastDayOfMonth(Date date){
		List<Date> startAndLastDayOfMonth = new ArrayList<Date>();
		Calendar calMin = Calendar.getInstance();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		calMin.setTime(date);
 		//设置时间为当月的第一天 00:00:00
		calMin.set(Calendar.DAY_OF_MONTH, 1);
		String format = sd.format(calMin.getTime());
		//设置开始时间从00点开始
		format = format+"00:00:00";
		SimpleDateFormat parse = new SimpleDateFormat("yyyyMMddHH:mm:ss");
		try {
			Date startDay = parse.parse(format);
			startAndLastDayOfMonth.add(startDay);
		} catch (ParseException e) {
 			//e.printStackTrace();
		}
 		//获取下月的第一天然后减一天
 		calMin.add(Calendar.MONTH, 1);  //获取下个月
 		calMin.set(Calendar.DAY_OF_MONTH, 1); //设置时间为下个月第一天
 		calMin.add(Calendar.DAY_OF_MONTH, -1); //下个月第一天减一天  
 		format = sd.format(calMin.getTime());
 		format= format+"23:59:59";
 		try {
			Date startDay = parse.parse(format);
			startAndLastDayOfMonth.add(startDay);
		} catch (ParseException e) {
 			//e.printStackTrace();
		}
 		return startAndLastDayOfMonth;
	}
   /**
    * 获取时间的前几个月日期
    * @param date  日期
    * @param beforeFewMonth  前几个月
    * @return
    */
	public static Date getFewMonthDate(Date date, int beforeFewMonth) {
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 cal.add(Calendar.MONTH, -beforeFewMonth+1);
		return cal.getTime();
	}
    /**
     * 获取时间范围内的月份集合
     * @param startDay 开始日期
     * @param fewMonth   几个月的集合
     * @return
     */
	public static List<String> getMonthList(Date endDay,Integer fewMonth) {
		return getMonthList(endDay, fewMonth, "yyyyMM");
	}
	
	/**
     * 获取时间范围内的月份集合
     * @param endDay 结束日期
     * @param fewMonth   几个月的集合
     * @param format 转换的时间格式
     * @return
     */
	public static List<String> getMonthList(Date endDay, Integer fewMonth, String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
		List<String> monthList=new ArrayList<String>();
		if(endDay!=null&&fewMonth!=null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(endDay);
			for(int i=1;i<=fewMonth;i++){
				if(i==1){
					monthList.add(sd.format(cal.getTime()));
				}else{
					cal.add(Calendar.MONTH, -1);
					monthList.add(sd.format(cal.getTime()));
				}
			}
			Collections.sort(monthList);
		}
		return monthList;
	}
	
	/**
	 * 比较两个日期字符串的日期大小(大于：GT、小于：LT、等于：EQ)
	 * @param oneDateStr 日期1
	 * @param anotherDateStr 日期2
	 * @param pattern 格式
	 * @return
	 */
	public static Integer compareTwoDate(String oneDateStr, String anotherDateStr, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date oneDate = null;
		Date anotherDate = null;
		try {
			oneDate = sdf.parse(oneDateStr);
			anotherDate = sdf.parse(anotherDateStr);
		} catch (ParseException e) {
			//e.printStackTrace();
			StringBuilder sb = new StringBuilder();
			sb.append("时间：").append(oneDate).append("，")
			  .append(anotherDate).append("在格式").append(pattern).append("中转换出错。");
			logger.error(sb.toString());
		}
		Integer result = null;
		if(oneDate != null && anotherDate != null) {
			result = compareTwoDate(oneDate, anotherDate);
		}
		return result;
	}
	
	/**
	 * 比较两个日期字符串的日期大小(大于：GT、小于：LT、等于：EQ)
	 * @param oneDate 日期1
	 * @param anotherDate 日期2
	 * @param pattern 格式
	 * @return
	 */
	public static int compareTwoDate(Date oneDate, Date anotherDate) {
		int result = 0;
		long t1 = oneDate.getTime();
		long t2 = anotherDate.getTime();
		if(t1 > t2) {
			result = GT;
		}else if(t1 < t2) {
			result = LT;
		} else if(t1 == t2) {
			result = EQ;
		}
		return result;
	}
	
	/**
	 * 判断目标日期是否在指定日期范围内(包含开始日期和结束日期)
	 * @param date 目标日期
	 * @param start 开始日期
	 * @param end 结束日期
	 * @return
	 */
	public static boolean isBetween(Date date, Date start, Date end) {
		return isBetween(date, start, true, end, true);
	}
	
	/**
	 * 判断目标日期是否在指定日期范围内(包含开始日期和结束日期)
	 * @param dateStr 目标日期描述
	 * @param startStr 开始日期描述
	 * @param endStr 结束日期描述
	 * @param pattern 日期转换格式
	 * @return
	 */
	public static boolean isBetween(String dateStr, String startStr, String endStr, String pattern) {
		return isBetween(dateStr, startStr, true, endStr, true, pattern);
	}
	
	/**
	 * 判断目标日期是否在指定日期范围内
	 * @param dateStr 目标日期描述
	 * @param startStr 开始日期描述
	 * @param isStartInclusive 是否包含开始日期
	 * @param endStr 结束日期描述
	 * @param isEndInclusive 是否包含结束日期
	 * @param pattern 日期转换格式
	 * @return
	 */
	public static boolean isBetween(String dateStr, String startStr, boolean isStartInclusive, 
			String endStr, boolean isEndInclusive, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		Date start = null;
		Date end = null;
		try {
			date = sdf.parse(dateStr);
			start = sdf.parse(startStr);
			end = sdf.parse(endStr);
		} catch (ParseException e) {
			//e.printStackTrace();
			StringBuilder sb = new StringBuilder();
			sb.append("时间：").append(dateStr).append("，").append(startStr).append("，").append(endStr)
			  .append("在格式").append(pattern).append("中转换出错。");
			logger.error(sb.toString());
		}
		boolean result = false;
		if(date != null && start != null && end != null) {
			result = isBetween(date, start, isStartInclusive, end, isEndInclusive);
		}
		return result;
	}
	
	/**
	 * 判断目标日期是否在指定日期范围内
	 * @param date 目标日期
	 * @param start 开始日期
	 * @param isStartInclusive 是否包含开始日期
	 * @param end 结束日期
	 * @param isEndInclusive 是否包含结束日期
	 * @return
	 */
	public static boolean isBetween(Date date, Date start, boolean isStartInclusive, Date end, boolean isEndInclusive) {
		boolean startCondition = false;
		boolean endCondition = false;
		if (isStartInclusive) {
			//包含开始日期，条件为大于或者等于
			startCondition = compareTwoDate(date, start) == GT || compareTwoDate(date, start) == EQ;
		} else {
			//不包含开始日期，条件为大于
			startCondition = compareTwoDate(date, start) == GT;
		}
		if(isEndInclusive) {
			//包含结束日期，条件为小于或者等于
			endCondition = compareTwoDate(date, end) == LT || compareTwoDate(date, end) == EQ;
		} else {
			//不包含结束日期，条件为小于
			endCondition = compareTwoDate(date, end) == LT;
		}
		return startCondition && endCondition;
	}
	
	/**
	 * 获取日期字符串所在月份最大天数的日期描述(当前月的最大天数，时分秒为23:59:59)
	 * @param dateStr 日期字符串
	 * @param pattern 格式
	 * @return
	 */
	public static String getMaxDateStr(String dateStr, String pattern) {
		if(StringUtils.isBlank(dateStr) || StringUtils.isBlank(pattern)) {
			return "";
		}
		String opDateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(dateStr));
			int maxDay = calendar.getActualMaximum(Calendar.DATE);
			calendar.set(Calendar.DAY_OF_MONTH, maxDay);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			opDateStr = sdf.format(calendar.getTime());
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
		return opDateStr;
	}
	/**
	 * 获取当前日期 前几个月的日期集合 一个月为间隔
	 * @param month
	 * @return
	 */
	public static List<Date> getPrevMonthDateList(Date endDate,int month){
		List<Date> dateList = new ArrayList<Date>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		//添加当月日期
		dateList.add(endDate);
		for(int i=1;i<month;i++){
			//月份-1获取上一个月
			calendar.add(Calendar.MONTH, -1);
			dateList.add(calendar.getTime());
		}
		 return dateList;
	}
	
	/**
	 * 获取首页周统计的开始日期和结束日期
	 * @param defWeekDay 统计截止日期(所定义的本周的周几)
	 * @param minusDays 统计天数(所定义的本周的周几向前推的天数)
	 * @return
	 */
	public static Map<String, String> getWeekStatTimeMap(int defWeekDay, int minusDays) {
		Calendar calendar = Calendar.getInstance();
		//通过所指定的本周的周几与当前时间所在周的天数求差，获得本周的周几的具体日期
		calendar.add(Calendar.DATE, (defWeekDay + 1) - calendar.get(Calendar.DAY_OF_WEEK));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//设置首页周统计结束日期(时分秒为23:59:59)
		String endTime = sdf.format(calendar.getTime()) + " 23:59:59";
		//向前推移指定的天数，设置首页周统计开始日期(时分秒为00:00:00)
		calendar.add(Calendar.DATE, -minusDays);
		String beginTime = sdf.format(calendar.getTime()) + " 00:00:00";
		
		Map<String, String> weekStatTimeMap = new HashMap<String, String>();
		weekStatTimeMap.put("weekBeginTime", beginTime);
		weekStatTimeMap.put("weekEndTime", endTime);
		return weekStatTimeMap;
	}
	
	/**
	 * 获取日期所在月份的周描述(默认值为null字符串，用于数据库入库使用)
	 * @param date 日期
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String weekOfDateDesc = "null";
		if(date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMWW");
			weekOfDateDesc = sdf.format(date);
		}
		return weekOfDateDesc;
	}
	
	/**
	 * 获取日期所在月份的周描述(默认值为null字符串，用于数据库入库使用)
	 * @param dateStr 日期字符串
	 * @param pattern 日期格式
	 * @return
	 */
	public static String getWeekOfDate(String dateStr, String pattern) {
		String weekOfDateDesc = "null";
		if(StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(pattern)) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				Date date = sdf.parse(dateStr);
				weekOfDateDesc = getWeekOfDate(date);
			} catch (ParseException e) {
				logger.error(e.getMessage());
			}
		}
		return weekOfDateDesc;
	}
//	public static void main(String []ddd){
	//	System.out.println(getWeekOfDate("2015-05-28 02:22:33", "yyyy-MM-dd HH:mm:ss"));
	//SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	}
	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static Date getLastDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}
	
	/**
     * 根据日期获取年龄
     * @param birthday 出生年月日
     * @return
     */
    public static Integer getAgeNum(Date birthday) {
    	if(birthday == null) {
    		return -1;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	return getAgeNum(sdf.format(birthday));
    }
	
	/**
     * 根据日期获取年龄
     * @param birthday 日期（格式：yyyy-MM-dd）
     * @return
     */
    public static Integer getAgeNum(String birthday) {
    	if(StringUtils.isBlank(birthday)) {
    		return -1;
    	}
    	birthday = birthday.substring(0, 10);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String currDate = sdf.format(new Date());
    	String[] currDates = currDate.split("-");
    	String[] birthdays = birthday.split("-");
    	//获取当前日期与生日日期的年份差
    	int yearDistance = Integer.parseInt(currDates[0]) - Integer.parseInt(birthdays[0]);
    	int currMonthAndDayMix = Integer.parseInt(currDates[1] + currDates[2]);
    	int birthdayMonthAndDayMix = Integer.parseInt(birthdays[1] + birthdays[2]);
    	//如果生日的月日整合值大于当前日期的月日整合值，年份减1
    	if(birthdayMonthAndDayMix > currMonthAndDayMix) {
    		yearDistance--;
    	}
    	return yearDistance;
    }
    
    /**
     * 获取日期所在周的天数（即每周第几天）
     * @param date 日期
     * @return
     */
    public static int getDayOfWeek(Date date) {
    	int day = 0;
    	//按照中国人习惯的星期值
    	int[] targetDays = {7, 1, 2, 3, 4, 5, 6};
    	if(date != null) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(date);
    		day = calendar.get(Calendar.DAY_OF_WEEK);
    	}
    	return targetDays[day - 1];
    }
    
    /**
	 * 获取星期值
	 * @param day 原星期值
	 * @param offset 递增量
	 * @return
	 */
	public static int getWeekDay(int day, int offset) {
		int result = day + offset;
		if(result > 7) {
			result -= 7;
		}
		return result;
	}
	
	/**
	 * 获取推移日期
	 * @param date 原日期
	 * @param type 推移类型
	 * @param offset 偏移量
	 * @return
	 */
	public static Date getOffsetDate(Date date, int type, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, offset);
		return calendar.getTime();
	}
	
	/**
	 * 获取推移日期字符串
	 * @param date 原日期
	 * @param type 推移类型
	 * @param offset 偏移量
	 * @param pattern 格式
	 * @return
	 */
	public static String getOffsetDateStr(Date date, int type, int offset, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(getOffsetDate(date, type, offset));
	}
	
	/**
	 * 根据时间戳获取日期描述
	 * @param millis 时间戳
	 * @return
	 */
	public static Map<String, Long> getTimeMapByMillis(long millis) {
		long time = millis / 1000;
		long year = time / (365 * 24 * 3600);
		time = time % (365 * 24 * 3600);
		long month = time / (30 * 24 * 3600);
		time = time % (30 * 24 * 3600);
		long day = time / (24 * 3600);
		time = time % (24 * 3600);
		long hour = time / 3600;
		time = time % 3600;
		long minute = time / 60;
		time = time % 60;
		long second = time;
		Map<String, Long> timeMap = new HashMap<String, Long>();
		timeMap.put("year", year);
		timeMap.put("month", month);
		timeMap.put("day", day);
		timeMap.put("hour", hour);
		timeMap.put("minute", minute);
		timeMap.put("second", second);
		return timeMap;
	}
	
	/**
	 * 获取指定日期在月份中第一天的日期
	 * @param date 日期
	 * @return
	 */
	public static Date getMonthFirstDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取格式化日期
	 * @param date 日期
	 * @param pattern 格式
	 * @return
	 */
	public static String getFormatDate(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
}
