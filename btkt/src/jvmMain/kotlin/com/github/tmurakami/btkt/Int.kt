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

import java.lang.Integer.bitCount
import java.lang.Integer.highestOneBit
import java.lang.Integer.numberOfLeadingZeros
import java.lang.Integer.numberOfTrailingZeros
import java.lang.Integer.reverse
import java.lang.Integer.reverseBytes

actual inline val Int.oneBits: Int get() = bitCount(this)
actual inline val Int.highestOneBit: Int get() = highestOneBit(this)
actual inline val Int.lowestOneBit: Int get() = and(-this)
actual inline val Int.leadingZeros: Int get() = numberOfLeadingZeros(this)
actual inline val Int.trailingZeros: Int get() = numberOfTrailingZeros(this)

actual inline fun Int.reverse(): Int = reverse(this)
actual inline fun Int.reverseBytes(): Int = reverseBytes(this)
actual inline infix fun Int.rol(bitCount: Int): Int = shl(bitCount) or ushr(-bitCount)
actual inline infix fun Int.ror(bitCount: Int): Int = ushr(bitCount) or shl(-bitCount)
