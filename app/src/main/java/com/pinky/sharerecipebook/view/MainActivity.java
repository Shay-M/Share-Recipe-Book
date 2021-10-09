package com.pinky.sharerecipebook.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

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


        SharedPreferences banana = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("len", "onCreate: " + banana.getString("language", "iw"));

        CameraManagerUrl.init(this);

    }
}