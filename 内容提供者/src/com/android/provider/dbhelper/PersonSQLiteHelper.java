package com.android.provider.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteHelper extends SQLiteOpenHelper{
  /**
   * ���ݿ⹹�췽��   �����������ݿ������ ���ݿ��ѯ�Ľ����  ���ݿ�İ汾
   * */
	public PersonSQLiteHelper(Context context) {
		super(context,"person.db", null, 3);
		 //������������null����ϵͳĬ�ϵ��α깤��
		// TODO Auto-generated constructor stub
	}
   /**
    * ���ݿ��һ�δ���ʱ������
    * SQLiteDatabase db ���������ݿ�
    * */
    public void onCreate(SQLiteDatabase db) {
    	//��ʼ����ṹ
    	db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),telnumber varchar(20),account varchar(10))");
    }
    
    /**
     * �����ݿ�汾�����仯ʱ�Զ�����
     * */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	// TODO Auto-generated method stub
    	Log.i("PersonSQLiteHelper","���ݿ�汾�仯��");
    	db.execSQL("alter table person add account varchar(20)");
    }
}
