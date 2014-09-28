package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.StringTokenizer;


import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Reporting extends Activity implements OnClickListener{
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	private TextView mStartDate;
	private Button mPickStartDate;
	
	private TextView mEndDate;
	private Button mPickEndDate;
	
	private Button process;
	
	private TextView user;
	String usr;
	
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
	            	
	         mStartDate.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	            	
	       }
	      
	      public void updateEndDisplay(){
          	
		         mEndDate.setText(
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
		setContentView(R.layout.activity_reporting);
		
		user=(TextView)findViewById(R.id.tv_history_viewlogger_rosh);
		Intent intent = getIntent();
		usr = intent.getStringExtra(DelearMenu.packagename);
		user.setText(usr);
		
		mStartDate = (TextView) findViewById(R.id.tv_rh_datedisplay_rosh);        
	    mPickStartDate = (Button) findViewById(R.id.btn_rephistry_date1_rosh);
	    
	    mEndDate = (TextView) findViewById(R.id.tv_rh_date2display_rosh);        
	    mPickEndDate = (Button) findViewById(R.id.btn_rh_date2_rosh);
	    
	    final RadioButton rep=(RadioButton)findViewById(R.id.radiobtn_rep_r);
	    final RadioButton itm=(RadioButton)findViewById(R.id.radiobtn_item_r);
	    
	    process=(Button)findViewById(R.id.btn_next_rosh);
	    
    
	    mPickStartDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
	    
	    mPickEndDate.setOnClickListener(new View.OnClickListener() {
			
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
		
		
		process.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				StringTokenizer token = new StringTokenizer(mStartDate.getText().toString(),"/");
				int Year,Month,Date;
				int [] Res = {1,2,3};
				int x=0;
				
				while(token.hasMoreTokens()){
					Res[x++]=Integer.parseInt(token.nextToken());
				
				}
				
				StringTokenizer token1 = new StringTokenizer(mEndDate.getText().toString(),"/");
				
				int [] ReqDate = {1,2,3};
				int y=0;
				
				while(token1.hasMoreTokens()){
					ReqDate[y++]=Integer.parseInt(token1.nextToken());
				}
				
				if(Res[0]<ReqDate[0] || (Res[0]==ReqDate[0] && Res[1]<ReqDate[1]) || (Res[0]==ReqDate[0] && Res[1]==ReqDate[1] && Res[2]<ReqDate[2]))
				{
			
					if(rep.isChecked()){
						Intent intent = new Intent(Reporting.this,ViewRepWiseHistory.class);
					    intent.putExtra("key", mStartDate.getText().toString());
					    intent.putExtra("key2", mEndDate.getText().toString());
					    intent.putExtra("delkey", user.getText().toString());
					    startActivity(intent);
				        
					}else if(itm.isChecked()){
					    Intent intent = new Intent(Reporting.this,AssignedItemHistory.class);
					    intent.putExtra("key", mStartDate.getText().toString());
					    intent.putExtra("key2", mEndDate.getText().toString());
					    intent.putExtra("delkey", user.getText().toString());
					    startActivity(intent);
					}else{
					    Toast.makeText(getApplicationContext(), "Select the radio button..",Toast.LENGTH_LONG).show();
					}
		        }
				
				else{
					Dialog d1=new Dialog(Reporting.this);
					d1.setTitle("Error");
					TextView t=new TextView(Reporting.this);
					t.setText("The end date should be greater the start date ");
					d1.setContentView(t);
					d1.show();
									
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reporting, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
