package com.orion.cfttest.data

import com.orion.cfttest.model.Bank
import com.orion.cfttest.model.Card
import com.orion.cfttest.model.Country
import com.orion.cfttest.model.NumberCard
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class JsonParser {
    private fun readJson(bin: String): JSONObject? {
        val url = URL("https://lookup.binlist.net/$bin")
        val connection = url.openConnection() as HttpURLConnection
        val inputStream = connection.inputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var data = ""
        val readLines: List<String> = bufferedReader.readLines()
        for (readLine in readLines) {
            data += readLine
        }

        var jsonObject: JSONObject? = null
        if (data.isNotEmpty()) jsonObject = JSONObject(data)

        return jsonObject
    }

    fun parseCard(bin: String): Card {
        val tmpCard: Card?
        val jsonObject: JSONObject = readJson(bin)!!
                val number = parseNumberCard(jsonObject)
                val scheme: String? = jsonObject.getString("scheme")
                val type: String? = jsonObject.getString("type")
                val brand: String? = jsonObject.getString("brand")
                val prepaid: Boolean? = jsonObject.getBoolean("prepaid")
                val country = parseCountry(jsonObject)
                val bank = parseBank(jsonObject)
                tmpCard = Card(
                    numberCard = number,
                    scheme = scheme,
                    type = type,
                    brand = brand,
                    prepaid = prepaid,
                    country = country,
                    bank = bank
                )
        return tmpCard
    }


    private fun parseNumberCard(jsonObject: JSONObject): NumberCard {
        val number: JSONObject = jsonObject.getJSONObject("number")
        val length: Int? = number.getInt("length")
        val luhn: Boolean? = number.getBoolean("luhn")
        return NumberCard(
            length = length,
            luhn = luhn
        )
    }

    private fun parseCountry(jsonObject: JSONObject): Country {
        val country = jsonObject.getJSONObject("country")
        val numeric: String? = country.getString("numeric")
        val alpha2: String? = country.getString("alpha2")
        val name: String? = country.getString("name")
        val emoji: String? = country.getString("emoji")
        val currency: String? = country.getString("currency")
        val latitude: Int? = country.getInt("latitude")
        val longitude: Int? = country.getInt("longitude")
        return Country(
            numeric = numeric,
            alpha2 = alpha2,
            name = name,
            emoji = emoji,
            currency = currency,
            latitude = latitude,
            longitude = longitude
        )
    }

    private fun parseBank(jsonObject: JSONObject): Bank {
        val bank: JSONObject = jsonObject.getJSONObject("bank")
        val name: String? = bank.getString("name")
        val url: String? = bank.getString("url")
        val phone: String? = bank.getString("phone")
        val city: String? = bank.getString("city")
        return Bank(
            name = name,
            url = url,
            phone = phone,
            city = city
        )
    }
}