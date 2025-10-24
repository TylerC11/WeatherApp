package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: ForecastContainer
)

data class Location(
    val name: String,
    val region: String,
    val country: String
)

data class Current(
    val condition: Condition,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("wind_kph") val windKph: Double
) {
    data class Condition(
        val text: String,
        val icon: String,
        val code: Int
    )
}

data class ForecastContainer(
    @SerializedName("forecastday") val forecastDay: List<Forecast>
)

data class Forecast(
    val date: String,
    val day: Day
)

data class Day(
    @SerializedName("maxtemp_c") val maxTempC: Double,
    @SerializedName("mintemp_c") val minTempC: Double,
    @SerializedName("avgtemp_c") val avgTempC: Double,
    val condition: Condition,
    @SerializedName("totalprecip_mm") val totalPrecipMm: Double,
    @SerializedName("maxwind_kph") val maxWindKph: Double,
    val avghumidity: Double
) {
    data class Condition(
        val text: String,
        val icon: String,
        val code: Int
    )
}
