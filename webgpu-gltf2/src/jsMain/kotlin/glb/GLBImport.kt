@file:OptIn(ExperimentalJsExport::class)

package glb

@ExperimentalJsExport
class GLTFSampler(private val sampler: dynamic, private val device: dynamic) {

    var internalSampler = createSampler()

    private fun createSampler(): dynamic {
        val magFilter =
            if (sampler.magFilter == undefined || sampler.magFilter == GLTFTextureFilter.LINEAR) "linear" else "nearest"
        val minFilter =
            if (sampler.minFilter == undefined || sampler.minFilter == GLTFTextureFilter.LINEAR) "linear" else "nearest"

        val wrapS = when (sampler.wrapS) {
            GLTFTextureFilter.REPEAT -> "repeat"
            GLTFTextureFilter.CLAMP_TO_EDGE -> "clamp-to-edge"
            else -> "mirror-repeat"
        }

        val wrapT = when (sampler.wrapT) {
            GLTFTextureFilter.REPEAT -> "repeat"
            GLTFTextureFilter.CLAMP_TO_EDGE -> "clamp-to-edge"
            else -> "mirror-repeat"
        }

        return device.createSampler(
            object {
                val magFilter = magFilter
                val minFilter = minFilter
                val addressModeU = wrapS
                val addressModeV = wrapT
            }
        )
    }
}
