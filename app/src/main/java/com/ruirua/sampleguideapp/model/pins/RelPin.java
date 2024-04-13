package com.ruirua.sampleguideapp.model.pins;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity(tableName = "relpin",indices = @Index(value = {"id"},unique = true))
public class RelPin {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    int id;

    @SerializedName("value")
    String value;

    @SerializedName("attrib")
    String attribute;

    @SerializedName("pin")
    int idPin;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getIdPin() {
        return idPin;
    }

    public void setIdPin(int idPin) {
        this.idPin = idPin;
    }

    @Override
    public String toString() {
        return "RelPin{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", attribute='" + attribute + '\'' +
                ", idPin=" + idPin +
                '}';
    }
}
