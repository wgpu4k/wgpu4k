package io.ygdrasil.webgpu

import org.khronos.webgl.Float32Array

actual fun FloatArray.writeInto(target: ArrayBuffer) {
    target.unsafeCast<Float32Array>()
        .set(this.unsafeCast<Float32Array>(), 0)
}