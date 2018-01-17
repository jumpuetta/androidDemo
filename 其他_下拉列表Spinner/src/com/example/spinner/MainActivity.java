package com.example.spinner;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// �ڶ�������Ϊ�����б��ÿһ��Ľ�����ʽ���ý�����ʽ��Androidϵͳ�ṩ����Ȼ��Ҳ�����Զ���
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter.add("java");
		adapter.add("dotNet");
		adapter.add("php");
		Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Spinner spinner = (Spinner)parent;
				String itemContent = (String)parent.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), itemContent, 0).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.i(TAG,  parent.getClass().getName());
			}

		
		
		});

	}

}
