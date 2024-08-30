package io.ygdrasil.wgpu.internal

import com.sun.jna.Native
import io.ygdrasil.wgpu.TextureViewDescriptor

object JnaInterface {

    init {
        Native.register(JnaInterface::class.java, "wgpu4kv2")
    }

    /*** Queue ***/
    external fun wgpuQueueSubmit(
        handler: Long,
        commandsBufferSize: Long,
        commandsBuffer: Long
    )

    /*** TextureView ***/
    external fun wgpuTextureViewRelease(handler: Long)

    /*** Texture ***/
    external fun wgpuTextureCreateView(
        handler: Long,
        textureViewDescriptor: Long
    ): Long

    external fun wgpuTextureRelease(handler: Long)

}