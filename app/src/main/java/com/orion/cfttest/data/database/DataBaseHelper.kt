package com.orion.cfttest.data.database

import com.orion.cfttest.data.entity.BankEntity
import com.orion.cfttest.data.entity.CardEntity
import com.orion.cfttest.data.entity.CountryEntity
import com.orion.cfttest.data.entity.NumberCardEntity
import com.orion.cfttest.data.repo.RoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DataBaseHelper @Inject constructor(dataBase: AppDataBase) {

    private val roomRepository = RoomRepository(dataBase.roomDao())

    fun saveCards(cardEntity: MutableList<CardEntity>) {
        runBlocking {
            launch(Dispatchers.IO) {
                roomRepository.insertCards(cardEntity)
            }
        }
    }

    fun loadCards(): MutableList<CardEntity> {
        var tmpCards = mutableListOf<CardEntity>()
        runBlocking {
            val job = async {
                tmpCards = roomRepository.getAllCards()
            }
            job.await()
        }
        return tmpCards
    }

    private fun createEntity() : CardEntity{
         return CardEntity(
            id = 0,
            scheme = "scheme",
            type = "type",
            brand = "brand",
            prepaid = true,
            numberCardEntity = NumberCardEntity(
                16, true
            ),
            countryEntity = CountryEntity(
                numeric = "000",
                alpha2 = "alpha2",
                nameCountry = "name",
                emoji = "emoji",
                currency = "dollar",
                latitude = 50,
                longitude = 40
            ),
            bankEntity = BankEntity(
                nameBank = "sber",
                url = "www",
                phone = "09",
                city = "nsk"
            )
        )
    }

    fun repeat(){
        val arr = mutableListOf<CardEntity>()
        for (i in 0..20){
            arr.add(createEntity())
        }
        saveCards(arr)
    }
}