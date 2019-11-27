package com.example.zyjappinteraction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3 * 1000);
					Intent intent = new Intent(StartActivity.this,
							MainActivity.class);
					startActivity(intent);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

}
