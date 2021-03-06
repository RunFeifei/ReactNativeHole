package com.example.mylibrary.wuxudong.rncharts.listener;

import com.example.mylibrary.wuxudong.rncharts.utils.EntryToWritableMapUtils;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.mock.mpchart.mikephil.charting.charts.Chart;
import com.mock.mpchart.mikephil.charting.data.Entry;
import com.mock.mpchart.mikephil.charting.highlight.Highlight;
import com.mock.mpchart.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.ref.WeakReference;

/**
 * Created by xudong on 07/03/2017.
 */

public class RNOnChartValueSelectedListener implements OnChartValueSelectedListener {

    private WeakReference<Chart> mWeakChart;

    public RNOnChartValueSelectedListener(Chart chart) {
        mWeakChart = new WeakReference<>(chart);
    }

    @Override
    public void onValueSelected(Entry entry, Highlight h) {

        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topSelect",
                    EntryToWritableMapUtils.convertEntryToWritableMap(entry));
        }
    }

    @Override
    public void onNothingSelected() {
        if (mWeakChart != null) {
            Chart chart = mWeakChart.get();

            ReactContext reactContext = (ReactContext) chart.getContext();
            reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                    chart.getId(),
                    "topSelect",
                    null);
        }

    }

}
