package com.example.superheroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfo {

    int id;
    String name;
    String slug;
    @SerializedName("powerstats")
    @Expose
    PowerStats powerStats;
    @SerializedName("appearance")
    @Expose
    Appearance appearance;
    @SerializedName("biography")
    @Expose
    Biography biography;
    @SerializedName("work")
    @Expose
    Work work;
    @SerializedName("connections")
    @Expose
    Connections connections;
    @SerializedName("images")
    @Expose
    Images images;

    public BasicInfo(int id, String name, Images image) {
        this.id=id;
        this.name=name;
        this.images=image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public PowerStats getPowerStats() {
        return powerStats;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public Biography getBiography() {
        return biography;
    }

    public Work getWork() {
        return work;
    }

    public Connections getConnections() {
        return connections;
    }

    public Images getImages() {
        return images;
    }
}
