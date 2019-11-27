package com.encdata.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 在MyApp里面使用的Service
 * Created by zhaoyuejun on 2018/7/23.
 */

public class ForMyAppService extends Service {

    private String data = "";
    private boolean running = false;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("________________________onCreate________________________");
        new Thread() {
            @Override
            public void run() {
                running = true;
                super.run();
                while (running) {
                    System.out.println("________________________ForMyAppService里面的data数据:" + data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("________________________onStartCommand________________________");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("________________________onBind________________________");
        return new IAppServiceRemoteBinder.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                ForMyAppService.this.data = data;
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("________________________onDestroy________________________");
        running = false;
    }
}
