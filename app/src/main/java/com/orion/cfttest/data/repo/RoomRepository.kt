package com.orion.cfttest.data.repo

import com.orion.cfttest.data.dao.RoomDao
import com.orion.cfttest.data.entity.CardEntity

class RoomRepository(private val roomDao: RoomDao) {

    fun insertCards(cardsEntity: MutableList<CardEntity>){
        roomDao.insertAllCards(cardsEntity)
    }

    fun getAllCards() : MutableList<CardEntity> {
        return roomDao.getCards()
    }
}