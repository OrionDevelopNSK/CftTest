package com.orion.cfttest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orion.cfttest.data.dao.RoomDao
import com.orion.cfttest.data.entity.CardEntity


@Database(
    entities = [
        CardEntity::class
    ],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun roomDao() : RoomDao
}