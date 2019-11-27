package com.charlie.mpandroidcharttest.bean;

import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class LineChartData {

    private List<String> xAxisValues;
    private List<String> xAxisInterval;
    private ArrayList<Entry> values;
    private String title;

    public List<String> getxAxisInterval() {
        if (xAxisInterval == null) {
            return new ArrayList<>();
        }
        return xAxisInterval;
    }

    public void setxAxisInterval(List<String> xAxisInterval) {
        this.xAxisInterval = xAxisInterval;
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

    public ArrayList<Entry> getValues() {
        if (values == null) {
            return new ArrayList<>();
        }
        return values;
    }

    public void setValues(ArrayList<Entry> values) {
        this.values = values;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
