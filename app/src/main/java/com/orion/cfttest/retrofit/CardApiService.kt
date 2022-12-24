package com.orion.cfttest.retrofit

import com.orion.cfttest.model.Card
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private const val BASE_URL =
    "https://lookup.binlist.net/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CardApiService {
    @GET
    fun getCard(@Url url: String): Call<Card>
}

object CardApi {
    val retrofitService: CardApiService by lazy {
        retrofit.create(CardApiService::class.java)
    }
}