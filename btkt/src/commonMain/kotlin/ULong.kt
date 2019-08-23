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
@UseExperimental(ExperimentalStdlibApi::class)
inline val ULong.oneBits: Int
    get() = countOneBits()

/**
 * Returns the leftmost 1-bit, or 0 if none.
 */
@UseExperimental(ExperimentalStdlibApi::class)
inline val ULong.lowestOneBit: ULong
    get() = takeLowestOneBit()

/**
 * Returns the rightmost 1-bit, or 0 if none.
 */
@UseExperimental(ExperimentalStdlibApi::class)
inline val ULong.highestOneBit: ULong
    get() = takeHighestOneBit()

/**
 * Returns the number of 0-bits preceding the leftmost 1-bit, or 64 if all bits are 0.
 */
@UseExperimental(ExperimentalStdlibApi::class)
inline val ULong.leadingZeros: Int
    get() = countLeadingZeroBits()

/**
 * Returns the number of 0-bits following the rightmost 1-bit, or 64 if all bits are 0.
 */
@UseExperimental(ExperimentalStdlibApi::class)
inline val ULong.trailingZeros: Int
    get() = countTrailingZeroBits()

/**
 * Returns the value obtained by reversing the order of the bits.
 */
inline fun ULong.reverse(): ULong = toLong().reverse().toULong()

/**
 * Returns the value obtained by reversing the order of the bytes.
 */
inline fun ULong.reverseBytes(): ULong = toLong().reverseBytes().toULong()

/**
 * Returns the value obtained by rotating to the left by the specified [bitCount].
 */
@UseExperimental(ExperimentalStdlibApi::class)
inline infix fun ULong.rol(bitCount: Int): ULong = rotateLeft(bitCount)

/**
 * Returns the value obtained by rotating to the right by the specified [bitCount].
 */
@UseExperimental(ExperimentalStdlibApi::class)
inline infix fun ULong.ror(bitCount: Int): ULong = rotateRight(bitCount)
