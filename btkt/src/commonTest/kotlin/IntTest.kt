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

import kotlin.test.Test
import kotlin.test.assertEquals

class IntTest {
    @Test
    fun testOneBits() {
        assertEquals(1, Int.MIN_VALUE.oneBits)
        assertEquals(32, (-1).oneBits)
        assertEquals(0, 0.oneBits)
        assertEquals(1, 1.oneBits)
        assertEquals(31, Int.MAX_VALUE.oneBits)
    }

    @Test
    fun testHighestOneBit() {
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE.highestOneBit)
        assertEquals(Int.MIN_VALUE, (-1).highestOneBit)
        assertEquals(0, 0.highestOneBit)
        assertEquals(1, 1.highestOneBit)
        assertEquals(0x40000000, Int.MAX_VALUE.highestOneBit)
    }

    @Test
    fun testLowestOneBit() {
        assertEquals(Int.MIN_VALUE, Int.MIN_VALUE.lowestOneBit)
        assertEquals(1, (-1).lowestOneBit)
        assertEquals(0, 0.lowestOneBit)
        assertEquals(1, 1.lowestOneBit)
        assertEquals(1, Int.MAX_VALUE.lowestOneBit)
    }

    @Test
    fun testLeadingZeros() {
        assertEquals(0, Int.MIN_VALUE.leadingZeros)
        assertEquals(0, (-1).leadingZeros)
        assertEquals(32, 0.leadingZeros)
        assertEquals(1, Int.MAX_VALUE.leadingZeros)
        repeat(Int.SIZE_BITS) { assertEquals(31 - it, (1 shl it).leadingZeros) }
    }

    @Test
    fun testTrailingZeros() {
        assertEquals(31, Int.MIN_VALUE.trailingZeros)
        assertEquals(0, (-1).trailingZeros)
        assertEquals(32, 0.trailingZeros)
        assertEquals(0, Int.MAX_VALUE.trailingZeros)
        repeat(Int.SIZE_BITS) { i -> assertEquals(i, (1 shl i).trailingZeros) }
    }

    @Test
    fun testReverse() {
        assertEquals(1, Int.MIN_VALUE.reverse())
        assertEquals(-1, (-1).reverse())
        assertEquals(0, 0.reverse())
        assertEquals(Int.MIN_VALUE, 1.reverse())
        assertEquals(-2, Int.MAX_VALUE.reverse())
    }

    @Test
    fun testReverseBytes() {
        assertEquals(0x00000080, Int.MIN_VALUE.reverseBytes())
        assertEquals(-1, (-1).reverseBytes())
        assertEquals(0, 0.reverseBytes())
        assertEquals(0x01000000, 1.reverseBytes())
        assertEquals(-0x00000081, Int.MAX_VALUE.reverseBytes())
    }

    @Test
    fun testRol() {
        val x = 0x07050301
        assertEquals(x, x rol 0)
        assertEquals(0x70503010, x rol 4)
        assertEquals(0x05030107, x rol 8)
        assertEquals(0x50301070, x rol 12)
        assertEquals(0x03010705, x rol 16)
        assertEquals(0x30107050, x rol 20)
        assertEquals(0x01070503, x rol 24)
        assertEquals(0x10705030, x rol 28)
        assertEquals(x, x rol 32)
        assertEquals(0x10705030, x rol -4)
        assertEquals(0x01070503, x rol -8)
        assertEquals(0x30107050, x rol -12)
        assertEquals(0x03010705, x rol -16)
        assertEquals(0x50301070, x rol -20)
        assertEquals(0x05030107, x rol -24)
        assertEquals(0x70503010, x rol -28)
        assertEquals(x, x rol -32)
    }

    @Test
    fun testRor() {
        val x = 0x07050301
        assertEquals(x, x ror 0)
        assertEquals(0x10705030, x ror 4)
        assertEquals(0x01070503, x ror 8)
        assertEquals(0x30107050, x ror 12)
        assertEquals(0x03010705, x ror 16)
        assertEquals(0x50301070, x ror 20)
        assertEquals(0x05030107, x ror 24)
        assertEquals(0x70503010, x ror 28)
        assertEquals(x, x ror 32)
        assertEquals(0x70503010, x ror -4)
        assertEquals(0x05030107, x ror -8)
        assertEquals(0x50301070, x ror -12)
        assertEquals(0x03010705, x ror -16)
        assertEquals(0x30107050, x ror -20)
        assertEquals(0x01070503, x ror -24)
        assertEquals(0x10705030, x ror -28)
        assertEquals(x, x ror -32)
    }
}
