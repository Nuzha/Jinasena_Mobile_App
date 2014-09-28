package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewMonthlyHistory extends Activity {
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView displayStart;
	TextView displayEnd;
	TextView rep;
	Button view;

	String d1,d2,d3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_monthly_history);
		
		view=(Button)findViewById(R.id.btn_mai_view_rosh);
		
		displayStart=(TextView)findViewById(R.id.tv_mai_viewdate1_rosh);
		displayEnd=(TextView)findViewById(R.id.tv_mai_viewdate2_rosh);
		rep=(TextView)findViewById(R.id.tv_mai_repview_rosh);
		
		Intent intent = getIntent();
		d1 = intent.getStringExtra("key");
		displayStart.setText(d1);
		
		Intent intent1 = getIntent();
		d2 = intent1.getStringExtra("key2");
		displayEnd.setText(d2);
		
		Intent intent2 = getIntent();
		d3 = intent2.getStringExtra("key3");
		rep.setText(d3);
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();
		
		//displayList();
	    
	    view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		
				displayList();
			}
		});
	}
	
	
	private void displayList(){

	    Cursor cursor = dbHelper.fetchmonthlyrecords((String)rep.getText(),(String)displayStart.getText(),(String)displayEnd.getText());
	    String[] columns = new String[] {
	       DBCreater.Key_repS_item_name,
	       DBCreater.Key_repS_assigned_qty,
	       DBCreater.Key_repS_unit_price,
	       DBCreater.Key_repS_assigned_date,
	       DBCreater.Key_repS_available_qty
	   };

	    int[] to = new int[]{
	        R.id.tv_sh_name_rosh,
	        R.id.tv_sh_assqty_rosh,
	        R.id.tv_sh_cost_rosh,
	        R.id.tv_sh_date_rosh,
	        R.id.tv_sh_availqty_rosh
	   };

	  final ListView lv = (ListView)findViewById(R.id.lv_mai_result_rosh);
      dataAdapter = new SimpleCursorAdapter(this, R.layout.single_history, cursor, columns, to,0);
      lv.setAdapter(dataAdapter);
      
      if(cursor.getCount()==0)
      {	
    	  AlertDialog alertDialog = new AlertDialog.Builder(ViewMonthlyHistory.this).create();
    	  alertDialog.setTitle("No Results Found");
    	  alertDialog.setMessage("You haven't assigned items to the selected rep during the given period.");
    	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    		  public void onClick(DialogInterface dialog, int which) {
    		} });
    	      	  
    	  alertDialog.show();
    	  //alertDialog.getWindow().setLayout(250, 250);
    	 /* 
    	  Dialog d = new Dialog(this);
  		d.setTitle("Succesfull");
  		TextView tv = new TextView(this);
  		tv.setText(" You logging as ");
  		d.setContentView(tv);
  		d.show();*/
    	}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_monthly_history, menu);
		return true;
	}

}
