package com.example.weatherapp.models

import java.time.LocalDate


data class Weather(
    val name: String,
    val current: List<Current> = emptyList(),
    val forecast: List<Forecast> = emptyList()
)

data class Current(
    val condition: Condition,
    val temp_c: Int, //
    val precip_mm: Double, //
    val wind_kph: String, //
) {
    data class Condition(
        val text: String,
        val icon: String,
        val code: Int
    )
}

data class Forecast(
    val last_updated: LocalDate,
    val maxtemp_c: Int, //
    val mintemp_c: Int, //
    val condition: Condition,
    val precip_mm: Double, //
    val wind_kph: String, //
    val humidity: Int //
){
    data class Condition(
        val text: String,
        val icon: String,
        val code: Int
    )
}



