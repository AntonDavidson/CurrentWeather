package com.example.currentweather.ui.permissions

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LocationPermissionRequestDialog(
    onPermissionGranted: @Composable () -> Unit,
    onDismissed: () -> Unit
) {
    val context = LocalContext.current

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )

    val isPermanentlyDenied = locationPermissionsState.permissions.any {
        !it.status.shouldShowRationale && !it.status.isGranted
    }



    if (locationPermissionsState.allPermissionsGranted) {
        onPermissionGranted()
    } else {

        AlertDialog(
            onDismissRequest = { onDismissed() },
            title = { Text("Request Precise ApiLocation") },
            text = {
                Text(
                    if (isPermanentlyDenied) {
                        "You have permanently denied apiLocation permission. Please enable it in settings."
                    } else {
                        "In order to use this feature, please grant access by accepting the apiLocation permission dialog. Would you like to continue?"
                    }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    if (isPermanentlyDenied) {
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", context.packageName, null)
                        )
                        context.startActivity(intent)
                    } else {
                        locationPermissionsState.launchMultiplePermissionRequest()
                    }
                }) {
                    Text(if (isPermanentlyDenied) "Open Settings" else "Continue")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    onDismissed()
                }) {
                    Text("Dismiss")
                }
            }
        )
    }
}