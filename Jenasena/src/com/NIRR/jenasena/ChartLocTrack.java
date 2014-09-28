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
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

public class ChartLocTrack extends Activity{

	String s1,s2,s3;
	public static String packagename="package com.NIRR.jenasena";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
		 s1=intent.getStringExtra(ReplocHistory.packagename);
		 Log.e("getter",""+s1);
		 StringTokenizer token = new StringTokenizer(s1,"*");
			
			while(token.hasMoreTokens()){
					s2 = token.nextToken();
					s3=token.nextToken();
				}
			Log.e("s2",s2);
			Log.e("s3",s3);

		//Intent intent=getIntent();
	    //String s1=intent.getStringExtra(C_Stock.packagename);
	    ChartLocTrack chart = new ChartLocTrack();
		GraphicalView gview = chart.getView(this,s1,s2,s3);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.rl_chartViewer_chart_Isuru);
		layout.addView(gview);
	}

	private GraphicalView getView(Context context,String s1,String  s2,String  s3) {
		// TODO Auto-generated method stub
		/*int[] x={1,2,3,4,5,6,7};
		int[] y={1000,500,300,1000,100,700,300};*/
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		//int stockcount = entry.countdelstockchart(s1); 
		Cursor cursor = entry.chartReploc(s2,s3);
		int size =cursor.getCount();
		int[] x = new int[size];
		int[] y = new int[size];
	//	if(size!=0){
	//888	Cursor cursor = entry.chartReploc();
		/*if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
		  x[a]=cursor.getInt(0);
		  Log.e("recordid",""+cursor.getInt(0));
		  y[a]=cursor.getInt(5);
		  Log.e("time",""+cursor.getInt(5));
		  a++;  
		  cursor.moveToNext();  
		}
		Log.e("outloop","true");
		
	/*	CategorySeries series = new CategorySeries("");
		for(int i=0;i<x.length;i++){
			series.add("isuru"+1,y[i]);
		}*/
		TimeSeries series = new TimeSeries("Rep Id: ");
		for(int i=0;i<x.length;i++){
			series.add(x[i],y[i]);
		}
		Log.e("outloop","A");
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		Log.e("outloop","B");
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		
	    renderer.setColor(Color.GREEN);
	    renderer.setPointStyle(PointStyle.DIAMOND);
	    renderer.setPointStrokeWidth(50);
	    renderer.setFillPoints(true); 
	    Log.e("outloop","C");
		
		
		
		
	/*	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
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
	    mRenderer.setChartTitle(" Delear Stock Chart");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.setXTitle("Itrms");
	    mRenderer.setYTitle("Quntity");
	    cursor.moveToFirst();
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(5), cursor.getString(1));
			  cursor.moveToNext();
			}

		//return ChartFactory.getBarChartView(context, dataset, mRenderer, Type.DE);//LineChartView(context, dataset, mRenderer);
		return ChartFactory.getBarChartView(context, dataset, mRenderer, org.achartengine.chart.BarChart.Type.DEFAULT);
		//return ChartFactory.get*/
	    XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	    mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.BLACK);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Location History ");  
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.setXTitle(" Rep ID ");
	    mRenderer.setYTitle(" Time (in 24 h) ");
	    cursor.moveToFirst();
	    
	    Log.e("outloop","D");
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(5), cursor.getString(1));
		//	  mRenderer.addYTextLabel(cursor.getInt(5), cursor.getString(1));
			//  Log.e("recordid",""+cursor.getString(1));
			  cursor.moveToNext();
			}
	    Log.e("outloop","E");
	    mRenderer.addSeriesRenderer(renderer);
	    return ChartFactory.getLineChartView(context, dataset, mRenderer);
	}
	
//	}
	

}

