package com.android.textnet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
    
     protected void onStart() {
	   // TODO Auto-generated method stub
	  super.onStart();
	//获取参数Context.CONNECTIVITY_SERVICE的系统服务ConnectivityManager
		ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		//得到当前联网信息
		NetworkInfo info = manager.getActiveNetworkInfo();
		//判断网络是否可以用
		if(info!=null&&info.isConnected()){
			Toast.makeText(MainActivity.this,"当前网络可用", Toast.LENGTH_SHORT).show();
		}else{
	         AlertDialog.Builder builder = new Builder(this);
	         builder.setTitle("提示");
	         builder.setMessage("当前网络不可用，请设置网络");
	         builder.setPositiveButton("确定",new OnClickListener() {
		     public void onClick(DialogInterface dialog, int which) {
				 //cmp=com.android.settings/.Settings
				Intent intent = new Intent();
				intent.setClassName("com.android.settings", "com.android.settings.Settings");
			    startActivity(intent); 
			}
	     });
	         builder.show();
	   }
		
     }
}
