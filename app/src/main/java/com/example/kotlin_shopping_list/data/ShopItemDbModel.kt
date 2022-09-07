package com.example.kotlin_shopping_list.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "shop_items")
data class ShopItemDbModel(
    @PrimaryKey
    @NotNull
    val id: UUID,
    val name: String,
    val count: Int = 1,
    val enabled: Boolean = true,
)