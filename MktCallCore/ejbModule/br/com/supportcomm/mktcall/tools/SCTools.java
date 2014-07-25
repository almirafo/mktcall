package br.com.supportcomm.mktcall.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

import br.com.supportcomm.mktcall.util.Configuration;



public class SCTools
{
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static Random rnd = new Random();
    private static final String RESOURCE_CONFIG = "br/com/supportcomm/block/resources/config";

    public static boolean isNullOrEmpty(List<?> lista)
    {
        if (lista == null || lista.isEmpty())
        {
            return true;
        }
        return false;
    }

    public static boolean isNullOrBlank(List<?> list)
    {
        if (list == null || list.isEmpty())
        {
            return true;
        }
        return false;
    }

    public static boolean isNullOrBlank(Object obj)
    {
        if (obj == null || obj.toString().equals(""))
        {
            return true;
        }
        return false;
    }

    public static String getDataExtenso(Date data)
    {
        SimpleDateFormat df = new SimpleDateFormat("EEEEEE ',' dd ' de 'MMMM ' de ' yyyy");
        return df.format(data);
    }

    public static String randomString(int len)
    {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public static Timestamp getActualTimestamp()
    {

        return null;
    }

    public static String getConfigMessage(String content)
    {
        // return ResourceBundle.getBundle(RESOURCE_CONFIG, new Locale("pt", "BR")).getString(content);
        return ResourceBundle.getBundle(RESOURCE_CONFIG, new Locale("en")).getString(content);
    }

    public static String getDateTimeIsoFormatted()
    {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }
    
    public static String getDateIsoFormatted()
    {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(date);
    }
    
 public static Timestamp calculateDelayedTimestamp(int delayMinute){
		
		Date todaysDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(todaysDate);
		gc.add(Calendar.MINUTE, delayMinute);
		Date futureDate = gc.getTime();	
		while (outOfTimeRange(futureDate)) {
		    gc.add(Calendar.MINUTE, delayMinute);
		    futureDate = gc.getTime();
		}
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	String strFormattedDate = sdf.format(futureDate);
    	Date formattedDate = futureDate;
    	try {
			formattedDate = sdf.parse(strFormattedDate);
		} catch (ParseException e) {}    
    	
    	return new Timestamp(formattedDate.getTime());
    }    

    public static Timestamp calculateDelayedTimestamp(int delayMinute,String timeRangeStartHour,String timeRangeEndHour){
		
		Date todaysDate = new Date();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(todaysDate);
		gc.add(Calendar.MINUTE, delayMinute);
		Date futureDate = gc.getTime();	
		while (outOfTimeRange(futureDate, timeRangeStartHour, timeRangeEndHour)) {
		    gc.add(Calendar.MINUTE, delayMinute);
		    futureDate = gc.getTime();
		}
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    	String strFormattedDate = sdf.format(futureDate);
    	Date formattedDate = futureDate;
    	try {
			formattedDate = sdf.parse(strFormattedDate);
		} catch (ParseException e) {}    
    	
    	return new Timestamp(formattedDate.getTime());
    }
    
    
    
    public static boolean outOfTimeRange(){
    	return outOfTimeRange(Calendar.getInstance().getTime());
    }
    
  
    
    public static boolean outOfTimeRange(Date date,String timeRangeStartHour,String timeRangeEndHour) {
    	boolean outOfRange = true;
    	
    	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
    	String date1 = format1.format(date);
    	
    	if ((date1.compareTo(timeRangeStartHour + ":00:00") > 0 ) && (date1.compareTo(timeRangeEndHour + ":00:00") < 0 )) {
    		outOfRange = false;
    	} 
    	
    	return outOfRange;
    }  
    
    
    public static boolean outOfTimeRange(Date date) {
    	boolean outOfRange = true;
    	
    	SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
    	String date1 = format1.format(date);
    	
    	if ((date1.compareTo(Configuration.getSetting("timeRangeStartHour") + ":00:00") > 0 ) && (date1.compareTo(Configuration.getSetting("timeRangeEndHour") + ":00:00") < 0 )) {
    		outOfRange = false;
    	} 
    	
    	return outOfRange;
    }
}
