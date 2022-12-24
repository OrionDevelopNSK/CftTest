package com.orion.cfttest.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "card"
)
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @Embedded
    val numberCardEntity: NumberCardEntity?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    @Embedded
    val countryEntity: CountryEntity?,
    @Embedded
    val bankEntity: BankEntity?
    )