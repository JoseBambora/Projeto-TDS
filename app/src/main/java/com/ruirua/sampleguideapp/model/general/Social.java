package com.ruirua.sampleguideapp.model.general;

import com.google.gson.annotations.SerializedName;

public class Social {

    @SerializedName("social_name")
    String name;

    @SerializedName("social_url")
    String url;

    @SerializedName("social_share_link")
    String share_link;

    @SerializedName("social_app")
    String app;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getShare_link() {
        return share_link;
    }

    public String getApp() {
        return app;
    }
}
