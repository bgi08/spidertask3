package com.example.superheroes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("all.json")
    Call<List<BasicInfo>> getBasicInfo();
    /*@GET("powerstats/1.json")
    Call<PowerStats> getPowerStats();
    @GET("appearance/1.json")
    Call<Appearance> getAppearance();
    @GET("biography/1.json")
    Call<Biography> getBiography();
    @GET("work/1.json")
    Call<Work> getWork();
    @GET("connections/1.json")
    Call<Connections> getConnections();
    @GET("1.json")
    Call<Images> getImages();*/


}
