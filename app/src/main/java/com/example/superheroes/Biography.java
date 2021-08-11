package com.example.superheroes;

import com.google.gson.annotations.SerializedName;

public class Biography {
    @SerializedName("fullName")
    String fullname;
    @SerializedName("alterEgos")
    String alteregos;
    String[] aliases;
    @SerializedName("placeofBirth")
    String placeofbirth;
    @SerializedName("firstAppearance")
    String firstappearance;
    String publisher;
    String alignment;

    public String getFullname() {
        return fullname;
    }

    public String getAlteregos() {
        return alteregos;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public String getFirstappearance() {
        return firstappearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAlignment() {
        return alignment;
    }
}
