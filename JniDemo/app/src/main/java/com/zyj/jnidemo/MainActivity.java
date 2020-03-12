package com.zyj.jnidemo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.zyj.jnidemo.JNITools.stringFromJNI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView sample_text;
    TextView add, div, mul, sub;
    int a = 3, b = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        sample_text = findViewById(R.id.sample_text);
        sample_text.setText(stringFromJNI());
        add = findViewById(R.id.add);
        div = findViewById(R.id.div);
        mul = findViewById(R.id.mul);
        sub = findViewById(R.id.sub);
        add.setOnClickListener(this);
        div.setOnClickListener(this);
        mul.setOnClickListener(this);
        sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int result = 0;
        switch (view.getId()) {
            case R.id.add:
                result = JNITools.add(a, b);
                break;
            case R.id.div:
                result = JNITools.div(a, b);
                break;
            case R.id.mul:
                result = JNITools.mul(a, b);
                break;
            case R.id.sub:
                result = JNITools.sub(a, b);
                break;
        }
        Toast.makeText(this, result + "", Toast.LENGTH_SHORT).show();
    }
}
