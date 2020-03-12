package com.zyj.net_encrypto;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zyj.crypto.AES;
import com.zyj.crypto.DH;
import com.zyj.crypto.RSA;
import com.zyj.net_encrypto.http.HttpRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String PUB_KEY =
            "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC29IaQyMvpdQbJePEz/aphREwE\n" +
                    "QrMgyuqwf3rk0BEu1iA8IsxGZY0EIugXjgWjt3P4MwESJyDfsoHUkYx8F3M3zqhN\n" +
                    "dLtp2LS9U68OzgHFW1lpEnN8/omCTXEL/Rr6MHxi5Sb5G8MTJc6T44evobXRZDK2\n" +
                    "ZgdJLN450uFbztrc3wIDAQAB";
    private static final String URL = "http://10.4.136.190:8080/";
    private static final String TAG = "MainActivity";
    private byte[] mAesKey;//AES密钥
    private AES mAes = new AES();//客户端持续加密使用的AES对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListener();
    }

    private void initListener() {
        final HttpRequest httpRequest = new HttpRequest(URL);
        findViewById(R.id.request_net_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断AES密钥是否为空
                final DH mDh = new DH();
                int publicKey = mDh.getPublicKey();
                if (mAesKey == null || mAesKey.length <= 0) {
                    httpRequest.handShake(RSA.encrypt(publicKey, PUB_KEY), new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, e.toString());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            byte[] dhPubKeyServer = response.body().bytes();
                            //通过服务端传来的明文，利用DH获取到公共私钥。作为AES的key
                            mAesKey = mDh.getPrivateKeyByte(dhPubKeyServer);
                            mAes.setKey(mAesKey);
                            Log.d(TAG, "aes key is:" + new String(mAesKey));
                        }
                    });
                } else {
                    httpRequest.request(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.e(TAG, "http onFailure");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            byte[] responseBytes = response.body().bytes();
                            Log.i(TAG, "http onResponse 数据:" + new String(responseBytes));
                            byte[] content = mAes.decrypt(responseBytes);
                            Log.i(TAG, "http onResponse 解密后数据:" + new String(content));
                        }
                    });
                }
            }
        });
    }
}
