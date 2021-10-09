package com.pinky.sharerecipebook.view.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.PagerAdapterDetails;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.utils.MyShimmer;
import com.pinky.sharerecipebook.view.fragments.ui.prepareAndIngredients;
import com.pinky.sharerecipebook.view.fragments.ui.uiCommentsFragment;
import com.pinky.sharerecipebook.viewmodels.RecipeDetailsViewModel;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeDetailsFragment extends Fragment {

    private final RecipeDetailsFragment mthis = this;
    private TextView recipe_title;
    private TextView recipe_details_likes_text;
    private TextView recipe_details_user_name_text;
    private ImageView recipe_img_url_string;
    private ImageView recipe_details_image_like;
    private ImageView recipe_details_image_user;
    private Boolean likeRecipe;
    private User LoginUserGet;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private String userMakeName;

    private RecipeDetailsViewModel recipeDetailsViewModel;

    final String API_TOKEN_KEY = "AAAAnhV6-hM:APA91bGf-3U4CbbpRZWDOXa_jRLp4fmGCrj8C2qdWMF7q82umHfj5-aVsJI_jj_8mGFDbyh3v_dpg_9EuMIf4ePq0aiJ7isGVbE8eiO_kxgjwC2t_HCqipD3poyvSRfOuOE0LA-M5LXq";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }

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


        recipe_img_url_string = view.findViewById(R.id.fragment_show_recipe_image);
        recipe_details_image_like = view.findViewById(R.id.frag_recipe_details_image_like);
        recipe_details_image_user = view.findViewById(R.id.frag_recipe_details_image_user);

        tabLayout = view.findViewById(R.id.frag_recipe_details_tabLayout);
        viewPager2 = view.findViewById(R.id.frag_recipe_details_view_Pager);

        //tabLayout.setupWithViewPager(viewPager2);


        new Boom(recipe_details_image_like);
        new Boom(recipe_details_image_user);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Recipe recipeGet = (Recipe) requireArguments().getSerializable("expandRecipe");
        LoginUserGet = (User) requireArguments().getSerializable("expandLoginUser");
//        recipeDetailsViewModel.setRecipe(recipeGet); todo?

        recipeDetailsViewModel.getUserLiveData(recipeGet.getFirebaseUserIdMade()).getValue();

        // update  //user load
        recipeDetailsViewModel
                .getUserLiveData(recipeGet.getFirebaseUserIdMade())
                .observe(getViewLifecycleOwner(),
                        new Observer<User>() {
                            @Override
                            public void onChanged(User user) {
                                userMakeName = user.getName();
                                recipe_details_user_name_text.setText(userMakeName);

                                Glide.with(mthis)
                                        .load(user.getUserImagePath())
                                        .circleCrop()
                                        .error(R.drawable.ic_twotone_person_outline_24)
                                        .into(recipe_details_image_user);

                            }
                        });

        recipe_title.setText(recipeGet.getTitle());


        // set up img like
        recipe_details_likes_text.setText(String.valueOf(recipeGet.getRank()));
        if (!LoginUserGet.getName().equals("Guest")) // todo
            if (LoginUserGet.getFavoriteRecipe().contains(recipeGet.getRecipeId())) {
                likeRecipe = true;
                recipe_details_image_like.setImageResource(R.drawable.ic_baseline_favorite_48);
            }

        // load recipe img
        Glide.with(this)
                .load(recipeGet.getImagePath())
                .placeholder(MyShimmer.getShimmer())
                .centerCrop()
                .error(android.R.drawable.ic_dialog_info)
                .into(recipe_img_url_string);

        // like recipe
        recipe_details_image_like.setOnClickListener(v -> {
            if (!LoginUserGet.getName().equals("Guest")) {
                int numOfFavorites = Integer.parseInt(recipe_details_likes_text.getText().toString());
                int tempLike = 0;

                if (!likeRecipe) {
                    recipe_details_image_like.setImageResource(R.drawable.ic_baseline_favorite_48);
                    likeRecipe = true;
                    LoginUserGet.addFavoriteRecipe(recipeGet.getRecipeId());
                    tempLike = 1;

                    if (LoginUserGet.getFirebaseUserId().equals(recipeGet.getFirebaseUserIdMade())) {
                        sendLikeNotification(LoginUserGet.getName(),
                                recipeGet.getFirebaseDeviceTokenMade(),
                                recipeGet.getTitle());
                    }
                } else {
                    recipe_details_image_like.setImageResource(R.drawable.ic_twotone_favorite_48);
                    likeRecipe = false;
                    LoginUserGet.removeFavoriteRecipe(recipeGet.getRecipeId());
                    if (numOfFavorites != 0)
                        tempLike = -1;

                }
                numOfFavorites = numOfFavorites + tempLike;
                recipe_details_likes_text.setText(Integer.toString(numOfFavorites));

                // change like to Recipe
                recipeDetailsViewModel.changeLikeToRecipe(recipeGet.getRecipeId(), numOfFavorites);

                // change like in user
                recipeDetailsViewModel.addIdLikeToUser(LoginUserGet.getFirebaseUserId(), LoginUserGet.getFavoriteRecipe());

            } else {
                // go to login?
            }
        });

        recipe_details_image_user.setOnClickListener(v -> { // todo
            /*Bundle bundle = new Bundle();
            bundle.putSerializable("expanduserId", recipeGet.getFirebaseUserIdMade());
            bundle.putSerializable("expanduserMakeName",  userMakeName);*/
            // RecipeDetailsFragmentDirections.actionRecipeDetailsFragmentToHomepageFragment(recipeGet.getFirebaseUserIdMade());
            //Navigation.findNavController(view).navigate(R.id.action_recipeDetailsFragment_to_homepageFragment, bundle);

        });

        //  pager Adapter
        FragmentManager fragmentManager = getParentFragmentManager();

        PagerAdapterDetails pagerAdapter = new PagerAdapterDetails(fragmentManager, getLifecycle());

        // adding fragments
        pagerAdapter.addFragment(new prepareAndIngredients(recipeGet.getIngredients()));
        pagerAdapter.addFragment(new prepareAndIngredients(recipeGet.getPreparation()));

        //Aaron
        pagerAdapter.addFragment(new uiCommentsFragment(recipeGet.getRecipeId(),
                recipeGet.getCommentArrayListHashMap(),
                LoginUserGet.getUserImagePath(),
                LoginUserGet.getName(),
                recipeGet.getFirebaseDeviceTokenMade(),
                recipeGet.getTitle()));

        viewPager2.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // update tabLayout
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
                super.onPageSelected(position);
            }
        });
    }

    public String sendLikeNotification (String senderName, String ownerToken, String recipeName)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Authorization", "key=" + API_TOKEN_KEY);
                    conn.setDoOutput(true);

                    String message = senderName + " has liked your " + recipeName + " recipe.";
                    final JSONObject rootObject = new JSONObject();
                    rootObject.put("to", ownerToken);
                    rootObject.put("data", new JSONObject().put("message", message).put("title", "You've got a like!"));
                    rootObject.put("priority", "high");

                    OutputStream os = conn.getOutputStream();
                    os.write(rootObject.toString().getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    System.out.println("\nSending 'POST' request to URL : " + url);
                    System.out.println("Post parameters : " + rootObject.toString());
                    System.out.println("Response Code : " + responseCode);
                    System.out.println("Response Code : " + conn.getResponseMessage());

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // print result
                    System.out.println(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        AsyncTask.execute(runnable);
        return "ok";
    }
}