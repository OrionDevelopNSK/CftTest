package com.orion.cfttest.screen

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.orion.cfttest.BaseViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun Information(viewModel: BaseViewModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UpContainer(viewModel = viewModel)
        Spacer(modifier = Modifier.height(18.dp))
        FirstContainer(viewModel = viewModel)
        Spacer(modifier = Modifier.height(18.dp))
        SecondContainer(viewModel = viewModel)
        Spacer(modifier = Modifier.height(18.dp))
        ThirdContainer(viewModel = viewModel)
    }
}

@Composable
fun UpContainer(viewModel: BaseViewModel) {
    val bin = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(55.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {

        OutlinedTextField(
            value = bin.value,
            modifier = Modifier
                .height(55.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.Top),
            maxLines = 1,
            singleLine = true,
            onValueChange = { bin.value = it },
            label = {
                Text(
                    text = "Enter the first digits of a card number (BIN/IIN)",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(5.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp)
        )
    }
}

@Composable
fun FirstContainer(viewModel: BaseViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SCHEME / NETWORK",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = "Visa",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Spacer(modifier = Modifier.height(0.dp))

        Text(
            text = "BRAND",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = "Visa/Dankort",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Spacer(modifier = Modifier.height(0.dp))

        Text(
            text = "CARD NUMBER",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = "LENGTH",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "16",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
            }

            Spacer(modifier = Modifier.width(24.dp))
            Column {
                Text(
                    text = "LUHN",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "Yes / No",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
            }
        }
    }
}

@Composable
fun SecondContainer(viewModel: BaseViewModel) {
    val context = LocalContext.current as Activity
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TYPE",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = "Debit / Credit",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "PREPAID",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Text(
            text = "Yes / No",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "COUNTRY",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row {
                Text(
                    text = "\uD83C\uDDE9\uD83C\uDDF0",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = "Denmark",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
            }

            Spacer(modifier = Modifier.width(24.dp))
            Row(modifier = Modifier.clickable {
                viewModel.openMap()
                //TODO
                val intent =
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q=loc:" + "56" + "," + "10")
                    )
                startActivity(context, intent, null)

            }) {
                Text(
                    text = "(latitude: ",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = "56",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = ", longitude: ",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = "10",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
                Text(
                    text = ")",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
            }
        }
    }
}

@Composable
fun ThirdContainer(viewModel: BaseViewModel) {
    val context = LocalContext.current as Activity
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BANK",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Row {
            Text(
                text = "Jyske Bank",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Hjørring",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
            )
        }


        Text(
            modifier = Modifier.clickable {
                //TODO
                viewModel.openBrowser()
                val intent =
                    Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.jyskebank.dk"))
                startActivity(context, intent, null)
            },
            text = "www.jyskebank.dk",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Text(
            modifier = Modifier.clickable {
                //TODO
                viewModel.callPhone()
                val intent =
                    Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+4589893300"))
                startActivity(context, intent, null)
            },
            text = "+4589893300",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

    }
}