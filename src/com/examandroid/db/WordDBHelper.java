package com.examandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class WordDBHelper extends SQLiteOpenHelper {
	private SQLiteDatabase db;
	private static final String CREATE_SQL="create table wordtable" +
			"(_id integer primary key autoincrement," +
			"word text," +
			"detail text)";



	public WordDBHelper(Context context) {
		super(context, "word.db", null, 2);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db=db;
		db.execSQL(CREATE_SQL);	

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		this.db=db;
		db.execSQL(CREATE_SQL);	


	}

	public int insertWord(String word,String detail){
		int i=0;
		db=getWritableDatabase();

		ContentValues cv=new ContentValues();
		cv.put("detail",detail);
		cv.put("word",word);
		i=(int) db.insert("wordtable", null, cv);
		db.close();
		return i;
	}

	public int deleteWord(){
		db=getWritableDatabase();
		int i=0;

		if(db==null)
			db=getReadableDatabase();
		i=db.delete("wordtable", null,null);
		return i;
	}
	public int deleteWordByWord(String word){
		db=getWritableDatabase();
		int i=0;
		String[] args={word};
		if(db==null)
			db=getReadableDatabase();
		i=db.delete("wordtable", "word=?",args);
		return i;
	}
	public int deleteWordById(String id){
		db=getWritableDatabase();
		int i=0;
		String[] args={id};
		if(db==null)
			db=getReadableDatabase();
		i=db.delete("wordtable", "_id=?",args);
		return i;

	}

	public Cursor query(){
		db=getWritableDatabase();
		Cursor c=db.query("wordtable", null, null, null, null, null, "_id asc","");
		return c;			
	}
	/*2.1 补充完善public int updateWord(String word,String detail)方法.
	 * 使其实现单词信息的修改功能，根据输入的单词修改对应的单词明细。
	 */
	public int updateWord(String word,String detail){
		db=getWritableDatabase();
		int i=0;
		
		
		
		
		
		
		
		return i;
	}
	


	public void close(){
		if(db!=null)
			db.close();
	}

}
