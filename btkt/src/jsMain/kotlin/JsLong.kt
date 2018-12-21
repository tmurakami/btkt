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
internal inline val Long.high_: Int
    get() = let { js("it").high_.unsafeCast<Int>() }

actual inline val Long.oneBits: Int get() = high_.oneBits + toInt().oneBits

actual val Long.highestOneBit: Long
    get() = when (val highBits = high_) {
        0 -> toInt().highestOneBit.toLong()
        else -> js("Kotlin").Long.fromBits(0, highBits.highestOneBit).unsafeCast<Long>()
    }

actual val Long.lowestOneBit: Long
    get() = when (val lowBits = toInt()) {
        0 -> js("Kotlin").Long.fromBits(0, high_.lowestOneBit).unsafeCast<Long>()
        else -> lowBits.lowestOneBit.toLong()
    }

actual val Long.leadingZeros: Int
    get() = when (val highBits = high_) {
        0 -> 32 + toInt().leadingZeros
        else -> highBits.leadingZeros
    }

actual val Long.trailingZeros: Int
    get() = when (val lowBits = toInt()) {
        0 -> 32 + high_.trailingZeros
        else -> lowBits.trailingZeros
    }

actual inline fun Long.reverse(): Long =
    js("Kotlin").Long.fromBits(high_.reverse(), toInt().reverse()).unsafeCast<Long>()

actual inline fun Long.reverseBytes(): Long =
    js("Kotlin").Long.fromBits(high_.reverseBytes(), toInt().reverseBytes()).unsafeCast<Long>()

actual infix fun Long.rol(bitCount: Int): Long {
    val highBits = high_
    val lowBits = toInt()
    val a = highBits shl bitCount or (lowBits ushr -bitCount)
    val b = lowBits shl bitCount or (highBits ushr -bitCount)
    val v = if (bitCount < 0) js("Kotlin").Long.fromBits(a, b) else js("Kotlin").Long.fromBits(b, a)
    return v.unsafeCast<Long>()
}

actual infix fun Long.ror(bitCount: Int): Long {
    val highBits = high_
    val lowBits = toInt()
    val a = highBits ushr bitCount or (lowBits shl -bitCount)
    val b = lowBits ushr bitCount or (highBits shl -bitCount)
    val v = if (bitCount < 0) js("Kotlin").Long.fromBits(a, b) else js("Kotlin").Long.fromBits(b, a)
    return v.unsafeCast<Long>()
}
