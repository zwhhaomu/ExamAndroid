package com.examandroid.ui;

import com.examandroid.db.WordDBHelper;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class WordManageActivity extends Activity implements OnClickListener {

	private Button btninsert,btnupdate,btnsearch;
	private EditText editTextword,editTextdetail;
	private String word,detail;
	private int i;
	WordDBHelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wordmanage);
		initView();
		Intent intent=getIntent();
		Bundle b=intent.getBundleExtra("data");
		editTextword.setText(b.getString("word"));
		editTextdetail.setText(b.getString("detail"));
		dbhelper=new WordDBHelper(this);
		btninsert.setOnClickListener(this);
		btnupdate.setOnClickListener(this);		
		btnsearch.setOnClickListener(this);

	}


	private void initView() {
		/*1.3 使用findViewByid()方法将
		 * R.id.btninsert、R.id.btnupdate、R.id.btnsearch、R.id.btnsearch、R.id.word控件
		 * 加载到WordManageActivity当中。
		 */
		
		
		
		

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("ShowToast")
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.btninsert:
			word=editTextword.getText().toString().trim();
			detail=editTextdetail.getText().toString().trim();
			i=dbhelper.insertWord(word,detail);
			if(i!=0)
				Toast.makeText(WordManageActivity.this, "单词录入成功 ", 8000).show();
			else
				Toast.makeText(WordManageActivity.this, "单词录入失败 ", 8000).show();
			break;
		case R.id.btnupdate:
			/*
			 * 2.2补充代码，
			 * 在WordManageActivity类的btnupdate单击事件中调用方法实现修改功能。
			 */
			
			
			
			
			
			
			break;
		case R.id.btnsearch:
			/*1.4 补充代码，
			 * 实现从WordManageActivity向QueryActivity页面跳转功能。
			 */
			
			
			
			
			
			
			break;
			

		}
	}

}
