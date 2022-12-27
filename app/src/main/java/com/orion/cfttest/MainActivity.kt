package com.orion.cfttest

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orion.cfttest.screen.MainScreen
import com.orion.cfttest.screen.SearchHistoryScreen
import com.orion.cfttest.ui.theme.CftTestTheme
import com.orion.cfttest.viewmodel.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: BaseViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityResultLauncher.launch(
            arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.CALL_PHONE
            )
        )
    }

    @SuppressLint("SourceLockedOrientationActivity")
    @RequiresApi(Build.VERSION_CODES.R)
    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var isGranted = false
            permissions.entries.forEach {
                isGranted = it.value
            }
            if (isGranted) {
                setContent {
                    CftTestTheme {
                        Surface(color =  MaterialTheme.colorScheme.onSurface) {
                            createStrictModePolicy()
                            AppNavHost(viewModel = viewModel)
                            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }
                    }
                }
            } else {
                Toast.makeText(this, getString(R.string.access_denied), Toast.LENGTH_SHORT).show()
            }
        }

    @RequiresApi(Build.VERSION_CODES.R)
    @Composable
    fun AppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = "mainScreen",
        viewModel: BaseViewModel
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("mainScreen") {
                MainScreen(
                    viewModel = viewModel,
                    onNavigateToSearchHistoryScreen = { navController.navigate("searchHistoryScreen") }
                )
            }
            composable("searchHistoryScreen") {
                SearchHistoryScreen(viewModel = viewModel, onNavigateToMainScreen = {navController.popBackStack()})
            }
        }
    }
}

fun createStrictModePolicy() {
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
}





