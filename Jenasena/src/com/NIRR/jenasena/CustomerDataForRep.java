package com.NIRR.jenasena;

import java.util.StringTokenizer;

import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDataForRep extends Activity{
	TextView company;
	EditText owner,city,address,phone,email,smscontener;
	private DBCreater dbHelper;
	ImageView call,send,sms,emailsend,location;
	public static String packagename="com.NIRR.jenasena";
	String id;
//	 LatLng fromcuster = new LatLng(AndroidGPSTrackingActivity.lati,AndroidGPSTrackingActivity.longt);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customerdata);
		Intent intent=getIntent();
	    final String s1=intent.getStringExtra(RepSearchCustomer.packagename);
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
		location=(ImageView)findViewById(R.id.iv_customerdata_map_Isuru);
		location.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			               //check the GPS is Enabled 
			           // Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
			        }else{
			            //IF GPS not working open settings for on the GPS
			              showGPSDisabledAlertToUser();
			        }
			        if(checkInterNet()){
			            //check the DATA/WIFI is Enabled
			// Toast.makeText(this, "Data is Enabled in your devide", Toast.LENGTH_SHORT).show();
			        }
			        else{
			//IF DATA/WIFI not working open settings for on the DATA/WIFI           
			        	showDATADisabledAlertToUser();
			        }
			        if(checkInterNet()&& locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			        	Intent p = new Intent(CustomerDataForRep.this,CustomerLocation.class);
			        	String passer=city.getText().toString();
			        	p.putExtra(packagename, passer);
			        	startActivity(p);
			        }
			}
		});
		emailsend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p = new Intent(CustomerDataForRep.this,Email.class);
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
	 private void showGPSDisabledAlertToUser(){ //Show AlertDialog to help to Enable GPS connection
	        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
	        .setCancelable(false)
	        .setPositiveButton("Goto Settings Page To Enable GPS",
	                new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int id){
	                Intent callGPSSettingIntent = new Intent(
	                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                startActivity(callGPSSettingIntent);
	            }
	        });
	        alertDialogBuilder.setNegativeButton("Cancel",
	                new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int id){
	                dialog.cancel();
	            }
	        });
	        AlertDialog alert = alertDialogBuilder.create();
	        alert.show();
	    }
	    private void showDATADisabledAlertToUser(){
	//Show AlertDialog to help to Enable DATA(3G)/WIFI connection
	         AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
	        alertDialogBuilder.setMessage("DATA is disabled in your device. Would you like to enable it?")
	        .setCancelable(false)
	        .setPositiveButton("Goto Settings Page To Enable DATA",
	                new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int id){
	                Intent callGPSSettingIntent = new Intent(
	                        android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS);
	                startActivity(callGPSSettingIntent);
	            }
	        });
	        alertDialogBuilder.setNegativeButton("Cancel",
	                new DialogInterface.OnClickListener(){
	            public void onClick(DialogInterface dialog, int id){
	                dialog.cancel();
	            }
	        });
	        AlertDialog alert = alertDialogBuilder.create();
	        alert.show();
	    }
	    private boolean checkInterNet(){//CHECK the WIFI/DATA(3G) connection
	         ConnectivityManager connec = (ConnectivityManager) getSystemService(
	                    Context.CONNECTIVITY_SERVICE);
	         NetworkInfo wifi = connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	         NetworkInfo mobile = connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	            // Check if wifi or mobile network is available or not. If any of them is
	            // available or connected then it will return true, otherwise false;
	            return  mobile.isConnected() || wifi.isConnected();
	       
	    }
	
	
}
