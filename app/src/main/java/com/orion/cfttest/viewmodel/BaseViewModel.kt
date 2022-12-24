package com.orion.cfttest.viewmodel

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.orion.cfttest.data.database.AppDataBase
import com.orion.cfttest.data.database.DataBaseHelper
import com.orion.cfttest.model.Card
import com.orion.cfttest.retrofit.CardApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val GOOGLE_MAP = "http://maps.google.com/maps?q=loc:"

class BaseViewModel : ViewModel() {
    private val _card: MutableLiveData<Card> = MutableLiveData()
    val card: LiveData<Card> = _card


    fun openMap(context: Activity, card: Card?) {
        val latitude = card?.country?.latitude
        val longitude = card?.country?.longitude
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$GOOGLE_MAP$latitude,$longitude")
            )
        ContextCompat.startActivity(context, intent, null)
    }

    fun openBrowser(context: Activity, card: Card?) {
        val intent =
            Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://" + card?.bank?.url))
        ContextCompat.startActivity(context, intent, null)
    }

    fun callPhone(context: Activity, card: Card?) {
        val intent =
            Intent(Intent.ACTION_CALL, Uri.parse("tel: ${card?.bank?.phone}"))
        ContextCompat.startActivity(context, intent, null)
    }

    fun getBankAndCity(card: Card?): String {
        return card?.bank?.name ?: "?, ${card?.bank?.city ?: "?"}"
    }

    fun getUrl(card: Card?): String {
        return card?.bank?.url ?: "?"
    }

    fun getPhoneNumber(card: Card?): String {
        return card?.bank?.phone ?: "?"
    }

    fun getScheme(card: Card?): String {
        return card?.scheme ?: "?"
    }

    fun getBrand(card: Card?): String {
        return card?.brand ?: "?"
    }

    fun getLuhn(card: Card?): String {
        return when (card?.numberCard?.luhn) {
            false -> "no"
            true -> "yes"
            else -> "?"
        }
    }

    fun getLength(card: Card?): String {
        return (card?.numberCard?.length ?: "?").toString()
    }

    fun getType(card: Card?): String {
        return card?.type ?: "?"
    }

    fun getPrepared(card: Card?): String {
        return when (card?.prepaid) {
            false -> "no"
            true -> "yes"
            else -> "?"
        }
    }

    fun getLocation(card: Card?): String {
        return "(latitude: ${card?.country?.latitude ?: "?"}, longitude: ${card?.country?.longitude ?: "?"})"
    }

    fun getAlfa2(card: Card?): String {
        return card?.country?.alpha2 ?: "?"
    }

    fun getCity(card: Card?): String {
        return card?.country?.name ?: "?"
    }

    fun getCurrency(card: Card?): String {
        return card?.country?.currency ?: "?"
    }

    fun getCountryCode(card: Card?): String {
        return card?.country?.numeric ?: "?"
    }

    fun getEmoji(card: Card?): String {
        return card?.country?.emoji ?: "?"

    }

    fun getCard(url: String) {
        CardApi.retrofitService.getCard(url).enqueue(object : Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                val tmpCard = response.body()
                _card.value = tmpCard
                println("onResponse")
            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                _card.value = null
                println("onFailure")
            }
        })
    }

    fun save(context: Activity) {
        val appDataBase = Room.databaseBuilder(context, AppDataBase::class.java, "cards_db").build()
        val dataBaseHelper = DataBaseHelper(appDataBase)
        dataBaseHelper.repeat()
    }
}



