package com.charlie.mpandroidcharttest.util;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class ModelUtils {

    public static List<Float> getExceptList() {
        List<Float> lineValues = new ArrayList<>();
        lineValues.add(900f);
        lineValues.add(700f);
        lineValues.add(550f);
        lineValues.add(800f);
        lineValues.add(1100f);
        lineValues.add(1000f);
        lineValues.add(950f);
        lineValues.add(900f);
        lineValues.add(980f);
        lineValues.add(900f);
        lineValues.add(970f);
        lineValues.add(950f);
        return lineValues;
    }

    public static List<Float> getVirtualList() {
        List<Float> barValues = new ArrayList<>();
        barValues.add(700f);
        barValues.add(300f);
        barValues.add(300f);
        barValues.add(420f);
        barValues.add(900f);
        barValues.add(800f);
        barValues.add(500f);
        barValues.add(300f);
        barValues.add(800f);
        barValues.add(1000f);
        barValues.add(700f);
        barValues.add(300f);
        return barValues;
    }

    public static List<Float> getCarList() {
        List<Float> barValues = new ArrayList<>();
        barValues.add(10f);
        barValues.add(9f);
        barValues.add(6f);
        barValues.add(8f);
        barValues.add(9f);
        barValues.add(8f);
        barValues.add(7f);
        barValues.add(6f);
        barValues.add(7f);
        barValues.add(10f);
        barValues.add(8f);
        barValues.add(4f);
        return barValues;
    }

    public static ArrayList<BarEntry> getStackedBarData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        List<Float> barValues1 = getStackedBarData1();
        List<Float> barValues2 = getStackedBarData2();
        for (int i = 0; i < barValues1.size(); i++) {
            values.add(new BarEntry(i, new float[]{barValues1.get(i), barValues2.get(i)}));
        }
        return values;
    }


    public static List<Float> getStackedBarData1() {
        List<Float> barValues = new ArrayList<>();
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(107f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(0f);
        barValues.add(0f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(107f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(0f);
        barValues.add(0f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(107f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(0f);
        barValues.add(0f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(107f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(0f);
        barValues.add(0f);
        return barValues;
    }

    public static List<Float> getStackedBarData2() {
        List<Float> barValues = new ArrayList<>();
        barValues.add(28f);
        barValues.add(73f);
        barValues.add(133f);
        barValues.add(73f);
        barValues.add(33f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(28f);
        barValues.add(73f);
        barValues.add(133f);
        barValues.add(73f);
        barValues.add(33f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(28f);
        barValues.add(73f);
        barValues.add(133f);
        barValues.add(73f);
        barValues.add(33f);
        barValues.add(47f);
        barValues.add(47f);
        barValues.add(28f);
        barValues.add(73f);
        barValues.add(133f);
        barValues.add(73f);
        barValues.add(33f);
        barValues.add(47f);
        barValues.add(47f);
        return barValues;
    }

    public static List<Float> getMealTimeData() {
        List<Float> barValues = new ArrayList<>();
        barValues.add(138f);
        barValues.add(107f);
        barValues.add(79f);
        barValues.add(129f);
        barValues.add(170f);
        barValues.add(155f);
        barValues.add(139f);
        barValues.add(147f);
        barValues.add(155f);
        barValues.add(142f);
        barValues.add(126f);
        return barValues;
    }
}
