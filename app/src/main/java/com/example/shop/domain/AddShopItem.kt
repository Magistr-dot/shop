package com.example.shop.domain

class AddShopItem(private val shopListRepo: ShopListRepo) {
    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepo.addShopItem(shopItem)
    }
}