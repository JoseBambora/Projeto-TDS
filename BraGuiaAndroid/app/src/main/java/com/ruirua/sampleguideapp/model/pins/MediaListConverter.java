package com.ruirua.sampleguideapp.model.pins;

import android.util.Log;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MediaListConverter {

    @TypeConverter
    public static List<Media> fromString(String value) {
        Type listType = new TypeToken<List<Media>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Media> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
