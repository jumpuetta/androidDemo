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

public class MainActivity extends Activity {
    private LinearLayout ll ;
    private ListView lv;
    private List<Person> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//		ll = (LinearLayout)findViewById(R.id.root);
//		PersonDao pd = new PersonDao(this);
//		list = pd.findAll();
//		for (Person person : list) {
//			String info = person.toString();
//			TextView tv = new TextView(this);
//			tv.setText(info);
//			ll.addView(tv);
		
		setContentView(R.layout.listview);
		showByListView();
		
	}
	
	public void showByListView(){
		PersonDao pd = new PersonDao(this);
		list = pd.findAll();
		lv = (ListView)findViewById(R.id.listview);
		lv.setAdapter(new MyAdapter());
	}
	
	
  class MyAdapter extends BaseAdapter{
	  public View getView(int position, View convertView, ViewGroup parent) {
		   Log.i("MyAdapter","获得第"+position+"个条目");
//		   TextView tv = new TextView(getApplicationContext());
//			tv.setTextSize(15);
			//获得当前的person对象并显示到界面
//			Person person = list.get(position);
//			tv.setText(person.toString());
		   
		   Person person = list.get(position);
           /**
            * 该方法将定义的布局文件.xml转换为类似TextView的View对象
            * */		   
		   View view = View.inflate(MainActivity.this, R.layout.list_item, null);
		   TextView tvname = (TextView)view.findViewById(R.id.tv_name);
		   TextView tvtelnumber = (TextView)view.findViewById(R.id.tv_telnumber);
		   TextView tvaccount = (TextView)view.findViewById(R.id.tv_account);
		   tvname.setText("姓名："+person.getName());
		   tvtelnumber.setText("电话："+person.getTelnumber());
		   /**
		    * 整形数据需转换成String类型才能显示，因为当里面是整形时会以为
		    * 是R.id.tv_id的结果  但却查找不到对应的资源 因此会报异常
		    */
		   tvaccount.setText("金钱："+person.getAccount());
		   
		   
		  return view;
		}
		@Override
		public long getItemId(int position) {
			return 0;
		}
		@Override
		public Object getItem(int position) {
			return null;
		}
		@Override
		public int getCount() {
			return list.size();
		}
  }
}
