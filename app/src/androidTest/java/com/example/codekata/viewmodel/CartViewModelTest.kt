package com.example.codekata.viewmodel

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.codekata.util.ReadPriceRuleCsv
import com.example.codekata.model.Rule
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CartViewModelTest() {

//    val cartViewModel = CartViewModel()

    lateinit var mockContext: Context
    lateinit var cartViewModel: CartViewModel


    @Before
    fun setup() {
        mockContext = ApplicationProvider.getApplicationContext()
        // Mock Read Price Rule
//        val mockReadPriceRule = mockk<ReadPriceRuleCsv>()
//        every {
//            mockReadPriceRule.readCsv(any())
//        } returns listOf(
//            Rule("SKU1", 10f, 3, 25f),
//            Rule("SKU2", 20f, null, null)
//        )

        cartViewModel = CartViewModel(mockContext)
//        cartViewModel.readPriceRuleCsv = mockReadPriceRule
    }

    @Test
    fun testAddToCartSuccess() {
        val item = hashMapOf("SKU1" to 2)
        cartViewModel.addToCart(item)

        assertEquals(2, cartViewModel.cart["SKU1"])
    }

    @Test
    fun testRemoveFromCartSuccess() {
        cartViewModel.cart["SKU1"] = 3
        cartViewModel.removeFromCart("SKU1")

        assertEquals(2, cartViewModel.cart["SKU1"])
    }

//    @Test
//    fun testCalculateCartTotal() {
//        cartViewModel.cart["SKU1"] = 3
//        cartViewModel.cart["SKU2"] = 2
//        cartViewModel.calculateCartTotal()
//
//        assertEquals(65f, cartViewModel.totalPrice.value)
//    }
}