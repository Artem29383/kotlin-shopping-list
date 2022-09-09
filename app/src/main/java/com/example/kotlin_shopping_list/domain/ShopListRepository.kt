package com.example.kotlin_shopping_list.domain

import androidx.lifecycle.LiveData
import java.util.*

interface ShopListRepository {
    suspend fun addItem(item: ShopItem)
    suspend fun editItem(item: ShopItem)
    suspend fun getItem(id: UUID): ShopItem
    fun getShopList(): LiveData<List<ShopItem>>
    suspend fun removeItem(id: UUID)
}