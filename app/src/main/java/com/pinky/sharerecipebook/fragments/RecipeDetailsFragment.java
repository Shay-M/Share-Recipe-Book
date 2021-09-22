package com.pinky.sharerecipebook.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.Recipe;

public class RecipeDetailsFragment extends Fragment {

    private TextView recipe_title;
    private TextView recipe_ingredients;
    private TextView recipe_preparation;
    private ImageView recipe_img_string;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_recipe_details_page, container, false);
        View view = inflater.inflate(R.layout.fragment_recipe_details_page, container, false);

        recipe_title = view.findViewById(R.id.fragment_show_recipe_title);
        recipe_ingredients = view.findViewById(R.id.fragment_show_recipe_ingredients);
        recipe_preparation = view.findViewById(R.id.fragment_show_recipe_preparation);
        recipe_img_string = view.findViewById(R.id.fragment_show_recipe_image);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Recipe recipeGet = (Recipe) requireArguments().getSerializable("expandRecipe");

        recipe_title.setText(recipeGet.getTitle());
        recipe_ingredients.setText(recipeGet.getIngredients());
        recipe_preparation.setText(recipeGet.getPreparation());

        Glide.with(this).load(recipeGet.getImagePath()).thumbnail(0.10f).centerCrop().into(recipe_img_string);


    }
}
