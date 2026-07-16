package io.ygdrasil.webgpu.mapper

import io.ygdrasil.kffi.CallbackPolicy
import io.ygdrasil.kffi.CallbackRegistration
import io.ygdrasil.kffi.MemoryAllocator
import io.ygdrasil.kffi.memoryScope
import io.ygdrasil.webgpu.GPUDeviceDescriptor
import io.ygdrasil.webgpu.GPUQueueDescriptor
import io.ygdrasil.webgpu.GPUSupportedLimits
import io.ygdrasil.webgpu.GPUUncapturedErrorCallback
import io.ygdrasil.webgpu.internal.OwnedRepeatingCallback
import io.ygdrasil.wgpu.WGPUDeviceDescriptor
import io.ygdrasil.wgpu.WGPUErrorType
import io.ygdrasil.wgpu.WGPULimits
import io.ygdrasil.wgpu.WGPUQueueDescriptor
import io.ygdrasil.wgpu.WGPUStringView
import io.ygdrasil.wgpu.WGPUUncapturedErrorCallback
import io.ygdrasil.wgpu.WGPUUncapturedErrorCallbackInfo
import io.ygdrasil.wgpu.allocate
import io.ygdrasil.wgpu.register

internal data class MappedDeviceDescriptor(
    val value: WGPUDeviceDescriptor,
    val uncapturedErrorRegistration: RegisteredUncapturedErrorCallback?,
)

internal class UncapturedErrorDispatch(
    userCallback: GPUUncapturedErrorCallback,
) : AutoCloseable {
    private val owner = OwnedRepeatingCallback(userCallback::onUncapturedError)
    private val callback = WGPUUncapturedErrorCallback { _, type, message, _ ->
        deliver(type, message.data?.toKString(message.length))
    }

    internal fun register(): CallbackRegistration<WGPUUncapturedErrorCallback> =
        WGPUUncapturedErrorCallback.register(
            policy = CallbackPolicy.REPEATING,
            callback = callback,
        ).also { check(owner.attach(it)) }

    internal fun dispatch(type: WGPUErrorType, message: String) = memoryScope { scope ->
        val nativeMessage = WGPUStringView.allocate(scope).also { scope.map(message, it) }
        callback.invoke(null, type, nativeMessage, null)
    }

    private fun deliver(type: WGPUErrorType, message: String?) {
        errorOf(type, message)?.let(owner::dispatch)
    }

    override fun close() = owner.close()
}

internal class RegisteredUncapturedErrorCallback(
    val registration: CallbackRegistration<WGPUUncapturedErrorCallback>,
    private val dispatcher: UncapturedErrorDispatch,
) : AutoCloseable by dispatcher {
    internal fun dispatch(type: WGPUErrorType, message: String) = dispatcher.dispatch(type, message)
}

internal fun registerUncapturedError(
    userCallback: GPUUncapturedErrorCallback,
): RegisteredUncapturedErrorCallback {
    val dispatcher = UncapturedErrorDispatch(userCallback)
    return RegisteredUncapturedErrorCallback(dispatcher.register(), dispatcher)
}

internal fun MemoryAllocator.mapDeviceDescriptor(input: GPUDeviceDescriptor): MappedDeviceDescriptor {
    val output = WGPUDeviceDescriptor.allocate(this)
    map(input.label, output.label)

    mapRequiredFeatures(input, output)
    input.requiredLimits?.let { output.requiredLimits = mapRequiredLimits(it) }

    map(input.defaultQueue, output.defaultQueue)

    val registration = input.onUncapturedError?.let(::registerUncapturedError)
    registration?.let {
        output.uncapturedErrorCallbackInfo = WGPUUncapturedErrorCallbackInfo.allocate(this, it.registration)
    }
    return MappedDeviceDescriptor(output, registration)
}

private fun MemoryAllocator.mapRequiredLimits(input: GPUSupportedLimits) =  WGPULimits.allocate(this).also { output ->
    output.maxTextureDimension1D = input.maxTextureDimension1D
    output.maxTextureDimension2D = input.maxTextureDimension2D
    output.maxTextureDimension3D = input.maxTextureDimension3D
    output.maxTextureArrayLayers = input.maxTextureArrayLayers
    output.maxBindGroups = input.maxBindGroups
    output.maxBindGroupsPlusVertexBuffers = input.maxBindGroupsPlusVertexBuffers
    output.maxBindingsPerBindGroup = input.maxBindingsPerBindGroup
    output.maxDynamicUniformBuffersPerPipelineLayout = input.maxDynamicUniformBuffersPerPipelineLayout
    output.maxDynamicStorageBuffersPerPipelineLayout = input.maxDynamicStorageBuffersPerPipelineLayout
    output.maxSampledTexturesPerShaderStage = input.maxSampledTexturesPerShaderStage
    output.maxSamplersPerShaderStage = input.maxSamplersPerShaderStage
    output.maxStorageBuffersPerShaderStage = input.maxStorageBuffersPerShaderStage
    output.maxStorageTexturesPerShaderStage = input.maxStorageTexturesPerShaderStage
    output.maxUniformBuffersPerShaderStage = input.maxUniformBuffersPerShaderStage
    output.maxUniformBufferBindingSize = input.maxUniformBufferBindingSize
    output.maxStorageBufferBindingSize = input.maxStorageBufferBindingSize
    output.minUniformBufferOffsetAlignment = input.minUniformBufferOffsetAlignment
    output.minStorageBufferOffsetAlignment = input.minStorageBufferOffsetAlignment
    output.maxVertexBuffers = input.maxVertexBuffers
    output.maxBufferSize = input.maxBufferSize
    output.maxVertexAttributes = input.maxVertexAttributes
    output.maxVertexBufferArrayStride = input.maxVertexBufferArrayStride
    output.maxInterStageShaderVariables = input.maxInterStageShaderVariables
    output.maxColorAttachments = input.maxColorAttachments
    output.maxColorAttachmentBytesPerSample = input.maxColorAttachmentBytesPerSample
    output.maxComputeWorkgroupStorageSize = input.maxComputeWorkgroupStorageSize
    output.maxComputeInvocationsPerWorkgroup = input.maxComputeInvocationsPerWorkgroup
    output.maxComputeWorkgroupSizeX = input.maxComputeWorkgroupSizeX
    output.maxComputeWorkgroupSizeY = input.maxComputeWorkgroupSizeY
    output.maxComputeWorkgroupSizeZ = input.maxComputeWorkgroupSizeZ
    output.maxComputeWorkgroupsPerDimension = input.maxComputeWorkgroupsPerDimension

}

private fun MemoryAllocator.mapRequiredFeatures(
    input: GPUDeviceDescriptor,
    output: WGPUDeviceDescriptor
) {
    if (input.requiredFeatures.isNotEmpty()) {
        output.requiredFeatureCount = input.requiredFeatures.size.toULong()
        output.requiredFeatures = allocateBuffer(output.requiredFeatureCount * Int.SIZE_BYTES.toULong())
            .also { it.writeUInts(input.requiredFeatures.map { it.value }.toUIntArray()) }
            .handler
    }
}

private fun MemoryAllocator.map(input: GPUQueueDescriptor, output: WGPUQueueDescriptor) {
    map(input.label, output.label)
}
