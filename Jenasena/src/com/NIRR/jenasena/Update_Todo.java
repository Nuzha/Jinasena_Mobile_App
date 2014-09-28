package com.NIRR.jenasena;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.NIRR.jenasena.DBCreater;

public class Update_Todo extends Activity{

	String rep;
	String A_date;
	String name,id;
	EditText task,date;
	Button back,update,delete;
	//DBCreater DBcreater;
	
	public static String packagename="com.NIRR.jenasena";
	protected SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_todo);
		
		Intent intent1 = getIntent();
	    rep = intent1.getStringExtra("rep");
	    Intent intent2 = getIntent();
	    A_date = intent2.getStringExtra("date");
	    Intent intent3 = getIntent();
	    name = intent3.getStringExtra("name");
	    Intent intent4 = getIntent();
	    id = intent4.getStringExtra("_id");
	    
	    task= (EditText)findViewById(R.id.editText1);
	    task.setText(name);
	    
	    date= (EditText)findViewById(R.id.editText2);
	    date.setText(A_date);
	    
	    
	    back = (Button)findViewById(R.id.button3);
	    update = (Button)findViewById(R.id.button1);
	    delete = (Button)findViewById(R.id.button2);
	    
       /* DBcreater= new DBCreater(Update_Todo.this);
		
		DBcreater.open();
		DBcreater.openforread();*/
	    
	    
	  /*  back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent= new Intent(Update_Todo.this,Search_Todo.class);
				startActivity(intent);
				
			}
		});*/
	    
	    update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 
                DBCreater DBcreater= new DBCreater(Update_Todo.this);
				
				DBcreater.open();
				
				ContentValues cv = new ContentValues();
				//cv.put(DBCreater.KEY_TASK_REP_ID,quantity.getText().toString());
				cv.put(DBCreater.KEY_TASKNAME,task.getText().toString());
				cv.put(DBCreater.KEY_DATE ,date.getText().toString());
				//fill.update("Invoice", cv, "_id=?",new String[] {s9} );
				DBcreater.updateTask("Tasks", cv, "rep_id=? and taskName=? and date=?", new String[] {rep,name,A_date});
					
				ContentValues cv1 = new ContentValues();
				cv1.put(DBCreater.KEY_TASK_LIST_TASKNAME,task.getText().toString());
				DBcreater.updateTaskList("Task_List", cv1, "rep_id=? and taskName=?", new String[] {rep,name});
				
				Dialog d=new Dialog(Update_Todo.this);
				d.setTitle("Successful" );
				TextView t=new TextView(Update_Todo.this);
				t.setText("Reocrd was updated to the Todo List");
				d.setContentView(t);
				d.show();
				
			}
		});
	    
	    delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
			DBCreater DBcreater= new DBCreater(Update_Todo.this); 
				
			
			System.out.println(rep);
			System.out.println(name);
			System.out.println(A_date);
			
				DBcreater.open();
				DBcreater.deleteTasks1("Tasks","rep_id=? and taskName=? and date=?", new String[] {rep,name,A_date}); 
				
				DBcreater.deleteTasksList("Task_List", "rep_id=? and taskName=?", new String[] {rep,name});
				
				Dialog dlg=new Dialog(Update_Todo.this);
				dlg.setTitle("Deleted!!!" );
				TextView txtvw=new TextView(Update_Todo.this);
				txtvw.setText("Successfully deleted the record");
				dlg.setContentView(txtvw);
				dlg.show();
				
				
			}
		});
	    
	    
	}
	


	
	
}
