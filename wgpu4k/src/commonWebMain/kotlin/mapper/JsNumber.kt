package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.JsNumber


expect internal fun map(value: JsNumber): UInt = value.toUInt()

expect internal fun map(value: Int): JsNumber

expect internal fun map(value: UInt): JsNumber

expect internal fun map(value: ULong): JsNumber