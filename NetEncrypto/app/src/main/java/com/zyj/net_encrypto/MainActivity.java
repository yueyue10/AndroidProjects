package com.zyj.net_encrypto;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zyj.net_encrypto.http.HttpRequest;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String URL = "http://10.4.136.190:8080/";
    private static final String TAG = "MainActivity";

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
                httpRequest.request(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e(TAG, "http onFailure");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.i(TAG, "http onResponse:" + response.body().string());
                    }
                });
            }
        });
    }
}
