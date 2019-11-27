package com.charlie.mpandroidcharttest.chartactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.StackedBarData;
import com.charlie.mpandroidcharttest.presenter.StackedBarPresenter;
import com.charlie.mpandroidcharttest.util.MPStackedBarHelper;
import com.github.mikephil.charting.charts.BarChart;

public class StackedBarActivity extends AppCompatActivity {

    private BarChart barChart;
    StackedBarData stackedBarData;
    StackedBarPresenter stackedBarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart1);
        stackedBarPresenter = new StackedBarPresenter();

        initView();
        process();
    }

    private void initView() {
        barChart = (BarChart) findViewById(R.id.barChart1);
    }

    private void process() {
        stackedBarPresenter.initData();
        stackedBarData = stackedBarPresenter.getStackedBarData();
        //叠分柱状图
        MPStackedBarHelper.setBarChart(barChart, (value, axis) -> {
            if (value < 0 || value >= (stackedBarData.getValues().size()))//使得两侧柱子完全显示
                return "";
            if (stackedBarData.getValues().size() >= 10 && value % 2 != 0)
                return "";
            return stackedBarData.getxAxisValues().get((int) value);
        });
        MPStackedBarHelper.initData(barChart, stackedBarData.getValues());
    }

}
