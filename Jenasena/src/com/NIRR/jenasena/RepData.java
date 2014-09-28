package com.NIRR.jenasena;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RepData extends Activity{
	TextView company;
	EditText owner,city,address,phone,email,smscontener;
	EditText packet;
	private DBCreater dbHelper;
	ImageView call,send,sms,emailsend;
	public static String packagename="com.NIRR.jenasena";
	String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repdata);
		Intent intent=getIntent();
		final String s1=intent.getStringExtra(SearchRep.packagename);
	    StringTokenizer token = new StringTokenizer(s1,"*");
		String s2="";
		String s3="";
		final String s4;
		while(token.hasMoreTokens()){
			s2 = token.nextToken();
			s3 = token.nextToken();
		}
		s4=s3;
	   company=(TextView)findViewById(R.id.tv_repdata_company_Isuru);
	   company.setText(s2);
	   address=(EditText)findViewById(R.id.et_repdata_address_Isuru);
	   phone=(EditText)findViewById(R.id.et_repdata_phone_Isuru);
	   email=(EditText)findViewById(R.id.et_repdata_email_Isuru);
	   packet=(EditText)findViewById(R.id.editText1);
	    dbHelper = new DBCreater(this);
		dbHelper.open();
		Cursor mcurser=dbHelper.fetchallrepdata(s2);
		id=mcurser.getString(0);
		phone.setText(mcurser.getString(1));
		email.setText(mcurser.getString(2));
		address.setText(mcurser.getString(3));
		call=(ImageView)findViewById(R.id.iv_repdata_call_Isuru);
		send=(ImageView)findViewById(R.id.iv_repdata_send_Isuru);
		sms=(ImageView)findViewById(R.id.iv_repdata_sms_Isuru);
		emailsend=(ImageView)findViewById(R.id.iv_repdata_email_Isuru);
		send.setVisibility(View.INVISIBLE);
		packet.setVisibility(View.INVISIBLE);
		sms.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				send.setVisibility(View.VISIBLE);
				packet.setVisibility(View.VISIBLE);
			}
		});
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
				SmsManager smsManager = SmsManager.getDefault();
				smsManager.sendTextMessage(phone.getText().toString(), null, packet.getText().toString(), null, null);
				send.setVisibility(View.INVISIBLE);
				packet.setVisibility(View.INVISIBLE);
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
		emailsend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p = new Intent(RepData.this,Email.class);
				String passer=s4+"*"+email.getText().toString();
				p.putExtra(packagename, passer);
				startActivity(p);
			}
		});
		
	}
	
	

}
