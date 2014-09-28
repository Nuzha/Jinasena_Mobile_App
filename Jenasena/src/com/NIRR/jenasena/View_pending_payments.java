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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class View_pending_payments extends Activity {
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	TextView logger,sdate,edate;
	String del;
	Spinner rep,status;
	Button sdatedis,edatedis,view;
	ListView results;
	
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
	            	
	         sdate.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	            	
	       }
	      
	      public void updateEndDisplay(){
          	
		         edate.setText(
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
		setContentView(R.layout.activity_view_pending_payments);
		
		logger=(TextView)findViewById(R.id.tv_vp_logger_rosh);
		Intent intent = getIntent();
		del = intent.getStringExtra(DelearMenu.packagename);
		logger.setText(del);
				
		rep=(Spinner)findViewById(R.id.sp_vp_rep_rosh);
		loadRep();
		
		sdate=(TextView)findViewById(R.id.tv_vp_sdate_rosh);
		edate=(TextView)findViewById(R.id.tv_edate_vp_rosh);
		
		sdatedis=(Button)findViewById(R.id.btn_vp_sdate_rosh);
		
		sdatedis.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
	    
		edatedis=(Button)findViewById(R.id.btn_edate_vp_rosh);
		
	    edatedis.setOnClickListener(new View.OnClickListener() {
			
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
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();
	    
	    view=(Button)findViewById(R.id.btn_vp_viewpaymnets_rosh);
	    
	    view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StringTokenizer token = new StringTokenizer(sdate.getText().toString(),"/");
				int Year,Month,Date;
				int [] start = {0,1,2};
				int x=0;
				
				while(token.hasMoreTokens()){
					start[x++]=Integer.parseInt(token.nextToken());
				
				}
				
				StringTokenizer token1 = new StringTokenizer(edate.getText().toString(),"/");
				
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
					Dialog d1=new Dialog(View_pending_payments.this);
					d1.setTitle("Error");
					TextView t=new TextView(View_pending_payments.this);
					t.setText("The end date should be greater than the start date ");
					d1.setContentView(t);
					d1.show();
									
				}
				
							
			}
		});
		
	}

	public void loadRep(){
			DBCreater entry = new DBCreater(View_pending_payments.this);
			entry.openforread();
			List<String> lables2 = entry.getAllLabelsRep((String)logger.getText());
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables2);
				 
	        // attaching data adapter to spinner
	        rep.setAdapter(dataAdapter);
	        entry.close();
		
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
	
	public void displayResult()
	{
			String ch="Pending";
		   Cursor cursor=dbHelper.fetchPendingPayments(rep.getSelectedItem().toString(), sdate.getText().toString(), toString());
		   
		   if(cursor.getCount()==0)
		   {	
		 	  AlertDialog alertDialog = new AlertDialog.Builder(View_pending_payments.this).create();
		 	  alertDialog.setTitle("No Results Found");
		 	  alertDialog.setMessage("Total amount has been paid");
		 	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
		 		  public void onClick(DialogInterface dialog, int which) {
		 		} });
		 	      	  
		 	  alertDialog.show();
		 	  
		 	}
		   
		   
		   String[] columns = new String[] {
		   DBCreater.Key_delS_payment_assign_date,
	       DBCreater.Key_delS_payment_total_cost,
	       DBCreater.Key_delS_payment_status	
	       
	       
	   };

	    int[] to = new int[]{
	        R.id.tv_pay_assigned_date,
	        R.id.tv_pay_tot,
	        R.id.tv_target_pay
	        
	   };

   results=(ListView)findViewById(R.id.lv_vp_result_rosh);
   dataAdapter = new SimpleCursorAdapter(this, R.layout.single_payment, cursor, columns, to,0);
   results.setAdapter(dataAdapter);
   
   
   results.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view,int position, long id) {
			Cursor cursor =(Cursor)results.getItemAtPosition(position);
			String assigndate=cursor.getString(cursor.getColumnIndexOrThrow("delS_payment_assign_date"));
			String targetdate=cursor.getString(cursor.getColumnIndexOrThrow("delS_payment_status"));
			String cost=cursor.getString(cursor.getColumnIndexOrThrow("delS_payment_total_cost"));
			
			String sid=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
			
			switch(position){
			   		
			  default:
			  Intent p2 = new Intent(View_pending_payments.this,Totamount.class);
			  p2.putExtra("key1", logger.getText().toString());
			  p2.putExtra("delS_payment_rep_id", rep.getSelectedItem().toString());
			  p2.putExtra("key4", assigndate);
			  p2.putExtra("key5", targetdate);
			  p2.putExtra("key6", cost);
			  p2.putExtra("id", id);
			  startActivity(p2);
			  break;
			  }
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_pending_payments, menu);
		return true;
	}

}
