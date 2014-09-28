package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InvoiceHistorySelection extends Activity {
TextView repid;
Button next;
RadioButton cusWise, dateWise, allinvo;
String repID;
RadioGroup rg;
public static String packagename="com.NIRR.redistributionsystem";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoice_history_selection);
		repid=(TextView)findViewById(R.id.tv_invoce_History_Selection_repid);
		
		Intent intent = getIntent();
		repID = intent.getStringExtra(RepMenu.packagename);
		repid.setText(repID);
		
		rg=(RadioGroup)findViewById(R.id.radioGroup1);
		
		
		next=(Button)findViewById(R.id.bt_invoce_History_Selection_next);
		cusWise=(RadioButton)findViewById(R.id.rb_invoce_History_Selection_customer);
		dateWise=(RadioButton)findViewById(R.id.rb_invoce_History_Selection_date);
		allinvo=(RadioButton)findViewById(R.id.rb_invoiceHistry_Selection_all);
		next=(Button)findViewById(R.id.bt_invoce_History_Selection_next);
		
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(cusWise.isChecked() || dateWise.isChecked() || allinvo.isChecked()){
					
					System.out.println("ABC");
					
					if(allinvo.isChecked()){
						Intent n1=new Intent(InvoiceHistorySelection.this,InvoiceHistory.class);
						n1.putExtra("repid", repID);
						startActivity(n1);
						
						
						
					}
					
					if(cusWise.isChecked()){
						Intent n2=new Intent(InvoiceHistorySelection.this,InvoiceHistoryCus.class);
						n2.putExtra("repid", repID);
						startActivity(n2);
						
						
					
					}
					
					if(dateWise.isChecked()){
						
						Intent n3=new Intent(InvoiceHistorySelection.this,InvoiceHisDate.class);
						n3.putExtra("repid", repID);
						startActivity(n3);
						
						
						
					}
					
								
				}
				
				else 
				
				{
					
					
					Dialog d1=new Dialog(InvoiceHistorySelection.this);
					d1.setTitle("Select one option");
					TextView t=new TextView(InvoiceHistorySelection.this);
					t.setText("You have to select a option to proceed");
					d1.setContentView(t);
					d1.show();
					
				}
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.invoice_history_selection, menu);
		return true;
	}

}
