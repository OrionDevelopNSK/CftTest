package com.orion.cfttest.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NumberCard(
    @Json(name = "length")
    val length: Int?,
    @Json(name = "luhn")
    val luhn: Boolean?
)