package com.example.shop.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shop.domain.ShopItem
import com.example.shop.domain.ShopListRepo
import java.lang.RuntimeException
import kotlin.random.Random

object ShopRepoImpl : ShopListRepo {

    private val shopList = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id) })
    private var autoID = 0
    private var shopListLD = MutableLiveData <List<ShopItem>>()


    init{
        for(i in 0 until 100){
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoID++
        }
        shopList.add(shopItem)
        updateList()
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
    }

    private fun updateList() {
        shopListLD.value = shopList.toList()
    }
}