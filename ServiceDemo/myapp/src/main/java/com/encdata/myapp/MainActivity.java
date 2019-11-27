package com.encdata.myapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.encdata.servicedemo.IAppServiceRemoteBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private EditText etInput;
    private Intent intent = null;
    private Intent serviceIntent = null;
    private IAppServiceRemoteBinder binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        serviceIntent = new Intent();
        intent.setComponent(new ComponentName("com.encdata.servicedemo", "com.encdata.servicedemo.MyService"));
        serviceIntent.setComponent(new ComponentName("com.encdata.servicedemo", "com.encdata.servicedemo.ForMyAppService"));

        etInput = findViewById(R.id.etInput);
        findViewById(R.id.startService_btn).setOnClickListener(this);
        findViewById(R.id.stopService_btn).setOnClickListener(this);
        findViewById(R.id.bindService_btn).setOnClickListener(this);
        findViewById(R.id.unbindService_btn).setOnClickListener(this);
        findViewById(R.id.asynData_btn1).setOnClickListener(this);
        findViewById(R.id.asynData_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService_btn:
                startService(intent);
                break;
            case R.id.stopService_btn:
                stopService(intent);
                break;
            case R.id.asynData_btn1:
                intent.putExtra("data", etInput.getText().toString());
                startService(intent);
                break;
            case R.id.bindService_btn:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindService_btn:
                try {
                    unbindService(this);
                    binder = null;
                } catch (Exception e) {
                    Toast.makeText(this, "没有绑定服务", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.asynData_btn:
                if (binder != null) {
                    try {
                        binder.setData(etInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = IAppServiceRemoteBinder.Stub.asInterface(service);
        System.out.println("________________________onServiceConnected________________________");
        System.out.println(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        System.out.println("________________________onServiceDisconnected________________________");
    }
}
