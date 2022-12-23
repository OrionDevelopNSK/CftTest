package com.orion.cfttest.data

//
//import org.json.JSONObject
//import java.io.BufferedReader
//import java.io.FileNotFoundException
//import java.io.InputStreamReader
//import java.net.HttpURLConnection
//import java.net.URL
//
//private const val url = "https://lookup.binlist.net/"
//
//class JsonParser {
//    private fun readJson(bin: String): JSONObject? {
//        var jsonObject: JSONObject? = null
//        try {
//            val url = URL("$url$bin")
//            val connection = url.openConnection() as HttpURLConnection
//            val inputStream = connection.inputStream
//            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
//            var data = ""
//            val readLines: List<String> = bufferedReader.readLines()
//            for (readLine in readLines) {
//                data += readLine
//            }
//            if (data.isNotEmpty()) jsonObject = JSONObject(data)
//
//        } catch (e: FileNotFoundException) {
//
//        }
//        return jsonObject
//
//    }
//
//    fun parseCard(bin: String): Card1? {
//        if (readJson(bin) != null) {
//            val jsonObject: JSONObject = readJson(bin)!!
//            val number = parseNumberCard(jsonObject)
//            val scheme: String? = jsonObject.getString("scheme")
//            val type: String? = jsonObject.getString("type")
//            val brand: String? = jsonObject.getString("brand")
//            val prepaid: Boolean? = jsonObject.getBoolean("prepaid")
//            val country = parseCountry(jsonObject)
//            val bank = parseBank(jsonObject)
//            return Card1(
//                numberCard = number,
//                scheme = scheme,
//                type = type,
//                brand = brand,
//                prepaid = prepaid,
//                country = country,
//                bank = bank
//            )
//        } else return null
//    }
//
//
//    private fun parseNumberCard(jsonObject: JSONObject): NumberCard {
//        val number: JSONObject = jsonObject.getJSONObject("number")
//        val length: Int? = number.getInt("length")
//        val luhn: Boolean? = number.getBoolean("luhn")
//        return NumberCard(
//            length = length,
//            luhn = luhn
//        )
//    }
//
//    private fun parseCountry(jsonObject: JSONObject): Country {
//        val country = jsonObject.getJSONObject("country")
//        val numeric: String? = country.getString("numeric")
//        val alpha2: String? = country.getString("alpha2")
//        val name: String? = country.getString("name")
//        val emoji: String? = country.getString("emoji")
//        val currency: String? = country.getString("currency")
//        val latitude: Int? = country.getInt("latitude")
//        val longitude: Int? = country.getInt("longitude")
//        return Country(
//            numeric = numeric,
//            alpha2 = alpha2,
//            name = name,
//            emoji = emoji,
//            currency = currency,
//            latitude = latitude,
//            longitude = longitude
//        )
//    }
//
//    private fun parseBank(jsonObject: JSONObject): Bank {
//        val bank: JSONObject = jsonObject.getJSONObject("bank")
//        val name: String? = bank.getString("name")
//        val url: String? = bank.getString("url")
//        val phone: String? = bank.getString("phone")
//        val city: String? = bank.getString("city")
//        return Bank(
//            name = name,
//            url = url,
//            phone = phone,
//            city = city
//        )
//    }
//}