package com.NIRR.jenasena;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import android.annotation.TargetApi;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RepLocation extends FragmentActivity{

	private GoogleMap googleMap;
	MarkerOptions markerOptions;
	Button myloc;
	
	public static double longt;
	public static double lati;

	
	
	
	// GPSTracker class
	GPSTracker gps;
	
	GoogleMap mMap;
    GMapV2Direction md;
   
    LatLng fromPosition ;
    
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.replocation);
	//	setUpMapIfNeeded();
		
		myloc=(Button)findViewById(R.id.myloc);
		
		myloc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				// create GPSTracker class object
		        gps = new GPSTracker(RepLocation.this);

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
		
		
	}
	 @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setUpMapIfNeeded() {
		// TODO Auto-generated method stub
		if(googleMap==null){
			googleMap = ((MapFragment)getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
			setupMap();
		}
		if(googleMap!=null){
		}
		
	}
	private void setupMap() {
		// TODO Auto-generated method stub
		//googleMap.setMyLocationEnabled(true);  
		LocationManager locationmanager =(LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationmanager.getBestProvider(criteria, true);
	//	String provider = locationmanager.GPS_PROVIDER;
		Location mylocation = locationmanager.getLastKnownLocation( provider);
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		
		
		double lat = 6.914425;//
	//	double lat =mylocation.getLatitude();
		double lon = 79.972856;//
	//	double lon =mylocation.getLongitude();
		
		LatLng latlng = new LatLng(lat, lon);
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(20));
		googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).title("Here you are").icon((BitmapDescriptorFactory.fromResource(R.drawable.pin))));
		//googleMap.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
	}
	
	

	

	
    
}

