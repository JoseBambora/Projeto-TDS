package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.user.LoginData;
import com.ruirua.sampleguideapp.model.user.UserLogged;
import com.ruirua.sampleguideapp.model.user.UserAPI;
import com.ruirua.sampleguideapp.model.user.UserDAO;
import com.ruirua.sampleguideapp.model.user.UserInfo;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilsFuns;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private final UserDAO userDAO;
    private UserLogged currentUser;
    private UserInfo userInfo;

    private final UserAPI userAPI;
    private UserRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        userDAO = database.userDAO();
        Retrofit retrofit = UtilsFuns.buildRetrofit();
        userAPI = retrofit.create(UserAPI.class);
        currentUser = null;
    }
    public void setCurrentUser(UserLogged user) {
        if (user != null) {
            currentUser = user;
        }
    }
    public LiveData<List<UserLogged>> usersLogged() {
        return userDAO.getUsers();
    }

    public void insert(UserLogged user){
        Executors.newSingleThreadExecutor().execute(() -> userDAO.insert(user));
    }

    private void assignUserInfo(Response<UserInfo> response) {
        userInfo = response.body();
    }

    private void assignUserType() {
        Call<UserInfo> call = userAPI.getUserInfo(getCsrfToken(),getSessionId());
        call.enqueue(new UtilRepository<>(this::assignUserInfo,(er) -> Log.d("DebugApp","Erro ao fazer pedido do tipo de user ")));
    }
    private void authUser(Response<ResponseBody> response, String username, Consumer<Boolean> consumer) {
        Map<String,String> cookies = UtilsFuns.getCookies(response);
        UserLogged ul = new UserLogged();
        ul.setUsername(username);
        ul.setSessionid(cookies.get("sessionid"));
        ul.setCsrftoken(cookies.get("csrftoken"));
        ul.setUser_type("user");
        setCurrentUser(ul);
        consumer.accept(true);
        assignUserType();
        // insert(currentUser);
    }
    public void login(String username, String password, Consumer<Boolean> consumer) {
        if(isLogged()) {
            consumer.accept(true);
        }
        else {
            Call<ResponseBody> call = userAPI.postLogin(new LoginData(username, password));
            call.enqueue(new UtilRepository<>((res) -> authUser(res,username,consumer), (res) -> consumer.accept(false)));
        }
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isLogged() {
        return currentUser != null;
    }

    public String getCsrfToken() {
        return currentUser.getCsrftoken();
    }

    public String getSessionId() {
        return currentUser.getSessionid();
    }

    public boolean isPremium() {
        return isLogged() && userInfo != null && userInfo.isPremium();
    }

    public boolean isStandard() {
        return isLogged() && userInfo != null && userInfo.isStandard();
    }

    public UserInfo getUserLoggedInfo() {
        return this.userInfo;
    }

    private static UserRepository instance;
    public static synchronized UserRepository getInstance() {
        return instance;
    }
    public static synchronized UserRepository createUserRepository(Application application) {
        if(instance == null)
            instance = new UserRepository(application);
        return instance;
    }
}
