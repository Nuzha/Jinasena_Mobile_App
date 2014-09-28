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

public class Chart_D_RepProformance extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MainDelRepProformance.packagename);
	    String date="",rep="";
		StringTokenizer token = new StringTokenizer(s1,"*");
		while(token.hasMoreTokens()){
				date = token.nextToken();
				rep  = token.nextToken();
		}
		Chart_D_RepProformance chart = new Chart_D_RepProformance();
		final DBCreater entry = new DBCreater(this);
		entry.openforread();
		int stockcount = entry.countrepproformancechart(rep,date); 
		if(stockcount==0){
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
				date = token.nextToken();
				rep  = token.nextToken();
		}
		//date ="2014/07/08";
		Log.e("s1",s1);
		Log.e("date",date);
		Log.e("rep",rep);
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		int stockcount = entry.countrepproformancechart(rep,date); 
		int[] x = new int[stockcount];
		Double[] y = new Double[stockcount];
		Double[] z = new Double[stockcount];
		Double[] w = new Double[stockcount];
		
		Cursor cursor = entry.chartDelrepProformance(rep,date);
		
	/*	if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		int size =cursor.getCount();
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
		  x[a]=cursor.getInt(0);
		  y[a]=cursor.getDouble(6);
		  z[a]=cursor.getDouble(7);
		  w[a]=cursor.getDouble(8);
		  Log.e("y",""+y[a]);
		  Log.e("z",""+z[a]);
		  Log.e("w",""+w[a]);
		  a++;  
		  cursor.moveToNext();  
		}
		
		CategorySeries series = new CategorySeries("Total");
		for(int i=0;i<x.length;i++){
			series.add("isuru"+1,y[i]);
		}
		
		CategorySeries series2 = new CategorySeries("Paid");
		for(int i=0;i<x.length;i++){
			series2.add("isuru"+1,z[i]);
		}
		
		CategorySeries series3 = new CategorySeries("To Paid");
		for(int i=0;i<x.length;i++){
			series3.add("isuru"+1,w[i]);
		}
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
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
	    

		mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Rep Proformance ");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.setXTitle(" Item ");
	    mRenderer.setYTitle(" qty ");
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
