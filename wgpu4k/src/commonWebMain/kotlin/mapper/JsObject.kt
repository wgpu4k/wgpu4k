package io.ygdrasil.webgpu.mapper

import io.ygdrasil.webgpu.Device
import io.ygdrasil.webgpu.Texture
import io.ygdrasil.webgpu.internal.web.JsObject

expect internal fun map(value: JsObject): Texture
expect internal fun map(value: Device): JsObject