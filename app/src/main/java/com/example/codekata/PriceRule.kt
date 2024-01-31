package com.example.codekata

import android.util.Log
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.File

class PriceRule {

    private var priceRule = readCsv(Constants.RULE_DESTINATION)

    fun readCsv(path: String): List<Rules> {
        return csvReader().open(path) {
            readAllAsSequence().map {
                val (sku, price, batchSize, batchPrice) = it
                Rules(sku = sku, price = price.toFloat(), batchSize = batchSize.toIntOrNull(), batchPrice = batchPrice.toFloatOrNull())
            }.toList()

        }
    }

    fun setPriceRule(path: String) {
        this.priceRule = PriceRule().readCsv(path)
    }

    fun calculateCartTotal(cart: LinkedHashMap<String, Int>): Float {
        var total = 0f
        cart.forEach { item ->
            val itemRule = priceRule.first { it.sku == item.key }
            total += if (itemRule.batchSize != null && itemRule.batchPrice != null) {
                if (item.value % itemRule.batchSize == 0) {
                    item.value / itemRule.batchSize * itemRule.batchPrice
                } else {
                    item.value % itemRule.batchSize * itemRule.price + item.value / itemRule.batchSize * itemRule.batchPrice
                }
            } else {
                item.value * itemRule.price
            }
        }
        return total
    }
}

data class Rules(
    val sku: String,
    val price: Float,
    val batchSize: Int?,
    val batchPrice: Float?
)