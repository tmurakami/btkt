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

actual inline fun Long.reverse(): Long = Long(highBits.reverse(), toInt().reverse())
actual inline fun Long.reverseBytes(): Long = Long(highBits.reverseBytes(), toInt().reverseBytes())

@Suppress("FunctionName")
@PublishedApi
internal inline fun Long(lowBits: Int, highBits: Int): Long =
    js("Kotlin").Long.fromBits(lowBits, highBits).unsafeCast<Long>()
