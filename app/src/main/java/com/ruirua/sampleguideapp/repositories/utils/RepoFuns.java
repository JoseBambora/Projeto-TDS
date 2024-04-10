package com.ruirua.sampleguideapp.repositories.utils;

import com.ruirua.sampleguideapp.BuildConfig;

import java.util.HashMap;
import java.util.List;
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
}
