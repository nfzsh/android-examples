package com.example.myexample01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.security.PrivateKey;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "SecActivity";

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.i(TAG, "SecActivity onCreate()");
        String value = getIntent().getStringExtra("value");
        textView = findViewById(R.id.textView);
        textView.setText(value);
    }
}
