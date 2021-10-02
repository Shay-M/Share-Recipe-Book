package com.pinky.sharerecipebook.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CameraManagerUrl.init(this);

    }
}