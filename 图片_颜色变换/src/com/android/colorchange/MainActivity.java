package com.android.colorchange;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
    private ImageView image;
    private SeekBar sb_red;
    private SeekBar sb_green;
    private SeekBar sb_blue;
    private SeekBar sb_saturability;//饱和度
    private SeekBar sb_transparency;//透明度
    private Bitmap alertBitmap;
    private Canvas canvas;
    private Paint paint;
    private int red = 128,green = 128,blue = 128,saturability=128;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    image = (ImageView)findViewById(R.id.image);
		sb_red = (SeekBar)findViewById(R.id.sb_red);
		sb_green = (SeekBar)findViewById(R.id.sb_green);
		sb_blue = (SeekBar)findViewById(R.id.sb_blue);
		sb_saturability = (SeekBar)findViewById(R.id.sb_saturability);
		sb_transparency = (SeekBar)findViewById(R.id.sb_transparency);
		sb_red.setProgress(128);
		sb_green.setProgress(128);
		sb_blue.setProgress(128);
		sb_saturability.setProgress(128);
	//	sb_red.set
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mm);
		
		alertBitmap = Bitmap.createBitmap(bitmap.getWidth()*2/3,bitmap.getHeight()*2/3,bitmap.getConfig());
		
		canvas = new Canvas(alertBitmap);
		paint = new Paint();
		paint.setColor(Color.BLACK);
		//初始化颜色矩阵
		final ColorMatrix cm = new ColorMatrix();
		final ColorMatrix cm_sb_saturability = new ColorMatrix();
		paint.setColorFilter(new ColorMatrixColorFilter(cm));
		final Matrix matrix = new Matrix();
		matrix.setScale(1, 1);
		canvas.drawBitmap(bitmap, matrix, paint);
		image.setImageBitmap(alertBitmap);
		
		sb_red.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			//进度停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				red = seekBar.getProgress();
			}
			//进度开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度正在改变
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				float[] values = cm.getArray();
				values[0] = progress/128f+(saturability-128)/128f;
				cm.set(values);
				
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap, matrix, paint);
				image.setImageBitmap(alertBitmap);
			}
		});
		
		sb_green.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			//进度停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				green = seekBar.getProgress();
			}
			//进度开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度正在改变
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				float[] values = cm.getArray();
				values[6] = progress/128f+(saturability-128)/128f;
				cm.set(values);
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap, matrix, paint);
				image.setImageBitmap(alertBitmap);
				
				
			}
		});
		
		sb_blue.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			//进度停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				blue = seekBar.getProgress();
			}
			//进度开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度正在改变
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				float[] values = cm.getArray();
				values[12] = progress/128f+(saturability-128)/128f;
				cm.set(values);
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap, matrix, paint);
				image.setImageBitmap(alertBitmap);
			}
		});
		sb_saturability.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			//进度停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度正在改变
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				float[] values = cm.getArray();
				saturability = progress;
				values[0] = ((progress-128)/128f)+(red/128f);
				values[6] = ((progress-128)/128f)+(green/128f);
				values[12] = ((progress-128)/128f)+(blue/128f);
				cm.set(values);
				System.out.println(values[0]+"  "+values[6]+"  "+values[12]);
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap, matrix, paint);
				image.setImageBitmap(alertBitmap);
			}
		});
		
		sb_transparency.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			//进度停止拖动
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度开始拖动
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			//进度正在改变
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				float[] values = cm.getArray();
				values[18] = progress/255f;
				cm.set(values);
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap, matrix, paint);
				image.setImageBitmap(alertBitmap);
				
				paint.setColorFilter(new ColorMatrixColorFilter(cm));
				canvas.drawBitmap(bitmap, matrix, paint);
				image.setImageBitmap(alertBitmap);
			}
		});
	}

}
