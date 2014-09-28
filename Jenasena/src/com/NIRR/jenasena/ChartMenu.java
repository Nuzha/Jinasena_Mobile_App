package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ChartMenu extends Activity implements OnItemClickListener{


	String[] list = { " Stock ", " Customer ", " Representatives "   }; 
	Integer[] images = { R.drawable.stockss,R.drawable.man,R.drawable.delear};
	public static String packagename="com.NIRR.jenasena";
	ListView listview;
	List<Item> rowItems;
	TextView e1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		e1=(TextView)findViewById(R.id.tv_menu_logger_Isuru);
		//get the delier id and show in the text
				Intent intent=getIntent();
			    String s1=intent.getStringExtra(DelearMenu.packagename);
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
		
			
		case 1:
			Intent p4 = new Intent(ChartMenu.this, MainDelCustomerProformance.class);
			p4.putExtra(packagename, e1.getText());
			startActivity(p4);
			break;
		case 0:
			Intent p5 = new Intent(ChartMenu.this, C_Stock.class);
			p5.putExtra(packagename, e1.getText());
			startActivity(p5);
			break;
		case 2:
			Intent p2 = new Intent(ChartMenu.this, MainDelRepProformance.class);
			p2.putExtra(packagename, e1.getText());
			startActivity(p2);
			break;
		}
		
	}

}
