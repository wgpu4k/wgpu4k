package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.Device
import io.ygdrasil.webgpu.Texture
import io.ygdrasil.webgpu.internal.web.JsObject

internal actual fun map(value: JsObject): Texture = Texture(value.unsafeCast())
internal actual fun map(value: Device): JsObject = value.handler