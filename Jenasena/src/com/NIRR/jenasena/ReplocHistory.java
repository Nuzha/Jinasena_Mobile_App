package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class ReplocHistory extends Activity {

	Spinner sp;
	private TextView mStartDate;
	private Button mPickStartDate,next,graph;
	private int mYear;
	private int mMonth;
	private int mDay;
	public static String packagename="package com.NIRR.jenasena";
		    
	static final int DATE_DIALOG_ID = 0;
	int count=0;
	private DatePickerDialog.OnDateSetListener pDateSetListener =new DatePickerDialog.OnDateSetListener() {
		 
	    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	          mYear = year;
	          mMonth = monthOfYear;
	          mDay = dayOfMonth;
	                    
	          //if(count==1){
	             updateStartDisplay();
	        //  }
	                    
	         
	             
	           }
	     };
	 
		
	            
	      public void updateStartDisplay(){
	            	
	         mStartDate.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	            	
	       }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.replochistory);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(RepLocMenu.packagename);
	    Log.e("posser",s1);
	    sp=(Spinner)findViewById(R.id.spinner1);
	    graph=(Button)findViewById(R.id.button3);
	   graph.setVisibility(View.GONE);
	    mStartDate=(TextView)findViewById(R.id.textView3);
	    mPickStartDate=(Button)findViewById(R.id.button1);
	    next=(Button)findViewById(R.id.button2);
	    final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
 
        updateStartDisplay();
		
	    loadRepNames(s1);
	    mPickStartDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
	    next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p1 = new Intent(ReplocHistory.this, RepHistoryMap.class);
				String text = mStartDate.getText()+"*"+sp.getSelectedItem().toString();
				Log.e("nest button",text);
				p1.putExtra(packagename,text);
				startActivity(p1);
			}
		});
	    graph.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p1 = new Intent(ReplocHistory.this,ChartLocTrack.class);
				String text = mStartDate.getText()+"*"+sp.getSelectedItem().toString();
				Log.e("graph button",text);
				p1.putExtra(packagename,text);
				startActivity(p1);
				
			}
		});
	}

	private void loadRepNames(String s1) {
		// TODO Auto-generated method stub
		DBCreater entry = new DBCreater(ReplocHistory.this);
		entry.openforread();
		List<String> lables = entry.getAllLabels(s1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
		// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
       entry.close();
		
	}
	@Override
	  protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, 
	                        pDateSetListener,
	                        mYear, mMonth, mDay);
	        }
	        return null;
	    }
	
	
}
