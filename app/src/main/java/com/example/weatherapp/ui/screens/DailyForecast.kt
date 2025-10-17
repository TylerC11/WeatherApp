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
        items(weather?.forecast ?: emptyList()) { forecast ->
            ForecastItem(forecast)
        }
    }
}

@Composable
fun ForecastItem(forecast: Forecast?) {

    val condition = forecast?.condition?.text?.lowercase() ?: "sunny"
    val imageResId = when (condition) {
        "sunny" -> R.drawable.sunny
        "rainy" -> R.drawable.rainy
        "cloudy" -> R.drawable.cloudy
        "snowy" -> R.drawable.snowy
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
        Text("Date: ${forecast?.last_updated ?: "--"}")
        Text("Temperature: High: ${forecast?.maxtemp_c ?: "--"}°C Low: ${forecast?.mintemp_c ?: "--"}°C")
        Text("Condition: ${forecast?.condition?.text ?: "--"}")
        Text("Precipitation: ${forecast?.precip_mm ?: "--"}")
        Text("Wind: ${forecast?.wind_kph ?: "--"}")
        Text("Humidity: ${forecast?.humidity ?: "--"}")
    }
}
