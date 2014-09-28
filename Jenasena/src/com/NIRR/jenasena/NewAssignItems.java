package com.NIRR.jenasena;

import harmony.java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import android.R.string;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewAssignItems extends Activity implements OnItemSelectedListener,OnClickListener{
	
	TextView del;
	TextView rep;
	TextView stckqty,ucost;
	String del1,rep1,ch;
	Spinner item;
	EditText assignqty;
	Button assign,lowstock,refresh;
	
	String date;
	
	private TextView mDateDisplay;
	private Button mPickDate;
			
	private int mYear;
	private int mMonth;
	private int mDay;
		    
	static final int DATE_DIALOG_ID = 0;
	int count=0;
		
	public static String packagename="com.NIRR.redistributionsystem";
	

	  
	   //call back listner for the date picker dialog
	private DatePickerDialog.OnDateSetListener pDateSetListener =new DatePickerDialog.OnDateSetListener() {
 
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
          mYear = year;
          mMonth = monthOfYear;
          mDay = dayOfMonth;
                    
          if(count==1){
             updateDisplay();
          }
                    
                     
           }
     };
 
	
            
      public void updateDisplay(){
            	
         mDateDisplay.setText(
         new StringBuilder()
                                  // Month is 0 based so add 1
         	.append(mYear).append("/")        
            .append(mMonth + 1).append("/")
            .append(mDay).append("")
             );
            	
       }

		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_assign_items);
		
		del=(TextView)findViewById(R.id.tv_del_rosh);
		Intent intent = getIntent();
		del1 = intent.getStringExtra("rep_delear");
		del.setText(del1);
		
		rep=(TextView)findViewById(R.id.tv_rep_rosh);
		Intent intent2 = getIntent();
		rep1 = intent2.getStringExtra("rep_uname");
		rep.setText(rep1);
		
		item=(Spinner)findViewById(R.id.spin_items_rosh);
		item.setOnItemSelectedListener(this);
		loadItems();
		
		stckqty=(TextView)findViewById(R.id.tv_stockqty_rosh);
		ucost=(TextView)findViewById(R.id.tv_unitcost_rosh);
		
		assignqty=(EditText)findViewById(R.id.etv_assignqty_rosh);
		
		mDateDisplay = (TextView) findViewById(R.id.tv_date_rosh);        
	    mPickDate = (Button) findViewById(R.id.btn_date_rosh);

	    mPickDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
				
				
			}
		});
		
		
		//Get the current date 
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
 
        updateDisplay();
        
        lowstock=(Button)findViewById(R.id.btn_chqstock_nai_rosh);
		lowstock.setVisibility(View.INVISIBLE);
		
					    
	    assign=(Button)findViewById(R.id.btn_assign_rosh);
		
	    assign.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
				String qty1=stckqty.getText().toString();
				int stockQty=Integer.valueOf(qty1);
				
				String qty3=ucost.getText().toString();
				double unitCost=Double.valueOf(qty3);
				
				
				String qty2=assignqty.getText().toString();
				
				if(qty2.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please enter a quantity before assigning items.", Toast.LENGTH_SHORT).show();
				}
				
				else{
					int assignQty=Integer.valueOf(qty2);
				if(0<assignQty && assignQty<=stockQty)
				{
							
					DBCreater insert=new DBCreater(NewAssignItems.this);
					insert.open();
					insert.createrepstock(rep.getText().toString(), del.getText().toString(), ch, assignQty, assignQty, mDateDisplay.getText().toString(), mDateDisplay.getText().toString(), "NULL", unitCost);
					//insert.createPayment(rep.getText().toString(), mDateDisplay.getText().toString(), 0.00, "NULL", "NULL", 0.00, 0.00, "NULL");
					//abc();
					
					ContentValues cv = new ContentValues();
					cv.put(DBCreater.Key_delS_available_qty,(stockQty-assignQty));
					insert.updateDelear("Del_Stock", cv, "delS_del_id=? and delS_del_item_name=?",new String[] {del.getText().toString(),ch} );
			       
					System.out.println(cv);					
					insert.close();
					
					if(stockQty<50){
						lowstock.setVisibility(View.VISIBLE);
						
						}
					
					else{
					lowstock.setVisibility(View.INVISIBLE);
					}
					
					Dialog msg=new Dialog(NewAssignItems.this);
					msg.setTitle("Success Message");
					TextView text=new TextView(NewAssignItems.this);
					text.setText("You have successfully assigned items");
					msg.setContentView(text);
					msg.show();
					
					
				}
				
				
				else if(assignQty>stockQty)
				{
					
					lowstock.setVisibility(View.INVISIBLE);
					
					Dialog msg=new Dialog(NewAssignItems.this);
					msg.setTitle("Error Message");
					TextView text=new TextView(NewAssignItems.this);
					text.setText("Assigning qty should be less than the available quantity");
					msg.setContentView(text);
					msg.show();
				}
				
				else if(assignQty<=0)
				{
					lowstock.setVisibility(View.INVISIBLE);
					
					Dialog msg=new Dialog(NewAssignItems.this);
					msg.setTitle("Error Message");
					TextView text=new TextView(NewAssignItems.this);
					text.setText("Assigning qty should be greater than zero");
					msg.setContentView(text);
					msg.show();
				}
				}
			}
		});
	    
	    refresh=(Button)findViewById(R.id.btn_refresh_rosh);
		refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				assignqty.setText("");
				
				DBCreater insert=new DBCreater(NewAssignItems.this);
				insert.open();
				insert.createPayment(rep.getText().toString(), mDateDisplay.getText().toString(), 0.00, "NULL", "NULL", 0.00, 0.00, "NULL");
				insert.close();
						
			}
		});
		
		
		
	   
	    	}
	
	 //On create dialog method called by showDialog()   
	@Override
	  protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, 
	                        pDateSetListener,
	                        mYear, mMonth, mDay);
	        }
	        return null;
	    }


	
	public void loadItems(){
		DBCreater entry = new DBCreater(NewAssignItems.this);
		entry.openforread();
		List<String> lbl1 = entry.getAllItems((String)del.getText());
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lbl1);
			 
        // attaching data adapter to spinner
        item.setAdapter(dataAdapter);
        entry.close();
	}
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_assign_items, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long arg3) {
		// TODO Auto-generated method stub
		ch=parent.getItemAtPosition(position).toString();
				
		DBCreater entry = new DBCreater(NewAssignItems.this);
		entry.openforread();
		List<String> lbl2 = entry.fetchqty(del.getText().toString(), ch); 
		stckqty.setText(lbl2.get(0).toString());
		ucost.setText(lbl2.get(1).toString());
		
		String qty1=stckqty.getText().toString();
		int stockQty=Integer.valueOf(qty1);
		
		if(stockQty<50)
		{
			lowstock.setVisibility(View.VISIBLE);
		}
		
		else{
			lowstock.setVisibility(View.INVISIBLE);
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
