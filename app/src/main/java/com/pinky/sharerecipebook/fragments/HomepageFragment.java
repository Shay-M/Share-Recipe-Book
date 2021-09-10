package com.pinky.sharerecipebook.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.RecipeAdapter;
import com.pinky.sharerecipebook.models.Recipe;

import java.util.ArrayList;


public class HomepageFragment extends Fragment implements RecipeAdapter.RecyclerViewListener {

    private ArrayList<Recipe> recipeArrayList;
    private RecipeAdapter recipeAdapter;
    private FloatingActionButton floatingAddButton;

    private ChipNavigationBar chipNavigationBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        recipeArrayList = new ArrayList<>();

        //demo
        Recipe temp = new Recipe("banana", "eat the banana", "1 banana", 4.3f);
        for (int i = 0; i < 10; i++) {
            recipeArrayList.add(temp);
        }

        recipeAdapter = new RecipeAdapter(recipeArrayList, getActivity());
        recipeAdapter.setClicksListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);


        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view_of_recipe);
        //recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(recipeAdapter);

        //
        chipNavigationBar = rootView.findViewById(R.id.chipnavigation_bar);
        chipNavigationBar.setItemSelected(R.id.home, true);

        chipNavigationBar.setOnItemSelectedListener(v -> {

            Log.d("TAG", "onCreateView: " + v);
        });
        //
        floatingAddButton = rootView.findViewById(R.id.floatingAddButton);

        floatingAddButton.setOnClickListener(view -> {
//                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Navigation.findNavController(view).navigate(R.id.action_homepageFragment_to_addNewRecipeFragment);
        });

        //

        return rootView;


    }

    // https://www.geeksforgeeks.org/shared-element-transition-in-android-with-example/ //todo

    @Override
    public void onItemClick(int position, View view) {
        Log.d("TAG", "onItemClick: ");

        Recipe recipe = recipeArrayList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("expandRecipe", recipe);

        Navigation.findNavController(view).navigate(R.id.action_homepageFragment_to_recipeDetailsFragment, bundle);
    }

    @Override
    public void onImgRatingClick(int position, View view) {
        Log.d("TAG", "onImgRatingClick: ");

    }
}

