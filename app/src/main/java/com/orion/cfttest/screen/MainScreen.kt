package com.orion.cfttest.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.height(16.dp).weight(0.025f))

        Card(
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState()).weight(0.9f),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            FinderContainer(
                viewModel = viewModel
            )
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CardContainer(viewModel = viewModel, card = card)
                Spacer(modifier = Modifier.height(16.dp))
                CardNumberContainer(viewModel = viewModel, card = card)
                Spacer(modifier = Modifier.height(16.dp))
                CountryContainer(viewModel = viewModel, card = card)
                Spacer(modifier = Modifier.height(16.dp))
                BankContainer(viewModel = viewModel, card = card)
            }
        }

        Spacer(modifier = Modifier.height(16.dp).weight(0.020f))

        Button(
            modifier = Modifier
                .wrapContentWidth()
                .padding(bottom = 12.dp).weight(0.08f),
            shape = RoundedCornerShape(15.dp),
            onClick = {
                viewModel.loadCard()
                onNavigateToSearchHistoryScreen.invoke()
            }
        ,colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Icon(
                painterResource(id = R.drawable.menu),
                contentDescription = stringResource(R.string.search_history),
            )
            Text(
                stringResource(R.string.search_history),
                fontSize = dimensionResourceSp(id = R.dimen.headline),
                fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp).weight(0.020f))

    }
}









