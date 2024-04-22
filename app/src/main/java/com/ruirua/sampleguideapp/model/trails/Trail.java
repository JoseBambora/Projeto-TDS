package com.ruirua.sampleguideapp.model.trails;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;
import com.ruirua.sampleguideapp.model.pins.Pin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(tableName = "trail", indices = @Index(value = {"id"}, unique = true))
@TypeConverters({EdgeConverter.class,RelTrailConverter.class})
public class Trail {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("trail_img")
    @ColumnInfo(name = "trail_img")
    private String trailImg;


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

    @SerializedName("rel_trail")
    private List<RelTrail> reltrails;

    @SerializedName("edges")
    private List<Edge> edges;

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

    public List<RelTrail> getReltrails() {
        return reltrails;
    }

    public void setReltrails(List<RelTrail> reltrails) {
        this.reltrails = reltrails;
    }


    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Pin> getPins(){
        Set<Pin> pins = new HashSet<>();
        for (Edge edge: edges) {
            pins.add(edge.getEdgeStart());
            pins.add(edge.getEdgeEnd());
        }
        return new ArrayList<>(pins);
    }

    public List<Pin> getPinsInOrder() {
        List<Pin> pinsInOrder = new ArrayList<>();
        for (Edge edge : edges) {
            if (!pinsInOrder.contains(edge.getEdgeStart())) {
                pinsInOrder.add(edge.getEdgeStart());
            }
            if (!pinsInOrder.contains(edge.getEdgeEnd())) {
                pinsInOrder.add(edge.getEdgeEnd());
            }
        }
        return pinsInOrder;
    }

    @NonNull
    @Override
    public String toString() {
        return "Trail{" +
                "id=" + id +
                ", trailImg='" + trailImg + '\'' +
                ", trailName='" + trailName + '\'' +
                ", trailDesc='" + trailDesc + '\'' +
                ", trailDuration=" + trailDuration +
                ", trailDifficulty='" + trailDifficulty + '\'' +
                ", pins=" + getPins() +
                ", relTrails='" + reltrails +
                '}';
    }
}
