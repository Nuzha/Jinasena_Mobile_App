package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Reminders extends Activity{
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String packagename = "com.NIRR.jenasena";
	TextView e1;
	String rep;
	String resultValue; 
	
	Calendar cal = new GregorianCalendar();
    String cdate=""+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"";

	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.reminders);
	    
	    //Intent intent = getIntent();
		// rep = intent.getStringExtra(MyTestService.ACTION);
		//e1=(TextView)findViewById(R.id.textView1);
		//e1.setText(cusid);
	    
	    //Intent in = new Intent();
	    //in.getExtras().getString("resultCode");
	    //in.getExtras().getString(packagename);
	    
	    dbHelper = new DBCreater(this);
		dbHelper.open();
		
		displayListView();
	}
	
	public void onStartService(View v) {
	    /*  Intent i = new Intent(this, MyTestService.class);
	      i.putExtra(packagename,resultValue);
	      startService(i);*/
	    }
	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
        
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	
	 // Define the callback for what to do when data is received
   // private BroadcastReceiver testReceiver = new BroadcastReceiver() {

		//public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			
		/*	int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                resultValue = intent.getExtras().getString(MyTestService.ACTION);//.getStringExtra(MyTestService.ACTION);
                Toast.makeText(NotificationReceiverActivity1.this, resultValue, Toast.LENGTH_SHORT).show();*/
           // }
			
		//}
    	
   // };

	@SuppressLint("NewApi") private void displayListView(){
		
		
		Cursor cursor = dbHelper.getreminderData("Del01Rep01",cdate);
		String[] columns = new String[] {
			DBCreater.KEY_TASKNAME,
			
		};
		
		int[] to = new int[]{
				R.id.tv_c_demotaskSearch,
				
					
		};
		
		dataAdapter = new SimpleCursorAdapter(this, R.layout.demo_search_taskname, cursor, columns, to,0);
		final ListView listView = (ListView)findViewById(R.id.lv_c_notification_cusID_r);
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> lidstView, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)listView.getItemAtPosition(position);
				//Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
				String name=cursor.getString(cursor.getColumnIndexOrThrow("taskName"));
				Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
				
				
				
				
			}
			
		});
		
	}


}
