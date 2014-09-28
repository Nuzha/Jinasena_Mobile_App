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

public class ViewLowStock extends Activity{
	
	TextView logger,availqty,items,ordereddate,getdate;
	String usr,ch,itm,quant;
	EditText receivd;
	Button update;
	
	
	TextView mDateDisplay,mTodayDisplay;
	Button mPickDate,mPickToday;
			
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
	             updateDisplay();
	          }
	                    
	          else{
	        	  updateTodayDisplay();
	          }
	             
	           }
	     };
	 
		
	            
	      public void updateDisplay(){
	            	
	         mDateDisplay.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	      } 
	      
	      
	      public void updateTodayDisplay(){
	            	
		      mTodayDisplay.setText(
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
		setContentView(R.layout.activity_view_low_stock);
		
		logger=(TextView)findViewById(R.id.tv_vls_logger_rosh);
		Intent intent = getIntent();
		usr = intent.getStringExtra("user");
		logger.setText(usr);
		
		items=(TextView)findViewById(R.id.tv_vls_itemname_rosh);
		Intent intent2 = getIntent();
		itm = intent2.getStringExtra("itemname");
		items.setText(itm);

		availqty=(TextView)findViewById(R.id.tv_vls_qty1_rosh);
		Intent intent3 = getIntent();
		quant = intent3.getStringExtra("quantity");
		availqty.setText(quant);
		
		receivd=(EditText)findViewById(R.id.edt_vls_rcvdstock_rosh);
		
		mTodayDisplay=(TextView)findViewById(R.id.tv_vls_datercvd_rosh);
		mDateDisplay = (TextView) findViewById(R.id.tv_vls_today_rosh);        
	    mPickDate = (Button) findViewById(R.id.btn_vls_date_rosh);
	    mPickToday=(Button)findViewById(R.id.btn_recivddate_vls_roshn);

	    mPickDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
	    
	    mPickToday.setOnClickListener(new View.OnClickListener() {
			
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
 
        updateDisplay();
        updateTodayDisplay();
        
        update=(Button)findViewById(R.id.btn_vls_stock_rosh);
        update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String availableqty=availqty.getText().toString();
				int availQty=Integer.valueOf(availableqty);
				
				String receivedqty=receivd.getText().toString();
				int getrequestedQty=Integer.valueOf(receivedqty);
				
				StringTokenizer token = new StringTokenizer(mDateDisplay.getText().toString(),"/");
				int Year,Month,Date;
				int [] Res = {1,2,3};
				int x=0;
				
				while(token.hasMoreTokens()){
					Res[x++]=Integer.parseInt(token.nextToken());
				
				}
				
				StringTokenizer token1 = new StringTokenizer(mTodayDisplay.getText().toString(),"/");
				
				int [] ReqDate = {1,2,3};
				int y=0;
				
				while(token1.hasMoreTokens()){
					ReqDate[y++]=Integer.parseInt(token1.nextToken());
				}
				
				if(Res[0]<ReqDate[0] || (Res[0]==ReqDate[0] && Res[1]<ReqDate[1]) || (Res[0]==ReqDate[0] && Res[1]==ReqDate[1] && Res[2]<ReqDate[2]))
				{
					DBCreater update=new DBCreater(ViewLowStock.this);
					update.open();
					
					ContentValues cv = new ContentValues();
					cv.put(DBCreater.Key_delS_available_qty,(availQty+getrequestedQty));
					update.updateDelear("Del_Stock", cv, "delS_del_id=? and delS_del_item_name=?",new String[] {logger.getText().toString(),itm} );
			       	
					update.createLowstockEntry(items.getText().toString(), logger.getText().toString(), availQty, getrequestedQty, mDateDisplay.getText().toString(), mTodayDisplay.getText().toString());
					update.close();
					
					Dialog msg=new Dialog(ViewLowStock.this);
					msg.setTitle("Success Message");
					TextView text=new TextView(ViewLowStock.this);
					text.setText("Successfully updated the stock");
					msg.setContentView(text);
					msg.show();
					
				}
					
				else{
					Dialog d1=new Dialog(ViewLowStock.this);
					d1.setTitle("Error");
					TextView t=new TextView(ViewLowStock.this);
					t.setText("The received date should be greater than or equal to reqested date ");
					d1.setContentView(t);
					d1.show();
									
				}
			}
		});
		
	}
	
	 //On create dialog method called by showDialog()   
		@Override
		  protected Dialog onCreateDialog(int id) {
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
		getMenuInflater().inflate(R.menu.view_low_stock, menu);
		return true;
	}

}
