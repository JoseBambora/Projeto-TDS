package com.ruirua.sampleguideapp.model.user;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("/login")
    Call<ResponseBody> postLogin(@Body LoginData loginData);

    @GET("/user")
    Call<UserInfo> getUserInfo(@Header("Cookie") String csrftoken, @Header("Cookie") String sessionid);

}
