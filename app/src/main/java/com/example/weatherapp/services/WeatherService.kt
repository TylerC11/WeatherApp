package com.example.weatherapp.services

import com.example.weatherapp.models.Weather
import com.example.weatherapp.models.Forecast
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface WeatherService {
    @GET("v1/forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int = 14
    ): Weather
}