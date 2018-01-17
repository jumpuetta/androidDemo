package com.example.tweenanim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private ImageView iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}

	/**
	 * 透明度变化的动画效果
	 * 
	 * @param view
	 */
	public void alpha(View view) {
//		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
//		aa.setDuration(2000);//显示时间
//		aa.setRepeatCount(3);// 最终执行四次
//		aa.setRepeatMode(Animation.REVERSE);//重复、逆向
//		iv.startAnimation(aa);
		
		
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha_in);
		iv.startAnimation(anim);
	}

	public void scale(View view) {
		// ScaleAnimation sa = new ScaleAnimation(0.0f, 2.0f, 0.0f, 2.0f);//左上角
//		ScaleAnimation sa = new ScaleAnimation(0.0f, 2.0f, 0.0f, 2.0f,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//
//		sa.setDuration(2000);
//		sa.setRepeatCount(2);// 最终执行2次
//		sa.setRepeatMode(Animation.REVERSE);
//		sa.setFillAfter(true);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale_in);
		iv.startAnimation(anim);

	}

	public void rotate(View view) {
//		RotateAnimation ra = new RotateAnimation(0, 360,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		ra.setDuration(2000);
//		iv.startAnimation(ra);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate_in);
		iv.startAnimation(anim);
	}

	public void trans(View view) {
//		TranslateAnimation ta = new TranslateAnimation(
//				Animation.RELATIVE_TO_PARENT, 0.0f,
//				Animation.RELATIVE_TO_PARENT, 1.0f,
//				Animation.RELATIVE_TO_PARENT, 0.0f,
//				Animation.RELATIVE_TO_PARENT, 1.0f);
//		ta.setDuration(3000);
//		iv.startAnimation(ta);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.trans_out);
		iv.startAnimation(anim);
	}

	public void set(View view){
//		RotateAnimation ra = new RotateAnimation(0, 360,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		ra.setDuration(2000);
//		ScaleAnimation sa = new ScaleAnimation(0.0f, 2.0f, 0.0f, 2.0f,
//				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//				0.5f);
//		sa.setDuration(2000);
//		AnimationSet set = new AnimationSet(false);
//		set.addAnimation(ra);
//		set.addAnimation(sa);
//		iv.startAnimation(set);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.set);
		iv.startAnimation(anim);
	}
}
