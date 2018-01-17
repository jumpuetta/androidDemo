package com.android.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class TestService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onBind");
		return new MyBinder();
	}
  
	private class MyBinder extends Binder implements BinderService{
		//��ӵ����ô����� �����˴���ķ���
		public void binderChange(String name){
			change(name);
		}
		
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("onCreate");
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("onDestroy");
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		System.out.println("onStart");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		System.out.println("onStartCommand");
		return super.onStartCommand(intent, flags, startId);
		
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onUnbind");
		return super.onUnbind(intent);
	}
	
	public void change(String str){
		Toast.makeText(getApplicationContext(), "�����˷�������ķ���",Toast.LENGTH_SHORT).show();
	}

}
