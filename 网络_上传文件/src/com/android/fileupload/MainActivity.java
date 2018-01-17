package com.android.fileupload;

import java.io.File;
import java.io.FileNotFoundException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class MainActivity extends Activity {
    private EditText ev_filepath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ev_filepath = (EditText)findViewById(R.id.et_filepath);
	}
    
	public void upLoad(View view){
		AsyncHttpClient client = new AsyncHttpClient();
		String  filepath = ev_filepath.getText().toString().trim();
		String path = "http://10.129.67.177:8080/login/file";
		File file = new File(filepath);
		try {
			if(file.exists()&&file.length()>0){
				RequestParams params = new RequestParams();
			    params.put("file", file);
			    client.post(path, params, new AsyncHttpResponseHandler() {
	               public void onSuccess(String content) {
	                  //content为服务器返回的数据	            	
		              Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
		            }

					public void onFailure(Throwable error, String content) {
						Toast.makeText(MainActivity.this,content, Toast.LENGTH_SHORT).show();
					}
		        } );
			}else{
				Toast.makeText(this,"文件无效", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(this,"文件无效", Toast.LENGTH_SHORT).show();		
		}
	}
}
