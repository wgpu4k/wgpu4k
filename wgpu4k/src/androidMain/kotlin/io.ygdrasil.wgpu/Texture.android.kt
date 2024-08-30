package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.JnaInterface
import io.ygdrasil.wgpu.internal.JniInterface
import io.ygdrasil.wgpu.internal.scoped
import io.ygdrasil.wgpu.mapper.map
import java.lang.Thread.sleep
import java.lang.foreign.MemorySegment

actual class Texture(val handler: Long) : AutoCloseable {

    actual val width: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetWidth(handler)
    actual val height: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetHeight(handler)
    actual val depthOrArrayLayers: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetDepthOrArrayLayers(handler)
    actual val mipLevelCount: GPUIntegerCoordinateOut
        get() = JniInterface.wgpuTextureGetMipLevelCount(handler)
    actual val sampleCount: GPUSize32Out
        get() = JniInterface.wgpuTextureGetSampleCount(handler)
    actual val dimension: TextureDimension
        get() = JniInterface.wgpuTextureGetDimension(handler)
            .let { TextureDimension.of(it) }
            ?: error("fail to get texture dimension")
    actual val format: TextureFormat
        get() = JniInterface.wgpuTextureGetFormat(handler)
            .let { TextureFormat.of(it) }
            ?: error("fail to get texture format")
    actual val usage: GPUFlagsConstant
        get() = JniInterface.wgpuTextureGetUsage(handler)

    actual fun createView(descriptor: TextureViewDescriptor?): TextureView  = scoped { arena ->
        descriptor?.let { arena.map(descriptor) }
            .let { JnaInterface.wgpuTextureCreateView(handler, it ?: 0L) }
            .let { TextureView(it) }
    }

    actual override fun close() {
        JnaInterface.wgpuTextureRelease(handler)
    }
}
