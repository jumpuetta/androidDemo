package com.android.provider.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.android.provider.dao.PersonDao;
import com.android.provider.dbhelper.PersonSQLiteHelper;

public class PersonDBProvider extends ContentProvider {
//定义一个匹配器  若匹配不成功返回-1
	private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
	private static final int INSERT = 1;
	private static final int QUERY = 2;
	private static final int UPDATE = 3;
	private static final int DELETE = 4;
	private static final int QUERYONE = 5;
	private PersonSQLiteHelper psh ;
	static{
		matcher.addURI("com.android.sqlite.provider", "insert", INSERT);
		matcher.addURI("com.android.sqlite.provider", "query", QUERY);
		matcher.addURI("com.android.sqlite.provider", "update", UPDATE);
		matcher.addURI("com.android.sqlite.provider", "delete", DELETE);
		matcher.addURI("com.android.sqlite.provider", "query/#",QUERYONE);
	}
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		if(matcher.match(arg0)==DELETE){
        	SQLiteDatabase db =  psh.getWritableDatabase();
        	/**作用为大叫一声发布消息到消息邮箱里面   以便第三方应用（观察者）
        	 * 获取并作出响应，然后第三方应用可通过本应用的数据提供者对数据库
        	 * 做出相应操作
        	 */
        	getContext().getContentResolver().notifyChange(PersonDao.smsuri, null);
        	return db.delete("person", arg1, arg2);
        }else{
        	throw new IllegalArgumentException("路径不合法");
        }
	}

	@Override
	/**
	 * 用于判断返回的数据
	 * */ 
	public String getType(Uri uri) {
		 if(matcher.match(uri)==QUERY){
	            return "vnd.android.cursor.dir/person";
	            //不要关闭数据库  框架会自己关闭
	        }else if(matcher.match(uri)==QUERYONE){
	            return "vnd.android.cursor.item/person";
	        }
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(matcher.match(uri)==INSERT){
        	SQLiteDatabase db =  psh.getWritableDatabase();
        	db.insert("person",null, values);
        	getContext().getContentResolver().notifyChange(PersonDao.smsuri, null);
        }else{
        	throw new IllegalArgumentException("路径不合法");
        }
		return null;
	}

	@Override
	//内容提供者被开启式自动调用
	public boolean onCreate() {
		psh = new PersonSQLiteHelper(getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
        if(matcher.match(uri)==QUERY){
        	SQLiteDatabase db =  psh.getReadableDatabase();
        	Cursor cursor = db.query("person", projection, selection, selectionArgs, null, null, sortOrder);
            return cursor;
            //不要关闭数据库  框架会自己关闭
        }else if(matcher.match(uri)==QUERYONE){
        	long id = ContentUris.parseId(uri);
        	SQLiteDatabase db =  psh.getReadableDatabase();
        	Cursor cursor = db.query("person", projection, "id=?", new String[]{id+""}, null, null, sortOrder);
            return cursor;
        }else{
        	throw new IllegalArgumentException("路径不合法");
        }
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		if(matcher.match(uri)==UPDATE){
    	  SQLiteDatabase db =  psh.getWritableDatabase();
    	  getContext().getContentResolver().notifyChange(PersonDao.smsuri, null);
    	  return  db.update("person", values, selection, selectionArgs);
        }else{
    	throw new IllegalArgumentException("路径不合法");
	}
   }

}
