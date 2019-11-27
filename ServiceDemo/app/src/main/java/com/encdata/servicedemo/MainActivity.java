package com.encdata.servicedemo;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    TextView out_tv;
    EditText data_et;
    Intent serviceIntent;
    MyService.MBinder mBinder = null;
    LinearLayout service_module_layout, service_module_layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        serviceIntent = new Intent(this, MyService.class);
        data_et = findViewById(R.id.data_et);
        out_tv = findViewById(R.id.out_tv);
        service_module_layout = findViewById(R.id.service_module_layout);
        service_module_layout1 = findViewById(R.id.service_module_layout1);
        findViewById(R.id.startsv_tv).setOnClickListener(this);
        findViewById(R.id.stopsv_tv).setOnClickListener(this);
        findViewById(R.id.bindsv_tv).setOnClickListener(this);
        findViewById(R.id.unbindsv_tv).setOnClickListener(this);
        findViewById(R.id.asyncmsg_tv).setOnClickListener(this);
        findViewById(R.id.asyncmsg_tv1).setOnClickListener(this);
        process();
    }

    @SuppressLint("WrongConstant")
    private void process() {
        GradientDrawable gd = new GradientDrawable();//创建drawable
        gd.setColor(getResources().getColor(R.color.colorPrimary));
        gd.setCornerRadius(10);
        gd.setStroke(2, getResources().getColor(R.color.colorPrimary));
        gd.setGradientType(GradientDrawable.RECTANGLE);
        service_module_layout.setBackgroundDrawable(gd);
        service_module_layout1.setBackgroundDrawable(gd);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startsv_tv:
                ((APP) getApplicationContext()).setStartType("start");
                startService(serviceIntent);
                break;
            case R.id.stopsv_tv:
                stopService(serviceIntent);
                break;
            case R.id.asyncmsg_tv1:
                serviceIntent.putExtra("data", data_et.getText().toString());
                startService(serviceIntent);
                break;
            case R.id.bindsv_tv:
                ((APP) getApplicationContext()).setStartType("bind");
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindsv_tv:
                try {
                    unbindService(this);
                } catch (Exception e) {
                    Toast.makeText(this, "没有开启服务!", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.asyncmsg_tv:
                if (mBinder != null)
                    mBinder.setData(data_et.getText().toString());
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Logger.d("onServiceConnected");
        mBinder = (MyService.MBinder) service;
        mBinder.getService().setCallBack(new MyService.CallBack() {
            @Override
            public void onDataChanged(String data) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Logger.d("onServiceDisconnected");
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            out_tv.setText(msg.getData().getString("data"));
        }
    };
}
