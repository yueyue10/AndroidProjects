package com.charlie.mpandroidcharttest.common;

import android.content.Context;

import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.CombineChartData;
import com.charlie.mpandroidcharttest.bean.LineChartData;
import com.charlie.mpandroidcharttest.util.StringUtils;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

/**
 * Created by JKWANG-PC on 2016/11/1.
 * <p>
 * Since release v3.0.0, markers (popup views) in the chart are represented by the IMarker interface.
 */

public class MyChartMarkerView extends MarkerView {

    private MPPointF mOffset;
    private ArrowTextView tvContent;
    private LineChartData lineChartData;
    private CombineChartData combineChartData;

    public MyChartMarkerView(Context context) {
        super(context, R.layout.my_marker_view);
        tvContent = findViewById(R.id.tvContent);
    }

    public MyChartMarkerView(Context context, CombineChartData combineChartData) {
        super(context, R.layout.my_marker_view);
        this.combineChartData = combineChartData;
        tvContent = findViewById(R.id.tvContent);
    }

    public MyChartMarkerView(Context context, LineChartData lineChartData) {
        super(context, R.layout.my_marker_view);
        this.lineChartData = lineChartData;
        tvContent = findViewById(R.id.tvContent);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        if (e instanceof CandleEntry) {
            CandleEntry ce = (CandleEntry) e;
            tvContent.setText(StringUtils.double2String(ce.getHigh(), 2));
        } else {
            String content = null;
            if (lineChartData != null) {
                String xAxisInterval = lineChartData.getxAxisInterval().get((int) e.getX());
                content = String.format("%s\n用餐游客:%s人", xAxisInterval, StringUtils.double2String(e.getY(), 0));
            } else if (combineChartData != null) {
                String xAxisInterval = combineChartData.getxAxisInterval().get((int) e.getX());
                String trainsNum = StringUtils.double2String(combineChartData.getLineValues().get((int) e.getX()), 0);
                String touristNum = StringUtils.double2String(combineChartData.getBarValues().get((int) e.getX()), 0);
                content = String.format("%s\n发车:%s次\n载客:%s人", xAxisInterval, trainsNum, touristNum);
            } else {
                content = StringUtils.double2String(e.getY(), 2);
            }
            tvContent.setText(content);
        }
        super.refreshContent(e, highlight);//必须加上该句话；This sentence must be added.
    }

    @Override
    public MPPointF getOffset() {
        mOffset = new MPPointF(-(getWidth() / 2), -getHeight());
        return mOffset;
    }
}
