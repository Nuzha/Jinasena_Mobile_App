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

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRepHistory extends Activity implements OnClickListener{
	
	public static String packagename="com.NIRR.jenasena";
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	private TextView mStartDate;
	private Button mPickStartDate;
	
	private TextView mEndDate;
	private Button mPickEndDate;
	
	Button report2;
	
	private Button next;
	Button pdf;
	
	private TextView user;
	String usr;
	
	Spinner sp_rep;
			
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
	                    
	          else{
	        	  updateEndDisplay();
	          }
	             
	           }
	     };
	 
		
	            
	      public void updateStartDisplay(){
	            	
	         mStartDate.setText(
	         new StringBuilder()
	                                  // Month is 0 based so add 1
	         	.append(mYear).append("/")        
	            .append(mMonth + 1).append("/")
	            .append(mDay).append("")
	             );
	            	
	       }
	      
	      public void updateEndDisplay(){
          	
		         mEndDate.setText(
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
		setContentView(R.layout.activity_view_rep_history);
		
		user=(TextView)findViewById(R.id.tv_history_viewlogger_rosh);
		Intent intent = getIntent();
		usr = intent.getStringExtra(DelearMenu.packagename);
		user.setText(usr);
		
		sp_rep=(Spinner)findViewById(R.id.spinner_rh_rep_rosh);
		loadSpinnerData();
		
		mStartDate = (TextView) findViewById(R.id.tv_rh_datedisplay_rosh);        
	    mPickStartDate = (Button) findViewById(R.id.btn_rephistry_date1_rosh);
	    
	    mEndDate = (TextView) findViewById(R.id.tv_rh_date2display_rosh);        
	    mPickEndDate = (Button) findViewById(R.id.btn_rh_date2_rosh);
	    
	    next=(Button)findViewById(R.id.btn_rh_history_rosh);
	    
	    mPickStartDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
	    
	    mPickEndDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			count=2;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
	    
	  //Get the current date 
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
 
        updateStartDisplay();
		
		updateEndDisplay();
		
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent1=new Intent(ViewRepHistory.this,ViewMonthlyHistory.class);
				intent1.putExtra("key", mStartDate.getText().toString());
				intent1.putExtra("key2", mEndDate.getText().toString());
				intent1.putExtra("key3", sp_rep.getSelectedItem().toString());
				startActivity(intent1);
				
			}
		});
		
		report2=(Button)findViewById(R.id.pdf_rosh);
		report2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				create(usr);
				open();
				           
			}

			private void open() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory() + "/PDF";

				File file = new File(path, "MonthlyAssignedItemReport.pdf");
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


			private void create(String usr) {
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
					
					File file =new File(dir, "MonthlyAssignedItemReport.pdf");
					FileOutputStream fout=new FileOutputStream(file);
					PdfWriter.getInstance(doc, fout);
					doc.open();
					
					String logger="Document created by "+usr+" ";
					
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
					PdfPCell c1 = new PdfPCell(new Phrase("Del ID"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Item Name"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Assigned Quantity"));
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
					DBCreater db = new DBCreater(ViewRepHistory.this);
					db.openforread();
					//db.getdata();
					String dataset=db.getMonthlyItemReport((String)sp_rep.getSelectedItem(),(String)mStartDate.getText(),(String)mEndDate.getText());
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
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, 
	                        pDateSetListener,
	                        mYear, mMonth, mDay);
	        }
	        return null;
	    }
	
	
	
	public void loadSpinnerData(){
		DBCreater entry = new DBCreater(ViewRepHistory.this);
		entry.openforread();
		List<String> lables2 = entry.getAllLabelsRep((String)user.getText());
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables2);
			 
        // attaching data adapter to spinner
        sp_rep.setAdapter(dataAdapter);
        entry.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_rep_history, menu);
		return true;
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
/*
	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		Cursor cursor =(Cursor)sp_rep.getItemAtPosition(position);
        String name=cursor.getString(cursor.getColumnIndexOrThrow("rep_uname"));
        
        Intent p = new Intent(ViewRepHistory.this,ViewMonthlyHistory.class);
		p.putExtra("rep_uname", name);
		startActivity(p);
		
	}*/

}

