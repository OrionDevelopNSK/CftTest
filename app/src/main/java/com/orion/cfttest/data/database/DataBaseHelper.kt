package com.orion.cfttest.data.database

import com.orion.cfttest.data.entity.CardEntity
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

    fun loadCards(): List<CardEntity> {
        var tmpCards = mutableListOf<CardEntity>()
        runBlocking {
            val job = async(Dispatchers.IO) {
                    tmpCards = roomRepository.getAllCards()
            }
            job.await()
        }
        return tmpCards
    }
}