package com.ruirua.sampleguideapp.model.trails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface TrailAPI {
    @GET("trails")
    Call<List<Trail>> getTrails();

    @GET("trail/{id}")
    Call<Trail> getTrail(@Path("id") int id, @Header("Cookie") String csrftoken, @Header("Cookie") String sessionid);

}
