package com.ruirua.sampleguideapp.model.trails;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;;

import java.lang.reflect.Type;
import java.util.List;

public class EdgeConverter {
    @TypeConverter
    public static List<Edge> fromString(String value) {
        Type listType = new TypeToken<List<Edge>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromList(List<Edge> edges) {
        Gson gson = new Gson();
        return gson.toJson(edges);
    }
}

