package com.example.codekata.util

import com.example.codekata.model.Rule
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream

object ReadPriceRuleCsv {

    fun readCsv(inputStream: InputStream): List<Rule> {
        return csvReader().open(inputStream) {
            readAllAsSequence().map {
                val (sku, price, batchSize, batchPrice) = it
                Rule(sku = sku, price = price.toFloat(), batchSize = batchSize.toIntOrNull(), batchPrice = batchPrice.toFloatOrNull())
            }.toList()
        }
    }
}