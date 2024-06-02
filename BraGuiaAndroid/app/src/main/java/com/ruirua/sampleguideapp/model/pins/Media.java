package com.ruirua.sampleguideapp.model.pins;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "Media",indices = @Index(value = {"id"},unique = true))
public class Media {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    int id;

    @ColumnInfo(name="media_file")
    @SerializedName("media_file")
    String media_file;

    @ColumnInfo(name="media_type")
    @SerializedName("media_type")
    String media_type;

    @ColumnInfo(name="media_pin")
    @SerializedName("media_pin")
    int media_pin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedia_file() {
        return media_file;
    }

    public void setMedia_file(String media_file) {
        this.media_file = media_file;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public int getMedia_pin() {
        return media_pin;
    }

    public void setMedia_pin(int media_pin) {
        this.media_pin = media_pin;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", media_file='" + media_file + '\'' +
                ", media_type='" + media_type + '\'' +
                ", media_pin=" + media_pin +
                '}';
    }

    public boolean isImage() {
        return media_type.equals("I");
    }

    public boolean isAudio() {
        return media_type.equals("R");
    }

    public boolean isVideo() {
        return media_type.equals("V");
    }
}
