package io.ygdrasil.webgpu

import io.ygdrasil.webgpu.internal.js.GPU

external object navigator {
    val gpu: GPU?
}

