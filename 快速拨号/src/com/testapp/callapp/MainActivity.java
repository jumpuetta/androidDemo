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
		//1.new一个实现接口的类
		//btn.setOnClickListener(new CallClickListener());
		//2.new一个匿名内部类
		//btn.setOnClickListener(new OnClickListener() {
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				callphone();
//			}
//		});
		//3.MainActivity实现监听器接口
		//btn.setOnClickListener(this);
		//4.在activity_main.xml中为按钮配置方法
	}

	
	//打电话话方法
	private void callPhone(){
		String str = text.getText().toString().trim();
		if(TextUtils.isEmpty(str)){
			Toast.makeText(MainActivity.this, "号码不能为空", 0).show();
			return;
		}
		//Intent 意图  指明想干一件事
		//Intent.ACTION_CALL 参数，指明具体干什么事
		Intent intent = new Intent(Intent.ACTION_CALL);
		//设置动作所需参数  url统一资源定位符  uri统一资源标示符
		//"tel:12345678901"表示数据位电话号码
		intent.setData(Uri.parse("tel:"+str)); 
		//开始动作
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
