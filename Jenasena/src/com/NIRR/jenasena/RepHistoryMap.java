package com.NIRR.jenasena;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.StringTokenizer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

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
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class RepHistoryMap extends Activity{

	public static String packagename="package com.NIRR.jenasena";
	String s1="Nattandiya",locName;
	SharedPreferences sharedPreferences;
	String s2,s3;
	int locationCount;
	GPSTracker gps;
	GoogleMap mMap;
    GMapV2Direction md;
    LatLng fromlatlng;
    LatLng toPosition ;
	private Double latitude1;
	private Double longitude1;
	private BufferedReader in;
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
		 s1=intent.getStringExtra(ReplocHistory.packagename);
		 Log.e("getter",""+s1);
		 StringTokenizer token = new StringTokenizer(s1,"*");
			
			while(token.hasMoreTokens()){
					s2 = token.nextToken();
					s3=token.nextToken();
				}
			Log.e("s2",s2);
			Log.e("s3",s3);
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
					Cursor mapcur=dbHelper.getRephistorylocationMap(s2,s3);
					Log.e("count", ""+mapcur.getCount());
					Log.e("count full",""+dbHelper.countGetloc());
					while (!mapcur.isAfterLast()) {
						String rid=mapcur.getString(0);
						String time=mapcur.getString(3);
						String val =mapcur.getColumnName(4);
						Log.e("val",val);
						String date=mapcur.getString(4);
						Log.e("date",date);
						int clock = Integer.parseInt(time);
						double maplan=mapcur.getDouble(2);
						double maplat=mapcur.getDouble(1);
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
