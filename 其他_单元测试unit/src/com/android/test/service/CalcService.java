package com.android.test.service;

import android.util.Log;

public class CalcService {
  private String tag = "CalcService";
  public  int  add(int x,int y){
	  Log.v(tag, "x="+x);
	  Log.d(tag, "y="+y);
	  int result = x+y;
	  Log.i(tag, "result="+result);
	  Log.w(tag, "result="+result);
	  Log.e(tag, "result="+result);
	  System.out.println("result="+result);
	  System.err.println("result="+result);
	  return x+y;
  }
  
  
  public static void main(String[] args) {
	System.out.println("能答应出来吗");
}
}
