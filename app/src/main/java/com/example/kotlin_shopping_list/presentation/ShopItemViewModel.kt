package com.example.kotlin_shopping_list.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_shopping_list.data.ShopListRepositoryImpl
import com.example.kotlin_shopping_list.domain.AddShopItem
import com.example.kotlin_shopping_list.domain.EditShopItem
import com.example.kotlin_shopping_list.domain.GetShopItem
import com.example.kotlin_shopping_list.domain.ShopItem
import java.lang.Exception
import java.util.*

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopItemUseCase = GetShopItem(repository)
    private val addShopItemUseCase = AddShopItem(repository)
    private val editShopItemUseCase = EditShopItem(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    fun getShopItem(id: UUID) {
        _shopItem.value = getShopItemUseCase.getItem(id)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isValid = validate(name, count)

        if (isValid) {
            addShopItemUseCase.addItem(ShopItem(name, count, true))
            _shouldCloseScreen.value = Unit
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        // TODO fix
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val isValid = validate(name, count)

        if (isValid) {
            _shopItem.value?.let {
                editShopItemUseCase.editItem(it.copy(name = name, count = count))
                _shouldCloseScreen.value = Unit
            }
        }
    }

    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }

    private fun parseCount(count: String?): Int {
        return try {
            count?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validate(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    public fun resetErrorInputName() {
        _errorInputName.value = false
    }

    public fun resetErrorInputCount() {
        _errorInputCount.value = false
    }
}