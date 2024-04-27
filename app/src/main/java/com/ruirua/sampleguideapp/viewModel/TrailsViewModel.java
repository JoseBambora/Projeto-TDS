package com.ruirua.sampleguideapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ruirua.sampleguideapp.repositories.TrailRepository;
import com.ruirua.sampleguideapp.model.trails.Trail;
import java.util.List;

public class TrailsViewModel extends AndroidViewModel {

    private TrailRepository repository;


    public TrailsViewModel(@NonNull Application application) {
        super(application);
        repository= new TrailRepository(application);
    }

    public LiveData<List<Trail>> getAllTrails() {
        return repository.getAllTrails();
    }

    public LiveData<Trail> getTrail(int id) { return repository.getTrail(id);}

    public List<LiveData<Trail>> getTrails(List<Integer> trailsIds) {
        return repository.getTrailsIds(trailsIds);
    }
}
