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

class LongTest {
    @Test
    fun oneBits() {
        assertEquals(1, Long.MIN_VALUE.oneBits)
        assertEquals(64, (-1L).oneBits)
        assertEquals(0, 0L.oneBits)
        assertEquals(1, 1L.oneBits)
        assertEquals(63, Long.MAX_VALUE.oneBits)
    }

    @Test
    fun highestOneBit() {
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.highestOneBit)
        assertEquals(Long.MIN_VALUE, (-1L).highestOneBit)
        assertEquals(0L, 0L.highestOneBit)
        assertEquals(1L, 1L.highestOneBit)
        assertEquals(0x4000000000000000L, Long.MAX_VALUE.highestOneBit)
    }

    @Test
    fun lowestOneBit() {
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.lowestOneBit)
        assertEquals(1L, (-1L).lowestOneBit)
        assertEquals(0L, 0L.lowestOneBit)
        assertEquals(1L, 1L.lowestOneBit)
        assertEquals(1L, Long.MAX_VALUE.lowestOneBit)
    }

    @Test
    fun leadingZeros() {
        assertEquals(0, Long.MIN_VALUE.leadingZeros)
        assertEquals(0, (-1L).leadingZeros)
        assertEquals(64, 0L.leadingZeros)
        assertEquals(1, Long.MAX_VALUE.leadingZeros)
        repeat(Long.SIZE_BITS) { assertEquals(63 - it, (1L shl it).leadingZeros) }
    }

    @Test
    fun trailingZeros() {
        assertEquals(63, Long.MIN_VALUE.trailingZeros)
        assertEquals(0, (-1L).trailingZeros)
        assertEquals(64, 0L.trailingZeros)
        assertEquals(0, Long.MAX_VALUE.trailingZeros)
        repeat(Long.SIZE_BITS) { assertEquals(it, (1L shl it).trailingZeros) }
    }

    @Test
    fun reverse() {
        assertEquals(1L, Long.MIN_VALUE.reverse())
        assertEquals(-1L, (-1L).reverse())
        assertEquals(0L, 0L.reverse())
        assertEquals(Long.MIN_VALUE, 1L.reverse())
        assertEquals(-2L, Long.MAX_VALUE.reverse())
    }

    @Test
    fun reverseBytes() {
        assertEquals(0x0000000000000080L, Long.MIN_VALUE.reverseBytes())
        assertEquals(-1L, (-1L).reverseBytes())
        assertEquals(0L, 0L.reverseBytes())
        assertEquals(0x0100000000000000L, 1L.reverseBytes())
        assertEquals(-0x0000000000000081L, Long.MAX_VALUE.reverseBytes())
    }

    @Test
    fun rol() {
        val x = 1L
        assertEquals(0x0000000000000100L, x rol 8)
        assertEquals(0x0100000000000000L, x rol -8)
    }

    @Test
    fun ror() {
        val x = 1L
        assertEquals(0x0100000000000000L, x ror 8)
        assertEquals(0x0000000000000100L, x ror -8)
    }
}
