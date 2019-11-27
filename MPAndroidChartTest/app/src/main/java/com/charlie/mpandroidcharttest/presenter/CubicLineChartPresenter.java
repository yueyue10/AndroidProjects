package com.charlie.mpandroidcharttest.presenter;

import com.charlie.mpandroidcharttest.bean.LineChartData;
import com.charlie.mpandroidcharttest.util.ModelUtils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class CubicLineChartPresenter {

    ArrayList<Entry> values;
    List<String> xAxisValues;
    LineChartData lineChartData;

    public void initData() {
        lineChartData = new LineChartData();
        values = new ArrayList<>();
        xAxisValues = new ArrayList<>();
        List<Float> mealTimes = ModelUtils.getMealTimeData();
        for (int i = 0; i < mealTimes.size(); i++) {
            values.add(new Entry(i, mealTimes.get(i), null));
            xAxisValues.add(9 + i + ":00");
        }
        lineChartData.setValues(values);
        lineChartData.setTitle("游客入园时段");
        lineChartData.setxAxisValues(xAxisValues);
    }

    public LineChartData getLineChartData() {
        return lineChartData;
    }
}
