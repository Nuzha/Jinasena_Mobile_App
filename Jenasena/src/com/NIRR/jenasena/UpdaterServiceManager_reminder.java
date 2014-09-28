package com.NIRR.jenasena;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class UpdaterServiceManager_reminder extends Service{

	
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
	        Toast.makeText(this,"Received reminder !  ", UPDATE_INTERVAL).show();
	        showNotification();
	        
	    }

	    @SuppressWarnings("deprecation")
		public void showNotification()
	    {
	        
	    	CharSequence text = getText(R.string.app_name);
			 Notification notification = new Notification(R.drawable.note, text,System.currentTimeMillis());
			 PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
			                new Intent(this, Reminders.class), 0);
			 
			 
			notification.setLatestEventInfo(this, getText(R.string.app_name), text, contentIntent);
			NotificationManager nm = (NotificationManager)
		    		getSystemService(NOTIFICATION_SERVICE);
			nm.notify(R.string.app_name, notification);
			
			 notification.flags |= Notification.FLAG_AUTO_CANCEL;
			// stopSelf();
	    	
	    }
	    
	    

	    @Override
	    public void onDestroy() {
	    	 nm.cancel(R.string.app_name);
	         Toast.makeText(this, "Service destroyed! "  , Toast.LENGTH_LONG).show();
	    }

	    @Override
	    public int onStartCommand(Intent intent, int flags, int startid) 
	    {
	        return START_STICKY;
	    }

}
