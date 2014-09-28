package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Selection extends Activity {

	Button createInvoice,viewOldInvoice;
	String cusid,repid;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selection);
		
		
		createInvoice=(Button)findViewById(R.id.btcreateInvo);
		viewOldInvoice=(Button)findViewById(R.id.btviewoldInvo);
		
		Intent intent = getIntent();
		cusid = intent.getStringExtra("cus_id");
		
		Intent intent2=getIntent();
		repid=intent2.getStringExtra("rep_id");
		
	createInvoice.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent p2 = new Intent(Selection.this,SellToCustomers.class);
				p2.putExtra("cus_id", cusid);
				p2.putExtra("rep_id", repid);
				startActivity(p2);
				
				
				
			}
		});
		
	viewOldInvoice.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent p3=new Intent(Selection.this,FinalizedInvoice.class);
			p3.putExtra("cus_id", cusid);
			p3.putExtra("rep_id", repid);
			startActivity(p3);
			
			
			
		}
	});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.selection, menu);
		return true;
	}

}
