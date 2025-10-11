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
        // Ensure everything is properly initialized
        _weather.value = Weather(
            name = "Current Forecast",
            current = listOf(
                Current(
                    condition = "Sunny",
                    temperature = 22,
                    precipitation = "5mm",
                    wind = "S 10km/h"
                )
            ),
            forecast = listOf(
                Forecast(LocalDate.of(2025, 10, 8), 22,15,"Sunny", "5mm", "S 10km/h", 60),
                Forecast(LocalDate.of(2025, 10, 9), 20,12, "Cloudy", "2mm", "SW 12km/h", 55),
                Forecast(LocalDate.of(2025, 10, 10), 18,16, "Rainy", "10mm", "W 8km/h", 70),
                Forecast(LocalDate.of(2025, 10, 11), 21,19, "Sunny", "0mm", "S 5km/h", 50)
            )
        )
    }
}
