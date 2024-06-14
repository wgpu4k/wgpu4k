@file:OptIn(ExperimentalJsExport::class)

package glb

import io.ygdrasil.wgpu.AddressMode
import io.ygdrasil.wgpu.Device
import io.ygdrasil.wgpu.FilterMode
import io.ygdrasil.wgpu.SamplerDescriptor
import io.ygdrasil.wgpu.internal.js.GPUDevice
import io.ygdrasil.wgpu.internal.js.GPUSampler

@JsExport
class GLTFSampler(private val samplerNode: dynamic, _device: GPUDevice) {

    private val device = Device(_device)
    val sampler = createSampler()

    private fun createSampler(): GPUSampler {
        val magFilter = when (samplerNode.magFilter) {
            undefined, GLTFTextureFilter.LINEAR.value -> FilterMode.linear
            else -> FilterMode.nearest
        }
        val minFilter = when (samplerNode.minFilter) {
            undefined, GLTFTextureFilter.LINEAR.value -> FilterMode.linear
            else -> FilterMode.nearest
        }

        val wrapS = when (samplerNode.wrapS) {
            GLTFTextureFilter.REPEAT.value -> AddressMode.repeat
            GLTFTextureFilter.CLAMP_TO_EDGE.value -> AddressMode.clamptoedge
            else -> AddressMode.mirrorrepeat
        }

        val wrapT = when (samplerNode.wrapT) {
            GLTFTextureFilter.REPEAT.value -> AddressMode.repeat
            GLTFTextureFilter.CLAMP_TO_EDGE.value -> AddressMode.clamptoedge
            else -> AddressMode.mirrorrepeat
        }
        val descriptor = SamplerDescriptor(
            magFilter = magFilter,
            minFilter = minFilter,
            addressModeU = wrapS,
            addressModeV = wrapT,
        )
        println(descriptor)
        return device.createSampler(
            SamplerDescriptor(
                magFilter = magFilter,
                minFilter = minFilter,
                addressModeU = wrapS,
                addressModeV = wrapT,
            )
        ).handler
    }
}
