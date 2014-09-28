package com.NIRR.jenasena;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddRep extends Activity{

	Button add;
	Integer testresults;
	String label="";
	protected Cursor cursor;
	protected SQLiteDatabase db;
	EditText name,phone,address,email;
	String scustemername,sphone,saddress,semail,sdel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addrep);
	}
	
	
	

}
