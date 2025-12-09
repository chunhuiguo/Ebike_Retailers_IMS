package com.luyuan.util;


public class SQLConnection {
	
	//JDBC连接SQL Server配置
	public static String driverName = "net.sourceforge.jtds.jdbc.Driver";
	
	public static String lyCRMURL = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=LYCRM";
	public static String lyCRMUserName = "sa";
	public static String lyCRMPassWord = "123456";	
	
	public static String lyServicesURL = "jdbc:jtds:sqlserver://127.0.0.1:1433;DatabaseName=LYJXCServices";
	public static String lyServicesUserName = "sa";
	public static String lyServicesPassWord = "123456";	
}
