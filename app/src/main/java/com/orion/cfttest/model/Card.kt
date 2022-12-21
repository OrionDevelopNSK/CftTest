package com.orion.cfttest.model

data class Card(
    val numberCard: NumberCard?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid : Boolean?,
    val country: Country?,
    val bank: Bank?
)