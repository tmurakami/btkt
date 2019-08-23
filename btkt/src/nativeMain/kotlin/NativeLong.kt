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
