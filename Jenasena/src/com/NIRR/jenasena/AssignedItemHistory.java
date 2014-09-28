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
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AssignedItemHistory extends Activity implements OnItemSelectedListener{
	
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView logger,startDate,endDate;
	String usr,sdate1,edate1,report;
	Spinner items;
	Button itmhistory,itmreport;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assigned_item_history);
		
		logger=(TextView)findViewById(R.id.tv_logger_itemload_rosh);
		Intent intent = getIntent();
		usr = intent.getStringExtra("delkey");
		logger.setText(usr);
		
		startDate=(TextView)findViewById(R.id.tv_aih_viewdate1_rosh);
		Intent intent1 = getIntent();
		sdate1 = intent1.getStringExtra("key");
		startDate.setText(sdate1);
		
		
		endDate=(TextView)findViewById(R.id.tv_aih_viewdate2_rosh);
		Intent intent2 = getIntent();
		edate1 = intent2.getStringExtra("key2");
		endDate.setText(edate1);
		
		items=(Spinner)findViewById(R.id.spn_aih_repid_rosh);
		items.setOnItemSelectedListener(this);
		loadItems();
		
		itmhistory=(Button)findViewById(R.id.btn_aih_view_rosh);
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();
		
		itmhistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				displayHistory();
				
			}
		});
		
		itmhistory=(Button)findViewById(R.id.btn_aih_report_rosh);
		
		itmhistory.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				create(usr);
				open();
				
			}
			
			
			private void open() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory() + "/PDF";

				File file = new File(path, "AssignedItemHistoryReport.pdf");
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
					
					File file =new File(dir, "AssignedItemHistoryReport.pdf");
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
					Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ic_launcher);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
					Image myImg = Image.getInstance(stream.toByteArray());
					myImg.setAlignment(Image.MIDDLE);

					//add image to document
					doc.add(myImg);
					Phrase footerText = new Phrase("This is an example of a footer");
					HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
					doc.setFooter(pdfFooter);
					
					
					PdfPTable table = new PdfPTable(5);
					PdfPCell c1 = new PdfPCell(new Phrase("Item Name"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Rep ID"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Assigned Date"));
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
					table.setHeaderRows(1);
					DBCreater db = new DBCreater(AssignedItemHistory.this);
					db.openforread();
					//db.getdata();
					String dataset=db.getItemHistoryReport((String)items.getSelectedItem(),(String)startDate.getText(),(String)endDate.getText());
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
	
	public void loadItems(){
		DBCreater entry = new DBCreater(AssignedItemHistory.this);
		entry.openforread();
		List<String> lbl1 = entry.getAllItemsforReport((String)logger.getText());
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lbl1);
			 
        // attaching data adapter to spinner
        items.setAdapter(dataAdapter);
        entry.close();
	}
	
	public void displayHistory(){
		Cursor cursor=dbHelper.fetchitemwiseRecords((String)items.getSelectedItem(), (String)startDate.getText(), (String)endDate.getText());
			   String[] columns = new String[] {
			   DBCreater.Key_repS_rep_id,
		       DBCreater.Key_repS_assigned_date,
		       DBCreater.Key_repS_assigned_qty,
		       DBCreater.Key_repS_unit_price
		       
		       
		   };

		    int[] to = new int[]{
		        R.id.txt_repid_show_rosh,
		        R.id.txt_date_show_rosh,
		        R.id.txt_qty_show_rosh,
		        R.id.txt_cost_show_rosh
		        
		   };

		  final ListView display = (ListView)findViewById(R.id.lv_aih_display_rosh);
	      dataAdapter = new SimpleCursorAdapter(this, R.layout.sigle_itmsearch, cursor, columns, to,0);
	      display.setAdapter(dataAdapter);
	      
	      if(cursor.getCount()==0)
	      {	
	    	  AlertDialog alertDialog = new AlertDialog.Builder(AssignedItemHistory.this).create();
	    	  alertDialog.setTitle("No Results Found");
	    	  alertDialog.setMessage("This item is not assigned to any representative yet.");
	    	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	    		  public void onClick(DialogInterface dialog, int which) {
	    		} });
	    	      	  
	    	  alertDialog.show();
	    	  
	    	}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.assigned_item_history, menu);
		return true;
	}

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
