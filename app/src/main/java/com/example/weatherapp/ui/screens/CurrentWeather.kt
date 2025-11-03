package com.example.weatherapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherapp.MainViewModel

@Composable
fun CurrentWeatherScreen(mainViewModel: MainViewModel) {
    val weather by mainViewModel.weather.collectAsState()
    val current = weather?.current
    val hourlyList = weather?.forecast?.forecastDay?.firstOrNull()?.hour ?: emptyList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = "https:${current?.condition?.icon ?: ""}",
                contentDescription = current?.condition?.text ?: "Weather Icon",
                modifier = Modifier.size(128.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Condition: ${current?.condition?.text ?: "--"}")
            Text("Temperature: ${current?.tempC ?: "--"}°C")
            Text("Precipitation: ${current?.precipMm ?: "--"} mm")
            Text("Wind: ${current?.windKph ?: "--"} kph")
        }

        if (hourlyList.isNotEmpty()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Text("Hourly Forecast", modifier = Modifier.padding(bottom = 8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(hourlyList) { hourData ->
                        HourlyWeatherItem(hourData.time, hourData.tempC, hourData.condition.icon)
                    }
                }
            }
        }
    }
}

@Composable
fun HourlyWeatherItem(time: String, temperature: Double, iconUrl: String?) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .width(90.dp)
            .padding(vertical = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            val hourLabel = time.takeLast(5)
            Text(hourLabel)

            AsyncImage(
                model = "https:${iconUrl ?: ""}",
                contentDescription = "Hourly icon",
                modifier = Modifier.size(48.dp)
            )
            Text("${temperature}°C")
        }
    }
}
