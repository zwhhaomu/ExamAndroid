package com.examandroid.ui;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.View;

public class DrawViewActivity extends Activity {
	int width,height;
	View myView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent inent=getIntent();
		int which=inent.getIntExtra("which", 0);
		
		myView=new DrawCircle(getApplication(),which);
		
		setContentView(myView);
	}


	private class DrawCircle extends View {
		int which;
		public DrawCircle(Context context,int i) {
			super(context);
			which=i;
		}

		@Override
		protected void onDraw(Canvas canvas) {
			int x,y,r;
			// TODO Auto-generated method stub
			super.onDraw(canvas);
	
			canvas.drawColor(Color.BLACK);
				
			Paint paint = new Paint();
			switch(which){
			case 0:

				paint.setStyle(Paint.Style.FILL_AND_STROKE);
				x=(int)width/2;
				y=(int)height/2;
				r=x/2-1;
			
				paint.setColor(Color.BLUE);
				canvas.drawCircle(x-r, y, r, paint);
				paint.setColor(Color.GREEN);
				canvas.drawCircle(x, y-r, r, paint);
				paint.setColor(Color.YELLOW);
				canvas.drawCircle(x+r, y, r, paint);
				paint.setColor(Color.RED);
				canvas.drawCircle(x, y+r, r, paint);
				paint.setColor(Color.WHITE);
				canvas.drawCircle(x, y, r, paint);
				paint.setColor(Color.DKGRAY);
				canvas.drawCircle(x, y, r/3, paint);

				break;
			case 1:
				

				paint.setStyle(Paint.Style.FILL_AND_STROKE);
				x=(int)width/2;
				y=(int)height/2;
				r=x;

				paint.setColor(Color.WHITE);
				canvas.drawCircle(x, y,r-2, paint);
				paint.setColor(Color.BLACK);
				canvas.drawCircle(x, y+r/4, r*3/4, paint);
				paint.setColor(Color.WHITE);
				canvas.drawCircle(x, y+r/2, r/2, paint);
				paint.setColor(Color.BLACK);
				canvas.drawCircle(x, y+r/2, r/4, paint);
				paint.setColor(Color.WHITE);
				canvas.drawCircle(x, y-r/4, r/6, paint);
				break;
			}
			
		}
		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			// TODO Auto-generated method stub
			super.onSizeChanged(w, h, oldw, oldh);
			width=w;
			height=h;
		}

	}
}