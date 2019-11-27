package com.example.zyjcontactsdemo;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ListView lv_contact;
	SideBar indexBar;
	TextView mDialogText;
	// ArrayAdapter<String> lv_Adapter;
	ContactAdapter adapter;
	ArrayList<ContactBean> listData = new ArrayList<>();
	WindowManager mWindowManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		process();
	}

	private void initView() {
		indexBar = (SideBar) findViewById(R.id.guestlist_myletterlistview);
		lv_contact = (ListView) findViewById(R.id.lv_contact);
		//
		mDialogText = (TextView) LayoutInflater.from(this).inflate(
				R.layout.list_position, null);
		mDialogText.setVisibility(View.INVISIBLE);
		mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
						| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		mWindowManager.addView(mDialogText, lp);
		//
		indexBar.setTextView(mDialogText);
		indexBar.setListView(lv_contact);
	}

	private void initData() {
		ContactBean bean1 = new ContactBean();
		ContactBean bean2 = new ContactBean();
		ContactBean bean3 = new ContactBean();
		ContactBean bean4 = new ContactBean();
		ContactBean bean5 = new ContactBean();
		for (int i = 0; i < 3; i++) {
			bean1.name = "张三";
			bean2.name = "李四";
			bean3.name = "王五";
			bean4.name = "白六";
			bean5.name = "大白";
			listData.add(bean1);
			listData.add(bean2);
			listData.add(bean3);
			listData.add(bean4);
			listData.add(bean5);
		}
	}

	private void process() {
		Collections.sort(listData, new PinyinContactsComparator());
		ArrayList<String> liststr = new ArrayList<>();
		for (int i = 0; i < listData.size(); i++) {
			liststr.add(listData.get(i).name);
		}
		// lv_Adapter = new ArrayAdapter<>(MainActivity.this,
		// android.R.layout.simple_list_item_1, liststr);
		adapter = new ContactAdapter(this, listData);
		lv_contact.setAdapter(adapter);
	}
}
