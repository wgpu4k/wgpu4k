package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.GPUIntegerCoordinates
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUOrigin3D
import java.lang.foreign.Arena

internal fun Arena.map(input: GPUIntegerCoordinates) = WGPUOrigin3D.allocate(this).also { output ->
    WGPUOrigin3D.x(output, input.first)
    WGPUOrigin3D.y(output, input.second)
    WGPUOrigin3D.z(output, 0)
}