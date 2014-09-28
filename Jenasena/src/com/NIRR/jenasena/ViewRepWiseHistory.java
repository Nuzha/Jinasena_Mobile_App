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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRepWiseHistory extends Activity implements OnItemSelectedListener,OnClickListener{
	
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	
	TextView displayStart;
	TextView displayEnd;
	String sdate,edate;
	Button view;
	Spinner rep;
	Button repreport;
	
	TextView user;
	String usr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_rep_wise_history);
		
		user=(TextView)findViewById(R.id.tv_logger_load_rosh);
				
		view=(Button)findViewById(R.id.btn_mai_view_rosh);
		
		displayStart=(TextView)findViewById(R.id.tv_mai_viewdate1_rosh);
		displayEnd=(TextView)findViewById(R.id.tv_mai_viewdate2_rosh);
				
		Intent intent = getIntent();
		usr = intent.getStringExtra("delkey");
		user.setText(usr);
		
		Intent intent1 = getIntent();
		sdate = intent1.getStringExtra("key");
		displayStart.setText(sdate);
		
		Intent intent2 = getIntent();
		edate = intent2.getStringExtra("key2");
		displayEnd.setText(edate);
		
		rep=(Spinner)findViewById(R.id.spn_repid_rosh);
		rep.setOnItemSelectedListener(this);
		loadSpinnerData();
		
		repreport=(Button)findViewById(R.id.btn_rep_report_rosh);
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();
		
		//displayList();
	    
	    view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				displayList();
			}
		});
	    
	    
	    repreport.setOnClickListener(new OnClickListener() {
			
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

				File file = new File(path, "RepHistoryReport.pdf");
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
					
					File file =new File(dir, "RepHistoryReport.pdf");
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
					DBCreater db = new DBCreater(ViewRepWiseHistory.this);
					db.openforread();
					//db.getdata();
					String dataset=db.getRepHistoryReport((String)rep.getSelectedItem(),(String)displayStart.getText(),(String)displayEnd.getText());
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
	
	public void loadSpinnerData(){
		DBCreater entry = new DBCreater(ViewRepWiseHistory.this);
		entry.openforread();
		List<String> lables2 = entry.getAllLabelsRep((String)user.getText());
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lables2);
		// attaching data adapter to spinner
        rep.setAdapter(dataAdapter);
        entry.close();
	}
	
	
	private void displayList(){

	    Cursor cursor = dbHelper.fetchmonthlyrecords((String)rep.getSelectedItem(),(String)displayStart.getText(),(String)displayEnd.getText());
	    String[] columns = new String[] {
	       DBCreater.Key_repS_item_name,
	       DBCreater.Key_repS_assigned_qty,
	       DBCreater.Key_repS_unit_price,
	       DBCreater.Key_repS_assigned_date,
	       DBCreater.Key_repS_available_qty
	   };

	    int[] to = new int[]{
	        R.id.tv_sh_name_rosh,
	        R.id.tv_sh_assqty_rosh,
	        R.id.tv_sh_cost_rosh,
	        R.id.tv_sh_date_rosh,
	        R.id.tv_sh_availqty_rosh
	   };

	  final ListView lv = (ListView)findViewById(R.id.lv_mai_result_rosh);
      dataAdapter = new SimpleCursorAdapter(this, R.layout.single_history, cursor, columns, to,0);
      lv.setAdapter(dataAdapter);
      
      if(cursor.getCount()==0)
      {	
    	  AlertDialog alertDialog = new AlertDialog.Builder(ViewRepWiseHistory.this).create();
    	  alertDialog.setTitle("No Results Found");
    	  alertDialog.setMessage("You haven't assigned items to the selected rep during the given period.");
    	  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    		  public void onClick(DialogInterface dialog, int which) {
    		} });
    	      	  
    	  alertDialog.show();
    	  
    	}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_rep_wise_history, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
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
