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

@file:Suppress("NOTHING_TO_INLINE", "UnsafeCastFromDynamic")

package com.github.tmurakami.btkt

actual inline val Long.oneBits: Int
    get() = let {
        val highBits: Int = js("it").high_
        highBits.oneBits + toInt().oneBits
    }

actual val Long.highestOneBit: Long
    get() = let {
        when (val highBits: Int = js("it").high_) {
            0 -> toInt().highestOneBit.toLong()
            else -> js("Kotlin").Long.fromBits(0, highBits.highestOneBit)
        }
    }

actual val Long.lowestOneBit: Long
    get() = when (val lowBits = toInt()) {
        0 -> let {
            val highBits: Int = js("it").high_
            js("Kotlin").Long.fromBits(0, highBits.lowestOneBit)
        }
        else -> lowBits.lowestOneBit.toLong()
    }

actual val Long.leadingZeros: Int
    get() = let {
        when (val highBits: Int = js("it").high_) {
            0 -> 32 + toInt().leadingZeros
            else -> highBits.leadingZeros
        }
    }

actual val Long.trailingZeros: Int
    get() = when (val lowBits = toInt()) {
        0 -> let {
            val highBits: Int = js("it").high_
            32 + highBits.trailingZeros
        }
        else -> lowBits.trailingZeros
    }

actual inline fun Long.reverse(): Long = let {
    val highBits: Int = js("it").high_
    js("Kotlin").Long.fromBits(highBits.reverse(), toInt().reverse())
}

actual inline fun Long.reverseBytes(): Long = let {
    val highBits: Int = js("it").high_
    js("Kotlin").Long.fromBits(highBits.reverseBytes(), toInt().reverseBytes())
}

actual inline infix fun Long.rol(bitCount: Int): Long = ror(-bitCount)

actual infix fun Long.ror(bitCount: Int): Long = let {
    val highBits: Int = js("it").high_
    val lowBits = toInt()
    val a = highBits ushr bitCount or (lowBits shl -bitCount)
    val b = lowBits ushr bitCount or (highBits shl -bitCount)
    if (bitCount < 0) js("Kotlin").Long.fromBits(a, b) else js("Kotlin").Long.fromBits(b, a)
}
