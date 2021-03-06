package com.example.mylibrary.wuxudong.rncharts.markers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.mylibrary.R;
import com.mock.mpchart.mikephil.charting.charts.Chart;
import com.mock.mpchart.mikephil.charting.components.MarkerView;
import com.mock.mpchart.mikephil.charting.data.CandleEntry;
import com.mock.mpchart.mikephil.charting.data.Entry;
import com.mock.mpchart.mikephil.charting.highlight.Highlight;
import com.mock.mpchart.mikephil.charting.utils.MPPointF;
import com.mock.mpchart.mikephil.charting.utils.Utils;

import java.util.List;
import java.util.Map;

public class RNRectangleMarkerView extends MarkerView {

    private TextView tvContent;

    private Drawable backgroundLeft = getResources().getDrawable(R.drawable.icon_chart_with_down_indicator_bg_left);
    private Drawable background = getResources().getDrawable(R.drawable.icon_chart_with_down_indicator_bg_normal);
    private Drawable backgroundRight = getResources().getDrawable(R.drawable.icon_chart_with_down_indicator_bg_right);

    private Drawable backgroundTopLeft = getResources().getDrawable(R.drawable.rectangle_marker_top_left);
    private Drawable backgroundTop = getResources().getDrawable(R.drawable.rectangle_marker_top);
    private Drawable backgroundTopRight = getResources().getDrawable(R.drawable.rectangle_marker_top_right);

    private int digits = 0;

    public RNRectangleMarkerView(Context context) {
        super(context, R.layout.rectangle_marker);

        tvContent = (TextView) findViewById(R.id.rectangle_tvContent);
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        String text = "";

        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            text = Utils.formatNumber(ce.getClose(), digits, true);
        } else {
            text = Utils.formatNumber(e.getY(), digits, true);
        }

        if (e.getData() instanceof Map) {
            if (((Map) e.getData()).containsKey("marker")) {

                Object marker = ((Map) e.getData()).get("marker");
                text = marker.toString();

                if (highlight.getStackIndex() != -1 && marker instanceof List) {
                    text = ((List) marker).get(highlight.getStackIndex()).toString();
                }

            }
        }

        tvContent.setText(text);

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        MPPointF mpPointF = super.getOffset();
        if (mpPointF == null) {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }
        return new MPPointF(-(getWidth() / 2) + mpPointF.getX(), -getHeight() + mpPointF.getY());
    }

    @Override
    public MPPointF getOffsetForDrawingAtPoint(float posX, float posY) {

        MPPointF offset = getOffset();

        MPPointF offset2 = new MPPointF();

        offset2.x = offset.x;
        offset2.y = offset.y;

        Chart chart = getChartView();

        float width = getWidth();
        float height = getHeight();

        if (posX + offset2.x < 0) {
            offset2.x = 0;

            if (posY + offset2.y < 0) {
                offset2.y = 0;
                tvContent.setBackground(backgroundTopLeft);
            } else {
                tvContent.setBackground(backgroundLeft);
            }
            setIndex(Index.START_LEFT);
        } else if (chart != null && posX + width + offset2.x > chart.getWidth()) {
            offset2.x = -width;

            if (posY + offset2.y < 0) {
                offset2.y = 0;
                tvContent.setBackground(backgroundTopRight);
            } else {
                tvContent.setBackground(backgroundRight);
            }
            setIndex(Index.END_RIGHT);
        } else {
            if (posY + offset2.y < 0) {
                offset2.y = 0;
                tvContent.setBackground(backgroundTop);
            } else {
                tvContent.setBackground(background);
            }
            setIndex(Index.NORMAL);
        }

        return offset2;
    }

    public TextView getTvContent() {
        return tvContent;
    }

}

