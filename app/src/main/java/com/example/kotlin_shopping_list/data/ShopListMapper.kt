package com.example.kotlin_shopping_list.data

import com.example.kotlin_shopping_list.domain.ShopItem

class ShopListMapper {

    fun mapEntityToDBModel(shopItem: ShopItem): ShopItemDbModel = ShopItemDbModel(
        id = shopItem.id,
        count = shopItem.count,
        enabled = shopItem.enabled,
        name = shopItem.name
    )

    fun mapDBModelToEntity(shopItemDbModel: ShopItemDbModel): ShopItem = ShopItem(
        id = shopItemDbModel.id,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled,
        name = shopItemDbModel.name
    )

    fun mapListDBModelToListEntity(list: List<ShopItemDbModel>): List<ShopItem> = list.map {
        mapDBModelToEntity(it)
    }
}