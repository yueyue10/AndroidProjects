package com.charlie.mpandroidcharttest.presenter;

import com.charlie.mpandroidcharttest.bean.CombineChartData;
import com.charlie.mpandroidcharttest.util.ModelUtils;

import java.util.ArrayList;
import java.util.List;

public class CombineChartPresenter {

    private List<String> xAxisValues;
    private List<Integer> yAxisValues;
    private List<Float> lineValues;
    private List<Float> barValues;
    private CombineChartData combineChartData;

    public void initData() {
        xAxisValues = new ArrayList<>();
        yAxisValues = new ArrayList<>();
        lineValues = ModelUtils.getExceptList();
        barValues = ModelUtils.getVirtualList();
        combineChartData = new CombineChartData();
        for (int i = 0; i < 12; ++i) {
            xAxisValues.add(i + 1 + "月");
        }
        for (int i = 0; i < 6; i++) {
            yAxisValues.add((i + 1) * 200);
        }
        combineChartData.setLineValues(lineValues);
        combineChartData.setBarValues(barValues);
        combineChartData.setxAxisValues(xAxisValues);
        combineChartData.setyAxisValues(yAxisValues);
    }

    public CombineChartData getCombineChartData() {
        return combineChartData;
    }
}
