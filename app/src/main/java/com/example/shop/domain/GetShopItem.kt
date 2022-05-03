package com.example.shop.domain

class GetShopItem(private val shopListRepo: ShopListRepo) {
    fun getShopItem(shopItemId: Int): ShopItem {
        return shopListRepo.getShopItem(shopItemId)
    }
}