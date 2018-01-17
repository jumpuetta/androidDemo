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
		   Log.i("MyAdapter","��õ�"+position+"����Ŀ");
//		   TextView tv = new TextView(getApplicationContext());
//			tv.setTextSize(15);
			//��õ�ǰ��person������ʾ������
//			Person person = list.get(position);
//			tv.setText(person.toString());
		   
		   Person person = list.get(position);
           /**
            * �÷���������Ĳ����ļ�.xmlת��Ϊ����TextView��View����
            * */		   
		   View view = View.inflate(MainActivity.this, R.layout.list_item, null);
		   TextView tvname = (TextView)view.findViewById(R.id.tv_name);
		   TextView tvtelnumber = (TextView)view.findViewById(R.id.tv_telnumber);
		   TextView tvaccount = (TextView)view.findViewById(R.id.tv_account);
		   tvname.setText("������"+person.getName());
		   tvtelnumber.setText("�绰��"+person.getTelnumber());
		   /**
		    * ����������ת����String���Ͳ�����ʾ����Ϊ������������ʱ����Ϊ
		    * ��R.id.tv_id�Ľ��  ��ȴ���Ҳ�����Ӧ����Դ ��˻ᱨ�쳣
		    */
		   tvaccount.setText("��Ǯ��"+person.getAccount());
		   
		   
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
