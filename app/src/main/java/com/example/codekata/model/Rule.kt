package com.example.codekata.model

data class Rule(
    val sku: String,
    val price: Float,
    val batchSize: Int?,
    val batchPrice: Float?
)
