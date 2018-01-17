package com.android.test.junit;

import android.test.AndroidTestCase;

import com.android.test.service.CalcService;

public class TestCalcService extends AndroidTestCase{
    public  void   testAdd(){
    	CalcService cs = new CalcService();
    	int result = cs.add(3, 8);
    	assertEquals(11,result);
    	
    }
}
