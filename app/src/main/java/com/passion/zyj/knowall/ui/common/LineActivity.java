package com.passion.zyj.knowall.ui.common;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.activity.BaseNorActivity;
import com.passion.zyj.knowall.utils.line.LineGraphicView;

import java.util.ArrayList;

public class LineActivity extends BaseNorActivity {

    LineGraphicView tu;
    ArrayList<Double> yList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_line;
    }

    @Override
    protected void initToolbar() {
        setTitleBack("曲线");
    }

    @Override
    protected void initEventAndData() {
        tu = (LineGraphicView) findViewById(R.id.line_graphic);

        yList = new ArrayList<Double>();
        yList.add(2.103);
        yList.add(4.05);
        yList.add(6.60);
        yList.add(3.08);
        yList.add(4.32);
        yList.add(2.0);
        yList.add(5.0);

        ArrayList<String> xRawDatas = new ArrayList<String>();
        xRawDatas.add("05-19");
        xRawDatas.add("05-20");
        xRawDatas.add("05-21");
        xRawDatas.add("05-22");
        xRawDatas.add("05-23");
        xRawDatas.add("05-24");
        xRawDatas.add("05-25");
        xRawDatas.add("05-26");
        tu.setData(yList, xRawDatas, 8, 2);
    }
}
