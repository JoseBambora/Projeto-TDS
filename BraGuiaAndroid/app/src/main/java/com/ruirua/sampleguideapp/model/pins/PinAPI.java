package com.ruirua.sampleguideapp.model.pins;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface PinAPI {
    @GET("pins")
    Call<List<Pin>> getPins(@Header("Cookie") String csrftoken, @Header("Cookie") String sessionid);

    @GET("pin/{id}")
    Call<Pin> getPin(@Path("id") int id, @Header("Cookie") String csrftoken, @Header("Cookie") String sessionid);
}
