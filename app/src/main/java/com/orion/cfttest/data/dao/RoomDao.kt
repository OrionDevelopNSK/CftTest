package com.orion.cfttest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.orion.cfttest.data.entity.CardEntity

@Dao
abstract class RoomDao {
    @Query("SELECT * FROM card")
    abstract fun getCards() : MutableList<CardEntity>

    @Insert
    abstract fun insertAllCards(cards: MutableList<CardEntity>)
}