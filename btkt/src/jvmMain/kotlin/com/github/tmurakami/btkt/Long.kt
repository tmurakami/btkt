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

import java.lang.Long.bitCount
import java.lang.Long.highestOneBit
import java.lang.Long.lowestOneBit
import java.lang.Long.numberOfLeadingZeros
import java.lang.Long.numberOfTrailingZeros
import java.lang.Long.reverse
import java.lang.Long.reverseBytes
import java.lang.Long.rotateLeft
import java.lang.Long.rotateRight

actual inline val Long.oneBits: Int get() = bitCount(this)
actual inline val Long.highestOneBit: Long get() = highestOneBit(this)
actual inline val Long.lowestOneBit: Long get() = lowestOneBit(this)
actual inline val Long.leadingZeros: Int get() = numberOfLeadingZeros(this)
actual inline val Long.trailingZeros: Int get() = numberOfTrailingZeros(this)

actual inline fun Long.reverse(): Long = reverse(this)
actual inline fun Long.reverseBytes(): Long = reverseBytes(this)
actual inline infix fun Long.rol(bitCount: Int): Long = rotateLeft(this, bitCount)
actual inline infix fun Long.ror(bitCount: Int): Long = rotateRight(this, bitCount)
