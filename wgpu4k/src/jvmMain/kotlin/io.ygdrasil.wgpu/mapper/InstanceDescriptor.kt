package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.WGPUInstanceBackend
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUInstanceDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUInstanceExtras
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h.WGPUSType_InstanceExtras
import java.lang.foreign.Arena

internal fun Arena.map(backend: WGPUInstanceBackend) = WGPUInstanceDescriptor.allocate(this).also { output ->
    WGPUInstanceExtras.allocate(this).also { nextInChain ->
        WGPUInstanceExtras.backends(nextInChain, backend.value)
        WGPUInstanceExtras.chain(nextInChain).also { chain ->
            WGPUChainedStruct.sType(nextInChain, WGPUSType_InstanceExtras())
        }
        WGPUInstanceDescriptor.nextInChain(output, nextInChain)
    }
}