package com.example.zyjpingmufbl;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zyj.video.FileUtils;
import com.zyj.video.VideoPlayerActivity;

public class VitamioWebVideoActivity extends Activity {

	private WebView mWebView;
	/** 网页正在加载 */
	private View mLoading;
	/** 历史记录 */
	private List<String> mHistory = new ArrayList<String>();
	private String mTitle;
	private String url="https://www.baidu.com/";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		mWebView = (WebView) findViewById(R.id.webview);
		mLoading = findViewById(R.id.loading);
		initWebView();
	}

	private void initWebView() {
		mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.getSettings().setJavaScriptEnabled(true);
		// mWebView.getSettings().setPluginsEnabled(true);

		mWebView.setWebViewClient(new WebViewClient() {

			/** 页面开始加载 */
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
				// mUrl.setText(url);
				// mUrl.setVisibility(View.VISIBLE);
			}

			/** 页面加载完成 */
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				mLoading.setVisibility(View.GONE);
				mWebView.setVisibility(View.VISIBLE);
				if (!mHistory.contains(url))
					mHistory.add(0, url);
				// mUrl.setVisibility(View.GONE);
				// 取得title
				mTitle = view.getTitle();
			};

			/** 页面跳转 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view,
					final String url) {
				if (FileUtils.isVideoOrAudio(url)) {
					Dialog dialog = new AlertDialog.Builder(VitamioWebVideoActivity.this)
							.setIcon(android.R.drawable.btn_star)
							.setTitle("播放/下载").setMessage(url)
							.setPositiveButton("播放", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											VitamioWebVideoActivity.this,
											VideoPlayerActivity.class);
									intent.putExtra("path", url);
									intent.putExtra("title", mTitle);
									startActivity(intent);
								}
							}).setNeutralButton("下载", new OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (Environment.MEDIA_MOUNTED
											.equals(Environment
													.getExternalStorageState())) {
										String savePath = Environment
												.getExternalStorageDirectory()
												+ "/";
										if (TextUtils.isEmpty(mTitle))
											savePath += FileUtils
													.getUrlFileName(url);
										else {
											savePath += mTitle
													+ "."
													+ FileUtils
															.getUrlExtension(url);
										}
										Toast.makeText(
												VitamioWebVideoActivity.this,
												"正在下载 .."
														+ FileUtils
																.getUrlFileName(savePath)
														+ " ，可从本地视频查看进度！",
												Toast.LENGTH_LONG).show();
									} else {
										Toast.makeText(VitamioWebVideoActivity.this,
												"请检测SD卡!", Toast.LENGTH_LONG)
												.show();
									}
								}
							}).setNegativeButton("取消", null).create();
					dialog.show();
					return true;
				}
				return false;
			};
		});

		/** 处理后退键 */
		mWebView.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView != null
						&& mWebView.canGoBack()) {
					if (mHistory.size() > 1) {
						mHistory.remove(0);
						mWebView.loadUrl(mHistory.get(0));
						return true;
					}
				}
				return false;
			}
		});
		mWebView.loadUrl(url);
	}
}
