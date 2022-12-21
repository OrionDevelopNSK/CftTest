package com.orion.cfttest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orion.cfttest.data.JsonParser
import com.orion.cfttest.model.Card


class BaseViewModel : ViewModel() {
    val card : MutableLiveData<Card> = MutableLiveData()


    fun parse(){
        val jsonParser = JsonParser()
        card.postValue(jsonParser.parseCard("457177360"))
    }

    fun openMap() {

    }

    fun openBrowser() {

    }

    fun callPhone(){

    }

}