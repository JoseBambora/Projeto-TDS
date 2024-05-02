package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinAPI;
import com.ruirua.sampleguideapp.model.pins.PinDAO;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class PinRepository {
    private final PinDAO pinDAO;

    private final PinAPI pinAPI;

    private void setValues(List<Pin> localPins, MediatorLiveData<List<Pin>> res) {
        if (localPins != null && !localPins.isEmpty()) {
            res.setValue(localPins);
        } else {
            getPinsAPI(res);
        }
    }

    public PinRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        Retrofit retrofit= RepoFuns.buildRetrofit();
        pinAPI = retrofit.create(PinAPI.class);
        pinDAO = database.pinDAO();
    }

    public void insert(List<Pin> pins,MutableLiveData<List<Pin>> res){
        res.setValue(pins);
        Executors.newSingleThreadExecutor().execute(() -> pinDAO.insert(pins));
    }

    private void getPinsAPI(MutableLiveData<List<Pin>> res) {
        UserRepository ur = UserRepository.getInstance();
        if(ur.isLogged()) {
            String csrftoken = ur.getCsrfToken();
            String sessionid = ur.getSessionId();
            Call<List<Pin>> call = pinAPI.getPins(csrftoken, sessionid);
            call.enqueue(new UtilRepository<>((response) -> this.insert(response.body(),res), null));
        }
    }

    private void getPinAPI(int id, MutableLiveData<Pin> res) {
        UserRepository ur = UserRepository.getInstance();
        if(ur.isLogged()) {
            Log.d("DebugApp","A pedir pin " + id + " à API");
            String csrftoken = ur.getCsrfToken();
            String sessionid = ur.getSessionId();
            Call<Pin> call = pinAPI.getPin(id,csrftoken,sessionid);
            call.enqueue(new UtilRepository<>((response) -> res.setValue(response.body()),null));
        }
    }

    private void getPin(Pin pin, int id, MediatorLiveData<Pin> res) {
        if (pin != null) {
            Log.d("DebugApp","Tem pin "+ id+" na base de dados");
            res.setValue(pin);
        }
        else
            getPinAPI(id,res);

    }
    public LiveData<List<Pin>> getAllPins(){
        MediatorLiveData<List<Pin>> res = new MediatorLiveData<>();
        res.addSource(pinDAO.getPins(),t->setValues(t,res));
        return res;
    }
    public LiveData<Pin> getPin(int id) {
        MediatorLiveData<Pin> res = new MediatorLiveData<>();
        res.addSource(pinDAO.getPin(id),p -> getPin(p,id,res));
        return res;
    }

    public List<LiveData<Pin>> getPinsIds(List<Integer> pinsIds) {
        return pinsIds.stream().map(this::getPin).collect(Collectors.toList());
    }
}
