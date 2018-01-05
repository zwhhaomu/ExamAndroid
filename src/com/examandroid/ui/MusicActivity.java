package com.examandroid.ui;

import com.examandroid.util.music;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MusicActivity extends Activity {
	private Button playbtn,onPausebtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music);
		playbtn=(Button) findViewById(R.id.playbtn);
		onPausebtn=(Button) findViewById(R.id.onPausebtn);
		playbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				music.paly(getApplicationContext(), R.raw.yue);
			}
		});
		
		onPausebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				music.stop(getApplicationContext());
			}
		});
	}
}
