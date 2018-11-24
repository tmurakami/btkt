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

@file:Suppress("NOTHING_TO_INLINE", "EXPERIMENTAL_API_USAGE")

package com.github.tmurakami.btkt

/**
 * Returns the number of 1-bits.
 */
inline val UInt.oneBits: Int get() = toInt().oneBits

/**
 * Returns the leftmost 1-bit, or 0 if none.
 */
inline val UInt.lowestOneBit: UInt get() = toInt().lowestOneBit.toUInt()

/**
 * Returns the rightmost 1-bit, or 0 if none.
 */
inline val UInt.highestOneBit: UInt get() = toInt().highestOneBit.toUInt()

/**
 * Returns the number of 0-bits preceding the leftmost 1-bit, or 32 if all bits are 0.
 */
inline val UInt.leadingZeros: Int get() = toInt().leadingZeros

/**
 * Returns the number of 0-bits following the rightmost 1-bit, or 32 if all bits are 0.
 */
inline val UInt.trailingZeros: Int get() = toInt().trailingZeros

/**
 * Returns the value obtained by reversing the order of the bits.
 */
inline fun UInt.reverse(): UInt = toInt().reverse().toUInt()

/**
 * Returns the value obtained by reversing the order of the bytes.
 */
inline fun UInt.reverseBytes(): UInt = toInt().reverseBytes().toUInt()

/**
 * Returns the value obtained by rotating to the left by the specified [bitCount].
 */
inline infix fun UInt.rol(bitCount: Int): UInt = toInt().rol(bitCount).toUInt()

/**
 * Returns the value obtained by rotating to the right by the specified [bitCount].
 */
inline infix fun UInt.ror(bitCount: Int): UInt = toInt().ror(bitCount).toUInt()
