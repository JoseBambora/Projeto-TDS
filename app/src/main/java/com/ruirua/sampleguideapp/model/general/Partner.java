package com.ruirua.sampleguideapp.model.general;

import com.google.gson.annotations.SerializedName;

public class Partner {

    @SerializedName("partner_name")
    String name;

    @SerializedName("partner_phone")
    String phone;

    @SerializedName("partner_url")
    String url;

    @SerializedName("partner_mail")
    String mail;

    @SerializedName("partner_desc")
    String desc;

    @SerializedName("partner_app")
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
