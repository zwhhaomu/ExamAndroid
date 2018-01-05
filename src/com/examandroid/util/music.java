package com.examandroid.util;


import com.examandroid.ui.Prefs;

import android.content.Context;
import android.media.MediaPlayer;

public class music {
	private static MediaPlayer mp=null;
	private static MediaPlayer mmsound=null;
	/*
	 * 控制启动音乐播放，context是音乐播放时的上下文环境，
	 * resources是要播放的音乐资源文件
	 * 
	 * */
	public static  void paly(Context context,int resources){
		stop(context);//播放指定音乐之前先把上下文任意多媒体停止
		/*
		 * 6.1补充代码，
		 * 实现paly方法，使其能实现音乐播放功能。
		 */
		
		
		
		
		
		
		
	}
	public static void stop(Context context) {
		/*
		 * 6.2.补充代码，
		 * 实现stop方法，使其能实现音乐停止功能。
		 */

		
		
		
		
		
		

	}
	public static void palySound(Context context,int resource){
		stop(context);
		if(Prefs.getSoundSet(context)){
			mmsound=MediaPlayer.create(context, resource);
			mmsound.start();
		}		
	}

}
