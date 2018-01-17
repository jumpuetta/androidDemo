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
	 * ȷ��ȡ���Ի���
	 * 
	 * @param view
	 */
	public void click1(View view) {
		// 1.����һ���Ի���Ĺ�����.
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("java��ѵ����");
		builder.setIcon(R.drawable.ic_launcher);
		builder.setMessage("��򿪹ٷ���վô?");
		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://edu.csdn.net"));
				startActivity(intent);
			}
		});
		builder.setNegativeButton("ȡ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Ĭ��ʵ��, ����� �رնԻ���
			}
		});

		// builder.create().show();
		builder.show();

	}

	/**
	 * ��ѡ�Ի���
	 * 
	 * @param view
	 */
	public void click2(View view) {
		// 1.����һ���Ի���Ĺ�����.
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("�����ʲô");
		final String[] items = new String[] { "ƻ��", "����", "�㽶" };
		builder.setSingleChoiceItems(items, 1, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this, "��ѡ���˳�" + items[which], 0)
						.show();
				dialog.dismiss();
			}
		});

		// builder.create().show();
		builder.show();

	}

	/**
	 * ��ѡ�Ի���
	 * 
	 * @param view
	 */
	public void click3(View view) {
		// 1.����һ���Ի���Ĺ�����.
		AlertDialog.Builder builder = new Builder(this);
		builder.setTitle("�����ʲô");
		final String[] items = new String[] { "ƻ��", "����", "�㽶" };
		final boolean[] status = new boolean[] { true, false, true };
		builder.setMultiChoiceItems(items, status,
				new OnMultiChoiceClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which,
							boolean isChecked) {
						if (isChecked) {
							Toast.makeText(MainActivity.this,
									items[which] + "��ѡ����", 0).show();

						} else {
							Toast.makeText(MainActivity.this,
									items[which] + "��ȡ��ѡ����", 0).show();
						}
						status[which] = isChecked;
					}
				});

		builder.setPositiveButton("ȷ��", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// todo:�������� �鿴״̬

			}
		});

		// builder.create().show();
		builder.show();

	}

	/**
	 * �������Ի���
	 * 
	 * @param view
	 */
	public void click4(View view) {
		ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("���Ժ�");
		pd.setMessage("���ں�̨����...");
		pd.show();
		
	}
	/**
	 * ��ʾ���ȵĽ������Ի���
	 * 
	 * @param view
	 */
	public void click5(View view) {
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("���Ժ�");
		pd.setMessage("���ں�̨����...");
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
