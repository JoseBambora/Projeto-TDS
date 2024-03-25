package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ruirua.sampleguideapp.BuildConfig;
import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.model.pins.PinAPI;
import com.ruirua.sampleguideapp.model.pins.PinDAO;

import java.util.List;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PinRepository {
    private final PinDAO pinDAO;
    private final MediatorLiveData<List<Pin>> allPins;

    public LiveData<List<Pin>> getAllPins(){
        return allPins;
    }


    public PinRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        pinDAO = database.pinDAO();
        allPins = new MediatorLiveData<>();
        allPins.addSource(
                pinDAO.getPins(), localTrails -> {
                    if (localTrails != null && localTrails.size() > 0) {
                        allPins.setValue(localTrails);
                    } else {
                        makeRequest();
                    }
                }
        );
    }

    public void insert(List<Pin> pins){
        Executors.newSingleThreadExecutor().execute(() -> pinDAO.insert(pins));
    }

    private void makeRequest() {
        String csrftoken = "";
        String sessionid = "";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BuildConfig.BRAGUIDE_BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PinAPI api = retrofit.create(PinAPI.class);
        Call<List<Pin>> call = api.getPins(csrftoken,sessionid);
        call.enqueue(new retrofit2.Callback<List<Pin>>() {
            @Override
            public void onResponse(Call<List<Pin>> call, Response<List<Pin>> response) {
                if(response.isSuccessful()) {
                    insert(response.body());
                }
                else{
                    Log.e("main", "onFailure: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Pin>> call, Throwable t) {
                Log.e("main", "onFailure: " + t.getMessage());
            }
        });
    }
}
