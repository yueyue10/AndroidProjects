package com.zyj.baidumap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView geometrydemo, timepiker, getlocation, overlay, routeline, myroute,
			openbaidu, geocoder, mroutline;
	Context mcontext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getlocation = (TextView) findViewById(R.id.getlocation);
		overlay = (TextView) findViewById(R.id.overlay);
		routeline = (TextView) findViewById(R.id.routeline);
		myroute = (TextView) findViewById(R.id.myroute);
		openbaidu = (TextView) findViewById(R.id.openbaidu);
		geocoder = (TextView) findViewById(R.id.geocoder);
		mroutline = (TextView) findViewById(R.id.mroutline);
		timepiker = (TextView) findViewById(R.id.timepiker);
		geometrydemo = (TextView) findViewById(R.id.GeometryDemo);
		mcontext = MainActivity.this;

		getlocation.setOnClickListener(new MyClickListener());
		overlay.setOnClickListener(new MyClickListener());
		routeline.setOnClickListener(new MyClickListener());
		myroute.setOnClickListener(new MyClickListener());
		openbaidu.setOnClickListener(new MyClickListener());
		geocoder.setOnClickListener(new MyClickListener());
		mroutline.setOnClickListener(new MyClickListener());
		timepiker.setOnClickListener(new MyClickListener());
		geometrydemo.setOnClickListener(new MyClickListener());
	}

	public class MyClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent i = new Intent();
			if (v.equals(getlocation)) {
				i.setClass(mcontext, LocationActivity.class);
				startActivity(i);
			} else if (v.equals(routeline)) {
				i.setClass(mcontext, RouteActivity.class);
				startActivity(i);
			} else if (v.equals(overlay)) {
				i.setClass(mcontext, OverlayActivity.class);
				startActivity(i);
			} else if (v.equals(myroute)) {
				i.setClass(mcontext, MyRouteActivity.class);
				startActivity(i);
			} else if (v.equals(openbaidu)) {
				i.setClass(mcontext, OpenBaiduMap.class);
				startActivity(i);
			} else if (v.equals(geocoder)) {
				i.setClass(mcontext, GeoCoderActivity.class);
				startActivity(i);
			} else if (v.equals(mroutline)) {
				i.setClass(mcontext, MRouteActivity.class);
				startActivity(i);
			} else if (v.equals(timepiker)) {
				i.setClass(mcontext, PluginDatePickerActivity.class);
				startActivity(i);
			} else if (v.equals(geometrydemo)) {
				i.setClass(mcontext, GeometryDemo.class);
				startActivity(i);
			}

		}
	}
}
