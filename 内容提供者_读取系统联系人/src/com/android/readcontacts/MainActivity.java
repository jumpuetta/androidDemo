package com.android.readcontacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
   public  void  readContacts(View view){
	   ContentResolver cr =  getContentResolver();
	   Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts");
	   Uri uri2 = Uri.parse("content://com.android.contacts/data");
	   Cursor raw_cursor = cr.query(uri1, new String[]{"contact_id"}, null, null, null);
	   while(raw_cursor.moveToNext()){
		   if(raw_cursor.getString(0)!=null){
		   Cursor data_cursor = cr.query(uri2, null,"raw_contact_id=?",new String[]{raw_cursor.getString(0)},null);
		   while(data_cursor.moveToNext()){
			   System.out.println(data_cursor.getString(data_cursor.getColumnIndex("data1")));
			   System.out.println(data_cursor.getString(data_cursor.getColumnIndex("mimetype")));
		   }
		   data_cursor.close();
		  }
	   }
	   raw_cursor.close();
	   
   }
}
