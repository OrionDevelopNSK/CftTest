package com.orion.cfttest.screen.container

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.orion.cfttest.R
import com.orion.cfttest.model.Card
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel

@Composable
fun CardContainer(viewModel: BaseViewModel, card: Card?) {

    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = stringResource(R.string.Card),
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResourceSp(id = R.dimen.headline),
        textAlign = TextAlign.Start,
        color = MaterialTheme.colorScheme.primary,
    )

    Row(
        modifier = Modifier
            .padding(16.dp, top = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.scheme_network),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = viewModel.getScheme(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.brand),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = viewModel.getBrand(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }

    Divider(modifier = Modifier.padding(bottom = 4.dp, start = 16.dp, end = 16.dp))
}