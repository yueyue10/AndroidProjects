package com.zyj.server;

import com.zyj.crypto.AES;
import com.zyj.crypto.DH;
import com.zyj.crypto.DataUtils;
import com.zyj.crypto.RSA;
import com.zyj.server.http.HttpCallback;
import com.zyj.server.http.HttpServer;

import java.util.Map;

public class Main {
    private static final String PRI_KEY =
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALb0hpDIy+l1Bsl4\n" +
                    "8TP9qmFETARCsyDK6rB/euTQES7WIDwizEZljQQi6BeOBaO3c/gzARInIN+ygdSR\n" +
                    "jHwXczfOqE10u2nYtL1Trw7OAcVbWWkSc3z+iYJNcQv9GvowfGLlJvkbwxMlzpPj\n" +
                    "h6+htdFkMrZmB0ks3jnS4VvO2tzfAgMBAAECgYBApVRrElhi7HPyeqaX3Vj3t384\n" +
                    "viy3OJwGs3TEJvT7XLPVK+KMOVPDk2X49LjvaWIz4CnPtT27bULMzoUaT9ro0qeX\n" +
                    "bbeLXNaX8D1/OTHP+6R47SX+UL2WcklLhIATju8fYOYjj0rEW00IaCoO6HoqUOLV\n" +
                    "kmUGnTXEDL0h6G+I+QJBAO1duQvxhAdJVLhrCmO5P5tEGiJaAHZbCz1Esj21LHaS\n" +
                    "WCn+QQWscmPQuZtLXVQ3qT8axmbbtzdDWlgvXayafF0CQQDFUVKErj+Uh32ftHlv\n" +
                    "eo4oezqZ1qdtnhLzY5KEZtpqzvFWa6E4Rx5zuqns8EMyGQc4/aTJiTHSn3Jk7+S2\n" +
                    "TkprAkAW3lK3rdsUgKIi6l0j4nMYWGVULeuhe4AHtRifDVdtTQglc5N8InMa3r8j\n" +
                    "EQ260WoC5Gd8/WoXbuvDVzzlJjUZAkBsNeYAN6NMrGV7gTkbpuVxU+tWVL7rQcZ4\n" +
                    "zgGbNODRtH3r/AilWXNc2mC4PSdMwScR3SBTGjdFoAXXTyxpwlPTAkEAhz57S2Z4\n" +
                    "7QgLmYdsxMJujY7FnEFafTbHr+v/oaGRzfFq5+xg08PyryUVFCvK6JX93MX0P/6l\n" +
                    "Q4mnmEp9MWkWlw==";
    private static final String HANDSHAKE = "handshake";

    public static void main(String[] args) {
        System.out.println("================start http server================");

        // TODO: 2020/3/12  服务端判断mAesKey是否为空，如果为空返回客户端一个标识。让客户端先执行{握手请求}逻辑
        HttpServer httpServer = new HttpServer(new HttpCallback() {
            private DH mDh = new DH();
            //服务端持续加密使用的AES对象
            private AES mAes = new AES();

            @Override
            public byte[] onResponse(String request) {
                System.out.println("____________HttpCallback_onResponse____________request____________\n" +
                        "**********将线程中数据传到HttpCallBack回调中处理并获取返回数据**********\n" + request);
                if (isHandShake(request)) {
                    Map<String, String> header = HttpServer.getHeader(request);
                    String handshake = header.get(HANDSHAKE);
                    System.out.println("____________HttpCallback_onResponse____________handshake____________\n" + handshake);

                    int dhPubKeyClient = Integer.parseInt(RSA.decrypt(handshake, PRI_KEY));
                    //服务端拿到客户端传来的dh pubKey，生成dh的Secret
                    //设置给AES作为AES的密钥
                    byte[] mAesKey = mDh.getPrivateKeyByte(dhPubKeyClient);
                    mAes.setKey(mAesKey);
                    System.out.println("____________HttpCallback_onResponse____________aes key is____________\n" + new String(mAesKey));
                    byte[] result = DataUtils.int2Byte(mDh.getPublicKey());
                    System.out.println("____________HttpCallback_onResponse____________return____________\n" + new String(result));
                    return result;
                } else {
                    byte[] result = mAes.encrypt("imooc aes");
                    System.out.println("____________HttpCallback_onResponse____________return____________\n" + new String(result));
                    return result;
                }
            }
        });
        httpServer.startHttpServer();
    }

    public static boolean isHandShake(String request) {
        return (request != null && request.contains(HANDSHAKE));
    }
}
