package com.zyj.server.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private boolean isRunning;
    private HttpCallback httpCallback;

    public HttpServer(HttpCallback httpCallback) {
        this.httpCallback = httpCallback;
    }

    public void startHttpServer() {
        if (isRunning) {
            return;
        }
        isRunning = true;
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (isRunning) {
                Socket socket = serverSocket.accept();
                System.out.println("socket accept");
                ExecutorService threadPool = Executors.newCachedThreadPool();
                threadPool.execute(new HttpThread(socket, httpCallback));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
