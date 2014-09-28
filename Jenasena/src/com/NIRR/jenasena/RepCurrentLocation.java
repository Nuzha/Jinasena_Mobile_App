package com.NIRR.jenasena;

import java.io.BufferedReader;
import java.util.Calendar;
import java.util.StringTokenizer;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RepCurrentLocation extends Activity{
	String s1="Nattandiya",locName;
	SharedPreferences sharedPreferences;
	String today;
	int locationCount;
	GPSTracker gps;
	GoogleMap mMap;
    GMapV2Direction md;
    LatLng fromlatlng;
    LatLng toPosition ;
	private Double latitude1;
	private Double longitude1;
	private BufferedReader in;
	 int mYear;
	 int mMonth;
	 int mDay;
	 int time;
	Button view;
	Double []latarr;
	Double []lanarr;
	public DBCreater dbHelper;
	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rephistorymap);
		view=(Button)findViewById(R.id.button1);
		Intent intent=getIntent();
		
		 dbHelper = new DBCreater(this);  
		 md = new GMapV2Direction();
		 //load the default map to web view
			mMap = ((MapFragment)getFragmentManager().findFragmentById(
                   R.id.map)).getMap();
			view.setVisibility(View.INVISIBLE);
		//	new DoAsyncTask().execute();
			view.setVisibility(View.VISIBLE);
			view.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dbHelper.open();
					Cursor mapcur=dbHelper.getRepCurrentlocationMap(today);
					Log.e("count", ""+mapcur.getCount());
					Log.e("count full",""+dbHelper.countGetloc());
					final Calendar cal = Calendar.getInstance();
		            mYear = cal.get(Calendar.YEAR);
		            mMonth = cal.get(Calendar.MONTH)+1;
		            mDay = cal.get(Calendar.DAY_OF_MONTH);
		            today=mYear+"/"+mMonth+"/"+mDay;
					while (!mapcur.isAfterLast()) {
						String rid=mapcur.getString(3);
						String time=mapcur.getString(4);
						int clock = Integer.parseInt(time);
						double maplan=mapcur.getDouble(1);
						double maplat=mapcur.getDouble(0);
						toPosition = new LatLng(maplat,maplan);
						Log.e("before",""+clock);
						if(clock>12){
							clock=clock-12;
							time=clock+"PM";
							Log.e("more12",time);
						}
						else if(clock<=12){
							time=clock+"AM";
							Log.e("less12",time);
						}
						mMap.moveCamera(CameraUpdateFactory.newLatLng(toPosition));
						mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
						mMap.addMarker(new MarkerOptions().position(toPosition).title(rid+" : "+time));
						mapcur.moveToNext();
					}
					
				}
			});
	}
	
	
	

}