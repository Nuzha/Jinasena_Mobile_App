package com.NIRR.jenasena;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDataForDelear extends Activity{
	TextView company;
	EditText owner,city,address,phone,email,smscontener;
	private DBCreater dbHelper;
	ImageView call,send,sms,emailsend;
	public static String packagename="com.NIRR.jenasena";
	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customerdata);
		Intent intent=getIntent();
	    final String s1=intent.getStringExtra(SearchCustomer.packagename);
	    StringTokenizer token = new StringTokenizer(s1,"*");
		String s2="";
		String s3="";
		final String s4;
		while(token.hasMoreTokens()){
			s2 = token.nextToken();
			s3 = token.nextToken();
		}
		s4=s3;
	    company=(TextView)findViewById(R.id.tv_customerdata_company_Isuru);
	    company.setText(s2);
	    owner=(EditText)findViewById(R.id.et_customerdata_owner_Isuru);
	    city=(EditText)findViewById(R.id.et_customerdata_city_Isuru);
	    address=(EditText)findViewById(R.id.et_customerdata_address_Isuru);
	    phone=(EditText)findViewById(R.id.et_customerdata_phone_Isuru);
	    email=(EditText)findViewById(R.id.et_customerdata_email_Isuru);
	    smscontener=(EditText)findViewById(R.id.et_customerdata_smscontent_Isuru);
	    dbHelper = new DBCreater(this);
		dbHelper.open();
		Cursor mcurser=dbHelper.fetchallcustomerdata(s2);
		id=mcurser.getString(0);
		owner.setText(mcurser.getString(5));
		city.setText(mcurser.getString(1));
		phone.setText(mcurser.getString(2));
		email.setText(mcurser.getString(3));
		address.setText(mcurser.getString(4));
		call=(ImageView)findViewById(R.id.iv_customerdata_call_Isuru);
		send=(ImageView)findViewById(R.id.iv_customerdata_send_Isuru);
		sms=(ImageView)findViewById(R.id.iv_customerdata_sms_Isuru);
		emailsend=(ImageView)findViewById(R.id.iv_customerdata_email_Isuru);
		emailsend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p = new Intent(CustomerDataForDelear.this,Email.class);
				String passer=s4+"*"+email.getText().toString();
				p.putExtra(packagename, passer);
				startActivity(p);
			}
		});
		send.setVisibility(View.INVISIBLE);
		smscontener.setVisibility(View.INVISIBLE);
		sms.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send.setVisibility(View.VISIBLE);
				smscontener.setVisibility(View.VISIBLE);
			}
		});
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phone.getText().toString(), null, smscontener.getText().toString(), null, null);
				send.setVisibility(View.INVISIBLE);
				smscontener.setVisibility(View.INVISIBLE);
				Toast.makeText(getApplicationContext(), " Your text message succsefully send to "+owner.getText().toString()+"", Toast.LENGTH_SHORT).show();
				}
				catch (android.content.ActivityNotFoundException ex) {
			         Toast.makeText(getApplicationContext(), 
			         "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
			      }
			}
		});
		call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phn_no = phone.getText().toString();
				try {
				    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
				    my_callIntent.setData(Uri.parse("tel:"+phn_no));
				    startActivity(my_callIntent);
				} catch (ActivityNotFoundException e) {
				    Toast.makeText(getApplicationContext(), "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
	    
	}

	
}
