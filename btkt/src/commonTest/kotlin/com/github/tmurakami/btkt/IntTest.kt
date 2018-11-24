/*
 * Copyright 2018 Tsuyoshi Murakami
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.tmurakami.btkt

import kotlin.Int.Companion.MAX_VALUE
import kotlin.Int.Companion.MIN_VALUE
import kotlin.Int.Companion.SIZE_BITS
import kotlin.test.Test
import kotlin.test.assertEquals

class IntTest {
    @Test
    fun oneBits() {
        assertEquals(1, MIN_VALUE.oneBits)
        assertEquals(32, (-1).oneBits)
        assertEquals(0, 0.oneBits)
        assertEquals(1, 1.oneBits)
        assertEquals(31, MAX_VALUE.oneBits)
    }

    @Test
    fun highestOneBit() {
        assertEquals(MIN_VALUE, MIN_VALUE.highestOneBit)
        assertEquals(MIN_VALUE, (-1).highestOneBit)
        assertEquals(0, 0.highestOneBit)
        assertEquals(1, 1.highestOneBit)
        assertEquals(0x40000000, MAX_VALUE.highestOneBit)
    }

    @Test
    fun lowestOneBit() {
        assertEquals(MIN_VALUE, MIN_VALUE.lowestOneBit)
        assertEquals(1, (-1).lowestOneBit)
        assertEquals(0, 0.lowestOneBit)
        assertEquals(1, 1.lowestOneBit)
        assertEquals(1, MAX_VALUE.lowestOneBit)
    }

    @Test
    fun leadingZeros() {
        assertEquals(0, MIN_VALUE.leadingZeros)
        assertEquals(0, (-1).leadingZeros)
        assertEquals(32, 0.leadingZeros)
        assertEquals(1, MAX_VALUE.leadingZeros)
        repeat(SIZE_BITS) { assertEquals(31 - it, (1 shl it).leadingZeros) }
    }

    @Test
    fun trailingZeros() {
        assertEquals(31, MIN_VALUE.trailingZeros)
        assertEquals(0, (-1).trailingZeros)
        assertEquals(32, 0.trailingZeros)
        assertEquals(0, MAX_VALUE.trailingZeros)
        repeat(SIZE_BITS) { i -> assertEquals(i, (1 shl i).trailingZeros) }
    }

    @Test
    fun reverse() {
        assertEquals(1, MIN_VALUE.reverse())
        assertEquals(-1, (-1).reverse())
        assertEquals(0, 0.reverse())
        assertEquals(MIN_VALUE, 1.reverse())
        assertEquals(-2, MAX_VALUE.reverse())
    }

    @Test
    fun reverseBytes() {
        assertEquals(0x00000080, MIN_VALUE.reverseBytes())
        assertEquals(-1, (-1).reverseBytes())
        assertEquals(0, 0.reverseBytes())
        assertEquals(0x01000000, 1.reverseBytes())
        assertEquals(-0x00000081, MAX_VALUE.reverseBytes())
    }

    @Test
    fun rol() {
        val x = 1
        val bitCount = 8
        val actual = x rol bitCount
        assertEquals(0x00000100, actual)
        assertEquals(x shl bitCount or (x ushr -bitCount), actual)
    }

    @Test
    fun ror() {
        val x = 1
        val bitCount = 8
        val actual = x ror bitCount
        assertEquals(0x01000000, actual)
        assertEquals(x ushr bitCount or (x shl -bitCount), actual)
    }
}
