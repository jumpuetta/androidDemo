package com.android.surface;


import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private EditText filepath;
    private Button bt_play,bt_stop;
    private MediaPlayer mediaPlayer; 
    private SurfaceView surfaceView;
    private SeekBar seekBar;
    private   int  currentPoint;
    private int max;
    private boolean flag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		filepath = (EditText)findViewById(R.id.filepath);
		surfaceView = (SurfaceView)findViewById(R.id.surfaceView);
		/* 下面设置Surface不维护自己的缓冲区，而是等待屏幕的渲染引擎将内容推送到用户面前 */
		//需要在低版本模拟器里面加上该句
		//surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		
		surfaceView.getHolder().addCallback(new Callback() {
			
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(mediaPlayer != null && mediaPlayer.isPlaying()){
					currentPoint = mediaPlayer.getCurrentPosition();
					//mediaPlayer.stop();
					stop();
				}
			}
			
			public void surfaceCreated(SurfaceHolder holder) {
				if(currentPoint > 0){
					play(currentPoint);
				}
				
			}
			
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
			}
		});
		
		
		bt_play = (Button)findViewById(R.id.play);
		bt_stop = (Button)findViewById(R.id.stop);
		seekBar = (SeekBar)findViewById(R.id.seekBar);
		bt_play.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				if(mediaPlayer != null){
					  mediaPlayer.seekTo(seekBar.getProgress());
					 }
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				 
			}
		});
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			play(currentPoint);
			break;
        case R.id.stop:
	        stop();
	       break;

		default:
			break;
		}
	}

	
	public void play(final int current){
		
		try {
			  if(mediaPlayer == null){
		         mediaPlayer = new MediaPlayer();
		         Uri myUri = Uri.parse(filepath.getText().toString().trim());
				  //设置音频流的类型
			     mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			         //SurfaceHolder为显示影片的容器
			     mediaPlayer.setDataSource(this, myUri);
				 mediaPlayer.prepareAsync();
			  }
			 
			  mediaPlayer.setDisplay(surfaceView.getHolder());
			  mediaPlayer.setOnPreparedListener(new OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer mp) {
					// TODO Auto-generated method stub
					mediaPlayer.seekTo(current);
					//获取最大时长
					max = mediaPlayer.getDuration();
					seekBar.setMax(max);
					mediaPlayer.start();
					
					new Thread(){
						public void run() {
							flag = true;
							while(flag){
								int point = mediaPlayer.getCurrentPosition();
								seekBar.setProgress(point);
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
					}.start();
				}
				
				
			});
		} catch (Exception e) {
			Toast.makeText(this, "视屏播放失败", Toast.LENGTH_SHORT).show();
		}
		
	
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			public void onCompletion(MediaPlayer mp) {
				bt_play.setEnabled(true);
				currentPoint = 0;
				flag = false;
			}
		});
		//当音频文件损坏时的回调函数
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			
			public boolean onError(MediaPlayer mp, int what, int extra) {
				flag = false;
				currentPoint = 0;
				return false;
			}
		});
		bt_play.setEnabled(false);
		bt_stop.setEnabled(true);
	}
	
	public void stop(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			currentPoint = mediaPlayer.getCurrentPosition();
			mediaPlayer.release();
			mediaPlayer  = null;
			bt_play.setEnabled(true);
			bt_stop.setEnabled(false);
			flag = false;
		}
	}
	
	
}
