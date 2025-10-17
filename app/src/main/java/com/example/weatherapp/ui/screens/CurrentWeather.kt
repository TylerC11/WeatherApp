package com.example.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.MainViewModel


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen(mainViewModel: MainViewModel) {

    val weather by mainViewModel.weather.collectAsState()

    val current = weather?.current?.firstOrNull()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.sunny),
            contentDescription = "Weather Icon",
            modifier = Modifier.size(128.dp)
        )

        Text("Condition: ${current?.condition?.text ?: "--"}")
        Text("Temperature: ${current?.temp_c ?: "--"}°C")
        Text("Precipitation: ${current?.precip_mm ?: "--"}")
        Text("Wind: ${current?.wind_kph ?: "--"}")
    }
}

