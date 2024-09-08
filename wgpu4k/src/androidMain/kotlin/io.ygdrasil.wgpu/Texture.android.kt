package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.mapper.map
import java.lang.foreign.MemorySegment

actual class Texture(val handler: Long) : AutoCloseable {

    internal val mhandler = MemorySegment(Pointer(handler), 0L)

    actual val width: GPUIntegerCoordinateOut
        get() = JnaInterface.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = JnaInterface.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = JnaInterface.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = JnaInterface.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = JnaInterface.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = JnaInterface.wgpuTextureGetDimension(handler)
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = JnaInterface.wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = JnaInterface.wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView = scoped { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { JnaInterface.wgpuTextureCreateView(handler, it ?: 0L) }
            .let { TextureView(it) }
    }

    actual override fun close() {
        JnaInterface.wgpuTextureRelease(handler)
    }
}
