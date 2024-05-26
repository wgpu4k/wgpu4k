package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.Callback


typealias WGPUBool = Int

typealias WGPUBuffer = WGPUBufferImpl

typealias WGPUDevice = WGPUDeviceImpl

typealias WGPUPipelineLayout = WGPUPipelineLayoutImpl

typealias WGPUQuerySet = WGPUQuerySetImpl

typealias WGPUShaderModule = WGPUShaderModuleImpl

typealias WGPUSurface = WGPUSurfaceImpl

typealias WGPUTexture = WGPUTextureImpl

typealias WGPUTextureView = WGPUTextureViewImpl

typealias WGPUShaderStageFlags = Int

typealias WGPUTextureUsageFlags = Int

interface WGPUDeviceLostCallback : Callback {
	operator fun invoke(
		param1: Unit,
		param2: Byte,
		param3: Unit,
	)
}

typealias WGPUInstanceBackendFlags = Int

typealias WGPUInstanceFlags = Int
