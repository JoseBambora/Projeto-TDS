package com.ruirua.sampleguideapp.repositories;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.ruirua.sampleguideapp.model.media.APIMedia;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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

    public static Uri getImage(String link, Context context) {
        String filename = getFileName(link);
        Uri place = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        if(!hasImage(filename)) {
            place = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            images.add(filename);
            saveMedia(filename,context);
        }
        String query =MediaStore.Images.Media._ID + " = ?";
        String[] parameters = new String[]{filename};
        String id = MediaStore.Images.Media._ID;
        String[] projections = new String[]{id};
        Cursor cursor = context.getContentResolver().query(
                place,
                projections,
                query,
                parameters,
                ""
        );
        if(cursor != null && cursor.moveToNext()) {
            Log.d("DebugApp","Tem content");
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            return ContentUris.withAppendedId(place, cursor.getLong(idColumn));
        }
        Log.d("DebugApp","No content");
        return Uri.parse(link);
    }

    public static Uri getAudio(String link, Context context) {
        String filename = getFileName(link);
        Uri place = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        if(!hasAudio(filename)) {
            place = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            audios.add(filename);
        }
        String query =MediaStore.Audio.Media._ID + " = ?";
        String[] parameters = new String[]{filename};
        String id = MediaStore.Audio.Media._ID;
        String[] projections = new String[]{id};
        Cursor cursor = context.getContentResolver().query(
                place,
                projections,
                query,
                parameters,
                ""
        );
        if(cursor != null && cursor.moveToNext()) {
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            return ContentUris.withAppendedId(place, cursor.getLong(idColumn));
        }
        return Uri.parse(filename);
    }

    public static Uri getVideo(String link, Context context) {
        String filename = getFileName(link);
        Uri place = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
        if(!hasVideo(filename)) {
            place = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            videos.add(filename);
        }
        String query =MediaStore.Video.Media._ID + " = ?";
        String[] parameters = new String[]{filename};
        String id = MediaStore.Video.Media._ID;
        String[] projections = new String[]{id};
        Cursor cursor = context.getContentResolver().query(
                place,
                projections,
                query,
                parameters,
                ""
        );
        if(cursor != null && cursor.moveToNext()) {
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return ContentUris.withAppendedId(place, cursor.getLong(idColumn));
        }
        Log.d("DebugApp","No content");
        return Uri.parse(filename);
    }

    private static void saveMediaAux(ResponseBody response, Context context, String filename) {
        try {
            Log.d("DebugApp","A salvar ficheiro.");
            byte[] bytes = response.bytes();
            File directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.close();
            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
            Log.d("DebugApp", "Deu save");
        } catch (IOException e) {
            Log.d("DebugApp","Erro a dar save ao ficheiro " + filename + " mensagem: " + e.getMessage());
        }
    }
    private static String getFileName(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
    private static void saveMedia(String filename, Context context) {
        apiMedia.downloadMedia(filename).enqueue(new UtilRepository<>(r -> saveMediaAux(r.body(), context, filename),err -> Log.d("DebugApp", "Erro " + err.toString())));
    }
}
