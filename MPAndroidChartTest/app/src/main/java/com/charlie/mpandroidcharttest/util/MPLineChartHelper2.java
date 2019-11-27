package com.charlie.mpandroidcharttest.util;

import android.graphics.Color;
import android.graphics.DashPathEffect;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.LineChartData;
import com.charlie.mpandroidcharttest.common.MPChartMarkerView;
import com.charlie.mpandroidcharttest.common.MyChartMarkerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

//普通折线图
public class MPLineChartHelper2 {

    public static void setLineChart(LineChart lineChart, LineChartData lineChartData, ArrayList<Entry> values, String lineName, IAxisValueFormatter xAxisValueFormatter) {
        // 图表样式
        {
            lineChart.setBackgroundColor(Color.WHITE);// 背景色
            lineChart.getDescription().setEnabled(true);// disable description text
            lineChart.setTouchEnabled(true);// enable touch gestures
            lineChart.setDrawGridBackground(false);//设置绘制网格背景
            // 在选择值时创建显示框的标记
            MyChartMarkerView markerView = new MyChartMarkerView(lineChart.getContext(), lineChartData);
            lineChart.setMarker(markerView);

            lineChart.setDragEnabled(true);//设置可拖拽
            lineChart.setScaleEnabled(true);//设置缩放
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);
            lineChart.setPinchZoom(false);
            lineChart.setScaleEnabled(false);

            //给图表添加描述
            Description description = new Description();
            description.setText("人次");
            description.setTextSize(11f);
            description.setTextColor(ColorUtils.string2Int("#B9BABE"));
            description.setPosition(SizeUtils.dp2px(23), SizeUtils.dp2px(10));
            lineChart.setDescription(description);
        }
        // x轴样式
        {
            XAxis xAxis = lineChart.getXAxis();
            // vertical grid lines
            // xAxis.enableGridDashedLine(10f, 10f, 0f);
            //xAxis.setCenterAxisLabels(true);
            xAxis.setDrawGridLines(false);
            xAxis.setSpaceMin(0.5f);//设置最小值前的间隔
            xAxis.setSpaceMax(0.5f);//设置最大值后的间隔
            xAxis.setAxisLineColor(Color.parseColor("#B9BABE"));
            xAxis.setTextColor(Color.parseColor("#B9BABE"));
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(xAxisValueFormatter);
        }
        //y轴样式
        {
            YAxis yAxis = lineChart.getAxisLeft();
            lineChart.getAxisRight().setEnabled(false);//禁用双重轴
            yAxis.setSpaceTop(15f);
            yAxis.setAxisMinimum(0f);
            yAxis.setDrawGridLines(true);
            yAxis.setAxisLineColor(Color.parseColor("#B9BABE"));
            yAxis.setTextColor(Color.parseColor("#B9BABE"));
        }
        //图例设置
        {
            Legend l = lineChart.getLegend();// 获取图例(只有在设置数据之后才可能)
            l.setForm(Legend.LegendForm.LINE);//将图例条目绘制为直线
            l.setEnabled(false);
        }
        setData(lineChart, values, lineName);
        lineChart.animateX(1500);
    }

    private static void setData(final LineChart lineChart, ArrayList<Entry> values, String lineName) {
        //=====================第一步 ArrayList<Entry> values=====================

        //=====================第二步 LineDataSet(values, "DataSet 1") set1=====================
        LineDataSet set1 = new LineDataSet(values, lineName);
        //配置线条样式
        {
            //设置线条样式和圆的样式
            {
                //set1.enableDashedLine(10f, 5f, 0f);//画虚线
                set1.setColor(Color.parseColor("#13C2B8"));//黑色的线
                set1.setLineWidth(1f);//设置线宽
                // set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);//绘图模式:三次贝塞尔曲线
                set1.setCircleColor(Color.parseColor("#13C2B8"));//设置圆的颜色
                set1.setCircleRadius(4.5f);//设置圆圈半径
                set1.setCircleHoleRadius(2.5f);//设置内圆半径
                set1.setDrawCircleHole(true);//是否绘制成圆圈
            }
            //自定义图例条目
            {
                set1.setFormLineWidth(1f);
                set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
                set1.setFormSize(15.f);
            }
            //设置图表数值显示样式
            {
                set1.setDrawValues(false);
                set1.setValueTextSize(9f);
                set1.enableDashedHighlightLine(10f, 5f, 0f);//将选择线绘制为虚线
                set1.setHighLightColor(Color.rgb(241, 64, 76));//设置指示线条颜色
            }
            //设置填充区域
            {
                set1.setDrawFilled(false);
                set1.setFillColor(Color.BLACK);
                set1.setFillFormatter(new IFillFormatter() {
                    @Override
                    public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                        return lineChart.getAxisLeft().getAxisMinimum();
                    }
                });
            }
        }
        //=====================第三步 ArrayList<ILineDataSet> dataSets.add(set1); =====================
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);
        //=====================第三步 LineData(dataSets) data=====================
        LineData data = new LineData(dataSets);

        //设置数据
        lineChart.setData(data);
    }
}
