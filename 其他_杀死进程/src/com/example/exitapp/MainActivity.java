package com.example.exitapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	//û����һ��activity �����������ô�����
    	MyApplication myapp = (MyApplication) getApplication();
    	myapp.activies.add(this);
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,MyService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
    	//ÿ����һ��activity �����������ô�����
    	MyApplication myapp = (MyApplication) getApplication();
    	myapp.activies.remove(this);
    	
    	super.onDestroy();
    }
    
    
    public void click(View view){
    	//���ܲ���ȫ.
    	//��ȫ���˳�  ����Ҫ��ȫ�İ�ÿһ��activity�����ر���. 
    	//��ȡ��ǰӦ�õ�id    android.os.Process.myPid()
    	android.os.Process.killProcess(android.os.Process.myPid());
    	
    	ActivityManager manager= (ActivityManager)getSystemService(ACTIVITY_SERVICE);
    	manager.killBackgroundProcesses("com.android.browser");
    }
    public void click2(View v){
    	Intent intent = new Intent(this,Activity01.class);
    	startActivity(intent);
    }
}
