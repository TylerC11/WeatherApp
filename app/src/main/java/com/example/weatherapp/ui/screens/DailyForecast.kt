package com.example.weatherapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun DailyForecastScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.sunny),
                    contentDescription = "Sunny",
                    modifier = Modifier.size(120.dp)
                )
                Text("Date: Sept 23, 2025")
                Text("Temperature: High: 22°C Low: 15°C")
                Text("Condition: Sunny")
                Text("Precipitation: Rain, 1 mm Chance: 5%")
                Text("Wind: SW 25 km/h")
                Text("Humidity: 85")
            }
        }
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.rainy),
                    contentDescription = "Rainy",
                    modifier = Modifier.size(120.dp)
                )
                Text("Date: Sept 24, 2025")
                Text("Temperature: High: 17°C Low: 12°C")
                Text("Condition: Rainy")
                Text("Precipitation: Rain, 19 mm Chance: 82%")
                Text("Wind: SE 20 km/h")
                Text("Humidity: 60")
            }
        }
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.cloudy),
                    contentDescription = "Cloudy",
                    modifier = Modifier.size(120.dp)
                )
                Text("Date: Sept 25, 2025")
                Text("Temperature: High: 15°C Low: 7°C")
                Text("Condition: Cloudy")
                Text("Precipitation: Rain, 3 mm Chance: 15%")
                Text("Wind: NW 15 km/h")
                Text("Humidity: 70")
            }
        }
        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.snowy),
                    contentDescription = "Snowy",
                    modifier = Modifier.size(120.dp)
                )
                Text("Date: Sept 26, 2025")
                Text("Temperature: High: 5°C Low: -15°C")
                Text("Condition: Snowy")
                Text("Precipitation: Snow, 21 cm Chance: 85%")
                Text("Wind: NE 15 km/h")
                Text("Humidity: 40")
            }
        }
    }
}