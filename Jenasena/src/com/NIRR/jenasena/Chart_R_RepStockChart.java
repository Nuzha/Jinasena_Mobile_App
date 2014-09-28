package com.NIRR.jenasena;

import java.util.StringTokenizer;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
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

public class Chart_R_RepStockChart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(Chart_R_RepStock.packagename);
	    Chart_R_RepStockChart chart = new Chart_R_RepStockChart();
	    String date="",rep="";
		StringTokenizer token = new StringTokenizer(s1,"*");
		while(token.hasMoreTokens()){
				rep = token.nextToken();
				date  = token.nextToken();
		}
		//String rep=s1;
		Log.e("s1",s1);
		Log.e("rep",rep);
		final DBCreater entry = new DBCreater(this);
		entry.openforread();
		//int stockcount = entry.countrepstock(rep,date);
		int stockcount = entry.countrepstock(rep,date);
		if(stockcount==0){
			Toast.makeText(this, "Sorry on data display", Toast.LENGTH_SHORT).show();
			Dialog d = new Dialog(this);
			d.setTitle("Sorry!");
			TextView tv = new TextView(this);
			tv.setText("No Matching Data Found !");
			d.setContentView(tv);
			d.show();
		}
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
		//String rep=s1;
		Log.e("s1",s1);
		Log.e("rep",rep);
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		//int stockcount = entry.countrepstock(rep,date);
		int stockcount = entry.countrepstock(rep,date);
		Log.e("count",""+stockcount);
		int[] x = new int[stockcount];
		int[] y = new int[stockcount];
		
		//Cursor cursor = entry.chartrepstock(rep,date);
		Cursor cursor = entry.chartrepstock(rep,date);
	/*	if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		
		int size =cursor.getCount();
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
		  x[a]=cursor.getInt(0);
		  Log.e("x",""+x[a]);
		  y[a]=cursor.getInt(4);
		  Log.e("y",""+y[a]);
		  a++;  
		  cursor.moveToNext();  
		}
		
		TimeSeries series = new TimeSeries("Quntity");
		for(int i=0;i<x.length;i++){
			series.add(x[i],y[i]);
		}
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    renderer.setColor(Color.GREEN);
	    renderer.setPointStyle(PointStyle.DIAMOND);
	    renderer.setPointStrokeWidth(50);
	    renderer.setFillPoints(true);mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Rep Stock ");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.setXTitle("Avable Qty");
	    mRenderer.setYTitle(" Item Name ");
	    cursor.moveToFirst();
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(0), cursor.getString(3));
			  cursor.moveToNext();
			}

	    
		return ChartFactory.getLineChartView(context, dataset, mRenderer);
	}
	
}
