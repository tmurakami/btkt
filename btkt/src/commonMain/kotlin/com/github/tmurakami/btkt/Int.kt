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

/**
 * Returns the number of 1-bits.
 */
expect val Int.oneBits: Int

/**
 * Returns the leftmost 1-bit, or 0 if none.
 */
expect val Int.highestOneBit: Int

/**
 * Returns the rightmost 1-bit, or 0 if none.
 */
expect val Int.lowestOneBit: Int

/**
 * Returns the number of 0-bits preceding the leftmost 1-bit, or 32 if all bits are 0.
 */
expect val Int.leadingZeros: Int

/**
 * Returns the number of 0-bits following the rightmost 1-bit, or 32 if all bits are 0.
 */
expect val Int.trailingZeros: Int

/**
 * Returns the value obtained by reversing the order of the bits.
 */
expect fun Int.reverse(): Int

/**
 * Returns the value obtained by reversing the order of the bytes.
 */
expect fun Int.reverseBytes(): Int

/**
 * Returns the value obtained by rotating to the left by the specified [bitCount].
 */
expect infix fun Int.rol(bitCount: Int): Int

/**
 * Returns the value obtained by rotating to the right by the specified [bitCount].
 */
expect infix fun Int.ror(bitCount: Int): Int
