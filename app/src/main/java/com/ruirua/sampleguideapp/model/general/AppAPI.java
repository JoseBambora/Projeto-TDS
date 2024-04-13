package com.ruirua.sampleguideapp.model.general;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AppAPI {
    @GET("app")
    Call<List<App>> getAppInfo();
}
