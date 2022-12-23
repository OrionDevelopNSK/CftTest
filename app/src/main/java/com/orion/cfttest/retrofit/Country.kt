package com.orion.cfttest.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "numeric")
    val numeric: String?,
    @Json(name = "alpha2")
    val alpha2: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "emoji")
    val emoji: String?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "latitude")
    val latitude: Int?,
    @Json(name = "longitude")
    val longitude: Int?
)