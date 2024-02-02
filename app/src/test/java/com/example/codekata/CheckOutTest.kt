package com.example.codekata

import org.junit.Assert.*
import org.junit.Test

class CheckOutTest {

    fun calculatePrice(goods: String): Float {
        val checkOut = CheckOut()
        for (good in goods) {
            checkOut.scan(good.toString())
        }
        return checkOut.total
    }

    @Test
    fun `test totals`() {
        assertEquals(0f, calculatePrice(""))
        assertEquals(50f, calculatePrice("A"))
        assertEquals(80f, calculatePrice("AB"))
        assertEquals(115f, calculatePrice("CDBA"))

        assertEquals(100f, calculatePrice("AA"))
        assertEquals(130f, calculatePrice("AAA"))
        assertEquals(180f, calculatePrice("AAAA"))
        assertEquals(230f, calculatePrice("AAAAA"))
        assertEquals(260f, calculatePrice("AAAAAA"))

        assertEquals(160f, calculatePrice("AAAB"))
        assertEquals(175f, calculatePrice("AAABB"))
        assertEquals(190f, calculatePrice("AAABBD"))
        assertEquals(190f, calculatePrice("DABABA"))
    }

    @Test
    fun `test incremental`() {
        val co = CheckOut()
        assertEquals(0f, co.total)
        co.scan("A"); assertEquals(50f,co.total)
        co.scan("B"); assertEquals(80f,co.total)
        co.scan("A"); assertEquals(130f,co.total)
        co.scan("A"); assertEquals(160f,co.total)
        co.scan("B"); assertEquals(175f,co.total)
    }

    @Test
    fun `test item not found in price rule`() {
        val co = CheckOut()
        assertThrows(IllegalArgumentException::class.java) {
            co.scan("E")
            co.scan("R")
        }
    }



}