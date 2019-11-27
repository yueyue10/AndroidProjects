package com.smartdot.mywebview.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;

import com.smartdot.mywebview.R;
import com.smartdot.mywebview.utils.AsyncTaskCallBack;
import com.smartdot.mywebview.utils.AsyncTaskForWait;

/**
 * Created by zhaoyuejun on 2017/6/15.
 */

public class StartActivity extends Activity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    AsyncTaskForWait asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // Translucent status bar
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        process();
    }

    private void process() {
        if (asyncTask != null)
            asyncTask.cancelTask();
        asyncTask = new AsyncTaskForWait(new AsyncTaskCallBack<String>() {
            @Override
            public void onPreExecute() {

            }

            @Override
            public void onPostExecute(String result) {
                handler.sendEmptyMessage(0);
            }

            @Override
            public void onCancled() {

            }
        });
        asyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (asyncTask != null) {
            asyncTask.cancelTask();
        }
    }

}
