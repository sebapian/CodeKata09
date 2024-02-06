package com.example.codekata

import com.example.codekata.ReadPriceRuleCsv.readCsv
import com.example.codekata.model.Rule
import java.io.InputStream

class PriceRule {

    lateinit var priceRule: List<Rule>

    fun setPriceRule(inputStream: InputStream) {
        this.priceRule = readCsv(inputStream)
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