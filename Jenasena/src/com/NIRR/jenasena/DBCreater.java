package com.NIRR.jenasena;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

public class DBCreater {
	
	
	
	public static final String Key_customer_Id       = "_id";
	public static final String Key_customer_CId      = "cus_id";
	public static final String Key_customer_Name     = "customer_name";
	public static final String Key_customer_Shop     = "customer_shop";
	public static final String Key_customer_Rep      = "customer_rep";
	public static final String Key_customer_Delear   = "customer_delear";
	public static final String Key_customer_Location = "customer_location";
	public static final String Key_customer_Phon     = "customer_phon";
	public static final String Key_customer_Email    = "customer_email";
	public static final String Key_customer_Address  = "customer_address";
	public static final String Key_customer_state    = "customer_state";
	
	public static final String Key_rep_Id            = "_id";
	public static final String Key_rep_RId           = "rep_id";
	public static final String Key_rep_Username      = "rep_uname";
	public static final String Key_rep_Password      = "rep_pwd";
	public static final String Key_rep_Name          = "rep_name";
	public static final String Key_rep_Phon          = "rep_phon";
	public static final String Key_rep_Email         = "rep_email";
	public static final String Key_rep_Address       = "rep_address";
	public static final String Key_rep_Delear        = "rep_delear";
	public static final String Key_rep_state         = "rep_state";
	
	public static final String Key_delear_Id         = "_id";
	public static final String Key_delear_DId        = "del_id";
	public static final String Key_delear_Username   = "del_uname";
	public static final String Key_delear_Password   = "del_pwd";
	public static final String Key_delear_Name       = "del_name";
	public static final String Key_delear_Phon       = "del_phon";
	public static final String Key_delear_Email      = "del_email";
	public static final String Key_delear_Address    = "del_address";
	public static final String Key_delear_Company    = "del_company";
	public static final String Key_delear_state      = "del_state";
	
	public static final String Key_Map_id            = "map_id";
	public static final String Key_Map_cusid         = "map_cusid";
	public static final String Key_Map_cusShop       = "map_cusShop";
	public static final String Key_Map_lan           = "map_lan";
	public static final String Key_Map_lat           = "map_lat";
	
	public static final String Key_loc_id            = "_id";
	public static final String Key_loc_rid           = "rid";
	public static final String Key_loc_lat           = "lat";
	public static final String Key_loc_lan           = "lan";
	public static final String Key_loc_date          = "date";
	public static final String Key_loc_time          = "time";
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//address jinasena STOCK
    public static final String Key_jinS_id				    ="_id";
    public static final String Key_jinS_item_id				="jinS_item_id";
	public static final String Key_jinS_item_name			="jinS_item_name";
	public static final String Key_jinS_quantity			="jinS_qty";
	public static final String Key_jinS_avail_qty			="jinS_avail_qty";
	public static final String Key_JinS_unit_price   		="jinS_unit_price";
	public static final String Key_jinS_selling_price		="jinS_selling_price";
	public static final String Key_JinS_discount		    ="JinS_discount";
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//############################################################################################################
	
	/*public static final String Key_repS_tab_id	               ="_id";
	public static final String Key_repS_rep_id				   ="repS_rep_id";
	public static final String Key_repS_del_id				   ="repS_del_id";
	public static final String Key_repS_item_name			   ="repS_item_name";
	public static final String Key_repS_assigned_qty		   ="repS_assigned_qty";
	public static final String Key_repS_available_qty		   ="repS_available_qty";
	public static final String Key_repS_assigned_date		   ="repS_assigned_date";
	public static final String Key_repS_due_date			   ="repS_due_date";
	public static final String Key_repS_paid_date			   ="repS_paid_date";
	public static final String Key_repS_unit_price             ="repS_unit_price";
	*/
	//RepStock
	public static final String Key_repS_tab_id	            ="_id"; 
	public static final String Key_repS_rep_id				="repS_rep_id";
	public static final String Key_repS_del_id				="repS_del_id";
	public static final String Key_repS_item_name			="repS_item_name";
	public static final String Key_repS_assigned_qty		="repS_assigned_qty";
	public static final String Key_repS_available_qty		="repS_available_qty";
	public static final String Key_repS_assigned_date		="repS_assigned_date";
	public static final String Key_repS_due_date			="repS_due_date";
	public static final String Key_repS_paid_date			="repS_paid_date";
	public static final String Key_repS_unit_price          ="repS_unit_price";
	
		
	public static final String  Key_delS_tbl_id			       ="_id";
	public static final String Key_delS_del_id			       ="delS_del_id";
	public static final String Key_delS_rep_id			       ="repS_rep_id";/////chnge nme
	public static final String Key_delS_del_item_name	       ="delS_del_item_name";
	public static final String Key_delS_unit_price		       ="delS_unit_price";
	public static final String Key_delS_purchased_qty	       ="delS_purchased_qty";
	public static final String Key_delS_available_qty	       ="delS_available_qty";
	public static final String Key_delS_purchased_date	       ="delS_purchased_date";
	public static final String Key_delS_due_date		       ="delS_due_date";
	public static final String Key_delS_paid_date		       ="delS_paid_date";
	public static final String Key_deltorepS_assigned_qty      ="deltorepS_assigned_qty";
	public static final String Key_delS_updated_assigned_qty   ="delS_updated_qty";
	
	//lowstock
	public static final String Key_lowstock_id = "_id";
	public static final String Key_lowstock_itemname = "lowstock_itemname";
	public static final String Key_lowstock_del_id = "lowstock_del_id";
	public static final String Key_lowstock_remaining_qty = "lowstock_remaining_qty";
	public static final String Key_lowstock_received_qty = "lowstock_received_qty";
	public static final String Key_lowstock_req_date = "lowstock_req_date";
	public static final String Key_lowstock_received_date = "lowstock_received_date";
	
	
	
	//invoice
	public static final String Key_ICart_tab_id            		="_id";
	public static final String Key_ICart_tab_in_id           	="In_id";
	public static final String Key_ICart_tab_cus_id            	="In_cusid";
	public static final String Key_ICart_tab_rep_id            	="In_repid";
	public static final String Key_ICart_tab_del_id            	="In_delid";
	public static final String Key_ICart_tab_dicount            ="In_discount";
	public static final String Key_ICart_tab_total_price        ="In_total_cost";
	public static final String Key_ICart_tab_total_priceAftDis  ="In_total_costdis";
	public static final String Key_ICart_tab_paid_cost          ="In_paid_cost";
	public static final String Key_ICart_tab_moreToPay          ="In_cost_to_pay";
	public static final String Key_ICart_tab_issued_date        ="In_issu_date";
	public static final String Key_ICart_tab_paid_date          ="In_paid_date";
	public static final String Key_ICart_tab_due_date           ="In_due_date";

	//Icart
	public static final String Key_IInvoic_tab_id            	="_id";
	public static final String Key_IInvoic_invoice_id        	="Crt_Inid";
	public static final String Key_IInvoic_item_id              ="Crt_itemid";
	public static final String Key_IInvoic_item_name            ="Crt_itemNameid";
	public static final String Key_IInvoic_quentity             ="Crt_quty";
	public static final String Key_IInvoic_cost            	    ="Crt_cost";
	public static final String Key_IInvoic_dictount            	="Crt_discount";
	
	
	// address the table Cart
/*	public static final String Key_Cart_tab_id = "_id";
	public static final String Key_Cart_rep_id = "Cart_rep_id";
	public static final String Key_Cart_cus_id = "Cart_cus_id";
	public static final String Key_Cart_invoice_id="Cart_invoice_id";
	public static final String Key_Cart_item_name = "Cart_item_name";
	public static final String Key_Cart_sold_quantity = "Cart_sold_qty";
	public static final String Key_Cart_unit_price = "Cart_unit_price";
	public static final String Key_Cart_total_price = "Cart_total_price";
	public static final String Key_Cart_issued_date = "Cart_issued_date";
	public static final String Key_Cart_due_date = "Cart_due_date";
	public static final String Key_Cart_paid_date = "Cart_paid_date";*/
	public static final String Key_Cart_tab_id = "_id";
	public static final String Key_Cart_rep_id = "Cart_rep_id";
	public static final String Key_Cart_cus_id = "Cart_cus_id";
	public static final String Key_Cart_invoice_id="Cart_invoice_id";
	public static final String Key_Cart_item_name = "Cart_item_name";
	public static final String Key_Cart_sold_quantity = "Cart_sold_qty";
	public static final String Key_Cart_unit_price = "Cart_unit_price";
	public static final String Key_Cart_total_price = "Cart_total_price";

	

			
	// address the table Invoice
 /*   public static final String Key_Invoice_id = "_id";
    public static final String Key_Invoice_invoice_id = "Invoice_invoice_id";
    public static final String Key_Invoice_rep_id = "Invoice_rep_id";
	public static final String Key_Invoice_cust_id = "Invoice_cus_id";
    public static final String Key_Invoice_selling_date = "Invoice_selling_date";
	public static final String Key_Invoice_amount = "Invoice_amount";
	public static final String Key_Invoice_amount_including_VAT = "Invoice_amount_in_vat";*/
    public static final String Key_Invoice_id = "_id";
    public static final String Key_Invoice_invoice_id = "Invoice_invoice_id";
    public static final String Key_Invoice_rep_id = "Invoice_rep_id";
	public static final String Key_Invoice_cust_id = "Invoice_cus_id";
	public static final String Key_Invoice_discount= "Invoice_discount";
	public static final String Key_Invoice_pre_dicount_amount = "Invoice_pre_discount_amount";
	public static final String Key_Invoice_post_dicount_amount = "Invoice_post_discount_amount";
	public static final String Key_Invoice_paid_cost = "Invoice_paid_cost";
	public static final String Key_Invoice_amount_to_be_paid= "Invoice_amount_to_be_paid";
    public static final String Key_Invoice_selling_date = "Invoice_selling_date";
	public static final String Key_Invoice_due_date= "Invoice_due_date";
	public static final String Key_Invoice_paid_date="Invoice_paid_date";

	
	
	//address the table of fakeInvoice
	public static final String Key_fInvoice_id			   ="_id";
	public static final String Key_fInvoice_name		   ="fInvoice_name";
	public static final String Key_fInvoice_qty		       ="fInvoice_qty";
	public static final String Key_fInvoice_unit_price	   ="fInvoice_unit_price";//delear stock
	
	//returned items
	public static final String Key_returned_items_id      ="_id";
	public static final String Key_returned_items_del_id  ="returned_items_del_id";
	public static final String Key_returned_items_rep_id  ="returned_items_rep_id";
	public static final String Key_returned_items_item_name="returned_items_items_name";
	public static final String Key_returned_items_quantity ="returned_items_quantity";
	public static final String Key_returned_items_date= "returned_items_date";
	public static final String Key_returned_items_issue ="returned_items_issue";
	
	//Del Payment
    public static final String Key_delS_payment_id				    ="_id";
    public static final String Key_delS_payment_rep_id				="delS_payment_rep_id";
    public static final String Key_delS_payment_assign_date			="delS_payment_assign_date";
    public static final String Key_delS_payment_total_cost			="delS_payment_total_cost";
    public static final String Key_delS_payment_status				="delS_payment_status";
    public static final String Key_delS_payment_target_date			="delS_payment_target_date";
    public static final String Key_delS_payment_received_amount		="delS_payment_received_amount";
    public static final String Key_delS_payment_remaining_amount	="delS_payment_remaining_amount";
    public static final String Key_delS_payment_received_final_date	="delS_payment_received_final_date";
    
 // tasks Table Columns names
 		public static final String KEY_TASK_ID = "_id";
 		public static final String KEY_TASK_REP_ID = "rep_id";
 		public static final String KEY_TASKNAME = "taskName";
 		public static final String KEY_DATE = "date";
 		
 		public static final String KEY_TASK_LIST_ID = "_id";
 		public static final String KEY_TASK_LIST_TASKNAME = "taskName";
 		public static final String KEY_TASK_LIST_REP_ID = "rep_id";
    
	
	
	//############################################################################################################
	
