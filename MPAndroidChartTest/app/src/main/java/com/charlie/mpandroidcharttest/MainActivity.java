package com.charlie.mpandroidcharttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.charlie.mpandroidcharttest.chartactivity.BarChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.CombineChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.CombineChartActivity1;
import com.charlie.mpandroidcharttest.chartactivity.CombineChartActivity2;
import com.charlie.mpandroidcharttest.chartactivity.CubicLineChartActivity1;
import com.charlie.mpandroidcharttest.chartactivity.LineChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.LineChartActivity2;
import com.charlie.mpandroidcharttest.chartactivity.MultiLineChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.PieChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.PieChartActivity1;
import com.charlie.mpandroidcharttest.chartactivity.PositiveNegativeBarChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.StackedBarActivity;
import com.charlie.mpandroidcharttest.chartactivity.ThreeBarChartActivity;
import com.charlie.mpandroidcharttest.chartactivity.TwoBarChartActivity;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridview;
    List<MainBean> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListData();
        Utils.init(this);
        gridview = findViewById(R.id.gridview);
        gridview.setAdapter(new MainAdapter(data, MainActivity.this));
        gridview.setOnItemClickListener(this);
    }

    private void initListData() {
        data.add(new MainBean("柱状图（单）", BarChartActivity.class));
        data.add(new MainBean("柱状图（双）", TwoBarChartActivity.class));
        data.add(new MainBean("柱状图（三）", ThreeBarChartActivity.class));
        data.add(new MainBean("正负柱状图", PositiveNegativeBarChartActivity.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("", null));
        data.add(new MainBean("折线图（单）", LineChartActivity.class));
        data.add(new MainBean("折线图（复）", MultiLineChartActivity.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("饼图", PieChartActivity.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("", null));
        data.add(new MainBean("组合图", CombineChartActivity.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("", null));
        data.add(new MainBean("单轴组合图测试", CombineChartActivity1.class));
        data.add(new MainBean("双轴组合图测试", CombineChartActivity2.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("饼图测试", PieChartActivity1.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("", null));
        data.add(new MainBean("普通折线图测试", LineChartActivity2.class));
        data.add(new MainBean("bezier折线图测试", CubicLineChartActivity1.class));
        data.add(new MainBean("", null));
        data.add(new MainBean("堆叠柱形图测试", StackedBarActivity.class));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (data.get(position).getActivityClass() != null)
            startActivity(new Intent(MainActivity.this, data.get(position).getActivityClass()));
    }
}
