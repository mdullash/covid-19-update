package com.example.covid_19update.service;

import com.example.covid_19update.model.Countries;
import com.example.covid_19update.model.WorldBd;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("all")
    Call<WorldBd> getWorldInfo();

    @GET("countries")
    Call<List<Countries>> getAllCountry();

    @GET("countries/bd")
    Call<WorldBd> getBdInfo();
}
