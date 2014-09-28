package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class DelearMenu extends Activity implements OnItemClickListener{

	String[] list = { "Assign Items ", "Quick Search" , " View Daily Assigned Stock", "View History Reports","Check low stocks","Manage Return Items", "Check Amount"," Customer " , " Representative " , " Locations " , " Report " ," Charts " , "Customer History" ,"Settings" , "Backup" };
	Integer[] images = {R.drawable.target,R.drawable.profermance,R.drawable.stock,R.drawable.profermance,R.drawable.target,R.drawable.stock,R.drawable.profermance,R.drawable.man,R.drawable.delear,R.drawable.map,R.drawable.reportss,R.drawable.charts,R.drawable.customerhistory,R.drawable.settings,R.drawable.note };
	public static String packagename="com.NIRR.jenasena";
	ListView listview;
	List<Item> rowItems;
	TextView e1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		e1=(TextView)findViewById(R.id.tv_menu_logger_Isuru);
		//get the delier id and show in the text
				Intent intent=getIntent();
			    String s1=intent.getStringExtra(MainActivity.packagename);
			    e1.setText(s1);
			    
			    //get data to the list view
				rowItems = new ArrayList<Item>();
				for (int i = 0; i < list.length; i++) {
					Item item = new Item(images[i], list[i]);
					rowItems.add(item);
				}
				//add the data to the list view and make the listview at the run time
				listview = (ListView) findViewById(R.id.lv_menu_list_Isuru);
				CustomListViewAdapter adapter = new CustomListViewAdapter(this,
						R.layout.menu_item, rowItems);
				
				listview.setAdapter(adapter);
				listview.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		switch (arg2) {
		
		case 0:
			Intent p1 = new Intent(DelearMenu.this, RepListView.class);
			p1.putExtra(packagename, e1.getText());
			startActivity(p1);
			break;
			
		case 1:
			Intent p2 = new Intent(DelearMenu.this, QuickSearch.class);
			p2.putExtra(packagename, e1.getText());
			startActivity(p2);
			break;
			
		case 2:
			Intent p3 = new Intent(DelearMenu.this, SearchStock.class);
			p3.putExtra(packagename, e1.getText());
			startActivity(p3);
			break;
			
		case 3:
			Intent p4 = new Intent(DelearMenu.this, Reporting.class);
			p4.putExtra(packagename, e1.getText());
			startActivity(p4);
			break;
			
		case 4:
			Intent p5 = new Intent(DelearMenu.this, CheckLowStocks.class);
			p5.putExtra(packagename, e1.getText());
			startActivity(p5);
			break;	
			
		case 5:
			Intent p6 = new Intent(DelearMenu.this, View_Returned_items.class);
			p6.putExtra(packagename, e1.getText());
			startActivity(p6);
			break;
			
		case 6:
			Intent p7 = new Intent(DelearMenu.this, View_pending_payments.class);
			p7.putExtra(packagename, e1.getText());
			startActivity(p7);
			break;
			
		case 7:
			Intent p8 = new Intent(DelearMenu.this, MenuCustomer.class);
			p8.putExtra(packagename, e1.getText());
			startActivity(p8);
			break;
			
		case 8:
			Intent p9 = new Intent(DelearMenu.this, SearchRep.class);
			p9.putExtra(packagename, e1.getText());
			startActivity(p9);
			break;
			
		case 9:
			Intent p10 = new Intent(DelearMenu.this, LocationViewer.class);
			p10.putExtra(packagename, e1.getText());
			startActivity(p10);
			break;
			
		/*case 10:
			Intent p11 = new Intent(DelearMenu.this, .class);
			p11.putExtra(packagename, e1.getText());
			startActivity(p11);
			break;*/
			
		case 11:
			Intent p12 = new Intent(DelearMenu.this, ChartMenu.class);
			p12.putExtra(packagename, e1.getText());
			startActivity(p12);
			break;
		
		case 12:
			Intent p13 = new Intent(DelearMenu.this, DemoCustomerSearch.class);
			p13.putExtra(packagename, e1.getText());
			startActivity(p13);
			break;
			
		case 13:
			Intent p14 = new Intent(DelearMenu.this, BackupSettingList.class);
			p14.putExtra(packagename, e1.getText());
			startActivity(p14);
			break;
			
		case 14:
			Intent p15 = new Intent(DelearMenu.this, ImportExportData.class);
			p15.putExtra(packagename, e1.getText());
			startActivity(p15);
			break;
		
		
		}
		
		
		
		
	}

}
