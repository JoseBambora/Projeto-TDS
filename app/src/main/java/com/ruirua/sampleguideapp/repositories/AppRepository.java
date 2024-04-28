package com.ruirua.sampleguideapp.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.general.App;
import com.ruirua.sampleguideapp.model.general.AppAPI;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;

import retrofit2.Retrofit;

public class AppRepository {
    private final AppAPI appAPI;

    private MutableLiveData<App> cache = new MutableLiveData<>();

    private AppRepository() {
        Retrofit retrofit = RepoFuns.buildRetrofit();
        appAPI = retrofit.create(AppAPI.class);
    }

    public LiveData<App> getAppInfo() {
        if(cache.getValue() == null) {
            Log.d("DebugApp","Cache == null");
            appAPI.getAppInfo().enqueue(new UtilRepository<>(r -> {
                App a = r.body().get(0);
                cache.setValue(a);
            },null));
        }
        return cache;
    }

    public static AppRepository instance = null;

    public static AppRepository getInstance() {
        if (instance == null)
            instance = new AppRepository();
        return instance;
    }


}
