package com.example.mylibrary.wuxudong.rncharts.data;


import com.example.mylibrary.wuxudong.rncharts.utils.BridgeUtils;
import com.example.mylibrary.wuxudong.rncharts.utils.ChartDataSetConfigUtils;
import com.example.mylibrary.wuxudong.rncharts.utils.ConversionUtil;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.mock.mpchart.mikephil.charting.data.PieData;
import com.mock.mpchart.mikephil.charting.data.PieDataSet;
import com.mock.mpchart.mikephil.charting.data.PieEntry;
import com.mock.mpchart.mikephil.charting.interfaces.datasets.IDataSet;

import java.util.ArrayList;

/**
 * Created by xudong on 02/03/2017.
 */

public class PieDataExtract extends DataExtract<PieData, PieEntry> {
    @Override
    PieData createData() {
        return new PieData();
    }

    @Override
    IDataSet<PieEntry> createDataSet(ArrayList<PieEntry> entries, String label) {
        return new PieDataSet(entries, label);
    }

    @Override
    void dataSetConfig(IDataSet<PieEntry> dataSet, ReadableMap config) {
        PieDataSet pieDataSet = (PieDataSet) dataSet;

        ChartDataSetConfigUtils.commonConfig(pieDataSet, config);

        // PieDataSet only config
        if (BridgeUtils.validate(config, ReadableType.Number, "sliceSpace")) {
            pieDataSet.setSliceSpace((float) config.getDouble("sliceSpace"));
        }
        if (BridgeUtils.validate(config, ReadableType.Number, "selectionShift")) {
            pieDataSet.setSelectionShift((float) config.getDouble("selectionShift"));
        }
    }

    @Override
    PieEntry createEntry(ReadableArray values, int index) {
        PieEntry entry;

        if (ReadableType.Map.equals(values.getType(index))) {
            ReadableMap map = values.getMap(index);

            float value = (float) map.getDouble("value");
            if (BridgeUtils.validate(map, ReadableType.String, "label")) {
                entry = new PieEntry(value, map.getString("label"), ConversionUtil.toMap(map));
            } else {
                entry = new PieEntry(value, ConversionUtil.toMap(map));
            }
        } else if (ReadableType.Number.equals(values.getType(index))) {
            entry = new PieEntry((float) values.getDouble(index));
        } else {
            throw new IllegalArgumentException("Unexpected entry type: " + values.getType(index));
        }

        return entry;
    }

}
