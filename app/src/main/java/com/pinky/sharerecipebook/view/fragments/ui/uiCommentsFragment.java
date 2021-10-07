package com.pinky.sharerecipebook.view.fragments.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pinky.sharerecipebook.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link uiCommentsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class uiCommentsFragment extends Fragment {



    public uiCommentsFragment() {
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ui_comments, container, false);
    }
}