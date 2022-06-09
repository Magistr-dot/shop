package com.example.shop.presentation


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop.data.ShopRepoImpl
import com.example.shop.domain.DeleteShopItem
import com.example.shop.domain.EditShopItem
import com.example.shop.domain.GetShopList
import com.example.shop.domain.ShopItem
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopRepoImpl(application)

    private val getShopListUseCase = GetShopList(repository)
    private val deleteShopListUseCase = DeleteShopItem(repository)
    private val editShopListUseCase = EditShopItem(repository)


    val shopList = getShopListUseCase.getShopList()

    fun deleteShopList(item: ShopItem) {
        viewModelScope.launch {
            deleteShopListUseCase.deleteShopItem(item)
        }
    }

    fun editShopList(item: ShopItem) {
        viewModelScope.launch {
            val new = item.copy(enabled = !item.enabled)
            editShopListUseCase.editShopItem(new)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}