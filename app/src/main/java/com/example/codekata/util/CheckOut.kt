package com.example.codekata.util

class CheckOut {
    private var cart: LinkedHashMap<String, Int> = linkedMapOf()
    private var priceRule = PriceRule()
    var total: Float = 0f

    fun scan(item: String) {
        if (cart.keys.contains(item)) {
            cart[item] = cart[item]!! + 1
        } else {
            cart[item] = 1
        }
        calculateTotal()
    }

    private fun calculateTotal() {
        total = priceRule.calculateCartTotal(cart)
    }

}