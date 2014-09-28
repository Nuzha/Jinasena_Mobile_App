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
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SellToCustomers extends Activity implements OnItemSelectedListener{

	TextView repID,cusID,availqty,unitPrice,sellDate;
	EditText sellqty;
	Button bAddItems,bviewInvoice;
	String s2,s1,todayDate,newInvoiceId,invoID,selectedItem,label;
	Spinner items;
	
	 private int pYear;
	 private int pMonth;
	 private int pDay;
	    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
	 static final int DATE_DIALOG_ID = 0;
	
	
	private DBCreater dbHelper;
	private SQLiteDatabase ourDatabase;
	
	
	 /** Callback received when the user "picks" a date in the dialog */
	 /** Callback received when the user "picks" a date in the dialog */
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
 
                
				@Override
				public void onDateSet(DatePicker view, int year, 
                        int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					 pYear = year;
	                    pMonth = monthOfYear;
	                    pDay = dayOfMonth;
	                    
	            
	                    updateDisplay();
	                    
	                    
				}
            };
 
	
            
            

            /** Updates the date in the TextView */
            private void updateDisplay() {
                sellDate.setText(
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
		setContentView(R.layout.activity_sell_to_customers);
		repID=(TextView)findViewById(R.id.tv_SellToCus_Repid);
		cusID=(TextView)findViewById(R.id.tv_SellToCus_Cusid);
		availqty=(TextView)findViewById(R.id.tv_SellToCus_AvailQty);
		unitPrice=(TextView)findViewById(R.id.tv_SellToCus_Unitprice);
		sellqty=(EditText)findViewById(R.id.et_SellToCus_SellQty);
		bAddItems=(Button)findViewById(R.id.bt_SellToCus_Additem);
		bviewInvoice=(Button)findViewById(R.id.bt_SellToCus_Vieworder);
		sellDate=(TextView)findViewById(R.id.tv_SellToCus_sellingdate);
		
		Intent intent1=getIntent();
		s2=intent1.getStringExtra("rep_id");
		repID.setText(s2);
		

		
		Intent intent = getIntent();
		s1 = intent.getStringExtra("cus_id");
		cusID.setText(s1);
		
		
		items=(Spinner) findViewById(R.id.sp_SellToCus_items);
		items.setOnItemSelectedListener(this);
		
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
        
        loadRepItems();
        
        availqty.setText(repID.getText().toString());
    	
		dbHelper = new DBCreater(this);
		dbHelper.open();
	    invoID=getNewInvoice();
		dbHelper.close();
		
		
		/** Get the current date */
        final Calendar calc = Calendar.getInstance();
        pYear = calc.get(Calendar.YEAR);
        pMonth = calc.get(Calendar.MONTH);
        pDay = calc.get(Calendar.DAY_OF_MONTH);
        
        
      
 
        updateDisplay();
        
        bAddItems.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
	
				
				if(sellqty.getText().toString().length()==0){
					Dialog d1=new Dialog(SellToCustomers.this);
					d1.setTitle("Fill empty fields");
					TextView t=new TextView(SellToCustomers.this);
					t.setText("Please enter values for empty fields ");
					d1.setContentView(t);
					d1.show();
					
					
				}
				 
				
				else if(sellqty.getText().toString().length()!=0){
				
					try{
						
						String nString=sellqty.getText().toString();
						int sellingQty=Integer.valueOf(nString);
						
						
						

						String mString=availqty.getText().toString();
						int availQty=Integer.valueOf(mString);
						
						String oString=unitPrice.getText().toString();
						double unitPricee=Double.valueOf(oString);
						

						if(sellingQty>availQty) {

								Dialog d1=new Dialog(SellToCustomers.this);
								d1.setTitle("Error");
								TextView t=new TextView(SellToCustomers.this);
								t.setText("Selling quantity need to be less than available quantity");
								d1.setContentView(t);
								d1.show();
								
								
							}
						
						
						 if(sellingQty==0){
							 //selectedItem=items.getSelectedItem().toString();
								
								//System.out.println("selected itemmmmmmm"+ selectedItem);
							
							Dialog d1=new Dialog(SellToCustomers.this);
							d1.setTitle("Warning");
							TextView t=new TextView(SellToCustomers.this);
							t.setText("Selling quantity cannot be 0");
							d1.setContentView(t);
							d1.show()  ;
							
							
						}
						
						 
						 
						 else if(sellingQty<=availQty){
							selectedItem=items.getSelectedItem().toString();
							
							System.out.println("selected itemmmmmmm"+ selectedItem);
							
							DBCreater fill=new DBCreater(SellToCustomers.this);
							fill.open();
							fill.createCart(repID.getText().toString(),cusID.getText().toString(),invoID,label,sellingQty,unitPricee,(sellingQty*unitPricee));
							
							ContentValues cv = new ContentValues();
							cv.put(DBCreater.Key_repS_available_qty,(availQty-sellingQty));
							fill.update("Rep_Stock", cv, "repS_rep_id=? and repS_item_name=?",new String[] {repID.getText().toString(),label} );
					       
							fill.close();
							
							
							Dialog d=new Dialog(SellToCustomers.this);
							d.setTitle("Successfull");
							TextView t=new TextView(SellToCustomers.this);
							t.setText("You successfully inserted the record");
							d.setContentView(t);
							d.show();
							

						
						
						
					}
					
			}
					catch(Exception e){
						
						
						Dialog d1=new Dialog(SellToCustomers.this);
						d1.setTitle("Please enter a digit" );
						TextView t=new TextView(SellToCustomers.this);
						t.setText("Paying amount sholud me in numeric format");
						d1.setContentView(t);
						d1.show();
						
						
					}
					
					
			}
					    
						
					 
			}
				 
				
				
			
		});
        
        bviewInvoice.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent p2 = new Intent(SellToCustomers.this,ViewInvoice.class);
				p2.putExtra("cus_id", s1);
				p2.putExtra("rep_id", s2);
				p2.putExtra("invoice_id", invoID);
				p2.putExtra("selling_date", sellDate.getText().toString());
				startActivity(p2);
		
			}
		});
        
	}
	
	private String getNewInvoice() {
		System.out.println("getInvoice inside");
		newInvoiceId =dbHelper.getNewInvoice();
		System.out.println(newInvoiceId);
		//fill.close();
		return newInvoiceId;
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
	
	

	private void loadRepItems() {
		DBCreater entry = new DBCreater(SellToCustomers.this);
		entry.openforread();
		List<String> lables = entry.getAllLabel(repID.getText().toString(),todayDate);
		
		
		if(lables.size()==0){
			
			 
			Dialog d1=new Dialog(SellToCustomers.this);
			d1.setTitle("No items to sell" );
			TextView t=new TextView(SellToCustomers.this);
			t.setText("You don't have items to sell today");
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
        items.setAdapter(dataAdapter);
        entry.close();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sell_to_customers, menu);
		return true;
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		label=parent.getItemAtPosition(position).toString();
		
		//Showing selected sprinner item
		Toast.makeText(parent.getContext(), "You selected:"+label , Toast.LENGTH_LONG).show();
		
		DBCreater entry = new DBCreater(SellToCustomers.this);
		entry.openforread();
		List<String> lables = entry.getAllItemsndQty(repID.getText().toString(),label,todayDate);
		availqty.setText(lables.get(1).toString());
		unitPrice.setText(lables.get(2).toString());
		
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
