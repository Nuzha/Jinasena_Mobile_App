package com.NIRR.jenasena;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewCustomerHistory extends Activity{
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String package_name = "com.NIRR.jenasena";
	TextView e1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_customer_history);
		Intent intent = getIntent();
		String cusname = intent.getStringExtra(RepCustomerHistory.package_name);
		e1=(TextView)findViewById(R.id.textView2);
		e1.setText(cusname);
		
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayListView();
	}
	private void displayListView(){
		
		Cursor cursor = dbHelper.getCusData(e1.getText().toString());
		String[] columns = new String[] {
			DBCreater.Key_Invoice_post_dicount_amount ,
			DBCreater.Key_Invoice_due_date,
			DBCreater.Key_Invoice_selling_date,
			DBCreater.Key_Invoice_paid_date,
			
		};
		
		int[] to = new int[]{
				R.id.textView4,
				R.id.textView6,
				R.id.textView8,
				R.id.textView10, 
				
		};
		dataAdapter = new SimpleCursorAdapter(this, R.layout.view_customer_history, cursor, columns, to,0);
		final ListView listView = (ListView)findViewById(R.id.listView1);
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> lidstView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)listView.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String name=cursor.getString(cursor.getColumnIndexOrThrow("Invoice_due_date"));
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
					
			}
			
		});
		
	}

}
