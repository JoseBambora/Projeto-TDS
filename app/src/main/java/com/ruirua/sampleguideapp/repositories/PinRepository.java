package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinAPI;
import com.ruirua.sampleguideapp.model.pins.PinDAO;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilsFuns;

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
        Retrofit retrofit= UtilsFuns.buildRetrofit();
        pinAPI = retrofit.create(PinAPI.class);
        pinDAO = database.pinDAO();
        allPins = new MediatorLiveData<>();
        allPins.addSource(pinDAO.getPins(), this::setValues);
    }

    public void insert(List<Pin> pins){
        Executors.newSingleThreadExecutor().execute(() -> pinDAO.insert(pins));
    }

    private void getPinsAPI() {
        String csrftoken = "csrftoken=CBkYlMArZvkqisTpUbgv6fQBTsf5nmVSPgUYEDX711bfmQnlpj5QhrgHrBA7Spio; Path=/; Expires=Wed, 26 Mar 2025 10:34:45 GMT;";
        String sessionid = "sessionid=f3kmcjkadvckulq0w3bt2wre7joynbxq; Path=/; Expires=Wed, 10 Apr 2024 10:34:45 GMT;";
        Call<List<Pin>> call = pinAPI.getPins(csrftoken,sessionid);
        call.enqueue(new UtilRepository<>((response) -> this.insert(response.body())));
    }

    private void getPinAPI(int id, MutableLiveData<Pin> res) {
        Log.d("Pinid","entrou4");
        String csrftoken = "csrftoken=CBkYlMArZvkqisTpUbgv6fQBTsf5nmVSPgUYEDX711bfmQnlpj5QhrgHrBA7Spio; Path=/; Expires=Wed, 26 Mar 2025 10:34:45 GMT;";
        String sessionid = "sessionid=f3kmcjkadvckulq0w3bt2wre7joynbxq; Path=/; Expires=Wed, 10 Apr 2024 10:34:45 GMT;";
        Call<Pin> call = pinAPI.getPin(id,csrftoken,sessionid);
        call.enqueue(new UtilRepository<>((response) -> res.setValue(response.body())));
    }
    public LiveData<Pin> getPin(int id) {
        MutableLiveData<Pin> res = new MutableLiveData<>();
        Log.d("Pinid","entrou");
        if(allPins.getValue() != null) {
            Log.d("Pinid","entrou1");
            int index = allPins.getValue().stream()
                    .map(Pin::getId)
                    .collect(Collectors.toList())
                    .indexOf(id);
            Log.d("Pinid","entrou2");
            if (index != -1) {
                res.setValue(allPins.getValue().get(index));
                Log.d("Pinid","entrou3");
                return res;
            }
        }
        getPinAPI(id,res);
        return res;
    }
}
