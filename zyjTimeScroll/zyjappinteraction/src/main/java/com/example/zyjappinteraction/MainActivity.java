package com.example.zyjappinteraction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		process();
	}

	private void process() {
		Intent intent = new Intent();
		intent.setAction("你发送的action");
		this.sendBroadcast(intent);
	}

}
