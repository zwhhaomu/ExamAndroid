package com.examandroid.ui;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.examandroid.util.HttpUtil;
import com.examandroid.util.Mac;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	private Button btnreg,btncancel,btnupdate;
	private EditText account,name,remark;
	private String accounttxt,nametxt,remarktext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		btnreg=(Button) findViewById(R.id.btnReg);
		btncancel=(Button) findViewById(R.id.btncancel);
		btnupdate=(Button) findViewById(R.id.btnupdate);
		account=(EditText) findViewById(R.id.account);
		name=(EditText) findViewById(R.id.name);
		account=(EditText) findViewById(R.id.account);
		remark=(EditText) findViewById(R.id.remark);
		String macaddress=new Mac().getLocalMacAddress();
		remark.setText(macaddress);
		btnreg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				accounttxt=account.getText().toString();
				nametxt=name.getText().toString();
				remarktext=remark.getText().toString();
				if(examuserRegister(accounttxt,nametxt,remarktext).equals("successful")){
					Toast.makeText(getBaseContext(), "考生注册成功", 8000).show();
				}
				else if(examuserRegister(accounttxt,nametxt,remarktext).equals("isExitsUser"))
					Toast.makeText(getBaseContext(), "已经存在该用户,请勿二次注册", 8000).show();
				else
					Toast.makeText(getBaseContext(), "考生注册失败", 8000).show();

			}
		});

		btncancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				return;

			}
		});
	}

	

	private String examuserRegister(String account,String name,String remark){
		String returnstr="error";
		String urlStr=new HttpUtil().getBASE_URL()+"/ExamUserRegister";
		/*7.补充代码
		 * 实现将考生学号、姓名、MAC地址注册添加到Web服务器当中，并获取返回的结果。
		 * 仔细阅读ExamUserRegister类的代码，根据服务器端的内容设计Android端代码，实现功能。
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		return returnstr;
	}
	
}
