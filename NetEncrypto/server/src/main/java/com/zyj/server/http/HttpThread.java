package com.zyj.server.http;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class HttpThread implements Runnable {
    private Socket mSocket;
    private HttpCallback httpCallback;

    public HttpThread(Socket socket, HttpCallback httpCallback) {
        mSocket = socket;
        this.httpCallback = httpCallback;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            String content;
            StringBuilder request = new StringBuilder();
            while ((content = bufferedReader.readLine()) != null && !content.trim().isEmpty()) {
                request.append(content).append("\n");
            }
            System.out.println("\n=========HttpThread================request================线程里面处理数据开始\n"
                    + request.toString());
            //返回数据
            byte[] response = new byte[]{0};
            if (httpCallback != null) {
                response = httpCallback.onResponse(request.toString());
            }
            //将业务数据包装成http格式
            //1.拼接请求行
            String responseFirLine = "HTTP/1.1 200 OK \r\n";
            //2.拼接请求头
            String responseHeader = "Content-type:" + "text/html" + "\r\n";

            OutputStream outputStream = mSocket.getOutputStream();
            outputStream.write(responseFirLine.getBytes());
            outputStream.write(responseHeader.getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(response);

            //仅输出使用
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(responseFirLine.getBytes());
            baos.write(responseHeader.getBytes());
            baos.write("\r\n".getBytes());
            baos.write(response);
            System.out.println("\n=========HttpThread================outputStream================线程里面处理数据逻辑完成，关闭socket\n"
                    + baos.toString());
            mSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
