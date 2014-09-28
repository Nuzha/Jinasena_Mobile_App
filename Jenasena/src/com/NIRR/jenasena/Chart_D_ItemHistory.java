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
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class Chart_D_ItemHistory extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_viewr);
		Intent intent=getIntent();
	    String s1=intent.getStringExtra(MainDelItemHistory.packagename);
		Chart_D_ItemHistory chart = new Chart_D_ItemHistory();
		GraphicalView gview = chart.getView(this,s1);
		RelativeLayout layout =(RelativeLayout)findViewById(R.id.rl_chartViewer_chart_Isuru);
		layout.addView(gview);
	}
	private GraphicalView getView(Context context,String s1) {
		int[] x={10,20,30,40,50,60,70,80,90,100};
		int[] y={56,89,4,90,12,67,88,90,100,90};
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
	    mRenderer.setChartTitle("Item History");
	    mRenderer.setChartTitleTextSize(40);
	    mRenderer.setLabelsTextSize(20);
	    mRenderer.setXLabelsPadding(10);
	    mRenderer.setBarSpacing(21.5);
	    mRenderer.setAxesColor(Color.CYAN);
	    mRenderer.addSeriesRenderer(renderer);
	    mRenderer.addXTextLabel(1, "It01");
	    mRenderer.addXTextLabel(2, "It02");
	    mRenderer.addXTextLabel(3, "It03");
	    mRenderer.addXTextLabel(4, "It04");
	    mRenderer.addXTextLabel(5, "It05");
	    mRenderer.addXTextLabel(6, "It06");
	    mRenderer.addXTextLabel(7, "It07");
	    mRenderer.addXTextLabel(8, "It08");
	    mRenderer.addXTextLabel(9, "It09");
	    mRenderer.addXTextLabel(10, "It10");
	    mRenderer.setXTitle("Month");
	    mRenderer.setYTitle("Quntity");
	    
		return ChartFactory.getLineChartView(context, dataset, mRenderer);
	}
}
