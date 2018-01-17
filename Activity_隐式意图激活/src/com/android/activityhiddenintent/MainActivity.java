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
	    * 隐式意图描述行为，并不指定具体实施方法
	    * 当系统检测到哪些应用能满足该行为并提供给用户调用
	    */
	   Intent intent = new Intent();
	   intent.setAction(Intent.ACTION_VIEW);
	   intent.setData(Uri.parse("http://www.baidu.com"));
	   startActivity(intent);
   }
   
   public void click2(View view){
	   Intent intent = new Intent();
	   //描述动作意图，会搜索那些应用完成该动作
	   intent.setAction("android.intent.action.XXXX");
	   
	   //注：setData与setType两方法不能同时调用，因为它们会互相清空对方的数据
	   //描述数据
	   // intent.setData(Uri.parse("ftp:110110110110"));
	   //描述数据类型
	   // intent.setType("audio/mpeg");
	   
	   intent.setDataAndType(Uri.parse("ftp:110110110110"), "audio/mpeg");
	   //不同Activity之间传数据,若传递对象则该对象需实现序列化接口
	   intent.putExtra("key", "1000000");
	   //附加信息
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
