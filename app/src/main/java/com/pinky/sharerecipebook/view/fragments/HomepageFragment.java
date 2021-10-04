package com.pinky.sharerecipebook.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

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
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.AuthRepository;
import com.pinky.sharerecipebook.viewmodels.HomeViewModel;

import java.util.ArrayList;


public class HomepageFragment extends Fragment implements RecipeAdapter.RecyclerViewListener {

    User loginUser = new User();
    private ArrayList<Recipe> recipeArrayList;
    private RecipeAdapter recipeAdapter;
    private FloatingActionButton floatingAddButton;
    private ChipNavigationBar chipNavigationBar;
    private HomeViewModel homeViewModel;


    // top bar
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);

        //inflater = getMenuInflater();
        inflater.inflate(R.menu.top_bar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recipeAdapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivity main = (MainActivity) getContext();

        recipeArrayList = new ArrayList<>();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.init();
        recipeArrayList = homeViewModel.getRecipeLiveData().getValue();

        homeViewModel.inituserLogin();
        loginUser = homeViewModel.getUserLiveData().getValue();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_of_recipe);
        recyclerView.setHasFixedSize(true);

        recipeAdapter = new RecipeAdapter(recipeArrayList, getActivity());
        recipeAdapter.setClicksListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recipeAdapter);

        // update
        homeViewModel.getRecipeLiveData().observe(getViewLifecycleOwner(),
                new Observer<ArrayList<Recipe>>() {
                    @Override
                    public void onChanged(ArrayList<Recipe> recipes) {
                        recipeAdapter.notifyDataSetChanged(); // see if can be changed
                        Log.d("loadRecipeViewModel", "onChanged: !!" + recipes);
                        Log.d("loadRecipeViewModel", "recipeArrayList:" + recipeArrayList);
                        //recipeArrayList = recipes;
                    }
                });
        // get login user
        if (AuthRepository.getInstance().getCurrentUser() != null) {
            homeViewModel.userLoginliveData.observe(getViewLifecycleOwner(),
                    new Observer<User>() {
                        @Override
                        public void onChanged(User user) {
                            Log.d("if-loginUser", "onChanged: " + user.getName());
                            loginUser = user;
                        }
                    });
        }


////////////////////////////////////
        //chipNavigationBar
        chipNavigationBar = view.findViewById(R.id.chipnavigation_bar);
        chipNavigationBar.setItemSelected(R.id.home, true);

        // select a tab
        chipNavigationBar.setOnItemSelectedListener(v -> {

            Log.d("TAG", "onCreateView: " + v);
        });
/////////////////////////////////////

        // floatingAddButton
        floatingAddButton = view.findViewById(R.id.floating_add_fragment);
        new Boom(floatingAddButton);

        floatingAddButton.setOnClickListener(v -> {
            if (AuthRepository.getInstance().getCurrentUser() != null) {
                Navigation.findNavController(v).navigate(R.id.action_homepageFragment_to_addNewRecipeFragment);
            } else
                Navigation.findNavController(v).navigate(R.id.action_homepageFragment_to_loginPageFragment);
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        setHasOptionsMenu(true);
        return rootView;
    }

    // https://www.geeksforgeeks.org/shared-element-transition-in-android-with-example/ // todo

    // Click on a item
    @Override
    public void onItemClick(int position, View view) {

        Recipe recipe = recipeArrayList.get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("expandRecipe", recipe);
        bundle.putSerializable("expandLoginUser", loginUser);


        Navigation.findNavController(view).navigate(R.id.action_homepageFragment_to_recipeDetailsFragment, bundle);
    }

    // Click on a rating in item
    @Override
    public void onImgRatingClick(int position, View view) {
        Log.d("TAG", "onImgRatingClick: ");

    }

}
