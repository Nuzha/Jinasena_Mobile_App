package com.NIRR.jenasena;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class Chart_R_RepStock extends Activity{
	TextView id,date;
	Button chart;
	Button viewdate;
	private int mYear;
	private int mMonth;
	private int mDay;
	static final int DATE_DIALOG_ID = 0;
	int count = 0;
	
	static String packagename="com.NIRR.jenasena";
	
	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;

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
		setContentView(R.layout.repstock);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(RepChartMenu.packagename);
	    chart=(Button)findViewById(R.id.bt_RepStock_chart_Isuru);
	    id=(TextView)findViewById(R.id.tv_RepStock_logger_Isuru);
	    date=(TextView)findViewById(R.id.tv_RepStock_date_Isuru);
	    viewdate=(Button)findViewById(R.id.bt_RepStock_date_Isuru);
	    id.setText(s1);
	    chart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p0 = new Intent(Chart_R_RepStock.this, Chart_R_RepStockChart.class);
				p0.putExtra(packagename, id.getText().toString()+"*"+date.getText().toString());
				startActivity(p0);
			}
		});
	    
	    viewdate.setOnClickListener(new View.OnClickListener() {

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

}
