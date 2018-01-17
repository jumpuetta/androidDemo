package com.android.activityhiddenintent;

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

		// cmp=com.android.browser/.BrowserActivity

   public void click(View view){
	   /**
	    * ��ʽ��ͼ������Ϊ������ָ������ʵʩ����
	    * ��ϵͳ��⵽��ЩӦ�����������Ϊ���ṩ���û�����
	    */
	   Intent intent = new Intent();
	   intent.setAction(Intent.ACTION_VIEW);
	   intent.setData(Uri.parse("http://www.baidu.com"));
	   startActivity(intent);
   }
   
   public void click2(View view){
	   Intent intent = new Intent();
	   //����������ͼ����������ЩӦ����ɸö���
	   intent.setAction("android.intent.action.XXXX");
	   
	   //ע��setData��setType����������ͬʱ���ã���Ϊ���ǻụ����նԷ�������
	   //��������
	   // intent.setData(Uri.parse("ftp:110110110110"));
	   //������������
	   // intent.setType("audio/mpeg");
	   
	   intent.setDataAndType(Uri.parse("ftp:110110110110"), "audio/mpeg");
	   //��ͬActivity֮�䴫����,�����ݶ�����ö�����ʵ�����л��ӿ�
	   intent.putExtra("key", "1000000");
	   //������Ϣ
	   intent.addCategory("android.intent.category.DEFAULT");
	   startActivity(intent);
   }
   
   public void click3(View view){
	   Intent intent = new Intent();
	   intent.setAction("android.intent.action.SENDTO");
	   intent.setData(Uri.parse("sms:110"));
	   intent.addCategory("android.intent.category.DEFAULT");
	   startActivity(intent);
   }
   
   
}
