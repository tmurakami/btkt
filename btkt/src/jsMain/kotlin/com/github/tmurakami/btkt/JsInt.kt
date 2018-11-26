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

// http://www.hackersdelight.org/hdcodetxt/pop.c.txt
actual val Int.oneBits: Int
    get() {
        var x = this
        x -= x shr 1 and 0x55555555
        x = (x and 0x33333333) + (x shr 2 and 0x33333333)
        x = x + (x shr 4) and 0x0F0F0F0F
        x += x shr 8
        x += x shr 16
        return x and 0x3F
    }

// http://www.hackersdelight.org/hdcodetxt/flp2.c.txt
actual val Int.highestOneBit: Int
    get() {
        var x = this
        x = x or (x shr 1)
        x = x or (x shr 2)
        x = x or (x shr 4)
        x = x or (x shr 8)
        x = x or (x shr 16)
        return x - (x ushr 1)
    }

// Robert Harley's algorithm
// http://www.hackersdelight.org/hdcodetxt/nlz.c.txt
// The entries shown as `-1` are never used.
private val NLZ_TABLE = intArrayOf(
    32, 31, -1, 16, -1, 30, 3, -1, 15, -1, -1, -1, 29, 10, 2, -1,
    -1, -1, 12, 14, 21, -1, 19, -1, -1, 28, -1, 25, -1, 9, 1, -1,
    17, -1, 4, -1, -1, -1, 11, -1, 13, 22, 20, -1, 26, -1, -1, 18,
    5, -1, -1, 23, -1, 27, -1, 6, -1, 24, 7, -1, 8, -1, 0, -1
)
// TODO if Kotlin drops support for ECMAScript 5.1, we may use `Math.clz32()`.
actual val Int.leadingZeros: Int
    get() {
        var x = this
        x = x or (x shr 1)
        x = x or (x shr 2)
        x = x or (x shr 4)
        x = x or (x shr 8)
        x = x or (x shr 16)
        return NLZ_TABLE[x * 0x06EB14F9 ushr 26]
    }

// David Seal's algorithm
// http://www.hackersdelight.org/hdcodetxt/ntz.c.txt
// The entries shown as `-1` are never used.
private val NTZ_TABLE = intArrayOf(
    32, 0, 1, 12, 2, 6, -1, 13, 3, -1, 7, -1, -1, -1, -1, 14,
    10, 4, -1, -1, 8, -1, -1, 25, -1, -1, -1, -1, -1, 21, 27, 15,
    31, 11, 5, -1, -1, -1, -1, -1, 9, -1, -1, 24, -1, -1, 20, 26,
    30, -1, -1, -1, -1, 23, -1, 19, 29, -1, 22, 18, 28, 17, 16, -1
)
actual val Int.trailingZeros: Int get() = NTZ_TABLE[lowestOneBit * 0x0450FBAF ushr 26]

// http://www.hackersdelight.org/hdcodetxt/reverse.c.txt
actual fun Int.reverse(): Int {
    var x = this
    x = x and 0x55555555 shl 1 or (x ushr 1 and 0x55555555)
    x = x and 0x33333333 shl 2 or (x ushr 2 and 0x33333333)
    x = x and 0x0F0F0F0F shl 4 or (x ushr 4 and 0x0F0F0F0F)
    return x shl 24 or (x and 0xFF00 shl 8) or (x ushr 8 and 0xFF00) or (x ushr 24)
}

// http://www.hackersdelight.org/hdcodetxt/reverse.c.txt
actual fun Int.reverseBytes(): Int =
    shl(24) or (and(0xFF00) shl 8) or (ushr(8) and 0xFF00) or ushr(24)
