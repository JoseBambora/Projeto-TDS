package com.ruirua.sampleguideapp.model.trails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrailAPI {
    @GET("trails")
    Call<List<Trail>> getTrails();
}
