package com.example.zyjwebtest.ui;

import com.example.zyjwebtest.R;
import com.example.zyjwebtest.R.id;
import com.example.zyjwebtest.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends Activity {

	public String url = "file:///android_asset/aa.html";
	WebView wv;
	ProgressBar bar;
	Button but_fanhui;
	private ValueCallback<Uri> mUploadMessage;
	private final static int FILECHOOSER_RESULTCODE = 1;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_showwebview);

		final Handler mHandler = new Handler();
		String aurl = getIntent().getStringExtra("url").trim();
		if (aurl != null && !aurl.equals("")) {
			url = aurl;
		}
		wv = (WebView) findViewById(R.id.webview);
		bar = (ProgressBar) findViewById(R.id.myProgressBar);
		but_fanhui = (Button) findViewById(R.id.but_fanhui);

		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setSupportZoom(true);

		wv.getSettings().setUseWideViewPort(true);
		wv.getSettings().setLoadWithOverviewMode(true);

		wv.getSettings().setBuiltInZoomControls(true);
		wv.getSettings().setDisplayZoomControls(false);

		wv.getSettings().setAllowFileAccess(true);
		wv.getSettings().setAllowFileAccess(true);
		wv.getSettings().setAllowContentAccess(true);
		// wv.setWebChromeClient(new WebChromeClient() {

		wv.setWebChromeClient(new TestWebChromeClient(new WebChromeClient()) {

			public void openFileChooser(ValueCallback<Uri> uploadFile,
					String acceptType) {
				Toast.makeText(WebViewActivity.this, "调用摄像头", Toast.LENGTH_SHORT)
						.show();

				System.out.println("aaaa");
				openFileChooser(uploadFile);
			}

			public void openFileChooser(ValueCallback<Uri> uploadFile,
					String acceptType, String capture) {
				Toast.makeText(WebViewActivity.this, "调用摄像头", Toast.LENGTH_SHORT)
						.show();
				System.out.println("aaaa");
				openFileChooser(uploadFile);
			}
		});

		wv.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				if (url.indexOf("tel:") >= 0) {// 页面上有数字会导致连接电话
					Uri uri = Uri.parse(url);
					Intent intent = new Intent(Intent.ACTION_CALL, uri);
					startActivity(intent);

				} else if (url.indexOf("mailto:") >= 0) {
					Intent data = new Intent(Intent.ACTION_SENDTO);
					data.setData(Uri.parse(url));
					data.putExtra(Intent.EXTRA_SUBJECT, "");
					data.putExtra(Intent.EXTRA_TEXT, "");
					startActivity(data);
				} else if (url.indexOf("preview:") >= 0) {
					Toast.makeText(WebViewActivity.this, "调用摄像头",
							Toast.LENGTH_SHORT).show();
				} else if (url.indexOf("uploadfrm:") >= 0) {
					Toast.makeText(WebViewActivity.this, "调用摄像头",
							Toast.LENGTH_SHORT).show();
				} else if (url.indexOf("uploadfrm:") >= 0) {
					Toast.makeText(WebViewActivity.this, "调用摄像头",
							Toast.LENGTH_SHORT).show();
				} else {
					view.loadUrl(url);
				}

				return true;

			}

			@Override
			public void onPageFinished(WebView view, String url) {
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				view.setVisibility(View.GONE);
			}
		});
		// wv.setWebViewClient(new WebViewClient());
		wv.loadUrl(url);
		but_fanhui.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (wv.canGoBack()) {
					wv.goBack();
				} else {
					finish();
				}
			}
		});
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wv.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wv.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		System.out.println("aaa:ShowWebViewActivity:finish");
		super.onDestroy();
		wv.destroy();
	}

}
