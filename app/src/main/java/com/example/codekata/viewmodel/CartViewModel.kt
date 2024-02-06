package com.example.codekata.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codekata.R
import com.example.codekata.ReadPriceRuleCsv
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(@ApplicationContext context: Context): ViewModel() {

    val cart = mutableStateMapOf<String, Int>()
    val priceRule = ReadPriceRuleCsv.readCsv(context.resources.openRawResource(R.raw.price_rules))
    val totalPrice = mutableFloatStateOf(0f)

    fun addToCart(item: HashMap<String, Int>) {
        val sku = item.keys.first()
        val itemAmount = item.values.first()
        Log.d("SK", "sku: $sku, itemAmount: $itemAmount")
        if (cart.keys.contains(sku)) {
            cart[sku] = cart[sku]!! + itemAmount
        } else {
            cart[sku] = itemAmount
        }
        totalPrice.value = calculateCartTotal(cart.toMap(LinkedHashMap()))
    }

    fun removeFromCart(sku: String) {
        if (cart.keys.contains(sku)) {
            if (cart[sku]!! > 0) {
                Log.d("SK", "sku: $sku, cartAmount: ${cart[sku]}")
                cart[sku] = cart[sku]!! - 1
            }
        }
        totalPrice.value = calculateCartTotal(cart.toMap(LinkedHashMap()))
    }

    fun calculateCartTotal(cart: LinkedHashMap<String, Int>): Float {
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
        return total
    }
}