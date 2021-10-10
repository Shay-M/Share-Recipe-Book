package com.pinky.sharerecipebook.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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


        SharedPreferences darkModeOn = PreferenceManager.getDefaultSharedPreferences(this);
        boolean darkMode = darkModeOn.getBoolean("dark", false);
        Log.d("darkMode", " " + darkMode);
        if (darkMode)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        CameraManagerUrl.init(this);

    }
}