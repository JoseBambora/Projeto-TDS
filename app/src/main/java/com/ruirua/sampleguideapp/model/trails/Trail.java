package com.ruirua.sampleguideapp.model.trails;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "trail", indices = @Index(value = {"id"}, unique = true))
@TypeConverters(TrailConverter.class) // Add TypeConverters annotation
public class Trail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("trail_img")
    @ColumnInfo(name = "trail_img")
    private String trailImg;

    @SerializedName("trail_languages")
    @ColumnInfo(name = "trail_languages")
    private CountryLangs trailLanguages;

    @SerializedName("trail_name")
    @ColumnInfo(name = "trail_name")
    private String trailName;

    @SerializedName("trail_desc")
    @ColumnInfo(name = "trail_desc")
    private String trailDesc;

    @SerializedName("trail_duration")
    @ColumnInfo(name = "trail_duration")
    private int trailDuration;

    @SerializedName("trail_difficulty")
    @ColumnInfo(name = "trail_difficulty")
    private String trailDifficulty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrailImg() {
        return trailImg;
    }

    public void setTrailImg(String trailImg) {
        this.trailImg = trailImg;
    }

    public CountryLangs getTrailLanguages() {
        return trailLanguages;
    }

    public void setTrailLanguages(CountryLangs trailLanguages) {
        this.trailLanguages = trailLanguages;
    }

    public String getTrailName() {
        return trailName;
    }

    public void setTrailName(String trailName) {
        this.trailName = trailName;
    }

    public String getTrailDesc() {
        return trailDesc;
    }

    public void setTrailDesc(String trailDesc) {
        this.trailDesc = trailDesc;
    }

    public int getTrailDuration() {
        return trailDuration;
    }

    public void setTrailDuration(int trailDuration) {
        this.trailDuration = trailDuration;
    }

    public String getTrailDifficulty() {
        return trailDifficulty;
    }

    public void setTrailDifficulty(String trailDifficulty) {
        this.trailDifficulty = trailDifficulty;
    }

    @NonNull
    @Override
    public String toString() {
        return "Trail{" +
                "id=" + id +
                ", trailImg='" + trailImg + '\'' +
                ", trailLanguages=" + trailLanguages +
                ", trailName='" + trailName + '\'' +
                ", trailDesc='" + trailDesc + '\'' +
                ", trailDuration=" + trailDuration +
                ", trailDifficulty='" + trailDifficulty + '\'' +
                '}';
    }
}
