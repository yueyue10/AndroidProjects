package com.charlie.mpandroidcharttest.util;

import android.graphics.Color;

import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.CombineChartData;
import com.charlie.mpandroidcharttest.common.MPChartMarkerView;
import com.charlie.mpandroidcharttest.common.MyChartMarkerView;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class MPCombineChartHelper2 {


    /**
     * 配置坐标轴样式
     *
     * @param combineChart
     * @param combineChartData 组合图表数据
     * @param xValueFormatter  x轴数值格式器
     * @param yValueFormatter  y轴数值格式器
     */
    public static void initCoordinateAxis(CombinedChart combineChart, CombineChartData combineChartData, IAxisValueFormatter xValueFormatter, IAxisValueFormatter yValueFormatter) {
        //x坐标轴设置
        {
            XAxis xAxis = combineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setGranularity(1f);//设置坐标轴最小间隔
            xAxis.setLabelCount(combineChartData.getxAxisValues().size() + 2);
            xAxis.setValueFormatter(xValueFormatter);//设置x轴数值格式器
            xAxis.setTextColor(Color.parseColor("#B9BABE"));
        }

        //y轴设置
        {
            YAxis leftAxis = combineChart.getAxisLeft();
            leftAxis.setDrawGridLines(true);
            leftAxis.setAxisMinimum(0);//y轴最小值
            leftAxis.setTextColor(Color.parseColor("#B9BABE"));
            leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
            leftAxis.setValueFormatter(yValueFormatter);//设置y轴数值格式器

            YAxis rightAxis = combineChart.getAxisRight();
            rightAxis.setDrawGridLines(false);
            rightAxis.setAxisMinimum(0);//y轴最小值
            rightAxis.setTextColor(Color.parseColor("#B9BABE"));
            rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        }
        //配置图例样式
        {
            Legend legend = combineChart.getLegend();
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
            legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
            legend.setTextColor(Color.parseColor("#B9BABE"));
            legend.setForm(Legend.LegendForm.SQUARE);
            legend.setDrawInside(false);
            //设置横轴上图例项之间的空间(以像素为单位)
            legend.setXEntrySpace(15f);
            legend.setTextSize(12f);
        }
    }

    /**
     * 设置柱线组合图样式及数据
     */
    public static void setCombineChartData(CombinedChart combineChart, CombineChartData combineChartData, String lineTitle, String barTitle) {
        combineChart.getDescription().setEnabled(false);//设置描述
        combineChart.setPinchZoom(false);//设置按比例放缩柱状图
        combineChart.setScaleEnabled(false);//设置可缩放
        combineChart.setExtraOffsets(13, 0, 0, 0);

        MyChartMarkerView markerView = new MyChartMarkerView(combineChart.getContext(), combineChartData);
        combineChart.setMarker(markerView);

        //设置绘制顺序，让线在柱的上层
        combineChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });
        //设置组合图数据
        {
            CombinedData data = new CombinedData();
            data.setData(generateLineData(combineChartData.getLineValues(), lineTitle));
            data.setData(generateBarData(combineChartData.getBarValues(), barTitle));
            combineChart.setData(data);//设置组合图数据源
        }

        XAxis xAxis = combineChart.getXAxis();
        //使得两侧柱子完全显示
        xAxis.setAxisMinimum(combineChart.getCombinedData().getXMin() - 1f);
        xAxis.setAxisMaximum(combineChart.getCombinedData().getXMax() + 1f);
        combineChart.animateX(1500);//数据显示动画，从左往右依次显示
        combineChart.invalidate();
    }

    /**
     * 生成线图数据
     */
    private static LineData generateLineData(List<Float> lineValues, String lineTitle) {
        //=====================第一步 ArrayList<Entry> lineEntries=====================
        ArrayList<Entry> lineEntries = new ArrayList<>();
        for (int i = 0, n = lineValues.size(); i < n; ++i) {
            lineEntries.add(new Entry(i, lineValues.get(i)));
        }
        //=====================第二步 LineDataSet(lineEntries, lineTitle) lineDataSet=====================
        LineDataSet lineDataSet = new LineDataSet(lineEntries, lineTitle);
        {
            lineDataSet.setLineWidth(1f);//设置线的宽度
            lineDataSet.setColor(Color.rgb(241, 64, 76));//设置线条颜色

            //配置圆圈
            lineDataSet.setCircleRadius(4.5f);//设置圆圈半径
            lineDataSet.setCircleHoleRadius(3f);//设置内圆半径
            lineDataSet.setCircleColorHole(Color.WHITE);//设置内圆的颜色
            lineDataSet.setCircleColor(Color.rgb(241, 64, 76));//设置圆圈的颜色

            //配置指示线
            lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);//将选择线绘制为虚线
            lineDataSet.setHighLightColor(Color.rgb(241, 64, 76));//设置指示线条颜色
            //lineDataSet.setValueTextColor(Color.rgb(254,116,139));
            lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);//设置线数据依赖于左侧y轴
            lineDataSet.setDrawValues(false);//不绘制线的数据
        }
        //=====================第三步 LineData(lineDataSet) lineData=====================
        LineData lineData = new LineData(lineDataSet);
        {
            lineData.setValueTextSize(10f);
            lineData.setValueFormatter((value, entry, i, viewPortHandler) ->
                    StringUtils.double2String(value, 2));
        }
        return lineData;
    }

    /**
     * 生成柱图数据
     *
     * @param barValues
     * @return
     */
    private static BarData generateBarData(List<Float> barValues, String barTitle) {
        //=====================第一步 ArrayList<BarEntry> barEntries=====================
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0, n = barValues.size(); i < n; ++i) {
            barEntries.add(new BarEntry(i, barValues.get(i)));
        }
        //=====================第二步 BarDataSet(barEntries, barTitle) barDataSet=====================
        BarDataSet barDataSet = new BarDataSet(barEntries, barTitle);
        {
            barDataSet.setDrawValues(false);
            barDataSet.setColor(Color.rgb(19, 194, 184));//设置柱子颜色
            barDataSet.setValueTextColor(Color.rgb(185, 186, 190));
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);//设置线数据依赖于左侧y轴
        }
        //=====================第三步 BarData(barDataSet) barData=====================
        BarData barData = new BarData(barDataSet);
        {
            barData.setValueTextSize(10f);
            barData.setBarWidth(0.5f);
            barData.setValueFormatter((value, entry, i, viewPortHandler) ->
                    StringUtils.double2String(value, 2));
        }
        return barData;
    }
}
