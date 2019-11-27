package com.charlie.mpandroidcharttest.chartactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.LineChartData;
import com.charlie.mpandroidcharttest.presenter.LineChartPresenter;
import com.charlie.mpandroidcharttest.util.MPChartHelper;
import com.charlie.mpandroidcharttest.util.MPLineChartHelper2;
import com.charlie.mpandroidcharttest.util.ModelUtils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity2 extends AppCompatActivity {

    private LineChart lineChart;
    LineChartData lineChartData;
    LineChartPresenter lineChartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart1);
        lineChartPresenter = new LineChartPresenter();

        initView();
        process();
    }

    private void initView() {
        lineChart = (LineChart) findViewById(R.id.lineChart);
    }


    private void process() {
        lineChartPresenter.initData();
        lineChartData = lineChartPresenter.getLineChartData();
        //折线图
        MPLineChartHelper2.setLineChart(lineChart, lineChartData, lineChartData.getValues(), lineChartData.getTitle(), new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if (value < 0 || value >= (lineChartData.getxAxisValues().size()))//使得两侧柱子完全显示
                    return "";
                if (lineChartData.getxAxisValues().size() > 10 && value % 2 != 0)//如果数据太多的话隐藏偶数列标签
                    return "";
                return lineChartData.getxAxisValues().get((int) value);
            }
        });
    }
}
