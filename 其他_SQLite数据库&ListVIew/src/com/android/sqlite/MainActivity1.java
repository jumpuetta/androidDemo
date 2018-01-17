package com.android.sqlite;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.sqlite.dao.PersonDao;
import com.android.sqlite.domain.Person;

public class MainActivity1 extends Activity {
    private LinearLayout ll ;
    private ListView lv;
    private List<Person> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll = (LinearLayout)findViewById(R.id.root);
		PersonDao pd = new PersonDao(this);
		list = pd.findAll();
		for (Person person : list) {
			String info = person.toString();
			TextView tv = new TextView(this);
			tv.setText(info);
			ll.addView(tv);
		
		
	}
	
	}
}
