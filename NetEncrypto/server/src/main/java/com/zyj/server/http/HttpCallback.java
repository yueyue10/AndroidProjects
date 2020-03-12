package com.zyj.server.http;

public interface HttpCallback {
    byte[] onResponse(String request);
}
