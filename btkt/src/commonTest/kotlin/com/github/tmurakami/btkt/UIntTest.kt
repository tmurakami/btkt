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

import kotlin.UInt.Companion.MAX_VALUE
import kotlin.UInt.Companion.SIZE_BITS
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalUnsignedTypes
class UIntTest {
    @Test
    fun oneBits() {
        assertEquals(0, 0u.oneBits)
        assertEquals(1, 1u.oneBits)
        assertEquals(32, MAX_VALUE.oneBits)
    }

    @Test
    fun highestOneBit() {
        assertEquals(0u, 0u.highestOneBit)
        assertEquals(1u, 1u.highestOneBit)
        assertEquals(0x80000000u, MAX_VALUE.highestOneBit)
    }

    @Test
    fun lowestOneBit() {
        assertEquals(0u, 0u.lowestOneBit)
        assertEquals(1u, 1u.lowestOneBit)
        assertEquals(1u, MAX_VALUE.lowestOneBit)
    }

    @Test
    fun leadingZeros() {
        assertEquals(32, 0u.leadingZeros)
        assertEquals(0, MAX_VALUE.leadingZeros)
        repeat(SIZE_BITS) { assertEquals(31 - it, (1u shl it).leadingZeros) }
    }

    @Test
    fun trailingZeros() {
        assertEquals(32, 0u.trailingZeros)
        assertEquals(0, MAX_VALUE.trailingZeros)
        repeat(SIZE_BITS) { assertEquals(it, (1u shl it).trailingZeros) }
    }

    @Test
    fun reverse() {
        assertEquals(0u, 0u.reverse())
        assertEquals(0x80000000u, 1u.reverse())
        assertEquals(MAX_VALUE, MAX_VALUE.reverse())
    }

    @Test
    fun reverseBytes() {
        assertEquals(0u, 0u.reverseBytes())
        assertEquals(0x01000000u, 1u.reverseBytes())
        assertEquals(MAX_VALUE, MAX_VALUE.reverseBytes())
    }

    @Test
    fun rol() {
        val x = 1u
        val bitCount = 8
        val actual = x rol bitCount
        assertEquals(0x00000100u, actual)
        assertEquals(x shl bitCount or (x shr -bitCount), actual)
    }

    @Test
    fun ror() {
        val x = 1u
        val bitCount = 8
        val actual = x ror bitCount
        assertEquals(0x01000000u, actual)
        assertEquals(x shr bitCount or (x shl -bitCount), actual)
    }
}
