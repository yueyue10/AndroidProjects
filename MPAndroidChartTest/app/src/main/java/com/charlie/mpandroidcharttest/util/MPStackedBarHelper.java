package com.charlie.mpandroidcharttest.util;

import android.graphics.Color;

import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.common.MPChartMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.StackedValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

//叠分柱状图配置类
public class MPStackedBarHelper {

    public static void setBarChart(BarChart barChart, IAxisValueFormatter xAxisValueFormatter) {
        barChart.getDescription().setEnabled(false);
        //设置自定义的markerView
        MPChartMarkerView markerView = new MPChartMarkerView(barChart.getContext(), R.layout.custom_marker_view);
        barChart.setMarker(markerView);

        barChart.setPinchZoom(false);//缩放现在只能分别在x轴和y轴上完成
        //barChart.setMaxVisibleValueCount(13);//设置柱状图最多数值 条件：setDrawValues
        barChart.setDrawGridBackground(false);//绘制网格背景
        barChart.setDrawBarShadow(false);//绘制灰色背景
        barChart.setDrawValueAboveBar(false);//设置value值在柱状图上方
        barChart.setHighlightFullBarEnabled(true);//设置柱状图全部点击范围

        //配置y轴
        {
            YAxis leftAxis = barChart.getAxisLeft();
            //leftAxis.setValueFormatter(new MyValueFormatter("K"));
            leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
            barChart.getAxisRight().setEnabled(false);
        }
        //设置x轴
        {
            XAxis xLabels = barChart.getXAxis();//设置x轴
            xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
            xLabels.setValueFormatter(xAxisValueFormatter);
            xLabels.setGranularity(1f);
            xLabels.setDrawGridLines(false);
            // barChart.setDrawXLabels(false);
            // barChart.setDrawYLabels(false);
        }
        //图例设置
        {
            Legend l = barChart.getLegend();
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            l.setDrawInside(false);
            l.setFormSize(8f);//*设置图例窗体的dp大小，默认为8f
            l.setFormToTextSpace(9f);//设置表单和实际标签/文本之间的空间
            l.setXEntrySpace(20f);//设置横轴上图例项之间的空间(以像素为单位)
            // barChart.setDrawLegend(false);
        }
    }

    public static void initData(BarChart barChart, ArrayList<BarEntry> values) {
        //=====================第一步 ArrayList<BarEntry> values=====================

        //=====================第二步 BarDataSet(values, "Statistics Vienna 2014") set1=====================
        BarDataSet set1 = new BarDataSet(values, "");
        {
            //set1.setDrawIcons(false);
            set1.setColors(getColors());
            set1.setStackLabels(new String[]{"团客", "散客"});
        }
        //=====================第三步 ArrayList<IBarDataSet> dataSets=====================
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        //=====================第四步 BarData=====================
        BarData data = new BarData(dataSets);
        {
            //设置是否绘制整条柱子的数值以及保留的小数
            data.setValueFormatter(new StackedValueFormatter(true, "", 0));
            //设置柱子上的value值显示的样式
            data.setValueTextColor(Color.WHITE);
            data.setValueTextSize(10f);
            if (values.size() <= 3) {
                data.setBarWidth(0.2f);//设置每个条在x轴上的宽度(以值为单位，而不是像素为单位)。默认0.85度
            } else {
                data.setBarWidth(0.5f);
            }
            data.setDrawValues(false);
        }
        barChart.setData(data);
        barChart.setVisibleXRangeMaximum(13);
        barChart.setFitBars(true);
        barChart.invalidate();
    }

    private static int[] getColors() {
        int[] colors = {Color.rgb(241, 64, 76), Color.rgb(19, 194, 184)};
        return colors;
    }
}
