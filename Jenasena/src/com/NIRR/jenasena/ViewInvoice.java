package com.NIRR.jenasena;

import android.bluetooth.BluetoothAdapter;
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
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewInvoice extends Activity {
	TextView titlerepid, titlecusid, titledate, titleinvoicetotl;
	TextView titleitemName, titleqty, titleuprice, titletotal;
	TextView repId, cusId, invoicettl;
	TextView date, invoiceID;
	Button sDate, genInvoice, confrmInvoice, bDueDate, bluetooth;
	String InvoiceId;
	EditText dueDate;
	String cusAddress, cusName, cusShop;

	String s3, s4;
	double total;

	private int pYear;
	private int pMonth;
	private int pDay;
	/**
	 * This integer will uniquely define the dialog to be used for displaying
	 * date picker.
	 */
	static final int DATE_DIALOG_ID = 0;

	private static final int DISCOVER_DURATION = 300;

	// our request code (must be greater than zero)
	private static final int REQUEST_BLU = 1;

	// int buttonCount=0;

	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;

			updateDisplay();

		}
	};

	public void updateDisplay() {

		dueDate.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(pYear).append("/").append(pMonth + 1).append("/")
				.append(pDay).append(""));

	}

	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_invoice);

		titlerepid = (TextView) findViewById(R.id.tvtitlerepid);
		titlecusid = (TextView) findViewById(R.id.tvtitlecusid);
		titledate = (TextView) findViewById(R.id.tvtitledate);
		titleinvoicetotl = (TextView) findViewById(R.id.tvtitleinvoicetotal);

		titleitemName = (TextView) findViewById(R.id.tvtitleitemName);
		titleqty = (TextView) findViewById(R.id.tvtitleqty);
		titleuprice = (TextView) findViewById(R.id.tvtitleuprice);
		titletotal = (TextView) findViewById(R.id.tvtitletotal);
		invoiceID = (TextView) findViewById(R.id.tvinvoid);

		invoicettl = (TextView) findViewById(R.id.tvinvcttl);

		repId = (TextView) findViewById(R.id.tvrepId);
		Intent intent = getIntent();
		s3 = intent.getStringExtra("rep_id");
		System.out.println(s3);
		repId.setText(s3);

		cusId = (TextView) findViewById(R.id.tvcusId);
		Intent intent1 = getIntent();
		s4 = intent1.getStringExtra("cus_id");
		System.out.println(s4);

		cusId.setText(s4);

		Intent intent2 = getIntent();
		InvoiceId = intent2.getStringExtra("invoice_id");
		invoiceID.setText(InvoiceId);

		date = (TextView) findViewById(R.id.tvDate);
		Intent intent3 = getIntent();
		date.setText(intent3.getStringExtra("selling_date"));

		dueDate = (EditText) findViewById(R.id.etDueDate);

		sDate = (Button) findViewById(R.id.btselectdate);
		genInvoice = (Button) findViewById(R.id.btgenInvoice);
		confrmInvoice = (Button) findViewById(R.id.bConfrm);
		bDueDate = (Button) findViewById(R.id.btDueDate);

		bluetooth = (Button) findViewById(R.id.btShareInvoice);

		// for report
		DBCreater entry = new DBCreater(ViewInvoice.this);
		entry.openforread();
		List<String> lables = entry.getCusAddressndName(cusId.getText()
				.toString());
		cusName = lables.get(0).toString();
		cusShop = lables.get(1).toString();
		cusAddress = lables.get(2).toString();

		confrmInvoice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// int total=Integer.valueOf(invoicettl.getText().toString());

				String s = invoicettl.getText().toString();// get the invoice
															// total
				double sum1 = Double.parseDouble(s);

				if (dueDate.getText().toString().length() == 0) {
					Dialog d1 = new Dialog(ViewInvoice.this);
					d1.setTitle("Cannot confirm the invoice!!!");
					TextView t = new TextView(ViewInvoice.this);
					t.setText("Please enter the due date before confirm the invoice");
					d1.setContentView(t);
					d1.show();

				}

				else if (dueDate.getText().toString().length() != 0) {
					String sellD = date.getText().toString();
					String dueD = dueDate.getText().toString();

					StringTokenizer token = new StringTokenizer(sellD, "/");
					int[] sellResult = { 1, 2, 3 };
					int i = 0;

					while (token.hasMoreTokens()) {
						sellResult[i++] = Integer.parseInt(token.nextToken());

					}
					System.out.println("sell yrrrrr  " + sellResult[0]);
					System.out.println("sell monthtttt  " + sellResult[1]);
					System.out.println("sell daaate " + sellResult[2]);

					StringTokenizer token2 = new StringTokenizer(dueD, "/");

					int[] dueResult2 = { 1, 2, 3 };
					int j = 0;

					while (token2.hasMoreTokens()) {
						dueResult2[j++] = Integer.parseInt(token2.nextToken());

					}

					System.out.println("due yrrrrr " + dueResult2[0]);
					System.out.println("due monthtttt " + dueResult2[1]);
					System.out.println("due daaate " + dueResult2[2]);

					if (sellResult[0] > dueResult2[0]
							|| (sellResult[0] == dueResult2[0] && sellResult[1] > dueResult2[1])
							|| (sellResult[0] == dueResult2[0]
									&& sellResult[1] == dueResult2[1] && sellResult[2] > dueResult2[2])) {
						Dialog d1 = new Dialog(ViewInvoice.this);
						d1.setTitle("Plese enter a valid due date");
						TextView t = new TextView(ViewInvoice.this);
						t.setText("The selling date should be less than or equal to due date ");
						d1.setContentView(t);
						d1.show();

					}

					else {

						DBCreater entry = new DBCreater(ViewInvoice.this);

						entry.open();

						entry.createInvoiceEntry(InvoiceId, repId.getText()
								.toString(), cusId.getText().toString(), 0,
								sum1, sum1, 0, sum1, date.getText().toString(),
								dueDate.getText().toString(), "Not Paid");

						entry.close();

						Dialog d1 = new Dialog(ViewInvoice.this);
						d1.setTitle("Successfully entered the invoice record!!!");
						TextView t = new TextView(ViewInvoice.this);
						t.setText("The invoice details are successfully entered on "
								+ date.getText().toString()
								+ " to "
								+ cusId.getText().toString());
						d1.setContentView(t);
						d1.show();

					}

				}

			}
		});

		genInvoice.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				create(repId.getText().toString());
				open();

			}

			private void open() {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_VIEW);
				String path = Environment.getExternalStorageDirectory()
						+ "/PDF";

				File file = new File(path, "Invoice.pdf");
				intent.setDataAndType(Uri.fromFile(file), "application/pdf");
				startActivity(intent.setDataAndType(Uri.fromFile(file),
						"application/pdf"));
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

				Document doc = new Document();
				try {

					// give the part for the doc
					String path = Environment.getExternalStorageDirectory()
							.getAbsolutePath() + "/PDF";
					// make object from file
					File dir = new File(path);
					// make new pdf if not extit
					if (!dir.exists())
						dir.mkdir();

					File file = new File(dir, "Invoice.pdf");
					FileOutputStream fout = new FileOutputStream(file);
					PdfWriter docWriter = PdfWriter.getInstance(doc, fout);
					doc.open();

					String logger = "Document created by " + usr + " ";

					Calendar cal = new GregorianCalendar();
					String am_pm = (cal.get(Calendar.AM_PM) == 0) ? "AM" : "PM";
					String pdfheder = logger + " Date  "
							+ cal.get(Calendar.YEAR) + "/"
							+ (cal.get(Calendar.MONTH) + 1) + "/"
							+ cal.get(Calendar.DAY_OF_MONTH)
							+ "        Created on  " + cal.get(Calendar.HOUR)
							+ " : " + cal.get(Calendar.MINUTE) + " : "
							+ cal.get(Calendar.SECOND) + " " + am_pm + "";
					Paragraph p1 = new Paragraph(pdfheder);
					Font paraFont = new Font(Font.HELVETICA);
					paraFont.setSize(36);
					paraFont.setColor(Color.MAGENTA);
					p1.setAlignment(Paragraph.ALIGN_LEFT);
					p1.setFont(paraFont);
					doc.add(p1);

					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					Bitmap bitmap = BitmapFactory
							.decodeResource(getBaseContext().getResources(),
									R.drawable.jininvo);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					Image myImg = Image.getInstance(stream.toByteArray());
					myImg.setAlignment(Image.MIDDLE);
					myImg.setAbsolutePosition(250, 650);

					// add image to document
					doc.add(myImg);
					Phrase footerText = new Phrase(
							"This is an example of a footer");
					HeaderFooter pdfFooter = new HeaderFooter(footerText, false);
					doc.setFooter(pdfFooter);

					PdfContentByte cb = docWriter.getDirectContent();
					// initialize fonts for text printing
					initializeFonts();

					createMainHeading(cb, 45, 620,
							"Jinasena InfoTech(Pvt) Limited");

					createHeadings(cb, 150, 600,
							"No: 514, Thimbirigasyaya Rd, Colombo 05, Srilanka");
					createHeadings(cb, 250, 585, "Tel:0112345678");
					createHeadings(cb, 250, 570, "Fax:0112345688");

					createSubHeading(cb, 250, 530, "Invoice");

					String refid = InvoiceId.substring(7);
					String referenceNo = "Jin-Ref" + refid;

					createHeadings(cb, 50, 510, "Mr/Ms " + cusName);
					createHeadings(cb, 50, 495, cusShop);

					StringTokenizer address = new StringTokenizer(cusAddress,
							",");

					int y = 480;
					while (address.hasMoreTokens()) {
						// StringTokenizer childToken = new
						// StringTokenizer(token.nextToken(),"\t");

						createHeadings(cb, 50, y, address.nextToken() + ",");
						y = y - 15;

					}

					createHeadings(cb, 400, 510, "Date:"
							+ date.getText().toString());
					createHeadings(cb, 400, 495, "Invoice No:" + InvoiceId);
					createHeadings(cb, 400, 480, "Reference No:" + referenceNo);

					PdfPTable table = new PdfPTable(4);

					// list all the products sold to the customer
					// float[] columnWidths = {1.5f, 2f, 5f, 2f,2f};

					// create PDF table with the given widths
					table.setTotalWidth(500f);

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
					DBCreater db = new DBCreater(ViewInvoice.this);
					db.openforread();
					// db.getdata();
					String dataset = db.getInvoiceDetails(invoiceID.getText()
							.toString());
					StringTokenizer token = new StringTokenizer(dataset, "*");
					String s11, s2, s3, s4;
					int countTokens = token.countTokens();
					while (token.hasMoreTokens()) {
						// StringTokenizer childToken = new
						// StringTokenizer(token.nextToken(),"\t");
						s11 = token.nextToken();
						table.addCell(s11);
						s2 = token.nextToken();
						table.addCell(s2);
						s3 = token.nextToken();
						table.addCell(s3);
						s4 = token.nextToken();
						table.addCell(s4);

					}
					// Anchor anc = new Anchor();

					// absolute location to print the PDF table from
					table.writeSelectedRows(0, -1, doc.leftMargin(), 370,
							docWriter.getDirectContent());

					// table.writeSelectedRows(rowStart, rowEnd, xPos, yPos,
					// canvas)
					// Paragraph preface = new Paragraph();
					// preface.add(table);
					// doc.add(preface);

					createHeadings(cb, 350, 100, "Total Amount :" + total);

					Toast.makeText(getApplicationContext(),
							"Report Created...", Toast.LENGTH_LONG).show();
				}

				catch (DocumentException de) {
					Log.e("PDFCreator", "DocumentException:" + de);
				} catch (IOException e) {
					Log.e("PDFCreator", "ioException:" + e);
				} finally {
					doc.close();
				}

			}

			private BaseFont bfBold;

			private void initializeFonts() {

				try {
					bfBold = BaseFont.createFont(BaseFont.HELVETICA,
							BaseFont.CP1252, BaseFont.NOT_EMBEDDED);

				} catch (DocumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void createHeadings(PdfContentByte cb, float x, float y,
					String text) {

				cb.beginText();
				cb.setFontAndSize(bfBold, 12);
				cb.setTextMatrix(x, y);
				cb.showText(text.trim());
				cb.endText();

			}

			private void createMainHeading(PdfContentByte cb, float x, float y,
					String text) {

				cb.beginText();
				cb.setFontAndSize(bfBold, 36);
				cb.setTextMatrix(x, y);
				cb.showText(text.trim());
				cb.endText();

			}

			private void createSubHeading(PdfContentByte cb, float x, float y,
					String text) {

				cb.beginText();
				cb.setFontAndSize(bfBold, 25);
				cb.setTextMatrix(x, y);
				cb.showText(text.trim());
				cb.endText();

			}

		});

		bluetooth.setOnClickListener(new View.OnClickListener() {

			String filelocation = "/storage/emulated/0/PDF/Invoice.pdf";
			File file = new File(filelocation);
			Uri pdfUri = Uri.fromFile(file);

			@Override
			public void onClick(View v) {
				/*
				 * BluetoothAdapter btAdapter =
				 * BluetoothAdapter.getDefaultAdapter(); if (btAdapter == null)
				 * { // Device does not support Bluetooth // Inform user that
				 * we're done.
				 * 
				 * Dialog d1=new Dialog(ViewInvoice.this);
				 * d1.setTitle("Doesn't support bluetooth"); TextView t=new
				 * TextView(ViewInvoice.this);
				 * t.setText("No bluetooth adapter found");
				 * d1.setContentView(t); d1.show();
				 * 
				 * 
				 * }
				 * 
				 * else{
				 * 
				 * 
				 * // bring up Android chooser Intent intent = new
				 * Intent(android.content.Intent.ACTION_SEND);
				 * //intent.setAction(android.content.Intent.ACTION_SEND);
				 * intent.setType("text/plain");
				 * intent.putExtra(android.content.Intent.EXTRA_STREAM, pdfUri
				 * ); //... startActivity(intent);
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * }
				 */

				enableBlu();
				connection();

			}
		});

		// date picker
		sDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);

			}
		});

		bDueDate.setOnClickListener(new View.OnClickListener() {

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

		dbHelper = new DBCreater(ViewInvoice.this);
		dbHelper.open();

		dispalyInvoice();

		String s = invoicettl.getText().toString();// get the invoice total
		total = Double.parseDouble(s);

	}

	private void dispalyInvoice() {
		Cursor cursor = dbHelper.disInvoice(repId.getText().toString(), cusId
				.getText().toString(), InvoiceId);
		double sum = 0.0;
		String[] columns;

		if (cursor.getCount() == 0) {

			Dialog d1 = new Dialog(ViewInvoice.this);
			d1.setTitle("No Records");
			TextView t = new TextView(ViewInvoice.this);
			t.setText("There are no records invoiced on "
					+ date.getText().toString() + " to "
					+ cusId.getText().toString());
			d1.setContentView(t);
			d1.show();

		}

		if (cursor.moveToFirst()) {
			do {
				// labels.add(cursor.getString(0));
				// labels.add(cursor.getString(1));
				sum = sum + Double.parseDouble(cursor.getString(4));
			} while (cursor.moveToNext());// to track the coloum
		}

		columns = new String[] { DBCreater.Key_Cart_item_name,
				DBCreater.Key_Cart_sold_quantity,
				DBCreater.Key_Cart_unit_price, DBCreater.Key_Cart_total_price

		// DBCreater.Key_repS_assigned_qty

		};

		int[] to = new int[] { R.id.tvItemName, R.id.tvSoldQty,
				R.id.tvUnitPrice, R.id.tvtotal

		};

		dataAdapter1 = new SimpleCursorAdapter(this, R.layout.visingleraw,
				cursor, columns, to, 0);
		final ListView viewInvoice = (ListView) findViewById(R.id.lvinvoice);
		viewInvoice.setAdapter(dataAdapter1);

		String s = Double.toString(sum);
		invoicettl.setText(s);

		viewInvoice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				//int availQuantity;
				//String avail;

				Cursor cursor = (Cursor) viewInvoice
						.getItemAtPosition(position);

				String itName = cursor.getString(cursor
						.getColumnIndexOrThrow("Cart_item_name"));
				String qty = cursor.getString(cursor
						.getColumnIndexOrThrow("Cart_sold_qty"));
				String id = cursor.getString(cursor
						.getColumnIndexOrThrow("_id"));
				String unitprice = cursor.getString(cursor
						.getColumnIndexOrThrow("Cart_unit_price"));

				if (position == 0) {
					DBCreater entry = new DBCreater(ViewInvoice.this);
					entry.openforread();
					List<String> lables = entry.getAvailQuantity(repId
							.getText().toString(), itName, date.getText()
							.toString());

					String availQt = lables.get(0).toString();
					System.out.println(availQt);

					Intent p2 = new Intent(ViewInvoice.this,
							UpdateInvoice.class);
					p2.putExtra("cus_id", s4);
					p2.putExtra("rep_id", s3);
					p2.putExtra("item Name", itName);
					p2.putExtra("quantity", qty);
					p2.putExtra("id", id);
					// p2.putExtra("paid Date", paidDate);
					p2.putExtra("sold date", date.getText().toString());
					p2.putExtra("unit cost", unitprice);
					// p2.putExtra("availQty",availQuantity);
					p2.putExtra("availQty", availQt);
					startActivity(p2);

				}

				else {

					DBCreater entry = new DBCreater(ViewInvoice.this);
					entry.openforread();
					List<String> lables = entry.getAvailQuantity(repId
							.getText().toString(), itName, date.getText()
							.toString());
					String availQt = lables.get(0).toString();
					System.out.println(availQt);

					Intent p4 = new Intent(ViewInvoice.this,
							UpdateInvoice.class);
					p4.putExtra("cus_id", s4);
					p4.putExtra("rep_id", s3);
					p4.putExtra("item Name", itName);
					p4.putExtra("quantity", qty);
					p4.putExtra("id", id);
					// p2.putExtra("paid Date", paidDate);
					p4.putExtra("sold date", date.getText().toString());
					p4.putExtra("unit cost", unitprice);
					p4.putExtra("availQty", availQt);
					startActivity(p4);

				}

			}

		});

	}

	public void enableBlu() {
		// enable device discovery - this will automatically enable Bluetooth
		Intent discoveryIntent = new Intent(
				BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

		discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,
				DISCOVER_DURATION);

		// startActivityForResult(discoveryIntent, REQUEST_BLU);

	}

	public void connection() {
		// When startActivityForResult completes...
		// ...
		// inside method
		// Check if bluetooth is supported
		BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

		if (btAdapter == null) {
			// Device does not support Bluetooth
			// Inform user that we're done.

			Dialog d1 = new Dialog(ViewInvoice.this);
			d1.setTitle("Doesn't support bluetooth");
			TextView t = new TextView(ViewInvoice.this);
			t.setText("No bluetooth adapter found");
			d1.setContentView(t);
			d1.show();

		}

		String filelocation = "/storage/emulated/0/PDF/Invoice.pdf";
		File file = new File(filelocation);
		Uri pdfUri = Uri.fromFile(file);

		// bring up Android chooser
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_STREAM, pdfUri);
		// ...
		// startActivity(intent);

		// list of apps that can handle our intent
		PackageManager pm = getPackageManager();
		List<ResolveInfo> appsList = pm.queryIntentActivities(intent, 0);

		if (appsList.size() > 0) {
			// proceed
			// select bluetooth
			String packageName = null;
			String className = null;
			boolean found = false;

			for (ResolveInfo info : appsList) {
				packageName = info.activityInfo.packageName;
				if (packageName.equals("com.android.bluetooth")) {
					className = info.activityInfo.name;
					found = true;
					break;// found
				}
			}
			if (!found) {
				Toast.makeText(this, "No bluetoothe found", Toast.LENGTH_SHORT)
						.show();
				// exit
			}

			// set our intent to launch Bluetooth
			intent.setClassName(packageName, className);
			startActivity(intent);

		}

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, pDateSetListener, pYear, pMonth,
					pDay);
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_invoice, menu);
		return true;
	}

}
