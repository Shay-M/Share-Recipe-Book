package com.pinky.sharerecipebook.view.fragments;
// https://www.youtube.com/watch?v=0OCmS4Ixgmw

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.pinky.sharerecipebook.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        container.setBackgroundColor(getResources().getColor(R.color.c, null));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}