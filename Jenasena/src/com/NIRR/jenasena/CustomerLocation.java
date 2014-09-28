package com.NIRR.jenasena;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;

import com.google.android.gms.internal.in;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CustomerLocation extends Activity{

		private GoogleMap googleMap;
		MarkerOptions markerOptions;
		String s1;
		LatLng fromlatlng;
		private Double latitude1;
		private Double longitude1;
		private BufferedReader in;
		LatLng toPosition ; 
		
		
		public static double longt;
		public static double lati;

		
		
		
		// GPSTracker class
		GPSTracker gps;
		
		GoogleMap mMap;
	    GMapV2Direction md;
	    Button getloc,drawloc;
	    LatLng fromPosition ;
	
	    // thread newww
	
	private class DoAsyncTask extends AsyncTask<URL, Integer, Long> {///Constructor

		String lat,lon,locName,temp;
		
		protected Long doInBackground(URL... params) {//callbackmethod
	
	
		// TODO Auto-generated method stub

		String addressUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
		                + locName
		                + "&sensor=true";
		try {
			
		            HttpClient httpClient = new DefaultHttpClient();// Client

		            HttpGet getRequest = new HttpGet();
		            getRequest.setURI(new URI(addressUrl));

		            // HttpPost postRequest = new HttpPost();
		            // postRequest.setURI(new URI(url));

		            HttpResponse response = httpClient.execute(getRequest);
		            in = new BufferedReader(new InputStreamReader(response
		                    .getEntity().getContent()));
		            StringBuffer sb = new StringBuffer("");
		            String line = "";
		            String NL = System.getProperty("line.separator");
		            while ((line = in.readLine()) != null) {
		                sb.append(line + NL);
		            }
		            in.close();
		            String page = sb.toString();
		            
		            //mylon.setText(page);
		            JSONObject jsonObject = new JSONObject(page);
		            if (jsonObject.has("results")) {
		                JSONArray jsonArray = (JSONArray) jsonObject.get("results");
		                //mylon.setText(locName);

		                if (jsonArray.length() > 0) {
		                    jsonObject = (JSONObject) jsonArray.get(0);
		                    if (jsonObject.has("geometry")) {
		                        jsonObject = (JSONObject) jsonObject
		                                .get("geometry");

		                        if (jsonObject.has("location")) {
		                            JSONObject location = (JSONObject) jsonObject
		                                    .get("location");

		                            latitude1 = (Double) location.get("lat");
		                            longitude1 = (Double) location.get("lng");
		                          			                            
		                        }
		                    }
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		          
		        } finally {
		            if (in != null) {
		                try {
		                    in.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        }

		return null;
	}

	@Override
	protected void onPostExecute(Long result) {
		// TODO Auto-generated method stub
		
		//interact with gui only here#######################################
		Log.e(locName,latitude1+""+longitude1);
		toPosition = new LatLng(latitude1,longitude1);
		
		
		draw();
	}

	@Override
	protected void onPreExecute() {
		
		// TODO Auto-generated method stub
				
		locName=s1;
			Log.e("Name", locName);
	}
     
}
	
	
	
	
	
	
	
	public void draw()
	{
		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Start"));
		mMap.addMarker(new MarkerOptions().position(toPosition).title("End"));
		
		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
		

		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
		
		for(int i = 0 ; i < directionPoint.size() ; i++) {			
			rectLine.add(directionPoint.get(i));
			//directionPoint the array returned by the decodePoly method are stored 
		}
		
		mMap.addPolyline(rectLine);
	}
	
	
	

	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customerlocation);
		Intent intent=getIntent();
		 s1=intent.getStringExtra(CustomerDataForRep.packagename);
		Log.e("i'm look ing @2",s1);
		
		getloc=(Button)findViewById(R.id.getlock);
		drawloc=(Button)findViewById(R.id.drawlock);
		
		Log.e("i'm look ing @2","ggggggggg");
		 md = new GMapV2Direction();
		 //load the default map to web view
			mMap = ((MapFragment)getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
			
			Log.e("i'm look ing @2","aaaaaaaaaaaaaa");
			
			
		//final LatLng coordinates = new LatLng(6.9036, 79.9547);
			
			
			
			
			getloc.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// create class object  
			        gps = new GPSTracker(CustomerLocation.this);

					// check if GPS enabled	  	
			        if(gps.canGetLocation()){
			        	
			        	double latitude = gps.getLatitude();
			        	double longitude = gps.getLongitude();
			        	
			        	longt = longitude;
			        	lati = latitude;
			        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
			        	
			        	fromPosition=new LatLng(latitude,longitude);
			        	mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(fromPosition, 16));
			        	mMap.setMyLocationEnabled(true);
			        }else{
			        	// can't get location
			        	// GPS or Network is not enabled
			        	// Ask user to enable GPS/network in settings
			        	gps.showSettingsAlert();
			        }
					
				}
			});
			 
					
			drawloc.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new DoAsyncTask().execute();
				}
			});
			
			
			

		
		
		
	}
	
	
}
