package com.ruirua.sampleguideapp.model.trails;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public class TrailConverter {
    @TypeConverter
    public CountryLangs storedStringToLanguages(String value) {
        List<String> langs = Arrays.asList(value.split("\\s*,\\s*"));
        return new CountryLangs(langs);
    }

    @TypeConverter
    public String languagesToStoredString(CountryLangs cl) {
        if (cl == null || cl.getCountryLangs() == null) {
            return "";
        }

        StringBuilder value = new StringBuilder();

        for (String lang : cl.getCountryLangs()) {
            value.append(lang).append(",");
        }

        return value.toString();
    }
}
