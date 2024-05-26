package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.Origin3D
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUOrigin3D
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment

internal fun Arena.map(input: Origin3D) = WGPUOrigin3D.allocate(this).also { output ->
    map(input, output)
}

internal fun Arena.map(input: Origin3D, output: MemorySegment) {
    WGPUOrigin3D.x(output, input.x)
    WGPUOrigin3D.y(output, input.y)
    WGPUOrigin3D.z(output, input.z)
}