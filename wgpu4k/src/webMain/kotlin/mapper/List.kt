@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.asJsNumber
import kotlin.collections.List
import kotlin.collections.mapIndexed
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.JsAny
import kotlin.js.JsArray
import kotlin.js.set

fun map(input: List<UInt>): JsArray<JsAny> = JsArray<JsAny>().also { array ->
    input.mapIndexed { index, value ->
        array[index] = value.asJsNumber()
    }
}