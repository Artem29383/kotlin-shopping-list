package com.example.kotlin_shopping_list.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface ShopListDao {
    @Query("SELECT * FROM SHOP_ITEMS")
    fun getShopList(): LiveData<List<ShopItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShopItem(shopItemDbModel: ShopItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:shopItemId")
    suspend fun deleteShopItem(shopItemId: UUID)

    @Query("SELECT * FROM SHOP_ITEMS WHERE id=:shopItemId LIMIT 1")
    suspend fun getShopItem(shopItemId: UUID): ShopItemDbModel
}