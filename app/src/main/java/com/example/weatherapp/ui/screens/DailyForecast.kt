package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.models.Forecast

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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https:${forecast.day.condition.icon ?: ""}",
            contentDescription = forecast.day.condition.text,
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
