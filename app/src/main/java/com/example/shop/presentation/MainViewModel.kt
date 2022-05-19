package com.example.shop.presentation


import androidx.lifecycle.ViewModel
import com.example.shop.data.ShopRepoImpl
import com.example.shop.domain.DeleteShopItem
import com.example.shop.domain.EditShopItem
import com.example.shop.domain.GetShopList
import com.example.shop.domain.ShopItem

class MainViewModel : ViewModel() {

    private val repository = ShopRepoImpl

    private val getShopListUseCase = GetShopList(repository)
    private val deleteShopListUseCase = DeleteShopItem(repository)
    private val editShopListUseCase = EditShopItem(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopList(item: ShopItem) {
        deleteShopListUseCase.deleteShopItem(item)
    }

    fun editShopList(item: ShopItem) {
        val new = item.copy(enabled = !item.enabled)
        editShopListUseCase.editShopItem(new)
    }
}