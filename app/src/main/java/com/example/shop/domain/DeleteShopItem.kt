package com.example.shop.domain

class DeleteShopItem(private val shopListRepo: ShopListRepo) {
    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepo.deleteShopItem(shopItem)
    }
}