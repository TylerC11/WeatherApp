package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.weatherapp.R
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.CurrentWeatherScreen
import com.example.weatherapp.ui.screens.DailyForecastScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme



// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                // Starting point for the app
                DisplayUI()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI() {

    val navController = rememberNavController()

    // Store the selectedIndex in a state flow variable
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            // Display a top app bar with a title
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    Text("Halifax, Nova Scotia")
                }
            )
        },
        bottomBar = {
            // Display a navigation bar
            NavigationBar (
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            )
            {
                // Navigation Bar Items go here...

                // Home screen
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_action_current),
                            contentDescription = "CurrentWeather"
                        )
                    },
                    label = {
                        Text("Current Weather")
                    },
                    selected = selectedIndex == 0,
                    onClick = {
                        selectedIndex = 0
                        navController.navigate(route = "CurrentWeather")
                    }
                )

                // Hello screen
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.ic_action_daily),
                            contentDescription = "DailyForecast"
                        )
                    },
                    label = {
                        Text("Daily Forecast")
                    },
                    selected = selectedIndex == 1,
                    onClick = {
                        selectedIndex = 1
                        navController.navigate(route = "DailyForecast")
                    }
                )
            }
        }
    ) { innerPadding ->
        // Display screens inside a NavHost()

        NavHost(
            navController = navController,
            startDestination = "CurrentWeather",
            modifier = Modifier.padding(innerPadding)
        )
        {
            // Render Home screen
            composable(route = "CurrentWeather") {
                CurrentWeatherScreen()
                //Hello()
            }

            // Render Hello screen
            composable(route = "DailyForecast") {
                DailyForecastScreen()
            }
        }

    }

}