package com.example.codekata

import android.util.Log
import com.example.codekata.ReadPriceRuleCsv.readCsv

class PriceRule {

    private var priceRule = readCsv(Constants.RULE_DESTINATION)

    fun setPriceRule(path: String) {
        this.priceRule = readCsv(path)
    }

    fun calculateCartTotal(cart: LinkedHashMap<String, Int>): Float {
        var total = 0f
        cart.forEach { item ->
            val selectedItemAmount = item.value
            val selectedItemRule = priceRule[item.key]
                ?: throw IllegalArgumentException("Item not found in price rule")

            val itemPrice = selectedItemRule.price
            val itemBatchSize = selectedItemRule.batchSize
            val itemBatchPrice = selectedItemRule.batchPrice

            // Because the batch size and batch price are optional, we need to check if they are null
            total += if (itemBatchSize != null && itemBatchPrice != null) {
                // If the item amount is a multiple of the batch size, we can calculate the total price using the batch price
                if (selectedItemAmount % itemBatchSize == 0) {
                    selectedItemAmount / itemBatchSize * itemBatchPrice
                } else {
                    // If the item amount is not a multiple of the batch size, we need to calculate the total price using the batch price and the regular price
                    selectedItemAmount % itemBatchSize * itemPrice + selectedItemAmount / itemBatchSize * itemBatchPrice
                }
            } else {
                selectedItemAmount * itemPrice
            }
        }
        return total
    }
}