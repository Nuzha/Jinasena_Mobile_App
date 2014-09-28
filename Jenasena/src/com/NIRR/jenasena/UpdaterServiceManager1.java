package com.NIRR.jenasena;

import java.util.Timer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class UpdaterServiceManager1 extends Service{

	
	private final int UPDATE_INTERVAL = 60 * 1000;
    private Timer timer = new Timer();
    private NotificationManager nm;
    String rep;
    
 // private NotificationManager nm = (NotificationManager)
    	//	getSystemService(NOTIFICATION_SERVICE);
    
    
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		
		//intent = getIntent();
		//rep = intent.getStringExtra(RepMenu.packagename);
		return null;
	}
	
	 @Override
	    public void onCreate() {
	        // code to execute when the service is first created
	        super.onCreate();
	        
	       // rep = intent.getStringExtra(RepMenu.packagename);
			//e1=(TextView)findViewById(R.id.textView1);
			//e1.setText(cusid);
	       // Log.i("MyService", "Service Started.");
	        Toast.makeText(this,"Service created  ", UPDATE_INTERVAL).show();
	        showNotification();
	       // clearNotification();
	      //  nm.cancel(R.string.service_started);
	        
	        
	    }

	    public void showNotification()
	    {
	        
	    	CharSequence text = getText(R.string.app_name);
			 Notification notification = new Notification(R.drawable.customerhistory, text,System.currentTimeMillis());
			 PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
			                new Intent(this, NotificationReceiverActivity1.class), 0);
			 
			 
			notification.setLatestEventInfo(this, getText(R.string.app_name), text, contentIntent);
			 nm = (NotificationManager)
		    		getSystemService(NOTIFICATION_SERVICE);
			nm.notify(R.string.service_started, notification);
			
			 notification.flags |= Notification.FLAG_AUTO_CANCEL;
			// clearNotification();
			 
			// stopSelf();
	    	
	    }
	    
	    

	    @Override
	    public void onDestroy() {
	    	
	    	 nm = (NotificationManager)
			    		getSystemService(NOTIFICATION_SERVICE);
	    	 nm.cancel(R.string.service_started);
	         Toast.makeText(this, "Service destroyed! "  , Toast.LENGTH_LONG).show();
	    }

	    @Override
	    public int onStartCommand(Intent intent, int flags, int startid) 
	    {
	        return START_STICKY;
	    }

	   private void stopService() {
	        if (timer != null) timer.cancel();
	    } 
	   
	   public void clearNotification() {
		   nm.cancel(R.string.service_started);
	    }

}
