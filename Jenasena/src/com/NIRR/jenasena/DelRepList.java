package com.NIRR.jenasena;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DelRepList extends Activity{

	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String packagename = "com.NIRR.jenasena";
	TextView e1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_history);
		Intent intent = getIntent();
		String repname = intent.getStringExtra(DelearMenu.packagename);
		e1=(TextView)findViewById(R.id.tv_c_customerhistory_loginId_d);
		e1.setText(repname);
		
		dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayListView();
	}
	private void displayListView(){
		
		Cursor cursor = dbHelper.getRepSearchData(e1.getText().toString());
		String[] columns = new String[] {
			DBCreater.Key_rep_RId ,
				
		};
		
		int[] to = new int[]{
				R.id.tv_c_democusSearch_searchRep_a,
					
		};
		dataAdapter = new SimpleCursorAdapter(this, R.layout.democus_search, cursor, columns, to,0);
		final ListView listView = (ListView)findViewById(R.id.lv_c_customerhistory_cusSearch_d);
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> lidstView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)listView.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String name=cursor.getString(cursor.getColumnIndexOrThrow("rep_id"));
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				
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
		        	Intent intent = new Intent(DelRepList.this,DelCusViewer.class);
				intent.putExtra(packagename, name);
				startActivity(intent);
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
