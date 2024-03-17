package io.ygdrasil.wgpu.mapper

import dev.krud.shapeshift.transformer.base.MappingTransformer
import io.ygdrasil.wgpu.ImageCopyTextureTagged
import io.ygdrasil.wgpu.internal.jvm.WGPUImageCopyTexture
import io.ygdrasil.wgpu.internal.jvm.WGPUOrigin3D
import io.ygdrasil.wgpu.mapper

internal val imageCopyTextureTaggedMapper = mapper<ImageCopyTextureTagged, WGPUImageCopyTexture> {
    ImageCopyTextureTagged::texture mappedTo WGPUImageCopyTexture::texture withTransformer MappingTransformer {
        it.originalValue?.handler
    }
    ImageCopyTextureTagged::origin mappedTo WGPUImageCopyTexture::origin withTransformer MappingTransformer {
        it.originalValue?.let { (x, y, z) ->
            WGPUOrigin3D().also {
                it.x = x
                it.y = y
                it.z = z
            }
        }
    }
}
