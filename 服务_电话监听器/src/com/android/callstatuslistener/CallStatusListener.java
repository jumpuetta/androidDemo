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
	// <!-- �����ں�̨���е���������û����ֶ��رգ��ǲ���ֹͣ�� -->
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
   
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("���񱻴�����");
		//��ȡϵͳ����ķ���
		TelephonyManager manager = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
	    manager.listen(new PhoneStateListener(){
	    	private MediaRecorder recorder ;
	    	public void onCallStateChanged(int state, String incomingNumber) {
	    		super.onCallStateChanged(state, incomingNumber);
	    		//����¼����
            	
	    		switch (state) {
				case TelephonyManager.CALL_STATE_IDLE://����״̬
					//ͣ��������
					if(recorder != null){
						 recorder.stop();
	                	 recorder.reset();   
	                	 recorder.release(); 
	                	 recorder = null;
					}
					break;
                case TelephonyManager.CALL_STATE_RINGING://����״̬
                	System.out.println(incomingNumber+"������");
                	 recorder = new MediaRecorder();
                	//����¼�Ƶ���ƵԴ MICΪ��˷�  VOICE_CALLΪ��Ͳ����˷����������Ҫ�ֻ�֧�֣�
                	 recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                	 //������Ƶ�����ʽ
                	 recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
                	 //������Ƶ�ļ�����
                	 recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                	 recorder.setOutputFile("/sdcard/"+System.currentTimeMillis()+".3gp");
                	//֪ͨ¼����׼��¼��
                	 try {
						recorder.prepare();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
                case TelephonyManager.CALL_STATE_OFFHOOK://��ͨ״̬
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
		System.out.println("����������");
	}
}
