package com.pinky.sharerecipebook.repositories;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pinky.sharerecipebook.view.MainActivity;


public class MyFirebaseMessagingService extends FirebaseMessagingService{

    static MyFirebaseMessagingService INSTANCE;

    public static MyFirebaseMessagingService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyFirebaseMessagingService();

        return INSTANCE;
    }

    final String TAG = "NotificationAdapter";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(this);

            // Pending intent to bring user to the app
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            if(Build.VERSION.SDK_INT>=26){
                NotificationChannel channel = new NotificationChannel("id_1", "name_1",NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                builder.setChannelId("id_1").setContentIntent(pendingIntent);
            }
            builder.setContentTitle(remoteMessage.getData().get("title")).setContentText(remoteMessage.getData().get("message")).setSmallIcon(android.R.drawable.btn_star);
            manager.notify(1, builder.build());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}