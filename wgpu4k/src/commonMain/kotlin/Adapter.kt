package io.ygdrasil.webgpu

/**
 * The GPUAdapter interface of the WebGPU API represents a GPU adapter.
 * From this you can request a Device, adapter info, features, and limits.
 */
@WGPULowLevelApi
expect class Adapter : GPUAdapter {

    override val features: GPUSupportedFeatures
    override val limits: GPUSupportedLimits
    override val info: GPUAdapterInfo

    override suspend fun requestDevice(descriptor: GPUDeviceDescriptor?): Result<GPUDevice>

    override fun close()
}
