package com.encdata.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class MyService extends Service {

    private boolean serviceRunning = false;
    private String data = "默认信息";
    private String startType = "";

    public MyService() {
    }

    /**
     * 第一次调用startService才走这里
     */
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("onCreate_________________");
        startLogThread();
    }

    /**
     * 每次调用startService都会走这里
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand_________________");
        if (intent.getStringExtra("data") != null) {//使用StartService启动的服务传入的消息
            data = intent.getStringExtra("data");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind_________________");
        return new MBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy_________________");
        serviceRunning = false;
    }

    /**
     * 输出日志的线程
     */

    public void startLogThread() {
        serviceRunning = true;
        startType = ((APP) getApplicationContext()).getStartType();
        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (serviceRunning) {
                    i++;
                    System.out.println(">>>服务启动类型：" + startType + ">>>传入消息内容：" + data);
                    String str = "时间:" + i + "s,\n内容:" + data;
                    if (callBack != null)
                        callBack.onDataChanged(str);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public class MBinder extends android.os.Binder {

        public void setData(String data) {
            MyService.this.data = data;
        }

        public MyService getService() {
            return MyService.this;
        }
    }
    //==================================================================================

    private CallBack callBack = null;

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public static interface CallBack {
        void onDataChanged(String data);
    }
}
