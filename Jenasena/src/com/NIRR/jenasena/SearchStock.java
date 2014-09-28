package com.NIRR.jenasena;

import harmony.java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchStock extends Activity implements OnClickListener,OnItemSelectedListener{
	
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	Spinner spinner_rep;
	//Spinner spinner_date;
	TextView logname,assigneddate,total;
	Button srch,dlt,report,assbtn;
	String ch,ch2,rep1,date,del1;
	
	double totamount=0.00;
	
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	private int mYear;
	private int mMonth;
	private int mDay;
		    
	static final int DATE_DIALOG_ID = 0;
	int count=0;
	
	   //call back listner for the date picker dialog
		private DatePickerDialog.OnDateSetListener pDateSetListener =new DatePickerDialog.OnDateSetListener() {
	 
	    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
	          mYear = year;
	          mMonth = monthOfYear;
	          mDay = dayOfMonth;
	                    
	          if(count==1){
	             updateStartDisplay();
	          }
	                               
	         }
	     };
	 
		
	            
	      public void updateStartDisplay(){
	            	
	         assigneddate.setText(
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
		setContentView(R.layout.activity_search_stock);
		
		ch2=null;
		
		// Spinner element
		spinner_rep = (Spinner) findViewById(R.id.r_srep);
		 srch=(Button)findViewById(R.id.r_bSearch);
		
		// Spinner click listener
        spinner_rep.setOnItemSelectedListener(this);
        
        total=(TextView)findViewById(R.id.tv_sc_totamt_rosh);
        
        assigneddate=(TextView)findViewById(R.id.tv_date_sc_rosh);
        assbtn=(Button)findViewById(R.id.btn_sc_date_rosh);
        
        assbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				total.setText("");
				
			}
		});
		
		//Get the current date 
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
        
        updateStartDisplay();
        
        
        dlt=(Button)findViewById(R.id.r_bdelete);
                
        logname=(TextView)findViewById(R.id.tv_loggedusername_rosh);
        Intent intent = getIntent();
		ch = intent.getStringExtra(DelearMenu.packagename);
		logname.setText(ch);
		        
      // Loading spinner data from database
      loadSpinnerData();
      //loadSpinnerDate();
      delete();
            
      dbHelper = new DBCreater(this);
      dbHelper.open();  
	    
	    srch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				displaysearch();
			}
		});
	    
	    report=(Button)findViewById(R.id.btn_report1_rosh);
	    report.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				create(rep1,date);
				open();
				
			}
			
			private void open() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory() + "/PDF";

				File file = new File(path, "DailyStock.pdf");
				intent.setDataAndType( Uri.fromFile( file ), "application/pdf" );
				startActivity(intent.setDataAndType( Uri.fromFile( file ), "application/pdf" ));
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				Intent target = Intent.createChooser(intent, "Open File");
				try {
				    startActivity(target);
				} catch (ActivityNotFoundException e) {
					Log.e("PDFCreator", "ActivityNotFoundException:" + e);
				}
				
			}
			

			private void create(String rep1, String date) {
				// TODO Auto-generated method stub
				
			Document doc=new Document();
				try{
					//give the part for the doc
					String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";
					//make object from file
					File dir = new File(path);
					//make new pdf if not extit
					if(!dir.exists())
						dir.mkdir();
					
					File file =new File(dir, "DailyStock.pdf");
					FileOutputStream fout=new FileOutputStream(file);
					PdfWriter.getInstance(doc, fout);
					doc.open();
					
					String logger="Document created by "+del1+" ";
					
					Calendar cal = new GregorianCalendar();
			        String am_pm = (cal.get(Calendar.AM_PM)==0)?"AM":"PM";
					String pdfheder=logger+" Date  "+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"        Created on  "+cal.get(Calendar.HOUR)+" : "+cal.get(Calendar.MINUTE)+" : "+cal.get(Calendar.SECOND)+" "+am_pm +"";
					Paragraph p1= new Paragraph(pdfheder);
					Font paraFont = new Font(Font.HELVETICA);
					paraFont.setSize(36);
					paraFont.setColor(Color.MAGENTA);
					p1.setAlignment(Paragraph.ALIGN_LEFT);
					p1.setFont(paraFont);
					doc.add(p1);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.delear);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
					Image myImg = Image.getInstance(stream.toByteArray());
					myImg.setAlignment(Image.MIDDLE);

					//add image to document
					doc.add(myImg);
					Phrase footerText = new Phrase("This is an example of a footer");
					HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
					doc.setFooter(pdfFooter);
					
					
					PdfPTable table = new PdfPTable(5);
					PdfPCell c1 = new PdfPCell(new Phrase("Rep Id"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Item Name"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Assigned Qty"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Unit Price"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Assigned Date"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					table.setHeaderRows(1);
					DBCreater db = new DBCreater(SearchStock.this);
					db.openforread();
					//db.getdata();
					String dataset=db.getDailyReport((String)spinner_rep.getSelectedItem(),(String)assigneddate.getText());
					StringTokenizer token = new StringTokenizer(dataset,"*");
					String s11,s2,s3,s4,s5;
					while(token.hasMoreTokens()){
					//	StringTokenizer childToken = new StringTokenizer(token.nextToken(),"\t");
						s11 = token.nextToken();
				    	table.addCell(s11);
				    	s2 = token.nextToken();
				    	table.addCell(s2);
				    	s3 = token.nextToken();
				    	table.addCell(s3);
				    	s4 = token.nextToken();
				    	table.addCell(s4);
				    	s5 = token.nextToken();
				    	table.addCell(s5);
				    	
				    	
					}
					//Anchor anc = new Anchor();
					Paragraph preface = new Paragraph();
					//Selection subcatpart = 
					preface.add(table);
					doc.add(preface);

					Toast.makeText(getApplicationContext(), "Report Created...", Toast.LENGTH_LONG).show();
				}
				
				catch (DocumentException de) {
					Log.e("PDFCreator", "DocumentException:" + de);
				} catch (IOException e) {
					Log.e("PDFCreator", "ioException:" + e);
				} 
				finally
				{
					doc.close();
				}
				
			}

		});
	    
	    
	    	 
	 }
	
	
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this, 
                        pDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
	}
	
	
	
	public void displaysearch() {
		
		System.out.println("rep "+spinner_rep.getSelectedItem().toString());
		System.out.println("date "+assigneddate.getText().toString());
		
		Cursor c=dbHelper.getRelatedItems(spinner_rep.getSelectedItem().toString(),assigneddate.getText().toString());
		    String[] columns = new String[] {
		        DBCreater.Key_repS_item_name,
		        DBCreater.Key_repS_assigned_qty,
		        DBCreater.Key_repS_unit_price
		    };
		    
		    int[] to = new int[]{
		            R.id.r_singlename,
		            R.id.r_singleqty,
		            R.id.r_singleunitcost
		    };
		    
		    
		 dataAdapter=new SimpleCursorAdapter(this, R.layout.single_search, c, columns, to, 0);
		 final ListView lv_r=(ListView)findViewById(R.id.r_lvstock);
		 lv_r.setAdapter(dataAdapter);
		 
		 
		 if (c.moveToFirst()) {
		       do {
		          totamount=totamount+ (Integer.parseInt(c.getString(4)))*(Double.parseDouble(c.getString(5)));
		          } while (c.moveToNext());//to track  the coloum
		   }
		   String amount=Double.toString(totamount);
		   amount=String.format("%.2f", totamount);
		   total.setText(amount);
		   
	
	
		 
		 lv_r.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view,int position, long id) {
			Cursor cursor =(Cursor)lv_r.getItemAtPosition(position);
			String itmname=cursor.getString(cursor.getColumnIndexOrThrow("repS_item_name"));
			String qty=cursor.getString(cursor.getColumnIndexOrThrow("repS_assigned_qty"));
			String uprice=cursor.getString(cursor.getColumnIndexOrThrow("repS_unit_price"));
			
			String sid=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
			Toast.makeText(getApplicationContext(),"Now you can delete the selected record from database.", Toast.LENGTH_SHORT).show();
			ch=itmname;
			           
			switch(position){
			   		
			  default:
			  Intent p2 = new Intent(SearchStock.this,DeleteAssignItems.class);
			  p2.putExtra("key1", logname.getText().toString());
			  p2.putExtra("repS_rep_id", spinner_rep.getSelectedItem().toString());
			  p2.putExtra("repS_assigned_date", assigneddate.getText().toString());
			  p2.putExtra("key4", itmname);
			  p2.putExtra("key5", qty);
			  p2.putExtra("key6", uprice);
			  p2.putExtra("id", id);
			  startActivity(p2);
			  break;
			  }
			}

		});
		
		    if(c.getCount()==0)
		    {
		    	Dialog dlg = new Dialog(SearchStock.this);
		  		dlg.setTitle("Search failed");
		  		TextView view = new TextView(SearchStock.this);
		  		view.setText("No records found");
		  		dlg.setContentView(view);
		  		dlg.show();
		    }
	}
	

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		}

	//load all rep names from the database
		public void loadSpinnerData(){
			DBCreater entry = new DBCreater(SearchStock.this);
			entry.openforread();
			List<String> lables2 = entry.getAllLabelsRep((String)logname.getText());
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables2);
				 
	        // attaching data adapter to spinner
	        spinner_rep.setAdapter(dataAdapter);
	        entry.close();
		}
		
				
		
		public void delete(){
			dlt.setOnClickListener(new OnClickListener() {
				 
				   @Override
				   public void onClick(View v) {
					   
					   DBCreater insert=new DBCreater(SearchStock.this);
					   insert.open();
					   ContentValues cv = new ContentValues();
					   cv.put(DBCreater.Key_delS_payment_total_cost,totamount);
					   cv.put(DBCreater.Key_delS_payment_status,"Pending");
					   //cv.put(DBCreater.Key_delS_payment_remaining_amount,totamount);
					   insert.updateDelear("Dealer_payments", cv, "delS_payment_rep_id=? and delS_payment_assign_date=?",new String[] {spinner_rep.getSelectedItem().toString(),assigneddate.getText().toString()} );
				       insert.close();
						
						Toast.makeText(getApplicationContext(),"Successfully Updated ", Toast.LENGTH_SHORT).show();
					   
					   
				   }
				  });
		}
		
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_stock, menu);
		return true;
	}

}
