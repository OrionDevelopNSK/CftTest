package com.orion.cfttest.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Card(
    @Json(name = "number")
    val number: Number?,
    @Json(name = "scheme")
    val scheme: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "prepaid")
    val prepaid: Boolean?,
    @Json(name = "country")
    val country: Country?,
    @Json(name = "bank")
    val bank: Bank?
    )