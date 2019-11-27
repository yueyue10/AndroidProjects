package com.example.zyjwebtest.ui;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;

import com.example.zyjwebtest.R;
import com.example.zyjwebtest.utils.AppTools;

public class WifiActivity extends Activity {
	String target = AppTools.getRootPath() + "/data/misc/wifi";
	File[] files = new File(target).listFiles();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi);

		String filename = "";
		if (files != null)
			for (File fff : files) {
				filename = fff.getName();
				System.out.println("WifiActivity:files=" + filename + "/n");
			}
	}
}
