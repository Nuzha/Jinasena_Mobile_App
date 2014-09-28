package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomersToManagePayments extends Activity {

	TextView CATRrepId;
	String s1;
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	//ListView CATRcusname;
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customers_to_manage_payments);
		
         CATRrepId  =(TextView)findViewById(R.id.tv_repid_CusToManagePay);
		
		
		Intent intent = getIntent();
		s1 = intent.getStringExtra(RepMenu.packagename);
		CATRrepId.setText(s1);
		
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayCusNames();
	}
	
	
	private void displayCusNames() {
		Cursor cursor = dbHelper.fetchcusNames((String)CATRrepId.getText());
		String[] columns = new String[] { 
				DBCreater.Key_customer_CId,
				DBCreater.Key_customer_Name
				
				//DBCreater.Key_repS_assigned_qty 
		};
		
		int[] to = new int[]{
				R.id.tvcartSRcusid,
				R.id.tvcartSRcusname
			
		};
		
		dataAdapter1 = new SimpleCursorAdapter(this, R.layout.catrsingleraw, cursor, columns, to,0);
		final ListView cusIDs = (ListView)findViewById(R.id.lv_cusId_CusToManagePay);
		cusIDs.setAdapter(dataAdapter1);
		cusIDs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				Cursor cursor =(Cursor)cusIDs.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String cusid=cursor.getString(cursor.getColumnIndexOrThrow("cus_id"));
				Toast.makeText(getApplicationContext(), cusid, Toast.LENGTH_SHORT).show();
				
				
				switch (position) {
				case 0 :
					Intent p2 = new Intent(CustomersToManagePayments.this,ManageCustomerPayments.class);
					p2.putExtra("cus_id", cusid);
					p2.putExtra("rep_id", s1);
					startActivity(p2);
					break;
					
					

				default:
					Intent p3 = new Intent(CustomersToManagePayments.this,ManageCustomerPayments.class);
					p3.putExtra("cus_id", cusid);
					p3.putExtra("rep_id", s1);
					startActivity(p3);
					break;
				}
				
			}
		});
		
	}

	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customers_to_manage_payments, menu);
		return true;
	}

}
