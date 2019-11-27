package com.example.zyjtimescroll;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	HorizontalListView horizontal_lv;
	HorizontalLvAdapter horlvAdapter;
	Context mcontext;
	TextView left_tv, right_tv, jumpToSecond;
	String currentnum = "19";
	//
	private Timer mTimer;
	WebView vipLeave;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(mcontext, "TimerTask测试隔10s加载webview",
						Toast.LENGTH_SHORT).show();
				vipLeave.reload(); // 刷新
				break;
			case 999:

				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mcontext = MainActivity.this;
		mTimer = new Timer();
		setTimerTask();
		initView();
		initData();
		process();
		setAllClick();
	}

	private void initView() {
		vipLeave = (WebView) findViewById(R.id.wv_home_vipLeave);
		left_tv = (TextView) findViewById(R.id.left_tv);
		right_tv = (TextView) findViewById(R.id.right_tv);
		jumpToSecond = (TextView) findViewById(R.id.jumpToSecond);
		horizontal_lv = (HorizontalListView) findViewById(R.id.horizontal_lv);
		horlvAdapter = new HorizontalLvAdapter(mcontext);
		horizontal_lv.setAdapter(horlvAdapter);
		// //
		vipLeave.getSettings().setJavaScriptEnabled(true);// 开启webview对JS的支持
		vipLeave.getSettings().setSupportZoom(true);
		vipLeave.getSettings().setUseWideViewPort(true);
		vipLeave.getSettings().setLoadWithOverviewMode(true);

		vipLeave.getSettings().setBuiltInZoomControls(true);
		vipLeave.getSettings().setDisplayZoomControls(false);

		vipLeave.getSettings().setAllowFileAccess(true);
		vipLeave.getSettings().setAllowFileAccess(true);
		vipLeave.getSettings().setAllowContentAccess(true);
		vipLeave.loadUrl("http://www.baidu.com");
	}

	ArrayList<Data> datas = new ArrayList<Data>();

	private void initData() {
		ArrayList<DataInfo> dateInfos1 = new ArrayList<>();
		DataInfo dateInfo1 = new DataInfo("18号的数据1");
		DataInfo dateInfo2 = new DataInfo("18号的数据2");
		DataInfo dateInfo7 = new DataInfo("18号的数据3");
		dateInfos1.add(dateInfo1);
		dateInfos1.add(dateInfo2);
		dateInfos1.add(dateInfo7);
		Data data1 = new Data("18", dateInfos1);
		//
		ArrayList<DataInfo> dateInfos2 = new ArrayList<>();
		DataInfo dateInfo3 = new DataInfo("19号的数据1");
		DataInfo dateInfo4 = new DataInfo("19号的数据2");
		DataInfo dateInfo8 = new DataInfo("19号的数据3");
		dateInfos2.add(dateInfo3);
		dateInfos2.add(dateInfo4);
		dateInfos2.add(dateInfo8);
		Data data2 = new Data("19", dateInfos2);
		//
		ArrayList<DataInfo> dateInfos3 = new ArrayList<>();
		DataInfo dateInfo5 = new DataInfo("20号的数据1");
		DataInfo dateInfo6 = new DataInfo("20号的数据2");
		DataInfo dateInfo9 = new DataInfo("20号的数据3");
		dateInfos3.add(dateInfo5);
		dateInfos3.add(dateInfo6);
		dateInfos3.add(dateInfo9);
		Data data3 = new Data("20", dateInfos3);
		//
		datas.add(data1);
		datas.add(data2);
		datas.add(data3);
	}

	private void process() {
		// 1.判断当前时间逻辑，今天是19号.给CurrentNum设置19
		horlvAdapter.LoadData(datas.get(1).datainfos);
		for (int i = 0; i < datas.size(); i++) {
			if (datas.get(i).date.equals(IntToString(19 - 1))) {
				left_tv.setText(IntToString(19 - 1));
			}
			if (datas.get(i).date.equals(IntToString(19 + 1))) {
				right_tv.setText(IntToString(19 + 1));
			}
		}
	}

	private void setTimerTask() {
		mTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		}, 10 * 1000, 60 * 1000/* 表示1000毫秒之後，每隔1000毫秒執行一次 */);
	}

	private void setAllClick() {
		// 2.点击左右两个按钮时的逻辑
		left_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String left = left_tv.getText().toString();
				// 如果按钮的文字为空时就不操作。
				if (left.trim().equals(""))
					return;
				// 下面的操作相当于将数据想左滚动。
				// 从数据源集合里面取出当前left_tv位置数前面的数据，给左边left_tv设置内容
				for (int i = 0; i < datas.size(); i++) {
					if (datas.get(i).date
							.equals(IntToString(StringToInt(left) - 1))) {
						left_tv.setText(IntToString(StringToInt(left) - 1));
						break;
					} else {
						left_tv.setText("");
					}
				}
				// 从数据源集合里面取出当前left_tv位置后面的数据，给右边right_tv设置内容
				for (int i = 0; i < datas.size(); i++) {
					if (datas.get(i).date
							.equals(IntToString(StringToInt(left) + 1))) {
						right_tv.setText(IntToString(StringToInt(left) + 1));
						break;
					} else {
						right_tv.setText(" ");
					}
				}
				// 从数据源集合里面取出当前left_tv位置的数据，刷新中间的横行listview
				for (int i = 0; i < datas.size(); i++) {
					if (datas.get(i).date.equals(left)) {
						horlvAdapter.LoadData(datas.get(i).datainfos);
					}
				}
			}
		});
		right_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String right = right_tv.getText().toString();
				// 这里的操作和上面类似。
				if (right.trim().equals(""))
					return;
				for (int i = 0; i < datas.size(); i++) {
					if (datas.get(i).date
							.equals(IntToString(StringToInt(right) - 1))) {
						left_tv.setText(IntToString(StringToInt(right) - 1));
						break;
					} else {
						left_tv.setText(" ");
					}
				}
				for (int i = 0; i < datas.size(); i++) {
					if (datas.get(i).date
							.equals(IntToString(StringToInt(right) + 1))) {
						right_tv.setText(IntToString(StringToInt(right) + 1));
						break;
					} else {
						right_tv.setText(" ");
					}
				}
				for (int i = 0; i < datas.size(); i++) {
					if (datas.get(i).date.equals(right)) {
						horlvAdapter.LoadData(datas.get(i).datainfos);
					}
				}
			}
		});
		jumpToSecond.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}

	public String IntToString(int a) {
		String aString = a + "";
		return aString;
	}

	public int StringToInt(String a) {
		int aint = 0;
		if (a != null && !a.equals(" ")) {
			aint = Integer.parseInt(a);
		}
		return aint;
	}
}
