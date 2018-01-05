package com.examandroid.util;

public class  HttpUtil {

	private static String BASE_URL;
	private static String SERVER_IP;
	private static String SERVER_PORT;
	public static String getSERVER_IP() {
		return SERVER_IP;
	}
	public static void setSERVER_IP(String sERVER_IP) {
		SERVER_IP = sERVER_IP;
	}
	public static String getSERVER_PORT() {
		return SERVER_PORT;
	}
	public static void setSERVER_PORT(String sERVER_PORT) {
		SERVER_PORT = sERVER_PORT;
	}
	public static void setBASE_URL(String bASE_URL) {
		BASE_URL = bASE_URL;
	}
	public static void setBASE_URL(String serverip,String serverport){
		BASE_URL="http://"+serverip+":"+serverport+"/WordManageSystem/";
		
	} 
	public String getBASE_URL(){
		return BASE_URL;
	}
	
}
