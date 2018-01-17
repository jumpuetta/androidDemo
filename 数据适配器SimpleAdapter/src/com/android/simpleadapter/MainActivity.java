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
		map1.put("name","����һ");
		map1.put("image","ͼƬһ");
		Map<String,Object> map2 = new HashMap<String,Object>();
		map2.put("name","���ܶ�");
		map2.put("image","ͼƬ��");
		Map<String,Object> map3 = new HashMap<String,Object>();
		map3.put("name","������");
		map3.put("image","ͼƬ��");
		Map<String,Object> map4 = new HashMap<String,Object>();
		map4.put("name","������");
		map4.put("image","ͼƬ��");
		Map<String,Object> map5 = new HashMap<String,Object>();
		map5.put("name","������");
		map5.put("image","ͼƬ��");
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		data.add(map5);
		
		lv.setAdapter(new SimpleAdapter(this, data, R.layout.list_item, new String[]{"name","image"},new int[]{R.id.tv,R.id.iv}));
	}

}
