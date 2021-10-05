package com.pinky.sharerecipebook.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.pinky.sharerecipebook.R;

public class UserProfileFragment extends Fragment {
    //((AppCompatActivity) getActivity()).getSupportActionBar();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Register");


//        registerButton = view.findViewById(R.id.fragment_register_register_button);
        return view;
    }
}