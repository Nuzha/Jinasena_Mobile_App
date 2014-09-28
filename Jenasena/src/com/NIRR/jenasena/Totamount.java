package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.List;


import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Totamount extends Activity {
	
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView logger,repid,assdate,targetdate,totcost,today;
	String usr,rep,date,tdate,cost;
	EditText receivdamt;
	Button displaydate,update,save;
	
	
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
	            	
	         today.setText(
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
		setContentView(R.layout.activity_totamount);
		
		logger=(TextView)findViewById(R.id.tv_tm_logger_rosh);
		Intent intent = getIntent();
		usr = intent.getStringExtra("key1");
		logger.setText(usr);
		
		repid=(TextView)findViewById(R.id.tv_tm_repid_rosh);
		Intent intent1 = getIntent();
		rep = intent1.getStringExtra("delS_payment_rep_id");
		repid.setText(rep);
		
		assdate=(TextView)findViewById(R.id.tv_tm_assdate_rosh);
		Intent intent2 = getIntent();
		date = intent2.getStringExtra("key4");
		assdate.setText(date);
		
		targetdate=(TextView)findViewById(R.id.tv_tm_target_rosh);
		
		DBCreater entry=new DBCreater(Totamount.this);
		entry.openforread();
		List<String> lbl1=entry.getTargetdate(repid.getText().toString(), assdate.getText().toString());
		String target=lbl1.get(0).toString();
		targetdate.setText(target);
		
		
		totcost=(TextView)findViewById(R.id.tv_tm_targetamt_rosh);
		Intent intent4 = getIntent();
		cost = intent4.getStringExtra("key6");
		totcost.setText(cost);
		
		receivdamt=(EditText)findViewById(R.id.edt_tm_received_rosh);
		//remainingqty=(EditText)findViewById(R.id.edt_tm_tobepaid_rosh);
		
		
		
		today=(TextView)findViewById(R.id.tv_received_date_tm_rosh);
		displaydate=(Button)findViewById(R.id.btn_received_date_tm_rosh);
		displaydate.setBackgroundColor(Color.TRANSPARENT);
		
		displaydate.setOnClickListener(new View.OnClickListener() {
			
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
        
        
        update=(Button)findViewById(R.id.btn_tm_update_rosh);
        update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				DBCreater insert=new DBCreater(Totamount.this);
				
					String totalcost=totcost.getText().toString();
					double total=Double.valueOf(totalcost);
				
					String receiveamt=receivdamt.getText().toString();
					if(receiveamt.equals("")){
						Toast.makeText(getApplicationContext(), "Please enter the paid amount to update.", Toast.LENGTH_SHORT).show();
					}
					
					else{
					double receivedcost=Double.valueOf(receiveamt);
					
					if(total==receivedcost)
					{
				   insert.open();
				   ContentValues cv = new ContentValues();
				   cv.put(DBCreater.Key_delS_payment_received_amount,receivedcost);
				   //cv.put(DBCreater.Key_delS_payment_remaining_amount,(total-receivedcost));
				   cv.put(DBCreater.Key_delS_payment_received_final_date,today.getText().toString());
				   cv.put(DBCreater.Key_delS_payment_status,"Received total amount");
				   insert.updateDelear("Dealer_payments", cv, "delS_payment_rep_id=? and delS_payment_assign_date=? ",new String[] {repid.getText().toString(),assdate.getText().toString()} );
				   Toast.makeText(getApplicationContext(),"Successfully Updated ", Toast.LENGTH_SHORT).show();
				   insert.close();
					}
					
					
					
					else
					{
						Dialog dlg = new Dialog(Totamount.this);
				  		dlg.setTitle("Failed to update");
				  		TextView view = new TextView(Totamount.this);
				  		view.setText("Haven't received the total amount. Therefore cannot update the record.");
				  		dlg.setContentView(view);
				  		dlg.show();
					}
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
		getMenuInflater().inflate(R.menu.totamount, menu);
		return true;
	}

}
