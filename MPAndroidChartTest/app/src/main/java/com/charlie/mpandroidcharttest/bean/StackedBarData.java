package com.charlie.mpandroidcharttest.bean;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class StackedBarData {

    private List<Float> barValues1;
    private List<Float> barValues2;
    private List<String> xAxisValues;
    ArrayList<BarEntry> values = new ArrayList<>();

    public List<Float> getBarValues1() {
        if (barValues1 == null) {
            return new ArrayList<>();
        }
        return barValues1;
    }

    public void setBarValues1(List<Float> barValues1) {
        this.barValues1 = barValues1;
    }

    public List<Float> getBarValues2() {
        if (barValues2 == null) {
            return new ArrayList<>();
        }
        return barValues2;
    }

    public void setBarValues2(List<Float> barValues2) {
        this.barValues2 = barValues2;
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

    public ArrayList<BarEntry> getValues() {
        if (values == null) {
            return new ArrayList<>();
        }
        return values;
    }

    public void setValues(ArrayList<BarEntry> values) {
        this.values = values;
    }
}
