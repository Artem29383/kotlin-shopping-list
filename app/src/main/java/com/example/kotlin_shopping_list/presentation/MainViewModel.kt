package com.example.kotlin_shopping_list.presentation

import androidx.lifecycle.ViewModel
import com.example.kotlin_shopping_list.data.ShopListRepositoryImpl
import com.example.kotlin_shopping_list.domain.EditShopItem
import com.example.kotlin_shopping_list.domain.GetShopList
import com.example.kotlin_shopping_list.domain.RemoveShopItem
import com.example.kotlin_shopping_list.domain.ShopItem
import java.util.*

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopList(repository)
    private val removeShopItemUseCase = RemoveShopItem(repository)
    private val editShopItemUseCase = EditShopItem(repository)

    val shopList = getShopListUseCase.getShopList()

    fun toggleEnabled(shopItem: ShopItem) {
        val shopItemCopy = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editItem(shopItemCopy)
    }

    fun removeShopItem(id: UUID) {
        removeShopItemUseCase.removeItem(id)
    }
}