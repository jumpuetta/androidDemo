package com.android.writecontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
    public  void  writeContacts(View view){
    	ContentResolver cr = getContentResolver();
    	Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts");
  	    Uri uri2 = Uri.parse("content://com.android.contacts/data");
        Cursor id_cursor = cr.query(uri1, new String[]{"_id"},null,null,null);
        id_cursor.moveToLast();
        int newid = id_cursor.getInt(0)+1;
        ContentValues values_id = new ContentValues();
        values_id.put("contact_id",newid);
  	    cr.insert(uri1, values_id);
  	    //添加姓名
  	    ContentValues values_name = new ContentValues();
  	    values_name.put("raw_contact_id",newid);
  	    values_name.put("mimetype", "vnd.android.cursor.item/phone_v2");
  	    values_name.put("date1", "15576110293");
  	    cr.insert(uri2, values_name);
  	   //添加号码
  	    ContentValues values_number = new ContentValues();
  	    values_number.put("raw_contact_id",newid);
  	    values_number.put("mimetype", "vnd.android.cursor.item/name");
  	    values_number.put("date1", "zhangsan");
  	    cr.insert(uri2, values_number);
  	   //添加姓名
  	    ContentValues values_email = new ContentValues();
  	    values_email.put("raw_contact_id",newid);
     	values_email.put("mimetype", "vnd.android.cursor.item/email_v2");
  	    values_email.put("date1", "748961193@qq.com");
  	    cr.insert(uri2 , values_email);
  	    
        Toast.makeText(this, "插入成功", Toast.LENGTH_LONG).show();
    }
}
