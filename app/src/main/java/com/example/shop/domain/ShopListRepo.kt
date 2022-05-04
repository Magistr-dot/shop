package com.example.shop.domain

import androidx.lifecycle.LiveData

interface ShopListRepo {
    fun addShopItem(shopItem: ShopItem) {
        TODO()
    }

    fun deleteShopItem(shopItem: ShopItem) {
        TODO()
    }

    fun editShopItem(shopItem: ShopItem) {
        TODO()
    }

    fun getShopItem(shopItemId: Int): ShopItem {
        TODO()
    }

    fun getShopList(): LiveData<List<ShopItem>> {
        TODO()
    }
}