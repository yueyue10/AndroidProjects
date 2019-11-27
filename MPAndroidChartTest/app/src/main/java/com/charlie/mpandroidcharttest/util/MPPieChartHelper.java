package com.charlie.mpandroidcharttest.util;

import android.graphics.Color;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by JKWANG-PC on 2016/10/20.
 */
// 饼状图
public class MPPieChartHelper {

    /**
     * 设置饼图样式
     *
     * @param pieChart
     * @param pieValues
     * @param title
     */
    public static void setPieChart(PieChart pieChart, Map<String, Float> pieValues, String title, int[] pic_colors) {
        pieChart.setUsePercentValues(true);//设置使用百分比setSelectionShift
        pieChart.getDescription().setEnabled(false);//设置描述
//        pieChart.setExtraOffsets(20, 15, 20, 15);
        pieChart.setRotationAngle(180f);//设置旋转角度
        pieChart.setHoleRadius(65f);//设置外环扩展的起点百分百，默认50%
        pieChart.setEntryLabelTextSize(9f);//设置标签字体大小
        pieChart.setDrawEntryLabels(false);//设置是否显示标签
        pieChart.setNoDataText("暂无数据");
        //设置环中的文字
        {
            pieChart.setCenterText(title);//设置环中的文字
            pieChart.setCenterTextSize(16f);//设置环中文字的大setHoleRadius小
            pieChart.setDrawCenterText(true);//设置绘制环中文字
            pieChart.setCenterTextColor(Color.parseColor("#3C3C3D"));
        }
        //图例设置
        {
            Legend legend = pieChart.getLegend();
            legend.setEnabled(false);
        }

        //设置饼图数据
        setPieChartData(pieChart, pieValues, pic_colors);

        pieChart.animateX(1500, Easing.EasingOption.EaseInOutQuad);//数据显示动画
    }

    /**
     * 设置饼图数据源
     */
    private static void setPieChartData(PieChart pieChart, Map<String, Float> pieValues, int[] pic_colors) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        {
            Set set = pieValues.entrySet();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                entries.add(new PieEntry(Float.valueOf(entry.getValue().toString()), entry.getKey().toString()));
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        {
            //dataSet.setSliceSpace(3f);//设置饼块之间的间隔
            dataSet.setSelectionShift(1f);//设置饼块选中时偏离饼图中心的距离
            dataSet.setColors(pic_colors);//设置饼块的颜色

            //dataSet.setValueLinePart1OffsetPercentage(80f);//数据连接线距图形片内部边界的距离，为百分数
            //dataSet.setValueLinePart1Length(0.3f);
            //dataSet.setValueLinePart2Length(0.4f);
            //dataSet.setValueLineColor(Color.BLUE);//设置连接线的颜色
            //dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        }
        PieData pieData = new PieData(dataSet);
        {
            pieData.setValueFormatter(new PercentFormatter());//设置数值格式器
            pieData.setValueTextColor(Color.DKGRAY);//设置数值字体颜色
            pieData.setValueTextSize(11f);//设置数值字体大小
            pieData.setDrawValues(false);//设置是否绘制数值
        }

        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }
}
