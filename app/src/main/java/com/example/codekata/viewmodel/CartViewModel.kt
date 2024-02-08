package com.example.codekata.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.codekata.R
import com.example.codekata.ReadPriceRuleCsv
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    val cart = mutableStateMapOf<String, Int>()
    val priceRule = ReadPriceRuleCsv.readCsv(context.resources.openRawResource(R.raw.price_rules))
    val totalPrice = mutableFloatStateOf(0f)

    fun addToCart(item: HashMap<String, Int>) {
        val sku = item.keys.first()
        val itemAmount = item.values.first()
        if (cart.keys.contains(sku)) {
            cart[sku] = cart[sku]!! + itemAmount
        } else {
            cart[sku] = itemAmount
        }
    }

    fun removeFromCart(sku: String) {
        if (cart.keys.contains(sku)) {
            if (cart[sku]!! > 0) {
                cart[sku] = cart[sku]!! - 1
            }
        }
    }

    fun calculateCartTotal() {
        var total = 0f
        cart.forEach { item ->
            val itemAmount = item.value
            val itemRule = priceRule.filter { it.sku == item.key }
            if (itemRule.isEmpty()) {
                throw IllegalArgumentException("Item not found in price rule")
            }
            val firstMatchingItem = itemRule.first()
            val itemPrice = firstMatchingItem.price
            val itemBatchSize = firstMatchingItem.batchSize
            val itemBatchPrice = firstMatchingItem.batchPrice

            // Because the batch size and batch price are optional, we need to check if they are null
            total += if (itemBatchSize != null && itemBatchPrice != null) {
                // If the item amount is a multiple of the batch size, we can calculate the total price using the batch price
                if (itemAmount % itemBatchSize == 0) {
                    itemAmount / itemBatchSize * itemBatchPrice
                } else {
                    // If the item amount is not a multiple of the batch size, we need to calculate the total price using the batch price and the regular price
                    itemAmount % itemBatchSize * itemPrice + itemAmount / itemBatchSize * itemBatchPrice
                }
            } else {
                itemAmount * itemPrice
            }
        }
        totalPrice.value = total
    }
}