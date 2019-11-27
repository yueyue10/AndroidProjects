package com.zyj.baidumap;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class MApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		SDKInitializer.initialize(this);
	}
}
