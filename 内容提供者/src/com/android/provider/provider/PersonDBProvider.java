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
//����һ��ƥ����  ��ƥ�䲻�ɹ�����-1
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
        	/**����Ϊ���һ��������Ϣ����Ϣ��������   �Ա������Ӧ�ã��۲��ߣ�
        	 * ��ȡ��������Ӧ��Ȼ�������Ӧ�ÿ�ͨ����Ӧ�õ������ṩ�߶����ݿ�
        	 * ������Ӧ����
        	 */
        	getContext().getContentResolver().notifyChange(PersonDao.smsuri, null);
        	return db.delete("person", arg1, arg2);
        }else{
        	throw new IllegalArgumentException("·�����Ϸ�");
        }
	}

	@Override
	/**
	 * �����жϷ��ص�����
	 * */ 
	public String getType(Uri uri) {
		 if(matcher.match(uri)==QUERY){
	            return "vnd.android.cursor.dir/person";
	            //��Ҫ�ر����ݿ�  ��ܻ��Լ��ر�
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
        	throw new IllegalArgumentException("·�����Ϸ�");
        }
		return null;
	}

	@Override
	//�����ṩ�߱�����ʽ�Զ�����
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
            //��Ҫ�ر����ݿ�  ��ܻ��Լ��ر�
        }else if(matcher.match(uri)==QUERYONE){
        	long id = ContentUris.parseId(uri);
        	SQLiteDatabase db =  psh.getReadableDatabase();
        	Cursor cursor = db.query("person", projection, "id=?", new String[]{id+""}, null, null, sortOrder);
            return cursor;
        }else{
        	throw new IllegalArgumentException("·�����Ϸ�");
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
    	throw new IllegalArgumentException("·�����Ϸ�");
	}
   }

}
