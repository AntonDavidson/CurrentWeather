package com.example.currentweather

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.currentweather.navigation.NavigationHost
import com.example.currentweather.navigation.Rout
import com.example.currentweather.ui.components.BottomBar
import com.example.currentweather.ui.theme.CurrentWeatherTheme
import com.example.currentweather.ui.viewmodel.DialogViewModel
import com.example.currentweather.ui.viewmodel.SavedLocationsViewModel
import com.example.currentweather.ui.viewmodel.WeatherViewModel
import com.example.currentweather.ui.viewmodel.weather_view_state.SavedLocationsState
import com.example.currentweather.ui.viewmodel.weather_view_state.UnitSystem
import com.example.currentweather.ui.viewmodel.weather_view_state.WeatherViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val savedLocationsViewModel: SavedLocationsViewModel by viewModels()
    private val dialogViewModel: DialogViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        setContent {
            CurrentWeatherTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray
                ) {
                    val weather = weatherViewModel.weather.collectAsState()
                    val savedLocations = savedLocationsViewModel.weather.collectAsState()
                    val dialogWeather = dialogViewModel.weather.collectAsState()
                    WeatherApp(
                        weatherViewModel = weatherViewModel,
                        savedLocationsViewModel = savedLocationsViewModel,
                        dialogViewModel = dialogViewModel,
                        weather.value,
                        dialogWeather.value,
                        savedLocations.value
                    )
//                    MainScreen(weather.value, UnitSystem.METRIC) { event ->
//                        (weatherViewModel::onViewEvent)(event)
//                    }
                }
            }
        }
    }


    @Composable
    fun WeatherApp(
        weatherViewModel: WeatherViewModel,
        savedLocationsViewModel: SavedLocationsViewModel,
        dialogViewModel: DialogViewModel,
        weather: WeatherViewState,
        dialogWeather: WeatherViewState, savedLocations: SavedLocationsState

    ) {

        val navController = rememberNavController()
        Scaffold(bottomBar = {
            BottomBar {
                navController.navigate(Rout.SavedLocationsScreen.screenRoute)
            }
        }) { paddingValues ->
            NavigationHost(
                navController = navController,
                paddingValues = paddingValues,
                viewEvent = { (weatherViewModel::onMainWeatherEvent)(it) },
                dialogEvent = { (dialogViewModel::onDialogEvent)(it) },
                savedLocationsEvent = {
                    (
                            savedLocationsViewModel::onSavedLocationsEvent)(
                        it,
                        UnitSystem.METRIC
                    )
                },
                savedLocations = savedLocations,
                dialogWeather = dialogWeather,
                weatherState = weather,
                unitSystem = UnitSystem.METRIC
            )
        }
    }
}
