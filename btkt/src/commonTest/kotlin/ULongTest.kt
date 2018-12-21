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

@ExperimentalUnsignedTypes
class ULongTest {
    @Test
    fun oneBits() {
        assertEquals(0, 0UL.oneBits)
        assertEquals(1, 1UL.oneBits)
        assertEquals(64, ULong.MAX_VALUE.oneBits)
    }

    @Test
    fun highestOneBit() {
        assertEquals(0UL, 0UL.highestOneBit)
        assertEquals(1UL, 1UL.highestOneBit)
        assertEquals(0x8000000000000000UL, ULong.MAX_VALUE.highestOneBit)
    }

    @Test
    fun lowestOneBit() {
        assertEquals(0UL, 0UL.lowestOneBit)
        assertEquals(1UL, 1UL.lowestOneBit)
        assertEquals(1UL, ULong.MAX_VALUE.lowestOneBit)
    }

    @Test
    fun leadingZeros() {
        assertEquals(64, 0UL.leadingZeros)
        assertEquals(0, ULong.MAX_VALUE.leadingZeros)
        repeat(ULong.SIZE_BITS) { assertEquals(63 - it, (1UL shl it).leadingZeros) }
    }

    @Test
    fun trailingZeros() {
        assertEquals(64, 0UL.trailingZeros)
        assertEquals(0, ULong.MAX_VALUE.trailingZeros)
        repeat(ULong.SIZE_BITS) { assertEquals(it, (1UL shl it).trailingZeros) }
    }

    @Test
    fun reverse() {
        assertEquals(0UL, 0UL.reverse())
        assertEquals(0x8000000000000000UL, 1UL.reverse())
        assertEquals(ULong.MAX_VALUE, ULong.MAX_VALUE.reverse())
    }

    @Test
    fun reverseBytes() {
        assertEquals(0UL, 0UL.reverseBytes())
        assertEquals(0x0100000000000000UL, 1UL.reverseBytes())
        assertEquals(ULong.MAX_VALUE, ULong.MAX_VALUE.reverseBytes())
    }

    @Test
    fun rol() {
        val x = 1UL
        assertEquals(0x0000000000000100UL, x rol 8)
        assertEquals(0x0100000000000000UL, x rol -8)
    }

    @Test
    fun ror() {
        val x = 1UL
        assertEquals(0x0100000000000000UL, x ror 8)
        assertEquals(0x0000000000000100UL, x ror -8)
    }
}
