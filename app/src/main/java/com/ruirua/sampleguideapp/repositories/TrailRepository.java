package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.model.trails.TrailAPI;
import com.ruirua.sampleguideapp.model.trails.TrailDAO;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilsFuns;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TrailRepository {
    private final TrailDAO trailDAO;
    private final MediatorLiveData<List<Trail>> allTrails;

    private void setTrails(List<Trail> localTrails) {
        // TODO: ADD cache validation logic
        if (localTrails != null && localTrails.size() > 0) {
            allTrails.setValue(localTrails);
        } else {
            makeRequest();
        }
    }

    public TrailRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        trailDAO = database.trailDAO();
        allTrails = new MediatorLiveData<>();
        allTrails.addSource(trailDAO.getTrails(), this::setTrails);
    }

    public void insert(List<Trail> trails){
        Executors.newSingleThreadExecutor().execute(() -> trailDAO.insert(trails));
    }

    private void makeRequest() {
        Retrofit retrofit= UtilsFuns.buildRetrofit();
        TrailAPI api = retrofit.create(TrailAPI.class);
        Call<List<Trail>> call = api.getTrails();
        call.enqueue(new UtilRepository<>(this::insert));
    }

    public LiveData<List<Trail>> getAllTrails(){
        return allTrails;
    }

}
