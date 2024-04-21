package com.ruirua.sampleguideapp.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ruirua.sampleguideapp.Permissions;
import com.ruirua.sampleguideapp.R;
import com.ruirua.sampleguideapp.repositories.MediaRepository;

import java.io.IOException;
import java.util.Map;
import java.util.Stack;

public class UIFuns {
    private static final Stack<Class<?>> stack = new Stack<>();
    public static void configureTheme(Activity activity) {
        if(Settings.getInstance().isLightMode())
            activity.setTheme(R.style.AppThemeLight);
        else
            activity.setTheme(R.style.AppThemeDark);
    }


    public static void changeFragment(FragmentManager fragmentManager, Fragment newFragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .addToBackStack(null)
                .commit();
    }

    public static void changeFragmentNoPushStack(FragmentManager fragmentManager, Fragment newFragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.container, newFragment)
                .commit();
    }

    private static <T> void changeActivity(Activity activity, Class<T> newActivity, Map<String,String> params, boolean push) {
        Intent intent = new Intent(activity, newActivity);
        if(params != null)
            params.forEach(intent::putExtra);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
        if(push) {
            stack.push(activity.getClass());
        }
        activity.finish();
    }


    public static <T> void changeActivity(Activity activity, Class<T> newActivity, Map<String,String> params) {
        changeActivity(activity,newActivity,params,true);
    }

    public static void finishActivity(Activity activity) {
        Class<?> name = stack.pop();
        changeActivity(activity,name,null,false);
    }


    public static Intent intentGoogleMaps(double origin_lat, double origin_lon, double destiny_lat, double destiny_lon) {
        if(Permissions.permission_google_maps) {
            Uri gmmIntentUri = new Uri.Builder()
                    .scheme("https")
                    .authority("www.google.com")
                    .appendPath("maps")
                    .appendPath("dir")
                    .appendPath("")
                    .appendQueryParameter("api", "1")
                    .appendQueryParameter("origin", origin_lat + "," + origin_lon)
                    .appendQueryParameter("destination", destiny_lat + "," + destiny_lon)
                    .build();

            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            return mapIntent;
        }
        else
            return new Intent();
    }

    public static Intent permissionsGoogleMap() {
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        mapIntent.setPackage("com.google.android.apps.maps");
        return mapIntent;
    }

    public static void emergencyCall(Context context) {
        if(Permissions.permission_call) {
            String emergencyNumber = "112";
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + emergencyNumber));
            if (callIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(callIntent);
            } else {
                Toast.makeText(context, "Não é possível realizar chamada de emergência", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void refreshPage(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction().detach(fragment).attach(fragment).commit();
    }

    private static void loadVideo(VideoView videoView, Button buttonVideo, ImageView imageView, Activity activity ) {
        Toast.makeText(activity, "Vídeo carregado", Toast.LENGTH_SHORT).show();
        buttonVideo.setOnClickListener(view -> {
            int drawable = videoView.isPlaying() ? R.drawable.btn_video : R.drawable.btn_pause;
            buttonVideo.setBackgroundResource(drawable);
            videoView.setVisibility(View.VISIBLE);
            if(imageView != null)
                imageView.setVisibility(View.GONE);
            if(videoView.isPlaying())
                videoView.pause();
            else {
                videoView.start();
                ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                videoView.setLayoutParams(layoutParams);
            }
        });
    }
    public static void playVideo(String video_url, VideoView videoView, Button buttonVideo, ImageView imageView, Activity activity) {
        String url = video_url.replace("http:", "https:");
        buttonVideo.setOnClickListener(view -> Toast.makeText(activity,"A carregar Vídeo", Toast.LENGTH_SHORT).show());
        MediaRepository.getVideo(url,activity,videoView, l -> UIFuns.loadVideo(videoView,buttonVideo,imageView,activity));
        MediaController mediaController = new MediaController(activity);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
    }

    private static void loadAudio(Button buttonAudio, Activity activity, MediaPlayer mediaPlayer) {
        Toast.makeText(activity, "Áudio carregado", Toast.LENGTH_SHORT).show();
        buttonAudio.setOnClickListener(view -> {
            int drawable = mediaPlayer.isPlaying() ? R.drawable.btn_sound : R.drawable.btn_mute;
            buttonAudio.setBackgroundResource(drawable);
            if (mediaPlayer.isPlaying())
                mediaPlayer.pause();
            else
                mediaPlayer.start();
        });
    }

    public static void playAudio(String audio_url, Button buttonAudio, Activity activity, MediaPlayer mediaPlayer) {
        String url = audio_url.replace("http:", "https:");
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build());
        mediaPlayer.reset();
        buttonAudio.setOnClickListener(view -> Toast.makeText(activity,"A carregar Áudio.", Toast.LENGTH_SHORT).show());
        MediaRepository.getAudio(url,activity,mediaPlayer, l -> loadAudio(buttonAudio,activity,mediaPlayer));
    }

    public static void showImage(String image_url, ImageView iv, Activity activity, Button buttonImage, VideoView videoView) {
        String url = image_url.replace("http:", "https:");
        iv.setVisibility(View.VISIBLE);
        MediaRepository.getImage(url,iv,activity);
        buttonImage.setOnClickListener(view-> {
            if(videoView != null) {
                videoView.setVisibility(View.GONE);
                if(videoView.isPlaying())
                    videoView.pause();
            }
            iv.setVisibility(View.VISIBLE);
        });
    }
}
