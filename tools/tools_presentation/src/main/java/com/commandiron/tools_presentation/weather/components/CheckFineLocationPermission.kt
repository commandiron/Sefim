package com.commandiron.tools_presentation.weather.components

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun CheckFineLocationPermission(
    permissionsState: MultiplePermissionsState,
    permissionGranted:() -> Unit,
    permissionDenied:() -> Unit,
) {
    LaunchedEffect(key1 = permissionsState.revokedPermissions) {
        permissionsState.permissions.forEach { perm ->
            when(perm.permission){
                Manifest.permission.ACCESS_FINE_LOCATION -> {
                    when(perm.status){
                        PermissionStatus.Granted -> {
                            permissionGranted()
                        }
                        is PermissionStatus.Denied -> {
                            if(perm.status.shouldShowRationale){
                                permissionDenied()
                            }else{
                                //Waiting for response
                            }
                        }
                    }
                }
            }
        }
    }
}