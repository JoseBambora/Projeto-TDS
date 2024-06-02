package com.ruirua.sampleguideapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ruirua.sampleguideapp.model.pins.Pin;
import com.ruirua.sampleguideapp.repositories.PinRepository;

import java.util.List;

public class PinsViewModel extends AndroidViewModel {
    private PinRepository repository;
    public PinsViewModel(@NonNull Application application) {
        super(application);
        repository= new PinRepository(application);
    }
    public LiveData<Pin> getPin(int id) {
        return repository.getPin(id);
    }

    public List<LiveData<Pin>> getPins(List<Integer> pinsIds) {
        return repository.getPinsIds(pinsIds);
    }
}
