package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.internal.web.JsNumber

actual internal fun map(value: JsNumber): UInt = value.unsafeCast<Int>().toUInt()
internal actual fun map(value: Int): JsNumber = value.unsafeCast<JsNumber>()