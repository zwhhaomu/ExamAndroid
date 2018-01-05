package com.examandroid.ui;

import com.examandroid.db.WordDBHelper;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class WordListActivity extends ListActivity {
	WordDBHelper dbhelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		dbhelper=new WordDBHelper(this);
		ListView listView = getListView();
		Cursor c = dbhelper.query();
		String[] from = { "_id","word", "detail"};
		int[] to = {R.id.textid, R.id.textword, R.id.textdetail};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
				R.layout.wordrow, c, from, to);

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				TextView textid=(TextView) arg1.findViewById(R.id.textid);
				String id=textid.getText().toString();
				deleteword(id);

			}
		});
		dbhelper.close();
	}

	private void deleteword(final String wordid) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("删除单词");

		builder.setMessage("是否确定删除？")
		.setCancelable(true)
		.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				int i=dbhelper.deleteWordById(wordid);
				if(i!=0){
					Toast.makeText(WordListActivity.this, "单词删除成功 ", 8000).show();
					ListView listView = getListView();
					Cursor c = dbhelper.query();
					String[] from = { "_id","word", "detail"};
					int[] to = {R.id.textid, R.id.textword, R.id.textdetail};
					SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
							R.layout.wordrow, c, from, to);

					listView.setAdapter(adapter);}
				else
					Toast.makeText(WordListActivity.this, "单词删除失败 ", 8000).show();
			}

		});
		AlertDialog alert = builder.create();
		alert.show();


	}

}
