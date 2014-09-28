package com.NIRR.jenasena;

import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Email extends Activity{
	private DBCreater dbHelper;
	EditText header,content;
	TextView from,to;
	ImageView send;
	Cursor mcurser;
	String s1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email);
		Intent intent=getIntent();
	    final String s1=intent.getStringExtra(CustomerDataForDelear.packagename);
	    from=(TextView)findViewById(R.id.tv_email_from_Isuru);
	    to=(TextView)findViewById(R.id.tv_email_to_Isuru);
	    header=(EditText)findViewById(R.id.et_email_emailheader_Isuru);
	    content=(EditText)findViewById(R.id.et_email_content_Isuru);
	    send=(ImageView)findViewById(R.id.iv_email_send_Isuru);
	    StringTokenizer token = new StringTokenizer(s1,"*");
		String s2,s3;
		while(token.hasMoreTokens()){
			s2 = token.nextToken();
			dbHelper = new DBCreater(this);
			dbHelper.open();
			mcurser=dbHelper.Delearemail(s2);
			if(mcurser.getCount()==0){
				mcurser=dbHelper.Repemail(s2);
			}
			from.setText(mcurser.getString(0));
	    	s3 = token.nextToken();
	    	to.setText(s3);
			
			
		}
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] TO = {to.getText().toString()};
			    String[] CC = {from.getText().toString()};
			      Intent emailIntent = new Intent(Intent.ACTION_SEND);
			      emailIntent.setData(Uri.parse("mailto:"));
			      emailIntent.setType("text/plain");


			      emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
			      emailIntent.putExtra(Intent.EXTRA_CC, CC);
			      emailIntent.putExtra(Intent.EXTRA_SUBJECT, header.getText().toString());
			      emailIntent.putExtra(Intent.EXTRA_TEXT, content.getText().toString());

			      try {
			         startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			         finish();
			      } catch (android.content.ActivityNotFoundException ex) {
			         Toast.makeText(getApplicationContext(), 
			         "There is no email client installed.", Toast.LENGTH_SHORT).show();
			      }
				
			}
		});
	}
	
	
}
