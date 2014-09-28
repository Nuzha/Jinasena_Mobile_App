package com.NIRR.jenasena;

import java.nio.channels.AlreadyConnectedException;
import java.util.Calendar;

import android.R.color;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CheckLowStocks extends Activity {
	
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	
	TextView logger,today,content;
	String usr,scontent="";
	Button date,stock,email;
	
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	private int mYear;
	private int mMonth;
	private int mDay;
		    
	static final int DATE_DIALOG_ID = 0;
	int count=0;
	
	private DatePickerDialog.OnDateSetListener pDateSetListner = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			mYear=year;
			mMonth=monthOfYear;
			mDay=dayOfMonth;
			
			if(count==1){
	             updateDisplay();
	          }
	                    
	          else{
	        	  
	          }
			
		}
	};
	
	public void updateDisplay(){
		today.setText(
		         new StringBuilder()
		                                  // Month is 0 based so add 1
		         	.append(mYear).append("/")        
		            .append(mMonth + 1).append("/")
		            .append(mDay).append("")
		             );
		//displayLowStocks();
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_low_stocks);
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();  
	      
		logger=(TextView)findViewById(R.id.tv_cls_logger_rosh);
		Intent intent1=getIntent();
		usr=intent1.getStringExtra(DelearMenu.packagename);
		logger.setText(usr);
		
			
		today=(TextView)findViewById(R.id.tv_cls_viewdate_rosh);
		date=(Button)findViewById(R.id.btn_cls_date_rosh);
		
		date.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		
		//Get the current date
		final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        
        updateDisplay();
        displayLowStocks();
        
        
        stock=(Button)findViewById(R.id.btn_cls_display_rosh);
        stock.setBackgroundColor(color.transparent);
        stock.setOnClickListener(new View.OnClickListener() {
        	
			@Override
			public void onClick(View arg0) {
				displayLowStocks();
				
			}
		});
        
        
        email=(Button)findViewById(R.id.btn_cls_reorder_rosh);
        email.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent2=new Intent(CheckLowStocks.this,SendEmail.class);
				intent2.putExtra("loggeduser", logger.getText().toString());
				intent2.putExtra("content", content.getText().toString());
				startActivity(intent2);
				
			}
		});
        
        

	}
	
	
	//On create dialog method called by showDialog()   
		@Override
		  protected Dialog onCreateDialog(int id) {
		        switch (id) {
		        case DATE_DIALOG_ID:
		            return new DatePickerDialog(this,pDateSetListner,mYear, mMonth, mDay); 
		                   
		        }
		        return null;
		    }
		
		
		public void displayLowStocks(){
			
			Cursor cursor=dbHelper.getlowstocks(logger.getText().toString());
			final ListView lv_stock=(ListView)findViewById(R.id.lv_cls_displayresult_rosh);
			String[] columns = new String[] {
			        DBCreater.Key_delS_del_item_name,
			        DBCreater.Key_delS_available_qty
			};
			
			int[] to = new int[]{
		            R.id.tv_sl_itemname_rosh,
		            R.id.tv_sl_qty_rosh
			};
			
			
			content=(TextView)findViewById(R.id.tv_intent_rosh);
			
			
			
			if(cursor.getCount()!=0){
				if(cursor.moveToFirst()) {
					do{
					String itmname=cursor.getString(cursor.getColumnIndexOrThrow("delS_del_item_name"));
					String qty=cursor.getString(cursor.getColumnIndexOrThrow("delS_available_qty"));
					
					
					scontent=scontent+(itmname+" remaining quantity has reached to the re-order level. Qty: "+qty+"\n");
					content.setText(scontent);
					}while(cursor.moveToNext());
				}
			}
			
			   
			content.setVisibility(View.INVISIBLE);
			
			dataAdapter=new SimpleCursorAdapter(this, R.layout.single_lowstock, cursor, columns, to, 0);
			lv_stock.setAdapter(dataAdapter);

			
			lv_stock.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View view,int position, long id) {
					Cursor cursor =(Cursor)lv_stock.getItemAtPosition(position);
					String itmname=cursor.getString(cursor.getColumnIndexOrThrow("delS_del_item_name"));
					String qty=cursor.getString(cursor.getColumnIndexOrThrow("delS_available_qty"));
										
					String did=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
					Toast.makeText(getApplicationContext(),"To update the stock", Toast.LENGTH_SHORT).show();
					
					
										           
					switch(position){
					   		
					  default:
					  Intent p2 = new Intent(CheckLowStocks.this,ViewLowStock.class);
					  p2.putExtra("user", logger.getText().toString());
					  p2.putExtra("itemname", itmname);
					  p2.putExtra("quantity", qty);
					  p2.putExtra("id", id);
					  startActivity(p2);
					  break;
					  }
					}

				});
			
			if(cursor.getCount()==0)
			{
				AlertDialog alertdlg=new AlertDialog.Builder(CheckLowStocks.this).create();
				alertdlg.setTitle("No low stocks");
				alertdlg.setMessage("There isn't any low stock items to re-order");
				alertdlg.setButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				alertdlg.show();
						
			}
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_low_stocks, menu);
		return true;
	}

}
