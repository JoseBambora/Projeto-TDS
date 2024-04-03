package com.ruirua.sampleguideapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinAPI;
import com.ruirua.sampleguideapp.model.pins.PinDAO;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PinRepository {
    private final PinDAO pinDAO;
    private final MediatorLiveData<List<Pin>> allPins;

    private final PinAPI pinAPI;

    public LiveData<List<Pin>> getAllPins(){
        return allPins;
    }

    private void setValues(List<Pin> localPins) {
        if (localPins != null && localPins.size() > 0) {
            allPins.setValue(localPins);
        } else {
            getPinsAPI();
        }
    }

    public PinRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        Retrofit retrofit= RepoFuns.buildRetrofit();
        pinAPI = retrofit.create(PinAPI.class);
        pinDAO = database.pinDAO();
        allPins = new MediatorLiveData<>();
        allPins.addSource(pinDAO.getPins(), this::setValues);
    }

    public void insert(List<Pin> pins){
        Executors.newSingleThreadExecutor().execute(() -> pinDAO.insert(pins));
    }

    private void getPinsAPI() {
        UserRepository ur = UserRepository.getInstance();
        String csrftoken = ur.getCsrfToken();
        String sessionid = ur.getSessionId();
        Call<List<Pin>> call = pinAPI.getPins(csrftoken,sessionid);
        call.enqueue(new UtilRepository<>((response) -> this.insert(response.body()),null));
    }

    private void getPinAPI(int id, MutableLiveData<Pin> res) {
        UserRepository ur = UserRepository.getInstance();
        String csrftoken = ur.getCsrfToken();
        String sessionid = ur.getSessionId();
        Call<Pin> call = pinAPI.getPin(id,csrftoken,sessionid);
        call.enqueue(new UtilRepository<>((response) -> res.setValue(response.body()),null));
    }
    public LiveData<Pin> getPin(int id) {
        MutableLiveData<Pin> res = new MutableLiveData<>();
        if(allPins.getValue() != null) {
            int index = allPins.getValue().stream()
                    .map(Pin::getId)
                    .collect(Collectors.toList())
                    .indexOf(id);
            if (index != -1) {
                res.setValue(allPins.getValue().get(index));
                return res;
            }
        }
        getPinAPI(id,res);
        return res;
    }
}
