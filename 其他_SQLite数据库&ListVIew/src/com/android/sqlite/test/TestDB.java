package com.android.sqlite.test;

import java.util.List;
import java.util.Random;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.android.sqlite.dao.PersonDao;
import com.android.sqlite.dbhelper.PersonSQLiteHelper;
import com.android.sqlite.domain.Person;

public class TestDB extends AndroidTestCase{
   public void testSQLite(){
	   PersonSQLiteHelper psh = new PersonSQLiteHelper(getContext());
	   psh.getWritableDatabase();
   }
   
   public void testAdd(){
	   PersonDao dao = new PersonDao(getContext());
	   long number =88880000L;
	   Random random = new Random();
	   for(int i = 0;i<=50;i++){
	      dao.add("lisi"+i,Long.toString(number+i),random.nextInt(5000));
	   }
   }
   public void testFind(){
	   PersonDao dao = new PersonDao(getContext());
	   boolean result = dao.find("zhangsan");
	   assertEquals(true, result);
   }
   public void testUpdate(){
	   PersonDao dao = new PersonDao(getContext());
	   dao.update("zhangsan","18711331200");
   }
   public void testFindAll(){
	   PersonDao dao = new PersonDao(getContext());
	   List<Person> list = dao.findAll();
	   System.out.println(list.get(0).getName()+list.get(0).getTelnumber());
   }
   public void testDelete(){
	   PersonDao dao = new PersonDao(getContext());
	   dao.delete("zhangsan");
   }
   
   /**
    * 测试事务
    * */
   String s;
      public void testTransaction(){
    	PersonSQLiteHelper psh = new PersonSQLiteHelper(getContext());
    	SQLiteDatabase db = psh.getWritableDatabase();
    	db.beginTransaction();
    	try{
   	    db.execSQL("update person set account=account-1000 where name='zhangsan'");
   	    
   	    s.equals("haha");
   	    db.execSQL("update person set account=account+1000 where name='lisi'");
   	   //标记数据库事务执行成功
   	    db.setTransactionSuccessful();
    	}catch(Exception e){
    		
    	}finally{
    		db.endTransaction();
    		db.close();
    	}
      }
}
