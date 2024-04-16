package com.ruirua.sampleguideapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.general.App;
import com.ruirua.sampleguideapp.model.general.AppAPI;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class AppRepository {
    private final AppAPI appAPI;

    private App cache = null;

    public AppRepository() {
        Retrofit retrofit = RepoFuns.buildRetrofit();
        appAPI = retrofit.create(AppAPI.class);
    }

    public LiveData<App> getAppInfo() {
        MutableLiveData<App> res = new MutableLiveData<>();
        if(cache == null) {
            appAPI.getAppInfo().enqueue(new UtilRepository<>(r -> {
                App a = r.body().get(0);
                cache = a;
                res.setValue(a);
            },null));
        }
        else
            res.setValue(cache);
        return res;
    }


}
