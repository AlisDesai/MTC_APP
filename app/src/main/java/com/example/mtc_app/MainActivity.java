package com.example.mtc_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mtc_app.customerRepresentative.CrMain;
import com.example.mtc_app.splashScreen.SplashScreenActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, CrMain.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}