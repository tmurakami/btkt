# BT.kt

![Kotlin](https://img.shields.io/badge/Kotlin-1.3.41%2B-blue.svg)
[![Build Status](https://travis-ci.org/tmurakami/btkt.svg?branch=master)](https://travis-ci.org/tmurakami/btkt/)
[![Download](https://api.bintray.com/packages/tmurakami/maven/btkt/images/download.svg)](https://bintray.com/tmurakami/maven/btkt/_latestVersion)

A Kotlin library that provides extensions for bit-twiddling.

```kotlin
val x = 0b00010001_00110011_01010101_00000000

// Count 1-bits
assertEquals(10, x.oneBits)

// Isolate the leftmost 1 bit
assertEquals(0b00010000_00000000_00000000_00000000, x.highestOneBit)

// Isolate the rightmost 1 bit
assertEquals(0b00000000_00000000_00000001_00000000, x.lowestOneBit)

// Count leading 0's
assertEquals(3, x.leadingZeros)

// Count trailing 0's
assertEquals(8, x.trailingZeros)

// Reverse bits
assertEquals(0b00000000_10101010_11001100_10001000, x.reverse())

// Reverse bytes
assertEquals(0b00000000_01010101_00110011_00010001, x.reverseBytes())

// Rotate left 8 bits
assertEquals(0b00110011_01010101_00000000_00010001, x rol 8)

// Rotate right 8 bits
assertEquals(0b00000000_00010001_00110011_01010101, x ror 8)
```

This library is based on material from
[Hacker's Delight](http://www.hackersdelight.org/).

## Installation

```groovy
apply plugin: 'org.jetbrains.kotlin.multiplatform'

repositories {
    jcenter()
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation 'com.github.tmurakami.btkt:btkt:1.0.0' 
            }
        }
    }
}
```

Note that you need to add `enableFeaturePreview('GRADLE_METADATA')` into
your `settings.gradle`. Please refer to
<https://kotlinlang.org/docs/reference/building-mpp-with-gradle.html#experimental-metadata-publishing-mode>
for more information.

## License

```
Copyright 2018 Tsuyoshi Murakami

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
