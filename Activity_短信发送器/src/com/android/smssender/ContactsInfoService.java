package com.android.smssender;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ContactsInfoService {
  public static List<Contact> getContactsInfo(Context context){
	  ContentResolver cr =  context.getContentResolver();
	   Uri uri1 = Uri.parse("content://com.android.contacts/raw_contacts");
	   Uri uri2 = Uri.parse("content://com.android.contacts/data");
	   Cursor raw_cursor = cr.query(uri1, new String[]{"contact_id"}, null, null, null);
	   List<Contact> list = new ArrayList<Contact>();
	   while(raw_cursor.moveToNext()){
		   if(raw_cursor.getString(0)!=null){
		   Contact contact= new Contact(); 
		   Cursor data_cursor = cr.query(uri2, null,"raw_contact_id=?",new String[]{raw_cursor.getString(0)},null);
		   while(data_cursor.moveToNext()){
			   if(data_cursor.getString(data_cursor.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/name")){
				   contact.setName(data_cursor.getString(data_cursor.getColumnIndex("data1")));
			   }else if(data_cursor.getString(data_cursor.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/phone_v2")){
				   contact.setNumber(data_cursor.getString(data_cursor.getColumnIndex("data1")));
			   }else if(data_cursor.getString(data_cursor.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/email_v2")){
				   contact.setEmail(data_cursor.getString(data_cursor.getColumnIndex("data1")));
			   }
		   }
		   list.add(contact);
		   data_cursor.close();
		  }
	   }
	   raw_cursor.close();
	   return list;
  }
}
