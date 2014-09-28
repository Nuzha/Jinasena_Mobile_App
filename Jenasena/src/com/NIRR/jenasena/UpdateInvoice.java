package com.NIRR.jenasena;

import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateInvoice extends Activity {

	TextView titlerepid, titlecusid;
	TextView titleitemName, titleqty, titlesolddate, titlepdaiDate;
	TextView repid, cusid, soldDate;
	Button delete, update;
	double unitp = 0.0;
	int sellingQty;

	TextView itemName;
	EditText quantity /* , paidDate */;
	int prevoiusEntered, newlyEntered;
	String s5, s6, s7, s8, s9, qty, pdate, sdate, unitprice, availableQty;

	private DBCreater dbHelper;

	private int pYear;
	private int pMonth;
	private int pDay;
	/**
	 * This integer will uniquely define the dialog to be used for displaying
	 * date picker.
	 */
	static final int DATE_DIALOG_ID = 0;

	private DatePickerDialog.OnDateSetListener pDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			pYear = year;
			pMonth = monthOfYear;
			pDay = dayOfMonth;

			// if(buttonCount==1)
			// updateDisplay();

			// else
			// updateDisplayDue();
			// displayToast();
		}
	};

	/*
	 * public void updateDisplay(){
	 * 
	 * 
	 * paidDate.setText( new StringBuilder() // Month is 0 based so add 1
	 * .append(pYear).append("/") .append(pMonth + 1).append("/")
	 * .append(pDay).append("") );
	 * 
	 * }
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_invoice);

		titlerepid = (TextView) findViewById(R.id.tvtitlerepid);
		titlecusid = (TextView) findViewById(R.id.tvtitlecusid);
		titleitemName = (TextView) findViewById(R.id.tvtitleItemname);
		titleqty = (TextView) findViewById(R.id.tvtitleQuantity);
		titlesolddate = (TextView) findViewById(R.id.tvtitleSoldDate);
		titlepdaiDate = (TextView) findViewById(R.id.tvtitlepaidDate);

		delete = (Button) findViewById(R.id.btDelete);
		update = (Button) findViewById(R.id.btupdate);
		// sDate=(Button)findViewById(R.id.btdate);

		soldDate = (TextView) findViewById(R.id.tvsoldDate);
		Intent intent7 = getIntent();
		sdate = intent7.getStringExtra("sold date");
		soldDate.setText(sdate);

		// paidDate=(EditText)findViewById(R.id.etpaidDate);
		// Intent intent6 = getIntent();
		// pdate= intent6.getStringExtra("paid Date");
		// paidDate.setText(pdate);

		repid = (TextView) findViewById(R.id.tvrepId);
		Intent intent = getIntent();
		s5 = intent.getStringExtra("rep_id");
		repid.setText(s5);

		cusid = (TextView) findViewById(R.id.tvcusId);
		Intent intent1 = getIntent();
		s6 = intent1.getStringExtra("cus_id");
		cusid.setText(s6);

		itemName = (TextView) findViewById(R.id.tvitemName);
		Intent intent2 = getIntent();
		s7 = intent2.getStringExtra("item Name");
		itemName.setText(s7);

		quantity = (EditText) findViewById(R.id.etquantity);
		Intent intent3 = getIntent();
		s8 = intent3.getStringExtra("quantity");
		quantity.setText(s8);

		qty = s8;

		Intent intent4 = getIntent();
		s9 = intent4.getStringExtra("id");

		Intent intent5 = getIntent();
		unitprice = intent5.getStringExtra("unit cost");
		unitp = Double.valueOf(unitprice);

		Intent intent6 = getIntent();
		availableQty = intent6.getStringExtra("availQty");
		System.out.println(availableQty);

		update.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.e("val", "" + availableQty);
				int availQt = Integer.parseInt(availableQty);
				Log.e("con", "" + availQt);

				/*
				 * 
				 * if(quantity.getText().toString().length()==0 /*||
				 * quantity.getText().toString().equalsIgnoreCase("0") ){ Dialog
				 * d1=new Dialog(UpdateInvoice.this);
				 * d1.setTitle("Fill empty fields"); TextView t=new
				 * TextView(UpdateInvoice.this);
				 * t.setText("Please enter values for empty fields ");
				 * d1.setContentView(t); d1.show();
				 * 
				 * }
				 */
				if(quantity.getText().toString().length()==0){
					Dialog d1=new Dialog(UpdateInvoice.this);
					d1.setTitle("Fill empty fields");
					TextView t=new TextView(UpdateInvoice.this);
					t.setText("Please enter values for empty fields ");
					d1.setContentView(t);
					d1.show();
					
					
				}
				
				
				
				else if (quantity.getText().toString().length() != 0) {

					try {
						sellingQty = Integer.parseInt(quantity.getText()
								.toString());
						
						
						if(sellingQty==0){
							Dialog d1 = new Dialog(UpdateInvoice.this);
							d1.setTitle("Error");
							TextView t = new TextView(UpdateInvoice.this);
							t.setText("You cannot enter zero as quantity");
							d1.setContentView(t);
							d1.show();
							
						}

						else if ((availQt - sellingQty) < 0) {
							Log.e("avb", "" + availQt);
							Log.e("so", "" + availQt);
							Dialog d1 = new Dialog(UpdateInvoice.this);
							d1.setTitle("Error");
							TextView t = new TextView(UpdateInvoice.this);
							t.setText("Updated quantity should be less than or equal to available quantity");
							d1.setContentView(t);
							d1.show();
							Log.e("less", "ok");

						}

						else {
							Log.e("up", "ok");
							DBCreater fill = new DBCreater(UpdateInvoice.this);
							fill.open();

							prevoiusEntered = Integer.valueOf(s8);
							newlyEntered = Integer.valueOf(quantity.getText()
									.toString());
							System.out.println("unit price:" + unitp);

							// update the cart sold stock
							ContentValues cv = new ContentValues();
							cv.put(DBCreater.Key_Cart_sold_quantity, quantity
									.getText().toString());

							// /###################################commented
							// this line db chnage
							// cv.put(DBCreater.Key_Cart_paid_date,paidDate.getText().toString());
							cv.put(DBCreater.Key_Cart_total_price,
									(unitp * newlyEntered));
							fill.update("Cart", cv, "_id=?",
									new String[] { s9 });

							// get avaibalboe quantity frm the rep stock
							List<String> lables = fill.getAvailableqty(s7, s5);
							qty = (lables.get(0).toString());
							int respSavailableqty = Integer.valueOf(qty);
							System.out.println("Available qty:" + qty);

							if (prevoiusEntered < newlyEntered) {

								// update repstock
								ContentValues cv1 = new ContentValues();
								cv1.put(DBCreater.Key_repS_available_qty,
										respSavailableqty
												- (newlyEntered - prevoiusEntered));
								fill.update("Rep_Stock", cv1,
										"repS_item_name=? AND repS_rep_id=?",
										new String[] { s7, s5 });

								fill.close();

							}

							else {

								ContentValues cv1 = new ContentValues();
								cv1.put(DBCreater.Key_repS_available_qty,
										respSavailableqty
												+ (prevoiusEntered - newlyEntered));
								fill.update("Rep_Stock", cv1,
										"repS_item_name=? AND repS_rep_id=?",
										new String[] { s7, s5 });

							}

							Dialog d1 = new Dialog(UpdateInvoice.this);
							d1.setTitle("Successful");
							TextView t = new TextView(UpdateInvoice.this);
							t.setText("Reocrd was updated to the Cart and to the Rep Stock");
							d1.setContentView(t);
							d1.show();

						}
					}
					 catch (Exception e) {
						Dialog d1 = new Dialog(UpdateInvoice.this);
						d1.setTitle("Not valid values");
						TextView t = new TextView(UpdateInvoice.this);
						t.setText("Please enter valid values as quantity ");
						d1.setContentView(t);
						d1.show();

					}

				}

			}

		});

		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DBCreater fill = new DBCreater(UpdateInvoice.this);
				fill.open();

				prevoiusEntered = Integer.valueOf(s8);
				newlyEntered = Integer.valueOf(quantity.getText().toString());

				fill.delete("Cart", "_id=?", new String[] { s9 });

				List<String> lables = fill.getAvailableqty(s7, s5);
				qty = (lables.get(0).toString());
				int respSavailableqty = Integer.valueOf(qty);
				System.out.println("Available qty:" + qty);

				ContentValues cv1 = new ContentValues();
				cv1.put(DBCreater.Key_repS_available_qty, respSavailableqty
						+ prevoiusEntered);
				fill.update("Rep_Stock", cv1,
						"repS_item_name=? AND repS_rep_id=?", new String[] {
								s7, s5 });

				Dialog d1 = new Dialog(UpdateInvoice.this);
				d1.setTitle("Successful");
				TextView t = new TextView(UpdateInvoice.this);
				t.setText("Reocrd was successfully deleted fromu the Cart and updated to the Rep Stock");
				d1.setContentView(t);
				d1.show();

			}
		});

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
		getMenuInflater().inflate(R.menu.update_invoice, menu);
		return true;
	}

}
