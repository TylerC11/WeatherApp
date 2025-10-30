package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.weatherapp.services.WeatherService
import com.example.weatherapp.models.Weather
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.weatherapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    init {
        fetchWeather()
    }

    private fun fetchWeather() {
        viewModelScope.launch {
                val apiKey = "73bde2e0c1824c67981174902251510"
                val response = weatherService.getForecast(apiKey, "Halifax", 14)
                _weather.value = response

        }
    }
}
