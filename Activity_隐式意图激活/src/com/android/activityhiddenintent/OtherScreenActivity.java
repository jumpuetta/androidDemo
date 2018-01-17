package com.android.activityhiddenintent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class OtherScreenActivity extends Activity {
    private TextView tv_data;
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	 super.onCreate(savedInstanceState);
    	 setContentView(R.layout.activity_two);
    	 tv_data = (TextView)findViewById(R.id.tv_data);
    	 Intent intent = getIntent();//获取激活它的意图
    	 Uri uri = intent.getData();
    	 String data = uri.toString();
    	 String value = intent.getStringExtra("key");
     	 tv_data.setText(data+"  "+value);
    	 System.out.println(data);
    }
}
