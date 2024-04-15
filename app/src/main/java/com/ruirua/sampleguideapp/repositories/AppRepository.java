package com.ruirua.sampleguideapp.repositories;

import com.ruirua.sampleguideapp.model.general.App;
import com.ruirua.sampleguideapp.model.general.AppAPI;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AppRepository {
    private final AppAPI appAPI;

    private Call<List<App>> cache = null;

    public AppRepository() {
        Retrofit retrofit = RepoFuns.buildRetrofit();
        appAPI = retrofit.create(AppAPI.class);
    }

    public Call<List<App>> getAppInfo() {
        if(cache == null)
            cache = appAPI.getAppInfo();
        return cache;
    }


}
