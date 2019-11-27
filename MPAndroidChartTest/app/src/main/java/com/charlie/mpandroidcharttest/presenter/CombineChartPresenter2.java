package com.charlie.mpandroidcharttest.presenter;

import com.charlie.mpandroidcharttest.bean.CombineChartData;
import com.charlie.mpandroidcharttest.util.ModelUtils;

import java.util.ArrayList;
import java.util.List;

public class CombineChartPresenter2 {

    private List<String> xAxisValues;
    private List<Float> lineValues;
    private List<Float> barValues;
    private List<String> xAxisInterval;
    private CombineChartData combineChartData;

    public void initData() {
        xAxisValues = new ArrayList<>();
        xAxisInterval = new ArrayList<>();
        lineValues = ModelUtils.getCarList();
        barValues = ModelUtils.getExceptList();
        combineChartData = new CombineChartData();
        for (int i = 0; i < lineValues.size(); ++i) {
            xAxisValues.add(String.format("%d:00", 8 + i));
            xAxisInterval.add(String.format("%d:00-%d:00", i + 8, i + 9));
        }
        combineChartData.setLineValues(lineValues);
        combineChartData.setBarValues(barValues);
        combineChartData.setxAxisValues(xAxisValues);
        combineChartData.setxAxisInterval(xAxisInterval);
    }

    public CombineChartData getCombineChartData() {
        return combineChartData;
    }
}
