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
import com.example.weatherapp.models.Current


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrentWeatherScreen(mainViewModel: MainViewModel) {

    val weather by mainViewModel.weather.collectAsState()

    val current = weather?.current

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
        Text("Temperature: ${current?.tempC ?: "--"}°C")
        Text("Precipitation: ${current?.precipMm ?: "--"}")
        Text("Wind: ${current?.windKph ?: "--"}")
    }
}


