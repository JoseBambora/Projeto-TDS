package com.ruirua.sampleguideapp.repositories.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilRepository<T> implements Callback<T> {
    Consumer<Response<T>> sucess;
    Consumer<Response<T>> error;
    public UtilRepository(Consumer<Response<T>> sucess, Consumer<Response<T>> error) {
        this.sucess = sucess;
        this.error = error;
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if(response.isSuccessful()) {
            sucess.accept(response);
        }
        else{
            if(error != null)
                error.accept(response);
        }
    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        Log.d("userslogged2","erro2 " + t.getMessage());
        Log.e("main", "onFailure: " + t.getMessage());
    }
}

