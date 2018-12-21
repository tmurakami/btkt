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
        val x = 0x0007000500030001UL
        assertEquals(x, x rol 0)
        assertEquals(0x0070005000300010UL, x rol 4)
        assertEquals(0x0700050003000100UL, x rol 8)
        assertEquals(0x7000500030001000UL, x rol 12)
        assertEquals(0x0005000300010007UL, x rol 16)
        assertEquals(0x0050003000100070UL, x rol 20)
        assertEquals(0x0500030001000700UL, x rol 24)
        assertEquals(0x5000300010007000UL, x rol 28)
        assertEquals(0x0003000100070005UL, x rol 32)
        assertEquals(0x0030001000700050UL, x rol 36)
        assertEquals(0x0300010007000500UL, x rol 40)
        assertEquals(0x3000100070005000UL, x rol 44)
        assertEquals(0x0001000700050003UL, x rol 48)
        assertEquals(0x0010007000500030UL, x rol 52)
        assertEquals(0x0100070005000300UL, x rol 56)
        assertEquals(0x1000700050003000UL, x rol 60)
        assertEquals(x, x rol 64)
        assertEquals(0x1000700050003000UL, x rol -4)
        assertEquals(0x0100070005000300UL, x rol -8)
        assertEquals(0x0010007000500030UL, x rol -12)
        assertEquals(0x0001000700050003UL, x rol -16)
        assertEquals(0x3000100070005000UL, x rol -20)
        assertEquals(0x0300010007000500UL, x rol -24)
        assertEquals(0x0030001000700050UL, x rol -28)
        assertEquals(0x0003000100070005UL, x rol -32)
        assertEquals(0x5000300010007000UL, x rol -36)
        assertEquals(0x0500030001000700UL, x rol -40)
        assertEquals(0x0050003000100070UL, x rol -44)
        assertEquals(0x0005000300010007UL, x rol -48)
        assertEquals(0x7000500030001000UL, x rol -52)
        assertEquals(0x0700050003000100UL, x rol -56)
        assertEquals(0x0070005000300010UL, x rol -60)
        assertEquals(x, x rol -64)
    }

    @Test
    fun ror() {
        val x = 0x0007000500030001UL
        assertEquals(x, x ror 0)
        assertEquals(0x1000700050003000UL, x ror 4)
        assertEquals(0x0100070005000300UL, x ror 8)
        assertEquals(0x0010007000500030UL, x ror 12)
        assertEquals(0x0001000700050003UL, x ror 16)
        assertEquals(0x3000100070005000UL, x ror 20)
        assertEquals(0x0300010007000500UL, x ror 24)
        assertEquals(0x0030001000700050UL, x ror 28)
        assertEquals(0x0003000100070005UL, x ror 32)
        assertEquals(0x5000300010007000UL, x ror 36)
        assertEquals(0x0500030001000700UL, x ror 40)
        assertEquals(0x0050003000100070UL, x ror 44)
        assertEquals(0x0005000300010007UL, x ror 48)
        assertEquals(0x7000500030001000UL, x ror 52)
        assertEquals(0x0700050003000100UL, x ror 56)
        assertEquals(0x0070005000300010UL, x ror 60)
        assertEquals(x, x ror 64)
        assertEquals(0x0070005000300010UL, x ror -4)
        assertEquals(0x0700050003000100UL, x ror -8)
        assertEquals(0x7000500030001000UL, x ror -12)
        assertEquals(0x0005000300010007UL, x ror -16)
        assertEquals(0x0050003000100070UL, x ror -20)
        assertEquals(0x0500030001000700UL, x ror -24)
        assertEquals(0x5000300010007000UL, x ror -28)
        assertEquals(0x0003000100070005UL, x ror -32)
        assertEquals(0x0030001000700050UL, x ror -36)
        assertEquals(0x0300010007000500UL, x ror -40)
        assertEquals(0x3000100070005000UL, x ror -44)
        assertEquals(0x0001000700050003UL, x ror -48)
        assertEquals(0x0010007000500030UL, x ror -52)
        assertEquals(0x0100070005000300UL, x ror -56)
        assertEquals(0x1000700050003000UL, x ror -60)
        assertEquals(x, x ror -64)
    }
}
