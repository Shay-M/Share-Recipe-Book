package com.pinky.sharerecipebook.repositories;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pinky.sharerecipebook.R;
import com.pinky.sharerecipebook.view.MainActivity;


public class MyFirebaseMessagingService extends FirebaseMessagingService{

    static MyFirebaseMessagingService INSTANCE;

    public static MyFirebaseMessagingService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MyFirebaseMessagingService();

        return INSTANCE;
    }

    final String TAG = "NotificationAdapter";
    final String CHANNEL_ID = "id_1";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            // Pending intent to bring user to the app
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);


            NotificationChannel channel = new NotificationChannel("id_1", "name_1",NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);

            builder.setContentIntent(pendingIntent)
                    .setContentTitle(remoteMessage.getData().get("title"))
                    .setContentText(remoteMessage.getData().get("message"))
                    .setSmallIcon(R.drawable.recipe_notif_icon)
                    .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(remoteMessage.getData().get("message")));

            manager.notify(1, builder.build());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
}