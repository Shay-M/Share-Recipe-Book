package com.pinky.sharerecipebook.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.viewmodels.RecipeDetailsViewModel;

public class RecipeDetailsFragment extends Fragment {

    private final RecipeDetailsFragment mthis = this;
    private TextView recipe_title;
    private TextView recipe_ingredients;
    private TextView recipe_preparation;
    private TextView recipe_details_likes_text;
    private TextView recipe_details_user_name_text;
    private ImageView recipe_img_string;
    private ImageView recipe_details_image_like;
    private ImageView recipe_details_image_user;
    private RecipeDetailsViewModel recipeDetailsViewModel;
    private Boolean likeRecipe;
    private User userLogin;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        likeRecipe = false;
        recipeDetailsViewModel = new ViewModelProvider(this).get(RecipeDetailsViewModel.class);
        recipeDetailsViewModel.init();

        //add all live? todo


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_recipe_details_page, container, false);
        View view = inflater.inflate(R.layout.fragment_recipe_details_page, container, false);

        recipe_title = view.findViewById(R.id.frag_recipe_details_title_text);
        recipe_details_user_name_text = view.findViewById(R.id.frag_recipe_details_user_name_text);
        recipe_details_likes_text = view.findViewById(R.id.frag_recipe_details_likes_text);

        recipe_ingredients = view.findViewById(R.id.fragment_show_recipe_ingredients);
        recipe_preparation = view.findViewById(R.id.fragment_show_recipe_preparation);
        recipe_img_string = view.findViewById(R.id.fragment_show_recipe_image);
        recipe_details_image_like = view.findViewById(R.id.frag_recipe_details_image_like);
        recipe_details_image_user = view.findViewById(R.id.frag_recipe_details_image_user);

        new Boom(recipe_details_image_like);
        new Boom(recipe_details_image_user);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Recipe recipeGet = (Recipe) requireArguments().getSerializable("expandRecipe");
        User LoginUserGet = (User) requireArguments().getSerializable("expandLoginUser");
//        Log.d("UserGet", "onViewCreated: " + UserGet.getName());
//        recipeDetailsViewModel.setRecipe(recipeGet); todo?

        recipeDetailsViewModel.getUserLiveData(recipeGet.getFirebaseUserIdMade()).getValue();

        // update  //user load
        recipeDetailsViewModel
                .getUserLiveData(recipeGet.getFirebaseUserIdMade())
                .observe(getViewLifecycleOwner(),
                        new Observer<User>() {
                            @Override
                            public void onChanged(User user) {
                                recipe_details_user_name_text.setText(user.getName());

                                Glide.with(mthis)
                                        .load(user.getUserImagePath())
                                        .circleCrop()
                                        .error(R.drawable.ic_twotone_person_outline_24)
                                        .into(recipe_details_image_user);

                            }
                        });

        recipe_title.setText(recipeGet.getTitle());
        recipe_ingredients.setText(recipeGet.getIngredients());
        recipe_preparation.setText(recipeGet.getPreparation());

        recipe_details_likes_text.setText(String.valueOf(recipeGet.getRank()));
        if (!LoginUserGet.getName().equals("Guest"))
            if (LoginUserGet.getFavoriteRecipe().contains(recipeGet.getRecipeId())) {
                likeRecipe = true;
                recipe_details_image_like.setImageResource(R.drawable.ic_baseline_favorite_48);
            }

        // load recipe img
        Glide.with(this)
                .load(recipeGet.getImagePath())
                //.thumbnail(1f)
//                .placeholder(R.drawable.gallery)
                .centerCrop()
                .error(android.R.drawable.ic_dialog_info)
                .into(recipe_img_string);

        // like recipe
        recipe_details_image_like.setOnClickListener(v -> {
            if (!LoginUserGet.getName().equals("Guest")) {
                int numOfFavorites = Integer.parseInt(recipe_details_likes_text.getText().toString());
                int tempLike = 0;

                if (!likeRecipe) {
                    recipe_details_image_like.setImageResource(R.drawable.ic_baseline_favorite_48);
                    likeRecipe = true;
                    tempLike = 1;
                } else {
                    recipe_details_image_like.setImageResource(R.drawable.ic_twotone_favorite_48);
                    likeRecipe = false;
                    if (numOfFavorites != 0)
                        tempLike = -1;

                }
                numOfFavorites = numOfFavorites + tempLike;
                recipe_details_likes_text.setText(Integer.toString(numOfFavorites));

                recipeDetailsViewModel.changeLikeToRecipe(recipeGet.getRecipeId(), numOfFavorites);
            } else {
                // go to login?
            }
        });




       /* recipeDetailsViewModel
                .getCurrentUserLiveData()
                .observe(getViewLifecycleOwner(),
                        new Observer<User>() {
                            @Override
                            public void onChanged(User user) {

                                recipe_details_image_like.setOnClickListener(v -> {
                                 *//*int numOfFavorites = Integer.parseInt(recipe_details_likes_text.getText().toString());
                                    numOfFavorites = numOfFavorites + 1;
                                    recipe_details_likes_text.setText(String.valueOf(numOfFavorites));*//*
                                    Log.d("userGet", "onChanged: "+user);

                                    //recipeDetailsViewModel.userLikedThisRecipe(recipeGet.getRecipeId());

                                });

                            }
                        });*/


    }
}
