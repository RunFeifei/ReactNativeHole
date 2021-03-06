package com.example.mylibrary.wuxudong.rncharts.charts;


import com.example.mylibrary.wuxudong.rncharts.data.DataExtract;
import com.example.mylibrary.wuxudong.rncharts.data.RadarDataExtract;
import com.example.mylibrary.wuxudong.rncharts.listener.RNOnChartValueSelectedListener;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.mock.mpchart.mikephil.charting.charts.Chart;
import com.mock.mpchart.mikephil.charting.charts.RadarChart;
import com.mock.mpchart.mikephil.charting.components.YAxis;
import com.mock.mpchart.mikephil.charting.data.RadarEntry;

public class RadarChartManager extends YAxisChartBase<RadarChart, RadarEntry> {

    @Override
    public String getName() {
        return "RNRadarChart";
    }

    @Override
    protected RadarChart createViewInstance(ThemedReactContext reactContext) {
        RadarChart radarChart =  new RadarChart(reactContext);
        radarChart.setOnChartValueSelectedListener(new RNOnChartValueSelectedListener(radarChart));
        return radarChart;
    }

    @Override
    DataExtract getDataExtract() {
        return new RadarDataExtract();
    }

    @Override
    public void setYAxis(Chart chart, ReadableMap propMap) {
        RadarChart radarChart = (RadarChart) chart;
        YAxis axis = radarChart.getYAxis();

        setCommonAxisConfig(chart, axis, propMap);
        setYAxisConfig(axis, propMap);
    }

    @ReactProp(name = "skipWebLineCount")
    public void setSkipWebLineCount(RadarChart chart, int count) {
        chart.setSkipWebLineCount(count);
    }

    @ReactProp(name = "drawWeb")
    public void setDrawWeb(RadarChart chart, boolean enabled) {
        chart.setDrawWeb(enabled);
    }

    @ReactProp(name = "minOffset")
    public void setMinOffset(RadarChart chart, float minOffset) {
        chart.setMinOffset(minOffset);
    }

    @ReactProp(name = "rotationEnabled")
    public void setRotationEnabled(RadarChart chart, boolean enabled) {
        chart.setRotationEnabled(enabled);
    }

    @ReactProp(name = "rotationAngle")
    public void setRotationAngle(RadarChart chart, float angle) {
        chart.setRotationAngle(angle);
    }


}
