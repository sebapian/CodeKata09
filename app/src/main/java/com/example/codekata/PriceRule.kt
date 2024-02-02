package com.example.codekata

import com.example.codekata.ReadPriceRuleCsv.readCsv

class PriceRule {

    private var priceRule = readCsv(Constants.RULE_DESTINATION)

    fun setPriceRule(path: String) {
        this.priceRule = readCsv(path)
    }

    fun calculateCartTotal(cart: LinkedHashMap<String, Int>): Float {
        var total = 0f
        cart.forEach { item ->
            val itemAmount = item.value
            val itemRule = priceRule.first { it.sku == item.key }
            val itemPrice = itemRule.price
            val itemBatchSize = itemRule.batchSize
            val itemBatchPrice = itemRule.batchPrice

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