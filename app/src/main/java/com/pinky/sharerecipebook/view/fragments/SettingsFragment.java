package com.pinky.sharerecipebook.view.fragments;
// https://www.youtube.com/watch?v=0OCmS4Ixgmw

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.pinky.sharerecipebook.R;

public class SettingsFragment extends PreferenceFragmentCompat {
//    private ListPreference mListPreference;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //container.setBackgroundColor(getResources().getColor(R.color.b, null));
        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference preference = findPreference("dark");
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Log.d("onPreferenceChange", "newValue: " + newValue);

                if (newValue.equals("true"))
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                return true;
            }
        });


    }


}