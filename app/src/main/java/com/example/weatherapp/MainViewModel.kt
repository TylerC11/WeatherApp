package com.example.weatherapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.weatherapp.models.Weather
import com.example.weatherapp.models.Current
import com.example.weatherapp.models.Forecast
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainViewModel : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather = _weather.asStateFlow()

    init {
        _weather.value = Weather(
            name = "Current Forecast",
            current = listOf(
                Current(
                    condition = Current.Condition(
                        text = "Sunny",
                        icon = "sunny.png",
                        code = 1000
                    ),
                    temp_c = 22,
                    precip_mm = 2.2,
                    wind_kph = "S 10km/h"
                )
            ),
            forecast = listOf(
                Forecast(LocalDate.of(2025, 10, 8), 22,15,Forecast.Condition("Sunny", "sunny.png", 1000), 2.2, "S 10km/h", 60),
                Forecast(LocalDate.of(2025, 10, 9), 20,12, Forecast.Condition("Cloudy", "cloudy.png", 1000), 5.3, "SW 12km/h", 55),
                Forecast(LocalDate.of(2025, 10, 10), 18,16, Forecast.Condition("Rainy", "rainy.png", 1000), 10.2, "W 8km/h", 70),
                Forecast(LocalDate.of(2025, 10, 11), 21,19, Forecast.Condition("Snowy", "snowy.png", 1000), 0.2, "S 5km/h", 50)
            )
        )
    }
}
