package cn.edu.xmu.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class StringAndDate {
	
	public static Date StringToDate(String d) {
	 
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	        Date date = null;  
	       	 
	        // 2012-02-24  
	        date = java.sql.Date.valueOf(d);  
	                                              
	        return date;  
	}
	
	public static String DateToString(Date d) {
		String str = null;  
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		format = DateFormat.getDateInstance(DateFormat.MEDIUM);  
        str = format.format(d); 
        return str;
	}
}
