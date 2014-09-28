package com.NIRR.jenasena;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class RepCustomerHistory extends Activity {
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String package_name = "com.NIRR.jenasena";
	TextView e1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.rep_customer_history);
		Intent intent = getIntent();
		String repname = intent.getStringExtra(DemoCustomerSearch.package_name);
		e1=(TextView)findViewById(R.id.tv_c_repCustomerHistory_cusSearch_a);
		e1.setText(repname);
		
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayListView();
	}
	private void displayListView(){
		
		Cursor cursor = dbHelper.getCusSearchData(e1.getText().toString());
		String[] columns = new String[] {
			DBCreater.Key_customer_CId ,
				
		};
		
		int[] to = new int[]{
				R.id.tv_c_democusSearch_searchRep_a,
					
		};
		dataAdapter = new SimpleCursorAdapter(this, R.layout.democus_search, cursor, columns, to,0);
		final ListView listView = (ListView)findViewById(R.id.lv_c_repCustomerHistory_cusID_a);
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> lidstView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)listView.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String name=cursor.getString(cursor.getColumnIndexOrThrow("cus_id"));
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent(RepCustomerHistory.this,ViewCustomerHistory.class);
				intent.putExtra(package_name, name);
				startActivity(intent);
				
			}
			
		});

	}
	
}


