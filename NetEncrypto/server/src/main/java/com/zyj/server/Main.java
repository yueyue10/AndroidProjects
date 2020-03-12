package com.zyj.server;

import com.zyj.server.http.HttpCallback;
import com.zyj.server.http.HttpServer;

public class Main {
    public static void main(String[] args) {
        System.out.println("start http server");
        HttpServer httpServer = new HttpServer(new HttpCallback() {
            @Override
            public byte[] onResponse(String request) {
                return "imooc".getBytes();
            }
        });
        httpServer.startHttpServer();
    }
}
