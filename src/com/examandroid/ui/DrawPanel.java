package com.examandroid.ui;


import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.app.Activity;

public class DrawPanel extends Activity {
	private ImageView needleView;  //指针图片
	private Timer timer;  //时间
	
	private float degree = 0.0f;  //记录指针旋转
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {  //设置仪表盘指针转动动画
			//仪表盘最大是172度，这个是自己测出来的
			if (degree >= 172.0f) {
				timer.cancel();
			}
			degree += 2.0f;
			
			/*
			 * 4.补充代码：
			 * 设计旋转动画，使得needleView实现转动。
			 */
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_panel);
		
		needleView = (ImageView) findViewById(R.id.needle);
		
		// 开始转动
		timer = new Timer();
		// 设置每一秒转动一下
		timer.schedule(new NeedleTask(), 0, 1000);
	}

	private class NeedleTask extends TimerTask {
		@Override
		public void run() {
			handler.sendEmptyMessage(0);
		}
	}
}
