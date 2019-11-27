package com.charlie.mpandroidcharttest.presenter;

import com.charlie.mpandroidcharttest.bean.StackedBarData;
import com.charlie.mpandroidcharttest.util.ModelUtils;

import java.util.ArrayList;
import java.util.List;

public class StackedBarPresenter {

    List<String> xAxisValues;
    StackedBarData stackedBarData;

    public void initData() {
        xAxisValues = new ArrayList<>();
        stackedBarData = new StackedBarData();
        for (int i = 0; i < ModelUtils.getStackedBarData().size(); i++) {
            xAxisValues.add(String.format("6.%d", i + 1));
        }
        stackedBarData.setxAxisValues(xAxisValues);
        stackedBarData.setValues(ModelUtils.getStackedBarData());
    }

    public StackedBarData getStackedBarData() {
        return stackedBarData;
    }
}
