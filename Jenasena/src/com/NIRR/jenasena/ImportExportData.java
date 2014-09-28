package com.NIRR.jenasena;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ImportExportData extends Activity {
	
	public static String package_name = "com.NIRR.jenasena";
	Button backup,restore;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.back_up);
		Intent intent= getIntent();
		
		backup =(Button)findViewById(R.id.button1);
		restore =(Button)findViewById(R.id.button2);
		
		//Backup();
		
		backup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Backup();
				
			}
		});
		
		restore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				restore();
			}
		});
		
	}
	
	public void Backup(){
		try {
		    File sd = Environment.getExternalStorageDirectory();
		    
		   File data = Environment.getDataDirectory();

		    if (sd.canWrite()) {
		    	
		    
		        String currentDBPath = "//data//com.NIRR.jenasena//databases//Jinasena";
		        String backupDBPath = "Jinasena";
		        File currentDB = new File(data, currentDBPath);
		        File backupDB = new File(sd, backupDBPath);
		        
		        System.out.println(data);
		          

		        if (currentDB.exists()) {
		        	
		        	//System.out.println(sd);
		            FileChannel src = new FileInputStream(currentDB).getChannel();
		            FileChannel dst = new FileOutputStream(backupDB).getChannel();
		            dst.transferFrom(src, 0, src.size());
		            src.close();
		            dst.close();
		           // Toast.makeText(, "Backup is successful to SD card", Toast.LENGTH_SHORT).show();
		            
		            Dialog d1=new Dialog(ImportExportData.this);
					d1.setTitle("Successful" );
					TextView t=new TextView(ImportExportData.this);
					t.setText("Backup is successful to SD card");
					d1.setContentView(t);
					d1.show(); 
		            
		        }
		       // System.out.println(sd);
		          
		    }
		} catch (Exception e) {
			System.out.println("cannot backup");
		}
		}
	
	public void restore(){
		
		try {
		    File sd = Environment.getExternalStorageDirectory();
		    File data = Environment.getDataDirectory();

		    if (sd.canWrite()) {
		    	String currentDBPath = "//data//com.NIRR.jenasena//databases//Jinasena";
		        String backupDBPath = "Jinasena";
		        File currentDB = new File(data, currentDBPath);
		        File backupDB = new File(sd, backupDBPath);

		        if (currentDB.exists()) {
		            FileChannel src = new FileInputStream(backupDB).getChannel();
		            FileChannel dst = new FileOutputStream(currentDB).getChannel();
		            dst.transferFrom(src, 0, src.size());
		            src.close();
		            dst.close();
		            
		           // Toast.makeText(getApplicationContext(), "Database Restored successfully", Toast.LENGTH_SHORT).show();
		            
		            Dialog d1=new Dialog(ImportExportData.this);
					d1.setTitle("Successful" );
					TextView t=new TextView(ImportExportData.this);
					t.setText("Database Restored successfully");
					d1.setContentView(t);
					d1.show(); 
					
		        }
		        
		    }
		} catch (Exception e) {
		}
	}

	

	
	

}
