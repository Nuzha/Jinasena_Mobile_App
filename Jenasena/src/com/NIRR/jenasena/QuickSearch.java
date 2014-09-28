package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuickSearch extends Activity {
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	TextView logger;
	RadioButton tot,availprodouct;
	String usr;
	Button next;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_search);
		
		logger=(TextView)findViewById(R.id.tv_qs_logger_rosh);
		Intent intent = getIntent();
		usr = intent.getStringExtra(DelearMenu.packagename);
		logger.setText(usr);
		
		tot=(RadioButton)findViewById(R.id.radiobtn_totitem_r);
		availprodouct=(RadioButton)findViewById(R.id.radiobtn_avail_r);
		
		next=(Button)findViewById(R.id.btn_next_qs_rosh);
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(tot.isChecked()){
					Intent intent1 = new Intent(QuickSearch.this,SearchToatalAssignedItems.class);
					intent1.putExtra("delkey", logger.getText().toString());
				    startActivity(intent1);
			        
				}else if(availprodouct.isChecked()){
				    Intent intent1 = new Intent(QuickSearch.this,AvailableProducts.class);
				    intent1.putExtra("delkey", logger.getText().toString());
				    startActivity(intent1);
				}else{
				    Toast.makeText(getApplicationContext(), "First select a radio button to proceed..",Toast.LENGTH_LONG).show();
				}
				
				
				
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quick_search, menu);
		return true;
	}

}
