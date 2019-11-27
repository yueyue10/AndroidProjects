package com.example.zyjpingmufbl;

import com.zyj.video.VideoPlayerActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView test;
	TextView test1, test2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		test = (TextView) findViewById(R.id.test);
		test1 = (TextView) findViewById(R.id.test1);
		test2 = (TextView) findViewById(R.id.test2);
		test();
		test1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						VitamioWebVideoActivity.class);
				startActivity(i);
			}
		});
		test2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,
						VideoPlayerActivity.class);
				startActivity(i);
			}
		});
	}

	private void test() {
		String str = "";

		DisplayMetrics dm = new DisplayMetrics();

		dm = this.getApplicationContext().getResources().getDisplayMetrics();

		int screenWidth = dm.widthPixels;

		int screenHeight = dm.heightPixels;

		float density = dm.density;

		float xdpi = dm.xdpi;

		float ydpi = dm.ydpi;

		str += "屏幕分辨率为:" + dm.widthPixels + " * " + dm.heightPixels + "\n";

		str += "绝对宽度:" + String.valueOf(screenWidth) + "pixels\n";

		str += "绝对高度:" + String.valueOf(screenHeight)

		+ "pixels\n";

		str += "逻辑密度:" + String.valueOf(density)

		+ "\n";

		str += "X 维 :" + String.valueOf(xdpi) + "像素每英尺\n";

		str += "Y 维 :" + String.valueOf(ydpi) + "像素每英尺\n";
		str += "dp宽高：" + px2dip(this, screenWidth) + " * "
				+ px2dip(this, screenHeight);
		test.setText(str);
	}

	public static int px2dip(Context context, int pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
