package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.NIRR.jenasena.Main_todo;
import com.NIRR.jenasena.DBCreater;

@SuppressLint("NewApi") public class Add_newTodo extends Activity{
	
	public static String packagename="com.NIRR.jenasena";
	protected SQLiteDatabase db;
	
	Button add_task;
	Button search_task;
	Button Date;
	Button assign_task;
	EditText item;
	String rep;
	String[] tasks;
	List<String> array;
	List<String> selchkboxlist;
	TextView date;
	CheckBox cb;
	String A_date;
	int last_id_before;
	int last_id_after;
	DBCreater DBcreater;
	private SimpleCursorAdapter dataAdapter;
	DatePicker Dpicker;
	
	//date picker
		private int mYear;
		private int mMonth;
		private int mDay;
		
		private TextView mDateDisplay;
		private Button mPickDate;
		
		static final int DATE_DIALOG_ID = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addnew_todo);
		
		//logger = (TextView) findViewById(R.id.tv_logger_id);
		Intent intent = getIntent();
	    rep = intent.getStringExtra(Main_todo.packagename);
		//logger.setText("Rep01");
		
		add_task = (Button)findViewById(R.id.button1);
		search_task = (Button)findViewById(R.id.button2);
		Date = (Button)findViewById(R.id.button4);
		assign_task = (Button)findViewById(R.id.button3);
		 mDateDisplay = (TextView) findViewById(R.id.textView3); 
		// Dpicker = (DatePicker) findViewById(R.id.)
		
		DBcreater= new DBCreater(Add_newTodo.this);
		
		DBcreater.open();
		DBcreater.openforread();
	
		 last_id_before = DBcreater.lastInsertedID();
		//System.out.println(DBcreater.getTable());
		 
		 System.out.println(mDateDisplay.getText().toString());
		 
		
		 
		 add_task.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//String new_task =getText(R.id.editText1).toString();
				
				DBcreater.open();
				item = (EditText)findViewById(R.id.editText1);
				String new_task =item.getText().toString();
				
				//int number = 1||(2||(3||(4||(5||(6||(7||(8||(9||(0)))))))));
				
				if(new_task.equals("") ){
					Dialog d1=new Dialog(Add_newTodo.this);
					d1.setTitle("Error in inserting !" );
					TextView t=new TextView(Add_newTodo.this);
					t.setText("Cannot add the task to the list !");
					d1.setContentView(t);
					d1.show(); 
					
			}else if(new_task.startsWith("1")||new_task.startsWith("2")||new_task.startsWith("3")||new_task.startsWith("4")||new_task.startsWith("5")||
					new_task.startsWith("6")||new_task.startsWith("7")||new_task.startsWith("8")||new_task.startsWith("9")||new_task.startsWith("0")){
				
				Dialog d=new Dialog(Add_newTodo.this);
				d.setTitle("Error in inserting !" );
				TextView t=new TextView(Add_newTodo.this);
				t.setText("Enter some valid text as the task!");
				d.setContentView(t);
				d.show(); 
			}
				else{
					
                    DBcreater.addTaskList(new_task,rep);
					
					Dialog d2=new Dialog(Add_newTodo.this); 
					d2.setTitle("Successful" );
					TextView t=new TextView(Add_newTodo.this);
					t.setText("successfully added the task to the list ");
					d2.setContentView(t);
					d2.show(); 
					
				}
				DBcreater.close();
					}
		});
		
		search_task.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//DBcreater.search_All_task(rep);
				
				displayListView();
				
			}
		});
		
		Date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		
		assign_task.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				last_id_after = DBcreater.lastInsertedID();
				
				if(last_id_after == last_id_before ){
					
					Dialog d3=new Dialog(Add_newTodo.this);
					d3.setTitle("Error in inserting !" );
					TextView t=new TextView(Add_newTodo.this);
					t.setText("select some tasks before assign to the list");
					d3.setContentView(t);
					d3.show(); 
				}
				else{
				Dialog d4=new Dialog(Add_newTodo.this);
				d4.setTitle("Successful !" );
				TextView t=new TextView(Add_newTodo.this);
				t.setText("successfully assigned the task to the list ");
				d4.setContentView(t);
				d4.show(); 
				}
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
	
	private void displayListView(){
		
		
		Cursor cursor = DBcreater.search_All_task(rep);
		String[] columns = new String[] {
			DBCreater.KEY_TASK_LIST_TASKNAME,
					
		};
		
		int[] to = new int[]{
				R.id.cb_taskName,
							
		};
		
		final ListView listView = (ListView)findViewById(R.id.listView1);
		dataAdapter = new SimpleCursorAdapter(this, R.layout.demo_search_todo, cursor, columns, to,0);
		
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
				
				DBcreater.open();
				//date = (TextView)findViewById(R.id.textView3);
			    A_date = mDateDisplay.getText().toString();
				DBcreater.addTask(name, rep,A_date);
				Log.e(" date ", A_date);
				DBcreater.close();
				
			}
			
		});
		
	}
	
	 //Update display method
	  private void updateDisplay() {
		    this.mDateDisplay.setText(
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

					
					/*public void onDateSet1(android.widget.DatePicker arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub
						
					}*/
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
			    
			    public void itemClicked(View v) {

			    	DBcreater.open();
			        //code to check if this checkbox is checked!
			    	cb = (CheckBox) v ; 
			    	String name = cb.getText().toString();
			    	 A_date = mDateDisplay.getText().toString();
			    	
			    	if (((CheckBox) v).isChecked()) {
			    		
			    		 
			    		Toast.makeText(Add_newTodo.this,
			            		"Clicked on Checkbox: "+name , Toast.LENGTH_LONG).show();
			    		
			    		
						//date = (TextView)findViewById(R.id.textView3);
					  
						DBcreater.addTask(name, rep,A_date);
						
			    		
			        }
			    	else {
			    		A_date = mDateDisplay.getText().toString();
			    		Log.e("errrrrrrrrr",rep);
			    		Log.e("errrrrrrrrr",name);
			    		
			    		DBcreater.deleteTask(name, rep, A_date);
			    		Toast.makeText(Add_newTodo.this,
			            		"Deselect Checkbox: "+name , Toast.LENGTH_LONG).show();
			    	}
			    	DBcreater.close();
			    }
			    
			   
			    
          /* private class MyCustomAdapter extends ArrayAdapter<Item> {
					  
					  public MyCustomAdapter(Context context, int textViewResourceId) {
						super(context, textViewResourceId);
						// TODO Auto-generated constructor stub
					}
					private ArrayList<Item> itemlist;
					 
					  public MyCustomAdapter(Context context, int textViewResourceId, ArrayList<Item> itemlist) {
					   super(context, textViewResourceId, itemlist);
					   this.itemlist = new ArrayList<Item>();
					   this.itemlist.addAll(itemlist);
					  }
					  
					  private class ViewHolder {
						  // TextView qty;
						   CheckBox name;
						  }
				  
					  
					  @Override
					  public View getView(int position, View convertView, ViewGroup parent) {
						  
						  
					 
					   ViewHolder holder = null;
					   Log.v("ConvertView", String.valueOf(position));
					 			 
					   if (convertView == null) {
					   LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					   convertView = vi.inflate(R.layout.demo_search_todo, null);
					 
					 
					   holder = new ViewHolder();
					  // holder.qty = (TextView) convertView.findViewById(R.id.r_availqtytbx);
					   holder.name = (CheckBox) convertView.findViewById(R.id.cb_taskName);
					   convertView.setTag(holder);
					   
				  
					   
					    holder.name.setOnClickListener( new View.OnClickListener() {  
					     public void onClick(View v) {  
					      
					    CheckBox cb = (CheckBox) v ;  
					      //Item item = (Item) cb.getTag();  
					      Toast.makeText(getApplicationContext(),"Clicked on Checkbox: " + cb.getText() +" is " + cb.isChecked(), Toast.LENGTH_LONG).show();
					     // item.setSelected(cb.isChecked());
					      
					      
					      
					    	 
					    	 selchkboxlist=new ArrayList<String>();
					    	 CheckBox cb1 = (CheckBox) v ;
					           String chk = null;
							if (((CheckBox) v).isChecked()) {
					                chk = Integer.toString(v.getId());
					                    selchkboxlist = new ArrayList<String>();
					                    selchkboxlist.add(chk);
					                    Toast.makeText(Add_newTodo.this, "Selected CheckBox ID" + v.getId(), Toast.LENGTH_SHORT).show();
					            } else {
					                 selchkboxlist.remove(chk);
					              Toast.makeText(Add_newTodo.this, "Not selected", Toast.LENGTH_SHORT).show();
					                }
					    	 
					    	 
					     }  
					    });  
					   }
					   
					   else {
					    holder = (ViewHolder) convertView.getTag();
					   }
					 /*
					   Item item = itemlist.get(position);
					   holder.qty.setText(item.getQty());
					   holder.name.setText(item.getName());
					   holder.name.setChecked(item.isSelected());
					   holder.name.setTag(item);
					 
					   return convertView;
					   
					  } 	 
		}
				  return convertView;
					  }
	
	
			    } */
}
