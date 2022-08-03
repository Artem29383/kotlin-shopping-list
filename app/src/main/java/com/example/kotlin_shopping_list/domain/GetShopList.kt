package com.example.kotlin_shopping_list.domain

import androidx.lifecycle.LiveData

class GetShopList(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}