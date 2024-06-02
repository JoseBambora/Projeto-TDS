package com.ruirua.sampleguideapp.model.trails;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RelTrailConverter {
     @TypeConverter
    public static List<RelTrail> fromString(String value) {
        Type listType = new TypeToken<List<RelTrail>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }
    @TypeConverter
    public static String fromList(List<RelTrail> relTrails) {
        Gson gson = new Gson();
        return gson.toJson(relTrails);
    }
}

