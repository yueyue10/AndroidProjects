package com.charlie.mpandroidcharttest.presenter;

import com.charlie.mpandroidcharttest.bean.LineChartData;
import com.charlie.mpandroidcharttest.util.ModelUtils;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class LineChartPresenter {

    ArrayList<Entry> values;
    List<String> xAxisValues;
    List<String> xAxisInterval;
    private LineChartData lineChartData;

    public void initData() {
        values = new ArrayList<>();
        xAxisValues = new ArrayList<>();
        xAxisInterval = new ArrayList<>();
        lineChartData = new LineChartData();
        List<Float> mealTimes = ModelUtils.getMealTimeData();
        for (int i = 0; i < mealTimes.size(); i++) {
            values.add(new Entry(i, mealTimes.get(i), null));
            xAxisValues.add(String.format("%d:00", 10 + i));
            xAxisInterval.add(String.format("%d:00-%d:00", i + 10, i + 11));
        }
        lineChartData.setValues(values);
        lineChartData.setTitle("用餐时段统计");
        lineChartData.setxAxisValues(xAxisValues);
        lineChartData.setxAxisInterval(xAxisInterval);
    }

    public LineChartData getLineChartData() {
        return lineChartData;
    }
}
