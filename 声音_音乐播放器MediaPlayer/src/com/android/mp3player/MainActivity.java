package com.android.mp3player;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private EditText filepath;
    private Button bt_play,bt_pause,bt_replay,bt_stop;
    private MediaPlayer mediaPlayer; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		filepath = (EditText)findViewById(R.id.filepath);
		bt_play = (Button)findViewById(R.id.play);
		bt_pause = (Button)findViewById(R.id.pause);
		bt_replay = (Button)findViewById(R.id.replay);
		bt_stop = (Button)findViewById(R.id.stop);
		
		bt_play.setOnClickListener(this);
		bt_pause.setOnClickListener(this);
		bt_replay.setOnClickListener(this);
		bt_stop.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			play();
			break;
        case R.id.replay:
			replay();
			break;
        case R.id.pause:
	        pause();
	        break;
        case R.id.stop:
	        stop();
	       break;

		default:
			break;
		}
	}

	
	public void play(){
		if(mediaPlayer == null){
		try {
			  Uri myUri = Uri.parse(filepath.getText().toString().trim());
		      mediaPlayer = new MediaPlayer();
		      //设置音频流的类型
		      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			  mediaPlayer.setDataSource(getApplicationContext(), myUri);
			  //mediaPlayer.prepare();
			  mediaPlayer.prepareAsync();
			  mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
				
				public void onPrepared(MediaPlayer mp) {
					mediaPlayer.start();
				}
			});
		} catch (Exception e) {
			Toast.makeText(this, "音乐播放失败", Toast.LENGTH_SHORT).show();
		}
		}
	
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			public void onCompletion(MediaPlayer mp) {
				bt_play.setEnabled(true);
			}
		});
		//当音频文件损坏时的回调函数
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});
		bt_play.setEnabled(false);
	}
	
	public void stop(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer = null;
			bt_play.setEnabled(true);
		}
	}
	
	public void replay(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.seekTo(0);
		}
		play();
	}
	
	public void pause(){
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.pause();
			bt_pause.setText("继续");
			bt_play.setEnabled(true);
		}else if(mediaPlayer != null && "继续".equals(bt_pause.getText().toString().trim())){
			bt_pause.setText("暂停");
			mediaPlayer.start();
			bt_play.setEnabled(false);
		}
	}
	
}
