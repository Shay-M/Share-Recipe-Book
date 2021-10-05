package com.pinky.sharerecipebook.view;

import android.os.Bundle;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraManagerUrl.init(this);

    }
}