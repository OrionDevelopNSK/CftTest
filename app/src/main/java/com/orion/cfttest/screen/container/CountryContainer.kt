package com.orion.cfttest.screen.container

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orion.cfttest.R
import com.orion.cfttest.model.Card
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel

@Composable
fun CountryContainer(viewModel: BaseViewModel, card: Card?) {
    val context = LocalContext.current as Activity
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = stringResource(R.string.country),
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResourceSp(id = R.dimen.headline),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.primary,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = viewModel.getEmoji(card),
            fontSize = 12.sp,
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = viewModel.getCity(card),
            fontSize = dimensionResourceSp(id = R.dimen.subtitle),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.tertiary,
        )

    }

    Row(
        modifier = Modifier
            .padding(start = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.alfa2),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = viewModel.getAlfa2(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.location),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                modifier = Modifier.clickable {
                    viewModel.openMap(context, card)
                },
                text = viewModel.getLocation(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(R.string.currency),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = viewModel.getCurrency(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiary,
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.numeric_country_code),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                text = viewModel.getCountryCode(card),
                fontSize = dimensionResourceSp(id = R.dimen.body),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.tertiary,
            )
        }
    }

    Divider(modifier = Modifier.padding(bottom = 4.dp, start = 16.dp, end = 16.dp, top = 8.dp))
}