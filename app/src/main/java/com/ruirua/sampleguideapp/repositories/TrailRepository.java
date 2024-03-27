package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.model.trails.TrailAPI;
import com.ruirua.sampleguideapp.model.trails.TrailDAO;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilsFuns;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TrailRepository {
    private final TrailDAO trailDAO;
    private final MediatorLiveData<List<Trail>> allTrails;

    private final TrailAPI trailAPI;

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
        Retrofit retrofit= UtilsFuns.buildRetrofit();
        trailAPI = retrofit.create(TrailAPI.class);
        trailDAO = database.trailDAO();
        allTrails = new MediatorLiveData<>();
        allTrails.addSource(trailDAO.getTrails(), this::setTrails);
    }

    public void insert(List<Trail> trails){
        Executors.newSingleThreadExecutor().execute(() -> trailDAO.insert(trails));
    }

    private void makeRequest() {
        Call<List<Trail>> call = trailAPI.getTrails();
        call.enqueue(new UtilRepository<>((response) -> this.insert(response.body())));
    }

    public LiveData<List<Trail>> getAllTrails(){
        return allTrails;
    }

    private void getTailAPI(int id, MutableLiveData<Trail> res) {
        String csrftoken = "csrftoken=CBkYlMArZvkqisTpUbgv6fQBTsf5nmVSPgUYEDX711bfmQnlpj5QhrgHrBA7Spio; Path=/; Expires=Wed, 26 Mar 2025 10:34:45 GMT;";
        String sessionid = "sessionid=f3kmcjkadvckulq0w3bt2wre7joynbxq; Path=/; Expires=Wed, 10 Apr 2024 10:34:45 GMT;";
        Call<Trail> call = trailAPI.getTrail(id,csrftoken,sessionid);
        call.enqueue(new UtilRepository<>((response) -> res.setValue(response.body())));
    }
    public LiveData<Trail> getTrail(int id) {
        MutableLiveData<Trail> res = new MutableLiveData<>();
        if(allTrails.getValue() != null) {
            int index = allTrails.getValue().stream()
                    .map(Trail::getId)
                    .collect(Collectors.toList())
                    .indexOf(id);
            if (index != -1) {
                res.setValue(allTrails.getValue().get(index));
                return res;
            }
        }
        getTailAPI(id,res);
        return res;
    }

}
