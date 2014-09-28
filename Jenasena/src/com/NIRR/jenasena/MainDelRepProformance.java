package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainDelRepProformance extends Activity implements OnItemSelectedListener{

	TextView e1,name;
	Spinner year,month,sp;
	String label,tp;
	Button chart;
	ImageButton call;
	static String packagename="com.NIRR.jenasena";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cdi_main_repproformance);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(ChartMenu.packagename);
	    e1=(TextView)findViewById(R.id.tv_cdMainRepProformance_id_Isuru);
	    e1.setText(s1);
	    year=(Spinner)findViewById(R.id.sp_cdMainRepProformance_year_Isuru);
	    month=(Spinner)findViewById(R.id.sp_cdMainRepProformance_month_Isuru);
	    sp=(Spinner)findViewById(R.id.sp_cdMainRepProformance_repid_Isuru);
	    name=(TextView)findViewById(R.id.tv_cdMainRepProformance_repname_Isuru);
	    call=(ImageButton)findViewById(R.id.bt_cdMainRepProformance_call_Isuru);
	    chart=(Button)findViewById(R.id.bt_cdMainRepProformance_chart_Isuru);
	    loader(s1);
	    sp.setOnItemSelectedListener(this);
	    call.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
				    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
				    my_callIntent.setData(Uri.parse("tel:"+tp));
				    startActivity(my_callIntent);
				} catch (ActivityNotFoundException e) {
				    Toast.makeText(getApplicationContext(), "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
				}
				
			}
		});
		chart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p0 = new Intent(MainDelRepProformance.this, Chart_D_RepProformance.class);
				String monthsetter;
				int mymonth =  year.getSelectedItemPosition();
				switch (mymonth) {
				case 0:
					monthsetter="1";
					break;
				case 1:
					monthsetter="2";
					break;
				case 2:
					monthsetter="3";
					break;
				case 3:
					monthsetter="4";
					break;
				case 4:
					monthsetter="5";
					break;
				case 5:
					monthsetter="6";
					break;
				case 6:
					monthsetter="7";
					break;
				case 7:
					monthsetter="8";
					break;
				case 8:
					monthsetter="9";
					break;
				case 9:
					monthsetter="10";
					break;
				case 10:
					monthsetter="11";
					break;
				case 11:
					monthsetter="12";
					break;

				default:
					monthsetter="01";
					break;
				}
				String date=month.getSelectedItem().toString()+"/"+monthsetter;
				String sender=date+"*"+sp.getSelectedItem().toString();
				p0.putExtra(packagename, sender);
				startActivity(p0);
				
			}
		});
		
	}
	private void loader(String s1) {
		// TODO Auto-generated method stub
		List<String> tagYears = new ArrayList<String>();
		List<String> tagMonth = new ArrayList<String>();
		tagMonth.add("January");
		tagMonth.add("Febuary");
		tagMonth.add("March");
		tagMonth.add("April");
		tagMonth.add("May");
		tagMonth.add("June");
		tagMonth.add("July");
		tagMonth.add("August");
		tagMonth.add("September");
		tagMonth.add("October");
		tagMonth.add("November");
		tagMonth.add("December");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tagMonth);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(dataAdapter);
        for(int year=2010;year<=2030;year++){
        	tagYears.add(""+year);
        }
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tagYears);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(dataAdapter1);
        DBCreater entry = new DBCreater(MainDelRepProformance.this);
		entry.openforread();
		List<String> lables = entry.getAllLabels(s1);
		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(dataAdapter3);
        entry.close();
		
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		 label = arg0.getItemAtPosition(arg2).toString();
		 DBCreater entry = new DBCreater(MainDelRepProformance.this);
		entry.openforread();
		 String rep = sp.getSelectedItem().toString();
		 Cursor mcuresr= entry.getrepnameandtep(rep);
		// mcuresr.getColumnCount();
			tp=mcuresr.getString(2);
			name.setText(mcuresr.getString(1));
		 
	        // Showing selected spinner item
	     Toast.makeText(arg0.getContext(), "You selected: " + mcuresr.getColumnCount(),
	                Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
