package com.NIRR.jenasena;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class ViewAvailableStock extends Activity {
	TextView repid;
	String repString;
	String todayDate;
	
	private int pYear;
	private int pMonth;
	private int pDay;

	private SimpleCursorAdapter dataAdapter;
    private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;
	public static String packagename="com.NIRR.redistributionsystem";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_available_stock);
		repid=(TextView)findViewById(R.id.tv_repid_View_Available_Stock);
		
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
      
		
		
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayAvailQty();
		
		
	}

	private void displayAvailQty() {
		Cursor cursor = dbHelper.fetchAvailqty(repString,todayDate);
		
		
		if(cursor.getCount()==0){
			
			 
			Dialog d1=new Dialog(ViewAvailableStock.this);
			d1.setTitle("No items to sell" );
			TextView t=new TextView(ViewAvailableStock.this);
			t.setText("You don't have items to sell today");
			d1.setContentView(t);
			d1.show();
			
			//Toast.makeText(getApplicationContext(), "No items to be sold today ", Toast.LENGTH_LONG).show();
			
			
		}
		
		
		
		
		else if(cursor!=null){
		String[] columns = new String[] { 
				DBCreater.Key_repS_item_name,
				DBCreater.Key_repS_assigned_qty,
				DBCreater.Key_repS_available_qty
				
				//DBCreater.Key_repS_assigned_qty 
		};
		
		int[] to = new int[]{
				R.id.tv_itemName_singleraw_available_stock,
				R.id.tv_assignedQty_singleraw_available_stock,
				R.id.tv_availQty_singleraw_available_stock
			
		};	
		
		
		
		dataAdapter = new SimpleCursorAdapter(this, R.layout.single_raw_avail_stock, cursor, columns, to,0);
		final ListView LoadListView = (ListView)findViewById(R.id.lv_view_available_stock);
		LoadListView.setAdapter(dataAdapter);
	}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_available_stock, menu);
		return true;
	}

}
