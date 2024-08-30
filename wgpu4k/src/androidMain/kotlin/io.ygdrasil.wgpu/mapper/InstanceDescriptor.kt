package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.WGPUInstanceBackend
import io.ygdrasil.wgpu.internal.jna.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jna.WGPUInstanceDescriptor
import io.ygdrasil.wgpu.internal.jna.WGPUInstanceExtras
import io.ygdrasil.wgpu.internal.jna.wgpu_h.WGPUSType_InstanceExtras
import io.ygdrasil.wgpu.internal.toAddress
import java.lang.foreign.SegmentAllocator

internal fun SegmentAllocator.map(backend: WGPUInstanceBackend) = WGPUInstanceDescriptor.allocate(this).also { output ->
    WGPUInstanceExtras.allocate(this).also { nextInChain ->
        WGPUInstanceExtras.backends(nextInChain, backend.value)
        WGPUInstanceExtras.chain(nextInChain).also { chain ->
            WGPUChainedStruct.sType(nextInChain, WGPUSType_InstanceExtras())
        }
        WGPUInstanceDescriptor.nextInChain(output, nextInChain)
    }
}.pointer.toAddress()