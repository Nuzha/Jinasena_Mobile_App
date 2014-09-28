package com.NIRR.jenasena;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.widget.SimpleCursorAdapter;

public class View_Assigned_Stock extends Activity {
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String packagename="com.NIRR.redistributionsystem";
	
	TextView text;
	Button bm;
	TextView normaltitle;
	 private int pYear;
	 private int pMonth;
	 private int pDay;
	 
	 String todayDate;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__assigned__stock);
		
		text=(TextView) findViewById(R.id.tvrEp);
		Intent intent = getIntent();
		String s1 = intent.getStringExtra(RepMenu.packagename);
		text.setText(s1);
		
		
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
       
		bm=(Button)findViewById(R.id.bVrEp);
		normaltitle=(TextView)findViewById(R.id.normalTitle);
		
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayDailyAssignedStock();
		
		//fillmydb();
		
		bm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p2 = new Intent(View_Assigned_Stock.this, AssigntoCustomers.class);
				startActivity(p2);
				
				
			}
		});
		

		
	}
	
	
private void displayDailyAssignedStock() {
		
		Cursor cursor = dbHelper.fetchrelatedinfo((String) text.getText(),todayDate);
		
		if(cursor.getCount()==0){
			
			 
			Dialog d1=new Dialog(View_Assigned_Stock.this);
			d1.setTitle("No items to sell" );
			TextView t=new TextView(View_Assigned_Stock.this);
			t.setText("You don't have items to sell today");
			d1.setContentView(t);
			d1.show();
			
			//Toast.makeText(getApplicationContext(), "No items to be sold today ", Toast.LENGTH_LONG).show();
			
			
		}
		
		else if(cursor!=null)
		{
		System.out.println("Not null");
		String[] columns = new String[] { 
			DBCreater.Key_repS_item_name,
			DBCreater.Key_repS_assigned_qty
		};
			
	
		int[] to = new int[]{
				R.id.srItemName,
				R.id.srQuantity
			
		};
		
		dataAdapter = new SimpleCursorAdapter(this, R.layout.singleraw, cursor, columns, to,0);
		final ListView LoadListView = (ListView)findViewById(R.id.lvVAS);
		LoadListView.setAdapter(dataAdapter);
		LoadListView.setOnItemClickListener(new OnItemClickListener() {

			
		
		

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)LoadListView.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String name=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				
				
			}
			
		});

		
		}
		
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__assigned__stock, menu);
		return true;
	}

}
