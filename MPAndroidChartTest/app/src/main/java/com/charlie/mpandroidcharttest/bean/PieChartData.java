package com.charlie.mpandroidcharttest.bean;

import java.util.LinkedHashMap;
import java.util.List;

public class PieChartData {

    String title;
    int[] pic_colors;
    LinkedHashMap pieValues;

    public PieChartData() {

    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getPic_colors() {
        return pic_colors;
    }

    public void setPic_colors(int[] pic_colors) {
        this.pic_colors = pic_colors;
    }

    public LinkedHashMap getPieValues() {
        return pieValues;
    }

    public void setPieValues(LinkedHashMap pieValues) {
        this.pieValues = pieValues;
    }
}
