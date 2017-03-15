package org.oa_common.date;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.ntp.TimeStamp;

/** 
 * 日期Util类 
 *  
 * @author calvin 
 */  
public class DateTools  
{  
    private static String defaultDatePattern = "yyyy-MM-dd ";  
  
    /** 
     * 获得默认的 date pattern 
     */  
    public static String getDatePattern()  
    {  
        return defaultDatePattern;  
    }  
  
    /** 
     * 返回预设Format的当前日期字符串 
     */  
    public static String getToday()  
    {  
        Date today = new Date();  
        return format(today);  
    }  
  
    /** 
     * 使用预设Format格式化Date成字符串 
     */  
    public static String format(Date date)  
    {  
        return date == null ? " " : format(date, getDatePattern());  
    }  
  
    /** 
     * 使用参数Format格式化Date成字符串 
     */  
    public static String format(Date date, String pattern)  
    {  
        return date == null ? " " : new SimpleDateFormat(pattern).format(date);  
    }  
  
    /** 
     * 使用预设格式将字符串转为Date 
     */  
    public static Date parse(String strDate) throws ParseException  
    {  
        return StringUtils.isBlank(strDate) ? null : parse(strDate,  
                getDatePattern());  
    }  
  
    /** 
     * 使用参数Format将字符串转为Date 
     */  
    public static Date parse(String strDate, String pattern)  
            throws ParseException  
    {  
        return StringUtils.isBlank(strDate) ? null : new SimpleDateFormat(  
                pattern).parse(strDate);  
    }  
  
    /** 
     * 在日期上增加数个整月 
     */  
    public static Date addMonth(Date date, int n)  
    {  
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.add(Calendar.MONTH, n);  
        return cal.getTime();  
    }  
  
    public static String getLastDayOfMonth(String year, String month)  
    {  
        Calendar cal = Calendar.getInstance();  
        // 年  
        cal.set(Calendar.YEAR, Integer.parseInt(year));  
        // 月，因为Calendar里的月是从0开始，所以要-1  
        // cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);  
        // 日，设为一号  
        cal.set(Calendar.DATE, 1);  
        // 月份加一，得到下个月的一号  
        cal.add(Calendar.MONTH, 1);  
        // 下一个月减一为本月最后一天  
        cal.add(Calendar.DATE, -1);  
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));// 获得月末是几号  
    }  
  
    public static Date getDate(String year, String month, String day)  
            throws ParseException  
    {  
        String result = year + "- "  
                + (month.length() == 1 ? ("0 " + month) : month) + "- "  
                + (day.length() == 1 ? ("0 " + day) : day);  
        return parse(result);  
    } 
    public static String StringPattern(String date, String oldPattern, String newPattern) {   
        if (date == null || oldPattern == null || newPattern == null)   
            return "";   
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern) ;        // 实例化模板对象    
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern) ;        // 实例化模板对象    
        Date d = null ;    
        try{    
            d = sdf1.parse(date) ;   // 将给定的字符串中的日期提取出来    
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理    
            e.printStackTrace() ;       // 打印异常信息    
        }    
        return sdf2.format(d);  
    }  
    public static String getTime(long time) {
        Calendar newCalendar = Calendar.getInstance();
        Calendar oldCalendar = Calendar.getInstance();
        oldCalendar.setTime(new Date(time));
        if (newCalendar.get(Calendar.YEAR) > oldCalendar.get(Calendar.YEAR)) {
        	return getFormatTime(time);
        } else if (newCalendar.get(Calendar.MONTH) > oldCalendar.get(Calendar.MONTH)) {
            return (newCalendar.get(Calendar.MONTH) - oldCalendar.get(Calendar.MONTH)) + "月前";
        } else if (newCalendar.get(Calendar.DAY_OF_MONTH) > oldCalendar.get(Calendar.DAY_OF_MONTH)) {
            return (newCalendar.get(Calendar.DAY_OF_MONTH) - oldCalendar.get(Calendar.DAY_OF_MONTH)) + "天前";
        } else if (newCalendar.get(Calendar.HOUR) > oldCalendar.get(Calendar.HOUR)) {
            return (newCalendar.get(Calendar.HOUR) - oldCalendar.get(Calendar.HOUR)) + "小时前";
        } else if (newCalendar.get(Calendar.MINUTE) > oldCalendar.get(Calendar.MINUTE)) {
            return (newCalendar.get(Calendar.MINUTE) - oldCalendar.get(Calendar.MINUTE)) + "分钟前";
        } else {
            return "刚刚";
        }
    }
    /**
     * 时间格式化
     * @param time
     * @return
     */
    private static String getFormatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(time));
    }
    /**
	 * 获取网络时间
	 * @param timeFormat 时间格式
	 * @param timeServerUrl 网络地址
	 * @param ceilingTime 上限时间（超过时间返回本地系统时间）
	 * @return
	 */
	public static String NetWorkTime(String timeFormat,String timeServerUrl,Integer ceilingTime ){
		DateFormat dateFormat = new SimpleDateFormat(timeFormat);  
		try {  
//			NTPUDPClient timeClient = new NTPUDPClient();  
//	        timeClient.setDefaultTimeout(ceilingTime);
//	        InetAddress timeServerAddress = InetAddress.getByName(timeServerUrl);  
//	        TimeInfo timeInfo = timeClient.getTime(timeServerAddress);  
//	        TimeStamp timeStamp = timeInfo.getMessage().getTransmitTimeStamp();
//	        return dateFormat.format(timeStamp.getDate());
	        return dateFormat.format(new Date());
 	    } catch (Exception e) {  
	 	    return dateFormat.format(new Date());
 	    } 
	}
	public static String TimeString(String timeFormat){
		DateFormat dateFormat = new SimpleDateFormat(timeFormat); 
		return dateFormat.format(new Date());
	}
    public static void main(String[] args){
    	System.out.println(DateTools.getFormatTime(System.currentTimeMillis()));
    	System.out.println(DateTools.getFormatTime((1480063552+157679956)*1000L));
    }
}  