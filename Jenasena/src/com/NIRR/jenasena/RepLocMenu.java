package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class RepLocMenu extends Activity implements OnItemClickListener{

	String[] list = { "Current Location", " History " };
	Integer[] images = { R.drawable.target,R.drawable.stock };
	public static String packagename="com.NIRR.jenasena";
	ListView listview;
	List<Item> rowItems;
	TextView e1;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu); 
		e1=(TextView)findViewById(R.id.tv_menu_logger_Isuru);
		//get the delier id and show in the text
				Intent intent=getIntent();
			    String s1=intent.getStringExtra(LocationViewer.packagename);
			    e1.setText(s1);
			    
			    //get data to the list view
				rowItems = new ArrayList<Item>();
				for (int i = 0; i < list.length; i++) {
					Item item = new Item(images[i], list[i]);
					rowItems.add(item);
				}
				//add the data to the list view and make the listview at the run time
				listview = (ListView) findViewById(R.id.lv_menu_list_Isuru);
				CustomListViewAdapter adapter = new CustomListViewAdapter(this,
						R.layout.menu_item, rowItems);
				
				listview.setAdapter(adapter);
				listview.setOnItemClickListener(this);
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub  
		switch (arg2) {
		
		case 0:
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
	        	Intent p1 = new Intent(RepLocMenu.this, RepCurrentLocation.class);
			p1.putExtra(packagename, e1.getText());
			startActivity(p1);
	        }
			
			break;
		case 1:
			LocationManager locationManagers = (LocationManager) getSystemService(LOCATION_SERVICE);

	        if (locationManagers.isProviderEnabled(LocationManager.GPS_PROVIDER)){
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
	        if(checkInterNet()&& locationManagers.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	        	Intent p2 = new Intent(RepLocMenu.this, ReplocHistory.class);
			Log.e("History pass",e1.getText().toString());
			p2.putExtra(packagename, e1.getText());
			startActivity(p2);
	        }
			
			break;
		}
		
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
