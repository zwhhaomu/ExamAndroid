package com.examandroid.util;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpPost {
   private String  Webcontent;
   private String request;
   
    public String getRequest() {
	return request;
}
public void setRequest(String request) {
	this.request = request;
}
	public String getWebcontent() {
        return Webcontent;

    }
    public void setWebcontent(String webcontent) {
        Webcontent = webcontent;
    }

    public int HttpPostResquest(String url,String strjson,String request){
        HttpURLConnection connection= null;
        JSONObject jsonObject;
        int status=0;

        try {
            URL url1 = new URL(url);
            connection= (HttpURLConnection) url1.openConnection();
            
            if(request.equals("POST")){
            	connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                byte[] requestStringBytes = strjson.getBytes("UTF-8"); 
                connection.setRequestProperty("Content-length", "" + requestStringBytes.length); 
                connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                connection.setRequestProperty("Connection", "Keep-Alive");// 维持长连接 
                connection.setRequestProperty("Charset", "UTF-8");
                
                connection.setDoOutput(true);  
                connection.setDoInput(true); 
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();
                
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                //String content = "name="+ URLEncoder.encode(requestString,"utf-8");
                outputStream.writeBytes(strjson);
                outputStream.flush();
                outputStream.close();
               
            }
            if(request.equals("GET")){
            	connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                connection.connect();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
            }
            
            int res= connection.getResponseCode();
            Log.i("res", String.valueOf(res));
            
            if(res==200){
                InputStream in= connection.getInputStream();
                ByteArrayOutputStream b= new ByteArrayOutputStream();
                byte[] buffer= new byte[1024];
                int len;
                while ((len=in.read(buffer))!=-1){
                    b.write(buffer,0,len);
                }
                in.close();
                b.close();
                String data = new String(b.toByteArray());
                setWebcontent(data);
                status=1;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
return status;
    }

}
