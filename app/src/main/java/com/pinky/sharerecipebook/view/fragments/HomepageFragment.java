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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astritveliu.boom.Boom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.RecipeAdapter;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.repositories.AuthRepository;
import com.pinky.sharerecipebook.viewmodels.HomeViewModel;
import com.pinky.sharerecipebook.viewmodels.LogOutViewModel;

import java.util.ArrayList;


public class HomepageFragment extends Fragment implements RecipeAdapter.RecyclerViewListener {

    User loginUser = new User();
    private ArrayList<Recipe> recipeArrayList;
    private RecipeAdapter recipeAdapter;
    private FloatingActionButton floatingAddButton;
    private ActionBar actionBar;// = ((AppCompatActivity) getActivity()).getSupportActionBar();

    private HomeViewModel homeViewModel;
    private LogOutViewModel logOutViewModel;

    // top bar search View
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        inflater.inflate(R.menu.top_bar, menu);
        /*CheckBox checkBox = (CheckBox) menu.findItem(R.id.action_favorites).getActionView();
        checkBox.setChecked(false);
        checkBox.setButtonDrawable(R.drawable.ic_twotone_favorite_48);*/

        // search
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

    //call back user selectd item
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d("action", "item: " + item);

        switch (item.getItemId()) {
            case R.id.action_settings:
                //  todo User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorites:
                // User chose the "Favorite" action, mark the current item
                if (loginUser.getName() != "Guest") {

                    if (item.isChecked()) {
                        recipeAdapter.getFilter().filter(null);
                        item.setIcon(R.drawable.ic_twotone_favorite_48);
                        actionBar.setTitle("");
                        item.setChecked(false);
                    } else {
                        recipeAdapter.getFilter().filter(loginUser.getFavoriteRecipe().get(0)); // todo
//                    recipeAdapter.getFilter().filter(loginUser.getFavoriteRecipe().get(0)); // todo
                        item.setIcon(R.drawable.ic_baseline_favorite_48);
                        actionBar.setTitle("Your Favorite Recipe");
                        item.setChecked(true);
                    }
                } // todo go to login

                return true;

            case R.id.action_logout:
                if (loginUser.getName() != "Guest") {
                    Log.d("action_logout", "onOptionsItemSelected: ");
                    Snackbar.make(this.getView(), R.string.msg_logout, BaseTransientBottomBar.LENGTH_LONG).show();
                    logOutViewModel.logOut();
                } else
                    Snackbar.make(this.getView(), R.string.msg_logout, BaseTransientBottomBar.LENGTH_LONG).show();


                return true;

            case R.id.action_my_recipes:
                if (loginUser.getName() != "Guest") {
                    if (item.isChecked()) {
                        recipeAdapter.getFilter().filter(null);
                        actionBar.setTitle("");
                        item.setChecked(false);
                    } else {
                        recipeAdapter.getFilter().filter("#my" + loginUser.getFirebaseUserId());
                        actionBar.setTitle("Your Recipe");
                        item.setChecked(true);
                    }
                }
                return true;

            default:
                recipeAdapter.getFilter().filter(null);
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivity main = (MainActivity) getContext();

        recipeArrayList = new ArrayList<>();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.init();
        recipeArrayList = homeViewModel.getRecipeLiveData().getValue();

        homeViewModel.initUserLogin();
        loginUser = homeViewModel.getUserLiveData().getValue();
        ////

        logOutViewModel = new ViewModelProvider(this).get(LogOutViewModel.class);

        //logOutViewModel.init();
        //recipeArrayList = homeViewModel.getRecipeLiveData().getValue();

        //homeViewModel.initUserLogin();
        //loginUser = homeViewModel.getUserLiveData().getValue();


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
                        recipeArrayList = recipes;
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
