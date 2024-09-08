package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.mapper.map
import io.ygdrasil.wgpu.nativeWgpu4k.NativeWgpu4k
import java.lang.foreign.MemorySegment

actual class Texture(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual val width: GPUIntegerCoordinateOut
        get() = NativeWgpu4k.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = NativeWgpu4k.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = NativeWgpu4k.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = NativeWgpu4k.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = NativeWgpu4k.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = NativeWgpu4k.wgpuTextureGetDimension(handler)
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = NativeWgpu4k.wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = NativeWgpu4k.wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView = scoped { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { NativeWgpu4k.wgpuTextureCreateView(handler, it ?: 0L) }
            .let { TextureView(it) }
    }

    actual override fun close() {
        NativeWgpu4k.wgpuTextureRelease(handler)
    }
}
