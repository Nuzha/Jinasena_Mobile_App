package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class ManageCustomerPayments extends Activity  implements  OnItemSelectedListener{

	Spinner invoices;
	TextView solddate,amount, payingDate,repid,cusid;
	EditText payingAmount;
	String rep,cus,selectedInvoiceId,todayDate;;
	Button enter,pDate;
	double alreadyPaidCost,newlyPaidCost,amounttobepaid;
	

	 private int pYear;
	 private int pMonth;
	 private int pDay;
	    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
	 static final int DATE_DIALOG_ID = 0;
	 
	 
	 
	 private DatePickerDialog.OnDateSetListener pDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {
	 
	                
					@Override
					public void onDateSet(DatePicker view, int year, 
                         int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						 pYear = year;
		                    pMonth = monthOfYear;
		                    pDay = dayOfMonth;
		                    
		                    
		                    updateDisplayDue();
		                    
		                    
					}
	            };
	 
		
	            
	            public void updateDisplayDue(){
	            	
	            	  payingDate.setText(
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
		setContentView(R.layout.activity_manage_customer_payments);
		
		invoices=(Spinner)findViewById(R.id.sp_invoid_ManageCusPayments);
		invoices.setOnItemSelectedListener(this);
		
		solddate=(TextView)findViewById(R.id.tv_soldDate_ManageCusPayments);
		amount=(TextView)findViewById(R.id.tv_amount_ManageCusPayments);
		payingAmount=(EditText)findViewById(R.id.tv_payingamnt_ManageCusPayments);
		payingDate=(TextView)findViewById(R.id.tv_PayingDate_ManageCusPayements);
		
		pDate=(Button)findViewById(R.id.tb_duedate_ManageCusPayments);
		enter=(Button)findViewById(R.id.bt_enter_ManageCusPayments);
		
		repid=(TextView)findViewById(R.id.tv_repid_ManageCusPayments);
		Intent intent1=getIntent();
		rep=intent1.getStringExtra("rep_id");
		repid.setText(rep);
		
		cusid=(TextView)findViewById(R.id.tv_cusid_ManageCusPayments);
		Intent intent2=getIntent();
		cus=intent2.getStringExtra("cus_id");
		cusid.setText(cus);
		

		
		loadinvoices();
		
		

		 /** Get the current date */
       final Calendar cal = Calendar.getInstance();
       pYear = cal.get(Calendar.YEAR);
       pMonth = cal.get(Calendar.MONTH);
       pDay = cal.get(Calendar.DAY_OF_MONTH);
       
       
      
       
       StringBuilder ddd= new StringBuilder()
       // Month is 0 based so add 1
       .append(pYear).append("/")        
       .append(pMonth + 1).append("/")
       .append(pDay).append("");
       
       todayDate=ddd.toString();
       System.out.println("Todaysss date " +todayDate);
       
		
	pDate.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			showDialog(DATE_DIALOG_ID);
			
			
		}
	});
		
		
	 updateDisplayDue();
		
	 
	 enter.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			if(payingAmount.getText().toString().equals("")){
				
				Dialog d1=new Dialog(ManageCustomerPayments.this);
				d1.setTitle("Please fill the empty field" );
				TextView t=new TextView(ManageCustomerPayments.this);
				t.setText("Fill the empty fiels to proceed.");
				d1.setContentView(t);
				d1.show();
				
				
				
			}
			
			
			
			else if(payingAmount.getText().toString().length()!=0 ) {
				
				try{
				
				    newlyPaidCost=Double.parseDouble(payingAmount.getText().toString());
				    
				    
				    StringTokenizer token = new StringTokenizer(todayDate,"/");
					int todayYear,todayMonth,todayDate;
					int [] todayResult = {1,2,3};
					int i=0;
					
					while(token.hasMoreTokens()){
						todayResult[i++]=Integer.parseInt(token.nextToken());
						
						
					}
					
					
					StringTokenizer token2 = new StringTokenizer(payingDate.getText().toString(),"/");
					
					int [] paidResult2 = {1,2,3};
					int j=0;
					
					while(token2.hasMoreTokens()){
						paidResult2[j++]=Integer.parseInt(token2.nextToken());
						
					}
					
					System.out.println("paid yrrrrr "+ paidResult2[0]);
					System.out.println("paid monthtttt "+ paidResult2[1]);
					System.out.println("paid daaate "+ paidResult2[2]);
				    
				    
				    
				    if(newlyPaidCost==0){

				    	Dialog d1=new Dialog(ManageCustomerPayments.this);
						d1.setTitle("Cannont Insert" );
						TextView t=new TextView(ManageCustomerPayments.this);
						t.setText("Paying amount cannot be zero");
						d1.setContentView(t);
						d1.show();
				    	
				    	
				    }
				    
				    else if(Double.parseDouble(amount.getText().toString())<Double.parseDouble(payingAmount.getText().toString())){
				    	
				    	Dialog d1=new Dialog(ManageCustomerPayments.this);
						d1.setTitle("Cannont Insert" );
						TextView t=new TextView(ManageCustomerPayments.this);
						t.setText("Paying amount should be less than or equal to amount to be paid");
						d1.setContentView(t);
						d1.show();
				    	
				    	
				    	
				    }
				    
				    
				    else if(todayResult[0]<paidResult2[0] || (todayResult[0]==paidResult2[0] && todayResult[1]<paidResult2[1]) || (todayResult[0]==paidResult2[0] && todayResult[1]==paidResult2[1] && todayResult[2]<paidResult2[2]))
					{
						Dialog d1=new Dialog(ManageCustomerPayments.this);
						d1.setTitle("Plese enter a valid paying date");
						TextView t=new TextView(ManageCustomerPayments.this);
						t.setText("The paying date should be less than or eqaul to todays date ");
						d1.setContentView(t);
						d1.show();
						
					}
				    
				    
				    else{
					
					DBCreater fill=new DBCreater(ManageCustomerPayments.this);
					fill.open();
					

					
					List<String> lables = fill.getInvoDetails(selectedInvoiceId);
					alreadyPaidCost=Double.parseDouble(lables.get(2).toString());
					amounttobepaid=Double.parseDouble(lables.get(1).toString());
					
					
					
					ContentValues cv = new ContentValues();
					cv.put(DBCreater.Key_Invoice_paid_date,payingDate.getText().toString());
					//?????????????????????
					cv.put(DBCreater.Key_Invoice_paid_cost, (alreadyPaidCost+newlyPaidCost));
					cv.put(DBCreater.Key_Invoice_amount_to_be_paid,(amounttobepaid-newlyPaidCost));
					fill.update("Invoice", cv, "Invoice_invoice_id=?",new String[] {selectedInvoiceId} );
					
					fill.close();
					
					
					Dialog d=new Dialog(ManageCustomerPayments.this);
					d.setTitle("Successfull");
					TextView t=new TextView(ManageCustomerPayments.this);
					t.setText("You successfully inserted the record");
					d.setContentView(t);
					d.show();
						
					
				    }
				}
				
				catch(Exception e){
					
					
					Dialog d1=new Dialog(ManageCustomerPayments.this);
					d1.setTitle("Please enter a digit" );
					TextView t=new TextView(ManageCustomerPayments.this);
					t.setText("Paying amount sholud me in numeric format");
					d1.setContentView(t);
					d1.show();
					
				}
				
				
				
				
				
				
				
			}
			
			
			
			
			
		}
	});
	 
		
	}

	private void loadinvoices() {
		DBCreater entry = new DBCreater(ManageCustomerPayments.this);
		entry.openforread();
		List<String> lables = entry.loadInvoiceIds(rep,cus);
		
		
		if(lables.size()==0){
			
			 
			Dialog d1=new Dialog(ManageCustomerPayments.this);
			d1.setTitle("No invoices" );
			TextView t=new TextView(ManageCustomerPayments.this);
			t.setText("No invoices handled to the selected customer");
			d1.setContentView(t);
			d1.show();
			
			//Toast.makeText(getApplicationContext(), "No items to be sold today ", Toast.LENGTH_LONG).show();
			
			
		}
		
		else{
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
			// Drop down layout style - list view with radio button
	        dataAdapter
	                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 
	        // attaching data adapter to spinner
	        invoices.setAdapter(dataAdapter);
	        entry.close();
			}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_customer_payments, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
	   
		  System.out.println("Onitem Click before parent s ");
		selectedInvoiceId=parent.getItemAtPosition(position).toString();
		
	   System.out.println("Onitem Click after parent statemnt  "+ selectedInvoiceId);
		//Showing selected sprinner item
		Toast.makeText(parent.getContext(), "You selected:"+selectedInvoiceId , Toast.LENGTH_LONG).show();
		
		DBCreater entry = new DBCreater(ManageCustomerPayments.this);
		entry.openforread();
		List<String> lables = entry.getInvoDetails(selectedInvoiceId);
		solddate.setText(lables.get(0).toString());
		amount.setText(lables.get(1).toString());
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
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
	
	

}
