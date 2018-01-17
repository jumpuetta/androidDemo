package com.android.simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
    private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.listview);
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("name","功能一");
		map1.put("image","图片一");
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("name","功能二");
		map2.put("image","图片二");
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("name","功能三");
		map3.put("image","图片三");
		Map<String,Object> map4 = new HashMap<String,Object>();
		map4.put("name","功能四");
		map4.put("image","图片四");
		Map<String,Object> map5 = new HashMap<String,Object>();
		map5.put("name","功能五");
		map5.put("image","图片五");
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		data.add(map5);
		
		lv.setAdapter(new SimpleAdapter(this, data, R.layout.list_item, new String[]{"name","image"},new int[]{R.id.tv,R.id.iv}));
	}

}
