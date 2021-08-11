package com.example.superheroes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {
    @SerializedName("lg")
    @Expose
    String imagesize;

    public String getSize() {
        return imagesize;
    }
}
