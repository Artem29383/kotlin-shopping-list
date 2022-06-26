package com.example.kotlin_shopping_list.domain

import androidx.lifecycle.LiveData
import java.util.*

interface ShopListRepository {
    fun addItem(item: ShopItem)
    fun editItem(item: ShopItem)
    fun getItem(id: UUID): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
    fun removeItem(id: UUID)
}