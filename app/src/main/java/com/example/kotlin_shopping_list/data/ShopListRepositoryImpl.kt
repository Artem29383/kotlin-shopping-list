package com.example.kotlin_shopping_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_shopping_list.domain.ShopItem
import com.example.kotlin_shopping_list.domain.ShopListRepository
import java.lang.RuntimeException
import java.util.*

object ShopListRepositoryImpl : ShopListRepository {
    private val shopListLiveData = MutableLiveData<List<ShopItem>>()
    private val shopList = mutableListOf<ShopItem>()

    init {
        for (i in 0 until 5) {
            addItem(ShopItem("Bread $i", enabled = Random().nextBoolean()))
        }
    }

    override fun addItem(item: ShopItem) {
        shopList.add(item)
        updateList()
    }

    override fun editItem(item: ShopItem) {
        val index = shopList.indexOfFirst { it.id === item.id }
        shopList[index] = item
        updateList()
    }

    override fun getItem(id: UUID): ShopItem {
        return shopList.find { it.id == id }
            ?: throw RuntimeException("Element with id $id not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLiveData
    }

    override fun removeItem(id: UUID) {
        shopList.remove(getItem(id))
        updateList()
    }

    private fun updateList() {
        shopListLiveData.value = shopList.toList()
    }
}