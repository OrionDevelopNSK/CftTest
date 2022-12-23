package com.orion.cfttest.util

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp


@Composable
@ReadOnlyComposable
fun dimensionResourceSp(@DimenRes id: Int) : TextUnit {
    return with(LocalContext.current.resources) {
        (getDimension(id) / displayMetrics.scaledDensity).sp
    }
}

