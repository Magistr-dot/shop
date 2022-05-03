package com.example.shop.domain

class DeleteShopItem(private val shopListRepo: ShopListRepo) {
    fun deleteShopItem(shopItem: ShopItem) {
        shopListRepo.deleteShopItem(shopItem)
    }
}