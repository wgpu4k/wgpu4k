package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.Callback
import com.sun.jna.NativeLong
import com.sun.jna.Pointer

public typealias WGPUFlags = Int

public typealias `WGPUFlags$Array` = IntArray

public typealias WGPUBool = Int

public typealias `WGPUBool$Array` = IntArray

public typealias WGPUAdapter = WGPUAdapterImpl

public typealias WGPUBindGroup = WGPUBindGroupImpl

public typealias WGPUBindGroupLayout = WGPUBindGroupLayoutImpl

public typealias WGPUBuffer = WGPUBufferImpl

public typealias WGPUCommandBuffer = WGPUCommandBufferImpl

public typealias WGPUCommandEncoder = WGPUCommandEncoderImpl

public typealias WGPUComputePassEncoder = WGPUComputePassEncoderImpl

public typealias WGPUComputePipeline = WGPUComputePipelineImpl

public typealias WGPUDevice = WGPUDeviceImpl

public typealias WGPUInstance = WGPUInstanceImpl

public typealias WGPUPipelineLayout = WGPUPipelineLayoutImpl

public typealias WGPUQuerySet = WGPUQuerySetImpl

public typealias WGPUQueue = WGPUQueueImpl

public typealias WGPURenderBundle = WGPURenderBundleImpl

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

public typealias WGPUMapModeFlags = Int

public typealias WGPUShaderStageFlags = Int

public typealias WGPUTextureUsageFlags = Int

public interface WGPUBufferMapCallback : Callback {
	public operator fun invoke(param1: Unit, param2: Unit)
}

public interface WGPUCompilationInfoCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUCompilationInfo,
		param3: Unit,
	)
}

public interface WGPUCreateComputePipelineAsyncCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPUComputePipelineImpl,
		param3: Byte,
		param4: Unit,
	)
}

public interface WGPUCreateRenderPipelineAsyncCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: WGPURenderPipelineImpl,
		param3: Byte,
		param4: Unit,
	)
}

public interface WGPUDeviceLostCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Byte,
		param3: Unit,
	)
}

public interface WGPUErrorCallback : Callback {
	public operator fun invoke(
		param1: Unit,
		param2: Byte,
		param3: Unit,
	)
}

public interface WGPUProc : Callback {
	public operator fun invoke(param1: Unit)
}

public interface WGPUQueueWorkDoneCallback : Callback {
	public operator fun invoke(param1: Unit, param2: Unit)
}

public interface WGPURequestAdapterCallback : Callback {
	public operator fun invoke(
		param1: Int,
		param2: WGPUAdapterImpl,
		param3: String?,
		param4: Pointer?,
	)
}

public interface WGPURequestDeviceCallback : Callback {
	public operator fun invoke(
		param1: Int,
		param2: WGPUDeviceImpl,
		param3: String?,
		param4: Pointer?,
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
