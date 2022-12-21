package com.orion.cfttest

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.orion.cfttest.screen.Information
import com.orion.cfttest.ui.theme.CftTestTheme

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
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
//                            Greeting(viewModel)
                            Information(viewModel = viewModel)
                        }
                    }
                }
            } else {

                Toast.makeText(this, "В доступе отказано", Toast.LENGTH_SHORT).show()
            }
        }
}

@Composable
fun Greeting(viewModel: BaseViewModel) {
    val card = viewModel.card.observeAsState().value
    Text(text = card?.numberCard?.length.toString())
    val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
    StrictMode.setThreadPolicy(policy)
    viewModel.parse()
}





