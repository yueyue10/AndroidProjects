package com.passion.zyj.knowall.ui.common;

import android.os.Handler;

import com.passion.zyj.knowall.R;
import com.passion.zyj.knowall.mvp.activity.BaseNorActivity;

public class CustomViewActivity extends BaseNorActivity {

    Handler handler = new Handler();

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
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            showToast("hello");
                        }
                    });
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
