package io.ygdrasil.webgpu

/**
 * A GPUSampler encodes transformations and filtering information that can be used in a shader to interpret texture resource data.
 *
 * @see <a href="https://www.w3.org/TR/webgpu/#gpusampler">W3C specifications</a>
 */
@WGPULowLevel
expect class Sampler : GPUSampler {

    override var label: String

    override fun close()
}
