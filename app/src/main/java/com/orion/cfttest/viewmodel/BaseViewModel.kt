package com.orion.cfttest.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orion.cfttest.data.database.DataBaseHelper
import com.orion.cfttest.data.entity.CardEntity
import com.orion.cfttest.data.util.Converter
import com.orion.cfttest.model.Card
import com.orion.cfttest.retrofit.CardApi
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


private const val GOOGLE_MAP = "http://maps.google.com/maps?q=loc:"


@HiltViewModel
class BaseViewModel @Inject constructor(
    private val dataBaseHelper: DataBaseHelper
) : ViewModel(

) {
    private val _card: MutableLiveData<Card> = MutableLiveData()
    val card: LiveData<Card> = _card

    private val _cardList: MutableLiveData<List<Card>> = MutableLiveData()
    val cardList: LiveData<List<Card>> = _cardList


    fun openMap(context: Context, card: Card?) {
        if (card?.country?.latitude == null ||
            card.country.longitude == null
        ) return

        val latitude = card.country.latitude
        val longitude = card.country.longitude
        val intent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("$GOOGLE_MAP$latitude,$longitude")
            )
        ContextCompat.startActivity(context, intent, null)
    }

    fun openBrowser(context: Context, card: Card?) {
        if (card?.bank?.url == null) return
        val intent =
            Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://" + card.bank.url))
        ContextCompat.startActivity(context, intent, null)
    }

    fun callPhone(context: Context, card: Card?) {
        if (card?.bank?.phone == null) return
        val intent =
            Intent(Intent.ACTION_CALL, Uri.parse("tel: ${card.bank.phone}"))
        ContextCompat.startActivity(context, intent, null)
    }

    fun getBankAndCity(card: Card?): String {
        return (card?.bank?.name ?: "?") + ", " + (card?.bank?.city ?: "?")
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

    fun getLocation(latitude: String, longitude : String,card: Card?): String {
        return "(${latitude}: ${card?.country?.latitude ?: "?"}, ${longitude}: ${card?.country?.longitude ?: "?"})"
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

    fun createCard(url: String) {
        CardApi.retrofitService.getCard(url).enqueue(object : Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                val tmpCard = response.body()
                if (tmpCard != null) {
                    tmpCard.bin = url
                    _card.value = tmpCard
                    val cardToCardEntity = Converter.cardToCardEntity(tmpCard)
                    saveCard(cardToCardEntity)
                } else {
                    _card.value = null
                }
                Log.d("ViewModel", "onResponse")
            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                _card.value = null
                Log.d("ViewModel", "onFailure")
            }
        })
    }


    fun saveCard(cardEntity: CardEntity) {
        dataBaseHelper.saveCards(mutableListOf(cardEntity))
    }

    fun loadCard() {
        val loadCardsEntities: List<CardEntity> = dataBaseHelper.loadCards().reversed()
        if (loadCardsEntities.isEmpty()) return
        val cards = mutableListOf<Card>()
        for (cardEntity in loadCardsEntities) {
            cards.add(Converter.cardEntityToCard(cardEntity))
        }
        _cardList.value = cards
    }
}



