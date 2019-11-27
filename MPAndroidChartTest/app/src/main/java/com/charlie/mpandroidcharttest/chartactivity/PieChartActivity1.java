package com.charlie.mpandroidcharttest.chartactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.charlie.mpandroidcharttest.R;
import com.charlie.mpandroidcharttest.bean.PieChartData;
import com.charlie.mpandroidcharttest.bean.RevenuePieBean;
import com.charlie.mpandroidcharttest.presenter.PieChartLegendAdapter;
import com.charlie.mpandroidcharttest.presenter.PieChartPresenter;
import com.charlie.mpandroidcharttest.util.MPPieChartHelper;
import com.github.mikephil.charting.charts.PieChart;

import java.util.List;

public class PieChartActivity1 extends AppCompatActivity {

    PieChart pieChart;
    PieChartData pieChartData;
    RecyclerView picChart_legend_rv;
    PieChartPresenter pieChartPresenter;
    List<RevenuePieBean> revenuePieBeans;
    PieChartLegendAdapter pieChartLegendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart1);
        pieChartPresenter = new PieChartPresenter();
        initView();
        process();
    }

    private void initView() {
        initRecyclerView();
        pieChart = (PieChart) findViewById(R.id.pieChart);
        picChart_legend_rv = (RecyclerView) findViewById(R.id.picChart_legend_rv);
    }

    private void initRecyclerView() {
        //图例列表
        revenuePieBeans = pieChartPresenter.getRevenuePieBeans();
        pieChartLegendAdapter = new PieChartLegendAdapter(R.layout.item_revenue_pie_legend, revenuePieBeans);
        initRecyclerView(R.id.picChart_legend_rv, pieChartLegendAdapter, new LinearLayoutManager(this));
    }

    public RecyclerView initRecyclerView(int resId, BaseQuickAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        RecyclerView recyclerView = findViewById(resId);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    private void process() {
        pieChartPresenter.initData();
        pieChartData = pieChartPresenter.getPieChartData();
        MPPieChartHelper.setPieChart(pieChart, pieChartData.getPieValues(), pieChartData.getTitle(), pieChartData.getPic_colors());
    }
}
