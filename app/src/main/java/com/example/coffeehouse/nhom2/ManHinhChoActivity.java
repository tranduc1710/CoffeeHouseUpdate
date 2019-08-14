package com.example.coffeehouse.nhom2;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ManHinhChoActivity extends AppCompatActivity {
    private static int OK = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_cho);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ManHinhChoActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },OK);
    }
}
