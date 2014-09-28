package com.NIRR.jenasena;

import harmony.java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchRep extends Activity{
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	String s1;
	Intent intent;
	public static String packagename="com.NIRR.jenasena";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_rep);
		 intent=getIntent();
		    final String s1=intent.getStringExtra(DelearMenu.packagename);
		   // Log.e("commingtext",s1);
			Button pdf = (Button)findViewById(R.id.bt_searchrep_pdf_Isuru);
			pdf.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CreateRepeportForDel(s1);
					openRepeportForDel();
					
				}

				private void openRepeportForDel() {
					// TODO Auto-generated method stub
					Intent intent = new Intent(Intent.ACTION_VIEW);
					String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";

					File file = new File(path, "DelRepReport.pdf");
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

				private void CreateRepeportForDel(String s1) {
					// TODO Auto-generated method stub
					Document doc  =new Document();
					try {
						//give the part for the doc
						String parth = Environment.getExternalStorageDirectory().getAbsolutePath() + "/PDF";
						//make object from file
						File dir = new File(parth);
						//make new pdf if not extit
						if(!dir.exists())
							dir.mkdir();
						//
						File file = new File(dir,"DelRepReport.pdf");
						FileOutputStream fOut = new FileOutputStream(file);
						PdfWriter.getInstance(doc, fOut);
						doc.open();
						String logger="Document Created by "+s1+" ";
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
						Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.repdatareport);
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
						c1 = new PdfPCell(new Phrase("Rep Name"));
						c1.setBackgroundColor(Color.LIGHT_GRAY);
						c1.setHorizontalAlignment(5);
						table.addCell(c1);
						c1 = new PdfPCell(new Phrase("Rep Address"));
						c1.setBackgroundColor(Color.LIGHT_GRAY);
						c1.setHorizontalAlignment(5);
						table.addCell(c1);
						c1 = new PdfPCell(new Phrase("Rep Email"));
						c1.setBackgroundColor(Color.LIGHT_GRAY);
						c1.setHorizontalAlignment(5);
						table.addCell(c1);
						c1 = new PdfPCell(new Phrase("Rep Phon"));
						c1.setBackgroundColor(Color.LIGHT_GRAY);
						c1.setHorizontalAlignment(5);
						table.addCell(c1);
						table.setHeaderRows(1);
						DBCreater db = new DBCreater(SearchRep.this);
						db.openforread();
						//db.getdata();
						String dataset=db.getRepDataForDELPdf(s1);
						StringTokenizer token = new StringTokenizer(dataset,"*");
						String s11,s2,s3,s4,s5;
						while(token.hasMoreTokens()){
							
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
			});
			dbHelper = new DBCreater(this);
			dbHelper.open();
			
			displayListView(s1);
	}
	private void displayListView(String getter) {
		// TODO Auto-generated method stub
		Cursor cursor = dbHelper.fetchallrep(getter);
		Toast.makeText(getApplicationContext(), " "+cursor.getString(0)+"", Toast.LENGTH_SHORT).show();
		String[] columns = new String[] {
				//get the needed columns of the db and feed them in to string array
		
			DBCreater.Key_rep_Name
		};
		
		int[] to = new int[]{
				//get the textboxs in xml layout,which going to display the values in to integer array  
				R.id.tv_demo_search_text_Isuru
		};
		//address the xml list view to java
		final ListView listView = (ListView)findViewById(R.id.lv_searchrep_cuzlist_Isuru);
		// feed the context,displaying layout,data of db, data source of the data and distination of the data
		if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data", Toast.LENGTH_SHORT).show();
		}
		else{
		 dataAdapter = new SimpleCursorAdapter(this, R.layout.demo_search, cursor, columns, to,0);
		 //load the data to list view
		listView.setAdapter(dataAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor =(Cursor)listView.getItemAtPosition(arg2);
				//get the value of the customer name from the clicked listitem
				String name=cursor.getString(cursor.getColumnIndexOrThrow("rep_name"));
				String cusid=cursor.getString(cursor.getColumnIndexOrThrow("_id"));
				//feed the clicked customer name to toast masssage
				//Toast.makeText(getApplicationContext(), " Company Owner is  "+cursor.getString(cursor.getColumnIndexOrThrow("customer_name")), Toast.LENGTH_SHORT).show();
				Intent p = new Intent(SearchRep.this, RepData.class);
				p.putExtra(packagename, name+"*"+intent.getStringExtra(DelearMenu.packagename));
				startActivity(p);
				
			}
			
		});
		EditText myFilter = (EditText)findViewById(R.id.et_searchrep_cuzname_Isuru);
		myFilter.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				//what happen when user type on the text view
				dataAdapter.getFilter().filter(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}
			
		});
		dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {

			@Override
			public Cursor runQuery(CharSequence arg0) {
				// TODO Auto-generated method stub
				return dbHelper.fetchrepbynamelike(arg0.toString(),intent.getStringExtra(DelearMenu.packagename));
			}
			
		});
		
	}
	
	}

}
