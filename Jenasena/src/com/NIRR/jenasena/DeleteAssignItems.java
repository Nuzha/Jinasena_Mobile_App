package com.NIRR.jenasena;

import com.lowagie.text.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteAssignItems extends Activity {
	
	private DBCreater dbHelper;
	TextView repview,dateview,delview,itm,quantity,price;
	String dlt1,dlt2,dlt3;
	Button deleteitems;
	String sitm,sqty,sprice;
	String strid;
	int earlyqty,newqty;
	String qty;
	
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete_assign_items);
		
		repview=(TextView)findViewById(R.id.tv_d_repview_rosh);
		Intent intent = getIntent();
		dlt1 = intent.getStringExtra("repS_rep_id");
		repview.setText(dlt1);
		
		dateview=(TextView)findViewById(R.id.tv_d_dateview_rosh);
		Intent intent1 = getIntent();
		dlt2 = intent1.getStringExtra("repS_assigned_date");
		dateview.setText(dlt2);
		
		delview=(TextView)findViewById(R.id.tv_d_logview_rosh);
		Intent intent2 = getIntent();
		dlt3 = intent2.getStringExtra("key1");
		delview.setText(dlt3);
		
		itm=(TextView)findViewById(R.id.tv_d_itemview_rosh);
		Intent intent3 = getIntent();
		sitm = intent3.getStringExtra("key4");
		itm.setText(sitm);
		
		quantity=(TextView)findViewById(R.id.tv_d_qtyview_rosh);
		Intent intent4 = getIntent();
		sqty = intent4.getStringExtra("key5");
		quantity.setText(sqty);
		
		price=(TextView)findViewById(R.id.tv_d_costview_rosh);
		Intent intent5 = getIntent();
		sprice = intent5.getStringExtra("key6");
		price.setText(sprice);
		
		
		deleteitems=(Button)findViewById(R.id.btn_d_dlt_rosh);
		Intent intentt=getIntent();
	    strid=intentt.getStringExtra("id");
		
		deleteitems.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		 
					// set title
					alertDialogBuilder.setTitle("Delete Record");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Are you sure you want to delete this record?")
						.setCancelable(false)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								
					DBCreater entrydelete=new DBCreater(DeleteAssignItems.this);
					entrydelete.open();
								
					earlyqty=Integer.valueOf(sqty);
					newqty=Integer.valueOf(quantity.getText().toString());
								
								
					entrydelete.delete("Rep_Stock","repS_rep_id=? and repS_assigned_date=? and repS_item_name=?", new String[] {dlt1,dlt2,sitm});
									
					
					java.util.List<String> labels1=entrydelete.getAssignedqtyupdate(sitm, dlt3);
					qty=(labels1.get(0).toString());
					int assignedqty=Integer.valueOf(qty);
					ContentValues cv1 = new ContentValues();
					cv1.put(DBCreater.Key_delS_available_qty,assignedqty+earlyqty);
					entrydelete.update("Del_Stock", cv1, "delS_del_item_name=? AND delS_del_id=?",new String[] {sitm,dlt3} );
					
					
					Dialog dlg=new Dialog(DeleteAssignItems.this);
					dlg.setTitle("Deleted!!!" );
					TextView txtvw=new TextView(DeleteAssignItems.this);
					txtvw.setText("Successfully deleted the record");
					dlg.setContentView(txtvw);
					dlg.show();
							}
						  })
					.setNegativeButton("No",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
						dialog.cancel();
						}
					});
		 
						// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
					alertDialog.show();
								
								
			}
		});
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete_assign_items, menu);
		return true;
	}

}
