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

@PublishedApi
internal inline val Long.highBits: Int
    get() = let { js("it").high_.unsafeCast<Int>() }

actual inline val Long.oneBits: Int get() = highBits.oneBits + toInt().oneBits

actual val Long.highestOneBit: Long
    get() = when (val highBits = highBits) {
        0 -> toInt().highestOneBit.toLong()
        else -> Long(0, highBits.highestOneBit)
    }

actual val Long.lowestOneBit: Long
    get() = when (val lowBits = toInt()) {
        0 -> Long(0, highBits.lowestOneBit)
        else -> lowBits.lowestOneBit.toLong()
    }

actual val Long.leadingZeros: Int
    get() = when (val highBits = highBits) {
        0 -> 32 + toInt().leadingZeros
        else -> highBits.leadingZeros
    }

actual val Long.trailingZeros: Int
    get() = when (val lowBits = toInt()) {
        0 -> 32 + highBits.trailingZeros
        else -> lowBits.trailingZeros
    }

actual inline fun Long.reverse(): Long = Long(highBits.reverse(), toInt().reverse())
actual inline fun Long.reverseBytes(): Long = Long(highBits.reverseBytes(), toInt().reverseBytes())

actual infix fun Long.rol(bitCount: Int): Long {
    val n = bitCount and 63
    if (n == 0) return this
    val highBits = highBits
    val lowBits = toInt()
    if (n == 32) return Long(highBits, lowBits)
    val a = highBits shl n or (lowBits ushr -n)
    val b = lowBits shl n or (highBits ushr -n)
    return if (n > 32) Long(a, b) else Long(b, a)
}

actual infix fun Long.ror(bitCount: Int): Long {
    val n = bitCount and 63
    if (n == 0) return this
    val highBits = highBits
    val lowBits = toInt()
    if (n == 32) return Long(highBits, lowBits)
    val a = highBits ushr n or (lowBits shl -n)
    val b = lowBits ushr n or (highBits shl -n)
    return if (n > 32) Long(a, b) else Long(b, a)
}

@Suppress("FunctionName")
@PublishedApi
internal inline fun Long(lowBits: Int, highBits: Int): Long =
    js("Kotlin").Long.fromBits(lowBits, highBits).unsafeCast<Long>()
