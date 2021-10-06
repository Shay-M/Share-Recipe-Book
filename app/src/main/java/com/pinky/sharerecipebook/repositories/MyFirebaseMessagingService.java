package com.pinky.sharerecipebook.repositories;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.adapters.RecipeAdapter;
import com.pinky.sharerecipebook.repositories.MyFirebaseMessagingService;
import com.pinky.sharerecipebook.view.fragments.RecipeDetailsFragment;
import com.pinky.sharerecipebook.viewmodels.RecipeDetailsViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    final String API_TOKEN_KEY = "AAAAnhV6-hM:APA91bGf-3U4CbbpRZWDOXa_jRLp4fmGCrj8C2qdWMF7q82umHfj5-aVsJI_jj_8mGFDbyh3v_dpg_9EuMIf4ePq0aiJ7isGVbE8eiO_kxgjwC2t_HCqipD3poyvSRfOuOE0LA-M5LXq";
    FirebaseMessaging messaging = FirebaseMessaging.getInstance();
    BroadcastReceiver receiver;

    static MyFirebaseMessagingService INSTANCE;

    public static MyFirebaseMessagingService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyFirebaseMessagingService();

        return INSTANCE;
    }

    public void changeDataFirebaseCommentArrayList(String SenderId, String ownerId, String recipeName, String folder, String fildeToChange){
        try {
            String message = "Banana Give like To" + recipeName + "recipe";
            final JSONObject rootObject  = new JSONObject();
            rootObject.put("to", ownerId);
            rootObject.put("data", new JSONObject().put("message", message));

            String url = "https://fcm.googleapis.com/fcm/send";

            RequestQueue queue = Volley.newRequestQueue(MyFirebaseMessagingService.this);
            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Authorization", "key=" + API_TOKEN_KEY);
                    return headers;
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    return rootObject.toString().getBytes();
                }
            };
            queue.add(request);
            queue.start();

        }catch (JSONException ex) {
            ex.printStackTrace();
        } ;

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

              // messageTv.setText(intent.getStringExtra("message"));
            }
        };
        IntentFilter filter = new IntentFilter("message_received");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);

        }
//        @Override
//        protected void onDestroy() {
//        super.onDestroy();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
//        }
}