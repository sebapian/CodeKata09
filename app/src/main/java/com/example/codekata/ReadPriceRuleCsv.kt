package com.example.codekata

import com.example.codekata.model.Rule
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

object ReadPriceRuleCsv {

    fun readCsv(path: String): LinkedHashMap<String, Rule> {
        var priceRuleHashMap = linkedMapOf<String, Rule>()
        csvReader().open(path) {
            readAllAsSequence().forEach {
                val (sku, price, batchSize, batchPrice) = it
                priceRuleHashMap[sku] = Rule(sku = sku, price = price.toFloat(), batchSize = batchSize.toIntOrNull(), batchPrice = batchPrice.toFloatOrNull())
            }
        }
        return priceRuleHashMap
    }
}