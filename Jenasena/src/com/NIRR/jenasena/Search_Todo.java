package com.NIRR.jenasena;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.DatePickerDialog;

public class Search_Todo extends Activity implements OnClickListener{
	
	public static String packagename="com.NIRR.jenasena";
	protected SQLiteDatabase db;
	
	Button date;
	TextView date_text;
	Button search;
	ListView tasks;
	String rep;
	String A_date;
	
	DBCreater DBcreater;
	private SimpleCursorAdapter dataAdapter;
	
	//date picker
		private int mYear;
		private int mMonth;
		private int mDay;
		
		static final int DATE_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_todolist);
		
		Intent intent = getIntent();
	    rep = intent.getStringExtra(Main_todo.packagename);
	    
	    date = (Button)findViewById(R.id.button1);
	    date_text = (TextView)findViewById(R.id.textView2);
	    search = (Button)findViewById(R.id.button2);
	    
	    A_date = date_text.getText().toString();
	    
	    System.out.println(A_date);
	    
        DBcreater= new DBCreater(Search_Todo.this);
		
        DBcreater.open();
	    
	    date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				showDialog(DATE_DIALOG_ID);
			}
		});
	    
	    search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				displayListView();
				
			}
		});
	    
	    // get the current date
	    final Calendar c = Calendar.getInstance();
	    mYear = c.get(Calendar.YEAR);
	    mMonth = c.get(Calendar.MONTH);
	    mDay = c.get(Calendar.DAY_OF_MONTH);

	    // display the current date
	    updateDisplay();
	 
	//checkButtonClick();
	DBcreater.close();
	}
	
	
	 //Update display method
	  private void updateDisplay() {
		    this.date_text.setText(
		        new StringBuilder()
		                // Month is 0 based so add 1
		        .append(mYear).append("/")
                .append(mMonth+1).append("/")
                .append(mDay).append(" "));
		}
	  
	  //call back listner for the date picker dialog
	  private DatePickerDialog.OnDateSetListener mDateSetListener =
			    new DatePickerDialog.OnDateSetListener() {
			       	public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mYear = year;
			            mMonth = monthOfYear;
			            mDay = dayOfMonth;
			            updateDisplay();
						
					}

			    };
	 
			    
			 //On create dialog method called by showDialog()   
			    @Override
				protected Dialog onCreateDialog(int id) {
					// TODO Auto-generated method stub
					switch (id) {
					   case DATE_DIALOG_ID:
					      return new DatePickerDialog(this,
					                mDateSetListener,
					                mYear, mMonth, mDay);
					   }
					   return null;
				}
			    
			    private void displayListView(){
					
			    	 A_date = date_text.getText().toString();
					Cursor cursor = DBcreater.search_All_task_update(rep,A_date);
					String[] columns = new String[] {
						DBCreater.KEY_TASKNAME,
								
					};
					
					int[] to = new int[]{
							R.id.tv_c_demotaskSearch,
										
					};
					
					dataAdapter = new SimpleCursorAdapter(this, R.layout.demo_search_taskname, cursor, columns, to,0);
					final ListView listView = (ListView)findViewById(R.id.listView1);
					
					
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
							
							Intent intent= new Intent(Search_Todo.this,Update_Todo.class);
							intent.putExtra("rep", rep);
							intent.putExtra("name", name);
							intent.putExtra("date", A_date);
							intent.putExtra("_id",id);
							
							startActivity(intent);
						}
						
					});	
			    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
}
