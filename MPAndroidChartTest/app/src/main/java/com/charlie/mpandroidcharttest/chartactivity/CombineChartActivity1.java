package com.charlie.mpandroidcharttest.chartactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.CombineChartData;
import com.charlie.mpandroidcharttest.presenter.CombineChartPresenter;
import com.charlie.mpandroidcharttest.util.MPCombineChartHelper1;
import com.github.mikephil.charting.charts.CombinedChart;

public class CombineChartActivity1 extends AppCompatActivity {

    private CombinedChart combineChart;
    private CombineChartData combineChartData;
    private CombineChartPresenter combineChartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine_chart1);
        initView();
        process();
    }

    private void process() {
        combineChartPresenter = new CombineChartPresenter();
        combineChartPresenter.initData();
        combineChartData = combineChartPresenter.getCombineChartData();

        //组合图
        MPCombineChartHelper1.initCoordinateAxis(combineChart, combineChartData.getxAxisValues(), (value, axis) -> {
            if (value < 0 || value >= (combineChartData.getxAxisValues().size()))//使得两侧柱子完全显示
                return "";
            if (combineChartData.getxAxisValues().size() > 10 && value % 2 != 0)//如果数据太多的话隐藏偶数列标签
                return "";
            return combineChartData.getxAxisValues().get((int) value);
        }, null);
        MPCombineChartHelper1.setCombineChartData(combineChart, combineChartData.getLineValues(), combineChartData.getBarValues(), "KPI目标", "实际完成");
    }

    private void initView() {
        combineChart = (CombinedChart) findViewById(R.id.combineChart);
    }


}
