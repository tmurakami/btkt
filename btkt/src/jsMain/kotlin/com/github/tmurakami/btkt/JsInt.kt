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

// http://www.hackersdelight.org/hdcodetxt/nlz.c.txt
// TODO if Kotlin drops support for ECMAScript 5.1, we may use `Math.clz32()`.
actual val Int.leadingZeros: Int
    get() {
        if (this <= 0) return inv() shr 26 and 32
        var x = this
        var n = 1
        if (x shr 16 == 0) run { n += 16; x = x shl 16 }
        if (x shr 24 == 0) run { n += 8; x = x shl 8 }
        if (x shr 28 == 0) run { n += 4; x = x shl 4 }
        if (x shr 30 == 0) run { n += 2; x = x shl 2 }
        return n - (x ushr 31)
    }

// http://www.hackersdelight.org/hdcodetxt/ntz.c.txt
actual val Int.trailingZeros: Int
    get() {
        if (this == 0) return 32
        var x = this
        var n = 31
        var y = x shl 16; if (y != 0) run { n -= 16; x = y }
        y = x shl 8; if (y != 0) run { n -= 8; x = y }
        y = x shl 4; if (y != 0) run { n -= 4; x = y }
        y = x shl 2; if (y != 0) run { n -= 2; x = y }
        return n - (x shl 1 ushr 31)
    }

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
