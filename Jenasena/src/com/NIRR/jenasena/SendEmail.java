package com.NIRR.jenasena;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendEmail extends Activity {
	EditText to,subject,content;
	Button send;
	TextView del;
	String delear,strContent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_email);
		
		to=(EditText)findViewById(R.id.etv_to_r);
		subject=(EditText)findViewById(R.id.etv_subject_r);
		send=(Button)findViewById(R.id.btn_sendemail_r);
		
		del=(TextView)findViewById(R.id.tv_logger_r);
		Intent intent = getIntent();
		delear = intent.getStringExtra("loggeduser");
		del.setText(delear);
		
		content=(EditText)findViewById(R.id.etv_content_r);
		Intent intent1 = getIntent();
		strContent = intent1.getStringExtra("content");
		content.setText(strContent);
		
		
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

	               }
			});
		
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send_email, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

}
