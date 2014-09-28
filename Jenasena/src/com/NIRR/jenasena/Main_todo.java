package com.NIRR.jenasena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main_todo extends Activity implements OnClickListener{
	
	Button add;
	Button update;
	Button delete;
	TextView logger;
	
	
	public static String packagename="com.NIRR.jenasena";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_todo_view);
		
		logger = (TextView) findViewById(R.id.tv_logger_id);
		Intent intent = getIntent();
		String s1 = intent.getStringExtra(MainActivity.packagename);
		logger.setText(s1);
		
		add= (Button)findViewById(R.id.bt_add_task);
		update= (Button)findViewById(R.id.bt_update_task);
		delete = (Button)findViewById(R.id.bt_delete_task);
		
		Intent p5 = new Intent(Main_todo.this, UpdaterServiceManager_reminder.class);
		p5.putExtra(packagename, logger.getText());
		startService(p5);
		//sendBroadcast(p5);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent= new Intent(Main_todo.this,Add_newTodo.class);
				intent.putExtra(packagename, logger.getText());
				startActivity(intent);
				
			}
		});
		
		
		update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent1= new Intent(Main_todo.this,Search_Todo.class);
				intent1.putExtra(packagename, logger.getText());
				startActivity(intent1);
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent2= new Intent(Main_todo.this,Search_Todo.class);
				intent2.putExtra(packagename, logger.getText());
				startActivity(intent2);
				
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
