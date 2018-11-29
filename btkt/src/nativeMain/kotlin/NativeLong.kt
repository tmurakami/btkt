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

@file:Suppress("NOTHING_TO_INLINE")

package com.github.tmurakami.btkt

import platform.posix.ffs

actual val Long.oneBits: Int
    get() {
        // http://www.hackersdelight.org/hdcodetxt/pop.c.txt
        var x = this
        x -= x ushr 1 and 0x5555555555555555L
        x = (x and 0x3333333333333333L) + (x ushr 2 and 0x3333333333333333L)
        x = x + (x ushr 4) and 0x0F0F0F0F0F0F0F0FL
        x += x ushr 8
        x += x ushr 16
        x += x ushr 32
        return x.toInt() and 0x7F
    }

actual val Long.highestOneBit: Long
    get() {
        // http://www.hackersdelight.org/hdcodetxt/flp2.c.txt
        var x = this
        x = x or (x shr 1)
        x = x or (x shr 2)
        x = x or (x shr 4)
        x = x or (x shr 8)
        x = x or (x shr 16)
        x = x or (x shr 32)
        return x - (x ushr 1)
    }

actual inline val Long.lowestOneBit: Long get() = and(-this)

actual val Long.leadingZeros: Int
    get() {
        if (this < 0L) return 0
        if (this == 0L) return 64
        // http://www.hackersdelight.org/hdcodetxt/nlz.c.txt
        val highBits = ushr(32).toInt()
        if (highBits != 0) return 1054 - ((highBits.toDouble() + 0.5).toRawBits() shr 52).toInt()
        val lowBits = toInt()
        if (lowBits < 0) return 32
        return 1086 - ((lowBits.toDouble() + 0.5).toRawBits() shr 52).toInt()
    }

actual val Long.trailingZeros: Int
    get() {
        if (this == 0L) return 64
        val lowBits = toInt()
        return if (lowBits == 0) 31 + ffs(ushr(32).toInt()) else ffs(lowBits) - 1
    }

actual fun Long.reverse(): Long {
    // http://www.hackersdelight.org/hdcodetxt/reverse.c.txt
    var x = this
    x = x and 0x5555555555555555L shl 1 or (x ushr 1 and 0x5555555555555555L)
    x = x and 0x3333333333333333L shl 2 or (x ushr 2 and 0x3333333333333333L)
    x = x and 0x0F0F0F0F0F0F0F0FL shl 4 or (x ushr 4 and 0x0F0F0F0F0F0F0F0FL)
    x = x and 0x00FF00FF00FF00FFL shl 8 or (x ushr 8 and 0x00FF00FF00FF00FFL)
    return x shl 48 or (x and 0xFFFF0000L shl 16) or (x ushr 16 and 0xFFFF0000L) or (x ushr 48)
}

actual fun Long.reverseBytes(): Long {
    // http://www.hackersdelight.org/hdcodetxt/reverse.c.txt
    var x = this
    x = x and 0x00FF00FF00FF00FFL shl 8 or (x ushr 8 and 0x00FF00FF00FF00FFL)
    return x shl 48 or (x and 0xFFFF0000L shl 16) or (x ushr 16 and 0xFFFF0000L) or (x ushr 48)
}

actual inline infix fun Long.rol(bitCount: Int): Long = shl(bitCount) or ushr(-bitCount)
actual inline infix fun Long.ror(bitCount: Int): Long = ushr(bitCount) or shl(-bitCount)
