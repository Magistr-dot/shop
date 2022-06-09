package com.example.shop.domain

class EditShopItem(private val shopListRepo: ShopListRepo) {
    suspend fun editShopItem(shopItem: ShopItem) {
        shopListRepo.editShopItem(shopItem)
    }
}