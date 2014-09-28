package com.NIRR.jenasena;

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

public class Chart_D_CustomerProformance extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);        
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MainDelCustomerProformance.packagename);
	    final DBCreater entry = new DBCreater(this);
		entry.openforread();
		int stockcount = entry.countcusproformancechart(s1); 
		if(stockcount==0){
			Dialog d = new Dialog(this);
			d.setTitle("Sorry!");
			TextView tv = new TextView(this);
			tv.setText("No Matching Data Found !");
			d.setContentView(tv);
			d.show();
		}
		Chart_D_CustomerProformance chart = new Chart_D_CustomerProformance();
		GraphicalView gview = chart.getView(this,s1);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.rl_chartViewer_chart_Isuru);
		layout.addView(gview);   
	}
	private GraphicalView getView(Context context,String s1) {
		Log.e("s1",""+s1);
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		int stockcount = entry.countcusproformancechart(s1); 
		Log.e("count",""+stockcount);
		int[] x = new int[stockcount];
		Double[] y = new Double[stockcount];
		Double[] z = new Double[stockcount];
		Double[] w = new Double[stockcount];
		Cursor cursor = entry.chartDelcusProformance(s1);
	/*	if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		
		int size =cursor.getCount();
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
			Log.e("count","a");
		/*  x[a]=cursor.getInt(0);
		  y[a]=cursor.getDouble(7);
		  z[a]=cursor.getDouble(8);
		  w[a]=cursor.getDouble(5);*/
			 x[a]=cursor.getInt(0);
			  y[a]=cursor.getDouble(8);
			  z[a]=cursor.getDouble(7);
			  w[a]=cursor.getDouble(4);
			  Log.e("x",""+x[a]);
			  Log.e("y",""+y[a]);
			  Log.e("w",""+w[a]);
			  Log.e("z",""+z[a]);
		  a++;  
		  cursor.moveToNext();  
		}
		
		TimeSeries series = new TimeSeries("Total bill");
		for(int i=0;i<x.length;i++){
			series.add(x[i],y[i]);
		}
		
		TimeSeries series2 = new TimeSeries("Paid");
		for(int i=0;i<x.length;i++){
			series2.add(x[i],z[i]);
		}
		
		TimeSeries series3 = new TimeSeries("Discount");
		for(int i=0;i<x.length;i++){
			series3.add(x[i],w[i]);
		}
		
		
		
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		
		
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		
	    renderer.setColor(Color.GREEN);
	    renderer.setPointStyle(PointStyle.DIAMOND);
	    renderer.setPointStrokeWidth(50);
	    renderer.setFillPoints(true); 
	    
	    
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		
	    renderer2.setColor(Color.YELLOW);
	    renderer2.setPointStyle(PointStyle.SQUARE);
	    renderer2.setPointStrokeWidth(50);
	    renderer2.setFillPoints(true);
	    
	    XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		
	    renderer3.setColor(Color.RED);
	    renderer3.setPointStyle(PointStyle.SQUARE);
	    renderer3.setPointStrokeWidth(50);
	    renderer3.setFillPoints(true);
	    
	    XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	    mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Customer Proformance ");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.setXTitle(" Bill Id ");
	    mRenderer.setYTitle(" Cost ");
	    cursor.moveToFirst();
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(0), cursor.getString(1));
			  cursor.moveToNext();
			}

	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addSeriesRenderer(renderer2);
	    mRenderer.addSeriesRenderer(renderer3);

		return ChartFactory.getLineChartView(context, dataset, mRenderer);
		
	}
}
