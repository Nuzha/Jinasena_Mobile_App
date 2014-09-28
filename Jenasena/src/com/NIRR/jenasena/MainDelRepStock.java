package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainDelRepStock extends Activity implements OnItemSelectedListener {

	TextView e1, name, date;
	Spinner id;
	// DatePicker date;
	String label, tp;
	Button chart, clickdate;
	ImageButton call;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	int count = 0;
	static String packagename = "com.NIRR.jenasena";
	
	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
//check to clck the button for get date
			if (count == 1) {
				updateDisplay();
			}

			else {

			}

		}
	};
	
	public void updateDisplay() {

		date.setText(new StringBuilder()
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
		setContentView(R.layout.cdi_main_repstock);
		Intent intent = getIntent();
		String s1 = intent.getStringExtra(C_Stock.packagename);
		e1 = (TextView) findViewById(R.id.tv_cdMainRepStock_id_Isuru);
		e1.setText(s1);
		id = (Spinner) findViewById(R.id.sp_cdMainRepStock_rid_Isuru);
		name = (TextView) findViewById(R.id.tv_cdMainRepStock_name_Isuru);
		date = (TextView) findViewById(R.id.tv_cdMainRepStock_date_Isuru);
		// date=(DatePicker)findViewById(R.id.dp_cdMainRepStock_date_Isuru);
		call = (ImageButton) findViewById(R.id.bt_cdMainRepStock_call_Isuru);
		chart = (Button) findViewById(R.id.bt_cdMainRepStock_Chart_Isuru);
		clickdate = (Button) findViewById(R.id.bt_cdMainRepStock_date_Isuru);
		load(s1);
		id.setOnItemSelectedListener(this);
		call.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Intent my_callIntent = new Intent(Intent.ACTION_CALL);
					my_callIntent.setData(Uri.parse("tel:" + tp));
					startActivity(my_callIntent);
				} catch (ActivityNotFoundException e) {
					Toast.makeText(getApplicationContext(),
							"Error in your phone call" + e.getMessage(),
							Toast.LENGTH_LONG).show();
				}

			}
		});
		chart.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p0 = new Intent(MainDelRepStock.this,
						Chart_D_RepStock.class);
				p0.putExtra(packagename, id.getSelectedItem().toString() + "*"
						+ date.getText().toString());
				startActivity(p0);

			}
		});
		clickdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		final Calendar cal = Calendar.getInstance();
		mYear = cal.get(Calendar.YEAR);
		mMonth = cal.get(Calendar.MONTH);
		mDay = cal.get(Calendar.DAY_OF_MONTH);

		updateDisplay();    
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, pDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;       
	}

	private void load(String s1) {
		// TODO Auto-generated method stub
		DBCreater entry = new DBCreater(MainDelRepStock.this);
		entry.openforread();
		List<String> lables = entry.getAllLabels(s1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);
		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		id.setAdapter(dataAdapter);
		entry.close();

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		label = arg0.getItemAtPosition(arg2).toString();
		DBCreater entry = new DBCreater(MainDelRepStock.this);
		entry.openforread();
		String rep = id.getSelectedItem().toString();
		Cursor mcuresr = entry.getrepnameandtep(rep);
		// mcuresr.getColumnCount();
		tp = mcuresr.getString(2);
		name.setText(mcuresr.getString(1));

		// Showing selected spinner item
		Toast.makeText(arg0.getContext(), "You selected: " + label,
				Toast.LENGTH_LONG).show();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	

	
}
