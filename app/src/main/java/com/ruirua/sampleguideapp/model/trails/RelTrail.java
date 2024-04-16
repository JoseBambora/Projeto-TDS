package com.ruirua.sampleguideapp.model.trails;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "relTrail", indices = @Index(value = {"id"}, unique = true))
public class RelTrail {
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("value")
    @ColumnInfo(name = "value")
    private String value;

    @SerializedName("attrib")
    @ColumnInfo(name = "attrib")
    private String attrib;

    @SerializedName("trail")
    @ColumnInfo(name = "trail")
    private int trail;

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

    public String getAttrib() {
        return attrib;
    }

    public void setAttrib(String attrib) {
        this.attrib = attrib;
    }

    public int getTrail() {
        return trail;
    }

    public void setTrail(int trail) {
        this.trail = trail;
    }

    @Override
    public String toString() {
        return "RelTrail{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", attrib='" + attrib + '\'' +
                ", trail=" + trail +
                '}';
    }
}