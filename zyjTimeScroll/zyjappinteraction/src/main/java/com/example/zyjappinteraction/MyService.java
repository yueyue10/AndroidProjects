package com.example.zyjappinteraction;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
	private boolean Runing = false;
	private String Data = null;

	public MyService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("MyService已经开启");
		Intent intent = new Intent();
		intent.setAction("android.intent.action.zyj.INFORMATION");
		intent.putExtra("count", 10086);
		this.sendBroadcast(intent);
		Runing = true;
		new Thread() {
			@Override
			public void run() {
				super.run();
				while (Runing) {
					System.out.println("MyService已经开启");
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Runing = false;
	}
}