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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReturnItems extends Activity implements OnItemSelectedListener {
TextView repid,availQty,date;
Spinner items;
EditText itemReturned,description;
Button selectDate,addRec;
String repString;
public static String packagename="com.NIRR.redistributionsystem";
private int pYear;
private int pMonth;
private int pDay;
String todayDate,label;


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
		                    
		                    
		                    updateEnteredDate();
		                    
		                    
					}
	            };
	 
		
	            
	            public void updateEnteredDate(){
	            	
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
		setContentView(R.layout.activity_return_items);
		repid=(TextView)findViewById(R.id.tv_repid_return_items);
		availQty=(TextView)findViewById(R.id.tv_availQty_retutn_items);
		
		items=(Spinner)findViewById(R.id.spnner_items_retutn_items);
		items.setOnItemSelectedListener(this);
		
		itemReturned=(EditText)findViewById(R.id.et_qty_return_item);
		description=(EditText)findViewById(R.id.et_descpt_return_item);
		selectDate=(Button)findViewById(R.id.bt_addDate_return_item);
		addRec=(Button)findViewById(R.id.bt_add_rec_return_item);
		date=(TextView)findViewById(R.id.tv_date_return_title);
		
		Intent intent = getIntent();
		repString = intent.getStringExtra(RepMenu.packagename);
		repid.setText(repString);
		
		 
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
	      
	      System.out.println("Today date is::"+ todayDate);
	      
	      loadRepItems();
			
	      
	      selectDate.setOnClickListener(new View.OnClickListener() {
	  		
	  		@Override
	  		public void onClick(View v) {
	  			showDialog(DATE_DIALOG_ID);
	  			
	  			
	  		}
	  	});
	  		
	      updateEnteredDate();
          
	      
	      
addRec.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		 if(itemReturned.getText().toString().length()==0 || description.getText().toString().length()==0){
	    	  Dialog d1=new Dialog(ReturnItems.this);
				d1.setTitle("Cannot Insert");
				TextView t=new TextView(ReturnItems.this);
				t.setText("Please fill the empty fields");
				d1.setContentView(t);
				d1.show();
	    	  
	    	  
	      }	      
	      
	      else if(itemReturned.getText().toString().length()!=0){
	    	  
	    	  
	    	  
					String nString=itemReturned.getText().toString();
					int returnQty=Integer.valueOf(nString);
					
					
					String mString=availQty.getText().toString();
					int availQty=Integer.valueOf(mString);
					
					if(returnQty==0){
						Dialog d1=new Dialog(ReturnItems.this);
						d1.setTitle("Cannot Insert");
						TextView t=new TextView(ReturnItems.this);
						t.setText("Return quantity cannot be zero");
						d1.setContentView(t);
						d1.show();
			    	  
						
					}

					else if(returnQty>availQty){
						Dialog d1=new Dialog(ReturnItems.this);
						d1.setTitle("Cannot Insert");
						TextView t=new TextView(ReturnItems.this);
						t.setText("Return quantity cannot be greater than the available qauntity");
						d1.setContentView(t);
						d1.show();

						
					}
	    	  
					else if(date.getText().toString().length()!=0){
						String enteredDate=date.getText().toString();
					
						//entered date
						StringTokenizer token = new StringTokenizer(enteredDate,"/");
						
						int [] eneteredResult = {1,2,3};
						int i=0;
						
						while(token.hasMoreTokens()){
							eneteredResult[i++]=Integer.parseInt(token.nextToken());
							
							
						}
						System.out.println("sell yrrrrr  "+ eneteredResult[0]);
						System.out.println("sell monthtttt  "+ eneteredResult[1]);
						System.out.println("sell daaate "+ eneteredResult[2]);
					
						//today 
						StringTokenizer token2 = new StringTokenizer(todayDate,"/");
						int [] todayResult2 = {1,2,3};
					
						int j=0;
						
						while(token2.hasMoreTokens()){
							todayResult2[j++]=Integer.parseInt(token2.nextToken());
							
						}
						
						System.out.println("due yrrrrr "+ todayResult2[0]);
						System.out.println("due monthtttt "+ todayResult2[1]);
						System.out.println("due daaate "+ todayResult2[2]);
						
						
						if(eneteredResult[0]>todayResult2[0] || (eneteredResult[0]==todayResult2[0] && eneteredResult[1]>todayResult2[1]) || (eneteredResult[0]==todayResult2[0] && eneteredResult[1]==todayResult2[1] && eneteredResult[2]>todayResult2[2]))
						{
							Dialog d1=new Dialog(ReturnItems.this);
							d1.setTitle("Plese enter a valid return date");
							TextView t=new TextView(ReturnItems.this);
							t.setText("The return date cannot be a future date ");
							d1.setContentView(t);
							d1.show();
							
						}
						
						else{
						
						String delId=repString.substring(0, 5);
						System.out.println(delId);
						
						String item_name=items.getSelectedItem().toString();
						
						DBCreater fill=new DBCreater(ReturnItems.this);
						fill.open();
						fill.createReturnedItems(delId,repString, item_name,returnQty, date.getText().toString(),description.getText().toString());
						
						ContentValues cv = new ContentValues();
						cv.put(DBCreater.Key_repS_available_qty,(availQty-returnQty));
						fill.update("Rep_Stock", cv, "repS_rep_id=? and repS_item_name=?",new String[] {repString,item_name} );
				       
						fill.close();
						
						Dialog d=new Dialog(ReturnItems.this);
						d.setTitle("Successfull");
						TextView t=new TextView(ReturnItems.this);
						t.setText("You successfully inserted the record");
						d.setContentView(t);
						d.show();
						}
						
						
					}
					
					
					
	    	  
	    	 
	      }

		
		
		
		
		
	}
});
	      
	     		
	}
	
	
	private void loadRepItems() {
		System.out.println("rep"+ repString);
		System.out.println("date "+todayDate);
		
		DBCreater entry = new DBCreater(ReturnItems.this);
		entry.openforread();
		List<String> lables = entry.getAllLabel(repString,todayDate);
		
		
		if(lables.size()==0){
			
			 
			Dialog d1=new Dialog(ReturnItems.this);
			d1.setTitle("No items to return" );
			TextView t=new TextView(ReturnItems.this);
			t.setText("You don't have items to return ");
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
		getMenuInflater().inflate(R.menu.return_items, menu);
		return true;
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
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
         label=parent.getItemAtPosition(position).toString();
		
		//Showing selected sprinner item
		Toast.makeText(parent.getContext(), "You selected:"+label , Toast.LENGTH_LONG).show();
		
		DBCreater entry = new DBCreater(ReturnItems.this);
		entry.openforread();
		List<String> lables = entry.getAllItemsndQty(repString,label,todayDate);
		availQty.setText(lables.get(1).toString());
		
	   }






	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
