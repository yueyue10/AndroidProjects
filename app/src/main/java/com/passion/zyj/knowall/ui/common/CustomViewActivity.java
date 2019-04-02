package com.passion.zyj.knowall.ui.common;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.activity.BaseNorActivity;

public class CustomViewActivity extends BaseNorActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    protected void initToolbar() {
        setTitleBack("自定义view");
    }

    @Override
    protected void initEventAndData() {

    }
}
