package com.ruirua.sampleguideapp.model.user;

import java.util.List;

public class UserInfo {
    private String user_type;
    private String last_login;
    private boolean is_superuser;
    private String username;
    private String last_name;
    private String email;
    private boolean is_staff;
    private boolean is_active;
    private String date_joined;
    private List<Integer> user_permissions;

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public boolean isIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_staff() {
        return is_staff;
    }

    public void setIs_staff(boolean is_staff) {
        this.is_staff = is_staff;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public List<Integer> getUser_permissions() {
        return user_permissions;
    }

    public void setUser_permissions(List<Integer> user_permissions) {
        this.user_permissions = user_permissions;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_type='" + user_type + '\'' +
                ", last_login='" + last_login + '\'' +
                ", is_superuser=" + is_superuser +
                ", username='" + username + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", is_staff=" + is_staff +
                ", is_active=" + is_active +
                ", date_joined='" + date_joined + '\'' +
                ", user_permissions=" + user_permissions +
                '}';
    }

    public boolean isStandard() {
        return user_type.equals("Standard");
    }

    public boolean isPremium() {
        return user_type.equals("Premium");
    }
}
