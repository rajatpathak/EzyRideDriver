package com.appentus.ezyridedriver.apiConnect;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.appentus.ezyridedriver.R;
import com.appentus.ezyridedriver.activities.activities.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService  {


    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        sendNotification(remoteMessage.getTtl()+"");


        Log.d("onMessageReceived: ",remoteMessage.getData().toString()+"");
//        try {
//
//                Map<String, String> data = remoteMessage.getData();
//                Log.d("onMessageReceived: ", data.toString());

//                JSONObject jsonObject = new JSONObject(data.get("result").toString());
//                if(jsonObject.getJSONObject("data")!=null){
//                    receiveData(jsonObject, jsonObject.getJSONObject("data").getString("msg"), jsonObject.getString("msgs"));
//                }else {
//                    receiveData(jsonObject, jsonObject.getString("distance"), data.get("type").toString());
//                }
//
//        } catch (JSONException e) {
//            JSONObject jsonObject = null;
//            try {
//                Map<String, String> data = remoteMessage.getData();
//                Log.d("onMessageReceived: ", data.toString());
//                jsonObject = new JSONObject(data.get("result").toString());
//
//                receiveData(jsonObject, jsonObject.getString("distance"), data.get("type").toString());
//            } catch (JSONException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        }


        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]


    // [START on_new_token]
    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("FCM Message")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
    /*Intent intent;
    String type;
    String loginSession="";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e("PVL", "MESSAGE RECEIVED!!");

        Log.e("TOKEN", "From: " + remoteMessage.getFrom());
        Log.e("TOKEN2", "Notification Message Body: " + remoteMessage.getNotification().getBody());
        try {
            loginSession=SharedPrefData.GetStringPref(MyFirebaseMessagingService.this, Constant.LOGIN_SESSION,"");
                Map<String, String> data = remoteMessage.getData();
                Log.d("onMessageReceived: ", data.toString());
                JSONObject jsonObject = new JSONObject(data.get("result").toString());
                    receiveData(jsonObject,"a message", "cab");
           } catch (JSONException e) {
            JSONObject jsonObject = null;
            try {
                Map<String, String> data = remoteMessage.getData();
                Log.d("onMessageReceived: ", data.toString());
                jsonObject = new JSONObject(data.get("result").toString());

                receiveData(jsonObject, jsonObject.getString("distance"), data.get("type").toString());
            } catch (JSONException e1) {

                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

**/

    private void receiveData(JSONObject data, String message, String title) throws JSONException {

        int code=(int)System.currentTimeMillis();
            Log.d("receiveDataaaataaa: ", "default");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "channel-02";
        String channelName = "EzyRide";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, code, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        notificationManager.notify(code, notificationBuilder.build());
    }



}