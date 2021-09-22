package com.pinky.sharerecipebook.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astritveliu.boom.Boom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.RecipeAdapter;
import com.pinky.sharerecipebook.models.AuthAppRepository;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.viewmodels.LoadRecipeViewModel;

import java.util.ArrayList;


public class HomepageFragment extends Fragment implements RecipeAdapter.RecyclerViewListener {

    private ArrayList<Recipe> recipeArrayList;
    private RecipeAdapter recipeAdapter;
    private FloatingActionButton floatingAddButton;

    private ChipNavigationBar chipNavigationBar;

    private LoadRecipeViewModel loadRecipeViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivity main = (MainActivity) getContext();

        recipeArrayList = new ArrayList<>();

        loadRecipeViewModel = new ViewModelProvider(this).get(LoadRecipeViewModel.class);
        loadRecipeViewModel.init();

        recipeArrayList = loadRecipeViewModel.getRecipeLiveData().getValue();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_of_recipe);
        recyclerView.setHasFixedSize(true);

        recipeAdapter = new RecipeAdapter(recipeArrayList, getActivity());

        /*//demo
        Recipe temp = new Recipe("banana", "eat the banana", "1 banana", 4.3f);
        for (int i = 0; i < 10; i++) {
            recipeArrayList.add(temp);
        }
        recipeAdapter = new RecipeAdapter(recipeArrayList, getActivity());*/

        recipeAdapter.setClicksListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recipeAdapter);

//
        loadRecipeViewModel.getRecipeLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Recipe>>() {
            @Override
            public void onChanged(ArrayList<Recipe> recipes) {
               recipeAdapter.notifyDataSetChanged(); // see if can be changed
                Log.d("loadRecipeViewModel", "onChanged: !!" + recipes);
                //recipeArrayList = recipes;
            }
        });
//

        //chipNavigationBar
        chipNavigationBar = view.findViewById(R.id.chipnavigation_bar);
        chipNavigationBar.setItemSelected(R.id.home, true);

        // select a tab
        chipNavigationBar.setOnItemSelectedListener(v -> {

            Log.d("TAG", "onCreateView: " + v);
        });

        // floatingAddButton
        floatingAddButton = view.findViewById(R.id.floating_add_fragment);
        new Boom(floatingAddButton);

        floatingAddButton.setOnClickListener(v -> {
            if (AuthAppRepository.getInstance().getCurrentUser() != null) {
                Log.d("onCreateView", "onCreateView: " + AuthAppRepository.getInstance().getCurrentUser());
                Navigation.findNavController(v).navigate(R.id.action_homepageFragment_to_addNewRecipeFragment);
            } else
                Navigation.findNavController(v).navigate(R.id.action_addNewRecipeFragment_to_homepageFragment);
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        return rootView;
    }

    // https://www.geeksforgeeks.org/shared-element-transition-in-android-with-example/ //todo
    // Click on a item
    @Override
    public void onItemClick(int position, View view) {
        Log.d("TAG", "onItemClick: ");

        Recipe recipe = recipeArrayList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("expandRecipe", recipe);

        Navigation.findNavController(view).navigate(R.id.action_homepageFragment_to_recipeDetailsFragment, bundle);
    }

    // Click on a rating in item
    @Override
    public void onImgRatingClick(int position, View view) {
        Log.d("TAG", "onImgRatingClick: ");

    }

}
