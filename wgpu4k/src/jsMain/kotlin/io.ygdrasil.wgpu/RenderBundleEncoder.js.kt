package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPURenderBundleEncoder

actual class RenderBundleEncoder(
    internal val handler: GPURenderBundleEncoder
)