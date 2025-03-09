package io.ygdrasil.webgpu

/**
 * The GPUAdapter interface of the WebGPU API represents a GPU adapter.
 * From this you can request a Device, adapter info, features, and limits.
 */
expect class Adapter : GPUAdapter {

    override val features: Set<GPUFeatureName>

    override val limits: GPUSupportedLimits

    suspend fun requestDevice(descriptor: DeviceDescriptor = DeviceDescriptor()): Device?

    override fun close()
}
