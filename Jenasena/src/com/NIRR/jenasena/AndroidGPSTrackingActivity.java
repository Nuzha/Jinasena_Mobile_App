package com.NIRR.jenasena;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity {
	
	Button btnShowLocation;
	
	public static double longt;
	public static double lati;

	
	Button b1;
	
	// GPSTracker class
	GPSTracker gps;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
      /*  btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        b1=(Button)findViewById(R.id.button1)*/;
        
        // show location button click event
        /*btnShowLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {*/		
				// create class object
        
       /////////////////////
		/*	}
		});*/
        
        /*b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				Intent in =new Intent(AndroidGPSTrackingActivity.this,NewMap12.class);
				
				startActivity(in);
				
				
			}
		});*/
    }
    public void getloca()
    
    {
	        gps = new GPSTracker(AndroidGPSTrackingActivity.this);

			// check if GPS enabled		
	        if(gps.canGetLocation()){
	        	
	        	double latitude = gps.getLatitude();
	        	double longitude = gps.getLongitude();
	        	
	        	longt = longitude;
	        	lati = latitude;
	        	
	        	
	        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();	
	        }else{
	        	// can't get location
	        	// GPS or Network is not enabled
	        	// Ask user to enable GPS/network in settings
	        	gps.showSettingsAlert();
	        }
			
    }
}