	private static final String DATABASE_NAME           ="IsuruJinasena";
	private static final String DATABASE_TABLE_CUSTOMER ="Customer";
	private static final String DATABASE_TABLE_REP      ="Rep";
	private static final String DATABASE_TABLE_DELEAR   ="DELEAR";
	private static final String DATABASE_TABLE_REP_STOCK    ="Rep_Stock"; 
	private static final String DATABASE_TABLE_DEALER_STOCK ="Del_Stock";
	private static final String DATABASE_TABLE_JIN_STOCK    ="Jin_Stock";
	private static final String DATABASE_TABLE_ICART         ="ICart";
	private static final String DATABASE_TABLE_IInvoice      ="IInovice";
	private static final String DATABASE_TABLE_CART         ="Cart";
	private static final String DATABASE_TABLE_INVOICE      ="Invoice";
	private static final String DATABASE_TABLE_F_INVOICE    ="FInvoice";
	private static final String DATABASE_TABLE_MAP    ="gMap";
	private static final String DATABASE_TABLE_STORLOC    ="storloc";
	private static final String DATABASE_TABLE_TRACKREP    ="trackrep";
	private static final String DATABASE_TABLE_GETLOC    ="getloc";
	private static final String DATABASE_TABLE_LOWSTOCK      ="Lowstock";
	private static final String DATABASE_TABLE_RETURNED_ITEMS="Returned_items";
	private static final String DATABASE_TABLE_DEALER_PAYMENTS="Dealer_payments";
	private static final String DATABASE_TABLE_TASKS = "Tasks";
	private static final String DATABASE_TABLE_TASKS_LIST = "Task_List"; 

	private static final Integer DATABASE_VERSION       =27;
	
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	private static final String tag ="DBCreater";
	
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE IF NOT EXISTS " +  DATABASE_TABLE_CUSTOMER + " ("+
					Key_customer_Id + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
					Key_customer_CId + " TEXT , " + 
					Key_customer_Name + " TEXT , " + 
					Key_customer_Shop + " TEXT , " + 
					Key_customer_Location + " TEXT , " + 
					Key_customer_Phon + " TEXT , " + 
					Key_customer_Email + " TEXT , " +
					Key_customer_Address + " TEXT , " +
					Key_customer_Rep + " TEXT , " +
					Key_customer_Delear + " TEXT , " +
					Key_customer_state + " TEXT  ); "
							);
			db.execSQL("CREATE TABLE IF NOT EXISTS " +  DATABASE_TABLE_REP + " ("+
					Key_rep_Id + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
					Key_rep_RId + " TEXT NOT NULL, " + 
					Key_rep_Username + " TEXT NOT NULL, " + 
					Key_rep_Password + " TEXT NOT NULL, " +
					Key_rep_Name + " TEXT NOT NULL, " +
					Key_rep_Phon + " TEXT NOT NULL, " + 
					Key_rep_Email + " TEXT NOT NULL, " +
					Key_rep_Address + " TEXT NOT NULL, " +
					Key_rep_Delear + " TEXT NOT NULL, " +
					Key_rep_state + " TEXT NOT NULL ); "
							);
			db.execSQL("CREATE TABLE IF NOT EXISTS " +  DATABASE_TABLE_DELEAR + " ("+
					Key_delear_Id + " INTEGER  PRIMARY KEY AUTOINCREMENT, " +
					Key_delear_DId + " TEXT NOT NULL, " + 
					Key_delear_Username + " TEXT NOT NULL, " + 
					Key_delear_Password + " TEXT NOT NULL, " +
					Key_delear_Name + " TEXT NOT NULL, " +
					Key_delear_Phon + " TEXT NOT NULL, " + 
					Key_delear_Email + " TEXT NOT NULL, " +
					Key_delear_Address + " TEXT NOT NULL, " +
					Key_delear_Company + " TEXT NOT NULL, " +
					Key_delear_state + " TEXT NOT NULL ); "
							);
			
			//create jinasena stock table
			db.execSQL("CREATE TABLE IF NOT EXISTS " +  DATABASE_TABLE_JIN_STOCK + " ("+
					Key_jinS_id + " TEXT PRIMARY KEY , " +
					Key_jinS_item_id + " TEXT  , " +
					Key_jinS_item_name + " TEXT, " + 
					Key_jinS_quantity + " INTEGER, " + 
					Key_jinS_avail_qty+ " INTEGER, " +
					Key_JinS_unit_price + " TEXT, " +
					Key_jinS_selling_price + " TEXT, " +
					Key_JinS_discount+ "TEXT );" 
							);
			
			db.execSQL("CREATE TABLE IF NOT EXISTS " +  DATABASE_TABLE_MAP + " ("+
					Key_Map_id + " INTEGER PRIMARY KEY , " +
					Key_Map_cusid + " TEXT  , " +
					Key_Map_cusShop + " TEXT, " + 
					Key_Map_lan + " REAL, " + 
					Key_Map_lat+ " REAL);" 
							);
			
			
			//???????????????????????????????????????????????????????????????????????????????????????????????
			
			//create repStock table
			/*db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_REP_STOCK + " ("+
		  	  
			Key_repS_tab_id	+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +	
			Key_repS_rep_id + " TEXT," +
			Key_repS_del_id + " TEXT, " +
			Key_repS_item_name + " TEXT, " +
			Key_repS_assigned_qty + " INTEGER, " +
			Key_repS_unit_price + " REAL, " +
		    Key_repS_available_qty + " INTEGER, " +
			Key_repS_assigned_date + " TEXT NOT NULL ," +
			Key_repS_due_date + " TEXT NOT NULL, " +
			Key_repS_paid_date + " TEXT NOT NULL );"
					);
			*/
			
			
			//create repStock table
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_REP_STOCK + " ("+
		  	  
