package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RepListView extends Activity implements OnItemClickListener{
	
	ListView l;
	String[] rep;
	protected Cursor cursor;
	protected SQLiteDatabase db;
	private DBCreater dbHelper;
	private SimpleCursorAdapter dataAdapter;
	//TextView t2;
	String ch;
	TextView replist;
	String rep1;
	
	public static String packagename="com.NIRR.redistributionsystem";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rep_list_view);
		
		ch=null;
		
		replist=(TextView)findViewById(R.id.rtv_replist);
		
		Intent intent = getIntent();
		rep1 = intent.getStringExtra(DelearMenu.packagename);
		replist.setText(rep1);
		
		dbHelper = new DBCreater(this);
	    dbHelper.open();

	    displayListView();
	    loadAssignItems();
	    
		}
	
	private void displayListView(){

	    Cursor cursor = dbHelper.fetchallrepdata((String)replist.getText());
	    String[] columns = new String[] {
	        DBCreater.Key_rep_Username,
	        DBCreater.Key_rep_Name,
	       
	    };

	    int[] to = new int[]{
	            R.id.rlv_repusrname,
	            R.id.rlv_repname
	           
	    };

	final ListView listView = (ListView)findViewById(R.id.rlv_replist);
    dataAdapter = new SimpleCursorAdapter(this, R.layout.single_text, cursor, columns, to,0);
   listView.setAdapter(dataAdapter);
   listView.setOnItemClickListener(new OnItemClickListener() {

       @Override
       public void onItemClick(AdapterView<?> lidstView, View view, int position,
               long id) {
           // TODO Auto-generated method stub
           Cursor cursor =(Cursor)listView.getItemAtPosition(position);
           //Toast.makeText(cursor.getColumnIndexOrThrow(""), text, duration)
           String name=cursor.getString(cursor.getColumnIndexOrThrow("rep_uname"));
           Toast.makeText(getApplicationContext(),"Now you can assign items to rep, "+ name, Toast.LENGTH_SHORT).show();
           ch=name;
           
           
           switch(position){
   		
   		default:
   			Intent p2 = new Intent(RepListView.this,NewAssignItems.class);
			p2.putExtra("rep_delear", rep1);
			p2.putExtra("rep_uname", name);
			startActivity(p2);
   		break;
   		}
       }

   });
 
	}
	
	private void loadAssignItems(){
		Button btn1 = (Button) findViewById(R.id.r_ba);
		btn1.setOnClickListener(new OnClickListener() {
		 
		   @Override
		   public void onClick(View v) {
			   
			   if(ch==null)
			   {
				   Toast.makeText(getApplicationContext(),"First select a rep before assigning items.. ", Toast.LENGTH_SHORT).show();
				   return;
			   }
			   
			   
			   
			   Intent intent1=new Intent(RepListView.this,NewAssignItems.class);
			   startActivity(intent1);
		   }
		  });
		  
		 }
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
