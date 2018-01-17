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

		// 第二个参数为下拉列表框每一项的界面样式，该界面样式由Android系统提供，当然您也可以自定义
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
