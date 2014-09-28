package com.NIRR.jenasena;



import java.net.Proxy.Type;

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
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;

public class Chart_D_DelearStock extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(C_Stock.packagename);
		Chart_D_DelearStock chart = new Chart_D_DelearStock();
		final DBCreater entry = new DBCreater(this);
		entry.openforread();
		int stockcount = entry.countdelstockchart(s1); 
		if(stockcount==0){
			Dialog d = new Dialog(this);
			d.setTitle("Sorry!");
			TextView tv = new TextView(this);
			tv.setText("No Mataching Data Found !");
			d.setContentView(tv);
			d.show();
		}
		GraphicalView gview = chart.getView(this,s1);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.rl_chartViewer_chart_Isuru);
		layout.addView(gview);
	}

	private GraphicalView getView(Context context,String s1) {
		// TODO Auto-generated method stub
		/*int[] x={1,2,3,4,5,6,7};
		int[] y={1000,500,300,1000,100,700,300};*/
		final DBCreater entry = new DBCreater(context);
		entry.openforread();
		int stockcount = entry.countdelstockchart(s1); 
		int[] x = new int[stockcount];
		int[] y = new int[stockcount];
		
		
		Cursor cursor = entry.chartDelMyStock(s1);
		/*if(cursor.getCount()==0){
			Toast.makeText(getApplicationContext(), " No matching data on selected catogary ", Toast.LENGTH_SHORT).show();
		}*/
		int size =cursor.getCount();
		cursor.moveToFirst();
		int a=0;
		while (cursor.isAfterLast() == false) {
		  x[a]=cursor.getInt(0);
		  y[a]=cursor.getInt(6);
		  
		  a++;  
		  cursor.moveToNext();  
		}
		
		CategorySeries series = new CategorySeries("Avable qty");
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
	    renderer.setFillPoints(true);
	   
	    mRenderer.setZoomButtonsVisible(true);
	    mRenderer.setXLabels(0);
	    mRenderer.setChartTitle(" Delear Stock Chart");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.addSeriesRenderer(renderer);
	    
	    mRenderer.setXTitle("Itrms Name");
	    mRenderer.setYTitle("Quntity");
	    cursor.moveToFirst();
	    while (cursor.isAfterLast() == false) {
			  mRenderer.addXTextLabel(cursor.getInt(0), cursor.getString(3));
			  cursor.moveToNext();
			}

		//return ChartFactory.getBarChartView(context, dataset, mRenderer, Type.DE);//LineChartView(context, dataset, mRenderer);
		return ChartFactory.getBarChartView(context, dataset, mRenderer, org.achartengine.chart.BarChart.Type.DEFAULT);
		//return ChartFactory.get
	}
	
	

}
