package com.charlie.mpandroidcharttest.presenter;

import android.graphics.Color;

import com.charlie.mpandroidcharttest.bean.PieChartData;
import com.charlie.mpandroidcharttest.bean.RevenuePieBean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class PieChartPresenter {

    PieChartData pieChartData;
    LinkedHashMap<String, Float> pieValues;
    int[] pie_colors = {
            Color.rgb(19, 194, 184),
            Color.rgb(52, 74, 241),
            Color.rgb(236, 83, 93),
            Color.rgb(255, 165, 19),
            Color.rgb(185, 230, 228),
            Color.rgb(136, 212, 208)
    };

    public void initData() {
        pieValues = new LinkedHashMap<>();
        pieChartData = new PieChartData();
        pieValues.put("团餐", 70f);
        pieValues.put("包车", 20f);
        pieValues.put("门票", 90f);
        pieValues.put("热气球", 50f);
        pieValues.put("游船", 40f);
        pieValues.put("观光车", 90f);
        pieChartData.setPieValues(pieValues);
        pieChartData.setTitle("509382");
        pieChartData.setPic_colors(pie_colors);
    }

    public List<RevenuePieBean> getRevenuePieBeans() {
        List<RevenuePieBean> revenuePieBeans = new ArrayList<>();
        revenuePieBeans.add(new RevenuePieBean("#F1404C", "门票", "¥2980", "54%"));
        revenuePieBeans.add(new RevenuePieBean("#88D4D0", "观光车", "¥2980", "54%"));
        revenuePieBeans.add(new RevenuePieBean("#2CBBB3", "团餐", "¥2980", "54%"));
        revenuePieBeans.add(new RevenuePieBean("#B9E6E4", "游船", "¥2980", "54%"));
        revenuePieBeans.add(new RevenuePieBean("#FFA513", "热气球", "¥2980", "54%"));
        revenuePieBeans.add(new RevenuePieBean("#344AF1", "包车", "¥2980", "54%"));
        return revenuePieBeans;
    }

    public PieChartData getPieChartData() {
        return pieChartData;
    }
}
