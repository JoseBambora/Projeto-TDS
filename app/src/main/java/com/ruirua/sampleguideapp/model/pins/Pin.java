package com.ruirua.sampleguideapp.model.pins;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pin",indices = @Index(value = {"id"},unique = true))
public class Pin {
    @PrimaryKey
    @ColumnInfo(name = "id")
    int id;

    @SerializedName("pin_name")
    @ColumnInfo(name = "pin_name")
    String pin_name;

    @SerializedName("pin_desc")
    @ColumnInfo(name="pin_desc")
    String pin_desc;

    @SerializedName("pin_lat")
    @ColumnInfo(name="pin_lat")
    float pin_lat;

    @SerializedName("pin_lng")
    @ColumnInfo(name="pin_lng")
    float pin_lng;

    @SerializedName("pin_alt")
    @ColumnInfo(name="pin_alt")
    float pin_alt;

    @NonNull
    @Override
    public String toString() {
        return "Pin{" +
                "id=" + id +
                ", pin_name='" + pin_name + '\'' +
                ", pin_desc='" + pin_desc + '\'' +
                ", pin_lat=" + pin_lat +
                ", pin_lng=" + pin_lng +
                ", pin_alt=" + pin_alt +
                '}';
    }
}
