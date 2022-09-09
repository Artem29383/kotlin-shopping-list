package com.example.kotlin_shopping_list.domain

import java.util.*

class RemoveShopItem(private val shopListRepository: ShopListRepository) {
    suspend fun removeItem(id: UUID) {
        shopListRepository.removeItem(id)
    }
}