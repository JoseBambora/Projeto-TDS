package com.ruirua.sampleguideapp.repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilRepository<T> implements Callback<List<T>> {
    Consumer<List<T>> insert;
    public UtilRepository(Consumer<List<T>> insertMethod) {
        this.insert = insertMethod;
    }

    @Override
    public void onResponse(@NonNull Call<List<T>> call, @NonNull Response<List<T>> response) {
        if(response.isSuccessful()) {
            insert.accept(response.body());
        }
        else{
            Log.e("main", "onFailure: "+response.errorBody());
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<T>> call, @NonNull Throwable t) {
        Log.e("main", "onFailure: " + t.getMessage());
    }
}
