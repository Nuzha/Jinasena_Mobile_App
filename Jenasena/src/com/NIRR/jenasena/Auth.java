package com.NIRR.jenasena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Auth extends Activity{
	
	Button addDel,addRep;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.auth);
		addDel=(Button)findViewById(R.id.button1);
		addDel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent adddell=new Intent(Auth.this,AddDel.class);
				startActivity(adddell);
			}
		});
		addRep=(Button)findViewById(R.id.button2);
		addRep.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent adddell=new Intent(Auth.this,AddRep.class);
				startActivity(adddell);
			}
		});
		
	}
	

}
