        package com.pinky.sharerecipebook.adapters;

        import android.app.Notification;
        import android.app.NotificationChannel;
        import android.app.NotificationManager;
        import android.content.BroadcastReceiver;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Build;
        import android.text.InputFilter;
        import android.util.Log;

        import androidx.annotation.NonNull;
        import androidx.localbroadcastmanager.content.LocalBroadcastManager;

        import com.google.firebase.messaging.FirebaseMessagingService;
        import com.google.firebase.messaging.RemoteMessage;

public class NotificationAdapter extends FirebaseMessagingService {

//    final String TAG = "NotificationAdapter";
//
//    @Override
//    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//
//        Log.d(TAG, "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
//            Intent intent = new Intent("message received");
//            intent.putExtra("message", remoteMessage.getData().get("message"));
//            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//            Notification.Builder builder = new Notification.Builder(this);
//
//
//            if(Build.VERSION.SDK_INT>=26){
//                NotificationChannel channel = new NotificationChannel("id_1", "name_1",NotificationManager.IMPORTANCE_HIGH);
//                manager.createNotificationChannel(channel);
//                builder.setChannelId("id_1");
//            }
//            builder.setContentTitle("new message from").setContentText(remoteMessage.getData().get("message")).setSmallIcon(android.R.drawable.btn_star);
//            manager.notify(1, builder.build());
//        }
//
//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//        }
//
//        // Also if you intend on generating your own notifications as a result of a received FCM
//        // message, here is where that should be initiated. See sendNotification method below.
//    }

}