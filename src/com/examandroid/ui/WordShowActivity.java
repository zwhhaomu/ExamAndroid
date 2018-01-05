package com.examandroid.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.examandroid.db.Word;
import com.examandroid.db.WordDBHelper;
import com.examandroid.util.HttpThread;
import com.examandroid.util.HttpUtil;
import com.examandroid.util.StreamTool;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class WordShowActivity extends Activity {
	WordDBHelper dbHelper;
	private EditText word,detail;
	private Button btnprior,btnnext,btnedit,updatewordbtn;
	Cursor c ;
//	Handler handler;
	List<Word> words;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wordshow);
		dbHelper=new WordDBHelper(this);
		word=(EditText) findViewById(R.id.word);
		detail=(EditText) findViewById(R.id.detail);
		btnprior=(Button) findViewById(R.id.btnprior);
		btnnext=(Button) findViewById(R.id.btnnext);
		btnedit=(Button) findViewById(R.id.editbtn);
		updatewordbtn=(Button) findViewById(R.id.updatewordbtn);
		words=new ArrayList<Word>();
		updatewordbtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				//getConnThread();
				dbHelper.deleteWord();
				
				try {
					String url=new HttpUtil().getBASE_URL()+"ListWordServlet";
					HttpThread  httpThread = new HttpThread(WordShowActivity.this,handler,"POST");
		            httpThread.setUrl(url);
		           // String strJson = "format="+"json";
		    		List<NameValuePair> params = new ArrayList<NameValuePair>();
		    		params.add(new BasicNameValuePair("format", URLEncoder.encode("json","utf-8")));
		    		
		    		StringBuffer sb=new StringBuffer();
		    		for(NameValuePair item:params){
		    		sb.append(item);
		    		}
		    		Log.i("url", url);
		            Log.i("strJson", sb.toString());
		            httpThread.setStrjson(sb.toString());
		            httpThread.start();
//					handler=new Handler(){
//						public void handleMessage(Message msg) {
//							JSONObject jo;
//							String str="null";
//							if(msg.what==1){
//								String strWebContent = null;
//								strWebContent = (String) msg.obj;
//								try {
//									jo= new JSONObject(strWebContent);
//									JSONArray array=new JSONArray(jo);
//									for(int i=0;i<array.length();i++){
//										JSONObject jsonObject=array.getJSONObject(i);
//										Word word=new Word(jsonObject.getInt("id"),jsonObject.getString("word"),jsonObject.getString("detail"));	
//										words.add(word);
//									}
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}
//					};
//					HttpThread  httpthread=new HttpThread(getApplicationContext(), handler);
//					String path=new HttpUtil().getBASE_URL()+"ListWordServlet?format=json";
//					httpthread.setUrl(path);
//					httpthread.start();
					//words=getListWord();
					for(Word word:words){
						dbHelper.insertWord(word.getWord(), word.getDetail());
					}
					refersh();

					/*Intent intent=new Intent(WordShowActivity.this,QueryActivity.class);
					startActivity(intent);*/

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		refersh();

		//3.3 补充代码使其能获取单词结果集的“上一条”记录，并显示到word和detail的文本框控件中。
		btnprior.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(c.moveToPrevious()){
					word.setText(c.getString(1));
					detail.setText(c.getString(2));}
				else
				{
					Toast.makeText(getApplicationContext(), "已经是第一条单词", 3000).show();
				}
			}
		});
		
		
		//3.3 补充代码使其能获取单词结果集的“下一条”记录，并显示到word和detail的文本框控件中。
		btnnext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(c.moveToNext()){
					word.setText(c.getString(1));
					detail.setText(c.getString(2));}
				else
				{
					Toast.makeText(getApplicationContext(), "已经是最后一条单词", 3000).show();
				}
			}

		});
		btnedit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//补充代码实现从WordShowActivity跳转到WordManageActivity页面当中。

				Intent intent=new Intent(WordShowActivity.this,WordManageActivity.class);
				Bundle b=new Bundle();
				b.putString("word", word.getText().toString());
				b.putString("detail", detail.getText().toString());
				intent.putExtra("data",b);
				startActivity(intent);

			}
		});
		dbHelper.close();
	}
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			List<Word> words;
			String strinfo=(String) msg.obj;
			Log.i("strinfo",strinfo);
			try {
				words = getListWord(strinfo);
				for(Word word:words){
					dbHelper.insertWord(word.getWord(), word.getDetail());
				}
				refersh();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		};
	};
	private void refersh() {
		c = dbHelper.query();
		if(c.getCount()>0){
			c.moveToFirst();
			word.setText(c.getString(1));
			detail.setText(c.getString(2));
		}
		/*else
			Toast.makeText(getApplicationContext(), "没有单词了！", 3000).show();*/

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		refersh();
	}

	
	
	public InputStream getConnNet(){
		InputStream inStream = null;
		try {
			String path=new HttpUtil().getBASE_URL()+"ListWordServlet?format=json";
			URL url=new URL(path);
			URLConnection conn=url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			inStream = conn.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inStream;
	}

	//	public static List<Word> getLastJsonUser() throws Exception{
	//		String path=new HttpUtil()+"ListWordServlet?format=json";
	//		URL url=new URL(path);
	//		URLConnection conn=url.openConnection();
	//		conn.setConnectTimeout(5000);
	//		conn.setDoOutput(true);
	//		conn.setDoInput(true);
	//		InputStream inStream=conn.getInputStream();
	//		//调用parseJSON()或者parseXML()方法
	//		return parseJSON(inStream);
	//	}

	//3.2 完善 List<Word> parseJSON(InputStream inStream)方法。将JSON格式的单词信息转换成List列表形式。

	private  List<Word> getListWord(String info) throws Exception {
		//InputStream inStream = getConnNet();
		List<Word> words=new ArrayList<Word>();
		InputStream   inStream   = new ByteArrayInputStream(info.getBytes("UTF-8")); 
		byte[] data=StreamTool.read(inStream);
		String json=new String(data);
		JSONArray array=new JSONArray(json);
		for(int i=0;i<array.length();i++){
			JSONObject jsonObject=array.getJSONObject(i);
			Word word=new Word(jsonObject.getInt("id"),jsonObject.getString("word"),jsonObject.getString("detail"));	
			words.add(word);
		}
		return words;
	}


	//3.2 完善 List<Word> parseXML(InputStream inStream)方法。将XML格式的单词信息转换成List列表形式。
	private  List<Word> parseXML() throws Exception {
		InputStream inStream=getConnNet();
		List<Word> words=new ArrayList<Word>();
		byte[] data=StreamTool.read(inStream);
		String xml=new String(data);
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		// step 2:获得具体的dom解析器 
		DocumentBuilder builder=factory.newDocumentBuilder();
		// step3: 解析一个xml文档，获得Document对象（根结点）  
		Document doc=(Document) builder.parse(inStream);
		NodeList list=doc.getElementsByTagName("words");
		for(int i=0;i<list.getLength();i++){
			Element wordElement = (Element)list.item(i); 
			Word word=new Word(
					Integer.parseInt(wordElement.getElementsByTagName("id").item(0).getFirstChild().getNodeValue()),
					wordElement.getElementsByTagName("word").item(0).getFirstChild().getNodeValue(),
					wordElement.getElementsByTagName("detail").item(0).getFirstChild().getNodeValue()
					);
			words.add(word);
		}
		return words;
	}
}
