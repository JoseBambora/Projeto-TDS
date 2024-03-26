package com.ruirua.sampleguideapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinAPI;
import com.ruirua.sampleguideapp.model.pins.PinDAO;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilsFuns;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PinRepository {
    private final PinDAO pinDAO;
    private final MediatorLiveData<List<Pin>> allPins;

    public LiveData<List<Pin>> getAllPins(){
        return allPins;
    }

    private void setValues(List<Pin> localPins) {
        if (localPins != null && localPins.size() > 0) {
            allPins.setValue(localPins);
        } else {
            makeRequest();
        }
    }

    public PinRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        pinDAO = database.pinDAO();
        allPins = new MediatorLiveData<>();
        allPins.addSource(pinDAO.getPins(), this::setValues);
    }

    public void insert(List<Pin> pins){
        Executors.newSingleThreadExecutor().execute(() -> pinDAO.insert(pins));
    }

    private void makeRequest() {
        String csrftoken = "";
        String sessionid = "";
        Retrofit retrofit= UtilsFuns.buildRetrofit();
        PinAPI api = retrofit.create(PinAPI.class);
        Call<List<Pin>> call = api.getPins(csrftoken,sessionid);
        call.enqueue(new UtilRepository<>(this::insert));
    }
}
