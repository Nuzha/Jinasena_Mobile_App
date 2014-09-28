package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ReturnItemsEmail extends Activity {
	
	EditText to,subject;
	Button send;
	TextView del,content;
	String delear,scont;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_return_items_email);
		
		to=(EditText)findViewById(R.id.etv_rie_to_r);
		subject=(EditText)findViewById(R.id.etv_rie_subject_r);
		send=(Button)findViewById(R.id.btn_rie_sendemail_r);
		
		del=(TextView)findViewById(R.id.tv_rie_logger_r);
		Intent intent = getIntent();
		delear = intent.getStringExtra("user");
		del.setText(delear);
		
		content=(TextView)findViewById(R.id.etv_rie_content_r);
		Intent intent2 = getIntent();
		scont = intent2.getStringExtra("content");
		content.setText(scont);
		
		
		final String email = to.getText().toString().trim();

		final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

		
		send.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					final String mail=to.getText().toString().trim();
	            	

	            	String emailAdd[]={mail};
	            	
	            	//emailAdd.add(mail);
	            	String sub=subject.getText().toString();
	                String con=content.getText().toString();
	                
	                Intent email=new Intent(android.content.Intent.ACTION_SEND);
	                email.putExtra(android.content.Intent.EXTRA_EMAIL, emailAdd);
	                email.putExtra(android.content.Intent.EXTRA_SUBJECT, sub);
	                email.setType("plain/text");
	                email.putExtra(android.content.Intent.EXTRA_TEXT, con);
	                startActivity(email);

	                /*Dialog d=new Dialog(SendEmail.this);
					d.setTitle("Successfull");
					TextView t=new TextView(SendEmail.this);
					t.setText("Email is successfully sent");
					d.setContentView(t);
					d.show();*/
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.return_items_email, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
