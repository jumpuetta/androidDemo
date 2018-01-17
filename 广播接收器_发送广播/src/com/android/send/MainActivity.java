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
		//intent.setData(Uri.parse("intent中设置的数据"));
		//把这个自定义的广播发送出去
		//sendBroadcast(intent); //发送一条无序的广播事件
		//如果广播事件是无序发送出去的 所有的广播接受者 都会接受到这个事件
		
		//如果广播是有序的发送出去的, 广播接收者会按照优先级 接受到广播事件
		// 有序广播 特点: 高优先级的广播接受者 可以终止掉 广播事件
		//sendOrderedBroadcast(intent, null);
		sendOrderedBroadcast(intent, null, new FinalRecevier(), null, 0, null, null);
	}

}
