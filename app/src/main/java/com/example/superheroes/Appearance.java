package com.example.superheroes;

import com.google.gson.annotations.SerializedName;
public class Appearance {


    private String gender;
    private String race;
    private String[] height;
    private String[] weight;
    private String eyeColor;
    private String hairColor;


    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public String[] getHeight() {
        return height;
    }

    public String[] getWeight() {
        return weight;
    }

    public String getHaircolor() {
        return hairColor;
    }

    public String getEyecolor() {
        return eyeColor;
    }
}