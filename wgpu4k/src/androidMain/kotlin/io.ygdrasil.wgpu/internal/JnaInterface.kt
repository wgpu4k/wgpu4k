package io.ygdrasil.wgpu.internal

import com.sun.jna.Native

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
}