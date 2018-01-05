package com.examandroid.ui;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SMSSendActivity extends Activity {
	
	//sms值赋值为考生的学号和姓名
	String sms="考生的学号和姓名";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssend);
        Button btnSendSms=(Button) findViewById(R.id.btnSendSms);
        btnSendSms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {			
				sendSMS();
			}

			
		});
    }

    private void sendSMS() {
    	
    	//补充代码将短信发送到5556手机号中
    	PendingIntent pi=PendingIntent.getBroadcast(SMSSendActivity.this, 0, new Intent(), 0);
		SmsManager smsManagfer=SmsManager.getDefault();
		List<String> smsList=smsManagfer.divideMessage(sms);
		for(String message:smsList){
			smsManagfer.sendTextMessage("5556", null, message, pi, null);
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
