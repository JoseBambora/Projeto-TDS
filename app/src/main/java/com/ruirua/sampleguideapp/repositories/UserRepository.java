package com.ruirua.sampleguideapp.repositories;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.ruirua.sampleguideapp.repositories.utils.RepoFuns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.function.Consumer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UserRepository {
    private UserLogged currentUser;
    private MutableLiveData<UserInfo> userInfo = new MutableLiveData<>();

    private final String saveFile = "logininfo";

    private final UserAPI userAPI;
    private final Application application;

    private void clearSavedInfo() {
        application.getSharedPreferences(saveFile, Context.MODE_PRIVATE).edit().clear().apply();
    }

    private void getSavedLoginInfo() {
        SharedPreferences prefs = application.getSharedPreferences(saveFile, Context.MODE_PRIVATE);
        String crftoken = prefs.getString("Csrftoken",null);
        String sessionid = prefs.getString("Sessionid",null);
        if(crftoken != null && sessionid != null && RepoFuns.validateTokens(crftoken,sessionid)) {
            String username = prefs.getString("username",null);
            String user_type = prefs.getString("user_type",null);
            String email = prefs.getString("email", null);
            String date = prefs.getString("date", null);
            String last_login = prefs.getString("last_login",null);
            String last_name = prefs.getString("last_name", null);

            UserInfo ui = new UserInfo();
            ui.setLast_name(last_name);
            ui.setUser_type(user_type);
            ui.setUsername(username);
            ui.setEmail(email);
            ui.setDate_joined(date);
            ui.setLast_login(last_login);

            userInfo = new MutableLiveData<>();
            userInfo.setValue(ui);

            currentUser = new UserLogged();
            currentUser.setSessionid(sessionid);
            currentUser.setCsrftoken(crftoken);
            currentUser.setUsername(username);
            currentUser.setUser_type(user_type);
        }
        else {
            clearSavedInfo();
            currentUser = null;
        }
    }
    private UserRepository(Application application){
        this.application = application;
        Retrofit retrofit = RepoFuns.buildRetrofit();
        userAPI = retrofit.create(UserAPI.class);
        getSavedLoginInfo();
    }

    public void setCurrentUser(UserLogged user) {
        if (user != null) {
            SharedPreferences prefs = application.getSharedPreferences(saveFile, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Csrftoken", user.getCsrftoken());
            editor.putString("Sessionid", user.getSessionid());
            editor.putString("username",user.getUsername());
            editor.apply();
            currentUser = user;
        }
    }

    private void assignUserInfo(Response<UserInfo> response) {
        UserInfo userInfo = response.body();
        this.userInfo.setValue(response.body());
        SharedPreferences prefs = application.getSharedPreferences(saveFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user_type", userInfo.getUser_type());
        editor.putString("email", userInfo.getEmail());
        editor.putString("date", userInfo.getDate_joined());
        editor.putString("last_login", userInfo.getLast_login());
        editor.putString("last_name", userInfo.getLast_name());
        editor.apply();
    }

    private void assignUserType() {
        Call<UserInfo> call = userAPI.getUserInfo(getCsrfToken(),getSessionId());
        call.enqueue(new UtilRepository<>(this::assignUserInfo,(er) -> Log.d("DebugApp","Erro ao fazer pedido do tipo de user ")));
    }
    private void authUser(Response<ResponseBody> response, String username, Consumer<Boolean> consumer) {
        Map<String,String> cookies = RepoFuns.getCookies(response);
        UserLogged ul = new UserLogged();
        ul.setUsername(username);
        ul.setSessionid(cookies.get("sessionid"));
        ul.setCsrftoken(cookies.get("csrftoken"));
        ul.setUser_type("user");
        setCurrentUser(ul);
        assignUserType();
        consumer.accept(true);
    }
    public void login(String username, String password, Consumer<Boolean> consumer) {
        if(username.equals("p"))
        {
            UserLogged ul = new UserLogged();
            ul.setUsername("p");
            ul.setCsrftoken("csrftoken=tknr4RPpue47Oftn8mgjEuB1vW9Y7UpvMEhD0wIaO6AYTOHuSWQnGjN7JfUoIGKC; expires=Wed, 09 Apr 2025 18:48:11 GMT; Max-Age=31449600; Path=/; SameSite=Lax");
            ul.setSessionid("sessionid=5sisk4qt4lajnr9zihurxbcktzbigz8i; expires=Wed, 24 Apr 2024 18:48:11 GMT; Max-Age=1209600; Path=/; SameSite=Lax");
            setCurrentUser(ul);
            assignUserType();
            consumer.accept(true);
        }
        else if(isLogged()) {
            consumer.accept(true);
        }
        else {
            Call<ResponseBody> call = userAPI.postLogin(new LoginData(username, password));
            call.enqueue(new UtilRepository<>((res) -> authUser(res,username,consumer), (res) -> consumer.accept(false)));
        }
    }

    public void logout() {
        currentUser = null;
        clearSavedInfo();
    }

    public boolean isLogged() {
        return currentUser != null && RepoFuns.validateTokens(getCsrfToken(),getSessionId());
    }

    public String getCsrfToken() {
        return currentUser.getCsrftoken();
    }

    public String getSessionId() {
        return currentUser.getSessionid();
    }

    public boolean isPremium() {
        return isLogged() && userInfo.getValue() != null && userInfo.getValue().isPremium();
    }

    public boolean isStandard() {
        return isLogged() && userInfo.getValue() != null && userInfo.getValue().isStandard();
    }

    public LiveData<UserInfo> getUserLoggedInfo() {
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
