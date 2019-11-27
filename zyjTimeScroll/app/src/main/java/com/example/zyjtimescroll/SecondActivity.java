package com.example.zyjtimescroll;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity {

	private BootReceiver bootReceiver;
	private Intent intent;
	TextView count_tv, open_tv, start_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		count_tv = (TextView) findViewById(R.id.count_tv);
		open_tv = (TextView) findViewById(R.id.open_tv);
		start_tv = (TextView) findViewById(R.id.start_tv);
		process();
		setAllClick();
	}

	private void process() {
		bootReceiver = new BootReceiver();
		IntentFilter filter = new IntentFilter(BootReceiver.ACTION);
		registerReceiver(bootReceiver, filter);

	}

	private void setAllClick() {
		open_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(
						"com.example.zyjappinteraction",
						"com.example.zyjappinteraction.MainActivity"));
				startActivity(intent);
			}
		});
		start_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast.makeText(SecondActivity.this, "启动服务", Toast.LENGTH_SHORT)
						.show();
				intent = new Intent();
				intent.setComponent(new ComponentName(
						"com.example.zyjappinteraction",
						"com.example.zyjappinteraction.MyService"));
				startService(intent);
			}
		});
	}

	public class BootReceiver extends BroadcastReceiver {

		static final String ACTION = "android.intent.action.zyj.INFORMATION";

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if (intent.getAction().equals(ACTION)) {
				// 接收返回数量 count为0 即为没有警情消息
				// 获取count的值来进行UI更新
				int count = intent.getIntExtra("count", 0);
				Toast.makeText(context, "接收到其他应用的广播信息：" + count,
						Toast.LENGTH_SHORT).show();
				count_tv.setText(count + "");
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(bootReceiver);
		try {
			//可能没有启动对应的服务
			stopService(intent);
		} catch (Exception e) {
		}
	}
}
