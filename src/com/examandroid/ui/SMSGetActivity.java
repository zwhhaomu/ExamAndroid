package com.examandroid.ui;

import android.app.Activity;

import android.app.Activity; 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter; 
import android.os.Bundle; 
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View; 
import android.widget.Toast; 

public class SMSGetActivity extends Activity { 
	private SmsReceiver recevier; 
	private boolean isregiset = false; 
	private static final String ACTION = "android.provider.Telephony.SMS_RECEIVED"; 

	@Override 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_smsreceiver); 
		recevier = new SmsReceiver(); 

	} 

	public void regiset(View v) { 
		/*
		 * 5.2 添加代码实现msmReciever广播接收器的动态注册。
		 */

	} 

	public void unregiset(View v) { 
		if (recevier != null && isregiset) { 
			unregisterReceiver(recevier); 
			isregiset = false; 
			Toast.makeText(this, "解注册成功", 0).show(); 
		} else 
			Toast.makeText(this, "尚未注册", 0).show(); 
	} 
	@Override 
	protected void onDestroy() { 
		super.onDestroy(); 
		if (recevier != null && isregiset) { 
			unregisterReceiver(recevier); 
			isregiset = false; 
			Toast.makeText(this, "解注册成功", 0).show(); 
		} 
	} 
	class SmsReceiver extends BroadcastReceiver { 
		public SmsReceiver() { 
			Log.v("TAG", "SmsRecevier create"); 
		} 

		@Override 
		public void onReceive(Context context, Intent intent) { 
			
			Log.v("TAG", "SmsRecevier onReceive"); 
			Object[] pdus = (Object[]) intent.getExtras().get("pdus"); 
			if (pdus != null && pdus.length > 0) { 
				SmsMessage[] messages = new SmsMessage[pdus.length]; 
				for (int i = 0; i < pdus.length; i++) { 
					byte[] pdu = (byte[]) pdus[i]; 
					messages[i] = SmsMessage.createFromPdu(pdu); 
				} 
				for (SmsMessage message : messages) { 
					/*5.1 补充代码
					 * 获取短信内容和短信发送者，并使用Toast方法显示接收到的信息和发送的短信的手机号码。
					 */

					
					
					
					
					
					
					
					
				} 
			} 
		} 
	}
}
