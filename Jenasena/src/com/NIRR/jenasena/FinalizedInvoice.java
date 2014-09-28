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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class FinalizedInvoice extends Activity {

	TextView repID,cusID,invoiceID,amount;
	EditText date;
	Button genInvoice,datePick,viewInvoReport;
	String cusid,repid,invoiceId;
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;
 
	private int pYear;
	 private int pMonth;
	 private int pDay;
	    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
	 static final int DATE_DIALOG_ID = 0;
	
		//int buttonCount=0;
		
	    private DatePickerDialog.OnDateSetListener pDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {
	 
	               

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						 pYear = year;
		                    pMonth = monthOfYear;
		                    pDay = dayOfMonth;
		                    
		                    //if(buttonCount==1)
		                    updateDisplay();
						
					}
	            };
	 
		
	            
	            public void updateDisplay(){
	            	
	            	  date.setText(
	                          new StringBuilder()
	                                  // Month is 0 based so add 1
	                          .append(pYear).append("/")        
	                          .append(pMonth + 1).append("/")
	                          .append(pDay).append("")
	                                  );
	            	
	            }


	
	
	
	
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finalized_invoice);
		
		
		repID=(TextView)findViewById(R.id.tvfinrepId);
		cusID=(TextView)findViewById(R.id.tvfcus);
		genInvoice=(Button)findViewById(R.id.btfgeninvoice);
		datePick=(Button)findViewById(R.id.btfdtepick);
		date=(EditText)findViewById(R.id.etfDate);
		invoiceID=(TextView)findViewById(R.id.tvfInvoiceId);
		amount=(TextView)findViewById(R.id.tvfamount);
		viewInvoReport=(Button)findViewById(R.id.btViewInvoReprt);
		
		Intent intent = getIntent();
		cusid = intent.getStringExtra("cus_id");
		
		Intent intent2=getIntent();
		repid=intent2.getStringExtra("rep_id");
		
		repID.setText(repid);
		cusID.setText(cusid);
		
		

viewInvoReport.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {

				create(repID.getText().toString());
				open();
				
				
			}

			private void open() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory() + "/PDF";

				File file = new File(path, "InvoiceReport.pdf");
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
					
					File file =new File(dir, "InvoiceReport.pdf");
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
					
					
					PdfPTable table = new PdfPTable(4);
					PdfPCell c1 = new PdfPCell(new Phrase("Item Name"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Sold Quantity"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Unit Price"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Total Price"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					table.setHeaderRows(1);
					DBCreater db = new DBCreater(FinalizedInvoice.this);
					db.openforread();
					//db.getdata();
					String dataset=db.getInvoiceDetails(invoiceID.getText().toString());
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

		

	//date picker
	datePick.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		showDialog(DATE_DIALOG_ID);
		

	}
});	

		 /** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);
		
		updateDisplay();
		

			genInvoice.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View v) {
				dbHelper = new DBCreater(FinalizedInvoice.this);
				dbHelper.open();
		
				dispalyInvoice();
		
		
	}

	
});





		}
	
	
	
	private void dispalyInvoice() {
		
		DBCreater entry = new DBCreater(FinalizedInvoice.this);
		entry.openforread();
		List<String> lables = entry.viewInvoceOld(repid, cusid, date.getText().toString());

		if(lables.size()==0){
			
			Dialog d1=new Dialog(FinalizedInvoice.this);
			d1.setTitle("No Records");
			TextView t=new TextView(FinalizedInvoice.this);
			t.setText("There are no records invoiced on "+date.getText().toString()+" to "+cusID.getText().toString());
			d1.setContentView(t);
			d1.show();
			
			
		}
		
		else{
		String invoice=lables.get(1).toString();
		String totalAmount=lables.get(5).toString();
		
		System.out.println("Invoice iddddd "+invoice);
		System.out.print("Billll "+ totalAmount);
		
		
		
		
		Cursor cursor = dbHelper.disInvoice(repID.getText().toString(),cusID.getText().toString(),invoice);
		double sum=0.0;
		String[] columns;
		
		if(cursor.getCount()==0){
				
			
			Dialog d1=new Dialog(FinalizedInvoice.this);
			d1.setTitle("No Records");
			TextView t=new TextView(FinalizedInvoice.this);
			t.setText("There are no records invoiced on "+date.getText().toString()+" to "+cusID.getText().toString());
			d1.setContentView(t);
			d1.show();
			
			
		}
		
		
		else if(cursor.getCount()!=0){
			
			
			
		
	 if (cursor.moveToFirst()) {
            do {
                //labels.add(cursor.getString(0));
                //labels.add(cursor.getString(1));
               sum=sum+ Double.parseDouble(cursor.getString(4));
               invoiceId=cursor.getString(5).toString();
            } while (cursor.moveToNext());//to track  the coloum
        }
	
		
		invoiceID.setText(invoiceId);
		
	columns = new String[] { 
			DBCreater.Key_Cart_item_name,
			DBCreater.Key_Cart_sold_quantity,
			DBCreater.Key_Cart_unit_price,
			DBCreater.Key_Cart_total_price
			
			//DBCreater.Key_repS_assigned_qty 
			
			
			
	};
	
	int[] to = new int[]{
			R.id.tvItemName,
			R.id.tvSoldQty,
			R.id.tvUnitPrice,
			R.id.tvtotal
		
	};
	
	
	String s=Double.toString(sum);
	amount.setText(s);
	
	dataAdapter1 = new SimpleCursorAdapter(this, R.layout.visingleraw, cursor, columns, to,0);
	final ListView viewInvoice = (ListView)findViewById(R.id.lvOldInvo);
	viewInvoice.setAdapter(dataAdapter1);
	
	
		
		
	}
	
	}
	}
	
	@Override
	  protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this, 
	                        pDateSetListener,
	                        pYear, pMonth, pDay);
	        }
	        return null;
	    }
	
	

	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finalized_invoice, menu);
		return true;
	}

}
