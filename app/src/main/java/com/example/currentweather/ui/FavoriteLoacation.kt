package com.example.currentweather.ui

import androidx.compose.runtime.Composable

//@Composable
//fun FavoriteLocations(
//
//    favoriteLocations: List<CurrentWeather>,
//    onLocationSelected: (location: CurrentWeather) -> Unit,
//    onLocationDeleted: (location: String) -> Unit,
//) {
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth(),
//        horizontalArrangement = Arrangement.spacedBy(8.dp)
//    ) {
//        items(favoriteLocations) { location ->
//            FavoriteLocationCard(
//                location = location,
//
//                onLocationSelected = { onLocationSelected(location) },
//                onLocationDeleted = { onLocationDeleted(location.location.name) },
//            )
//        }
//    }
//}

@Composable
fun FavoriteLocationCard(
//    location: CurrentWeather,
    onLocationSelected: () -> Unit, onLocationDeleted: () -> Unit,
) {


}
