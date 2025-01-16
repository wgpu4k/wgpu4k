package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.internal.js.toJsNumber
import io.ygdrasil.webgpu.internal.web.JsNumber

internal actual fun map(value: JsNumber): UInt = value.toInt().toUInt()
internal actual fun map(value: Int): JsNumber = value.toJsNumber()
internal actual fun map(value: UInt): JsNumber = value.toLong().toJsNumber()
internal actual fun map(value: ULong): JsNumber = value.toLong().toJsNumber()