package com.example.shop.domain

import androidx.lifecycle.LiveData

class GetShopList(private val shopListRepo: ShopListRepo) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepo.getShopList()
    }
}