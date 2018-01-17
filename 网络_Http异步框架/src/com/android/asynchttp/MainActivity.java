package com.android.asynchttp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

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

	public void loginAsyncGet(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		AsyncHttpClient client = new AsyncHttpClient();
		String path;
		try {
			path = "http://10.129.67.177:8080/login/login?username="+URLEncoder.encode(name,"utf-8")+"&password="+URLEncoder.encode(pwd,"utf-8");
			client.get(path, new AsyncHttpResponseHandler() {
	            public void onSuccess(String content) {
                  //content为服务器返回的数据	            	
	              Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
	            }

				public void onFailure(Throwable error, String content) {
					Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
				}
	        });
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
	}
	public void loginAsyncPost(View view){
		final String name = username.getText().toString().trim();
		final String pwd = password.getText().toString().trim();
		AsyncHttpClient client = new AsyncHttpClient();
		try {
			String  path = "http://10.129.67.177:8080/login/login";
			RequestParams params = new RequestParams();
			params.add("username", name);
			params.add("password", pwd);
			client.post(path, params, new AsyncHttpResponseHandler() {
	            public void onSuccess(String content) {
	                  //content为服务器返回的数据	            	
		              Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
		            }

					public void onFailure(Throwable error, String content) {
						Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
					}
		        } );
		} catch (Exception e) {
			e.printStackTrace();
		}
       
	}
}
