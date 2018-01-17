package com.android.sdsize;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView  tvshow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvshow = (TextView)findViewById(R.id.tvshow);
		String info = getSDSize()+"\n"+getPhoneSize();
		tvshow.setText(info);
		
	}
        public String getSDSize(){
        	File path = Environment.getExternalStorageDirectory();
    		StatFs stat = new StatFs(path.getPath());
    		long blockSize = stat.getBlockSize();
    		long totalBlocks = stat.getBlockCount();
    		long availableBlocks = stat.getAvailableBlocks();
    		long totalSize = blockSize * totalBlocks;
    		long availableSize = blockSize * availableBlocks;
    		String totalStr = Formatter.formatFileSize(this, totalSize);
    		String availableStr = Formatter.formatFileSize(this, availableSize);
    		return "SD卡总内存："+totalStr+"\nSD卡可用空间："+availableStr;
        }
        
        public String getPhoneSize(){
        	File path = Environment.getDataDirectory();
    		StatFs stat = new StatFs(path.getPath());
    		long blockSize = stat.getBlockSize();
    		long totalBlocks = stat.getBlockCount();
    		long availableBlocks = stat.getAvailableBlocks();
    		long totalSize = blockSize * totalBlocks;
    		long availableSize = blockSize * availableBlocks;
    		String totalStr = Formatter.formatFileSize(this, totalSize);
    		String availableStr = Formatter.formatFileSize(this, availableSize);
    		return "手机总内存："+totalStr+"\n手机可用空间："+availableStr;
        }

}
