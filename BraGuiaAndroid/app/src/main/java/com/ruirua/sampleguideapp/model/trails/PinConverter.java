package com.ruirua.sampleguideapp.model.trails;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruirua.sampleguideapp.model.pins.Pin;

import java.lang.reflect.Type;

public class PinConverter {

    @TypeConverter
    public static Pin fromString(String value) {
        Type listType = new TypeToken<Pin>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(Pin pin) {
        Gson gson = new Gson();
        return gson.toJson(pin);
    }
}
