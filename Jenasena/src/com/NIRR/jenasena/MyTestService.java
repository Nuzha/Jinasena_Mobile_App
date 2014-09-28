package com.NIRR.jenasena;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class MyTestService extends IntentService{
	
	public static final String ACTION = "com.NIRR.jenasena.MyTestService";
	public static String packagename="com.NIRR.jenasena";

	public MyTestService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
	
		 // Fetch data passed into the intent on start
	      String val = intent.getStringExtra(RepMenu.packagename);
	      // Construct an Intent tying it to the ACTION (arbitrary event namespace)
	      Intent in = new Intent(ACTION);
	      // Put extras into the intent as usual
	      in.putExtra("resultCode", Activity.RESULT_OK);
	      in.putExtra(packagename ,val);
	      // Fire the broadcast with intent packaged
	      LocalBroadcastManager.getInstance(this).sendBroadcast(in);
	      // or sendBroadcast(in) for a normal broadcast;
	  }
	
	

}
