package io.ygdrasil.wgpu

/**
 * The GPUAdapter interface of the WebGPU API represents a GPU adapter.
 * From this you can request a Device, adapter info, features, and limits.
 */
expect class Adapter: AutoCloseable {
	suspend fun requestDevice(descriptor: DeviceDescriptor = DeviceDescriptor()): Device?

	override fun close()
}


data class DeviceDescriptor(
	val label: String? = null,
	/**
	 * Specifies the features that are required by the device request. The request will fail if the adapter cannot provide these features. Exactly the specified set of features, and no more or less, will be allowed in validation of API calls on the resulting device.
	 */
	val requiredFeatures: Set<FeatureName> = setOf(),
	/**
	 * Specifies the limits that are required by the device request. The request will fail if the adapter cannot provide these limits. Each key must be the name of a member of supported limits. Exactly the specified limits, and no limit/better or worse, will be allowed in validation of API calls on the resulting device.
	 */
	val requiredLimits: Map<String, GPUSize64> = mapOf(),
	/**
	 * The descriptor for the default Queue.
	 */
	val defaultQueue: QueueDescriptor = QueueDescriptor()
)

data class QueueDescriptor(
	val label: String? = null
)