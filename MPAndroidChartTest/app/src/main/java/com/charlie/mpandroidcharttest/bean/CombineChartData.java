package com.charlie.mpandroidcharttest.bean;

import java.util.ArrayList;
import java.util.List;

//组合图数据
public class CombineChartData {

    private List<String> xAxisValues;
    private List<Integer> yAxisValues;
    private List<String> xAxisInterval;//10:00-11:00
    private List<Float> lineValues;
    private List<Float> barValues;

    public CombineChartData() {

    }

    public CombineChartData(List<String> xAxisValues, List<Integer> yAxisValues, List<Float> lineValues, List<Float> barValues) {
        this.xAxisValues = xAxisValues;
        this.yAxisValues = yAxisValues;
        this.lineValues = lineValues;
        this.barValues = barValues;
    }

    public List<String> getxAxisValues() {
        if (xAxisValues == null) {
            return new ArrayList<>();
        }
        return xAxisValues;
    }

    public void setxAxisValues(List<String> xAxisValues) {
        this.xAxisValues = xAxisValues;
    }

    public List<Integer> getyAxisValues() {
        if (yAxisValues == null) {
            return new ArrayList<>();
        }
        return yAxisValues;
    }

    public void setyAxisValues(List<Integer> yAxisValues) {
        this.yAxisValues = yAxisValues;
    }

    public List<String> getxAxisInterval() {
        if (xAxisInterval == null) {
            return new ArrayList<>();
        }
        return xAxisInterval;
    }

    public void setxAxisInterval(List<String> xAxisInterval) {
        this.xAxisInterval = xAxisInterval;
    }

    public List<Float> getLineValues() {
        if (lineValues == null) {
            return new ArrayList<>();
        }
        return lineValues;
    }

    public void setLineValues(List<Float> lineValues) {
        this.lineValues = lineValues;
    }

    public List<Float> getBarValues() {
        if (barValues == null) {
            return new ArrayList<>();
        }
        return barValues;
    }

    public void setBarValues(List<Float> barValues) {
        this.barValues = barValues;
    }
}
