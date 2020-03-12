package com.zyj.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
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
                System.out.println("================socket accept================添加socket到线程中处理请求数据及返回数据逻辑");
                ExecutorService threadPool = Executors.newCachedThreadPool();
                threadPool.execute(new HttpThread(socket, httpCallback));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, String> getHeader(String request) {
        Map<String, String> header = new HashMap<>();
        try {
            //逐行解析request内容，读取到map中
            BufferedReader bufferedReader = new BufferedReader(new StringReader(request));
            String line = bufferedReader.readLine();
            while (line != null && !line.trim().isEmpty()) {
                int p = line.indexOf(":");
                if (p >= 0) {
                    header.put(line.substring(0, p).trim().toLowerCase(), line.substring(p + 1).trim());
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return header;
    }
}
