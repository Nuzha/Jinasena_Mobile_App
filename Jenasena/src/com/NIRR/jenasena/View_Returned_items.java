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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class View_Returned_items extends Activity {
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView del,date,temp;
	String sdel,ch,stemp="",select="";
	Button bdate,result,mail;
	ListView returns;
	
	private int mYear;
	private int mMonth;
	private int mDay;
		    
	static final int DATE_DIALOG_ID = 0;
	int count=0;
	
		
	   //call back listner for the date picker dialog
		private DatePickerDialog.OnDateSetListener pDateSetListener =new DatePickerDialog.OnDateSetListener() {
	 
	    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	          mYear = year;
	          mMonth = monthOfYear;
	          mDay = dayOfMonth;
	                    
	          if(count==1){
	             updateStartDisplay();
	          }
	                               
	         }
	     };
	 
		
	            
	      public void updateStartDisplay(){
	            	
	         date.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	           	
	       }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__returned_items);
		
		ch=null;
		
		del=(TextView)findViewById(R.id.tv_vri_logger_rosh);
		Intent intent = getIntent();
		sdel = intent.getStringExtra(DelearMenu.packagename);
		del.setText(sdel);
		
		date=(TextView)findViewById(R.id.tv_vri_datetxt_rosh);
		bdate=(Button)findViewById(R.id.btn_vri_date_rosh);
		
		
		bdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		
		//Get the current date 
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        
        updateStartDisplay();
        //displayresults();
        
        dbHelper = new DBCreater(this);
	    dbHelper.open();
	    
	    result=(Button)findViewById(R.id.btn_vri_return_rosh);
	    result.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				displayresults();
				
				
			}
		});
	    
	    mail=(Button)findViewById(R.id.btn_vri_next_rosh);
	    mail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(stemp.equals("")){
					Toast.makeText(getApplicationContext(), "First select the records to send the email", Toast.LENGTH_SHORT).show();
				}
				
				else{
				Intent intent=new Intent(View_Returned_items.this,ReturnItemsEmail.class);
				intent.putExtra("user", del.getText().toString());
				intent.putExtra("content", temp.getText().toString());
				startActivity(intent);
				}
				
			}
		});
	    
	    
	    
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this, 
                        pDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
	}

	public void displayresults(){
		Cursor cursor=dbHelper.fetchreturns(del.getText().toString(), date.getText().toString());
		   
		   if(cursor.getCount()==0)
		   {	
		 	  AlertDialog alertDialog = new AlertDialog.Builder(View_Returned_items.this).create();
		 	  //alertDialog.setTitle("No Results Found");
		 	  alertDialog.setMessage("No Returned Items Found.");
		 	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		 		  public void onClick(DialogInterface dialog, int which) {
		 		} });
		 	      	  
		 	  alertDialog.show();
		 	  
		 	}
		   
		   
		   String[] columns = new String[] {
		   DBCreater.Key_returned_items_rep_id,
	       DBCreater.Key_returned_items_item_name,
	       DBCreater.Key_returned_items_quantity,
	       DBCreater.Key_returned_items_issue
	       
	       
	   };

	    int[] to = new int[]{
	        R.id.tv_sr_repid_rosh,
	        R.id.tv_sr_itmname,
	        R.id.tv_sr_qty_rosh,
	        R.id.tv_sr_issue_rosh
	        
	   };

	    returns=(ListView)findViewById(R.id.lv_vri_rlist_rosh);
		dataAdapter = new SimpleCursorAdapter(this, R.layout.single_return, cursor, columns, to,0);
		returns.setAdapter(dataAdapter);
		
		returns.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,int position, long id) {
				Cursor cursor =(Cursor)returns.getItemAtPosition(position);
				String itmname=cursor.getString(cursor.getColumnIndexOrThrow("returned_items_items_name"));
				String qty=cursor.getString(cursor.getColumnIndexOrThrow("returned_items_quantity"));
				String issue=cursor.getString(cursor.getColumnIndexOrThrow("returned_items_issue"));
				String rep=cursor.getString(cursor.getColumnIndexOrThrow("returned_items_rep_id"));
				
				String did=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
				Toast.makeText(getApplicationContext(),"To notify the company", Toast.LENGTH_SHORT).show();
							
				temp=(TextView)findViewById(R.id.tv_vri_load_rosh);
				
				if (cursor.moveToPosition(position)) {
				    stemp=stemp+(itmname+" "+qty+" items were returned back by "+rep+" (Issue: "+issue+")"+"\n");
				   }
				   
				temp.setText(stemp);
				temp.setVisibility(View.INVISIBLE);
				
			}

			});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__returned_items, menu);
		return true;
	}

}
