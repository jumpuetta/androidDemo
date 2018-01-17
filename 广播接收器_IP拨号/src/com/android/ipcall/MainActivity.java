package com.android.ipcall;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText et_number;
    private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_number = (EditText)findViewById(R.id.et_number);
		sp = getSharedPreferences("ipnumber",MODE_PRIVATE);
		String ipnumber = sp.getString("ipnumber","");
		et_number.setText(ipnumber);
	}

   public void click(View view){
	   String number = et_number.getText().toString().trim();
	   Editor editor = sp.edit();
	   editor.putString("ipnumber",number);
	   editor.commit();
	   Toast.makeText(this,"…Ë÷√ÕÍ≥…", Toast.LENGTH_SHORT).show();
   }
}
