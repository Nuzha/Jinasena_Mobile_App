package com.NIRR.jenasena;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EmailDailyInvoiceDetails extends Activity {

	 
	TextView content,to,subjecttext;
	Button sendemail;
    String  subject, message, attachmentFile;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;
    int columnIndex;
    String Dealeremail;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email_daily_invoice_details);
		to=(TextView)findViewById(R.id.tv_To_emailDailyInvo);
		content=(TextView)findViewById(R.id.tv_content_emailDailyInvo);
		subjecttext=(TextView)findViewById(R.id.tv_subject_emailDailyInvo);
		sendemail=(Button)findViewById(R.id.bt_sendEmail_emailDailyInvo);
		
		content.setText("The daily invoice report has been attached to the email");
		subjecttext.setText("Daily Invoice Details");
		
		Intent intent=getIntent();
		Dealeremail = intent.getStringExtra("delEmail");
		to.setText(Dealeremail);
		
		
		/*dbHelper = new DBCreater(this);
		dbHelper.open();
		mcurser=dbHelper.Delearemail(s2);
		*/
		
		//String delId=
		
		
		
		
		sendemail.setOnClickListener(new View.OnClickListener() {
			String emailAddress=to.getText().toString();
		    String sub=subjecttext.getText().toString();
		    String cc="";
		    
	
			@Override
			public void onClick(View v) {
				
				
				
				// TODO Auto-generated method stub
				String filelocation="/storage/emulated/0/PDF/DailyInvoiceReport.pdf";
				File file = new File(filelocation);
			    Uri pdfUri = Uri.fromFile(file);
				
				
				    
			    
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				//Intent emailIntent = new Intent(Intent.);
				// set the type to 'email'
				
				////////////
				emailIntent.setType("application/pdf");
				//emailIntent.setType("plain/text");
				
				
				String toadd[] = {to.getText().toString()};
				String CC[] = {cc};
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, toadd);
				emailIntent.putExtra(android.content.Intent.EXTRA_CC, CC);
				
				
				// the message body
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, content.getText().toString());
				// the mail subject
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subjecttext.getText().toString());
				//the attachment
				emailIntent .putExtra(android.content.Intent.EXTRA_STREAM, pdfUri);
				
				 try {
			         startActivity(emailIntent);
			         finish();
			      } catch (android.content.ActivityNotFoundException ex) {
			         Toast.makeText(getApplicationContext(), 
			         "There is no email client installed.", Toast.LENGTH_SHORT).show();
			      }
				
			
				
			}
		});
	}
		
	
	
		
	
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email_daily_invoice_details, menu);
		return true;
	}

}
