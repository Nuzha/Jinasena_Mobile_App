package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class SearchToatalAssignedItems extends Activity {
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView logger,sdatetxt,edatetxt,tot,profit;
	String usr;
	Button sdate,edate,next;
	Spinner itms;
	ListView result;
	
	int total=0;
	double bdgt=0.00;
	
	
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
	                    
	          else{
	        	  updateEndDisplay();
	          }
	             
	           }
	     };
	 
		
	            
	      public void updateStartDisplay(){
	            	
	         sdatetxt.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	            	
	       }
	      
	      public void updateEndDisplay(){
          	
		         edatetxt.setText(
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
		setContentView(R.layout.activity_search_toatal_assigned_items);
		
		logger=(TextView)findViewById(R.id.tv_stai_logger_rosh);
		
		Intent intent = getIntent();
		usr = intent.getStringExtra("delkey");
		logger.setText(usr);
		
		sdatetxt=(TextView)findViewById(R.id.tv_sdate_stai_rosh);
		sdate=(Button)findViewById(R.id.btn_sdate_stai_total);
		
		edatetxt=(TextView)findViewById(R.id.tv_edate_stai_rosh);
		edate=(Button)findViewById(R.id.btn_edate_stai_rosh);
		
		itms=(Spinner)findViewById(R.id.sp_itemname_stai_rosh);
		loaditems();
		
		result=(ListView)findViewById(R.id.exlv_stai_items_rosh);
		
		
		sdate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
	    
	    edate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
							
			count=2;
				showDialog(DATE_DIALOG_ID);
		        }
		});
	    
	  //Get the current date 
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        
        
        updateStartDisplay();
		
		updateEndDisplay();
		
		next=(Button)findViewById(R.id.btn_stai_process_rosh);
		
		tot=(TextView)findViewById(R.id.tv_stai_tot_rosh);
		profit=(TextView)findViewById(R.id.tv_stai_itemname);
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StringTokenizer token = new StringTokenizer(sdatetxt.getText().toString(),"/");
				int Year,Month,Date;
				int [] start = {0,1,2};
				int x=0;
				
				while(token.hasMoreTokens()){
					start[x++]=Integer.parseInt(token.nextToken());
				
				}
				
				StringTokenizer token1 = new StringTokenizer(edatetxt.getText().toString(),"/");
				
				int [] end = {0,1,2};
				int y=0;
				
				while(token1.hasMoreTokens()){
					end[y++]=Integer.parseInt(token1.nextToken());
				}
				
				if(start[0]<end[0] || (start[0]==end[0] && start[1]<end[1]) || (start[0]==end[0] && start[1]==end[1] && start[2]<end[2]))
				{
					displayResult();
					
				}
				
				else{
					Dialog d1=new Dialog(SearchToatalAssignedItems.this);
					d1.setTitle("Error");
					TextView t=new TextView(SearchToatalAssignedItems.this);
					t.setText("The end date should be greater than the start date ");
					d1.setContentView(t);
					d1.show();
									
				}
				
							
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_toatal_assigned_items, menu);
		return true;
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
	
	public void loaditems(){
		DBCreater entry = new DBCreater(SearchToatalAssignedItems.this);
		entry.openforread();
		List<String> lbl1 = entry.getAllItemsforReport((String)logger.getText());
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lbl1);
			 
        // attaching data adapter to spinner
        itms.setAdapter(dataAdapter);
        entry.close();
	}
	
	public void displayResult()
	{
		   Cursor cursor=dbHelper.fetchTotalRecords((String)itms.getSelectedItem(), (String)sdatetxt.getText(), (String)edatetxt.getText());
		   
		   if(cursor.getCount()==0)
		   {	
		 	  AlertDialog alertDialog = new AlertDialog.Builder(SearchToatalAssignedItems.this).create();
		 	  alertDialog.setTitle("No Results Found");
		 	  alertDialog.setMessage("This item has not been assigned.");
		 	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		 		  public void onClick(DialogInterface dialog, int which) {
		 		} });
		 	      	  
		 	  alertDialog.show();
		 	  
		 	}
		   
		   
		   String[] columns = new String[] {
		   DBCreater.Key_repS_rep_id,
		   DBCreater.Key_repS_assigned_qty,
	       DBCreater.Key_repS_unit_price,
	       DBCreater.Key_repS_assigned_date
	       
	       
	   };

	    int[] to = new int[]{
	        R.id.tv_st_repid_rosh,
	        R.id.tv_st_qty_rosh,
	        R.id.tv_st_cost_rosh,
	        R.id.tv_st_date_rosh
	        
	   };

   result=(ListView)findViewById(R.id.exlv_stai_items_rosh);
   dataAdapter = new SimpleCursorAdapter(this, R.layout.single_total, cursor, columns, to,0);
   result.setAdapter(dataAdapter);
   
   
   
   if (cursor.moveToFirst()) {
       do {
           total=total+ Integer.parseInt(cursor.getString(2));
          } while (cursor.moveToNext());//to track  the coloum
   }
   String t=Integer.toString(total);
   tot.setText(t);
   
   if (cursor.moveToFirst()) {
       do {
          bdgt=bdgt+ (Integer.parseInt(cursor.getString(2)))*(Double.parseDouble(cursor.getString(3)));
          } while (cursor.moveToNext());//to track  the coloum
   }
   String p=Double.toString(bdgt);
   p=String.format("%.2f", bdgt);
   profit.setText(p);
   }

}

