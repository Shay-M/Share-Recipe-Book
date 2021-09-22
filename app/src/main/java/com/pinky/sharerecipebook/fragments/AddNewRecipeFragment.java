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
import androidx.lifecycle.ViewModelProvider;

import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.AuthAppRepository;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.utils.CameraManagerUrl;
import com.pinky.sharerecipebook.viewmodels.AddRecipeViewModel;


public class AddNewRecipeFragment extends Fragment {
    final int GALLERY_REQUEST_CODE = 3;
    private EditText TitleText;
    private EditText IngredientsText;
    private EditText preparationText;
    private ImageView picContentView;
    private Uri imgUri = null;

    private EditText linkText;
    private EditText nameText;

    private FloatingActionButton floating_attach_recipe;

    private CameraManagerUrl cameraManagerUrl;

    private AddRecipeViewModel addRecipeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //addRecipeViewModel = new ViewModelProvider(this).get(AddRecipeViewModel.class);
        //addRecipeViewModel.init();

        //recipeArrayList = loadRecipeViewModel.getRecipeLiveData().getValue();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_new_recipe, container, false);

        cameraManagerUrl = CameraManagerUrl.getInstance();

        TitleText = view.findViewById(R.id.fragment_add_recipe_title);
        IngredientsText = view.findViewById(R.id.fragment_add_recipe_ingredients);
        preparationText = view.findViewById(R.id.fragment_add_recipe_preparation);
        picContentView = view.findViewById(R.id.fragment_show_recipe_image);
        floating_attach_recipe = view.findViewById(R.id.fragment_add_floating_attach_new_recipe);

        new Boom(floating_attach_recipe);


        ImageView takeApicBtn = view.findViewById(R.id.take_a_pic);
        ImageView galleriaPicBtn = view.findViewById(R.id.add_a_pic);

        //From Camera Button
        new Boom(takeApicBtn);
        takeApicBtn.setOnClickListener(v -> takeApicFromCamera());

        //From Galleria Button
        new Boom(galleriaPicBtn);
        galleriaPicBtn.setOnClickListener(v -> picFromGalleria());


        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




       /* floating_attach_recipe.setOnClickListener(v -> {
            Log.d("onViewCreated", "floating_attach_recipe: " + AuthAppRepository.getInstance().getCurrentUser());
            Recipe tempRecipe = new Recipe(
                    "123",
                    TitleText.getText().toString(),
                    IngredientsText.getText().toString(),
                    preparationText.getText().toString(),
                    AuthAppRepository.getInstance().getCurrentUser(),
                    imgUri.toString()

                    );
            addRecipeViewModel.AttachNewRecipe(tempRecipe);
        });*/


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

        Glide.with(this).load(imgUri).centerCrop().thumbnail(0.15f).into(picContentView);
    }

}