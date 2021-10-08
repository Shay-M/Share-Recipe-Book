package com.pinky.sharerecipebook.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;

public class MainActivity extends AppCompatActivity {

    /*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MysplashScreen);
        getSupportActionBar().setTitle(""); // show user name or not?

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);*/

        CameraManagerUrl.init(this);

    }
}