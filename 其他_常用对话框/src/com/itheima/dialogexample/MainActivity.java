package com.itheima.dialogexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/**
	 * 确定取消对话框
	 * 
	 * @param view
	 */
	public void click1(View view) {
		// 1.创建一个对话框的构造器.
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("java培训标题");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("想打开官方网站么?");
		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://edu.csdn.net"));
				startActivity(intent);
			}
		});
		builder.setNegativeButton("取消", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 默认实现, 点击后 关闭对话框
			}
		});

		// builder.create().show();
		builder.show();

	}

	/**
	 * 单选对话框
	 * 
	 * @param view
	 */
	public void click2(View view) {
		// 1.创建一个对话框的构造器.
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("你想吃什么");
		final String[] items = new String[] { "苹果", "葡萄", "香蕉" };
		builder.setSingleChoiceItems(items, 1, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "您选择了吃" + items[which], 0)
						.show();
				dialog.dismiss();
			}
		});

		// builder.create().show();
		builder.show();

	}

	/**
	 * 多选对话框
	 * 
	 * @param view
	 */
	public void click3(View view) {
		// 1.创建一个对话框的构造器.
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("你想吃什么");
		final String[] items = new String[] { "苹果", "葡萄", "香蕉" };
		final boolean[] status = new boolean[] { true, false, true };
		builder.setMultiChoiceItems(items, status,
				new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						if (isChecked) {
							Toast.makeText(MainActivity.this,
									items[which] + "被选中了", 0).show();

						} else {
							Toast.makeText(MainActivity.this,
									items[which] + "被取消选中了", 0).show();
						}
						status[which] = isChecked;
					}
				});

		builder.setPositiveButton("确定", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// todo:便利数组 查看状态

			}
		});

		// builder.create().show();
		builder.show();

	}

	/**
	 * 进度条对话框
	 * 
	 * @param view
	 */
	public void click4(View view) {
		ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("请稍后");
		pd.setMessage("正在后台处理...");
		pd.show();
		
	}
	/**
	 * 显示进度的进度条对话框
	 * 
	 * @param view
	 */
	public void click5(View view) {
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("请稍后");
		pd.setMessage("正在后台处理...");
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMax(100);
		new Thread(){
			public void run() {
				for(int i = 0;i<100;i++){
					pd.setProgress(i);
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				pd.dismiss();
			};
		}.start();
		pd.show();
	}
}
