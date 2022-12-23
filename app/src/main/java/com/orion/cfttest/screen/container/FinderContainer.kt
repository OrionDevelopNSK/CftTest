package com.orion.cfttest.screen.container

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orion.cfttest.BaseViewModel
import com.orion.cfttest.R
import com.orion.cfttest.util.dimensionResourceSp

@Composable
fun FinderContainer(viewModel: BaseViewModel) {
    val bin = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedTextField(
            value = bin.value,
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                bin.value = it
                viewModel.getCard(it)
            },

            label = {
                Text(
                    text = stringResource(R.string.entering_digits),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.primary,
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(5.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(
                stringResource(R.string.search_history),
                fontSize = dimensionResourceSp(id = R.dimen.headline)
            )
        }
    }
}