package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.internal.web.JsNumber

expect internal fun map(value: JsNumber): UInt

expect internal fun map(value: Int): JsNumber

expect internal fun map(value: UInt): JsNumber

expect internal fun map(value: ULong): JsNumber