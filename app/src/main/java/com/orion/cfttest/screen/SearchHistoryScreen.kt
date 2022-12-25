package com.orion.cfttest.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
fun SearchHistoryScreen(viewModel: BaseViewModel) {
    val listState = rememberLazyListState()
    val cards = viewModel.cardList.observeAsState(mutableListOf())

    val isExpanded = mutableListOf<MutableState<Boolean>>()
    if (viewModel.cardList.value != null) {
        for (i in 0 until viewModel.cardList.value!!.size) {
            isExpanded.add(remember { mutableStateOf(false) })
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {

        Text(
            stringResource(R.string.search_history),
            fontSize = dimensionResourceSp(id = R.dimen.headline),
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
        )

        Surface(
            modifier = Modifier

                .padding(8.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 5.dp
        ) {
            LazyColumn(
                state = listState,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                itemsIndexed(items = cards.value) { index, item: Card ->
                    Material3Card(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                        .padding(vertical = 8.dp)

                        .clickable {
                            isExpanded[index].value = !isExpanded[index].value
                        }) {
                        Text(
                            text = item.bin,
                            modifier = Modifier
                                .padding(12.dp)
                                .align(Alignment.CenterHorizontally),
                            color = MaterialTheme.colorScheme.primary,
                        )

                        if (isExpanded[index].value) {
                            CardContainer(viewModel = viewModel, card = item)
                            CardNumberContainer(viewModel = viewModel, card = item)
                            CountryContainer(viewModel = viewModel, card = item)
                            BankContainer(viewModel = viewModel, card = item)
                        }
                    }
                }
            }
        }
    }
}









