package com.NIRR.jenasena;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText username,password;
	public static String packagename="com.NIRR.jenasena";
	String idgetter;
	Button loging,auth;
	RadioGroup rg;
	protected SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password=(EditText)findViewById(R.id.et_I_password_activitymain_a);
		loging=(Button)findViewById(R.id.bt_I_loging_activitymain_a);
		auth=(Button)findViewById(R.id.button1);
		auth.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent authpage=new Intent(MainActivity.this,Auth.class);
				startActivity(authpage);
			}
		});
		username=(EditText)findViewById(R.id.et_I_username_activitymain_a);
		rg=(RadioGroup)findViewById(R.id.rg_I_selectloging_activity_a);
		final DBCreater entry = new DBCreater(MainActivity.this);
		entry.openforread();
		int repcount = entry.countrep();
		int delcount = entry.countdel();
		int delstock = entry.countdelStock();
		int repstock = entry.countrepstock();
		int invoice =entry.countInvoice();
		int cart=entry.countRanCart();
		int invo=entry.countRanInvo();
		int cus=entry.countCustomers();
		int ret=entry.countReturnedItems();
		int task= entry.countTasks();
		int taskList= entry.countTasksList();
		
		if(repcount==0){
			entry.createRepentry("Del01Rep01", "Del01Rep01", "Isuru", "Del01","Del01Rep01","0771478529","isurusnegativefeedbacking@gmail.com","Weediya Bandara Mawatha Nattandiya");
			entry.createRepentry("Del02Rep02", "Del02Rep02", "Yassasivi", "Del02","Del02Rep02","0714567891","Yassasivi@gmail.com","Marawila");
			entry.createRepentry("Del03Rep03", "Del03Rep03", "Mindule", "Del03","Del03Rep03","0781234569","Mindule@gmail.com","Walahapitiya");
			entry.createRepentry("Del01Rep04", "Del01Rep04", "Menoli", "Del01","Del01Rep04","0727894562","Menoli@gmail.com","Thuthtirepitiya");
			entry.createRepentry("Del02Rep05", "Del02Rep05", "Chamira", "Del02","Del02Rep05","0774561238","Chamira@gmail.com","Mathara");
			entry.createRepentry("Del03Rep06", "Del03Rep06", "Roshini", "Del03","Del03Rep06","0778945612","Roshini@gmail","Kalaniya");
			entry.createRepentry("Del01Rep07", "Del01Rep07", "Randi", "Del01","Del01Rep07","07745612397","Randi@gmail","Baralla");
			entry.createRepentry("Del03Rep08", "Del03Rep08", "Nadisha", "Del03","Del03Rep08","0778963214","Nadisha@gmail","Kalaniya");
			entry.createRepentry("Del02Rep09", "Del02Rep09", "Narmada", "Del02","Del02Rep09","0781654739","Narmada@gmail","Walahapitiya");
			entry.createRepentry("Del01Rep10", "Del01Rep10", "Infass", "Del01","Del01Rep10","0721654739","Infass@gmail","Kollupitiya");
			
		}
		if(delcount==0){
			entry.createDeler("Del01", "Del01", "Del01", "Rangith","0723849465","Rangiti@gmail.com","Nattandiya","Samira Backers");
			entry.createDeler("Del02", "Del02", "Del02", "Nilmini","0712587536","Nilmini@2gmail.com","Nattandiya","Dhamro Pvt.ltd");
			entry.createDeler("Del03", "Del03", "Del03", "Ramish","0774238915","remish1993@gmail.com","Nattandiya","Singar Pvt.ltd");
		}
		if(delstock==0){
			entry.createdelstock("Del01", "Isuru", "Electric Motors", 3000.00, 1000, 1000, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			entry.createdelstock("Del01", "Isuru", "Water Pumps", 3500.00, 500, 500, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			entry.createdelstock("Del01", "Isuru", "Water Purifiers", 4000.00, 300, 300, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			entry.createdelstock("Del01", "Nimal", "Reapers", 1500.00, 1000, 1000, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			entry.createdelstock("Del01", "Nimal", "Threshers", 2000.00, 100, 100, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			entry.createdelstock("Del01", "Nimal", "Domestic pumps", 2200.00, 700, 700, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			entry.createdelstock("Del01", "Isuru", "Drip system", 2500.00, 300, 300, "2014/09/01", "2014/09/30", "2014/09/30", 0, 0);
			
			entry.createdelstock("Del02", "A.W.Perera", "Electric Motors", 3000.00, 1000, 1000, "2014/08/21", "2014/08/26", "2014/08/30", 0, 0);
			entry.createdelstock("Del02", "Sahan Silva", "Water Pumps", 3500.00, 500, 500, "2014/08/21", "2014/08/26", "2014/08/30", 0, 0);
			entry.createdelstock("Del02", "A.W.Perera", "Water Purifiers", 4000.00, 300, 300, "2014/08/21", "2014/08/26", "2014/08/30", 0, 0);
			entry.createdelstock("Del03", "Siril Diaz", "Electric Motors", 3000.00, 1000, 1000, "2014/08/20", "2014/08/26", "2014/08/30", 0, 0);
			entry.createdelstock("Del03", "Siril Diaz", "Water Pumps", 3500.00, 500, 500, "2014/08/20", "2014/08/27", "2014/08/30", 0, 0);
			entry.createdelstock("Del03", "Ishan Perera", "Water Purifiers", 4000.00, 300, 300, "2014/08/20", "2014/08/26", "2014/08/30", 0, 0);
		}
		if(repstock==0){
			entry.createrepstock("Del01Rep01", "Del01", "Electric Motors", 100, 100, "2014/9/23", "2014/11/1", "2014/10/1", 3000.00);
			entry.createrepstock("Del01Rep01", "Del01", "Water Purifiers", 80, 80, "2014/9/23", "2014/11/3", "2014/10/3", 4000.00);
			entry.createrepstock("Del01Rep01", "Del01", "Water Pumps", 70, 70, "2014/9/23", "2014/11/3", "2014/10/3", 3500.00);
			entry.createrepstock("Del01Rep01", "Del01", "Drip system", 160, 160, "2014/9/23", "2014/11/3", "2014/10/3", 2500.00);
			entry.createrepstock("Del01Rep01", "Del01", "Threshers", 60, 60, "2014/9/23", "2014/11/3", "2014/10/3", 2000.00);
			entry.createrepstock("Del01Rep01", "Del01", "Reapers",  12, 12, "2014/9/23", "2014/11/3", "2014/10/3", 1500.00);
			entry.createrepstock("Del01Rep01", "Del01", "Domestic pumps", 90, 90, "2014/9/23", "2014/11/3", "2014/10/3", 2200.00);
 
			entry.createrepstock("Del01Rep01", "Del01", "Drip system", 160, 20, "2014/7/11", "2014/8/3", "2014/7/13", 2500.00);
			entry.createrepstock("Del01Rep01", "Del01", "Threshers", 60, 20, "2014/7/11", "2014/8/3", "2014/7/13", 2000.00);
			entry.createrepstock("Del01Rep01", "Del01", "Reapers",  12, 20, "2014/7/11", "2014/8/3", "2014/7/13", 1500.00);
			entry.createrepstock("Del01Rep01", "Del01", "Domestic pumps", 90, 20, "2014/7/11", "2014/8/3", "2014/7/13", 2200.00);
			
			entry.createrepstock("Del01Rep04", "Del01", "Drip system", 160, 20, "2014/7/11", "2014/6/3", "2014/6/3", 2500.00);
			entry.createrepstock("Del01Rep04", "Del01", "Threshers", 60, 20, "2014/7/11", "2014/6/3", "2014/6/3", 2000.00);
			entry.createrepstock("Del01Rep04", "Del01", "Reapers",  12, 20, "2014/7/11", "2014/6/3", "2014/6/3", 1500.00);
			entry.createrepstock("Del01Rep04", "Del01", "Domestic pumps", 90, 20, "2014/7/11", "2014/6/3", "2014/6/3", 2200.00);
		
			
		
		}
		if(invoice==0){ 
			entry.createIInvoice( "In01", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 25000.00,20000.00 , 10000.00, 10000.00, "2014/7/11", "2014/06/01", "2014/07/01");
			entry.createIInvoice( "In02", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 55000.00,50000.00 , 10000.00, 40000.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In03", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 5000.00,4500.00 , 500.00, 4000.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In04", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 15000.00,10000.00 , 100.00, 9900.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In05", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 2000.00,1500.00 , 1000.00, 500.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In06", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 25000.00,20000.00 , 10000.00, 10000.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In07", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 125000.00,120000.00 , 120000.00, 0.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In08", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 25000.00,20000.00 , 10000.00, 10000.00, "2014/7/11", "2014/06/01", "2014/07/01");
			entry.createIInvoice( "In09", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 55000.00,50000.00 , 10000.00, 40000.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In10", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 5000.00,4500.00 , 500.00, 4000.00, "2014/7/11", "2014/06/03", "2014/07/03");
			entry.createIInvoice( "In11", "Del01Rep01Cus1", "Del01Rep01", "Del01", 500.00, 15000.00,10000.00 , 100.00, 9900.00, "2014/7/11", "2014/06/03", "2014/07/03");
		}
		
		if(cart==0){
			entry.createCart("Del01Rep01", "Del01Rep01Cus1","Invoice1", "Water Pumps", 10, 3500.00, 35000.00);
			entry.createCart("Del01Rep01", "Del01Rep01Cus1", "Invoice1","Reapers", 10,  1500.00,  15000.00 );
			entry.createCart("Del01Rep01", "Del01Rep01Cus1","Invoice1", "Domestic pumps", 20, 2200.00, 44000.00 );
			entry.createCart("Del01Rep01", "Del01Rep01Cus2", "Invoice2","Electric Motors", 20, 3000.00, 60000.00 );
			entry.createCart("Del01Rep01", "Del01Rep01Cus2","Invoice2" , "Reapers", 10,  1500.00,  15000.00 );
			entry.createCart("Del01Rep01", "Del01Rep01Cus3","Invoice3", "Domestic pumps", 10,   2200.00, 22000.00 );
			entry.createCart("Del01Rep02", "Del01Rep01Cus3","Invoice3", "Domestic pumps", 20, 3500.00, 70000.00);
			

//		entry.createCart("Del01Rep01", "Del01Rep01Cus1","Invo1", "Water Pumps", 10, 3500.00, 35000.00, "2014/2/14", "2014/7/13", "Not Paid");
//		entry.createCart("Del01Rep01", "Del01Rep01Cus1", "Invo1","Reapers", 10,  1500.00,  15000.00, "2014/2/14", "2014/7/1", "Not Paid");
//		entry.createCart("Del01Rep01", "Del01Rep01Cus1","Invo1", "Domestic pumps", 20, 2200.00, 44000.00, "2014/2/14", "2014/8/9","Not Paid");
//		entry.createCart("Del01Rep01", "Del01Rep01Cus1", "Invo1","Electric Motors", 20, 3000.00, 60000.00,  "2014/2/14", "2014/8/10", "Not Paid");
		
//		entry.createCart("Del01Rep01", "Del01Rep01Cus1","Invo2" , "Reapers", 10,  1500.00,  15000.00, "2014/2/16", "2014/6/26", "Not Paid");
//		entry.createCart("Del01Rep01", "Del01Rep01Cus2","Invo3" , "Electric Motors", 20, 3000.00, 60000.00, "2014/2/20", "2014/7/14", "Not Paid");
		
		/*
		entry.createCart("Del01Rep01", "Cus5","Invo4", "Domestic pumps", 10,   2200.00, 22000.00, "2014/2/20", "2014/8/20","Not Paid");
		
		entry.createCart("Del01Rep01", "Cus1","Invo5" ,"Water Pumps", 10, 4000.00, 40000.00, "2014/5/14", "2014/7/12", "Not Paid");
		
		entry.createCart("Del01Rep02", "Cus3","Invo6", "Domestic pumps", 20, 3500.00, 70000.00, "2014/4/14", "2014/7/7","2014/3/25");
		entry.createCart("Del01Rep03", "Cus4","Invo7", "Electric Motors", 50, 3000.00, 150000.00,  "2014/1/4", "2014/7/10", "2014/2/15");
		entry.createCart("Del01Rep01", "Cus2","Invo8" ,"Reapers", 10,  3000.00,  30000.00, "2014/2/8", "2014/7/11", "NULL");
		entry.createCart("Del01Rep01", "Cus5","Invo9","Domestic pumps", 10,   3500.00,  35000.00, "2014/1/20", "2014/7/11","2014/2/25");

*/
		}
		
		if(invo==0){
			entry.createInvoiceEntry("Invoice1", "Del01Rep01", "Del01Rep01Cus1",0,94000.00,94000.00,0,94000.00,"2014/2/4","2014/9/14", "Not Paid");
			entry.createInvoiceEntry("Invoice2", "Del01Rep01", "Del01Rep01Cus2",0,75000.00,75000.00,0,75000.00,"2014/2/14", "2014/9/28", "Not Paid");
			entry.createInvoiceEntry("Invoice3", "Del01Rep01", "Del01Rep01Cus3",0,92000.00,92000.00,0,92000.00,"2014/2/14","2014/10/1", "Not Paid");
			
			entry.createInvoiceEntry("Invoice4", "Del01Rep01", "Del01Rep01Cus1",0,90000.00,90000.00,4000.0,86000.00,"2014/3/14","2014/9/14", "2014/9/12");
			entry.createInvoiceEntry("Invoice5", "Del01Rep01", "Del01Rep01Cus2",0,78000.00,78000.00,78000.00,0,"2014/3/4", "2014/9/18", "2014/9/14");
			entry.createInvoiceEntry("Invoice6", "Del01Rep01", "Del01Rep01Cus3",0,98000.00,98000.00,0,98000.00,"2014/3/14","2014/11/1", "Not Paid");
			
			entry.createInvoiceEntry("Invoice7", "Del01Rep01", "Del01Rep01Cus1",0,90000.00,90000.00,4000.0,86000.00,"2014/6/14","2014/10/20", "2014/9/12");
			entry.createInvoiceEntry("Invoice8", "Del01Rep01", "Del01Rep01Cus2",0,78000.00,78000.00,8000.00,70000.00,"2014/8/5", "2014/10/8", "2014/9/14");
			entry.createInvoiceEntry("Invoice9", "Del01Rep01", "Del01Rep01Cus3",0,98000.00,98000.00,0,98000.00,"2014/7/14","2014/10/1", "Not Paid");
			
		}
		
	//	entry.createCusEntry(scustemername, sshopname, slocation, semail, sphone, saddress, srep, sloger)
	
			/*
			entry.createCusEntry("Tania", "ABans", "Maharagama", "abans@gmail.com", "0114257841", "318-Maharagama","Del01Rep01", "Del01");
			entry.createCusEntry("Kamali", "Singer", "Panadura", "singer@gmail.com", "0114257842", "300-Panadura","Del01Rep01", "Del01");
			entry.createCusEntry("Sunil", "Modern Homes", "Kalutar", "mhomes@gmail.com", "0114267842", "3-Kalutara","Del02Rep02", "Del02");
			entry.createCusEntry("Sena", "Sidath Hardware", "Kadawata", "ss@gmail.com", "0111267842", "3-Kadawatha","Del02Rep02", "Del02");
			
			*/
	
		if(ret==0){
			entry.createReturnedItems("Del01", "Rep01", "Electric Motors", 2, "2014/9/22", "Damaged covers");
			entry.createReturnedItems("Del01", "Rep01", "Water Pumps", 2, "2014/9/22", "Damaged covers");
			entry.createReturnedItems("Del01", "Rep01", "Electric Motors", 3, "2014/9/30", "Machine is not working properly");
			entry.createReturnedItems("Del01", "Rep02", "Electric Motors", 3, "2014/9/30", "Machine is not working properly");
			entry.createReturnedItems("Del01", "Rep02", "Threshers", 2, "2014/9/30", "Machine is not working properly");
			}	
		
		if(task==0){
			entry.addTask("Settle the payments", "Del01Rep01", "2014/9/27");
			entry.addTask("Get the stock", "Del01Rep01", "2014/9/27");
		}
		
		if(taskList==0){
			entry.addTaskList("Settle the payments", "Del01Rep01");
			entry.addTaskList("Get the stock", "Del01Rep01");
		}
		
		if(cus==0){
			entry.createEntry("Tania", "ABans", "Maharagama", "0114257841", "abans@gmail.com", "318-Maharagama","Del01Rep01", "Del01","");
			entry.createEntry("Kamali", "Singer", "Panadura",  "0114257842","singer@gmail.com","300-Panadura","Del01Rep01", "Del01","");
			entry.createEntry("Sunil", "Modern Homes", "Kalutar", "0114267842","mhomes@gmail.com", "3-Kalutara","Del02Rep02", "Del02","");
		}
		
		
		loging.setOnClickListener(new View.OnClickListener() {
			
			
			@TargetApi(Build.VERSION_CODES.GINGERBREAD) public void onClick(View v) {
				// TODO Auto-generated method stub
				final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
				String uname=username.getText().toString();
				String pwd=password.getText().toString();
				if(uname.isEmpty()  && pwd.isEmpty()){
					Toast.makeText(getApplicationContext(), " Please enter borth username and password to logging ", Toast.LENGTH_SHORT).show();
				}
				if(value.equalsIgnoreCase("Dealer")&& !uname.isEmpty()  && !pwd.isEmpty()){
					Log.e("A","A");
					Cursor mcurser = entry.getunamebypwd(pwd);
					Log.e("A","A");
					if(mcurser.getCount()==0){
						Log.e("A","A");
						Toast.makeText(getApplicationContext(), " Your id not regestered ", Toast.LENGTH_SHORT).show();
					}
					else if(mcurser.getCount()!=0){
						Log.e("A","A");
						String getusername=mcurser.getString(0);
						Log.e("A","A");
						if(uname.equals(getusername)){
							Log.e("A","A");
							if(!entry.getuid(pwd).isEmpty()){
								Log.e("A","A");
								Toast.makeText(getApplicationContext(), " Succesfully loging in as "+entry.getuid(pwd).toString(), Toast.LENGTH_SHORT).show();
								Log.e("A","A");
								Intent openStartingPoint = new Intent(MainActivity.this,DelearMenu.class);
								
								openStartingPoint.putExtra(packagename, entry.getuid(pwd).toString());
								startActivity(openStartingPoint);
							}
						}
						else{
							Toast.makeText(getApplicationContext(), " Password dosen't match for the username ", Toast.LENGTH_SHORT).show();
						}
					}
				}
				if(value.equalsIgnoreCase("Rep")&& !uname.isEmpty()  && !pwd.isEmpty()){
					Cursor mcurser = entry.getunamebypwdrep(pwd);
					if(mcurser.getCount()==0){
						Toast.makeText(getApplicationContext(), " Your id not regestered ", Toast.LENGTH_SHORT).show();
					}
					else if(mcurser.getCount()!=0){
						String getusername=mcurser.getString(0);
						if(uname.equals(getusername)){
							if(!entry.getuidrep(pwd).isEmpty()){
								Toast.makeText(getApplicationContext(), " Succesfully loging in as "+entry.getuidrep(pwd).toString(), Toast.LENGTH_SHORT).show();
								Intent openStartingPoint = new Intent(MainActivity.this,RepMenu.class);
								openStartingPoint.putExtra(packagename, entry.getuidrep(pwd).toString());
								startActivity(openStartingPoint);
							}
						}
						else{
							Toast.makeText(getApplicationContext(), " Password dosen't match for the username ", Toast.LENGTH_SHORT).show();
						}
					}
				}
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
