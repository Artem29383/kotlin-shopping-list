package com.example.kotlin_shopping_list.domain

import java.util.*

class GetShopItem(private val shopListRepository: ShopListRepository) {
    fun getItem(id: UUID): ShopItem {
        return shopListRepository.getItem(id)
    }
}