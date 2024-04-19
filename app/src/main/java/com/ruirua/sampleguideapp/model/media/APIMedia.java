package com.ruirua.sampleguideapp.model.media;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIMedia {
    @GET("/media/{url}")
    Call<ResponseBody> downloadMedia(@Path("url") String url);

}
