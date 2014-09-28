package com.NIRR.jenasena;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainDelCustomerProformance extends Activity  implements OnItemSelectedListener{

	TextView e1,id,name;
	Button chart;
	ImageButton call;
	Spinner sp;
	String label,tp;
	static String packagename="com.NIRR.jenasena";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cdi_main_customerproformance);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(ChartMenu.packagename);
	    e1=(TextView)findViewById(R.id.tv_cdMainCustomerProformance_id_Isuru);
	    id=(TextView)findViewById(R.id.tv_cdMainCustomerProformance_cusid_Isuru);
	    name=(TextView)findViewById(R.id.textview_cdMainCustomerProformance_name_Isuru);
	    chart=(Button)findViewById(R.id.bt_cdMainCustomerProformance_chart_Isuru);
	    call=(ImageButton)findViewById(R.id.bt_cdMainCustomerProformance_call_Isuru);
	    sp=(Spinner)findViewById(R.id.sp_cdMainCustomerProformance_shop_Isuru);
	    e1.setText(s1);
	    loadshopname(s1);
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
				Intent p0 = new Intent(MainDelCustomerProformance.this, Chart_D_CustomerProformance.class);
				p0.putExtra(packagename, id.getText().toString());
				startActivity(p0);
				
			}
		});
	    
	}
	private void loadshopname(String s1) {
		// TODO Auto-generated method stub
		DBCreater entry = new DBCreater(MainDelCustomerProformance.this);
		entry.openforread();
		if(s1.indexOf("R")!=-1){
			List<String> lables = entry.getshoprep(s1);
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
			// Drop down layout style - list view with radio button
	        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	         Log.e("R","find");
	        // attaching data adapter to spinner
	        sp.setAdapter(dataAdapter);
	       entry.close();
		}
		
		else{
		List<String> lables = entry.getshop(s1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
		// Drop down layout style - list view with radio button
		Log.e("R","notfind");
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
       entry.close();
		}
	/*	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
		// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
       entry.close();*/
		
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		 label = arg0.getItemAtPosition(arg2).toString();
		 DBCreater entry = new DBCreater(MainDelCustomerProformance.this);
		entry.openforread();
		 String rep = sp.getSelectedItem().toString();
		 Cursor mcuresr= entry.getcusnameandtep(rep);
		 
			tp=mcuresr.getString(5);
			name.setText(mcuresr.getString(2));
			id.setText(mcuresr.getString(1));
		 
	        // Showing selected spinner item
	     Toast.makeText(arg0.getContext(), "You selected: " + label,Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
