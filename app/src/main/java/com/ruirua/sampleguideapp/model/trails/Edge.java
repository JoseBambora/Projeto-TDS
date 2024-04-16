package com.ruirua.sampleguideapp.model.trails;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.ruirua.sampleguideapp.model.pins.Pin;

@Entity(tableName = "edge", indices = @Index(value = {"id"}, unique = true))
@TypeConverters({PinConverter.class})
public class Edge {
    @SerializedName("id")
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("edge_start")
    private Pin edgeStart;

    @SerializedName("edge_end")
    private Pin edgeEnd;

    @SerializedName("edge_transport")
    @ColumnInfo(name = "edge_transport")
    private String edgeTransport;

    @SerializedName("edge_duration")
    @ColumnInfo(name = "edge_duration")
    private int edgeDuration;

    @SerializedName("edge_desc")
    @ColumnInfo(name = "edge_desc")
    private String edgeDescription;

    @SerializedName("edge_trail")
    @ColumnInfo(name = "edge_trail")
    private int edgeTrail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pin getEdgeStart() {
        return edgeStart;
    }

    public void setEdgeStart(Pin edgeStart) {
        this.edgeStart = edgeStart;
    }

    public Pin getEdgeEnd() {
        return edgeEnd;
    }

    public void setEdgeEnd(Pin edgeEnd) {
        this.edgeEnd = edgeEnd;
    }

    public String getEdgeTransport() {
        return edgeTransport;
    }

    public void setEdgeTransport(String edgeTransport) {
        this.edgeTransport = edgeTransport;
    }

    public int getEdgeDuration() {
        return edgeDuration;
    }

    public void setEdgeDuration(int edgeDuration) {
        this.edgeDuration = edgeDuration;
    }

    public String getEdgeDescription() {
        return edgeDescription;
    }

    public void setEdgeDescription(String edgeDescription) {
        this.edgeDescription = edgeDescription;
    }

    public int getEdgeTrail() {
        return edgeTrail;
    }

    public void setEdgeTrail(int edgeTrail) {
        this.edgeTrail = edgeTrail;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id=" + id +
                ", edgeStart=" + edgeStart +
                ", edgeEnd=" + edgeEnd +
                ", edgeTransport='" + edgeTransport + '\'' +
                ", edgeDuration=" + edgeDuration +
                ", edgeDescription='" + edgeDescription + '\'' +
                ", edgeTrail=" + edgeTrail +
                '}';
    }
}