package com.ruirua.sampleguideapp.model.general;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class App {
    @SerializedName("app_name")
    String app_name;

    @SerializedName("social")
    List<Social> socialMedia;

    @SerializedName("contacts")
    List<Contact> contacts;

    @SerializedName("partners")
    List<Partner> partners;

    @SerializedName("app_desc")
    String app_desc;

    @SerializedName("app_landing_page_text")
    String page_text;

    public String getApp_name() {
        return app_name;
    }

    public List<Social> getSocialMedia() {
        return socialMedia;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Partner> getPartners() {
        return partners;
    }

    public String getApp_desc() {
        return app_desc;
    }

    public String getPage_text() {
        return page_text;
    }
}
