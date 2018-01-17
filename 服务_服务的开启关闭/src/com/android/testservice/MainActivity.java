package com.android.testservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends Activity {
	private BinderService myBinder;
	private MyConn conn;
	private boolean flag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
    @Override
    protected void onDestroy() {
   	  // TODO Auto-generated method stub
	   super.onDestroy();
	   if(flag && conn != null){
    	   unbindService(conn);
    	   flag = false;
    	}
    }
    public void start(View view){
    	Intent intent = new Intent(this,TestService.class);
		/**
		 * ��������  ��API��ʼ����  �����Ϊϵͳnew������
		 * startService�����������ѱ�����������onStartCommand()����onStart()
		 *                ������û�б�����������onCreate����onStartCommand()����onStart()
		 * startService�����ķ�����Activity�ر�ʱ  ���񲻻�رյ�����
		 * startService�����ķ����ڽ����ʱ���ᱻ����           
		 */
    	
		startService(intent);
    }
    
    public void stop(View view){
    	Intent intent = new Intent(this,TestService.class);
		//ֹͣ����
		stopService(intent);
    }
    
    public void bind(View view){
    	Intent intent = new Intent(this,TestService.class);
		/**
		 * �󶨷���
		 * intent  ����������ͼ
		 * conn  ��������� ���������������  ����Ϊnull
		 * BIND_AUTO_CREATE ��������������ȿ����÷���
		 * bindService�����������ѱ�����������onBind()
		 *            ������û�б�����������onCreate����onBind()
		 * bindService����������Activity�ر�ʱ  ����Ҳ����󲢹ر�
		 * bindService��Ϊ�󶨶������ķ����ڽ����ʱͬʱҲ�ᱻ����
		 *            ���ڰ�״̬�ķ����ڽ��ǰ���ܱ�stopService���٣��������ʱ����stopService��������������Ч��������ȵ�unbindService�������ú�������Ч
		 */
    	conn =  new MyConn();
		flag = bindService(intent,conn, BIND_AUTO_CREATE );
    }
    
    public void unbind(View view){
    	/**
    	 * ������δ���󶨶�����󶨻ᱨ�쳣
    	 * */
    	if(flag && conn != null){
    	   unbindService(conn);
    	   flag = false;
    	}
    }
    
	private class MyConn implements ServiceConnection{

		// �ڷ��񱻳ɹ��󶨵�ʱ�� ���õķ���
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("����Ѵ����˷��ػ�����...");
			//����3: ���񷵻ص�ibinder���� �ᱻ���ݸ� MyConn �Ļص�����
			myBinder  = (BinderService) service;
			//�ö����������onBind()���ص�IBinder����Ϊͬһ����
		}
		// �ڷ���ʧȥ�󶨵�ʱ�� ���õķ��� ֻ�г����쳣 ��ֹ,
		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}
		
	}
    public void change(View view){
    	// ����ϵͳ����ڴ��������ʱ�� �ᴴ����֮��Ӧ��������,
        // ����Ĵ��� ��ֱ��new���� .
    	// CungeService service = new CungeService();
        // service.changeSing("����֮��");
        //����5: ����ibinder ���� ��ӵĵ����˷�������ķ���
    	myBinder.binderChange("hello");
    }
}
