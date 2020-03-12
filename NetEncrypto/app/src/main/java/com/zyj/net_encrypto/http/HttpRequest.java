package com.zyj.net_encrypto.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpRequest {
    private Call mCall;

    public HttpRequest(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        mCall = okHttpClient.newCall(request);
    }

    public void request(Callback callback) {
        if (mCall != null) {
            if (mCall.isExecuted()) {
                mCall.clone().enqueue(callback);
            } else {
                mCall.enqueue(callback);
            }
        }
    }
}
