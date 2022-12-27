package com.orion.cfttest.screen.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orion.cfttest.R
import com.orion.cfttest.util.dimensionResourceSp
import com.orion.cfttest.viewmodel.BaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinderContainer(viewModel: BaseViewModel) {
    val bin = remember { mutableStateOf(viewModel.card.value?.bin ?: "") }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                text = stringResource(R.string.bin_inn),
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResourceSp(id = R.dimen.headline),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
            )
        }

        OutlinedTextField(
            value = bin.value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            maxLines = 1,
            singleLine = true,
            onValueChange = {
                viewModel.createCard(it)
                bin.value = it
            },
            label = {
                Text(
                    text = stringResource(R.string.entering_digits),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = RoundedCornerShape(5.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_alternates_extra_bold))
            ),
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colorScheme.primaryContainer,
                containerColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}