package com.examandroid.db;

import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


public class DictProvider extends ContentProvider
{
	// 数据库帮助类
	private WordDBHelper dbHelper; 
	// Uri工具类
	private static final UriMatcher sUriMatcher;
	// 查询、更新条件
	private static final int WORDS = 1;
	private static final int WORDS_ID = 2;
	// 查询列集合
	private static HashMap<String, String> wordProjectionMap;

	static {
		// Uri匹配工具类
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(Words.AUTHORITY, "word1", WORDS);
		sUriMatcher.addURI(Words.AUTHORITY, "word1/#", WORDS_ID);
		// 实例化查询列集合
		wordProjectionMap = new HashMap<String, String>();
		// 添加查询列
		wordProjectionMap.put(Words.Word._ID, Words.Word._ID);
		wordProjectionMap.put(Words.Word.WORD, Words.Word.WORD);
		wordProjectionMap.put(Words.Word.DETAIL, Words.Word.DETAIL);

	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count=0;
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 获得数据库实例
		switch(sUriMatcher.match(uri)){
		case WORDS:
			count=db.delete(Words.Word.WORDS_TableTbl, selection, selectionArgs);
		case WORDS_ID:
			//通过Uri传递参数temp
			long wordid=ContentUris.parseId(uri);
			String whereClause=Words.Word._ID+"="+wordid;
			count=db.delete(Words.Word.WORDS_TableTbl,whereClause, selectionArgs);
			break;
		}
		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri))
		{
		// 如果操作的数据是多项记录
		case WORDS:
			return "com.examandroid.db.DictProvider/word1";
			// 如果操作的数据是单项记录
		case WORDS_ID:
			return "com.examandroid.db.DictProvider/word1/#";
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// 获得数据库实例
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		switch (sUriMatcher.match(uri))
		{
		// 如果Uri参数代表操作全部数据项
		case WORDS:
			// 插入数据，返回插入记录的ID
			long rowId = db.insert(Words.Word.WORDS_TableTbl, Words.Word._ID, values);
			// 如果插入成功返回uri
			if (rowId > 0)
			{
				// 在已有的 Uri的后面追加ID
				Uri wordUri = ContentUris.withAppendedId(uri, rowId);
				// 通知数据已经改变
				getContext().getContentResolver().notifyChange(wordUri, null);
				return wordUri;
			}
			break;
		default :
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
		return null;
	}

	@Override
	public boolean onCreate() {
		// 实例化数据库帮助类
		dbHelper = new WordDBHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String where,
			String[] whereArgs, String sortOrder)
	{
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		switch (sUriMatcher.match(uri))
		{
		// 如果Uri参数代表操作全部数据项
		case WORDS:
			// 执行查询
			return db.query(Words.Word.WORDS_TableTbl, projection, where,
					whereArgs, null, null, sortOrder);
			// 如果Uri参数代表操作指定数据项
		case WORDS_ID:
			// 解析出想查询的记录ID
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			// 如果原来的where子句存在，拼接where子句
			if (where != null && !"".equals(where))
			{
				whereClause = whereClause + " and " + where;
			}
			return db.query(Words.Word.WORDS_TableTbl, projection, whereClause, whereArgs,
					null, null, sortOrder);
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String where, String[] whereArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		// 记录所修改的记录数
		int num = 0;
		switch (sUriMatcher.match(uri))
		{
		// 如果Uri参数代表操作全部数据项
		case WORDS:
			num = db.update(Words.Word.WORDS_TableTbl, values, where, whereArgs);
			break;
			// 如果Uri参数代表操作指定数据项	
		case WORDS_ID:
			// 解析出想修改的记录ID
			long id = ContentUris.parseId(uri);
			String whereClause = Words.Word._ID + "=" + id;
			// 如果原来的where子句存在，拼接where子句
			if (where != null && !where.equals(""))
			{
				whereClause = whereClause + " and " + where;
			}
			num = db.update(Words.Word.WORDS_TableTbl, values, whereClause, whereArgs);
			break;
		default:
			throw new IllegalArgumentException("未知Uri:" + uri);
		}
		// 通知数据已经改变
		getContext().getContentResolver().notifyChange(uri, null);
		return num;
	}
}