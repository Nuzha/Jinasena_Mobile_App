package com.NIRR.jenasena;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class InvoiceHistoryCus extends Activity implements  OnItemSelectedListener {

	
	TextView repId;
	Spinner customers;
	String repID,cus;
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoice_history_cus);
		customers=(Spinner)findViewById(R.id.sp_invoiceHisCus_customerId);
		customers.setOnItemSelectedListener(this);
		
		repId=(TextView)findViewById(R.id.tv_invoiceHisCus_repid);
		
		Intent intent = getIntent();
		repID = intent.getStringExtra("repid");
		repId.setText(repID);
		
		loadCustomers();
		
		
	}

	private void loadCustomers() {
		DBCreater entry = new DBCreater(InvoiceHistoryCus.this);
		entry.openforread();
		List<String> lables = entry.loadCusId(repID);
		
		
		if(lables.size()==0){
			
			 
			Dialog d1=new Dialog(InvoiceHistoryCus.this);
			d1.setTitle("No customers" );
			TextView t=new TextView(InvoiceHistoryCus.this);
			t.setText("No customers under this Sales Representative");
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
	        customers.setAdapter(dataAdapter);
	        entry.close();
			}
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.invoice_history_cus, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		
		System.out.println("Onitem Click before parent statemnt  "+ cus);
		cus=parent.getItemAtPosition(position).toString();
		
		 System.out.println("Onitem Click after parent statemnt  "+ cus);
			//Showing selected sprinner item
		Toast.makeText(parent.getContext(), "You selected:"+cus , Toast.LENGTH_LONG).show();
			
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
	    diplayInvoices();
	    //entry.close();
		
	}

	private void diplayInvoices() {
		Cursor cursor = dbHelper.InvoiceDetailsGivenCus(cus,repID);
		if(cursor.getCount()==0){
			
	  
			
	    Dialog d1=new Dialog(InvoiceHistoryCus.this);
		d1.setTitle("No Records");
		TextView t=new TextView(InvoiceHistoryCus.this);
		t.setText("There are no bills invoiced to this customer ");
		d1.setContentView(t);
		d1.show();
		
		
	}
		
		else{
			String[] columns = new String[] { 
					DBCreater.Key_Invoice_cust_id,
					DBCreater.Key_Invoice_invoice_id,
					DBCreater.Key_Invoice_selling_date,
					DBCreater.Key_Invoice_post_dicount_amount,
					DBCreater.Key_Invoice_paid_cost,
					DBCreater.Key_Invoice_amount_to_be_paid
					
				
			};
			
			int[] to = new int[]{
					R.id.tv_cusID_invoiceSingle,
					R.id.tv_invoice_id_invoiceSingle,
					R.id.tv_invoiced_date_invoiceSingle,
					R.id.tv_total_amount_invoiceSingle,
					R.id.tv_paid_amount_invoiceSingle,
					R.id.tv_amountTobePaid_invoiceSingle
				
			};
			
			dataAdapter1 = new SimpleCursorAdapter(this, R.layout.invosingle, cursor, columns, to,0);
			final ListView lvinvoiceHis = (ListView)findViewById(R.id.lvInvoHistoryCus);
			lvinvoiceHis.setAdapter(dataAdapter1);
			lvinvoiceHis.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					
				}
			});

		
		
		
		
		
		
	}
}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
