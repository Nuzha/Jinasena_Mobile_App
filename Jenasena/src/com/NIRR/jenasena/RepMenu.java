package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.games.internal.constants.AvailabilityCode;

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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RepMenu extends Activity implements OnItemClickListener{
	 
	
	String[] list = {  " View Assigned Stock ", " View Customer " ,"View Available Stock", " Make Invoice " ,"Manage Payments" ,"Invoice History","Faulty Items" , " Report " , " Charts ", "Customer History" , " Due customer Notification ", " settings ", "Monthly Notification","Todo List"};
	Integer[] images = { R.drawable.viewtarget,R.drawable.man,R.drawable.stocksavail,R.drawable.makeinvoices,R.drawable.managepayments,R.drawable.historyicon,R.drawable.faultyitem,R.drawable.reportss,R.drawable.charts,R.drawable.customerhistory,R.drawable.notice,R.drawable.settings,R.drawable.notice,R.drawable.note };
	ListView listview;
	List<Item> rowItems;
	TextView e1;
	public static String packagename="com.NIRR.jenasena";
	public DBCreater dbHelper;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		Log.e("a","1");
		dbHelper = new DBCreater(this);
		Log.e("a","1");
		  LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

	        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
	               //check the GPS is Enabled 
	            Toast.makeText(this, "GPS is Enabled in your devide", Toast.LENGTH_SHORT).show();
	        }else{
	            //IF GPS not working open settings for on the GPS
	              showGPSDisabledAlertToUser();
	        }
	        if(checkInterNet()){
	            //check the DATA/WIFI is Enabled
	 Toast.makeText(this, "Data is Enabled in your devide", Toast.LENGTH_SHORT).show();
	        }
	        else{
	//IF DATA/WIFI not working open settings for on the DATA/WIFI           
	        	showDATADisabledAlertToUser();
	        }
		dbHelper.openforread();
		if(dbHelper.countTrackloc()!=0){
			Log.e("a","1");
			dbHelper.cleantrackrep();}
		e1=(TextView)findViewById(R.id.tv_menu_logger_Isuru);
		//get the delier id and show in the text
				Intent intent=getIntent();
			    String s1=intent.getStringExtra(MainActivity.packagename);
			    e1.setText(s1);
			    Log.e("a","1");
			    dbHelper.trackrep(s1);
			    Log.e("a","1");
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
				//////
				startService(new Intent(RepMenu.this,RepLocBackgroundService.class));
		        Intent startMain = new Intent(Intent.ACTION_MAIN);
		        startMain.addCategory(Intent.CATEGORY_HOME);
		        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		        RepMenu.this.startService(startMain);
				
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (arg2) {
		
		
		case 0:
			Intent p1 = new Intent(RepMenu.this, View_Assigned_Stock.class);
			p1.putExtra(packagename, e1.getText());
			startActivity(p1);
			break;
		
		case 1:
			Intent p2 = new Intent(RepMenu.this, RepSearchCustomer.class);
			p2.putExtra(packagename, e1.getText());
			startActivity(p2);
			break;
			
		case 2:
			Intent p3 = new Intent(RepMenu.this, ViewAvailableStock.class);
			p3.putExtra(packagename, e1.getText());
		    startActivity(p3);
			break;
			
			
		case 3:
			Intent p4 = new Intent(RepMenu.this, CustomersAcdnToRep.class);
			p4.putExtra(packagename, e1.getText());
		    startActivity(p4);
			break;
			
		case 4:
			Intent p5= new Intent(RepMenu.this, CustomersToManagePayments.class);
			p5.putExtra(packagename, e1.getText());
			startActivity(p5);
			break;
			
		
		
		case 5:
			Intent p6= new Intent(RepMenu.this, InvoiceHistorySelection.class);
			p6.putExtra(packagename, e1.getText());
			startActivity(p6);
			break;
		
		case 6:
			Intent p7= new Intent(RepMenu.this, ReturnItems.class);
			p7.putExtra(packagename, e1.getText());
			startActivity(p7);
			break;
			
			
			
		case 7:
			Intent p8= new Intent(RepMenu.this, Report.class);
			p8.putExtra(packagename, e1.getText());
			startActivity(p8);
			break;
		case 8:
			Intent p9 = new Intent(RepMenu.this, RepChartMenu.class);
			p9.putExtra(packagename, e1.getText());
			startActivity(p9);
			break;
		case 11:
			Intent p10 = new Intent(RepMenu.this, BackupSettingList.class);
			p10.putExtra(packagename, e1.getText());
			startActivity(p10);
			break;
		case 9:
			Intent p11 = new Intent(RepMenu.this, RepCustomerHistory.class);
			p11.putExtra(packagename, e1.getText());
			startActivity(p11);
			break;
		case 10:
			Intent p12 = new Intent(RepMenu.this, UpdaterServiceManager1.class);
			p12.putExtra(packagename, e1.getText());
			startService(p12);
			sendBroadcast(p12);
			break; 
		case 12:
			Intent p13 = new Intent(RepMenu.this, UpdaterServiceManager2.class);
			p13.putExtra(packagename, e1.getText());
			startService(p13);
			sendBroadcast(p13);
			break; 
			
		case 13:
			Intent p14 = new Intent(RepMenu.this, Main_todo.class);
			p14.putExtra(packagename, e1.getText());
			startActivity(p14);
			break; 
		
		}
		
	}
}
