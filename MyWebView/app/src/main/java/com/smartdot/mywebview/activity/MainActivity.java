package com.smartdot.mywebview.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.smartdot.mywebview.R;
import com.smartdot.mywebview.utils.JSObject;
import com.smartdot.mywebview.utils.MyWebChromeClient;
import com.smartdot.mywebview.utils.MyWebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    ProgressBar myProgressBar;
    private MyWebViewClient WVClient;
    private WebSettings webSettings;
    private MyWebChromeClient chromeClient;
    //封装接收js调用Android的方法类
    private JSObject jsobject;
    //异步请求
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1000:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initView();
    }

    private void init() {
        mWebView = (WebView) findViewById(R.id.webview);
        myProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);
        WVClient = new MyWebViewClient();
        chromeClient = new MyWebChromeClient();
        jsobject = new JSObject(MainActivity.this, mHandler);
    }

    private void initView() {
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSavePassword(false);
        //支持多种分辨率，需要js网页支持
//        webSettings.setUserAgentString("mac os");
//        webSettings.setDefaultTextEncodingName("utf-8");
        //zyj新加
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDisplayZoomControls(false);
        //显示本地js网页
        mWebView.loadUrl("file:///android_asset/test.html");
//        mWebView.loadUrl("http://cmccdi.chinamobile.com");
        //测试环境
//        mWebView.loadUrl("http://cmccdi.chinamobile.com/app/appIndex");
        //生产环境
//        mWebView.loadUrl("http://cmccdi.chinamobile.com/cmcc_dism_webapp/appIndex");

        mWebView.setWebViewClient(WVClient);
        mWebView.setWebChromeClient(new WebChromeClient() {// 为webview添加进度条
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    myProgressBar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == myProgressBar.getVisibility()) {
                        myProgressBar.setVisibility(View.VISIBLE);
                    }
                    myProgressBar.setProgress(newProgress);
//					System.out.println("newProgress      " + newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

        });
        //注意第二个参数android，这个是JS网页调用Android方法的一个类似ID的东西
        mWebView.addJavascriptInterface(jsobject, "android");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("javascript:androidKeyBack('调用js代码')");
                }
            });
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
