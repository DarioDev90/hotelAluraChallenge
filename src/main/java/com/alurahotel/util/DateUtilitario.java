package com.alurahotel.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtilitario {
	
	public java.sql.Date convert(java.util.Date fechaUtil) {
        return new java.sql.Date(fechaUtil.getTime());
    } 
	
	public java.sql.Date stringDateToSqlDate(String fecha){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date fechaSql = null;
		try {
			java.util.Date fechaUtil = format.parse(fecha);
			fechaSql = convert(fechaUtil);
			
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		
		return fechaSql;		
		
	}
}
