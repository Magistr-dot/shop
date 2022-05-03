package com.example.shop.domain

class GetShopList(private val shopListRepo: ShopListRepo) {
    fun getShopList(): List <ShopItem>{
        return shopListRepo.getShopList()
    }
}