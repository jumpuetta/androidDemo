package com.android.send;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void click(View view){
		Intent intent = new Intent();
		intent.setAction("XXOO");
		//intent.setData(Uri.parse("intent�����õ�����"));
		//������Զ���Ĺ㲥���ͳ�ȥ
		//sendBroadcast(intent); //����һ������Ĺ㲥�¼�
		//����㲥�¼��������ͳ�ȥ�� ���еĹ㲥������ ������ܵ�����¼�
		
		//����㲥������ķ��ͳ�ȥ��, �㲥�����߻ᰴ�����ȼ� ���ܵ��㲥�¼�
		// ����㲥 �ص�: �����ȼ��Ĺ㲥������ ������ֹ�� �㲥�¼�
		//sendOrderedBroadcast(intent, null);
		sendOrderedBroadcast(intent, null, new FinalRecevier(), null, 0, null, null);
	}

}
