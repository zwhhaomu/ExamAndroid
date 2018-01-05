package com.examandroid.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;



public class HttpThread extends Thread {
    private String url;
    private String strjson;
    private String request;
    private Context context;
    private Handler handler;

    public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStrjson() {
        return strjson;
    }

    public void setStrjson(String strjson) {
        this.strjson = strjson;
    }
    public HttpThread(Context context,Handler handler,String request){
        this.context=context;
        this.handler=handler;
        this.request=request;
    }

    @Override
    public void run() {
        HttpPost httpPost =new HttpPost();
        int res=httpPost.HttpPostResquest(url,strjson,request);
        String Webcontent=httpPost.getWebcontent();
        if(res==1){
            Message message = new Message();
            message.what=res;
            message.obj=Webcontent;
            handler.sendMessage(message);
        }

        super.run();
    }
}
