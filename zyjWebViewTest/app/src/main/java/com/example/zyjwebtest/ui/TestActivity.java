package com.example.zyjwebtest.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.zyjwebtest.R;
import com.example.zyjwebtest.utils.SharePreferenceUtils;

public class TestActivity extends Activity {

	TextView turn;
	EditText et;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		context = TestActivity.this;
		SharePreferenceUtils.getAppConfig(context);
		turn = (TextView) findViewById(R.id.turn);
		et = (EditText) findViewById(R.id.test);
		String murl = (String) SharePreferenceUtils.getParam("url", "");
		et.setText(murl);
		turn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SharePreferenceUtils.setParam("url", et.getText().toString());
				Intent intent = new Intent(TestActivity.this,
						WebViewActivity.class);
				intent.putExtra("url", et.getText().toString());
				startActivity(intent);
			}
		});
	}

	public void jumpToCameraWebViewActivity(View view) {
		Intent intent = new Intent(TestActivity.this,
				CameraWebviewActivity.class);
		startActivity(intent);
	}

	public void jumpToWifiActivity(View view) {
		Intent intent = new Intent(TestActivity.this, WifiActivity.class);
		startActivity(intent);
	}
}
