package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.trails.Trail;
import com.ruirua.sampleguideapp.model.trails.TrailAPI;
import com.ruirua.sampleguideapp.model.trails.TrailDAO;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Retrofit;

public class TrailRepository {
    private final TrailDAO trailDAO;

    private final TrailAPI trailAPI;

    private static boolean fstTime = true;

    public TrailRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        Retrofit retrofit= RepoFuns.buildRetrofit();
        trailAPI = retrofit.create(TrailAPI.class);
        trailDAO = database.trailDAO();
    }

    public void insert(List<Trail> trails,MediatorLiveData<List<Trail>> res){
        res.setValue(trails);
        Executors.newSingleThreadExecutor().execute(() -> trailDAO.insert(trails));
    }

    private void errorGetTrailsAPI(MediatorLiveData<List<Trail>> res, boolean fstTime) {
        Log.d("DebugApp","Erro aqui");
        if(fstTime)
            res.addSource(trailDAO.getTrails(), res::setValue);
    }
    private void getTrailsAPI(MediatorLiveData<List<Trail>> res) {
        Log.d("DebugApp","A pedir trilhos");
        boolean copy = fstTime;
        Call<List<Trail>> call = trailAPI.getTrails();
        call.enqueue(new UtilRepository<>((response) -> this.insert(response.body(),res),e -> errorGetTrailsAPI(res,copy)));
    }
    private void setTrails(List<Trail> localTrails, MediatorLiveData<List<Trail>> res) {
        if (localTrails != null && !localTrails.isEmpty()) {
            res.setValue(localTrails);
        } else {
            getTrailsAPI(res);
        }
    }

    public LiveData<List<Trail>> getAllTrails(){
        MediatorLiveData<List<Trail>> res = new MediatorLiveData<>();
        if(fstTime)
            getTrailsAPI(res);
        else
            res.addSource(trailDAO.getTrails(), t -> setTrails(t,res));
        fstTime = false;
        return res;
    }

    private void getTailAPI(int id, MediatorLiveData<Trail> res) {
        UserRepository ur = UserRepository.getInstance();
        if(ur.isLogged()) {
            Log.d("DebugApp","A pedir trilho " + id + " à API");
            String csrftoken = ur.getCsrfToken();
            String sessionid = ur.getSessionId();
            Call<Trail> call = trailAPI.getTrail(id,csrftoken,sessionid);
            call.enqueue(new UtilRepository<>((response) -> res.setValue(response.body()), null));
        }
    }
    private void setTrail(Trail trail, int id, MediatorLiveData<Trail> res) {
        if (trail != null) {
            Log.d("DebugApp","Tem trilho " + id + " na base de dados");
            res.setValue(trail);
        }
        else
            getTailAPI(id,res);

    }
    public LiveData<Trail> setTrail(int id) {
        MediatorLiveData<Trail> res = new MediatorLiveData<>();
        res.addSource(trailDAO.getTrail(id),t -> setTrail(t,id,res));
        return res;
    }

    public List<LiveData<Trail>> getTrailsIds(List<Integer> trailsIds) {
        return trailsIds.stream().map(this::setTrail).collect(Collectors.toList());
    }

}
