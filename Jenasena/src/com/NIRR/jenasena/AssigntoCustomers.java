package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.StringTokenizer;

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
import java.util.List;

public class AssigntoCustomers extends Activity implements  OnItemSelectedListener {

	//String customerId,repId;
		String label;
		
		TextView repID;
		TextView cusID;
		Spinner items;
		TextView availqty;
		TextView titlesellqty;
		TextView titletwo;
		TextView titleone;
		EditText sellqty;
		
		TextView sellDate,dueDate;
		//ListView l;
		Button bAddItems;
		Button bviewInvoice;
		TextView titleAc1;
		TextView titleAc2,titleselldte,titleduedte,titlepaiddte;
		TextView titleup;
		TextView unitPrice;
		
		Button sDate;
		Button dDate;
		
		String s2,s1,todayDate,newInvoiceId,invoID;
		
		
		 private int pYear;
		 private int pMonth;
		 private int pDay;
		    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
		 static final int DATE_DIALOG_ID = 0;
		
		int buttonCount=0;
		private DBCreater dbHelper;
		private SQLiteDatabase ourDatabase;
		 
		//private DBCreater dbHelper;
		//private SimpleCursorAdapter dataAdapter;
		
		//private DbHelper ourHelper;
		//private final Context ourContext;
		//private SQLiteDatabase ourDatabase;


		
		//private DbHelper hh;
		
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
		                    
		                    if(buttonCount==1)
		                    updateDisplay();
		                    
