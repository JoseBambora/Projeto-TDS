package com.ruirua.sampleguideapp.repositories;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.VideoView;

import com.ruirua.sampleguideapp.model.media.APIMedia;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import okhttp3.ResponseBody;

public class MediaRepository {
    private static Set<String> audios = new HashSet<>();

    private static APIMedia apiMedia = RepoFuns.buildRetrofit().create(APIMedia.class);
    private static Set<String> images = new HashSet<>();
    private static Set<String> videos = new HashSet<>();

    private static boolean hasAudio(String link) {
        return audios.contains(link);
    }

    private static boolean hasImage(String link) {
        return images.contains(link);
    }

    private static boolean hasVideo(String link) {
        return videos.contains(link);
    }


    private static String getFileName(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    private static File getImageFile(Context context, String filename) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir, filename);
    }

    private static File getAudioFile(Context context, String filename) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        return new File(storageDir, filename);
    }

    private static File getVideoFile(Context context, String filename) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        return new File(storageDir, filename);
    }

    private static void readContent(InputStream inputStream, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fos.close();
        }
        catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }

    private static void saveImage(ResponseBody responseBody, Context context, String filename, ImageView imageView) {
        try {
            InputStream inputStream = responseBody.byteStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            File imageFile = getImageFile(context,filename);
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            imageView.setImageBitmap(bitmap);
            Log.d("DebugApp","Deu save imagem");
            images.add(filename);
        } catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }
    private static void saveAudio(ResponseBody responseBody, Context context, String filename, MediaPlayer mediaPlayer, Consumer<MediaPlayer> posPrepared) {
        try {
            InputStream inputStream = responseBody.byteStream();
            File audioFile = getAudioFile(context,filename);
            readContent(inputStream,audioFile);
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
            posPrepared.accept(mediaPlayer);
            Log.d("DebugApp","Deu save audio");
            audios.add(filename);
        } catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }
    private static void saveVideo(ResponseBody responseBody, Context context, String filename, VideoView videoView) {
        try {
            InputStream inputStream = responseBody.byteStream();
            File videoFile = getVideoFile(context,filename);
            readContent(inputStream,videoFile);
            Log.d("DebugApp","Deu save video");
            videoView.setVideoURI(Uri.fromFile(videoFile));
            videos.add(filename);
        } catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }

    public static void getImage(String link, ImageView imageView, Context context) {
        String filename = getFileName(link);
        if(hasImage(filename)) {
            Log.d("DebugApp", "Tem imagem guardado");
            File imageFile = getImageFile(context,filename);
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }
        else {
            Log.d("DebugApp", "A pedir imagem à API");
            apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveImage(r.body(), context, filename, imageView), err -> Log.d("DebugApp", "Erro " + err.toString())));
        }
    }


    public static void getAudio(String link, Context context, MediaPlayer mediaPlayer, Consumer<MediaPlayer> posPrepared) {
        String filename = getFileName(link);
        if(hasAudio(filename)) {
            try {
                Log.d("DebugApp", "Tem audio guardado");
                File imageFile = getImageFile(context,filename);
                mediaPlayer.setDataSource(imageFile.getAbsolutePath());
                mediaPlayer.prepare();
                posPrepared.accept(mediaPlayer);
            } catch (IOException ignored) {}
        }
        else {
            Log.d("DebugApp", "A pedir audio à API");
            apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveAudio(r.body(), context, filename, mediaPlayer, posPrepared), err -> Log.d("DebugApp", "Erro " + err.toString())));
        }
    }

    public static void getVideo(String link, Context context, VideoView videoView) {
        String filename = getFileName(link);
        if(hasVideo(filename)) {
            Log.d("DebugApp", "Tem video guardado");
            File videoFile = getImageFile(context,filename);
            videoView.setVideoURI(Uri.fromFile(videoFile));
        }
        else {
            Log.d("DebugApp", "A pedir video à API");
            apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveVideo(r.body(), context, filename, videoView), err -> Log.d("DebugApp", "Erro " + err.toString())));
        }
    }
}
