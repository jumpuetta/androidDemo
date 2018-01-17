package com.android.smssender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText et_contact1;
    private EditText et_contact2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_contact1 = (EditText)findViewById(R.id.et_contact1);
		et_contact2 = (EditText)findViewById(R.id.et_contact2);
	}
	//当新开启的Activity关闭时调用该方法
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		System.out.println("onActivityResult");
		if(data != null&&requestCode==0){
			et_contact1.setText(data.getStringExtra("number"));
		}
		if(data != null&&requestCode==1){
			et_contact2.setText(data.getStringExtra("number"));
		}
	}

  public void selectContact1(View view){
	  Intent intent = new Intent();
	  intent.setClassName(this,"com.android.smssender.SelectContactActivity");
	 // startActivity(intent);
	  //开启一个新的Activity，并且它执行后会有结果返回
	  startActivityForResult(intent, 0);
  }
  
  public void selectContact2(View view){
	  Intent intent = new Intent();
	  intent.setClassName(this,"com.android.smssender.SelectContactActivity");
	 // startActivity(intent);
	  //开启一个新的Activity，并且它执行后会有结果返回
	  startActivityForResult(intent, 1);
  }
}
