package com.ruirua.sampleguideapp.repositories.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilRepository<T> implements Callback<T> {
    Consumer<Response<T>> insert;
    public UtilRepository(Consumer<Response<T>> insertMethod) {
        this.insert = insertMethod;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if(response.isSuccessful()) {
            Log.d("Pinsid","Aceite");
            insert.accept(response);
        }
        else{
            Log.d("userslogged2","erro1");
            Log.e("main", "onFailure: "+response.errorBody());
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        Log.d("userslogged2","erro2 " + t.getMessage());
        Log.e("main", "onFailure: " + t.getMessage());
    }
}

