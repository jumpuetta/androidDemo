package com.android.asyncclient;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	 private EditText username;
	 private EditText password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText)findViewById(R.id.et_username);
		password = (EditText)findViewById(R.id.et_password);
	}

	public void loginAsyncModGet(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		AsyncHttpClient client = new AsyncHttpClient();
		try {
			String  path = "http://10.129.67.177:8080/login/login?username="+URLEncoder.encode(name,"utf-8")+"&password="+URLEncoder.encode(pwd,"utf-8");
			client.get(path,new AsyncHttpResponseHandler(){

				@Override
				public void doFailure(String content) {
					 Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
				}

				@Override
				public void doSuccess(String content) {
					 Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}

	public void loginAsyncModPost(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		AsyncHttpClient client = new AsyncHttpClient();
		try {
			String  path = "http://10.129.67.177:8080/login/login";
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new BasicNameValuePair("username",name));
			parameters.add(new BasicNameValuePair("password",pwd));
			client.post(path,parameters,new AsyncHttpResponseHandler(){

				@Override
				public void doFailure(String content) {
					 Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
				}

				@Override
				public void doSuccess(String content) {
					 Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
				}
				
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}
}
