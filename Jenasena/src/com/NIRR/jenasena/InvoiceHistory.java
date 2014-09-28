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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class InvoiceHistory extends Activity {

	TextView repid,startDate,endDate;
	String repID;
	Button invoiceReport,email,buStarDate,buEndDate,buViewInvoices;
    public static String packagename="com.NIRR.redistributionsystem";
	String date,delEmail;
	
	
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;
	
	private int pYear;
	 private int pMonth;
	 private int pDay;
	    /** This integer will uniquely define the dialog to be used for displaying date picker.*/
	 static final int DATE_DIALOG_ID = 0;
	
	 int buttonCount=0;
		
	    private DatePickerDialog.OnDateSetListener pDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {
	 
	               

					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						 pYear = year;
		                    pMonth = monthOfYear;
		                    pDay = dayOfMonth;
		                    
		                    if(buttonCount==1){
		                    updateStartDate();
		                    }
		                    
		                    if(buttonCount==2){
		                    updateEndDate();
		                    	
		                    }
						
					}
	            };
	 
		
	            
	            public void updateStartDate(){
	            	
	            	
	            	  date=(new StringBuilder()
	                                  // Month is 0 based so add 1
	                          .append(pYear).append("/")        
	                          .append(pMonth + 1).append("/")
	                          .append(pDay).append("")
	                                  ).toString();
	            	  
	            	  startDate.setText(date);
	            	
	            }


	            public void updateEndDate(){
	            	
	            	
	            	  date=(new StringBuilder()
	                                  // Month is 0 based so add 1
	                          .append(pYear).append("/")        
	                          .append(pMonth + 1).append("/")
	                          .append(pDay).append("")
	                                  ).toString();
	            	  
	            	  endDate.setText(date);
	            	
	            }
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoice_history);
		repid=(TextView)findViewById(R.id.tv_repid_invoiceHistory);
		invoiceReport=(Button)findViewById(R.id.bt_ViewInvoHistryReport_invoiceHistory);
		email=(Button)findViewById(R.id.bt_sendEmail_invoiceHistory);
		startDate=(TextView)findViewById(R.id.tv_startDate_invoiceHistory);
		endDate=(TextView)findViewById(R.id.tv_endDate_invoiceHistory);
		buStarDate=(Button)findViewById(R.id.bt_startDate_invoiceHistory);
		buEndDate=(Button)findViewById(R.id.bt_endDate_invoiceHistory);
		buViewInvoices=(Button)findViewById(R.id.bt_viewInvoice_invoiceHistory);
		
		
		
		
		
		Intent intent = getIntent();
		repID = intent.getStringExtra("repid");
		repid.setText(repID);
		
		
		
		 /** Get the current date */
		final Calendar cal = Calendar.getInstance();
		pYear = cal.get(Calendar.YEAR);
		pMonth = cal.get(Calendar.MONTH);
		pDay = cal.get(Calendar.DAY_OF_MONTH);
		
		String delId=repID.substring(0, 5);
		System.out.println(delId);
		
		
		DBCreater entry = new DBCreater(InvoiceHistory.this);
		entry.openforread();
		List<String> lables = entry.getDealerEmail(delId);
		delEmail=lables.get(0).toString();
		
	    System.out.println("Email of dealer "+ delEmail);
		
		
		
		
		updateStartDate();
		updateEndDate();
		
		
		System.out.println("dateeeeeee" + date);
		System.out.println("ina all invoices");
		
		buViewInvoices.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dbHelper = new DBCreater(InvoiceHistory.this);
				dbHelper.open();
				
				displayInvoDerails();
				
				
			}
		});
	
		
		
		buStarDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buttonCount=1;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		
		
		buEndDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				buttonCount=2;
				showDialog(DATE_DIALOG_ID);
				
			}
		});
		
		email.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent n=new Intent(InvoiceHistory.this,EmailDailyInvoiceDetails.class);
				n.putExtra("delEmail", delEmail);
				startActivity(n);
				
			}
		});
		
		
		invoiceReport.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				create(repID);
				open();
				
				
			
				
			}
			
			
			private void open() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory() + "/PDF";

				File file = new File(path, "DailyInvoiceReport.pdf");
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
					
					File file =new File(dir, "DailyInvoiceReport.pdf");
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
					Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.jininvo);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100 , stream);
					Image myImg = Image.getInstance(stream.toByteArray());
					myImg.setAlignment(Image.MIDDLE);

					//add image to document
					doc.add(myImg);
					Phrase footerText = new Phrase("This is an example of a footer");
					HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
					doc.setFooter(pdfFooter);
					
					
					PdfPTable table = new PdfPTable(6);
					PdfPCell c1 = new PdfPCell(new Phrase("Custeomer Id"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Invoice Id"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Invoiced Date"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Total Amount"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Paid Amount"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					c1 = new PdfPCell(new Phrase("Amount to be Paid"));
					c1.setBackgroundColor(Color.LIGHT_GRAY);
					c1.setHorizontalAlignment(5);
					table.addCell(c1);
					table.setHeaderRows(1);
					DBCreater db = new DBCreater(InvoiceHistory.this);
					db.openforread();
					//db.getdata();
					String dataset=db.getDailyInvoiceDateils(repID,date);
					StringTokenizer token = new StringTokenizer(dataset,"*");
					String s11,s2,s3,s4,s5,s6;
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
				    	s6 = token.nextToken();
				    	table.addCell(s6);
				    	
				    					    	
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

	private void displayInvoDerails() {

		String start_Date=startDate.getText().toString();
		String end_Date=endDate.getText().toString();
		
		Cursor cursor = dbHelper.getInvoiceDetailsForHistory(repID,start_Date,end_Date);
			if(cursor.getCount()==0){
				
			
			Dialog d1=new Dialog(InvoiceHistory.this);
			d1.setTitle("No Records");
			TextView t=new TextView(InvoiceHistory.this);
			t.setText("There are no records invoiced on ");
			d1.setContentView(t);
			d1.show();
			
			
		}
		
		
		
      else{
		String[] columns = new String[] { 
				DBCreater.Key_Invoice_cust_id,
				DBCreater.Key_Invoice_invoice_id,
				DBCreater.Key_Invoice_selling_date,
				DBCreater.Key_Invoice_post_dicount_amount,
				DBCreater.Key_Invoice_paid_cost,
				DBCreater.Key_Invoice_amount_to_be_paid
				
				//DBCreater.Key_repS_assigned_qty 
		};
		
		int[] to = new int[]{
				R.id.tv_cusID_invoiceSingle,
				R.id.tv_invoice_id_invoiceSingle,
				R.id.tv_invoiced_date_invoiceSingle,
				R.id.tv_total_amount_invoiceSingle,
				R.id.tv_paid_amount_invoiceSingle,
				R.id.tv_amountTobePaid_invoiceSingle
			
		};
		
		dataAdapter1 = new SimpleCursorAdapter(this, R.layout.invosingle, cursor, columns, to,0);
		final ListView lvinvoiceHistory = (ListView)findViewById(R.id.lv_invoHistry_invoiceHistory);
		lvinvoiceHistory.setAdapter(dataAdapter1);
		lvinvoiceHistory.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), " ", Toast.LENGTH_SHORT).show();
				
				
			}
		});
	
			
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
		getMenuInflater().inflate(R.menu.invoice_history, menu);
		return true;
	}

}
