package com.NIRR.jenasena;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView; 
import android.widget.Toast;
import com.NIRR.jenasena.RepMenu;
import com.NIRR.jenasena.UpdaterServiceManager1;

public class NotificationReceiverActivity1 extends Activity{
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String packagename = "com.NIRR.jenasena";
	TextView e1;
	String rep;
	String resultValue;
	int amount;
	
	Calendar cal = new GregorianCalendar();
    String cdate=""+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"";
   

	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.notification);
	    
	    //Intent intent = getIntent();
		// rep = intent.getStringExtra(MyTestService.ACTION);
		//e1=(TextView)findViewById(R.id.textView1);
		//e1.setText(cusid);
	    
	    //Intent in = new Intent();
	    //in.getExtras().getString("resultCode");
	    //in.getExtras().getString(packagename);
	    
	    System.out.println(cdate);
	   // System.out.println();
	   // Date();
	    
	    dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayListView();
		
	}
	
	public void onStartService(View v) {
	      Intent i = new Intent(this, MyTestService.class);
	      i.putExtra(packagename,resultValue);
	      startService(i);
	    }
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		 // Unregister the listener when the application is paused
        LocalBroadcastManager.getInstance(this).unregisterReceiver(testReceiver);
        // or `unregisterReceiver(testReceiver)` for a normal broadcast
        
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// Register for the particular broadcast based on ACTION string
        IntentFilter filter = new IntentFilter(MyTestService.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(testReceiver, filter);
        // or `registerReceiver(testReceiver, filter)` for a normal broadcast
        
	}
	
	 // Define the callback for what to do when data is received
    private BroadcastReceiver testReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
			int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                resultValue = intent.getExtras().getString(MyTestService.ACTION);//.getStringExtra(MyTestService.ACTION);
                Toast.makeText(NotificationReceiverActivity1.this, resultValue, Toast.LENGTH_SHORT).show();
            }
			
		}
    	
    };

	private void displayListView(){
		
		amount = 0;
		Cursor cursor = dbHelper.getnotificationData("Del01Rep01","Not Paid",cdate,amount);
		String[] columns = new String[] {
			DBCreater.Key_Invoice_cust_id,
			DBCreater.Key_Invoice_post_dicount_amount,
			DBCreater.Key_Invoice_selling_date ,
			DBCreater.Key_Invoice_due_date ,
			DBCreater.Key_Invoice_paid_cost,
				
		};
		
		int[] to = new int[]{
				R.id.textView1,
				R.id.textView2,
				R.id.textView3,
				R.id.textView4,
				R.id.textView6,
					
		};
		
		dataAdapter = new SimpleCursorAdapter(this, R.layout.demo_notification, cursor, columns, to,0);
		final ListView listView = (ListView)findViewById(R.id.lv_c_notification_cusID_r);
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> lidstView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)listView.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String name=cursor.getString(cursor.getColumnIndexOrThrow("Invoice_cus_id"));
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				
				
				
				
			}
			
		});
		
	}

}
