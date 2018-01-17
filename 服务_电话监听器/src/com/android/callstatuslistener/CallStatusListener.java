package com.android.callstatuslistener;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class CallStatusListener extends Service {
	// <!-- 长期在后台运行的组件，若用户不手动关闭，是不会停止的 -->
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
   
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("服务被创建了");
		//获取系统服务的方法
		TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
	    manager.listen(new PhoneStateListener(){
	    	private MediaRecorder recorder ;
	    	public void onCallStateChanged(int state, String incomingNumber) {
	    		super.onCallStateChanged(state, incomingNumber);
	    		//创建录音机
            	
	    		switch (state) {
				case TelephonyManager.CALL_STATE_IDLE://空闲状态
					//停掉收音机
					if(recorder != null){
						 recorder.stop();
	                	 recorder.reset();   
	                	 recorder.release(); 
	                	 recorder = null;
					}
					break;
                case TelephonyManager.CALL_STATE_RINGING://响铃状态
                	System.out.println(incomingNumber+"来电了");
                	 recorder = new MediaRecorder();
                	//设置录制的音频源 MIC为麦克风  VOICE_CALL为听筒和麦克风的声音（需要手机支持）
                	 recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                	 //设置音频输出格式
                	 recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
                	 //设置音频文件编码
                	 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                	 recorder.setOutputFile("/sdcard/"+System.currentTimeMillis()+".3gp");
                	//通知录音机准备录音
                	 try {
						recorder.prepare();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
                case TelephonyManager.CALL_STATE_OFFHOOK://接通状态
                	if(recorder != null ) {
                	   recorder.start();   
                	}
					break;
				default:
					break;
				}
	    	}
	    	
	    }, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("服务被销毁了");
	}
}
