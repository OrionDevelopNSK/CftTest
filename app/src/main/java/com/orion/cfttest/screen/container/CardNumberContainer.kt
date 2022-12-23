package com.orion.cfttest.screen.container

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.orion.cfttest.BaseViewModel
import com.orion.cfttest.R
import com.orion.cfttest.retrofit.Card
import com.orion.cfttest.util.dimensionResourceSp

@Composable
fun CardNumberContainer(viewModel: BaseViewModel, card: Card?) {

    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = stringResource(R.string.card_number),
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResourceSp(id = R.dimen.headline),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
    )

    Row(
        modifier = Modifier
            .padding(16.dp, top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stringResource(R.string.length),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = viewModel.getLength(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.type),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = viewModel.getType(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.algorithm_luhn),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = viewModel.getLuhn(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.prepaid),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primary,
            )
            Text(
                text = viewModel.getPrepared(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary,
            )
        }
    }
    Divider(modifier = Modifier.padding(bottom = 4.dp, start = 16.dp, end = 16.dp))
}