package com.itheima.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String[] names = {"老张", "老方", "老毕", "李明" , "李丽", "陈江", "abc", "acc"};
		/**
		 * MultiAutoCompleteTextView nameText = (AutoCompleteTextView)this.findViewById(R.id.actv);
		 * ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names);
		 * nameText.setAdapter(adapter);
		 * nameText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		 * */
		
		AutoCompleteTextView nameText = (AutoCompleteTextView)this.findViewById(R.id.actv);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, names);
		nameText.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item1:
			Toast.makeText(this, "菜单条目1被点击了", 0).show();
			break;

		case R.id.item2:
			Toast.makeText(this, "菜单条目2被点击了", 0).show();
			break;
		}

		return super.onOptionsItemSelected(item);
	}

}
