package com.android.soundpool;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private SoundPool pool;
	private int soundID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		pool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		soundID = pool.load(this, R.drawable.fbv004, 1);
	}

   public void click(View view) {
	   
        pool.play(soundID, 1f, 1f, 0, -1, 1);
   }
}
