package com.NIRR.jenasena;

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
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import harmony.java.awt.Color;

//import com.lowagie.text.Document;

public class HistoryReport extends Activity implements OnItemSelectedListener {

	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	public static String package_name = "com.NIRR.jenasena";
	Button b1,b2;
	String rep;
	TextView t1;
	Spinner customer;
	int amount = 0;
	String edatemonth;
	String edateweek ;
	int expmonth;
	int expdate;
	int sum;
	
	Calendar cal = new GregorianCalendar();
    String cdate=""+cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.DAY_OF_MONTH)+"";
	
    int month = cal.get(Calendar.MONTH)+1;
	int year = cal.get(Calendar.YEAR);
	int day = cal.get(Calendar.DAY_OF_MONTH);
	
	// expmonth = month +1;
	 //expdate = day +7; 
	
	//int ckmonth= month + 5;
	//int ckdate= day + 25;
	
	
	
	// edatemonth = ""+year+"/"+expmonth+"/"+day+"";
	
	// edateweek = ""+year+"/"+month+"/"+expdate+"";
	
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_report);
		
		b1= (Button)findViewById(R.id.button1);
		b2= (Button)findViewById(R.id.button2);
		//Log.e("xxxxxxxxxxxx",edateweek);
		Intent intent=getIntent();
	     rep=intent.getStringExtra(RepMenu.packagename);
	     t1= (TextView)findViewById(R.id.textView2);
	     t1.setText(rep);
	     
	     customer=(Spinner)findViewById(R.id.spinner1);
	     customer.setOnItemSelectedListener(this);
	     
	     //System.out.println(""+year+"/"+ckmonth+"/"+day+"");
	     
	     //System.out.println(""+year+"/"+month+"/"+ckdate+"");
	     
		
	     // Loading spinner data from database
	      loadSpinnerData();
	      
	   
	     
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 //month=12;
				
				if(month==12){
					
					expmonth = 1;
					year = year+1;
					
				    edatemonth = ""+year+"/"+expmonth+"/"+day+"";
				}
				else{
					
					expmonth = month +1;
					edatemonth = ""+year+"/"+expmonth+"/"+day+"";
				}
				
				create( rep,"Not Paid",edatemonth,cdate);
				open();
				
			}
		});
		
        b2.setOnClickListener(new OnClickListener() {
        	
			@Override
			public void onClick(View v) {
				
				sum = day +7;
				if((month==1 || month==3 || month==5 || month==7 || month==8 || month==10) && sum > 31)
				{
					expdate = sum - 31;
					month = month + 1;
					edateweek = ""+year+"/"+month+"/"+expdate+"";
				}
				else if( month==2 && sum > 28)
				{
					expdate = sum - 28;
					month = month + 1;
					edateweek = ""+year+"/"+month+"/"+expdate+"";
				}
				else if((month==4 || month==6 || month==9 || month==11) && sum > 30)
				{
					expdate = sum - 30;
					month = month + 1;
					edateweek = ""+year+"/"+month+"/"+expdate+"";
				}
				else if(month==12 && sum > 31)
				{
					expdate = sum - 31;
					month = 1;
					year = year + 1;
					edateweek = ""+year+"/"+month+"/"+expdate+"";
				}
				else{
					expdate = sum;
					
					edateweek = ""+year+"/"+month+"/"+expdate+"";	
				}
				
				create1( rep,"Not Paid",edateweek,cdate);
				open1();
				
			}
		});
		
		
	}
    
  //load all customer names from the database
  		public void loadSpinnerData(){
  			DBCreater entry = new DBCreater(HistoryReport.this);
  			entry.openforread();
  			List<String> lables2 = entry.getAllLabelsCustomer(rep);
  			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables2);
  				 
  	        // attaching data adapter to spinner
  			customer.setAdapter(dataAdapter);
  	        entry.close();
  		}
	
	private void open() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";

		File file = new File(path, "Chamira.pdf");
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

	private void create(String rep,String pdate,String edatemonth,String cdate) {
		// TODO Auto-generated method stub
		Document doc = new Document();
		try{
			String parth = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PDF";
			File dir = new File(parth);
			if(!dir.exists())
				dir.mkdir();
				File file = new File(dir,"Chamira.pdf");
				FileOutputStream fOut = new FileOutputStream(file);
				PdfWriter.getInstance(doc, fOut);
				doc.open();
				String logger="Document Created by "+rep+" ";
				
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
				Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ic_launcher);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
				Image myImg = Image.getInstance(stream.toByteArray());
				myImg.setAlignment(Image.MIDDLE);

				//add image to document
				doc.add(myImg);
				Phrase footerText = new Phrase("This is an example of a footer");
				HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
				doc.setFooter(pdfFooter);
				
				
				PdfPTable table = new PdfPTable(4);
				PdfPCell c1 = new PdfPCell(new Phrase("Total Price"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Issued date"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Due Date"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Paid Cost"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				
				
				table.setHeaderRows(1);
				DBCreater db = new DBCreater(HistoryReport.this);
				db.openforread();
				//db.getdata();
				
				
				String dataset=db.getCustomerHistoryForDELPdf(rep,pdate,edatemonth,cdate,customer.getSelectedItem().toString(), amount);
				//"Rep01","NULL","2014/4/15"
				
				StringTokenizer token = new StringTokenizer(dataset,"*");
				String s11,s2,s3,s4;
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
			    	
			    	
				
			}
			
			//Anchor anc = new Anchor();
			Paragraph preface = new Paragraph();
			//Selection subcatpart = 
			preface.add(table);
			//Log.e("PDFCreator", "chami" );
			doc.add(preface);

			Toast.makeText(getApplicationContext(), "Created...", Toast.LENGTH_LONG).show();
		
			
		} catch (DocumentException de) {
			Log.e("PDFCreator", "DocumentException:" + de);
		} catch (IOException e) {      
			Log.e("PDFCreator", "ioException:" + e);
		} 
		finally
		{
			Log.e("PDFCreator", "close" );
			doc.close();
		}

		}

	
	private void open1() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";

		File file = new File(path, "Priyamanthi.pdf");
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

	private void create1(String rep,String pdate,String edateweek,String cdate) {
		// TODO Auto-generated method stub
		Document doc = new Document();
		try{
			String parth = Environment.getExternalStorageDirectory().getAbsolutePath()+"/PDF";
			File dir = new File(parth);
			if(!dir.exists())
				dir.mkdir();
				File file = new File(dir,"Priyamanthi.pdf");
				FileOutputStream fOut = new FileOutputStream(file);
				PdfWriter.getInstance(doc, fOut);
				doc.open();
				String logger="Document Created by "+rep+" ";
				
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
				Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ic_launcher);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
				Image myImg = Image.getInstance(stream.toByteArray());
				myImg.setAlignment(Image.MIDDLE);

				//add image to document
				doc.add(myImg);
				Phrase footerText = new Phrase("This is an example of a footer");
				HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
				doc.setFooter(pdfFooter);
				
				
				PdfPTable table = new PdfPTable(4);
				PdfPCell c1 = new PdfPCell(new Phrase("Total Price"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Issued date"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Due Date"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				c1 = new PdfPCell(new Phrase("Paid Cost"));
				c1.setBackgroundColor(Color.LIGHT_GRAY);
				c1.setHorizontalAlignment(5);
				table.addCell(c1);
				
				table.setHeaderRows(1);
				DBCreater db = new DBCreater(HistoryReport.this);
				db.openforread();
				//db.getdata();
				String dataset=db.getCustomerHistoryForDELPdfweek(rep,pdate,edateweek,cdate,customer.getSelectedItem().toString(),amount);
				//"Rep01","NULL","2014/4/15"
				
				StringTokenizer token = new StringTokenizer(dataset,"*");
				String s11,s2,s3,s4;
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
			    	
			    	
				
			}
			
			//Anchor anc = new Anchor();
			Paragraph preface = new Paragraph();
			//Selection subcatpart = 
			preface.add(table);
			doc.add(preface);

			Toast.makeText(getApplicationContext(), "Created...", Toast.LENGTH_LONG).show();
		
			
		} catch (DocumentException de) {
			Log.e("PDFCreator", "DocumentException:" + de);
		} catch (IOException e) {      
			Log.e("PDFCreator", "ioException:" + e);
		} 
		finally
		{
			doc.close();
		}

		}
	
	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_history_report, menu);
		return true;
	}*/

	

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
}
