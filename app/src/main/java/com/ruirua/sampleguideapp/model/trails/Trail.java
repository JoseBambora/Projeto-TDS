package com.ruirua.sampleguideapp.model.trails;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity(tableName = "trail",indices = @Index(value = {"id"},unique = true))
public class Trail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    String id;

    @SerializedName("trail_img")
    @ColumnInfo(name = "trail_img")
    String image_url;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }



    public String getUrl() {
        return image_url;
    }

    public void setUrl(String url) {
        this.image_url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trail trail = (Trail) o;
        return id.equals(trail.id) &&
                Objects.equals(image_url, trail.image_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image_url);
    }

    @NonNull
    @Override
    public String toString() {
        return "Trail{" +
                "id='" + id + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
