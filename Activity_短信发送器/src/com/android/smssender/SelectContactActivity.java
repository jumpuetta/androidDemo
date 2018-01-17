package com.android.smssender;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectContactActivity extends Activity {
	private ListView list;
	private List<Contact> contacts;
   protected void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_selectcontact);
	   list = (ListView)findViewById(R.id.list_contacts);
	   contacts = ContactsInfoService.getContactsInfo(this);
	   list.setAdapter(new MyAdapter());
	   //给ListView的条目设置点击事件
	   list.setOnItemClickListener(new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Contact contact =  contacts.get(arg2);
			String number = contact.getNumber();
			//传递数据给调用它的Activity
			Intent intent = new Intent();
			intent.putExtra("number", number);
			setResult(0,intent);
			/**关闭掉当前的Activity  并传递数据给调用者
			   *并且调用者出调用onActivityResult对返回的数据做处理
			*/
			finish();
		}
	   });
   }
   
   
  public class MyAdapter extends BaseAdapter{

	public int getCount() {
		return contacts.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		  Contact contact = contacts.get(position);
         /**
          * 该方法将定义的布局文件.xml转换为类似TextView的View对象
          * */		   
		   View view = View.inflate(SelectContactActivity.this, R.layout.list_item, null);
		   TextView tvname = (TextView)view.findViewById(R.id.name);
		   TextView tvtelnumber = (TextView)view.findViewById(R.id.number);
		   tvname.setText("姓名："+contact.getName());
		   tvtelnumber.setText("电话："+contact.getNumber());
		  return view;
	}
	   
  }
}
