package com.example.kotlin_shopping_list.domain

import java.util.*

data class ShopItem(
    val name: String,
    val count: Int = 1,
    val enabled: Boolean = true,
    val id: UUID = UUID.randomUUID(),
)