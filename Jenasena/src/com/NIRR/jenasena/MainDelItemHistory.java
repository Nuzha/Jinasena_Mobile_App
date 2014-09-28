package com.NIRR.jenasena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainDelItemHistory extends Activity implements OnItemSelectedListener{

	TextView e1,itemname;
	String label;
	Spinner itemid;
	Button chart;
	static String  packagename ="com.NIRR.jenasena";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cdi_main_itemhistory);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MainDelItemHistory.packagename);
	    e1=(TextView)findViewById(R.id.tv_cdMainItemHistory_id_Isuru);
	    itemname=(TextView)findViewById(R.id.tv_cdMainItemHistory_itemofname_Isuru);
	    e1.setText(s1);
	    itemid=(Spinner)findViewById(R.id.sp_cdMainItemHistory_itemid_Isuru);
	    chart=(Button)findViewById(R.id.myloc);
	  /*  chart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p0 = new Intent(MainDelItemHistory.this, Chart_D_ItemHistory.class);
				p0.putExtra(packagename, itemid.getSelectedItem().toString());
				startActivity(p0);
				
			}
		});*/
	    load(s1);
	    
	}
	private void load(String s1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
