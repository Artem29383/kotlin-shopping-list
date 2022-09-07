package com.example.kotlin_shopping_list.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.kotlin_shopping_list.domain.ShopItem
import com.example.kotlin_shopping_list.domain.ShopListRepository
import java.lang.RuntimeException
import java.util.*

class ShopListRepositoryImpl(application: Application) : ShopListRepository {
    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val shopListMapper: ShopListMapper = ShopListMapper()

    override fun addItem(item: ShopItem) {
        shopListDao.addShopItem(shopListMapper.mapEntityToDBModel(item))
    }

    override fun editItem(item: ShopItem) {
        shopListDao.addShopItem(shopListMapper.mapEntityToDBModel(item))
    }

    override fun getItem(id: UUID): ShopItem {
        val dbModel = shopListDao.getShopItem(id)
        return shopListMapper.mapDBModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> =
        Transformations.map(shopListDao.getShopList()) {
            shopListMapper.mapListDBModelToListEntity(it)
        }

//    override fun getShopList(): LiveData<List<ShopItem>> = MediatorLiveData<List<ShopItem>>().apply {
//        addSource(shopListDao.getShopList()) {
//            value = shopListMapper.mapListDBModelToListEntity(it)
//        }
//    }

    override fun removeItem(id: UUID) {
        shopListDao.deleteShopItem(id)
    }
}