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
class UIntTest {
    @Test
    fun testOneBits() {
        assertEquals(0, 0u.oneBits)
        assertEquals(1, 1u.oneBits)
        assertEquals(32, UInt.MAX_VALUE.oneBits)
    }

    @Test
    fun testHighestOneBit() {
        assertEquals(0u, 0u.highestOneBit)
        assertEquals(1u, 1u.highestOneBit)
        assertEquals(0x80000000u, UInt.MAX_VALUE.highestOneBit)
    }

    @Test
    fun testLowestOneBit() {
        assertEquals(0u, 0u.lowestOneBit)
        assertEquals(1u, 1u.lowestOneBit)
        assertEquals(1u, UInt.MAX_VALUE.lowestOneBit)
    }

    @Test
    fun testLeadingZeros() {
        assertEquals(32, 0u.leadingZeros)
        assertEquals(0, UInt.MAX_VALUE.leadingZeros)
        repeat(UInt.SIZE_BITS) { assertEquals(31 - it, (1u shl it).leadingZeros) }
    }

    @Test
    fun testTrailingZeros() {
        assertEquals(32, 0u.trailingZeros)
        assertEquals(0, UInt.MAX_VALUE.trailingZeros)
        repeat(UInt.SIZE_BITS) { assertEquals(it, (1u shl it).trailingZeros) }
    }

    @Test
    fun testReverse() {
        assertEquals(0u, 0u.reverse())
        assertEquals(0x80000000u, 1u.reverse())
        assertEquals(UInt.MAX_VALUE, UInt.MAX_VALUE.reverse())
    }

    @Test
    fun testReverseBytes() {
        assertEquals(0u, 0u.reverseBytes())
        assertEquals(0x01000000u, 1u.reverseBytes())
        assertEquals(UInt.MAX_VALUE, UInt.MAX_VALUE.reverseBytes())
    }

    @Test
    fun testRol() {
        val x = 0x07050301u
        assertEquals(x, x rol 0)
        assertEquals(0x70503010u, x rol 4)
        assertEquals(0x05030107u, x rol 8)
        assertEquals(0x50301070u, x rol 12)
        assertEquals(0x03010705u, x rol 16)
        assertEquals(0x30107050u, x rol 20)
        assertEquals(0x01070503u, x rol 24)
        assertEquals(0x10705030u, x rol 28)
        assertEquals(x, x rol 32)
        assertEquals(0x10705030u, x rol -4)
        assertEquals(0x01070503u, x rol -8)
        assertEquals(0x30107050u, x rol -12)
        assertEquals(0x03010705u, x rol -16)
        assertEquals(0x50301070u, x rol -20)
        assertEquals(0x05030107u, x rol -24)
        assertEquals(0x70503010u, x rol -28)
        assertEquals(x, x rol -32)
    }

    @Test
    fun testRor() {
        val x = 0x07050301u
        assertEquals(x, x ror 0)
        assertEquals(0x10705030u, x ror 4)
        assertEquals(0x01070503u, x ror 8)
        assertEquals(0x30107050u, x ror 12)
        assertEquals(0x03010705u, x ror 16)
        assertEquals(0x50301070u, x ror 20)
        assertEquals(0x05030107u, x ror 24)
        assertEquals(0x70503010u, x ror 28)
        assertEquals(x, x ror 32)
        assertEquals(0x70503010u, x ror -4)
        assertEquals(0x05030107u, x ror -8)
        assertEquals(0x50301070u, x ror -12)
        assertEquals(0x03010705u, x ror -16)
        assertEquals(0x30107050u, x ror -20)
        assertEquals(0x01070503u, x ror -24)
        assertEquals(0x10705030u, x ror -28)
        assertEquals(x, x ror -32)
    }
}
