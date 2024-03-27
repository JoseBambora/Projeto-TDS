package com.ruirua.sampleguideapp.model.pins;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return id == pin.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int getId() {
        return id;
    }

    public String getPin_name() {
        return pin_name;
    }

    public String getPin_desc() {
        return pin_desc;
    }

    public float getPin_lat() {
        return pin_lat;
    }

    public float getPin_lng() {
        return pin_lng;
    }

    public float getPin_alt() {
        return pin_alt;
    }
}
