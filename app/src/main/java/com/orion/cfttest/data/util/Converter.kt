package com.orion.cfttest.data.util

import com.orion.cfttest.data.entity.BankEntity
import com.orion.cfttest.data.entity.CardEntity
import com.orion.cfttest.data.entity.CountryEntity
import com.orion.cfttest.data.entity.NumberCardEntity
import com.orion.cfttest.model.Bank
import com.orion.cfttest.model.Card
import com.orion.cfttest.model.Country
import com.orion.cfttest.model.NumberCard

object Converter {
    fun cardEntityToCard(cardEntity: CardEntity): Card {
        val bankEntity = cardEntity.bankEntity
        val numberCardEntity = cardEntity.numberCardEntity
        val countryEntity = cardEntity.countryEntity
        val bank = Bank(
            name = bankEntity?.nameBank,
            url = bankEntity?.url,
            phone = bankEntity?.phone,
            city = bankEntity?.city
        )
        val numberCard = NumberCard(
            length = numberCardEntity?.length,
            luhn = numberCardEntity?.luhn
        )
        val country = Country(
            numeric = countryEntity?.numeric,
            alpha2 = countryEntity?.alpha2,
            name = countryEntity?.nameCountry,
            emoji = countryEntity?.emoji,
            currency = countryEntity?.currency,
            latitude = countryEntity?.latitude,
            longitude = countryEntity?.longitude
        )
        return Card(
            bin = cardEntity.bin!!,
            scheme = cardEntity.scheme,
            type = cardEntity.type,
            brand = cardEntity.brand,
            prepaid = cardEntity.prepaid,
            bank = bank,
            numberCard = numberCard,
            country = country
        )
    }

    fun cardToCardEntity(card: Card): CardEntity {
        val bank = card.bank
        val numberCard = card.numberCard
        val country = card.country

        val bankEntity = BankEntity(
            nameBank = bank?.name,
            url = bank?.url,
            phone = bank?.phone,
            city = bank?.city
        )
        val numberCardEntity = NumberCardEntity(
            length = numberCard?.length,
            luhn = numberCard?.luhn
        )
        val countryEntity = CountryEntity(
            numeric = country?.numeric,
            alpha2 = country?.alpha2,
            nameCountry = country?.name,
            emoji = country?.emoji,
            currency = country?.currency,
            latitude = country?.latitude,
            longitude = country?.longitude
        )
        return CardEntity(
            bin = card.bin,
            scheme = card.scheme,
            type = card.type,
            brand = card.brand,
            prepaid = card.prepaid,
            bankEntity = bankEntity,
            numberCardEntity = numberCardEntity,
            countryEntity = countryEntity,
            id = 0
        )
    }
}