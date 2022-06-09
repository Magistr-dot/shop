package com.example.shop.domain

class GetShopItem(private val shopListRepo: ShopListRepo) {
    suspend fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepo.getShopItem(shopItemId)
    }
}