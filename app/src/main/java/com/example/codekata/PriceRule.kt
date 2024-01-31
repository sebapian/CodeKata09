package com.example.codekata

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class PriceRule {

    private var priceRule = readCsv(Constants.RULE_DESTINATION)

    fun readCsv(path: String): List<Rule> {
        return csvReader().open(path) {
            readAllAsSequence().map {
                val (sku, price, batchSize, batchPrice) = it
                Rule(sku = sku, price = price.toFloat(), batchSize = batchSize.toIntOrNull(), batchPrice = batchPrice.toFloatOrNull())
            }.toList()

        }
    }

    fun setPriceRule(path: String) {
        this.priceRule = PriceRule().readCsv(path)
    }

    fun calculateCartTotal(cart: LinkedHashMap<String, Int>): Float {
        var total = 0f
        cart.forEach { item ->
            val itemAmount = item.value
            val itemRule = priceRule.first { it.sku == item.key }
            total += if (itemRule.batchSize != null && itemRule.batchPrice != null) {
                if (itemAmount % itemRule.batchSize == 0) {
                    itemAmount / itemRule.batchSize * itemRule.batchPrice
                } else {
                    itemAmount % itemRule.batchSize * itemRule.price + itemAmount / itemRule.batchSize * itemRule.batchPrice
                }
            } else {
                itemAmount * itemRule.price
            }
        }
        return total
    }
}

data class Rule(
    val sku: String,
    val price: Float,
    val batchSize: Int?,
    val batchPrice: Float?
)