package com.examandroid.ui;

import java.lang.reflect.Array;
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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MainActivity extends Activity {
	ListView examList;
	EditText serveriptxt,serverporttxt;
	private final static int DIALOG=1;
	String userinfo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		examList=(ListView) findViewById(R.id.listexam);    
		ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,getData());
		examList.setAdapter(adapter);   
		examList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent;
				switch(position){
				case 0:
					intent=new Intent(MainActivity.this,WordShowActivity.class);
					startActivity(intent);
					break;
				case 1:
					drawView();
					break;
				case 2:
					showDialog(DIALOG);
					break;
				case 3:
					Intent intentreg=new Intent();
					intentreg.setClass(MainActivity.this, RegisterActivity.class);
					startActivity(intentreg);
					//alertExamuserInfo("考生注册");
					break;
				case 4:
					Intent intentmusic=new Intent();
					intentmusic.setClass(MainActivity.this, MusicActivity.class);
					startActivity(intentmusic);
					break;
				case 5:
					alertServerSet("服务器设置：");
					break;
				}

			}


		});
	}
	private List<String> getData(){

		List<String> data = new ArrayList<String>();
		data.add("1.单词管理");
		data.add("2.图形绘制");
		data.add("3.短信管理");
		data.add("4.考生注册");
		data.add("5.音乐播放");
		data.add("6.服务器设置");
		return data;
	}

	private void drawView() {

		String items[]=new String[]{"彩环绘制","黑白环形","仪表绘制"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("图形绘制");
		builder.setItems(items,new OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int which) {
				Intent intent;
			switch(which){
				case 0:				
				case 1:
					 intent=new Intent(MainActivity.this,DrawViewActivity.class);

					intent.putExtra("which", which);
					startActivity(intent);
					break;
				case 2:
					intent=new Intent(MainActivity.this,DrawPanel.class);
					startActivity(intent);	
					break;
			}
				


			}
		});

		AlertDialog alert = builder.create();
		alert.show();



	}
	private void alertAbout(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		.setCancelable(false)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		AlertDialog alert = builder.create();
		alert.show();

	}

	private void alertServerSet(String msg) {

		LayoutInflater factory = LayoutInflater.from(this);
		final View textEntryView = factory.inflate(
				R.layout.alert_dialog_text_entry, null);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		serveriptxt=(EditText)textEntryView.findViewById(R.id.ServerIP);
		serveriptxt.setText(HttpUtil.getSERVER_IP());

		serverporttxt=(EditText)textEntryView.findViewById(R.id.serverPort);
		serverporttxt.setText(HttpUtil.getSERVER_PORT());
		alertDialog.setTitle("Android 提示");
		alertDialog.setView(textEntryView);
		alertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				HttpUtil.setSERVER_IP(serveriptxt.getText().toString());
				HttpUtil.setSERVER_PORT(serverporttxt.getText().toString());
				HttpUtil.setBASE_URL(serveriptxt.getText().toString(),serverporttxt.getText().toString());
			}
		});
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				/* User clicked cancel so do some stuff */
			}
		});
		alertDialog.show();



	}


	@Override
	protected Dialog onCreateDialog(int id) {
		String message[]=new String[]{"＊.短信发送","＊.短信接收"};
		Dialog dialog=null;
		switch (id) {
		case DIALOG:
			Builder builder=new android.app.AlertDialog.Builder(this);
			//设置对话框的标题
			builder.setTitle("短信管理");
			//添加按钮，android.content.DialogInterface.OnClickListener.OnClickListener
			builder.setItems(message, new OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch(which){
					case 0:
						Toast.makeText(getApplication(), "短信发送", 8000).show();
						Intent intent=new Intent(MainActivity.this, SMSSendActivity.class);
						startActivity(intent);
						break;
					case 1:
						Toast.makeText(getApplication(), "短信接收", 8000).show();
						Intent intent1=new Intent(MainActivity.this, SMSGetActivity.class);
						startActivity(intent1);
						break;
					}

				}
			});
			//创建一个列表对话框
			dialog=builder.create();
			break;
		}
		return dialog;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent=new Intent();
			intent.setClass(MainActivity.this, Prefs.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}