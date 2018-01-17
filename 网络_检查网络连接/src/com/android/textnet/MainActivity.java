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
	//��ȡ����Context.CONNECTIVITY_SERVICE��ϵͳ����ConnectivityManager
		ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		//�õ���ǰ������Ϣ
		NetworkInfo info = manager.getActiveNetworkInfo();
		//�ж������Ƿ������
		if(info!=null&&info.isConnected()){
			Toast.makeText(MainActivity.this,"��ǰ�������", Toast.LENGTH_SHORT).show();
		}else{
	         AlertDialog.Builder builder = new Builder(this);
	         builder.setTitle("��ʾ");
	         builder.setMessage("��ǰ���粻���ã�����������");
	         builder.setPositiveButton("ȷ��",new OnClickListener() {
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
