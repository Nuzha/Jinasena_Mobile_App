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
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class DelCusViewer  extends Activity{

	String s1="Nattandiya",locName;
	SharedPreferences sharedPreferences;
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
	
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delcusview);
		view=(Button)findViewById(R.id.cusviewer);
		Intent intent=getIntent();
		 s1=intent.getStringExtra(DelRepList.packagename);
		 Log.e("getter",s1);
		 dbHelper = new DBCreater(this);
		 md = new GMapV2Direction();
		 //load the default map to web view
			mMap = ((MapFragment)getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
			view.setVisibility(View.INVISIBLE);
			new DoAsyncTask().execute();
			view.setVisibility(View.INVISIBLE);
			view.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dbHelper.open();
					Cursor mapcur=dbHelper.getmappoint(s1);
					while (!mapcur.isAfterLast()) {
						String shop=mapcur.getString(2);
						double maplan=mapcur.getDouble(3);
						double maplat=mapcur.getDouble(4);
						toPosition = new LatLng(maplat,maplan);
						mMap.addMarker(new MarkerOptions().position(toPosition).title(shop));
						mapcur.moveToNext();
					}
					
				}
			});
	}

	private class DoAsyncTask extends AsyncTask<URL, Integer, Long> {

		String lat,lon,locName,temp;
		int counter=0;
		String []a={"Nattandiya","Colombo"};
		
		
		
		protected Long doInBackground(URL... params) {
	
			 
				dbHelper.open();
				Cursor mcurser=dbHelper.getToMap();
				dbHelper.cleanMap();
				int count=mcurser.getCount();
				String []city = null;
				int num=0;
				Log.e("xxx", "come");
				while(!mcurser.isAfterLast()){
					String acity=mcurser.getString(2);
					Log.e("location",mcurser.getString(2));
					Log.e("id",mcurser.getString(0));
					Log.e("name",mcurser.getString(1));
					String cusid=mcurser.getString(0);
                    String cusshop=mcurser.getString(1);
					Log.e("xxx", "in");
					//city[num]=mcurser.getString(2);
					mcurser.moveToNext();
					num=num+1;
				
		// TODO Auto-generated method stub
		
				Log.e("arr city name", acity);
		String addressUrl = "http://maps.googleapis.com/maps/api/geocode/json?address="
		                + acity//locName
		                + "&sensor=true";
		
		try {
			        Log.e("try", ""+"a");
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
		            	//Log.e("while", ""+"a");
		                sb.append(line + NL);
		            }
		            in.close();
		            String page = sb.toString();
		            
		            //mylon.setText(page);
		            JSONObject jsonObject = new JSONObject(page);
		            if (jsonObject.has("results")) {
		                JSONArray jsonArray = (JSONArray) jsonObject.get("results");
		                //mylon.setText(locName);
		                Log.e("if1", ""+"a");

		                if (jsonArray.length() > 0) {
		                    jsonObject = (JSONObject) jsonArray.get(0);
		                    Log.e("if2", ""+"a");
		                    if (jsonObject.has("geometry")) {
		                        jsonObject = (JSONObject) jsonObject
		                                .get("geometry");
		                        Log.e("if3", ""+"a");

		                        if (jsonObject.has("location")) {
		                            JSONObject location = (JSONObject) jsonObject
		                                    .get("location");
		                            Log.e("if4", ""+"a");
		                        //    locationCount++;
		                          //  SharedPreferences.Editor editor = sharedPreferences.edit();
		                          //  editor.putString("lat"+ Integer.toString((locationCount-1)), Double.toString(toPosition.latitude));
		             
		                            // Storing the longitude for the i-th location
		                           // editor.putString("lng"+ Integer.toString((locationCount-1)), Double.toString(toPosition.longitude));
		             
		                            // Storing the count of locations or marker count
		                          //  editor.putInt("locationCount", locationCount);
		             
		                            /** Saving the values stored in the shared preferences */
		                           // editor.commit();
		                          //  Log.e("feed",""+locationCount);
		                            int mapid=dbHelper.Mapid();
		                            Log.e("start maptable",""+dbHelper.Mapid());
		                            mapid=mapid+1;
		                            
		                            dbHelper.Putmap(mapid, cusid, cusshop, (Double) location.get("lng"), (Double) location.get("lat"));
		                            Log.e("End maptable",""+dbHelper.Mapid());
		                            Log.e("get city lat", ""+(Double) location.get("lat"));	
		                            Log.e("get city lan", ""+(Double) location.get("lng"));
		                            latitude1 = (Double) location.get("lat");
		                            longitude1 = (Double) location.get("lng");
		                            Log.e("get city lat", ""+(Double) location.get("lat"));	
		                            Log.e("get city lan", ""+(Double) location.get("lng"));
	
		                          //  latarr[st] = (Double) location.get("lat");
		                           // lanarr[st] = (Double) location.get("lng");
		                         // Storing the latitude for the i-th location
		                           
		             
		                          
		                            
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
			}

		return null;
	}

	@Override
	protected void onPostExecute(Long result) {
		// TODO Auto-generated method stub
		
		//interact with gui only here#######################################
	//	Log.e(locName,latitude1+""+longitude1);
	//	for(int st=0;st<a.length;st++){
		//toPosition = new LatLng(latitude1,longitude1);
		//	 Log.e("draw city lat", ""+latarr[0]);	
         //    Log.e("draw city lan", ""+lanarr[0]);
	//	Log.e("d",""+latarr.length);
			//////////toPosition = new LatLng(latitude1,longitude1);
			/////////////draw();
		view.setVisibility(View.VISIBLE);
			//counter++;
		
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
		mMap.addMarker(new MarkerOptions().position(toPosition).title("Start"));
		/*mMap.addMarker(new MarkerOptions().position(toPosition).title("End"));
		
		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
		

		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
		
		for(int i = 0 ; i < directionPoint.size() ; i++) {			
			rectLine.add(directionPoint.get(i));
			//directionPoint the array returned by the decodePoly method are stored 
		}
		
		mMap.addPolyline(rectLine);*/
	}
	private void drawMarker(LatLng point){
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();
 
        // Setting latitude and longitude for the marker
        markerOptions.position(point);
 
        // Adding marker on the Google Map
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toPosition));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
    }

}
