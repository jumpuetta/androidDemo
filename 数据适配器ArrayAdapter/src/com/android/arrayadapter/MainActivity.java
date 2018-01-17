package com.android.arrayadapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    private String[] names = {"功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四","功能一","功能二","功能三","功能四"};
    private ListView lv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.listview);
		/**
		 *该适配器 只能设定一个TextView的ID，即变动参数只有一个，显示具有很大局限性
		 *若用BaseAdapter则可自定义，灵活多变
		 */
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv, names));
	}

}
