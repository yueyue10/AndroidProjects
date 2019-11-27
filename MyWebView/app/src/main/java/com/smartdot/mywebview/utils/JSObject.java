package com.smartdot.mywebview.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.smartdot.mywebview.activity.DragRvActivity;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * JS调用android的方法
 *
 * @JavascriptInterface仍然必不可少
 */
public class JSObject {
    private Context context;
    private Handler handler;

    public JSObject(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    //js调用无参方法
    @JavascriptInterface
    public void callNull() {
        Toast.makeText(context, "JsCallAndroid", Toast.LENGTH_SHORT).show();
    }

    //js调用有参方法
    @JavascriptInterface
    public void callMessage(String data) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }

    //js调用有参方法，参数类型：JSON
    @JavascriptInterface
    public void callJson(String data) throws JSONException {
        JSONArray jsonArray = new JSONArray(data);
        Toast.makeText(context, jsonArray.toString(), Toast.LENGTH_SHORT).show();
    }

    //js调用有参方法，参数类型：JSON，获取电话号码拨打
    @JavascriptInterface
    public void callPhone(String data) {
        context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + data)));
    }

    //js调用有参方法，参数类型：JSON，关闭当前界面
    @JavascriptInterface
    public void closeActivity(String data) {
        handler.sendEmptyMessage(1000);
    }

    //js调用有参方法，参数类型：JSON，关闭当前界面
    @JavascriptInterface
    public void startToDragRvActivity(String data) {
        Intent intent = new Intent(context, DragRvActivity.class);
        context.startActivity(intent);
    }
}
