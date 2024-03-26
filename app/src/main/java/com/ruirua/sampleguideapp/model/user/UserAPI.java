package com.ruirua.sampleguideapp.model.user;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/login")
    Call<ResponseBody> postLogin(@Body LoginData loginData);

}
