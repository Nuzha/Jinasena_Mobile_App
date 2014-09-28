package com.NIRR.jenasena;

import java.util.StringTokenizer;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Chart_D_RepStock extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MainDelRepStock.packagename);
	    String date="",rep="";
		StringTokenizer token = new StringTokenizer(s1,"*");
		while(token.hasMoreTokens()){
				rep = token.nextToken();
				date  = token.nextToken();
		}
		Log.e("s1",s1);
		Log.e("date",date);
		Log.e("rep",rep);
		final DBCreater entry = new DBCreater(this);
		entry.openforread();
		//int stockcount = entry.countrepstock(rep,"2014/06/03"); 
		int stockcount = entry.countrepstock(rep,date); 
		if(stockcount==0){
			Dialog d = new Dialog(this);
			d.setTitle("Sorry!");
			TextView tv = new TextView(this);
			tv.setText("No Matching Data Found !");
			d.setContentView(tv);
			d.show();
		}
		Chart_D_RepStock chart = new Chart_D_RepStock();
		GraphicalView gview = chart.getView(this,s1);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.rl_chartViewer_chart_Isuru);
		layout.addView(gview);
	}
	private GraphicalView getView(Context context,String s1) {
		String date="",rep="";
		StringTokenizer token = new StringTokenizer(s1,"*");
		while(token.hasMoreTokens()){
				rep = token.nextToken();
				date  = token.nextToken();
		}
		Log.e("s1",s1);
		Log.e("date",date);
		Log.e("rep",rep);
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		//int stockcount = entry.countrepstock(rep,"2014/06/03"); 
		int stockcount = entry.countrepstock(rep,date); 
		
		Log.e("count",""+stockcount);
		int[] x = new int[stockcount];
		int[] y = new int[stockcount];
		int[] z = new int[stockcount];
		Cursor cursor = entry.chartrepstock(rep,date);
		/*if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		int size =cursor.getCount();
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
		  x[a]=cursor.getInt(0);
		  Log.e("x",""+x[a]);
		  y[a]=cursor.getInt(6);
		  
		  Log.e("y",""+y[a]);
		  z[a]=cursor.getInt(4);
		  a++;  
		  cursor.moveToNext();  
		}
		
		CategorySeries series = new CategorySeries("Avable qty");
		for(int i=0;i<x.length;i++){
			series.add("isuru"+1,y[i]);
		}
		CategorySeries seriess = new CategorySeries("Assigen qty");
		for(int i=0;i<x.length;i++){
			seriess.add("isuru"+1,z[i]);
		}
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(seriess.toXYSeries());
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		XYSeriesRenderer renderer1 = new XYSeriesRenderer();
		mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    renderer.setColor(Color.GREEN);
	    renderer.setPointStyle(PointStyle.DIAMOND);
	    renderer.setPointStrokeWidth(50);
	    renderer.setFillPoints(true);
	    renderer1.setColor(Color.RED);
	    renderer1.setPointStyle(PointStyle.DIAMOND);
	    renderer1.setPointStrokeWidth(50);
	    renderer1.setFillPoints(true);
	    mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Rep Stock ");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer1);
	    mRenderer.setXTitle(" Item Name ");
	    mRenderer.setYTitle(" Amount ");
	    cursor.moveToFirst();
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(0), cursor.getString(3));
			  cursor.moveToNext();
			}

	    
		return ChartFactory.getLineChartView(context, dataset, mRenderer);
	}
}