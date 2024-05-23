package io.ygdrasil.wgpu.mapper

import io.ygdrasil.wgpu.ImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.WGPUImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.WGPUOrigin3D
import io.ygdrasil.wgpu.internal.jvm.WGPUTextureImpl
import io.ygdrasil.wgpu.internal.jvm.toPointer

fun ImageCopyTexture.convert(): WGPUImageCopyTexture = WGPUImageCopyTexture().also {

    it.texture = WGPUTextureImpl(texture.handler.toPointer())
    it.mipLevel = mipLevel
    it.origin = origin.let { (x, y) ->
        WGPUOrigin3D().also {
            it.x = x
            it.y = y
        }
    }
    it.aspect = aspect.value
}