			Key_repS_tab_id	+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +	
			Key_repS_rep_id + " TEXT," +
			Key_repS_del_id + " TEXT, " +
			Key_repS_item_name + " TEXT, " +
			Key_repS_assigned_qty + " INTEGER, " +
			Key_repS_unit_price + " REAL, " +
		    Key_repS_available_qty + " INTEGER, " +
			Key_repS_assigned_date + " TEXT NOT NULL ," +
			Key_repS_due_date + " TEXT NOT NULL, " +
			Key_repS_paid_date + " TEXT NOT NULL );"
					);
			
			//create delStock table
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_DEALER_STOCK + " ("+
			Key_delS_tbl_id	 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  
			Key_delS_del_id + " TEXT , " +
			Key_delS_rep_id + " TEXT , " +
			Key_delS_del_item_name + " TEXT , " +
			Key_delS_unit_price + " REAL , " +
			Key_delS_purchased_qty + " INTEGER , " +
			Key_delS_available_qty + " INTEGER , " +
			Key_delS_purchased_date + " TEXT , " +
			Key_delS_due_date + " TEXT , " +
			Key_delS_paid_date + " TEXT , " +
			Key_deltorepS_assigned_qty + " INTEGER , " +
 			Key_delS_updated_assigned_qty + " INTEGER ); "
			);
			
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_IInvoice + " ("+
					Key_ICart_tab_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  
					Key_ICart_tab_in_id + " TEXT , " +
					Key_ICart_tab_cus_id + " TEXT , " +
					Key_ICart_tab_rep_id + " TEXT , " +
					Key_ICart_tab_del_id + " TEXT , " +
					Key_ICart_tab_dicount + " REAL , " +
					Key_ICart_tab_total_price+ " REAL , " +
					Key_ICart_tab_total_priceAftDis + " REAL , " +
					Key_ICart_tab_paid_cost + " REAL , " +
					Key_ICart_tab_moreToPay + " REAL , " +
					Key_ICart_tab_issued_date + " TEXT , " +
					Key_ICart_tab_paid_date + " TEXT , " +
					Key_ICart_tab_due_date + " TEXT ); "
					);
			
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_ICART + " ("+
					Key_IInvoic_tab_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  
					Key_IInvoic_invoice_id + " TEXT , " +
					Key_IInvoic_item_id + " TEXT , " +
					Key_IInvoic_item_name+ " TEXT , " +
					Key_IInvoic_quentity + " TEXT , " +
					Key_IInvoic_cost + " TEXT ); "
					);
			
			
			// table cart
			/*db.execSQL("CREATE TABLE IF NOT EXISTS "
					+ DATABASE_TABLE_CART
					+ " ("
					+ Key_Cart_tab_id + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
					+ Key_Cart_rep_id + " TEXT  , " 
					+ Key_Cart_cus_id + " TEXT  , " 
					+ Key_Cart_invoice_id + " TEXT , "
					+ Key_Cart_item_name + " TEXT  , "
					+ Key_Cart_sold_quantity + " INTEGER  , "
					+ Key_Cart_unit_price + " REAL  , " + Key_Cart_total_price
					+ " REAL , " + Key_Cart_issued_date + " TEXT , "
					+ Key_Cart_due_date + " TEXT  ," + Key_Cart_paid_date
					+ " TEXT);");*/
			db.execSQL("CREATE TABLE IF NOT EXISTS "
					+ DATABASE_TABLE_CART
					+ " ("
					+ Key_Cart_tab_id + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
					+ Key_Cart_rep_id + " TEXT  , " 
					+ Key_Cart_cus_id + " TEXT  , " 
					+ Key_Cart_invoice_id + " TEXT , "
					+ Key_Cart_item_name + " TEXT  , "
					+ Key_Cart_sold_quantity + " INTEGER  , "
					+ Key_Cart_unit_price + " REAL  , "
					+ Key_Cart_total_price + " REAL);");

			
			
			//table invoice
		/*	db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_INVOICE
					+ " (" + Key_Invoice_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ Key_Invoice_invoice_id + " TEXT , " 
					+ Key_Invoice_rep_id + " TEXT , " 
					+ Key_Invoice_cust_id + " TEXT , "
					+ Key_Invoice_selling_date + " TEXT , "
					+ Key_Invoice_amount + " REAL , "
					+ Key_Invoice_amount_including_VAT + " REAL);"
					);*/
			db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_INVOICE
					+ " (" + Key_Invoice_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ Key_Invoice_invoice_id + " TEXT , " 
					+ Key_Invoice_rep_id + " TEXT , " 
					+ Key_Invoice_cust_id + " TEXT , "
					+ Key_Invoice_discount + " REAL , "
					+ Key_Invoice_pre_dicount_amount + " REAL, "
					+ Key_Invoice_post_dicount_amount + " REAL, "
					+ Key_Invoice_paid_cost + " TEXT, "
					+ Key_Invoice_amount_to_be_paid + " REAL, "
					+ Key_Invoice_selling_date + " TEXT , "
					+ Key_Invoice_due_date + " TEXT, "
					+ Key_Invoice_paid_date + " TEXT);"
					
					);

			
			//create returned_items
			db.execSQL("CREATE TABLE IF NOT EXISTS "
					+ DATABASE_TABLE_RETURNED_ITEMS
					+ " ("
					+ Key_returned_items_id + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
					+ Key_returned_items_del_id + " TEXT  , " 
					+ Key_returned_items_rep_id + " TEXT  , " 
					+ Key_returned_items_item_name + " TEXT , "
					+ Key_returned_items_quantity + " INTEGER  , "
					+ Key_returned_items_date + " TEXT  , "
					+ Key_returned_items_issue + " TEXT);");
			
			
			//create fake cart table
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_F_INVOICE + " ("+
				    Key_fInvoice_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					Key_fInvoice_name+ " TEXT , " +
				    Key_fInvoice_qty+ " INTEGER , "+
					Key_fInvoice_unit_price+ " TEXT  ); " 
					);
			
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_STORLOC + " ("+
				    Key_loc_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					Key_loc_rid+ " TEXT , " +
				    Key_loc_lat+ " REAL , "+
				    Key_loc_lan+ " REAL , " +
				    Key_loc_date+ " TEXT , " +
					Key_loc_time+ " TEXT  ); " 
					);

			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_GETLOC + " ("+
				    Key_loc_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					Key_loc_rid+ " TEXT , " +
				    Key_loc_lat+ " REAL , "+
				    Key_loc_lan+ " REAL , " +
				    Key_loc_date+ " TEXT , " +
					Key_loc_time+ " TEXT  ); " 
					);
			
			db.execSQL("CREATE TABLE IF NOT EXISTS "+ DATABASE_TABLE_TRACKREP + " ("+
				    Key_loc_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
					Key_loc_rid+ " TEXT ); " 
					);
			
			//Rosheen
			// create lowstock table
			db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_LOWSTOCK
					+ " (" + Key_lowstock_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ Key_lowstock_itemname + " TEXT , " 
					+ Key_lowstock_del_id + " TEXT , " 
					+ Key_lowstock_remaining_qty + " INTEGER , "
					+ Key_lowstock_received_qty + " INTEGER , "
					+ Key_lowstock_req_date + " TEXT , "
					+ Key_lowstock_received_date + " TEXT);"
					);
			
			
			//create dealer_payments
		    db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE_DEALER_PAYMENTS
					+ " (" + Key_delS_payment_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ Key_delS_payment_rep_id + " TEXT , " 
					+ Key_delS_payment_assign_date + " TEXT , " 
					+ Key_delS_payment_total_cost + " REAL , "
					+ Key_delS_payment_status + " TEXT , "
					+ Key_delS_payment_target_date + " TEXT , "
					+ Key_delS_payment_received_amount + " REAL , "
					+ Key_delS_payment_remaining_amount + " REAL , "
					+ Key_delS_payment_received_final_date + " TEXT);"
					);
		    
		    
		  //create task
			db.execSQL("CREATE TABLE IF NOT EXISTS " 
					+ DATABASE_TABLE_TASKS
					+ " ( "
					+ KEY_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ KEY_TASK_REP_ID + " TEXT, "
					+ KEY_TASKNAME + " TEXT, " 
					+ KEY_DATE + " TEXT)");
			
			
			db.execSQL( "CREATE TABLE IF NOT EXISTS " 
					+ DATABASE_TABLE_TASKS_LIST 
					+ " ( "
					+ KEY_TASK_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ KEY_TASK_LIST_REP_ID + " TEXT, " 
					+ KEY_TASK_LIST_TASKNAME + " TEXT)");
		    
			
			//????????????????????????????????????????????????????????????????????????????????????????????????
			
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_CUSTOMER);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_REP);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_DELEAR);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_REP_STOCK);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_DEALER_STOCK);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_ICART);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_IInvoice);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_DEALER_STOCK);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_MAP);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_STORLOC);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_GETLOC);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_TRACKREP);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_RETURNED_ITEMS);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_LOWSTOCK);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_DEALER_PAYMENTS);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_INVOICE);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_CART);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TASKS);
			onCreate(db);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TASKS_LIST);
			// Create tables again
			onCreate(db);
			
			
			
		}
		
	}
	public DBCreater(Context c){
		ourContext = c;
	}
	//make the connection to open db for writing purpose
		public DBCreater open() throws SQLException {
			ourHelper= new DbHelper(ourContext);
			ourDatabase= ourHelper.getWritableDatabase();
			return this;
		}
		
		//make the connection to open db for reding purpose
		public DBCreater openforread() throws SQLException {
			ourHelper= new DbHelper(ourContext);
			ourDatabase= ourHelper.getReadableDatabase();
			return this;
		}
		
		//close the connection
		public void close() {
			ourHelper.close();
		}
		public int countrep(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_REP;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countdel(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_DELEAR;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int Mapid(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_MAP;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countrepstock(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_REP_STOCK;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countdelStock(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_DEALER_STOCK;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countInvoice(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_IInvoice;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countCart(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_ICART;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		public int countRanCart(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_CART;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countStorloc(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_STORLOC;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countTrackloc(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_TRACKREP;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		public int countGetloc(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_GETLOC;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		

		public int countRanInvo(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_INVOICE;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		
		
		public int countCustomers(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_CUSTOMER;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		//randi
	    public int countReturnedItems(){
					String countQuery = "SELECT  * FROM " + DATABASE_TABLE_RETURNED_ITEMS;
				    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
				    int count=cursor.getCount();
				    return count ;
				}
	    
	    
	    public int countTasksList(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_TASKS_LIST;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		public int countTasks(){
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_TASKS;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}		
		
		/*errrr
		public long createCusEntry(String scustemername, String sshopname, String slocation,String semail, String sphone,String saddress,String srep,String sloger) {
			// TODO Auto-generated method stub
			ContentValues cv = new ContentValues();
			String countQuery = "SELECT  * FROM " + DATABASE_TABLE_CUSTOMER;
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    count++;
		    String cus="Cus"+count;
		    cv.put(Key_customer_Id, cus);
			cv.put(Key_customer_Name, scustemername);
			cv.put(Key_customer_Shop, sshopname);
			cv.put(Key_customer_Location, slocation);
			cv.put(Key_customer_Phon, sphone);
			cv.put(Key_customer_Email, semail);
			cv.put(Key_customer_Address, saddress);
			cv.put(Key_customer_state, "YES");
			cv.put(Key_customer_Rep, srep);
			cv.put(Key_customer_Delear, sloger);
			return ourDatabase.insert(DATABASE_TABLE_CUSTOMER, null, cv);
			
		}
		*/
		public long createRepentry(String username,String pwd,String name,String del,String rid,String phon,String Email,String Address){
			ContentValues cv = new ContentValues();
			cv.put(Key_rep_RId,rid);
			cv.put(Key_rep_Username,username);
			cv.put(Key_rep_Password,pwd);
			cv.put(Key_rep_Name,name);
			cv.put(Key_rep_Phon, phon);
			cv.put(Key_rep_Email, Email);
			cv.put(Key_rep_Address, Address);
			cv.put(Key_rep_state, "YES");
			cv.put(Key_rep_Delear,del);
			return ourDatabase.insert(DATABASE_TABLE_REP, null, cv);
			
		}
		
		public long Putmap(int id,String cusid,String cusshop,double lan,double lat){
			ContentValues cv = new ContentValues();
			cv.put(Key_Map_id,id);
			cv.put(Key_Map_cusid,cusid);
			cv.put(Key_Map_cusShop,cusshop);
			cv.put(Key_Map_lan,lan);
			cv.put(Key_Map_lat, lat);
			return ourDatabase.insert(DATABASE_TABLE_MAP, null, cv);
			
		}
		public long PutStorloc(String repid,double lan,double lat,String date,String time){
			ContentValues cv = new ContentValues();
			cv.put(Key_loc_rid,repid);
			cv.put(Key_loc_lan,lan);
			cv.put(Key_loc_lat,lat);
			cv.put(Key_loc_date,date);
			cv.put(Key_loc_time, time);
			return ourDatabase.insert(DATABASE_TABLE_STORLOC, null, cv);
			
		}
		public long getloc(String repid,double lan,double lat,String date,String time){
			ContentValues cv = new ContentValues();
			cv.put(Key_loc_rid,repid);
			cv.put(Key_loc_lan,lan);
			cv.put(Key_loc_lat,lat);
			cv.put(Key_loc_date,date);
			cv.put(Key_loc_time, time);
			return ourDatabase.insert(DATABASE_TABLE_GETLOC, null, cv);
			
		}
		
		public long trackrep(String repid){
			ContentValues cv = new ContentValues();
			cv.put(Key_loc_rid,repid);
			return ourDatabase.insert(DATABASE_TABLE_TRACKREP, null, cv);
			
		}
		public void cleantrackrep() {
			
			SQLiteDatabase db = ourHelper.getWritableDatabase();
			//db.rawQuery(countQuery, null);
			db.delete(DATABASE_TABLE_TRACKREP, null, null);
		}
		public void cleanstorloc() {
			
			SQLiteDatabase db = ourHelper.getWritableDatabase();
			//db.rawQuery(countQuery, null);
			db.delete(DATABASE_TABLE_STORLOC, null, null);
		}
			


		public long createDeler(String id,String username,String pass,String name,String phon,String Email,String Address,String cmp){
			ContentValues cv = new ContentValues();
			cv.put(Key_delear_DId,id);
			cv.put(Key_delear_Username,username);
			cv.put(Key_delear_Password,pass);
			cv.put(Key_delear_Name,name);
			cv.put(Key_delear_Phon, phon);
			cv.put(Key_delear_Email, Email);
			cv.put(Key_delear_Address, Address);
			cv.put(Key_delear_state, "YES");
			cv.put(Key_delear_Company,cmp);
			return ourDatabase.insert(DATABASE_TABLE_DELEAR, null, cv);
		}
		
		
		public long createEntry(String scustemername, String sshopname, String slocation,String sphone, String semail,String saddress,String srep,String sloger,String sender) {
			// TODO Auto-generated method stub
			ContentValues cv = new ContentValues();
			String countQuery = "select _id from Customer where cus_id like '%"+sender+"%'";
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    Log.e("sending string",sender);
		    int count=cursor.getCount();
		    Log.e("early count",""+count);
		    count++;
		    Log.e("now count",""+count);
		    String cus=srep+"Cus"+count;
		    Log.e("id",""+cus);
		    cv.put(Key_customer_CId, cus);
			cv.put(Key_customer_Name, scustemername);
			cv.put(Key_customer_Shop, sshopname);
			cv.put(Key_customer_Location, slocation);
			cv.put(Key_customer_Phon, sphone);
			cv.put(Key_customer_Email, semail);
			cv.put(Key_customer_Address, saddress);
			cv.put(Key_customer_state, "YES");
			cv.put(Key_customer_Rep, srep);
			cv.put(Key_customer_Delear, sloger);
			return ourDatabase.insert(DATABASE_TABLE_CUSTOMER, null, cv);
			
		}
		
		
		//*****************************************************************************************************************
	/*	public long createrepstock(String rep,String del,String item,int assignqty,int avblqty,String assdate,String dudate,String paddate,double unt_price){
			ContentValues cv = new ContentValues();
			cv.put(Key_repS_rep_id,rep);
			cv.put(Key_repS_del_id,del);
			cv.put(Key_repS_item_name,item);
			cv.put(Key_repS_assigned_qty,assignqty);
			cv.put(Key_repS_available_qty,avblqty);
			cv.put(Key_repS_assigned_date,assdate);
			cv.put(Key_repS_due_date,dudate);
			cv.put(Key_repS_paid_date,paddate);
			cv.put(Key_repS_unit_price, unt_price);
			return ourDatabase.insert(DATABASE_TABLE_REP_STOCK, null, cv);
		}
		
		*/
		
		public long createrepstock(String rep,String del,String item,int assignqty,int avblqty,String assdate,String dudate,String paddate,double unt_price){
			ContentValues cv = new ContentValues();
			cv.put(Key_repS_rep_id,rep);
			cv.put(Key_repS_del_id,del);
			cv.put(Key_repS_item_name,item);
			cv.put(Key_repS_assigned_qty,assignqty);
			cv.put(Key_repS_available_qty,avblqty);
			cv.put(Key_repS_assigned_date,assdate);
			cv.put(Key_repS_due_date,dudate);
			cv.put(Key_repS_paid_date,paddate);
			cv.put(Key_repS_unit_price, unt_price);
			return ourDatabase.insert(DATABASE_TABLE_REP_STOCK, null, cv);
		}
		
		
		public long createdelstock(String del,String delrep,String itemname,Double price,Integer pqty,Integer aqty,String purdate,String dudate,String paddate,Integer delaqty,Integer updatedaqty){
			ContentValues cv = new ContentValues();
			cv.put(Key_delS_del_id,del);
			cv.put(Key_delS_rep_id,delrep);
			cv.put(Key_delS_del_item_name,itemname);
			cv.put(Key_delS_unit_price,price);
			cv.put(Key_delS_purchased_qty,pqty);
			cv.put(Key_delS_available_qty,aqty);
			cv.put(Key_delS_purchased_date,purdate);
			cv.put(Key_delS_due_date,dudate);
			cv.put(Key_delS_paid_date,paddate);
			cv.put(Key_deltorepS_assigned_qty,delaqty);
			cv.put(Key_delS_updated_assigned_qty,updatedaqty);
			return ourDatabase.insert(DATABASE_TABLE_DEALER_STOCK, null, cv);
		}
		
		public long createIInvoice(String invoicId,String cusId,String repId,String delId,double discount,double totle,double totalAftDis,double paid,double topay,String issuDate,String paidDate,String dueDate){
			ContentValues cv = new ContentValues();
			cv.put(Key_ICart_tab_in_id,invoicId);
			cv.put(Key_ICart_tab_cus_id,cusId);
			cv.put(Key_ICart_tab_rep_id,repId);
			cv.put(Key_ICart_tab_del_id,delId);
			cv.put(Key_ICart_tab_dicount,discount);
			cv.put(Key_ICart_tab_total_price,totle);
			cv.put(Key_ICart_tab_total_priceAftDis,totalAftDis);
			cv.put(Key_ICart_tab_paid_cost,paid);
			cv.put(Key_ICart_tab_moreToPay,topay);
			cv.put(Key_ICart_tab_issued_date,issuDate);
			cv.put(Key_ICart_tab_paid_date,paidDate);
			cv.put(Key_ICart_tab_due_date,dueDate);
			return ourDatabase.insert(DATABASE_TABLE_IInvoice, null, cv);
		}
		
		public long createICart(String id,String invoicId,String itemId,String itemName,double cost,int qty){
			ContentValues cv = new ContentValues();
			cv.put(Key_IInvoic_tab_id,id);
			cv.put(Key_IInvoic_invoice_id,invoicId);
			cv.put(Key_IInvoic_item_id,itemId);
			cv.put(Key_IInvoic_item_name,itemName);
			cv.put(Key_IInvoic_cost,cost);
			cv.put(Key_IInvoic_quentity,qty);
			
			return ourDatabase.insert(DATABASE_TABLE_ICART, null, cv);
		}
		
	/*	public long createCart(String rid, String cusid, String invoId,String itemName, int qty,
				double unit_price, double tprice, String idate, String ddate,
				String pdate) {
			ContentValues cv = new ContentValues();
			// cv.put(Key_Cart_cart_id,crtid);
			cv.put(Key_Cart_rep_id, rid);
			cv.put(Key_Cart_cus_id, cusid);
			cv.put(Key_Cart_invoice_id, invoId);
			cv.put(Key_Cart_item_name, itemName);
			cv.put(Key_Cart_sold_quantity, qty);
			cv.put(Key_Cart_unit_price, unit_price);
			cv.put(Key_Cart_total_price, tprice);
			cv.put(Key_Cart_issued_date, idate);
			cv.put(Key_Cart_due_date, ddate);
			cv.put(Key_Cart_paid_date, pdate);
			return ourDatabase.insert(DATABASE_TABLE_CART, null, cv);
		}*/
		public long createCart(String rid, String cusid, String invoId,String itemName, int qty,
				double unit_price, double tprice /*String idate*/ /*String ddate*/
				/*String pdate*/) {
			ContentValues cv = new ContentValues();
			// cv.put(Key_Cart_cart_id,crtid);
			cv.put(Key_Cart_rep_id, rid);
			cv.put(Key_Cart_cus_id, cusid);
			cv.put(Key_Cart_invoice_id, invoId);
			cv.put(Key_Cart_item_name, itemName);
			cv.put(Key_Cart_sold_quantity, qty);
			cv.put(Key_Cart_unit_price, unit_price);
			cv.put(Key_Cart_total_price, tprice);
			//cv.put(Key_Cart_issued_date, idate);
			//cv.put(Key_Cart_due_date, ddate);
			//cv.put(Key_Cart_paid_date, pdate);
			return ourDatabase.insert(DATABASE_TABLE_CART, null, cv);
		}

		
		
	//	public long createInvoiceEntry(String invoice_id,String repid,String cusid,String sellingdate,double invoiceamount,double amountwithVAT){
	//		ContentValues cv = new ContentValues();
			//String countQuery = "SELECT  * FROM " + DATABASE_TABLE_INVOICE;
			//Cursor cursor = ourDatabase.rawQuery(countQuery, null);
			//int count = cursor.getCount();
			//count++;
			//String invoice="Invoice" +count;
			
		/*	cv.put(Key_Invoice_invoice_id, invoice_id);
			cv.put(Key_Invoice_rep_id, repid);
			cv.put(Key_Invoice_cust_id,cusid);
			cv.put(Key_Invoice_selling_date, sellingdate);
			cv.put(Key_Invoice_amount, invoiceamount);
			cv.put(Key_Invoice_amount_including_VAT, amountwithVAT);
			return ourDatabase.insert(DATABASE_TABLE_INVOICE, null, cv);*/
			
	//	}
		public long createInvoiceEntry(String invoice_id,String repid,String cusid,double disount,double pre_dis_amount,double post_dis_amount,double paid_cost,double amount_to_be_paid,String sellingdate, String due_date,String paid_date){
			ContentValues cv = new ContentValues();
			//String countQuery = "SELECT  * FROM " + DATABASE_TABLE_INVOICE;
			//Cursor cursor = ourDatabase.rawQuery(countQuery, null);
			//int count = cursor.getCount();
			//count++;
			//String invoice="Invoice" +count;
			
			cv.put(Key_Invoice_invoice_id, invoice_id);
			cv.put(Key_Invoice_rep_id, repid);
			cv.put(Key_Invoice_cust_id,cusid);
			cv.put(Key_Invoice_discount, disount);
			cv.put(Key_Invoice_pre_dicount_amount, pre_dis_amount);
			cv.put(Key_Invoice_post_dicount_amount, post_dis_amount);
			cv.put(Key_Invoice_paid_cost, paid_cost);
			cv.put(Key_Invoice_amount_to_be_paid, amount_to_be_paid);
			cv.put(Key_Invoice_selling_date, sellingdate);
			cv.put(Key_Invoice_due_date, due_date);
			cv.put(Key_Invoice_paid_date, paid_date);
			//cv.put(Key_Invoice_amount, invoiceamount);
			//cv.put(Key_Invoice_amount_including_VAT, amountwithVAT);
			return ourDatabase.insert(DATABASE_TABLE_INVOICE, null, cv);
			
		}

		
		public long createFakeInvoice(String finvoicename,int qty,String unitprice){
			ContentValues cv = new ContentValues();
			cv.put(Key_fInvoice_name,finvoicename);
			cv.put(Key_fInvoice_qty,qty);
			cv.put(Key_fInvoice_unit_price,unitprice);
			
			return ourDatabase.insert(DATABASE_TABLE_F_INVOICE, null, cv);
		}
		
		
		public long createReturnedItems(String del_id,String rep_id,String item_name, int quantity, String date, String issue){
			ContentValues cv = new ContentValues();
			cv.put(Key_returned_items_del_id,del_id);
			cv.put(Key_returned_items_rep_id,rep_id);
			cv.put(Key_returned_items_item_name, item_name);
			cv.put(Key_returned_items_quantity,quantity);
			cv.put(Key_returned_items_date, date);
			cv.put(Key_returned_items_issue, issue);
			
			return ourDatabase.insert(DATABASE_TABLE_RETURNED_ITEMS, null, cv);
		}
		
		//Rosh
		
		
		public long createLowstockEntry(String lowstock_item_name,String delid,Integer remainingqty,Integer receivedqty,String reqdate,String receiveddate){
			ContentValues cv = new ContentValues();
			cv.put(Key_lowstock_itemname, lowstock_item_name);
			cv.put(Key_lowstock_del_id, delid);
			cv.put(Key_lowstock_remaining_qty, remainingqty);
			cv.put(Key_lowstock_received_qty, receivedqty);
			cv.put(Key_lowstock_req_date, reqdate);
			cv.put(Key_lowstock_received_date, receiveddate);
			return ourDatabase.insert(DATABASE_TABLE_LOWSTOCK, null, cv);
			
		}

		public long createPayment(String prid, String adate, double totcost,String dstatus,String tdate,
				double recvdamt, double remamt, String rfdate) {
			ContentValues cv = new ContentValues();
			cv.put(Key_delS_payment_rep_id, prid);
			cv.put(Key_delS_payment_assign_date, adate);
			cv.put(Key_delS_payment_total_cost, totcost);
			cv.put(Key_delS_payment_status, dstatus);
			cv.put(Key_delS_payment_target_date, tdate);
			cv.put(Key_delS_payment_received_amount, recvdamt);
			cv.put(Key_delS_payment_remaining_amount, remamt);
			cv.put(Key_delS_payment_received_final_date, rfdate);
			return ourDatabase.insert(DATABASE_TABLE_DEALER_PAYMENTS, null, cv);
		}
		
		// Adding new task
					public long addTask(String task,String rep,String date) {
						//SQLiteDatabase db = this.getWritableDatabase();

						ContentValues values = new ContentValues();
						values.put(KEY_TASKNAME,task ); // task name
						values.put(KEY_TASK_REP_ID, rep);
						values.put(KEY_DATE, date);

						// Inserting Row
						//db.insert(TABLE_TASKS, null, values);
						//db.close(); // Closing database connection
						return ourDatabase.insert(DATABASE_TABLE_TASKS, null, values);
					}
					
					public long addTaskList(String task,String rep) {
						//SQLiteDatabase db = ourHelper.getWritableDatabase();

						ContentValues values = new ContentValues();
						values.put(KEY_TASK_LIST_TASKNAME,task ); // task name
						values.put(KEY_TASK_LIST_REP_ID, rep);
						
						// Inserting Row
						//db.insert(TABLE_TASKS_LIST, null, values);
						//db.close(); // Closing database connection
						return ourDatabase.insert(DATABASE_TABLE_TASKS_LIST, null, values);
					}
		
//*************************************************************************************************************************
		
		public Cursor getunamebypwd(String pwd)throws SQLException{
			Cursor mCursor = null;
			mCursor =ourDatabase.query(true,DATABASE_TABLE_DELEAR,new String[]{ Key_delear_Username }, Key_delear_Password+ "=?",
					new String[]{pwd},null, null, null, null);
			if(mCursor != null  ){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor getunamebypwdrep(String pwd)throws SQLException{
			Cursor mCursor = null;
			mCursor =ourDatabase.query(true,DATABASE_TABLE_REP,new String[]{ Key_rep_Username }, Key_rep_Password+ "=?",
					new String[]{pwd},null, null, null, null);
			if(mCursor != null  ){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public String getuid(String pwd)throws SQLException{
			Cursor mCursor = null;
			mCursor =ourDatabase.query(true,DATABASE_TABLE_DELEAR,new String[]{ Key_delear_DId }, Key_delear_Password+ "=?",
					new String[]{pwd},null, null, null, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor.getString(0);
		}
		
		public Cursor fetchallcustomercompany(String delear) {
			
			 String selectQuery = "select  _id,customer_shop,customer_name from Customer where customer_delear= '"+delear+"'" ;
		//	 String selectQuery = "select  _id,customer_shop,customer_location,customer_phon,customer_email,customer_address from Customer where customer_delear= '"+delear+"'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor fetchallrep(String delear) {
			
			 String selectQuery = "select  _id,rep_name from REP where rep_delear= '"+delear+"'" ;
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor fetchallcustomercompanyfordelear(String rep) {
			
			 String selectQuery = "select  _id,customer_shop,customer_name from Customer where customer_rep= '"+rep+"'" ;
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor getRephistorylocationMap(String date ,String rid){
			 String selectQuery = "select rid, lat,lan,time,date from getloc where date ='"+date+"' AND rid = '"+rid+"' " ;
			 		//" where rid='"+rid+"'" ;
			// String selectQuery = "select rid, lat,lan,time from getloc " ;
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Cursor getRepCurrentlocationMap(String date ){
			 String selectQuery = "select lat,lan, Max(_id),rid,time from getloc where date='"+date+"' group by rid" ;
			 		//" where rid='"+rid+"'" ;
			// String selectQuery = "select rid, lat,lan,time from getloc " ;
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Cursor fetchallcustomerdata(String delear) {
			
			 String selectQuery = "select  cus_id,customer_location,customer_phon,customer_email,customer_address,customer_name from Customer where customer_shop= '"+delear+"'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
	
				public Cursor fetchallrepdata(String delId) {
					String selectQuery = "select  _id,rep_uname,rep_name from Rep where rep_delear='"+delId+"' ";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
		
		public Cursor Delearemail(String delear) {
			
			 String selectQuery = "select  del_email from DELEAR where del_id = '"+delear+"'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor Repemail(String delear) {
			
			 String selectQuery = "select  rep_email from REP where rep_id = '"+delear+"'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public String getuidrep(String pwd)throws SQLException{
			Cursor mCursor = null;
			mCursor =ourDatabase.query(true,DATABASE_TABLE_REP,new String[]{ Key_rep_RId }, Key_rep_Password+ "=?",
					new String[]{pwd},null, null, null, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor.getString(0);
		}
		public List<String> getAllLabels(String s1) {
			// TODO Auto-generated method stub
			List<String> labels = new ArrayList<String>();
	        // Select All Query
	        String selectQuery = "SELECT  * FROM  Rep where rep_delear = '"+s1+"'" ;
	      
	        SQLiteDatabase db = ourHelper.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	      
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                labels.add(cursor.getString(1));
	            } while (cursor.moveToNext());
	        }
	         
	        // closing connection
	        cursor.close();
	        db.close();
	         
	        // returning lables
	        return labels;
		}
		public List<String> getshop(String s1) {
			// TODO Auto-generated method stub
			List<String> labels = new ArrayList<String>();
	        
	        // Select All Query
	        String selectQuery = "SELECT  * FROM  Customer where customer_delear = '"+s1+"'" ;
	      
	        SQLiteDatabase db = ourHelper.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	      
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                labels.add(cursor.getString(3));
	            } while (cursor.moveToNext());
	        }
	         
	        // closing connection
	        cursor.close();
	        db.close();
	         
	        // returning lables
	        return labels;
		}
		public List<String> getshoprep(String s1) {
			// TODO Auto-generated method stub
			List<String> labels = new ArrayList<String>();
	        
	        // Select All Query
	        String selectQuery = "SELECT  * FROM  Customer where customer_rep = '"+s1+"'" ;
	      
	        SQLiteDatabase db = ourHelper.getReadableDatabase();
	        Cursor cursor = db.rawQuery(selectQuery, null);
	      
	        // looping through all rows and adding to list
	        if (cursor.moveToFirst()) {
	            do {
	                labels.add(cursor.getString(3));
	            } while (cursor.moveToNext());
	        }
	         
	        // closing connection
	        cursor.close();
	        db.close();
	         
	        // returning lables
	        return labels;
		}
		public Cursor fetchcustomerbynamelike(String inputtext,String delear) {
			// TODO Auto-generated method stub
			Cursor mycursor =null;
		       
			
			if(inputtext == null||inputtext.length()==0){
			
				String getter = "select  cus_id,_id,customer_location,customer_phon,customer_shop,customer_email,customer_address,customer_name from Customer where customer_delear= '"+delear+"'" ;
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				 mycursor = db.rawQuery(getter, null);
			}
			else{
				String getter = "select  cus_id,_id,customer_location,customer_phon,customer_shop,customer_email,customer_address,customer_name from Customer where customer_delear= '"+delear+"' AND customer_shop like '%"+inputtext+"%'";
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				 mycursor = db.rawQuery(getter, null);
			}
			if(mycursor != null){
				mycursor.moveToFirst();
			}
			return mycursor;
		}
		
		public Cursor fetchrepbynamelike(String inputtext,String delear) {
			// TODO Auto-generated method stub
			Cursor mycursor =null;
		       
			
			if(inputtext == null||inputtext.length()==0){
			
				String getter = "select  rep_id,_id,rep_phon,rep_email,rep_address,rep_name from REP where rep_delear= '"+delear+"'" ;
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				 mycursor = db.rawQuery(getter, null);
			}
			else{
				String getter = "select  rep_id,_id,rep_phon,rep_email,rep_address,rep_name from REP where rep_delear= '"+delear+"' AND rep_name like '%"+inputtext+"%'";
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				 mycursor = db.rawQuery(getter, null);
			}
			if(mycursor != null){
				mycursor.moveToFirst();
			}
			return mycursor;
		}
		
		public Cursor repfetchcustomerbynamelike(String inputtext,String delear) {
			// TODO Auto-generated method stub
			Cursor mycursor =null;
		       
			
			if(inputtext == null||inputtext.length()==0){
			
				String getter = "select  cus_id,_id,customer_location,customer_phon,customer_shop,customer_email,customer_address,customer_name from Customer where customer_rep= '"+delear+"'" ;
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				 mycursor = db.rawQuery(getter, null);
			}
			else{
				String getter = "select  cus_id,_id,customer_location,customer_phon,customer_shop,customer_email,customer_address,customer_name from Customer where customer_rep= '"+delear+"' AND customer_shop like '%"+inputtext+"%'";
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				 mycursor = db.rawQuery(getter, null);
			}
			if(mycursor != null){
				mycursor.moveToFirst();
			}
			return mycursor;
		}
		public String getCustomerDataForDELPdf(String s1) {
			// TODO Auto-generated method stub
			SQLiteDatabase db = ourHelper.getReadableDatabase();
			
			 String selectQuery = "select  cus_id,_id,customer_location,customer_phon,customer_shop,customer_email,customer_address,customer_name,customer_rep from Customer where customer_delear= '"+s1+"'" ; 
			Cursor c = ourDatabase.rawQuery(selectQuery, null);
			 String result="";
			 int iid=c.getColumnIndex(Key_customer_CId);
			 int iname=c.getColumnIndex(Key_customer_Name);
			 int iaddress=c.getColumnIndex(Key_customer_Address);
			 int icmp=c.getColumnIndex(Key_customer_Shop);
			 int icity=c.getColumnIndex(Key_customer_Location);
			 int iemail=c.getColumnIndex(Key_customer_Email);
			 int iphon=c.getColumnIndex(Key_customer_Phon);
			 int irep=c.getColumnIndex(Key_customer_Rep);
			 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				 result=result+c.getString(iid)+"*"+c.getString(icmp)+"*"+c.getString(iname)+"*"+c.getString(icity)+"*"+c.getString(iaddress)+"*"+c.getString(iemail)+"*"+c.getString(iphon)+"*"+c.getString(irep)+"*";
			 }
			 db.close();
			 return result;
		}
		public String getCustomerDataForRepPdf(String s1) {
			// TODO Auto-generated method stub
			SQLiteDatabase db = ourHelper.getReadableDatabase();
			
			 String selectQuery = "select  cus_id,_id,customer_location,customer_phon,customer_shop,customer_email,customer_address,customer_name,customer_rep from Customer where customer_rep= '"+s1+"'" ; 
			Cursor c = ourDatabase.rawQuery(selectQuery, null);
			 String result="";
			 int iid=c.getColumnIndex(Key_customer_CId);
			 int iname=c.getColumnIndex(Key_customer_Name);
			 int iaddress=c.getColumnIndex(Key_customer_Address);
			 int icmp=c.getColumnIndex(Key_customer_Shop);
			 int icity=c.getColumnIndex(Key_customer_Location);
			 int iemail=c.getColumnIndex(Key_customer_Email);
			 int iphon=c.getColumnIndex(Key_customer_Phon);
			 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				 result=result+c.getString(iid)+"*"+c.getString(icmp)+"*"+c.getString(iname)+"*"+c.getString(icity)+"*"+c.getString(iaddress)+"*"+c.getString(iemail)+"*"+c.getString(iphon)+"*";
			 }
			 db.close();
			 return result;
		}
		public String getRepDataForDELPdf(String s1) {
			// TODO Auto-generated method stub
			SQLiteDatabase db = ourHelper.getReadableDatabase();
			
			 String selectQuery = "select  rep_id,_id,rep_phon,rep_email,rep_address,rep_name from REP where rep_delear= 'Del01'"  ; 
			Cursor c = ourDatabase.rawQuery(selectQuery, null);
			 String result="";
			 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
				 result=result+c.getString(0)+"*"+c.getString(5)+"*"+c.getString(4)+"*"+c.getString(3)+"*"+c.getString(2)+"*";
			 }
			 db.close();
			 return result;
		}
		public Cursor getrepnameandtep(String rep) {
			
			 String selectQuery = "select  _id,rep_name,rep_phon from REP where rep_id= '"+rep+"'" ;
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor getcusnameandtep(String rep) {
			
			 String selectQuery = "SELECT  * FROM  Customer where customer_shop = '"+rep+"'" ;
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Cursor getlogrep() {
			
			 String selectQuery = "SELECT  * FROM trackrep";
			 SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		//////////for my charts/////////
		public int countdelstockchart(String s1){
			String countQuery = "select * from Del_Stock where delS_del_id = '"+s1+"'";
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		public Cursor chartDelMyStock(String s1) {
			
			 String selectQuery = "select * from Del_Stock where delS_del_id = '"+s1+"'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Cursor chartReploc(String s2,String s3) {
			
			 String selectQuery = "select * from getloc where rid = '"+s3 +"' AND date = '"+s2+"' group by rid,time " ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Cursor getToMap() {
			
			 String selectQuery = "Select cus_id,customer_shop,customer_location from Customer" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
	
		public int countrepproformancechart(String s1,String s2){
			String countQuery = "select * from Invoice where Invoice_rep_id='"+s1+"' AND Invoice_selling_date like '%"+s2+"%'";
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		public Cursor chartDelrepProformance(String s1,String s2) {
			
			// String selectQuery = "select * from IInovice where In_repid='"+s1+"' AND In_issu_date like '%"+s2+"%'" ; 
			String selectQuery = "select * from Invoice where Invoice_rep_id='"+s1+"' AND Invoice_selling_date like '%"+s2+"%'";
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public int countcusproformancechart(String s1){
			String countQuery = "select * from Invoice  where Invoice_cus_id='"+s1+"'";
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		public Cursor chartDelcusProformance(String s1) {
			
			// String selectQuery = "select * from IInovice where In_cusid='"+s1+"'" ;  
			String selectQuery = "select * from Invoice where Invoice_cus_id='"+s1+"'" ; 
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public int countcuscreadetschart(String s1){
			String countQuery = "select * from IInovice where In_repid='"+s1+"'";
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		public Cursor chartDelcuscredets(String s1) {
			
			 String selectQuery = "select * from IInovice where In_repid='"+s1+"'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public Cursor getmappoints() {
			
			 String selectQuery = "select * from gMap" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		public Cursor getmappoint(String s1) {
			
			 String selectQuery = "select * from gMap where map_cusid like '%"+s1+"%'" ;     
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(selectQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		public int countrepstock(String s1,String s2){
			//String countQuery = "select * from Rep_stock where repS_rep_id='"+s1+"' AND repS_assigned_date ='"+s2+"'";
			 String countQuery = "select * from Rep_stock where repS_rep_id='"+s1+"' AND repS_assigned_date ='"+s2+"'";
		    Cursor cursor = ourDatabase.rawQuery(countQuery, null);
		    int count=cursor.getCount();
		    return count ;
		}
		
		
		
		public Cursor chartrepstock(String s1,String s2) {
			String countQuery = "select * from Rep_stock where repS_rep_id='"+s1+"' AND repS_assigned_date ='"+s2+"'";
			// String countQuery = "select * from Rep_stock where repS_rep_id='"+s1+"' AND repS_assigned_date ='"+s2+"'";    
		        SQLiteDatabase db = ourHelper.getReadableDatabase();
		        Cursor mCursor = db.rawQuery(countQuery, null);
			if(mCursor != null){
				mCursor.moveToFirst();
			}
			return mCursor;
		}
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		//Rosheen
				public void delete(String table, String whereClause,
						String[] whereArgs) {
					 SQLiteDatabase db = ourHelper.getWritableDatabase();
					 
					 db.delete(table, whereClause, whereArgs);

					
				}

				public void updateDelear(String table, ContentValues cv, String whereClause,
						String[] whereArgs) {
					 SQLiteDatabase db = ourHelper.getWritableDatabase();
					 
					    //db.update(table, values, whereClause, whereArgs)
					     db.update(table, cv, whereClause, whereArgs);
					    
					
				}
				
				
				public String getDailyReport(String rep1, String date) {
					String selectQuery = "select  repS_rep_id,repS_item_name,repS_assigned_qty,repS_unit_price,repS_assigned_date from Rep_Stock where repS_rep_id='"+rep1+"' and repS_assigned_date='"+date+"'";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor c = ourDatabase.rawQuery(selectQuery, null);
					String result="";
					 int iid=c.getColumnIndex(Key_repS_rep_id);
					 int iname=c.getColumnIndex(Key_repS_item_name);
					 int iaddress=c.getColumnIndex(Key_repS_assigned_qty);
					 int icmp=c.getColumnIndex(Key_repS_unit_price);
					 int icity=c.getColumnIndex(Key_repS_assigned_date);
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(iid)+"*"+c.getString(icmp)+"*"+c.getString(iname)+"*"+c.getString(icity)+"*"+c.getString(iaddress)+"*";
					 }
					 db.close();
					 return result;
				}
				
				//Rosheen
				public List<String> getAllItems(String delearid){
			        List<String> lbl1 = new ArrayList<String>();
			        String selectQuery = "select delS_del_item_name from Del_Stock where delS_purchased_date =(select max (delS_purchased_date) from Del_Stock) and delS_del_id='"+delearid+"' " ;
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        if (cursor.moveToFirst()) {
			            do {
			                lbl1.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			         
			        cursor.close();
			        db.close();
			         
			        return lbl1;
			    }
				
				//Rosheen
				public List<String> fetchqty(String delid ,String itemName) {
					 List<String> lbl2 = new ArrayList<String>();
				        lbl2.clear();
				        // Select repid Query
				        String selectQuery = "SELECT  delS_available_qty,delS_unit_price FROM  Del_Stock where delS_del_id = '"+delid+"' and delS_del_item_name='"+itemName+"' "; 
				        		/*repS_rep_id = '"+repid+"' "*/;     
				        SQLiteDatabase db = ourHelper.getReadableDatabase();
				        Cursor cursor = db.rawQuery(selectQuery, null);
				      
				        // looping through all rows and adding to list
				        //move to first means selecting the 1st row
				        if (cursor.moveToFirst()) {
				            do {
				                lbl2.add(cursor.getString(0));
				                lbl2.add(cursor.getString(1));
				               } while (cursor.moveToNext());//to track  the coloum
				        }
				         
				        // closing connection
				        cursor.close();
				        db.close();
				         
				        // returning lables
				        return lbl2;	
				}
				
				public Cursor fetchallrepdetails(String delId) {
					String selectQuery = "select  _id,rep_uname,rep_name from Rep where rep_delear='"+delId+"' ";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				public Cursor getRelatedItems(String rep,String date) {
					String selectQuery = "select  _id,repS_rep_id,repS_item_name,repS_assigned_date,repS_assigned_qty,repS_unit_price from Rep_Stock where repS_rep_id='"+rep+"' AND repS_assigned_date='"+date+"' ";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				//Rosheen
				public List<String> getAllLabelsRep(String delid){
			        List<String> labels2 = new ArrayList<String>();
			        
			        // Select All Query
			        String selectQuery = "select distinct repS_rep_id from Rep_Stock where repS_del_id='"+delid+"'  order by repS_rep_id asc " ;
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        if (cursor.moveToFirst()) {
			            do {
			                labels2.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels2;
			    }
				
				public List<String> getAllLabelsDate(){
			        List<String> labels3 = new ArrayList<String>();
			        
			        // Select All Query
			        String selectQuery = "select distinct repS_assigned_date from Rep_Stock" ;
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        if (cursor.moveToFirst()) {
			            do {
			                labels3.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels3;
			    }
				
				public void cleanMap() {
					SQLiteDatabase db = ourHelper.getWritableDatabase();
					db.delete(DATABASE_TABLE_MAP, null, null);
				}
				
				public Cursor fetchmonthlyrecords(String repID,String startdate,String enddate) {
					String selectQuery = "select  _id,repS_item_name,repS_assigned_qty,repS_assigned_date,repS_unit_price,repS_available_qty from Rep_Stock where repS_rep_id='"+repID+"' and repS_assigned_date between '"+startdate+"' and '"+enddate+"'";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				public String getMonthlyItemReport(String rep, String startdate, String enddate) {
					String selectQuery = "select  repS_del_id,repS_item_name,repS_assigned_qty,repS_unit_price,repS_assigned_date from Rep_Stock where repS_rep_id='"+rep+"'and repS_assigned_date between '"+startdate+"' and '"+enddate+"'";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor c = ourDatabase.rawQuery(selectQuery, null);
					String result="";
					 int id=c.getColumnIndex(Key_repS_del_id);
					 int name=c.getColumnIndex(Key_repS_item_name);
					 int qty=c.getColumnIndex(Key_repS_assigned_qty);
					 int cost=c.getColumnIndex(Key_repS_unit_price);
					 int date=c.getColumnIndex(Key_repS_assigned_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(id)+"*"+c.getString(name)+"*"+c.getString(qty)+"*"+c.getString(cost)+"*"+c.getString(date)+"*";
					 }
					 db.close();
					 return result;
				}
				
				
				//Randi
				public List<String> getAllItemsndQty(String repid ,String itemName,String date) {
					 List<String> labels = new ArrayList<String>();
				        labels.clear();
				        // Select repid Query
				        String selectQuery = "SELECT  repS_item_name,repS_available_qty,repS_unit_price FROM  Rep_Stock where repS_rep_id = '"+repid+"' and repS_item_name='"+itemName+"' and repS_assigned_date='"+date+"' "; 
				        		/*repS_rep_id = '"+repid+"' "*/;     
				        SQLiteDatabase db = ourHelper.getReadableDatabase();
				        Cursor cursor = db.rawQuery(selectQuery, null);
				      
				        // looping through all rows and adding to list
				        //move to first means selecting the 1st row
				        if (cursor.moveToFirst()) {
				            do {
				                labels.add(cursor.getString(0));
				                labels.add(cursor.getString(1));
				                labels.add(cursor.getString(2));
				            } while (cursor.moveToNext());//to track  the coloum
				        }
				         
				        // closing connection
				        cursor.close();
				        db.close();
				         
				        // returning lables
				        return labels;	
				}


				
				// Randi
				public Cursor fetchrelatedinfo(String text, String today) {

					String query = "select _id,repS_item_name,repS_assigned_qty from Rep_Stock where repS_rep_id='"
							+ text + "' and repS_assigned_date='" + today + "'  ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mcurs = db.rawQuery(query, null);

					if (mcurs != null) {
						mcurs.moveToFirst();

					}
					return mcurs;

				}

				
				

				//Randi
				public void update(String table, ContentValues cv, String whereClause,
						String[] whereArgs) {
					 SQLiteDatabase db = ourHelper.getWritableDatabase();
					 
					    //db.update(table, values, whereClause, whereArgs)
					     db.update(table, cv, whereClause, whereArgs);
					    
					
				}

				//Randi
				
				public String getNewInvoice(){
					
				String cQuery = "SELECT * FROM Invoice"; 
				Cursor cursor = ourDatabase.rawQuery(cQuery, null);
				int count = cursor.getCount();
				count++;
				//int count=Integer.parseInt(cursor.getString(1));
				//count++;
				String invoice="Invoice" +count;
				return invoice;
				
				}
			
				

				// Randi
				public List<String> getAllLabel(String repid,String date) {
					List<String> labels = new ArrayList<String>();

					// Select repid Query
					String selectQuery = "SELECT  repS_item_name FROM  Rep_Stock where repS_rep_id = '"
							+ repid + "' and repS_assigned_date='"+date+"' ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor cursor = db.rawQuery(selectQuery, null);

					// looping through all rows and adding to list
					// move to first means selecting the 1st row
					if (cursor.moveToFirst()) {
						do {
							labels.add(cursor.getString(0));
						} while (cursor.moveToNext());// to track the row
					}

					// closing connection
					cursor.close();
					db.close();

					// returning lables
					return labels;
				}

				//Randi
				public List<String> getAllItemsndQty(String repid ,String itemName) {
					 List<String> labels = new ArrayList<String>();
				        labels.clear();
				        // Select repid Query
				        String selectQuery = "SELECT  repS_item_name,repS_available_qty,repS_unit_price FROM  Rep_Stock where repS_rep_id = '"+repid+"' and repS_item_name='"+itemName+"' "; 
				        		/*repS_rep_id = '"+repid+"' "*/;     
				        SQLiteDatabase db = ourHelper.getReadableDatabase();
				        Cursor cursor = db.rawQuery(selectQuery, null);
				      
				        // looping through all rows and adding to list
				        //move to first means selecting the 1st row
				        if (cursor.moveToFirst()) {
				            do {
				                labels.add(cursor.getString(0));
				                labels.add(cursor.getString(1));
				                labels.add(cursor.getString(2));
				            } while (cursor.moveToNext());//to track  the coloum
				        }
				         
				        // closing connection
				        cursor.close();
				        db.close();
				         
				        // returning lables
				        return labels;	
				}

				
				// Randi
				public Cursor disInvoice(String repid, String cusid, String invoice) {

					String query = "select _id,Cart_item_name,Cart_sold_qty,Cart_unit_price,Cart_total_price,Cart_invoice_id from Cart where Cart_rep_id='"
							+ repid
							+ "' AND Cart_cus_id='"
							+ cusid
							+ "' AND Cart_invoice_id='" + invoice + "' "; 

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mcurs = db.rawQuery(query, null);

					if (mcurs != null) {
						mcurs.moveToFirst();

					}
					return mcurs;

				}
				
				//Randi
				//Randi
				public List<String>  getAvailableqty(String itemName,String repid) {
					 List<String> labels = new ArrayList<String>();
					 
					String query="select repS_available_qty from Rep_Stock where repS_item_name='"+itemName+"' AND repS_rep_id='"+repid+"' ";
					SQLiteDatabase db=ourHelper.getReadableDatabase();
					Cursor mcurs=db.rawQuery(query, null);
					
					
					 if (mcurs.moveToFirst()) {
				            do {
				                labels.add(mcurs.getString(0));
				            } while (mcurs.moveToNext());
				        }
				         
				        // closing connection
				        mcurs.close();
				        db.close();
				         
				    return labels;


				}
				
				

				//Randi
				
				public Cursor fetchcusNames(String text) {
					String query="select _id,customer_name,cus_id from Customer where customer_rep='"+text+"' ";
					
					SQLiteDatabase db=ourHelper.getReadableDatabase();
					Cursor mcurs=db.rawQuery(query, null);
					
					if(mcurs!=null){
						mcurs.moveToFirst();
						
					}
					return mcurs;
					
				}				
				
				//Randi
				public String getInvoiceDetails(String invoiceId) {
					String selectQuery = "select  Cart_item_name,Cart_sold_qty,Cart_unit_price,Cart_total_price from Cart where Cart_invoice_id='"+invoiceId+"' ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor c = ourDatabase.rawQuery(selectQuery, null);
					String result="";
					 int id=c.getColumnIndex(Key_Cart_item_name);
					 int name=c.getColumnIndex(Key_Cart_sold_quantity);
					 int qty=c.getColumnIndex(Key_Cart_unit_price);
					 int cost=c.getColumnIndex(Key_Cart_total_price);
					 //int date=c.getColumnIndex(Key_repS_assigned_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(id)+"*"+c.getString(name)+"*"+c.getString(qty)+"*"+c.getString(cost)+"*";
					 }
					 db.close();
					 return result;
				}
			
				

				//Randi
				public List<String> getAllLabelRstock(String repid){
			        List<String> labels = new ArrayList<String>();
			        
			        // Select repid Query
			        String selectQuery = "SELECT  repS_item_name FROM  Rep_Stock where repS_rep_id = '"+repid+"' ";
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        //move to first means selecting the 1st row
			        if (cursor.moveToFirst()) {
			            do {
			                labels.add(cursor.getString(0));
			            } while (cursor.moveToNext());//to track  the coloum
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels;
			    }
					
				

				//randi
				public Cursor fetchAvailqty(String repid, String today) {

					String query = "select _id,repS_item_name,repS_assigned_qty,repS_available_qty from Rep_Stock where repS_rep_id='"
							+ repid+ "' and repS_assigned_date='" + today + "'  ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mcurs = db.rawQuery(query, null);

					if (mcurs != null) {
						mcurs.moveToFirst();

					}
					return mcurs;

				}
				
				//randi
				public List<String> viewInvoceOld(String repid ,String cusid,String sellingDate) {
					 List<String> labels = new ArrayList<String>();
				        labels.clear();
				        // Select repid Query
				        String selectQuery = "select _id,Invoice_invoice_id,Invoice_rep_id,Invoice_cus_id,Invoice_selling_date,Invoice_post_discount_amount from Invoice where Invoice_rep_id='"+repid+"' AND Invoice_cus_id='"+cusid+"' AND Invoice_selling_date='"+sellingDate+"' "; 
				        		/*repS_rep_id = '"+repid+"' "*/;     
				        SQLiteDatabase db = ourHelper.getReadableDatabase();
				        Cursor cursor = db.rawQuery(selectQuery, null);
				      
				        // looping through all rows and adding to list
				        //move to first means selecting the 1st row
				        if (cursor.moveToFirst()) {
				            do {
				                labels.add(cursor.getString(0));
				                labels.add(cursor.getString(1));
				                labels.add(cursor.getString(2));
				                labels.add(cursor.getString(3));
				                labels.add(cursor.getString(4));
				                labels.add(cursor.getString(5));
				            } while (cursor.moveToNext());//to track  the coloum
				        }
				         
				        // closing connection
				        cursor.close();
				        db.close();
				         
				        // returning lables
				        return labels;	
				}


				
				
				
				//randi
				public List<String> getInvoDetails(String selectedInvoiceId) {
					List<String> labels = new ArrayList<String>();
			        labels.clear();
			        // Select repid Query
			        String selectQuery = "select Invoice_selling_date,Invoice_amount_to_be_paid,Invoice_paid_cost from Invoice where Invoice_invoice_id='"+selectedInvoiceId+"'  "; 
			        		/*repS_rep_id = '"+repid+"' "*/;     
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        //move to first means selecting the 1st row
			        if (cursor.moveToFirst()) {
			            do {
			                //labels.add(cursor.getString(0));
			                labels.add(cursor.getString(0));
			                labels.add(cursor.getString(1));
			                labels.add(cursor.getString(2));
			                
			            } while (cursor.moveToNext());//to track  the coloum
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels;	
				}				
				
				//randi
				public List<String> loadInvoiceIds(String rep, String cus) {
					List<String> labels = new ArrayList<String>();

					// Select repid Query
					//Invoice_rep_id,Invoice_cus_id
					String selectQuery = "SELECT  Invoice_invoice_id FROM  Invoice where Invoice_rep_id = '"
							+ rep + "' and Invoice_cus_id='"+cus+"' ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor cursor = db.rawQuery(selectQuery, null);

					// looping through all rows and adding to list
					// move to first means selecting the 1st row
					if (cursor.moveToFirst()) {
						do {
							labels.add(cursor.getString(0));
						} while (cursor.moveToNext());// to track the coloum
					}

					// closing connection
					cursor.close();
					db.close();

					// returning lables
					return labels;

				}
				
				
				//randi
				public List<String> getDealerEmail(String delId) {
					List<String> labels = new ArrayList<String>();
			        labels.clear();
			        // Select repid Query
			        String selectQuery = "select del_email  from DELEAR where del_id='"+delId+"'  "; 
			        		/*repS_rep_id = '"+repid+"' "*/;     
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        //move to first means selecting the 1st row
			        if (cursor.moveToFirst()) {
			            do {
			                //labels.add(cursor.getString(0));
			                labels.add(cursor.getString(0));
			                
			                
			            } while (cursor.moveToNext());//to track  the coloum
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels;	
	
				
				}
				
				
				//randi
				public String getDailyInvoiceDateils(String repID, String date) {
			 String query="select  Invoice_cus_id,Invoice_invoice_id,Invoice_selling_date,Invoice_post_discount_amount,Invoice_paid_cost,Invoice_amount_to_be_paid from Invoice where Invoice_rep_id='"+repID+"' AND Invoice_selling_date='"+date+"'";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor c = ourDatabase.rawQuery(query, null);
					String result="";
					 int id=c.getColumnIndex(Key_Invoice_cust_id);
					 int invoiceId=c.getColumnIndex(Key_Invoice_invoice_id);
					 int invoicedDate=c.getColumnIndex(Key_Invoice_selling_date);
					 int amount=c.getColumnIndex(Key_Invoice_post_dicount_amount);
					 int paidAAmount=c.getColumnIndex(Key_Invoice_paid_cost);
					 int amountToBePaid=c.getColumnIndex(Key_Invoice_amount_to_be_paid);
					 //int date=c.getColumnIndex(Key_repS_assigned_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(id)+"*"+c.getString(invoiceId)+"*"+c.getString(invoicedDate)+"*"+c.getString(amount)+"*"+c.getString(paidAAmount)+"*"+c.getString(amountToBePaid)+"*";
					 }
					 db.close();
					 return result;
				}
				
				
				//randi
				public Cursor getInvoiceDetailsForHistory(String repID,String startdate,String enddate) {
          String query="select _id,Invoice_invoice_id,Invoice_selling_date,Invoice_amount_to_be_paid,Invoice_paid_cost,Invoice_cus_id,Invoice_post_discount_amount from Invoice where Invoice_rep_id='"+repID+"' and Invoice_selling_date between '"+startdate+"' and '"+enddate+"' ";
					
					SQLiteDatabase db=ourHelper.getReadableDatabase();
					Cursor mcurs=db.rawQuery(query, null);
					
					if(mcurs!=null){
						mcurs.moveToFirst();
						
					}
					return mcurs;
				}
				
				
				//randi
				public List<String> loadCusId(String rep) {
					List<String> labels = new ArrayList<String>();

					// Select repid Query
					//Invoice_rep_id,Invoice_cus_id
					String selectQuery = "select cus_id from Customer where customer_rep='"+rep+"' ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor cursor = db.rawQuery(selectQuery, null);

					// looping through all rows and adding to list
					// move to first means selecting the 1st row
					if (cursor.moveToFirst()) {
						do {
							labels.add(cursor.getString(0));
						} while (cursor.moveToNext());// to track the coloum
					}

					// closing connection
					cursor.close();
					db.close();

					// returning lables
					return labels;

				}
				
				
				//randi
				public Cursor InvoiceDetailsGivenCus(String cus,String rep) {
					 String query="select _id,Invoice_invoice_id,Invoice_selling_date,Invoice_amount_to_be_paid,Invoice_paid_cost,Invoice_cus_id,Invoice_post_discount_amount from Invoice where Invoice_rep_id='"+rep+"' and Invoice_cus_id='"+cus+"' ";
						
						SQLiteDatabase db=ourHelper.getReadableDatabase();
						Cursor mcurs=db.rawQuery(query, null);
						
						if(mcurs!=null){
							mcurs.moveToFirst();
							
						}
						return mcurs;
				}
	
				//randi
				public Cursor getInvoiceDetailsGivenDate(String repID,
						String date) {
					 String query="select _id,Invoice_invoice_id,Invoice_selling_date,Invoice_amount_to_be_paid,Invoice_paid_cost,Invoice_cus_id,Invoice_post_discount_amount from Invoice where Invoice_rep_id='"+repID+"' and Invoice_selling_date='"+date+"' ";
						
						SQLiteDatabase db=ourHelper.getReadableDatabase();
						Cursor mcurs=db.rawQuery(query, null);
						
						if(mcurs!=null){
							mcurs.moveToFirst();
							
						}
						return mcurs;
					
					
					
				}
					
				

				//randi
				public List<String> getCusAddressndName(String cusid) {
					List<String> labels = new ArrayList<String>();
			        labels.clear();
			        // Select repid Query
			        String selectQuery = "select customer_name,customer_shop,customer_address  from Customer where cus_id='"+cusid+"'  "; 
			        		/*repS_rep_id = '"+repid+"' "*/;     
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        //move to first means selecting the 1st row
			        if (cursor.moveToFirst()) {
			            do {
			                //labels.add(cursor.getString(0));
			                labels.add(cursor.getString(0));
			                labels.add(cursor.getString(1));
			                labels.add(cursor.getString(2));
			                
			                
			            } while (cursor.moveToNext());//to track  the coloum
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels;	
	
				
				}

				//randi
				public List<String> getAvailQuantity(String repid,String itemName,String date) {
					List<String> labels = new ArrayList<String>();
			        labels.clear();
			        // Select repid Query
			        String selectQuery = "select repS_available_qty from Rep_Stock where repS_rep_id='"+repid+"' and repS_item_name='"+itemName+"' and repS_assigned_date='"+date+"' "; 
			        		/*repS_rep_id = '"+repid+"' "*/;     
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        //move to first means selecting the 1st row
			        if (cursor.moveToFirst()) {
			            do {
			                //labels.add(cursor.getString(0));
			                labels.add(cursor.getString(0));
			                
			                
			            } while (cursor.moveToNext());//to track  the coloum
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels;	
				}
				
				
				//////////////////////////###########
				
				
				//chami
				public Cursor getRepSearchData(String id) {
					// TODO Auto-generated method stub
			String query = "select _id , rep_id from Rep where rep_delear = '"+id+"' "; 
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(query, null);
					
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
				
				//chami
				public Cursor getCusSearchData(String id) {
					// TODO Auto-generated method stub
					String query = "select _id,cus_id from Customer where customer_rep = '"+id+"' ";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(query, null);
					
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
				
				//chami
				public Cursor getCusData(String id) {
					// TODO Auto-generated method stub
					String query = "select _id, Invoice_post_discount_amount ,Invoice_due_date, Invoice_selling_date, Invoice_paid_date from Invoice where Invoice_cus_id='"+id+"' group by Invoice_due_date, Invoice_selling_date, Invoice_paid_date";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(query, null);
					
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
					
				}
				
				//chami
				public Cursor getnotificationData(String rep,String date, String Ddate) {
					// TODO Auto-generated method stub
					String query = "select _id, Cart_cus_id, Cart_total_price, Cart_issued_date, Cart_due_date from Cart where " +
							"Cart_rep_id = '"+rep+"' and Cart_paid_date = '"+date+"' and Cart_due_date < '"+Ddate+"'  ";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(query, null);
					
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}

				//chami
		/*		public String getCustomerHistoryForDELPdf(String rep, String pdate, String edatemonth, String cdate) {
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					String query = "select Cart_cus_id, Cart_total_price, Cart_issued_date, Cart_due_date from Cart where " +
							"Cart_rep_id = '"+rep+"' and Cart_paid_date = '"+pdate+"' and Cart_due_date BETWEEN '"+cdate+"' and '"+edatemonth+"' ";
					
					//and Cart_due_date < '"+edatemonth+"'
					Cursor c = ourDatabase.rawQuery(query, null);
					 String result="";
					 int cid=c.getColumnIndex(Key_Cart_cus_id);
					 int total=c.getColumnIndex(Key_Cart_total_price);
					 int idate=c.getColumnIndex(Key_Cart_issued_date);
					 int ddate=c.getColumnIndex(Key_Cart_due_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(cid)+"*"+c.getString(total)+"*"+c.getString(idate)+"*"+c.getString(ddate)+"*";
					 }
					 db.close();
					 return result;
				}*/


				//chami
			/*	public String getCustomerHistoryForDELPdfweek(String rep, String pdate,String edateweek, String cdate) {

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					String query = "select Cart_cus_id, Cart_total_price, Cart_issued_date, Cart_due_date from Cart where " +
							"Cart_rep_id = '"+rep+"' and Cart_paid_date = '"+pdate+"' and Cart_due_date BETWEEN '"+cdate+"' and '"+edateweek+"' ";
					
					Cursor c = ourDatabase.rawQuery(query, null);
					 String result="";
					 int cid=c.getColumnIndex(Key_Cart_cus_id);
					 int total=c.getColumnIndex(Key_Cart_total_price);
					 int idate=c.getColumnIndex(Key_Cart_issued_date);
					 int ddate=c.getColumnIndex(Key_Cart_due_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(cid)+"*"+c.getString(total)+"*"+c.getString(idate)+"*"+c.getString(ddate)+"*";
					 }
					 db.close();
					 return result;
				}*/
				
				//Rosheen
				public List<String> getAllItemsforReport(String delearid){
			        List<String> lbl1 = new ArrayList<String>();
			        String selectQuery = "select distinct delS_del_item_name from Del_Stock where delS_del_id='"+delearid+"' " ;
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        if (cursor.moveToFirst()) {
			            do {
			                lbl1.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			         
			        cursor.close();
			        db.close();
			         
			        return lbl1;
			    }
				
				//Rosheen
				public Cursor fetchTotalRecords(String itemName,String startdate,String enddate) {
					String selectQuery = "select  _id,repS_rep_id,repS_assigned_qty,repS_unit_price,repS_assigned_date from Rep_Stock where repS_item_name='"+itemName+"' and repS_assigned_date between '"+startdate+"' and '"+enddate+"'";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				//Rosheen
				public Cursor fetchavailableqty(String delearid) {
					String selectQuery = "select  _id,delS_del_item_name,delS_available_qty from Del_Stock where delS_purchased_date =(select max (delS_purchased_date) from Del_Stock) and delS_del_id='"+delearid+"' " ;

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(selectQuery, null);
					if(mCursor != null){
					   mCursor.moveToFirst();
					}
					   return mCursor;
					}
				
				//Rosheen
				public List<String>  getAssignedqtyupdate(String itemName,String delid) {
					 List<String> labels1 = new ArrayList<String>();
					 
					String query="select delS_available_qty from Del_Stock where delS_del_item_name='"+itemName+"' AND delS_del_id='"+delid+"' ";
					SQLiteDatabase db=ourHelper.getReadableDatabase();
					Cursor mcurs=db.rawQuery(query, null);
					
					
					 if (mcurs.moveToFirst()) {
				            do {
				                labels1.add(mcurs.getString(0));
				            } while (mcurs.moveToNext());
				        }
				         
				        // closing connection
				        mcurs.close();
				        db.close();
				         
				    return labels1;


				}
				
				//Rosheen
				public String getRepHistoryReport(String rep, String startdate, String enddate) {
					String selectQuery = "select  repS_del_id,repS_item_name,repS_assigned_qty,repS_unit_price,repS_assigned_date from Rep_Stock where repS_rep_id='"+rep+"'and repS_assigned_date between '"+startdate+"' and '"+enddate+"'";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor c = ourDatabase.rawQuery(selectQuery, null);
					String result="";
					 int id=c.getColumnIndex(Key_repS_del_id);
					 int name=c.getColumnIndex(Key_repS_item_name);
					 int qty=c.getColumnIndex(Key_repS_assigned_qty);
					 int cost=c.getColumnIndex(Key_repS_unit_price);
					 int date=c.getColumnIndex(Key_repS_assigned_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(id)+"*"+c.getString(name)+"*"+c.getString(qty)+"*"+c.getString(cost)+"*"+c.getString(date)+"*";
					 }
					 db.close();
					 return result;
				}
				
				//Rosheen
				public String getItemHistoryReport(String itemname, String startdate, String enddate) {
					String selectQuery = "select  repS_item_name,repS_rep_id,repS_assigned_date,repS_assigned_qty,repS_unit_price from Rep_Stock where repS_item_name='"+itemname+"'and repS_assigned_date between '"+startdate+"' and '"+enddate+"'";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor c = ourDatabase.rawQuery(selectQuery, null);
					String result="";
					 int name=c.getColumnIndex(Key_repS_item_name);
					 int rid=c.getColumnIndex(Key_repS_rep_id);
					 int date=c.getColumnIndex(Key_repS_assigned_date);
					 int qty=c.getColumnIndex(Key_repS_assigned_qty);
					 int cost=c.getColumnIndex(Key_repS_unit_price);
					 
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(name)+"*"+c.getString(rid)+"*"+c.getString(date)+"*"+c.getString(qty)+"*"+c.getString(cost)+"*";
					 }
					 db.close();
					 return result;
				}
				
				//Rosheen
				public Cursor fetchitemwiseRecords(String itemName,String startdate,String enddate) {
					String selectQuery = "select  _id,repS_rep_id,repS_assigned_date,repS_assigned_qty,repS_unit_price from Rep_Stock where repS_item_name='"+itemName+"' and repS_assigned_date between '"+startdate+"' and '"+enddate+"'";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				public Cursor getlowstocks(String delId) {
					String selectQuery = "select  _id,delS_del_item_name,delS_available_qty from Del_Stock where delS_del_id='"+delId+"' and delS_purchased_date =(select max (delS_purchased_date) from Del_Stock)  and delS_available_qty<=50 ";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				
				//Rosheen
				public Cursor fetchreturns(String delearid,String date) {
					String selectQuery = "select  _id,returned_items_rep_id,returned_items_items_name,returned_items_quantity,returned_items_issue from Returned_items where returned_items_del_id='"+delearid+"' and returned_items_date='"+date+"' ";

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(selectQuery, null);
					if(mCursor != null){
					   mCursor.moveToFirst();
					}
					   return mCursor;
					}
				
				//Rosheen
				public Cursor fetchPendingPayments(String repid,String startdate,String enddate) {
					String selectQuery = "select  _id,delS_payment_assign_date,delS_payment_status,delS_payment_total_cost from Dealer_payments where delS_payment_rep_id='"+repid+"' and delS_payment_status='Pending' and delS_payment_assign_date between '"+startdate+"' and '"+enddate+"' ";

			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor mCursor = db.rawQuery(selectQuery, null);
			    if(mCursor != null){
			        mCursor.moveToFirst();
			    }
			    return mCursor;
				}
				
				//Rosheen
				public List<String> getTargetdate(String repid,String assdate){
			        List<String> lbl1 = new ArrayList<String>();
			        String selectQuery = "select distinct Invoice_due_date from Invoice where Invoice_due_date =(select max (Invoice_due_date) from Invoice) and Invoice_rep_id='"+repid+"' and Invoice_selling_date='"+assdate+"'" ; 
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        if (cursor.moveToFirst()) {
			            do {
			                lbl1.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			         
			        cursor.close();
			        db.close();
			         
			        return lbl1;
			    }
				
				//chami
				public Cursor getnotificationData(String rep,String date, String Ddate, int amount) {
					// TODO Auto-generated method stub
					String query = "select _id, Invoice_cus_id, Invoice_post_discount_amount, Invoice_selling_date, Invoice_due_date, Invoice_paid_cost from Invoice where " +
							"Invoice_rep_id = '"+rep+"' and Invoice_due_date < '"+Ddate+"' and Invoice_paid_date = '"+date+"' or Invoice_amount_to_be_paid > '"+amount+"' ";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(query, null);
					
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
				

				//chami
				public Cursor getMonthnotificationData(String rep, String pdate,String edatemonth, String cdate, int amount) {
					
					String query = "select _id, Invoice_cus_id, Invoice_post_discount_amount, Invoice_selling_date, Invoice_due_date, Invoice_paid_cost from Invoice " +
							"where " +
							"Invoice_rep_id = '"+rep+"' and Invoice_due_date BETWEEN '"+cdate+"' and '"+edatemonth+"' and Invoice_paid_date = '"+pdate+"' or Invoice_amount_to_be_paid > '"+amount+"'  ";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					Cursor mCursor = db.rawQuery(query, null);
					
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
				
				//chami
				public Cursor search_All_task(String rep){
					
					String query ="select _id, taskName from Task_List where rep_id = '"+ rep +"' ";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor mCursor = db.rawQuery(query, null);
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
				
		      public Cursor search_All_task_update(String rep,String date){
					
					String query ="select _id, taskName from Tasks where rep_id ='"+ rep +"' and date ='"+ date +"' ";
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					Cursor mCursor = db.rawQuery(query, null);
					if(mCursor != null){
						mCursor.moveToFirst();
					}
					return mCursor;
				}
		      
		      public String getTable() {
		    	  return DATABASE_TABLE_TASKS;
				
			}
		      
		      public int lastInsertedID()
		      {
		    	  int retVar=0;
		    	  
		    	  String query = " select max(_id) from Tasks ";
		    	  SQLiteDatabase db = ourHelper.getReadableDatabase();
		         
		          Cursor mCursor = db.rawQuery(query, null);

		          if (mCursor != null) {
		              mCursor.moveToFirst();
		              retVar =Integer.parseInt(mCursor.getString(0));
		          }
		          mCursor.close();
		          mCursor.deactivate();
		          return retVar ;
		      }
		      
		      
		      public void deleteTask(String task,String rep,String date)
		  	{
		  		// ourDatabase.delete(DATABASE_TABLE_REP, Key_rep_Id, new String[]{del}); 
		    	  
		  		String delete_query = "Delete from  Tasks where taskName ='"+task+"' AND rep_id='"+rep+"' AND date='"+date+"'";
		  		Cursor cursor = ourDatabase.rawQuery(delete_query, null);
		  		
		  		 cursor.close();
		  		
		  	}

			public Cursor getreminderData(String rep, String cdate) {
				String query = "select _id, taskName from Tasks where rep_id='"+rep+"' and date='"+cdate+"'";
						
				
				SQLiteDatabase db = ourHelper.getReadableDatabase();
				Cursor mCursor = db.rawQuery(query, null);
				
				if(mCursor != null){
					mCursor.moveToFirst();
				}
				return mCursor;
		}
			
			
			
			//chami
			public void updateTask(String table, ContentValues cv, String whereClause,
							String[] whereArgs) {
						 SQLiteDatabase db = ourHelper.getWritableDatabase();
						 
						    //db.update(table, values, whereClause, whereArgs)
						     db.update(table, cv, whereClause, whereArgs);
						    	
					}
		
			//chami
			public void updateTaskList(String table, ContentValues cv, String whereClause,
							String[] whereArgs) {
						 SQLiteDatabase db = ourHelper.getWritableDatabase();
						 
						    //db.update(table, values, whereClause, whereArgs)
						     db.update(table, cv, whereClause, whereArgs);
						    
						
					}
			
			 public void deleteTasks1(String table, String whereClause,
						String[] whereArgs) {
					 SQLiteDatabase db = ourHelper.getWritableDatabase();
					 
					 db.delete(table, whereClause, whereArgs);

					
				}
		      
		      public void deleteTasksList(String table, String whereClause,
						String[] whereArgs) {
					 SQLiteDatabase db = ourHelper.getWritableDatabase();
					 
					 db.delete(table, whereClause, whereArgs);

					
				}
		      
		    //chami spinner
				public List<String> getAllLabelsCustomer(String rep) {
		          List<String> labels2 = new ArrayList<String>();
			        
			        // Select All Query
			        String selectQuery = "select distinct Invoice_cus_id from Invoice where Invoice_rep_id ='"+rep+"'  order by Invoice_cus_id asc " ;
			      
			        SQLiteDatabase db = ourHelper.getReadableDatabase();
			        Cursor cursor = db.rawQuery(selectQuery, null);
			      
			        // looping through all rows and adding to list
			        if (cursor.moveToFirst()) {
			            do {
			                labels2.add(cursor.getString(0));
			            } while (cursor.moveToNext());
			        }
			         
			        // closing connection
			        cursor.close();
			        db.close();
			         
			        // returning lables
			        return labels2;
				}
				
				//chami
				public String getCustomerHistoryForDELPdf(String rep, String pdate, String edatemonth, String cdate, String customer, int amount) {
					
					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					String query = "select Invoice_post_discount_amount, Invoice_selling_date, Invoice_due_date, Invoice_paid_cost from Invoice where " +
							"Invoice_rep_id = '"+rep+"' and Invoice_cus_id = '"+customer+"' and (Invoice_paid_date = '"+pdate+"' or Invoice_amount_to_be_paid > '"+amount+"') and Invoice_due_date BETWEEN '"+cdate+"' and '"+edatemonth+"' ";
					
					Cursor c = ourDatabase.rawQuery(query, null);
					 String result="";
					// int cid=c.getColumnIndex(Key_Cart_cus_id);
					 int total=c.getColumnIndex(Key_Invoice_post_dicount_amount);
					 int idate=c.getColumnIndex(Key_Invoice_selling_date);
					 int ddate=c.getColumnIndex(Key_Invoice_due_date);
					 int pcost=c.getColumnIndex(Key_Invoice_paid_cost);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(total)+"*"+c.getString(idate)+"*"+c.getString(ddate)+"*"+c.getString(pcost)+"*";
					 }
					 db.close();
					 return result;
				}

				/*
				//chami
				public String getCustomerHistoryForDELPdfweek(String rep, String pdate,String edateweek, String cdate, String customer, int amount) {

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					String query = "select Cart_total_price, Cart_issued_date, Cart_due_date from Cart where " +
							"Cart_rep_id = '"+rep+"' and Cart_paid_date = '"+pdate+"' and Cart_cus_id = '"+customer+"' and Cart_due_date BETWEEN '"+cdate+"' and '"+edateweek+"' ";
					
					Cursor c = ourDatabase.rawQuery(query, null);
					 String result="";
					 //int cid=c.getColumnIndex(Key_Cart_cus_id);
					 int total=c.getColumnIndex(Key_Cart_total_price);
					 int idate=c.getColumnIndex(Key_Cart_issued_date);
					 int ddate=c.getColumnIndex(Key_Cart_due_date);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+"*"+c.getString(total)+"*"+c.getString(idate)+"*"+c.getString(ddate)+"*";
					 }
					 db.close();
					 return result;
				}*/



				//chami
				public String getCustomerHistoryForDELPdfweek(String rep, String pdate,String edateweek, String cdate , String customer , int amount) {

					SQLiteDatabase db = ourHelper.getReadableDatabase();
					
					String query = "select Invoice_post_discount_amount, Invoice_selling_date, Invoice_due_date, Invoice_paid_cost from Invoice where " +
							"Invoice_rep_id = '"+rep+"' and Invoice_cus_id = '"+customer+"' and Invoice_paid_date = '"+pdate+"' or Invoice_amount_to_be_paid > '"+amount+"' and Invoice_due_date BETWEEN '"+cdate+"' and '"+edateweek+"' ";
					
					Cursor c = ourDatabase.rawQuery(query, null);
					 String result="";
					// int cid=c.getColumnIndex(Key_Cart_cus_id);
					 int total=c.getColumnIndex(Key_Invoice_post_dicount_amount);
					 int idate=c.getColumnIndex(Key_Invoice_selling_date);
					 int ddate=c.getColumnIndex(Key_Invoice_due_date);
					 int pcost=c.getColumnIndex(Key_Invoice_paid_cost);
					 
					 for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
						 result=result+c.getString(total)+"*"+c.getString(idate)+"*"+c.getString(ddate)+"*"+c.getString(pcost)+"*";
					 }
					 db.close();
					 return result;
				}
		
	
}