package com.example.kotlin_shopping_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_shopping_list.data.ShopListRepositoryImpl
import com.example.kotlin_shopping_list.domain.EditShopItem
import com.example.kotlin_shopping_list.domain.GetShopList
import com.example.kotlin_shopping_list.domain.RemoveShopItem
import com.example.kotlin_shopping_list.domain.ShopItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopList(repository)
    private val removeShopItemUseCase = RemoveShopItem(repository)
    private val editShopItemUseCase = EditShopItem(repository)

    val shopList = getShopListUseCase.getShopList()

    fun toggleEnabled(shopItem: ShopItem) {
        val shopItemCopy = shopItem.copy(enabled = !shopItem.enabled)
        viewModelScope.launch {
            editShopItemUseCase.editItem(shopItemCopy)
        }
    }

    fun removeShopItem(id: UUID) {
        viewModelScope.launch {
            removeShopItemUseCase.removeItem(id)
        }
    }
}