package com.ruirua.sampleguideapp.model.user;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "user")
public class UserLogged {

    @PrimaryKey
    @NonNull
    //@SerializedName("id")
    @ColumnInfo(name = "username")
    private String username;

    //@SerializedName("image_url")
    @ColumnInfo(name = "user_type")
    private String user_type;

    @ColumnInfo(name = "csrftoken")
    private String csrftoken = "";

    @ColumnInfo(name = "sessionid")
    private String sessionid = "";

    public boolean isPremium() {
        return user_type.equals("Premium");

    }

    public boolean isStandard() {
        return user_type.equals("Standard");
    }

    public void setCsrftoken(String csrftoken) {
        this.csrftoken = csrftoken;
    }
    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    @NonNull
    public String getUsername() {
        return username;
    }



    public String getCsrftoken() {
        return csrftoken;
    }

    public String getSessionid() {
        return sessionid;
    }


    public String getUser_type() {
        return user_type;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", user_type='" + user_type + '\'' +
                ", csrftoken='" + csrftoken + '\'' +
                ", sessionid='" + sessionid + '\'' +
                '}';
    }
}
