package com.NIRR.jenasena;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class UpdaterServiceManager2 extends Service{
	
	private final int UPDATE_INTERVAL = 60 * 1000;
	   // private Timer timer = new Timer();
	    private NotificationManager nm;
	    String rep;
	    
	    
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
		        Toast.makeText(this,"Monthly notification received! ", UPDATE_INTERVAL).show();
		        showNotification();
		        
		    }

		    public void showNotification()
		    {
		        
		    	CharSequence text = getText(R.string.app_name);
				 Notification notification = new Notification(R.drawable.invoice, text,System.currentTimeMillis());
				 PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				                new Intent(this, NotificationReceiverActivity2.class), 0);
				 
				 
				notification.setLatestEventInfo(this, getText(R.string.app_name), text, contentIntent);
				NotificationManager nm = (NotificationManager)
			    		getSystemService(NOTIFICATION_SERVICE);
				nm.notify(R.string.service_started, notification);
				
				 notification.flags |= Notification.FLAG_AUTO_CANCEL;
				// stopSelf();
		    	
		    }
		    
		    

		    @Override
		    public void onDestroy() {
		    	NotificationManager nm = (NotificationManager)
			    		getSystemService(NOTIFICATION_SERVICE);
		    	 nm.cancel(R.string.service_started);
		         Toast.makeText(this, "Service destroyed! "  , Toast.LENGTH_LONG).show();
		    }

		    @Override
		    public int onStartCommand(Intent intent, int flags, int startid) 
		    {
		        return START_STICKY;
		    }

}
