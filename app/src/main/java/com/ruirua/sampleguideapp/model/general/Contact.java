package com.ruirua.sampleguideapp.model.general;

import com.google.gson.annotations.SerializedName;

public class Contact {


    @SerializedName("contact_name")
    String name;

    @SerializedName("contact_phone")
    String phone;

    @SerializedName("contact_url")
    String url;

    @SerializedName("contact_mail")
    String mail;

    @SerializedName("contact_desc")
    String desc;

    @SerializedName("contact_app")
    String app;

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUrl() {
        return url;
    }

    public String getMail() {
        return mail;
    }

    public String getDesc() {
        return desc;
    }

    public String getApp() {
        return app;
    }
}
