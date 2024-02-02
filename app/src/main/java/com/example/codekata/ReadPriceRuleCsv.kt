package com.example.codekata

import com.example.codekata.model.Rule
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

object ReadPriceRuleCsv {

    fun readCsv(path: String): List<Rule> {
        return csvReader().open(path) {
            readAllAsSequence().map {
                val (sku, price, batchSize, batchPrice) = it
                Rule(sku = sku, price = price.toFloat(), batchSize = batchSize.toIntOrNull(), batchPrice = batchPrice.toFloatOrNull())
            }.toList()
        }
    }
}