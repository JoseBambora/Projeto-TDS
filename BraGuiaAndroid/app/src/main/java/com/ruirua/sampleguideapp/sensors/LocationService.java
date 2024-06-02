package com.ruirua.sampleguideapp.sensors;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.ruirua.sampleguideapp.R;

public class LocationService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void createNotificationChannel() {
        String CHANNEL_ID = "notifications";
        String name = "Channel Name";
        String descriptionText = "Channel Description";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(descriptionText);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                 .setContentTitle("Serviço de localização")
                .setContentText("Service de localização está ligado")
                .setSmallIcon(R.drawable.logo)
                .build();
        startForeground(100, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        OurLocationListener.getInstance().onStart();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        OurLocationListener.getInstance().onStop();
    }
}
