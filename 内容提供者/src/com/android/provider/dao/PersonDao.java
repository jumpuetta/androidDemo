package com.android.provider.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.android.provider.dbhelper.PersonSQLiteHelper;
import com.android.provider.domain.Person;


public class PersonDao {
  private PersonSQLiteHelper psh;
  private Context context;
  public static final Uri smsuri = Uri.parse("content://notify"); 
  public PersonDao(Context context){
	  psh = new PersonSQLiteHelper(context);
	  this.context=context;
  }
  
  public long add(String name,String number,int account){
	  SQLiteDatabase db = psh.getWritableDatabase();
	  //db.execSQL("insert into person(name,telnumber) values(?,?)",new Object[]{name,number});
	  ContentValues values = new ContentValues();
	  values.put("name",name);
	  values.put("telnumber",number);
	  values.put("account",account);
	  long result = db.insert("person",null, values);
	  db.close();
	  context.getContentResolver().notifyChange(smsuri, null);
	  return result;
	  
  }
  
  public boolean find(String name){
	  SQLiteDatabase db = psh.getReadableDatabase();
//	  Cursor cursor = db.rawQuery("select * from person where name=?",new String[]{name});
	  Cursor cursor = db.query("person",null,"name=?",new String[]{name}, null, null, null);
	  boolean result = cursor.moveToNext();
	  db.close();
	  return result;
  }
  
  public List<Person> findAll(){
	  SQLiteDatabase db = psh.getReadableDatabase();
	  //Cursor cursor = db.rawQuery("select id,name,telnumber from person",null);
	  Cursor cursor = db.query("person",new String[]{"name","telnumber","account"}, null, null,null,null,null);
	  List<Person> list = new ArrayList<Person>();
	  while(cursor.moveToNext()){
		  Person p =new Person();
		  p.setName(cursor.getString(0));
		  p.setTelnumber(cursor.getString(1));
		  p.setAccount(cursor.getString(2));
		  list.add(p);
	  }
	  return list;
  }
  
  public int  update(String name,String newnumber){
	  SQLiteDatabase db = psh.getWritableDatabase();
	  //db.execSQL("update person set telnumber=? where name=?",new String[]{newnumber,name});
      ContentValues values = new ContentValues();
      values.put("number",newnumber);
      int number = db.update("person", values,"name=?",new String[]{name});
	  db.close();
	  context.getContentResolver().notifyChange(smsuri, null);
	  return number;
  }
  
  public void delete(String name){
	  SQLiteDatabase db = psh.getWritableDatabase();
	  //db.execSQL("delete from person where name=?",new String[]{name});
      db.delete("person","name=?",new String[]{name});
      context.getContentResolver().notifyChange(smsuri, null);
	  db.close();
  }
  
}
