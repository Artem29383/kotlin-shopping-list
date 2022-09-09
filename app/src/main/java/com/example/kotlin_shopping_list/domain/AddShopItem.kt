package com.example.kotlin_shopping_list.domain

class AddShopItem(private val shopListRepository: ShopListRepository) {

    suspend fun addItem(item: ShopItem) {
        shopListRepository.addItem(item)
    }
}