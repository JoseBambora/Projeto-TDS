package com.ruirua.sampleguideapp.repositories.utils;

import android.util.Log;

import com.ruirua.sampleguideapp.BuildConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoFuns {
    public static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BRAGUIDE_BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static Map<String,String> getCookies(Response<ResponseBody> response) {
        Map<String,String> res = new HashMap<>();
        List<String> cookies = response.headers().values("Set-Cookie");
        res.put("csrftoken",cookies.get(0));
        res.put("sessionid",cookies.get(1));
        return res;
    }

    private static LocalDateTime extractExpiration(String tokenString) {
        String[] parts = tokenString.split("; ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        for (String part : parts) {
            if (part.startsWith("expires=")) {
                return LocalDateTime.parse(part.substring(8), formatter);
            }
        }
        return null;
    }
    public static boolean validateTokens(String csrfToken, String sessionID) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime csrfDate = extractExpiration(csrfToken);
        LocalDateTime sessionDate = extractExpiration(sessionID);
        return currentDate.isBefore(csrfDate) && currentDate.isBefore(sessionDate);
    }
}
