package com.example.zyjcheckfile;

import android.app.Activity;
import android.app.Notification;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SecondActivity extends Activity {

	TextView text;
	int a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		text = (TextView) findViewById(R.id.text);
		a = 5;
		final Notification noti = new NotificationCompat.Builder(
				SecondActivity.this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setNumber(13)
				// .setContentIntent(pendingIntent)
				.setStyle(
						new NotificationCompat.InboxStyle()
								.addLine(
										"M.Twain (Google+) Haiku is more than a cert...")
								.addLine("M.Twain Reminder")
								.addLine("M.Twain Lunch?")
								.addLine("M.Twain Revised Specs")
								.addLine("M.Twain ")
								.addLine(
										"Google Play Celebrate 25 billion apps with Goo..")
								.addLine(
										"Stack Exchange StackOverflow weekly Newsl...")
								.setBigContentTitle("6 new message")
								.setSummaryText("mtwain@android.com")).build();
		text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				BadgeUtil.setBadgeCount(noti, SecondActivity.this, a);
			}
		});
	}

}
