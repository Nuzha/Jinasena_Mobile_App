package com.NIRR.jenasena;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AvailableProducts extends Activity {
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView logger,date;
	String del;
	Button today,getresult;
	ListView displayitms;
	
	      
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_available_products);
		
		logger=(TextView)findViewById(R.id.tv_ap_logger_rosh);
		Intent intent=getIntent();
		del=intent.getStringExtra("delkey");
		logger.setText(del);
		
		date=(TextView)findViewById(R.id.tv_ap_date_rosh);
		today=(Button)findViewById(R.id.btn_ap_date_rosh);
		today.setVisibility(View.INVISIBLE);
		date.setVisibility(View.INVISIBLE);
		
		getresult=(Button)findViewById(R.id.btn_ap_chkavail_rosh);
		
        dbHelper = new DBCreater(this);
	    dbHelper.open();
	    
	    loadavailableitems();
        
	    /*getresult.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				loadavailableitems();
				
			}
		});*/
        
        
		
	}
	
	public void loadavailableitems(){
		Cursor cursor=dbHelper.fetchavailableqty(logger.getText().toString());
		   
		   if(cursor.getCount()==0)
		   {	
		 	  AlertDialog alertDialog = new AlertDialog.Builder(AvailableProducts.this).create();
		 	  alertDialog.setTitle("No Results Found");
		 	  alertDialog.setMessage("No more items available.");
		 	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		 		  public void onClick(DialogInterface dialog, int which) {
		 		} });
		 	      	  
		 	  alertDialog.show();
		 	  
		 	}
		   
		   
		   String[] columns = new String[] {
		   DBCreater.Key_delS_del_item_name,
	       DBCreater.Key_delS_available_qty
	       	       
	       
	   };

	    int[] to = new int[]{
	        R.id.tv_sa_itemname_rosh,
	        R.id.tv_sa_qty_rosh
	        	        
	        
	   };

		displayitms=(ListView)findViewById(R.id.lv_ap_avail_rosh);
		dataAdapter = new SimpleCursorAdapter(this, R.layout.single_avail, cursor, columns, to,0);
		displayitms.setAdapter(dataAdapter);
		
		displayitms.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,int position, long id) {
				Cursor cursor =(Cursor)displayitms.getItemAtPosition(position);
				String itmname=cursor.getString(cursor.getColumnIndexOrThrow("delS_del_item_name"));
				String qty=cursor.getString(cursor.getColumnIndexOrThrow("delS_available_qty"));
													           
				switch(position){
				   		
				  default:
				  AlertDialog alertdlg=new AlertDialog.Builder(AvailableProducts.this).create();
				  //alertdlg.setTitle("");
				  alertdlg.setMessage("You have "+qty+" more "+itmname+" in your stock to assign.");
				  alertdlg.setButton("OK", new DialogInterface.OnClickListener() {
							
				  @Override
				  public void onClick(DialogInterface dialog, int which) {
				  }
				  });
				  alertdlg.show();
				  }
				}

			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.available_products, menu);
		return true;
	}

}
