package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ruirua.sampleguideapp.model.GuideDatabase;
import com.ruirua.sampleguideapp.model.user.LoginData;
import com.ruirua.sampleguideapp.model.user.User;
import com.ruirua.sampleguideapp.model.user.UserAPI;
import com.ruirua.sampleguideapp.model.user.UserDAO;
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
    private final MediatorLiveData<User> currentUser;

    public void setCurrentUser(User user) {
        if (user != null) {
            currentUser.setValue(user);
        }
    }
    public UserRepository(Application application){
        GuideDatabase database = GuideDatabase.getInstance(application);
        userDAO = database.userDAO();
        currentUser = new MediatorLiveData<>();
    }
    public LiveData<List<User>> usersLogged() {
        return userDAO.getUsers();
    }

    public LiveData<User> userLogged() {return this.currentUser;}

    public void insert(User user){
        Executors.newSingleThreadExecutor().execute(() -> userDAO.insert(user));
    }
    public void login(String username, String password) {
        Retrofit retrofit = UtilsFuns.buildRetrofit();
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<ResponseBody> call = userAPI.postLogin(new LoginData(username, password));
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    Map<String,String> cookies = UtilsFuns.getCookies(response);
                    User u = new User();
                    u.setUsername(username);
                    u.setSessionid(cookies.get("sessionid"));
                    u.setCsrftoken(cookies.get("csrftoken"));
                    u.setUser_type("user");
                    insert(u);
                    currentUser.setValue(u);
                    Log.d("login","sucesso");
                }
                else {
                   Log.e("login","Credenciais Erradas");
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("login","Erro falha login");
            }
        });
    }

    public void logout() {
        User u = currentUser.getValue();
        if(u != null){
            userDAO.deleteUser(u.getUsername());
            // logout api
            currentUser.setValue(null);
        }
    }

    public boolean isLogged() {
        return currentUser.getValue() != null;
    }
}
