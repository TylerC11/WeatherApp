package com.example.weatherapp.models

import java.time.LocalDate


data class Weather(
    val name: String,
    val current: List<Current> = emptyList(),
    val forecast: List<Forecast> = emptyList()
)

data class Current(
    val condition: String,
    val temperature: Int,
    val precipitation: String,
    val wind: String,
)

data class Forecast(
    val date: LocalDate,
    val hightemperature: Int,
    val lowtemperature: Int,
    val condition: String,
    val precipitation: String,
    val wind: String,
    val humidity: Int
)



