package com.pinky.sharerecipebook.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;


public class AddNewRecipeFragment extends Fragment {
    final int GALLERY_REQUEST_CODE = 3;
    private EditText TitleText;
    private EditText IngredientsText;
    private EditText preparationText;
    private ImageView picContentView;
    private Uri imgUri = null;

    private CameraManagerUrl cameraManagerUrl;

    //private AddSongDialogListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_recipe, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setHasOptionsMenu(true);


        cameraManagerUrl = CameraManagerUrl.getInstance();

        initializationViews(view);
        //initializationListeners();
        //populateViews();
    }

    private void initializationViews(View view) {
//        TitleText = view.findViewById(R.id.add_recipe_title);
//        IngredientsText = view.findViewById(R.id.add_recipe_ingredients);
//        preparationText = view.findViewById(R.id.add_recipe_preparation);
//        picContentView = view.findViewById(R.id.add_recipe_image);

    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/
}