package com.android.providertest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
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
  
    public void getData(View view ){
    	/**
    	 * 得到手机里面应用的中间人,可关联代理手机内任何应用的内容提供者
    	 * */
    	ContentResolver resolver = getContentResolver();
    	//uri默认标准content://地址/路径
    	Uri uri = Uri.parse("content://com.android.sqlite.provider/query");
    	Cursor cursor = resolver.query(uri, null, null, null, null);
    	while(cursor.moveToNext()){
    		System.out.println(cursor.getString(1));
    		System.out.println(cursor.getString(2));
    	}
    	cursor.close();
    }
    
    public void getOneData(View view ){
    	/**
    	 * 得到手机里面应用的中间人,可关联代理手机内任何应用的内容提供者
    	 * */
    	ContentResolver resolver = getContentResolver();
    	//uri默认标准content://地址/路径
    	Uri uri = Uri.parse("content://com.android.sqlite.provider/query/5");
    	Cursor cursor = resolver.query(uri, null, null, null, null);
    	while(cursor.moveToNext()){
    		System.out.println(cursor.getString(1));
    		System.out.println(cursor.getString(2));
    	}
    	cursor.close();
    }
    
    public void insertData(View view ){
    	ContentResolver resolver = getContentResolver();
    	Uri uri = Uri.parse("content://com.android.sqlite.provider/insert");
    	ContentValues cv = new ContentValues();
    	cv.put("name","jiangpeng");
    	cv.put("telnumber","18711331232");
    	cv.put("account","100000000");
    	resolver.insert(uri, cv);
    }
    public void updateData(View view ){
    	ContentResolver resolver = getContentResolver();
    	Uri uri = Uri.parse("content://com.android.sqlite.provider/update");
    	ContentValues cv = new ContentValues();
    	cv.put("account","12345");
    	resolver.update(uri, cv , "name=?", new String[]{"lisi0"});
    }
    public void deleteData(View view ){
    	ContentResolver resolver = getContentResolver();
    	Uri uri = Uri.parse("content://com.android.sqlite.provider/delete");
    	resolver.delete(uri,"name=?", new String[]{"lisi1"});

    }
}
