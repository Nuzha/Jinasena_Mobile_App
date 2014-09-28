package com.NIRR.jenasena;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class AddDel extends Activity{

	Button add;
	Integer testresults;
	String label="";
	protected Cursor cursor;
	protected SQLiteDatabase db;
	EditText name,phone,address,email,company;
	String scustemername,sshopname,slocation,sphone,saddress,semail,srep,sdelear,sloger;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddel);
		name=(EditText)findViewById(R.id.editText1);
		phone=(EditText)findViewById(R.id.editText2);
		email=(EditText)findViewById(R.id.editText3);
		address=(EditText)findViewById(R.id.editText4);
		company=(EditText)findViewById(R.id.editText5);
		add=(Button)findViewById(R.id.button1);
		add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
boolean isenter=true;
				
				//get the inserted values
				scustemername=name.getText().toString();
				sshopname=company.getText().toString();
				slocation=address.getText().toString();
				sphone=phone.getText().toString();
				saddress=address.getText().toString();
				semail=email.getText().toString();
				srep=label;
				testresults=checkcustomer(scustemername,sshopname,sphone,semail,saddress);
			
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
					final DBCreater entry = new DBCreater(AddDel.this);
					entry.openforread();
					Log.e("before",""+entry.countdel());
					int delcount = entry.countdel()+1;
					String did="Del0"+delcount;
					entry.createDeler(did, did, did, scustemername,sphone,semail,saddress,sshopname);
					Log.e("after",""+entry.countdel());
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
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public int checkcustomer(String scustemername,String sshopname,String sphone,String semail,String saddress) {
		
		
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
         
        if(scustemername.isEmpty()||sshopname.isEmpty()||sphone.isEmpty()||semail.isEmpty()||saddress.isEmpty()){
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
