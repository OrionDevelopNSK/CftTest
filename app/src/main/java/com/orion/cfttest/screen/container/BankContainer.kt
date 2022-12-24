package com.orion.cfttest.screen.container

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.orion.cfttest.R
import com.orion.cfttest.model.Card
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel

@Composable
fun BankContainer(viewModel: BaseViewModel, card: Card?) {
    val context = LocalContext.current as Activity
    Text(
        modifier = Modifier.padding(start = 16.dp),
        text = stringResource(R.string.bank),
        fontWeight = FontWeight.Bold,
        fontSize = dimensionResourceSp(id = R.dimen.headline),
        textAlign = TextAlign.Start,
        color = MaterialTheme.colors.primary,
    )

    Column(
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = viewModel.getBankAndCity(card),
            fontSize = dimensionResourceSp(id = R.dimen.subtitle),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.primary,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.openBrowser(context, card)
                    },
                text = viewModel.getUrl(card),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.secondary,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.callPhone(context, card)
                    },
                text = viewModel.getPhoneNumber(card),
                fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.secondary,
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }


}