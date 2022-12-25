package com.orion.cfttest.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orion.cfttest.R
import com.orion.cfttest.screen.container.*
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(viewModel: BaseViewModel, onNavigateToSearchHistoryScreen: () -> Unit) {
    val card = viewModel.card.observeAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FinderContainer(
            viewModel = viewModel
        )
        Divider(modifier = Modifier.padding(bottom = 4.dp))
        Surface(
            modifier = Modifier
                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 5.dp
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
        Button(
            modifier = Modifier
                .wrapContentWidth().padding(bottom = 12.dp),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                viewModel.loadCard()
                onNavigateToSearchHistoryScreen.invoke()
            }
        ) {
            Text(
                stringResource(R.string.search_history),
                fontSize = dimensionResourceSp(id = R.dimen.headline)
            )
        }
    }
}









