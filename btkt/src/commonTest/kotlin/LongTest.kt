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
    fun testOneBits() {
        assertEquals(1, Long.MIN_VALUE.oneBits)
        assertEquals(64, (-1L).oneBits)
        assertEquals(0, 0L.oneBits)
        assertEquals(1, 1L.oneBits)
        assertEquals(63, Long.MAX_VALUE.oneBits)
    }

    @Test
    fun testHighestOneBit() {
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.highestOneBit)
        assertEquals(Long.MIN_VALUE, (-1L).highestOneBit)
        assertEquals(0L, 0L.highestOneBit)
        assertEquals(1L, 1L.highestOneBit)
        assertEquals(0x4000000000000000L, Long.MAX_VALUE.highestOneBit)
    }

    @Test
    fun testLowestOneBit() {
        assertEquals(Long.MIN_VALUE, Long.MIN_VALUE.lowestOneBit)
        assertEquals(1L, (-1L).lowestOneBit)
        assertEquals(0L, 0L.lowestOneBit)
        assertEquals(1L, 1L.lowestOneBit)
        assertEquals(1L, Long.MAX_VALUE.lowestOneBit)
    }

    @Test
    fun testLeadingZeros() {
        assertEquals(0, Long.MIN_VALUE.leadingZeros)
        assertEquals(0, (-1L).leadingZeros)
        assertEquals(64, 0L.leadingZeros)
        assertEquals(1, Long.MAX_VALUE.leadingZeros)
        repeat(Long.SIZE_BITS) { assertEquals(63 - it, (1L shl it).leadingZeros) }
    }

    @Test
    fun testTrailingZeros() {
        assertEquals(63, Long.MIN_VALUE.trailingZeros)
        assertEquals(0, (-1L).trailingZeros)
        assertEquals(64, 0L.trailingZeros)
        assertEquals(0, Long.MAX_VALUE.trailingZeros)
        repeat(Long.SIZE_BITS) { assertEquals(it, (1L shl it).trailingZeros) }
    }

    @Test
    fun testReverse() {
        assertEquals(1L, Long.MIN_VALUE.reverse())
        assertEquals(-1L, (-1L).reverse())
        assertEquals(0L, 0L.reverse())
        assertEquals(Long.MIN_VALUE, 1L.reverse())
        assertEquals(-2L, Long.MAX_VALUE.reverse())
    }

    @Test
    fun testReverseBytes() {
        assertEquals(0x0000000000000080L, Long.MIN_VALUE.reverseBytes())
        assertEquals(-1L, (-1L).reverseBytes())
        assertEquals(0L, 0L.reverseBytes())
        assertEquals(0x0100000000000000L, 1L.reverseBytes())
        assertEquals(-0x0000000000000081L, Long.MAX_VALUE.reverseBytes())
    }

    @Test
    fun testRol() {
        val x = 0x0007000500030001L
        assertEquals(x, x rol 0)
        assertEquals(0x0070005000300010L, x rol 4)
        assertEquals(0x0700050003000100L, x rol 8)
        assertEquals(0x7000500030001000L, x rol 12)
        assertEquals(0x0005000300010007L, x rol 16)
        assertEquals(0x0050003000100070L, x rol 20)
        assertEquals(0x0500030001000700L, x rol 24)
        assertEquals(0x5000300010007000L, x rol 28)
        assertEquals(0x0003000100070005L, x rol 32)
        assertEquals(0x0030001000700050L, x rol 36)
        assertEquals(0x0300010007000500L, x rol 40)
        assertEquals(0x3000100070005000L, x rol 44)
        assertEquals(0x0001000700050003L, x rol 48)
        assertEquals(0x0010007000500030L, x rol 52)
        assertEquals(0x0100070005000300L, x rol 56)
        assertEquals(0x1000700050003000L, x rol 60)
        assertEquals(x, x rol 64)
        assertEquals(0x1000700050003000L, x rol -4)
        assertEquals(0x0100070005000300L, x rol -8)
        assertEquals(0x0010007000500030L, x rol -12)
        assertEquals(0x0001000700050003L, x rol -16)
        assertEquals(0x3000100070005000L, x rol -20)
        assertEquals(0x0300010007000500L, x rol -24)
        assertEquals(0x0030001000700050L, x rol -28)
        assertEquals(0x0003000100070005L, x rol -32)
        assertEquals(0x5000300010007000L, x rol -36)
        assertEquals(0x0500030001000700L, x rol -40)
        assertEquals(0x0050003000100070L, x rol -44)
        assertEquals(0x0005000300010007L, x rol -48)
        assertEquals(0x7000500030001000L, x rol -52)
        assertEquals(0x0700050003000100L, x rol -56)
        assertEquals(0x0070005000300010L, x rol -60)
        assertEquals(x, x rol -64)
    }

    @Test
    fun testRor() {
        val x = 0x0007000500030001L
        assertEquals(x, x ror 0)
        assertEquals(0x1000700050003000L, x ror 4)
        assertEquals(0x0100070005000300L, x ror 8)
        assertEquals(0x0010007000500030L, x ror 12)
        assertEquals(0x0001000700050003L, x ror 16)
        assertEquals(0x3000100070005000L, x ror 20)
        assertEquals(0x0300010007000500L, x ror 24)
        assertEquals(0x0030001000700050L, x ror 28)
        assertEquals(0x0003000100070005L, x ror 32)
        assertEquals(0x5000300010007000L, x ror 36)
        assertEquals(0x0500030001000700L, x ror 40)
        assertEquals(0x0050003000100070L, x ror 44)
        assertEquals(0x0005000300010007L, x ror 48)
        assertEquals(0x7000500030001000L, x ror 52)
        assertEquals(0x0700050003000100L, x ror 56)
        assertEquals(0x0070005000300010L, x ror 60)
        assertEquals(x, x ror 64)
        assertEquals(0x0070005000300010L, x ror -4)
        assertEquals(0x0700050003000100L, x ror -8)
        assertEquals(0x7000500030001000L, x ror -12)
        assertEquals(0x0005000300010007L, x ror -16)
        assertEquals(0x0050003000100070L, x ror -20)
        assertEquals(0x0500030001000700L, x ror -24)
        assertEquals(0x5000300010007000L, x ror -28)
        assertEquals(0x0003000100070005L, x ror -32)
        assertEquals(0x0030001000700050L, x ror -36)
        assertEquals(0x0300010007000500L, x ror -40)
        assertEquals(0x3000100070005000L, x ror -44)
        assertEquals(0x0001000700050003L, x ror -48)
        assertEquals(0x0010007000500030L, x ror -52)
        assertEquals(0x0100070005000300L, x ror -56)
        assertEquals(0x1000700050003000L, x ror -60)
        assertEquals(x, x ror -64)
    }
}
