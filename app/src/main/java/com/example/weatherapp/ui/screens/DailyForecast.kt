package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.models.Forecast

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecastScreen(mainViewModel: MainViewModel) {
    val weather by mainViewModel.weather.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val forecastList = weather?.forecast?.forecastDay ?: emptyList()
        items(forecastList) { forecastDay ->
            ForecastItem(forecastDay)
        }
    }
}
@Composable
fun ForecastItem(forecast: Forecast) {
    val condition = forecast.day.condition.text.lowercase()
    val imageResId = when {
        "sun" in condition -> R.drawable.sunny
        "rain" in condition -> R.drawable.rainy
        "cloud" in condition -> R.drawable.cloudy
        "snow" in condition -> R.drawable.snowy
        else -> R.drawable.sunny
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = "Weather",
            modifier = Modifier.size(120.dp)
        )

        Text("Date: ${forecast.date}")
        Text("Temperature: High: ${forecast.day.maxTempC}°C Low: ${forecast.day.minTempC}°C")
        Text("Condition: ${forecast.day.condition.text}")
        Text("Precipitation: ${forecast.day.totalPrecipMm} mm")
        Text("Wind: ${forecast.day.maxWindKph} kph")
        Text("Humidity: ${forecast.day.avghumidity}%")
    }
}
