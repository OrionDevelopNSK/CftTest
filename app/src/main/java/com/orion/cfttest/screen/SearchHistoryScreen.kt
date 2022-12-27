package com.orion.cfttest.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.orion.cfttest.R
import com.orion.cfttest.model.Card
import com.orion.cfttest.screen.container.*
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel
import androidx.compose.material3.Card as Material3Card


@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun SearchHistoryScreen(viewModel: BaseViewModel, onNavigateToMainScreen: () -> Unit) {
    val listState = rememberLazyListState()
    val cards = viewModel.cardList.observeAsState(mutableListOf())
    val isExpandedList = mutableListOf<MutableState<Boolean>>()

    if (viewModel.cardList.value != null && viewModel.cardList.value!!.isNotEmpty()) {
        for (i in 0 until viewModel.cardList.value!!.size) {
            isExpandedList.add(remember { mutableStateOf(false) })
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(R.string.search_history),
                fontSize = dimensionResourceSp(id = R.dimen.headline),
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            state = listState,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
        ) {
            itemsIndexed(items = cards.value) { index, item: Card ->
                Material3Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .padding(vertical = 8.dp)

                        .clickable {
                            isExpandedList[index].value = !isExpandedList[index].value
                        },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = stringResource(R.string.bin_inn) + ": " + item.bin,
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.CenterHorizontally),
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
                    )

                    if (isExpandedList[index].value) {
                        CardContainer(viewModel = viewModel, card = item)
                        Spacer(modifier = Modifier.height(16.dp))
                        CardNumberContainer(viewModel = viewModel, card = item)
                        Spacer(modifier = Modifier.height(16.dp))
                        CountryContainer(viewModel = viewModel, card = item)
                        Spacer(modifier = Modifier.height(16.dp))
                        BankContainer(viewModel = viewModel, card = item)
                    }
                }
            }
        }
    }

    IconButton(
        onClick = onNavigateToMainScreen, modifier = Modifier.requiredSize(50.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.back),
            contentDescription = "back",
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}









