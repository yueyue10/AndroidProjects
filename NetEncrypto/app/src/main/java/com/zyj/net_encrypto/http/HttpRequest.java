package com.zyj.net_encrypto.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpRequest {
    private static final String HANDSHAKE = "handshake";
    private Request.Builder mBuilder;

    public HttpRequest(String url) {
        mBuilder = new Request.Builder()
                .get()
                .url(url);
    }

    // 发起握手请求，目的是与对方交换公钥
    public void handShake(String pubKey, Callback callback) {
        mBuilder.addHeader(HANDSHAKE, pubKey);
        request(callback);
        mBuilder.removeHeader(HANDSHAKE);
    }

    public void request(Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Call mCall = okHttpClient.newCall(mBuilder.build());
        mCall.enqueue(callback);
    }

}
