package com.ruirua.sampleguideapp.notifications;



import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.ruirua.sampleguideapp.R;

import java.util.Map;

public class OurNotifications {

    private static String CHANNEL_ID = "BraGuia";
    public static void createNotificationChannel(Context context) {
        CharSequence name = "Notificações";
        String description = "Notificações pins";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(description);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    public static PendingIntent assignIntent(Activity activity, Class<?> activityres, Map<String,Integer> params){
        Intent intent = new Intent(activity, activityres);
        if(params != null)
            params.forEach(intent::putExtra);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
    }

    public static void sendNotification(Context context, String title, String text, PendingIntent pendingIntent) {
        Log.d("DebugApp","A enviar notificação " + title);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_location_pin_24)
                .setContentTitle(title)
                .setContentText(text)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(20, builder.build());
        Log.d("DebugApp","Notificação enviada " + title);
    }
}
