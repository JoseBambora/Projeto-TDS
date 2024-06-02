package com.ruirua.sampleguideapp.repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
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

    private final Context context;

    private final Set<String> images = new HashSet<>();
    private final Set<String> videos = new HashSet<>();
    private final Set<String> audios = new HashSet<>();

    private MediaRepository(Context context){
        this.context = context;
        loadInfo();
    }

    private void readFiles(File directory, Set<String> set) {
        if(directory != null) {
            File [] files = directory.listFiles();
            if(files != null)
                for(File file : files)
                    set.add(file.getName());
        }
    }

    private void loadInfo() {
        File imageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File audioDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File videoDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        readFiles(imageDir,images);
        readFiles(audioDir,audios);
        readFiles(videoDir,videos);
    }
    private final APIMedia apiMedia = RepoFuns.buildRetrofit().create(APIMedia.class);

    private boolean hasAudio(String link) {
        return audios.contains(link);
    }

    private boolean hasImage(String link) {
        return images.contains(link);
    }

    private boolean hasVideo(String link) {
        return videos.contains(link);
    }


    private String getFileName(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }

    private File getImageFile(String filename) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir, filename);
    }

    private File getAudioFile(String filename) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        return new File(storageDir, filename);
    }

    private File getVideoFile(String filename) {
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES);
        return new File(storageDir, filename);
    }

    private void readContent(InputStream inputStream, File file) {
        try {
            file.createNewFile();
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

    private void saveImage(ResponseBody responseBody, String filename, ImageView imageView) {
        try {
            InputStream inputStream = responseBody.byteStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            File imageFile = getImageFile(filename);
            imageFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.close();
            imageView.setImageBitmap(bitmap);
            images.add(filename);
            Log.d("DebugApp","Deu save imagem " + filename);
        } catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }
    private void saveAudio(ResponseBody responseBody,  String filename, MediaPlayer mediaPlayer, Consumer<MediaPlayer> posPrepared) {
        try {
            InputStream inputStream = responseBody.byteStream();
            File audioFile = getAudioFile(filename);
            readContent(inputStream,audioFile);
            mediaPlayer.setDataSource(audioFile.getAbsolutePath());
            mediaPlayer.prepare();
            posPrepared.accept(mediaPlayer);
            audios.add(filename);
            Log.d("DebugApp","Deu save audio " + filename);
        } catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }
    private void saveVideo(ResponseBody responseBody, String filename, VideoView videoView, Consumer<VideoView> posPrepared) {
        try {
            InputStream inputStream = responseBody.byteStream();
            File videoFile = getVideoFile(filename);
            readContent(inputStream,videoFile);
            Log.d("DebugApp","Deu save video " + filename);
            videoView.setVideoURI(Uri.fromFile(videoFile));
            posPrepared.accept(videoView);
            videos.add(filename);
        } catch (Exception e) {
            Log.d("DebugApp", "Erro " + e.getMessage());
        }
    }

    public void getImage(String link, ImageView imageView) {
        String filename = getFileName(link);
        if(hasImage(filename)) {
            // Log.d("DebugApp", "Tem imagem " + filename + " guardada");
            File imageFile = getImageFile(filename);
            Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            imageView.setImageBitmap(bitmap);
        }
        else {
            Log.d("DebugApp", "A pedir imagem à API");
            apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveImage(r.body(), filename, imageView), err -> Log.d("DebugApp", "Erro " + err.toString())));
        }
    }


    public void getAudio(String link, MediaPlayer mediaPlayer, Consumer<MediaPlayer> posPrepared) {
        String filename = getFileName(link);
        if(hasAudio(filename)) {
            try {
                // Log.d("DebugApp", "Tem audio " + filename +" guardado");
                File imageFile = getAudioFile(filename);
                mediaPlayer.setDataSource(imageFile.getAbsolutePath());
                mediaPlayer.prepare();
                posPrepared.accept(mediaPlayer);
            } catch (IOException ignored) {}
        }
        else {
            Log.d("DebugApp", "A pedir audio à API");
            apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveAudio(r.body(), filename, mediaPlayer, posPrepared), err -> Log.d("DebugApp", "Erro " + err.toString())));
        }
    }

    public void getVideo(String link, VideoView videoView, Consumer<VideoView> posPrepared) {
        String filename = getFileName(link);
        if(hasVideo(filename)) {
            // Log.d("DebugApp", "Tem video " + filename + " guardado");
            File videoFile = getVideoFile(filename);
            videoView.setVideoURI(Uri.fromFile(videoFile));
            posPrepared.accept(videoView);
        }
        else {
            Log.d("DebugApp", "A pedir video à API");
            apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveVideo(r.body(), filename, videoView,posPrepared), err -> Log.d("DebugApp", "Erro " + err.toString())));
        }
    }

    private static MediaRepository instance = null;

    public static MediaRepository getInstance() {
        return instance;
    }

    public static MediaRepository createInstance(Context context) {
        if (instance == null)
            instance = new MediaRepository(context);
        return instance;
    }

}
