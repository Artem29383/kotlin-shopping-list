package com.example.kotlin_shopping_list.domain

class EditShopItem(private val shopListRepository: ShopListRepository) {
    fun editItem(item: ShopItem) {
        shopListRepository.editItem(item)
    }
}