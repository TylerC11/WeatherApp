package com.example.weatherapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.screens.CurrentWeatherScreen
import com.example.weatherapp.ui.screens.DailyForecastScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                DisplayUI()
                GetLocation()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DisplayUI() {
        val navController = rememberNavController()
        var selectedIndex by remember { mutableIntStateOf(0) }
        val weather by mainViewModel.weather.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    title = {
                        val city = weather?.location?.name
                        val country = weather?.location?.country
                        Text(
                            text = if (city != null && country != null) "$city, $country" else "Loading..."
                        )
                    }
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_action_current),
                                contentDescription = "CurrentWeather"
                            )
                        },
                        label = { Text("Current Weather") },
                        selected = selectedIndex == 0,
                        onClick = {
                            selectedIndex = 0
                            navController.navigate(route = "CurrentWeather")
                        }
                    )

                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_action_daily),
                                contentDescription = "DailyForecast"
                            )
                        },
                        label = { Text("Daily Forecast") },
                        selected = selectedIndex == 1,
                        onClick = {
                            selectedIndex = 1
                            navController.navigate(route = "DailyForecast")
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = "CurrentWeather",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(route = "CurrentWeather") {
                    CurrentWeatherScreen(mainViewModel = mainViewModel)
                }
                composable(route = "DailyForecast") {
                    DailyForecastScreen(mainViewModel = mainViewModel)
                }
            }
        }
    }
    @OptIn(ExperimentalPermissionsApi::class, ExperimentalPermissionsApi::class)
    @Composable
    fun GetLocation() {
        // Remember the permission state(asking for Fine location)
        val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionState.status.isGranted) {
            Log.i("TESTING", "Hurray, permission granted!")

            // Get Location
            val currentContext = LocalContext.current
            val fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(currentContext)

            if (ContextCompat.checkSelfPermission(
                    currentContext,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val cancellationTokenSource = CancellationTokenSource()

                Log.i("TESTING", "Requesting location...")

                fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
                )
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            val lat = location.latitude.toString()
                            val lng = location.longitude.toString()

                            Log.i("TESTING", "Success: $lat $lng")

                            val coordinates = "$lat,$lng"

                            // call a function, like in View Model, to do something with location...
                            mainViewModel.fetchWeather(lat.toDouble(), lng.toDouble())
                        } else {
                            Log.i("TESTING", "Problem encountered: Location returned null")
                        }
                    }
            }
        } else {
            // Run a side-effect (coroutine) to get permission. The permission popup.
            LaunchedEffect(permissionState) {
                permissionState.launchPermissionRequest()
            }
        }
    }
}
