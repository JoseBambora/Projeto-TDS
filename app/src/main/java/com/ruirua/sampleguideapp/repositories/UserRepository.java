package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.user.LoginData;
import com.ruirua.sampleguideapp.model.user.User;
import com.ruirua.sampleguideapp.model.user.UserAPI;
import com.ruirua.sampleguideapp.model.user.UserDAO;
import com.ruirua.sampleguideapp.model.user.UserInfo;
import com.ruirua.sampleguideapp.repositories.utils.UtilRepository;
import com.ruirua.sampleguideapp.repositories.utils.UtilsFuns;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private final UserDAO userDAO;
    private User currentUser;

    private final UserAPI userAPI;
    private UserRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        userDAO = database.userDAO();
        Retrofit retrofit = UtilsFuns.buildRetrofit();
        userAPI = retrofit.create(UserAPI.class);
    }
    public void setCurrentUser(User user) {
        if (user != null) {
            currentUser = user;
        }
    }
    public LiveData<List<User>> usersLogged() {
        return userDAO.getUsers();
    }

    public void insert(User user){
        Executors.newSingleThreadExecutor().execute(() -> userDAO.insert(user));
    }

    private void authUser(Response<ResponseBody> response,String username) {
        Map<String,String> cookies = UtilsFuns.getCookies(response);
        currentUser = new User();
        currentUser.setUsername(username);
        currentUser.setSessionid(cookies.get("sessionid"));
        currentUser.setCsrftoken(cookies.get("csrftoken"));
        currentUser.setUser_type("user");
        insert(currentUser);
        Log.d("userslogged2","Sucesso "+ username);
    }
    public void login(String username, String password) {
        Call<ResponseBody> call = userAPI.postLogin(new LoginData(username, password));
        call.enqueue(new UtilRepository<>((res) -> authUser(res,username)));
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

    public LiveData<UserInfo> getUserInfo() {
        MutableLiveData<UserInfo> res = new MutableLiveData<>();
        userAPI.getUserInfo(getCsrfToken(),getSessionId()).enqueue(new UtilRepository<>((response) -> res.setValue(response.body())));
        return res;
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
