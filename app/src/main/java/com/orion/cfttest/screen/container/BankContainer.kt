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
fun BankContainer(viewModel: BaseViewModel, card: Card?) {
    val context = LocalContext.current as Activity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp),
            text = stringResource(R.string.bank),
            fontWeight = FontWeight.Bold,
            fontSize = dimensionResourceSp(id = R.dimen.headline),
            textAlign = TextAlign.Start,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Column {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = viewModel.getBankAndCity(card),
            fontSize = dimensionResourceSp(id = R.dimen.subtitle),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {

            Row(modifier = Modifier
                .weight(1f)
                .clickable {
                    viewModel.openBrowser(context, card)
                }) {

                Icon(
                    painterResource(id = R.drawable.web),
                    contentDescription = stringResource(R.string.search_history),
                    modifier = Modifier.size(16.dp),
                    tint = Color.Blue
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(

                    text = viewModel.getUrl(card),
                    fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = TextStyle(fontStyle = FontStyle.Italic)
                )

            }

            Spacer(modifier = Modifier.width(42.dp))

            Row(modifier = Modifier
                .weight(1f)
                .clickable {
                    viewModel.callPhone(context, card)
                }) {

                Icon(
                    painterResource(id = R.drawable.phone),
                    contentDescription = stringResource(R.string.search_history),
                    modifier = Modifier.size(16.dp),
                    tint = Color.Green
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = viewModel.getPhoneNumber(card),
                    fontSize = dimensionResourceSp(id = R.dimen.subtitle),
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = TextStyle(fontStyle = FontStyle.Italic)
                )

            }


        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}