		                    else
		                    updateDisplayDue();
					}
	            };
	 
		
	            
	            public void updateDisplayDue(){
	            	
	            	  dueDate.setText(
	                          new StringBuilder()
	                                  // Month is 0 based so add 1
	                          .append(pYear).append("/")        
	                          .append(pMonth + 1).append("/")
	                          .append(pDay).append("")
	                                  );
	            	
	            }

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
		setContentView(R.layout.activity_assignto_customers);
		
		sDate=(Button)findViewById(R.id.btsDate);
		dDate=(Button)findViewById(R.id.btdDate);
		
		
		/*
		String countQuery = "SELECT  * FROM Invoice";
		Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		int count = cursor.getCount();
		count++;
		String invoiceId="Invoice" +count;
			
		System.out.println(invoiceId);
		*/
	
		repID=(TextView) findViewById(R.id.tvACRepName);
		Intent intent1=getIntent();
		 s2=intent1.getStringExtra("rep_id");
		repID.setText(s2);
		
		
		
		
		
		cusID=(TextView) findViewById(R.id.tvACCusId);
		Intent intent = getIntent();
		s1 = intent.getStringExtra("cus_id");
		cusID.setText(s1);
		
		
		
		
		titleAc1=(TextView)findViewById(R.id.tvtitleAC1);
		titleAc2=(TextView)findViewById(R.id.tvtitleAC2);
		titlesellqty=(TextView)findViewById(R.id.tvACtitle3);
		titletwo=(TextView)findViewById(R.id.tvACtitle2);
		availqty=(TextView) findViewById(R.id.tvACavailQty);
		bAddItems=(Button)findViewById(R.id.bACAdditems);
		bviewInvoice=(Button)findViewById(R.id.btviewInvoice);
		sellqty=(EditText)findViewById(R.id.etACqty);
		titleone=(TextView)findViewById(R.id.tvACtitle1);
		
		titleup=(TextView)findViewById(R.id.tvtitleuprice);
		unitPrice=(TextView)findViewById(R.id.tvunitPrice);
		
		titleselldte=(TextView)findViewById(R.id.tvtitleselldate);
		sellDate=(TextView)findViewById(R.id.tvselldate);
		
		titleduedte=(TextView)findViewById(R.id.tvtitledueDate);
		dueDate=(TextView)findViewById(R.id.tvduedate);
		
		//titlepaiddte=(TextView)findViewById(R.id.tvtitlepaidDate);
		//paidDate=(EditText)findViewById(R.id.etpaidDate);
		
		items=(Spinner) findViewById(R.id.spACItemLoader);
		//items.setOnItemSelectedListener(this);
		items.setOnItemSelectedListener(this);

		//l=(ListView)findViewById(R.id.listView1);
		
		
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
		
	    
		//System.out.println(iID);
		 
		loadRepItems();
	    //String rId=	repID.getText().toString();
		
		availqty.setText(repID.getText().toString());
	
		dbHelper = new DBCreater(this);
		dbHelper.open();
	     invoID=getNewInvoice();
		dbHelper.close();
		
		//date picker
				sDate.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						buttonCount=1;
						showDialog(DATE_DIALOG_ID);
						
					}
				});
				
				
				dDate.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
					buttonCount=2;
						showDialog(DATE_DIALOG_ID);
						
					}
				});
				
				
				 /** Get the current date */
		        final Calendar calc = Calendar.getInstance();
		        pYear = calc.get(Calendar.YEAR);
		        pMonth = calc.get(Calendar.MONTH);
		        pDay = calc.get(Calendar.DAY_OF_MONTH);
		 
		        updateDisplay();
		        
		        updateDisplayDue();
				
		        
		       


				bAddItems.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						
						 if(sellqty.getText().toString().equals("")){
							Dialog d1=new Dialog(AssigntoCustomers.this);
							d1.setTitle("Fill empty fields");
							TextView t=new TextView(AssigntoCustomers.this);
							t.setText("Please enter values for empty fields ");
							d1.setContentView(t);
							d1.show();
							
							
						}
						 
						 
						 if(sellqty.getText().toString().length()!=0){
						
						
						//check for the validation of dates
						String sellD=sellDate.getText().toString();
						String dueD=dueDate.getText().toString();
						
						
						StringTokenizer token = new StringTokenizer(sellD,"/");
						int SellYear,SellMonth,SellDate;
						int [] sellResult = {1,2,3};
						int i=0;
						
						while(token.hasMoreTokens()){
							sellResult[i++]=Integer.parseInt(token.nextToken());
							
							/*
							SellYear=Integer.parseInt(token.nextToken());
							SellMonth=Integer.parseInt(token.nextToken("1"));
							SellDate=Integer.parseInt(token.nextToken("2"));
							*/
							
						}
						System.out.println("sell yrrrrr  "+ sellResult[0]);
						System.out.println("sell monthtttt  "+ sellResult[1]);
						System.out.println("sell daaate "+ sellResult[2]);
						
						
						
						
						
						StringTokenizer token2 = new StringTokenizer(dueD,"/");
						
						int [] dueResult2 = {1,2,3};
						int j=0;
						
						while(token2.hasMoreTokens()){
							dueResult2[j++]=Integer.parseInt(token2.nextToken());
							
							/*
							SellYear=Integer.parseInt(token.nextToken());
							SellMonth=Integer.parseInt(token.nextToken("1"));
							SellDate=Integer.parseInt(token.nextToken("2"));
							*/
							
						}
						
						System.out.println("due yrrrrr "+ dueResult2[0]);
						System.out.println("due monthtttt "+ dueResult2[1]);
						System.out.println("due daaate "+ dueResult2[2]);
						
						
						String nString=sellqty.getText().toString();
						int sellingQty=Integer.valueOf(nString);
						
						String mString=availqty.getText().toString();
						int availQty=Integer.valueOf(mString);
						
						String oString=unitPrice.getText().toString();
						double unitPricee=Double.valueOf(oString);
						
						
						System.out.println(availQty);
						System.out.println(sellingQty);
						
						
						if(sellResult[0]>dueResult2[0] || (sellResult[0]==dueResult2[0] && sellResult[1]>dueResult2[1]) || (sellResult[0]==dueResult2[0] && sellResult[1]==dueResult2[1] && sellResult[2]>dueResult2[2]))
						{
							Dialog d1=new Dialog(AssigntoCustomers.this);
							d1.setTitle("Plese enter a valid due date");
							TextView t=new TextView(AssigntoCustomers.this);
							t.setText("The selling date should be less than or eqaul to due date ");
							d1.setContentView(t);
							d1.show();
							
						}
						
					
						
						else if(0<sellingQty && sellingQty<=availQty )
						{
						DBCreater fill=new DBCreater(AssigntoCustomers.this);
						fill.open();
						
						//insert data to FakeInvoice
						fill.createFakeInvoice(label, sellingQty, "100");
						
						
						
						
						// total;
						
						
				//		fill.createCart(repID.getText().toString(), cusID.getText().toString(),invoID,label, sellingQty, unitPricee, (sellingQty*unitPricee), sellDate.getText().toString(), dueDate.getText().toString(),"Not Paid");
						
						
						
						//DBCreater dbcreator=new DatabaseCreator(context);
						//SQLiteDatabase sqdb=fill
						
						
						ContentValues cv = new ContentValues();
						cv.put(DBCreater.Key_repS_available_qty,(availQty-sellingQty));
						fill.update("Rep_Stock", cv, "repS_rep_id=? and repS_item_name=?",new String[] {repID.getText().toString(),label} );
				       
						
						
						
						fill.close();
						
						
						Dialog d=new Dialog(AssigntoCustomers.this);
						d.setTitle("Successfull");
						TextView t=new TextView(AssigntoCustomers.this);
						t.setText("You successfully inserted the record");
						d.setContentView(t);
						d.show();
						
						}
						
						
						else if(sellingQty>availQty) {

								Dialog d1=new Dialog(AssigntoCustomers.this);
								d1.setTitle("Error");
								TextView t=new TextView(AssigntoCustomers.this);
								t.setText("Selling quantity need to be less than available quantity");
								d1.setContentView(t);
								d1.show();
								
								
							}
						
						
						else if(sellingQty==0){
							
							Dialog d1=new Dialog(AssigntoCustomers.this);
							d1.setTitle("Warning");
							TextView t=new TextView(AssigntoCustomers.this);
							t.setText("Selling quantity cannot be 0");
							d1.setContentView(t);
							d1.show()  ;
							
							
						}
						
						
				}
						/*
						 else if(sellingQty>availQty) {

							Dialog d1=new Dialog(AssigntoCustomers.this);
							d1.setTitle("Error");
							TextView t=new TextView(AssigntoCustomers.this);
							t.setText("Selling quantity need to be less than available quantity");
							d1.setContentView(t);
							d1.show();
							
							
						}
						
						else if(sellingQty==0){
							
							Dialog d1=new Dialog(AssigntoCustomers.this);
							d1.setTitle("Warning");
							TextView t=new TextView(AssigntoCustomers.this);
							t.setText("Selling quantity cannot be 0");
							d1.setContentView(t);
							d1.show()  ;
							
							
						}
						*/
						
					}
				});
				
				
				bviewInvoice.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
					/*
						Dialog d1=new Dialog(AssigntoCustomers.this);
						d1.setTitle("Clicked");
						TextView t=new TextView(AssigntoCustomers.this);
						t.setText("You clicked view invoive button");
						d1.setContentView(t);
						d1.show();
						*/
						
						Intent p2 = new Intent(AssigntoCustomers.this,ViewInvoice.class);
						p2.putExtra("cus_id", s1);
						p2.putExtra("rep_id", s2);
						p2.putExtra("invoice_id", invoID);
						p2.putExtra("selling_date", sellDate.getText().toString());
						startActivity(p2);
						
						
						
						
						
					}
				});
				
				
				
				


		
	}
	
	private String getNewInvoice() {
		//DBCreater fill=new DBCreater(AssigntoCustomers.this);
		//fill.openforread();
		System.out.println("getInvoice inside");
		newInvoiceId =dbHelper.getNewInvoice();
		System.out.println(newInvoiceId);
		//fill.close();
		return newInvoiceId;
		
		
	}

	
 
	//
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
	
	
	
	public void loadRepItems(){
		DBCreater entry = new DBCreater(AssigntoCustomers.this);
		entry.openforread();
		List<String> lables = entry.getAllLabel(repID.getText().toString(),todayDate);
		
		
		if(lables.size()==0){
			
			 
			Dialog d1=new Dialog(AssigntoCustomers.this);
			d1.setTitle("No items to sell" );
			TextView t=new TextView(AssigntoCustomers.this);
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
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		
		label=parent.getItemAtPosition(position).toString();
		
		//Showing selected sprinner item
		Toast.makeText(parent.getContext(), "You selected:"+label , Toast.LENGTH_LONG).show();
		
		DBCreater entry = new DBCreater(AssigntoCustomers.this);
		entry.openforread();
		List<String> lables = entry.getAllItemsndQty(repID.getText().toString(),label);
		availqty.setText(lables.get(1).toString());
		unitPrice.setText(lables.get(2).toString());
		
		
	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.assignto_customers, menu);
		return true;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
