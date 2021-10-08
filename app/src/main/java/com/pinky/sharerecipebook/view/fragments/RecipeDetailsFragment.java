package com.pinky.sharerecipebook.view.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.astritveliu.boom.Boom;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.CommentAdapter;
import com.pinky.sharerecipebook.models.Comment;
import com.pinky.sharerecipebook.models.Recipe;
import com.pinky.sharerecipebook.models.User;
import com.pinky.sharerecipebook.viewmodels.RecipeDetailsViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private User LoginUserGet;

    final String API_TOKEN_KEY = "AAAAnhV6-hM:APA91bGf-3U4CbbpRZWDOXa_jRLp4fmGCrj8C2qdWMF7q82umHfj5-aVsJI_jj_8mGFDbyh3v_dpg_9EuMIf4ePq0aiJ7isGVbE8eiO_kxgjwC2t_HCqipD3poyvSRfOuOE0LA-M5LXq";
    FirebaseMessaging messaging = FirebaseMessaging.getInstance();
    BroadcastReceiver receiver;

    private ArrayList<Comment> commentArrayList;
    private CommentAdapter commentAdapter;

    private Map<String, Comment> commentHashMap;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        likeRecipe = false;
        recipeDetailsViewModel = new ViewModelProvider(this).get(RecipeDetailsViewModel.class);
        recipeDetailsViewModel.init();

        //add all live? todo

        commentArrayList = new ArrayList<>();

        commentHashMap = new HashMap<>();


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
        LoginUserGet = (User) requireArguments().getSerializable("expandLoginUser");
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
        commentArrayList = recipeGet.getCommentArrayList();
        commentHashMap = recipeGet.getCommentArrayListHashMap();

        if (commentArrayList == null)
            commentArrayList = new ArrayList<>();

        // set up img like
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
                    LoginUserGet.addFavoriteRecipe(recipeGet.getRecipeId());
                    tempLike = 1;
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
                // notification to user
                sendLikeNotification(LoginUserGet.getName(),
                        "deLzdVdlR76RqieFtWI0ry:APA91bHY5nliOWp-j0ZK9PFWdVU0g2MKXDM6Ye6DF0s1OwF0gHd3oeCxINkJhhsnGwKPoRy8T-kbPSdG3ESMfXc7vCyS0_xJ9Sl8XRmMuC22fNJr_t6L7YEuvJKMR6ojP5SYC97qYP4L",
                        recipeGet.getTitle());

            } else {
                // go to login?
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_of_comments);
        recyclerView.setHasFixedSize(true);

//        commentAdapter = new CommentAdapter(commentArrayList, getActivity());
//        commentAdapter = new CommentAdapter(commentHashMap, getActivity());
//        commentAdapter.setClicksListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(commentAdapter);
    }


    public String sendLikeNotification (String SenderName, String ownerToken, String recipeName)  {
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

                    String message = SenderName + " has liked your " + recipeName + " recipe.";
                    final JSONObject rootObject  = new JSONObject();
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
