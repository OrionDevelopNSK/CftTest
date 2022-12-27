package com.orion.cfttest.screen.container

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.orion.cfttest.R
import com.orion.cfttest.model.Card
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel

@Composable
fun CountryContainer(
    viewModel: BaseViewModel,
    card: Card?
) {
    val context = LocalContext.current as Activity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
            text = stringResource(R.string.country),
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResourceSp(id = R.dimen.headline),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = viewModel.getEmoji(card),
            fontSize = dimensionResourceSp(id = R.dimen.subtitle),
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

            Row(modifier = Modifier.clickable {
                viewModel.openMap(context, card)
            }
            ) {
                Icon(
                    painterResource(id = R.drawable.loc),
                    contentDescription = stringResource(R.string.search_history),
                    modifier = Modifier.size(16.dp),
                    tint = Color.Red
                )
                Text(

                    text = viewModel.getLocation(
                        stringResource(R.string.latitude),
                        stringResource(R.string.longitude),
                        card
                    ),
                    fontSize = dimensionResourceSp(id = R.dimen.body),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = TextStyle(fontStyle = FontStyle.Italic)
                )
            }
        }

        Spacer(modifier = Modifier.width(49.dp))

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
}