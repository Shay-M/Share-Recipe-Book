package com.pinky.sharerecipebook.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;


public class AddNewRecipeFragment extends Fragment {
    final int GALLERY_REQUEST_CODE = 3;
    private EditText TitleText;
    private EditText IngredientsText;
    private EditText preparationText;
    private ImageView picContentView;
    private Uri imgUri = null;

    private EditText linkText;
    private EditText nameText;


    private CameraManagerUrl cameraManagerUrl;

    //private AddSongDialogListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_recipe, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        picContentView = view.findViewById(R.id.add_recipe_image);

        ImageView takeApicBtn = view.findViewById(R.id.take_a_pic);
        ImageView galleriaPicBtn = view.findViewById(R.id.add_a_pic);

        //From Camera Button
        new Boom(takeApicBtn);
        takeApicBtn.setOnClickListener(v -> takeApicFromCamera());

        //From Galleria Button
        new Boom(galleriaPicBtn);
        galleriaPicBtn.setOnClickListener(v -> picFromGalleria());


        cameraManagerUrl = CameraManagerUrl.getInstance();

        //initializationViews(view);
        //initializationListeners();
        //populateViews();
    }

    private void picFromGalleria() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    //Galleria Result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST_CODE) {
            Log.d("onActivityResult", "Intent: " + data);
            /*if (data != null) {
                imgUri = data.getData();
                Glide.with(this).load(imgUri).centerCrop().thumbnail(0.10f).into(picContentView);
            }*/
            try {
                imgUri = data.getData();
                Glide.with(this).load(imgUri).centerCrop().thumbnail(0.10f).into(picContentView);
            } catch (Exception e) {
                Log.d("picFromGalleria", "onActivityResult: " + e.getMessage());
            }

        }

    }

    private void takeApicFromCamera() {
        imgUri = cameraManagerUrl.dispatchTakePictureIntent();
        Log.d("takeApicFromCamera", "imgUri: " + imgUri);

        Glide.with(this).load(imgUri).centerCrop().thumbnail(0.10f).into(picContentView);
    }

}