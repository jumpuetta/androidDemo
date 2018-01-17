package com.android.arrayadapter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    private String[] names = {"����һ","���ܶ�","������","������","����һ","���ܶ�","������","������","����һ","���ܶ�","������","������","����һ","���ܶ�","������","������","����һ","���ܶ�","������","������","����һ","���ܶ�","������","������","����һ","���ܶ�","������","������","����һ","���ܶ�","������","������"};
    private ListView lv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv = (ListView)findViewById(R.id.listview);
		/**
		 *�������� ֻ���趨һ��TextView��ID�����䶯����ֻ��һ������ʾ���кܴ������
		 *����BaseAdapter����Զ��壬�����
		 */
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv, names));
	}

}
