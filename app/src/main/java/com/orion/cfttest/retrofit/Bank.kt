package com.orion.cfttest.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank (
    @Json(name = "name")
     val name : String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "city")
    val city: String?,
)

