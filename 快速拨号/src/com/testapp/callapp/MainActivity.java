package com.testapp.callapp;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    private EditText text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btn = (Button)findViewById(R.id.btn_call);
		text = (EditText)findViewById(R.id.num_text);
		//1.newһ��ʵ�ֽӿڵ���
		//btn.setOnClickListener(new CallClickListener());
		//2.newһ�������ڲ���
		//btn.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callphone();
//			}
//		});
		//3.MainActivityʵ�ּ������ӿ�
		//btn.setOnClickListener(this);
		//4.��activity_main.xml��Ϊ��ť���÷���
	}

	
	//��绰������
	private void callPhone(){
		String str = text.getText().toString().trim();
		if(TextUtils.isEmpty(str)){
			Toast.makeText(MainActivity.this, "���벻��Ϊ��", 0).show();
			return;
		}
		//Intent ��ͼ  ָ�����һ����
		//Intent.ACTION_CALL ������ָ�������ʲô��
		Intent intent = new Intent(Intent.ACTION_CALL);
		//���ö����������  urlͳһ��Դ��λ��  uriͳһ��Դ��ʾ��
		//"tel:12345678901"��ʾ����λ�绰����
		intent.setData(Uri.parse("tel:"+str)); 
		//��ʼ����
		startActivity(intent);
	}
	
	public void xmlCallPhone(View view){
		callPhone();
	}
	
	private class CallClickListener implements OnClickListener{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//Button btn = (Button)v;
			//btn.setBackgroundColor(0);
			callPhone();
		}
	}


	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_call:
			callPhone();
			break;
		default:
			break;
		}
	}

}
