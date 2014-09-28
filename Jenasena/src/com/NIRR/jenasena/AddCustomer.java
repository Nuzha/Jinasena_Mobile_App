package com.NIRR.jenasena;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddCustomer extends Activity implements OnItemSelectedListener{

	TextView logger;
	Button add;
	Integer testresults;
	Spinner sp;
	EditText custemername,shopname,location,phone,address,email;
	String scustemername,sshopname,slocation,sphone,saddress,semail,srep,sdelear,sloger;
	String label="";
	protected Cursor cursor;
	protected SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_customer);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MenuCustomer.packagename);
	    add=(Button)findViewById(R.id.bt_add_customer_add_Isuru);
	    sp=(Spinner)findViewById(R.id.sp_add_customer_Replist_Isuru);
	    logger=(TextView)findViewById(R.id.tv_add_customer__Isuru);
	    logger.setText(s1);
	    loadRepNames(s1);
		custemername=(EditText)findViewById(R.id.et_add_customer_Owner_Isuru);
		shopname=(EditText)findViewById(R.id.et_add_customer_Company_Isuru);
		location=(EditText)findViewById(R.id.et_add_customer_City_Isuru);
		phone=(EditText)findViewById(R.id.et_add_customer_Phone_Isuru);
		address=(EditText)findViewById(R.id.et_add_customer_Address_Isuru);
		email=(EditText)findViewById(R.id.et_add_customer_Email_Isuru);
		sp.setOnItemSelectedListener(this);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean isenter=true;
				
				//get the inserted values
				scustemername=custemername.getText().toString();
				sshopname=shopname.getText().toString();
				slocation=location.getText().toString();
				sphone=phone.getText().toString();
				saddress=address.getText().toString();
				semail=email.getText().toString();
				srep=label;
				sloger=logger.getText().toString();
				testresults=checkcustomer(scustemername,sshopname,slocation,sphone,semail,saddress);
			
				if(testresults==1){
					fillallfildes();
				}
				else if(testresults==2){
					errorname();
				}
				else if(testresults==3){
					errorphone();
				}
				else if(testresults==4){
					erroremail();
				}
				else if(testresults==0){
				
				try{
					//open the db and send values for inserting
					DBCreater entry = new DBCreater(AddCustomer.this);
					entry.open();
					String rep = sp.getSelectedItem().toString();
					String sender=rep;
					entry.createEntry(scustemername,sshopname,slocation,sphone,semail,saddress,rep,sloger,sender);
					entry.close();
				}
				catch(Exception e){
					isenter=false;	
				}
				finally{
					if(isenter){
						correctInserting();
					}
					else{
						errorInserting();
					}
				}
			}
			}
		});
	}
	private void loadRepNames(String s1) {
		// TODO Auto-generated method stub
		DBCreater entry = new DBCreater(AddCustomer.this);
		entry.openforread();
		List<String> lables = entry.getAllLabels(s1);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables);
		// Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        sp.setAdapter(dataAdapter);
       entry.close();
		
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) {
		// TODO Auto-generated method stub
		  label = parent.getItemAtPosition(position).toString();
		 
	        // Showing selected spinner item
	        Toast.makeText(parent.getContext(), "You selected: " + label,
	                Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//to validate formes
		@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		public int checkcustomer(String scustemername,String sshopname,String slocation,String sphone,String semail,String saddress) {
			
			
			int index1 = semail.indexOf('@');
	        int index2 = semail.indexOf('.');
	        int index3 = semail.lastIndexOf('@');
	        int index4 = semail.lastIndexOf('.');
	        boolean emailvalidater = true;
	        boolean namevalidate = false;
	        boolean phonvalidate = true;
	        
	        //validate email
	        if ((index1 <= 0) || (index2 <= 0) || (index4 <= index3)) {
	            emailvalidater = false;
	        }
			
	        //validate name
			int nameleng = scustemername.length();
	        int counter = 0;
	        for (int k = 0; k < nameleng; k++) {
	            char let = scustemername.charAt(k);
	            if (Character.isLetter(let)) {
	                counter++;
	            } else {
	                break;
	            }
	        }
	        
	        // validate phonnumber
	        if(sphone.length()!=10){
	        	phonvalidate=false;
	        }
	        
	        // validate phonnumber
	        int leng = sphone.length();
	        int phoncounter = 0;
	        for (int k = 0; k < leng; k++) {
	             char let = sphone.charAt(k);
	                if (Character.isDigit(let)) {
	                    phoncounter++;
	                } else {
	                    break;
	              }
	         }
	        if(leng!=phoncounter){
	        	phonvalidate=false;
	        }
	         
	        if(scustemername.isEmpty()||sshopname.isEmpty()||slocation.isEmpty()||sphone.isEmpty()||semail.isEmpty()||saddress.isEmpty()){
				return 1;
			}
	        else if(counter!=nameleng){
	        	return 2;
	        }
	        else if(phonvalidate==false){
	        	return 3;
	        }
	        else if(emailvalidater==false){
	        	return 4;
	        }
	        else{
	        	return 0;
	        }
		}
		
		public void errorInserting(){
			Dialog d = new Dialog(this);
			d.setTitle("Error!");
			TextView tv = new TextView(this);
			tv.setText("Error occer when inserting !");
			d.setContentView(tv);
			d.show();
		}
		//display an message when inserting values
		public void correctInserting(){
			Dialog d = new Dialog(this);
			d.setTitle("Succes!");
			TextView tv = new TextView(this);
			tv.setText("Inserted succes fully !");
			d.setContentView(tv);
			d.show();
		}
		//display message when not fill all the fields
		public void fillallfildes(){
			Dialog d = new Dialog(this);
			d.setTitle("Error!");
			TextView tv = new TextView(this);
			tv.setText("fill all data feilds !");
			d.setContentView(tv);
			d.show();
		}
		//display message when insert name
		public void errorname(){
			Dialog d = new Dialog(this);
			d.setTitle("Error!");
			TextView tv = new TextView(this);
			tv.setText("Invalied name !");
			d.setContentView(tv);
			d.show();
		}
		//display message when inserting an wrong email
		public void erroremail(){
			Dialog d = new Dialog(this);
			d.setTitle("Error!");
			TextView tv = new TextView(this);
			tv.setText("Invalied email !");
			d.setContentView(tv);
			d.show();
		}
		//display message when inserting an error wrong phone number
		public void errorphone(){
			Dialog d = new Dialog(this);
			d.setTitle("Error!");
			TextView tv = new TextView(this);
			tv.setText("Invalied phone number !");
			d.setContentView(tv);
			d.show();
		}

}
