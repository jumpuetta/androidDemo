package com.android.provider.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteHelper extends SQLiteOpenHelper{
  /**
   * 数据库构造方法   用来定义数据库的名称 数据库查询的结果集  数据库的版本
   * */
	public PersonSQLiteHelper(Context context) {
		super(context,"person.db", null, 3);
		 //第三个参数用null调用系统默认的游标工厂
		// TODO Auto-generated constructor stub
	}
   /**
    * 数据库第一次创建时被调用
    * SQLiteDatabase db 创建的数据库
    * */
    public void onCreate(SQLiteDatabase db) {
    	//初始化表结构
    	db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),telnumber varchar(20),account varchar(10))");
    }
    
    /**
     * 当数据库版本发生变化时自动调用
     * */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	// TODO Auto-generated method stub
    	Log.i("PersonSQLiteHelper","数据库版本变化了");
    	db.execSQL("alter table person add account varchar(20)");
    }
}
