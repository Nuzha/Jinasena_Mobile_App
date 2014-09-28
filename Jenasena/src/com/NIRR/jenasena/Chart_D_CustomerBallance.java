package com.NIRR.jenasena;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Chart_D_CustomerBallance extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MainDelCustomerBillBalance.packagename);
		Chart_D_CustomerBallance chart = new Chart_D_CustomerBallance();
		GraphicalView gview = chart.getView(this,s1);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.rl_chartViewer_chart_Isuru);
		layout.addView(gview);
	}
	private GraphicalView getView(Context context,String s1) {
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		int stockcount = entry.countcuscreadetschart(s1); 
		int[] x = new int[stockcount];
		int[] y = new int[stockcount];
		
		Cursor cursor = entry.chartDelcuscredets(s1);
	/*	if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		
		int size =cursor.getCount();
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
		  x[a]=cursor.getInt(0);
		  y[a]=cursor.getInt(9);
		  a++;  
		  cursor.moveToNext();  
		}
		
		CategorySeries series = new CategorySeries("");
		for(int i=0;i<x.length;i++){
			series.add("isuru"+1,y[i]);
		}
		
		
		
		
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    renderer.setColor(Color.GREEN);
	    renderer.setPointStyle(PointStyle.DIAMOND);
	    renderer.setPointStrokeWidth(50);
	    renderer.setFillPoints(true);mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Customer Balance");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.setXTitle("Bill id");
	    mRenderer.setYTitle("Cost");
	    cursor.moveToFirst();
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(0), cursor.getString(1));
			  cursor.moveToNext();
			}

		return ChartFactory.getLineChartView(context, dataset, mRenderer);
	}
}
