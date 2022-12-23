package com.orion.cfttest.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orion.cfttest.BaseViewModel
import com.orion.cfttest.screen.container.*

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(viewModel: BaseViewModel) {
    val card = viewModel.card.observeAsState().value

    Column {
        FinderContainer(viewModel = viewModel)
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = 5.dp
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                CardContainer(viewModel = viewModel, card = card)
                CardNumberContainer(viewModel = viewModel, card = card)
                CountryContainer(viewModel = viewModel, card = card)
                BankContainer(viewModel = viewModel, card = card)

            }
        }
    }



}









