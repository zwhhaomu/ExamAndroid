/**
 *
 */
package com.examandroid.db;

import android.net.Uri;
import android.provider.BaseColumns;


public final class Words
{
	// 授权常量
	public static final String AUTHORITY = "com.examandroid.db.DictProvider";
	// 定义一个静态内部类，定义该ContentProvider所包的数据列的列名
	public static final class Word implements BaseColumns
	{
		public static final String WORDS_TableTbl = "wordtable";
		// 定义Content所允许操作的3个数据列
		public final static String _ID = "_id";
		public final static String WORD = "word";
		public final static String DETAIL = "detail";
		// 定义该Content提供服务的两个Uri
		public final static Uri DICT_CONTENT_URI = Uri
				.parse("content://" + AUTHORITY + "/word1");
	}
}
