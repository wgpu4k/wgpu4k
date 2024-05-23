package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.Callback
import com.sun.jna.NativeLong
import com.sun.jna.Pointer


public typealias WGPUBool = Int

public typealias WGPUAdapter = WGPUAdapterImpl

public typealias WGPUBindGroupLayout = WGPUBindGroupLayoutImpl

public typealias WGPUBuffer = WGPUBufferImpl

public typealias WGPUCommandEncoder = WGPUCommandEncoderImpl

public typealias WGPUComputePassEncoder = WGPUComputePassEncoderImpl

public typealias WGPUDevice = WGPUDeviceImpl

public typealias WGPUPipelineLayout = WGPUPipelineLayoutImpl

public typealias WGPUQuerySet = WGPUQuerySetImpl

public typealias WGPUQueue = WGPUQueueImpl

public typealias WGPURenderBundleEncoder = WGPURenderBundleEncoderImpl

public typealias WGPURenderPassEncoder = WGPURenderPassEncoderImpl

public typealias WGPURenderPipeline = WGPURenderPipelineImpl

public typealias WGPUSampler = WGPUSamplerImpl

public typealias WGPUShaderModule = WGPUShaderModuleImpl

public typealias WGPUSurface = WGPUSurfaceImpl

public typealias WGPUTexture = WGPUTextureImpl

public typealias WGPUTextureView = WGPUTextureViewImpl

public typealias WGPUBufferUsageFlags = Int

public typealias WGPUColorWriteMaskFlags = Int

public typealias WGPUShaderStageFlags = Int

public typealias WGPUTextureUsageFlags = Int

public interface WGPUDeviceLostCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Byte,
		param3: Unit,
	)
}

public typealias WGPUInstanceBackendFlags = Int

public typealias WGPUInstanceFlags = Int

public typealias WGPUSubmissionIndex = NativeLong

public interface WGPULogCallback : Callback {
	public operator fun invoke(
		level: Int,
		message: String,
		param3: Pointer?,
	)
}
