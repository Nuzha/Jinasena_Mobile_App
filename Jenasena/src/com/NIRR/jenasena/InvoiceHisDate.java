package com.NIRR.jenasena;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class InvoiceHisDate extends Activity {

	TextView repId, date;
	Button dateSelect,viewHistory;
	String repID="aaa";
	private int pYear;
	private int pMonth;
	private int pDay;
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;
	    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
	static final int DATE_DIALOG_ID = 0;
	 
	 
	 private DatePickerDialog.OnDateSetListener pDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {
	 
	               

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						 pYear = year;
		                    pMonth = monthOfYear;
		                    pDay = dayOfMonth;
		                    
		                    //if(buttonCount==1)
		                    updateDisplay();
						
					}
	            };
	 
	            
	            public void updateDisplay(){
	            	
	            	  date.setText(
	                          new StringBuilder()
	                                  // Month is 0 based so add 1
	                          .append(pYear).append("/")        
	                          .append(pMonth + 1).append("/")
	                          .append(pDay).append("")
	                                  );
	            	
	            }


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoice_his_date);
		repId=(TextView)findViewById(R.id.tv_invoiceHisDate_repid);
		date=(TextView)findViewById(R.id.tv_invoiceHisDate_date);
		dateSelect=(Button)findViewById(R.id.bt_invoiceHisDate_date);
		viewHistory=(Button)findViewById(R.id.bt_invoiceHisDate_genInvoce);
		
		

		Intent intent = getIntent();
		repID = intent.getStringExtra("repid");
	    repId.setText(repID)   ;
		
		dateSelect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		
		
		 /** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);
		
		updateDisplay();
		
		

		
		viewHistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dbHelper = new DBCreater(InvoiceHisDate.this);
				dbHelper.open();
				displayInvoDerails();
				
				
			}
		});
		
		
	}

	private void displayInvoDerails() {
		Cursor cursor = dbHelper.getInvoiceDetailsGivenDate(repID,date.getText().toString());
		if(cursor.getCount()==0){
			
		
		Dialog d1=new Dialog(InvoiceHisDate.this);
		d1.setTitle("No Records");
		TextView t=new TextView(InvoiceHisDate.this);
		t.setText("There are no records invoiced on the selected date ");
		d1.setContentView(t);
		d1.show();
		
		
		}
		
		
		 else{
				String[] columns = new String[] { 
						DBCreater.Key_Invoice_cust_id,
						DBCreater.Key_Invoice_invoice_id,
						DBCreater.Key_Invoice_selling_date,
						DBCreater.Key_Invoice_post_dicount_amount,
						DBCreater.Key_Invoice_paid_cost,
						DBCreater.Key_Invoice_amount_to_be_paid
						
						//DBCreater.Key_repS_assigned_qty 
				};
				
				int[] to = new int[]{
						R.id.tv_cusID_invoiceSingle,
						R.id.tv_invoice_id_invoiceSingle,
						R.id.tv_invoiced_date_invoiceSingle,
						R.id.tv_total_amount_invoiceSingle,
						R.id.tv_paid_amount_invoiceSingle,
						R.id.tv_amountTobePaid_invoiceSingle
					
				};
				
				
				dataAdapter1 = new SimpleCursorAdapter(this, R.layout.invosingle, cursor, columns, to,0);
				final ListView lvinvoiceHistory = (ListView)findViewById(R.id.lv_invoiceHisDate_his);
				lvinvoiceHistory.setAdapter(dataAdapter1);
				
		 }
	
	}


	@Override
	  protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, 
	                        pDateSetListener,
	                        pYear, pMonth, pDay);
	        }
	        return null;
	    }
	
	

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.invoice_his_date, menu);
		return true;
	}

}
