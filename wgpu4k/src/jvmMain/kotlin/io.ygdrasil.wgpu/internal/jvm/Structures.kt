package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.PointerType
import com.sun.jna.Structure
import com.sun.jna.ptr.PointerByReference
import io.ygdrasil.wgpu.toNativeLong

class WGPUAdapterImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUBindGroupImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUBufferImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUCommandBufferImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUCommandEncoderImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUComputePassEncoderImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUComputePipelineImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUDeviceImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUInstanceImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUPipelineLayoutImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUQuerySetImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUQueueImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPURenderBundleImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPURenderBundleEncoderImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPURenderPassEncoderImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPURenderPipelineImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUShaderModuleImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUSurfaceImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUTextureImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

class WGPUTextureViewImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}

@Structure.FieldOrder("next", "sType")
open class WGPUChainedStruct : Structure {
	/**
	 * mapped from (Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var next: Pointer? = null

	/**
	 * mapped from WGPUSType
	 */
	@JvmField
	var sType: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUChainedStruct(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUChainedStruct(pointer), Structure.ByValue
}

@Structure.FieldOrder("next", "sType")
open class WGPUChainedStructOut : Structure {
	/**
	 * mapped from (Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	var next: Pointer? = null

	/**
	 * mapped from WGPUSType
	 */
	@JvmField
	var sType: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUChainedStructOut(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUChainedStructOut(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "vendorID", "vendorName", "architecture", "deviceID", "name",
	"driverDescription", "adapterType", "backendType"
)
open class WGPUAdapterProperties : Structure {
	/**
	 * mapped from (typedef Optional[WGPUChainedStructOut] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var vendorID: Int = 0

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var vendorName: String? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var architecture: String? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var deviceID: Int = 0

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var name: String? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var driverDescription: String? = null

	/**
	 * mapped from WGPUAdapterType
	 */
	@JvmField
	var adapterType: Int = 0

	/**
	 * mapped from WGPUBackendType
	 */
	@JvmField
	var backendType: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUAdapterProperties(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUAdapterProperties(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "type", "hasDynamicOffset", "minBindingSize")
open class WGPUBufferBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUBufferBindingType
	 */
	@JvmField
	var type: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var hasDynamicOffset: WGPUBool = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var minBindingSize: Long = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUBufferBindingLayout(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUBufferBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder("r", "g", "b", "a")
open class WGPUColor : Structure {
	/**
	 * mapped from double
	 */
	@JvmField
	var r: Double = 0.0

	/**
	 * mapped from double
	 */
	@JvmField
	var g: Double = 0.0

	/**
	 * mapped from double
	 */
	@JvmField
	var b: Double = 0.0

	/**
	 * mapped from double
	 */
	@JvmField
	var a: Double = 0.0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUColor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUColor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUCommandBufferDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUCommandBufferDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUCommandBufferDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUCommandEncoderDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUCommandEncoderDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUCommandEncoderDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "message", "type", "lineNum", "linePos", "offset", "length",
	"utf16LinePos", "utf16Offset", "utf16Length"
)
open class WGPUCompilationMessage : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var message: String? = null

	/**
	 * mapped from WGPUCompilationMessageType
	 */
	@JvmField
	var type: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var lineNum: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var linePos: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var offset: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var length: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var utf16LinePos: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var utf16Offset: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var utf16Length: Long = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUCompilationMessage(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUCompilationMessage(pointer), Structure.ByValue
}

@Structure.FieldOrder("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")
open class WGPUComputePassTimestampWrites : Structure {
	/**
	 * mapped from WGPUQuerySet
	 */
	@JvmField
	var querySet: WGPUQuerySet = WGPUQuerySetImpl()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var beginningOfPassWriteIndex: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var endOfPassWriteIndex: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUComputePassTimestampWrites(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUComputePassTimestampWrites(pointer), Structure.ByValue
}

@Structure.FieldOrder("width", "height", "depthOrArrayLayers")
open class WGPUExtent3D : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var width: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var height: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var depthOrArrayLayers: Int? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUExtent3D(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUExtent3D(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"maxTextureDimension1D", "maxTextureDimension2D", "maxTextureDimension3D",
	"maxTextureArrayLayers", "maxBindGroups", "maxBindGroupsPlusVertexBuffers",
	"maxBindingsPerBindGroup", "maxDynamicUniformBuffersPerPipelineLayout",
	"maxDynamicStorageBuffersPerPipelineLayout", "maxSampledTexturesPerShaderStage",
	"maxSamplersPerShaderStage", "maxStorageBuffersPerShaderStage",
	"maxStorageTexturesPerShaderStage", "maxUniformBuffersPerShaderStage",
	"maxUniformBufferBindingSize", "maxStorageBufferBindingSize", "minUniformBufferOffsetAlignment",
	"minStorageBufferOffsetAlignment", "maxVertexBuffers", "maxBufferSize", "maxVertexAttributes",
	"maxVertexBufferArrayStride", "maxInterStageShaderComponents", "maxInterStageShaderVariables",
	"maxColorAttachments", "maxColorAttachmentBytesPerSample", "maxComputeWorkgroupStorageSize",
	"maxComputeInvocationsPerWorkgroup", "maxComputeWorkgroupSizeX", "maxComputeWorkgroupSizeY",
	"maxComputeWorkgroupSizeZ", "maxComputeWorkgroupsPerDimension"
)
open class WGPULimits : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxTextureDimension1D: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxTextureDimension2D: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxTextureDimension3D: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxTextureArrayLayers: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxBindGroups: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxBindGroupsPlusVertexBuffers: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxBindingsPerBindGroup: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxDynamicUniformBuffersPerPipelineLayout: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxDynamicStorageBuffersPerPipelineLayout: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxSampledTexturesPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxSamplersPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxStorageBuffersPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxStorageTexturesPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxUniformBuffersPerShaderStage: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var maxUniformBufferBindingSize: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var maxStorageBufferBindingSize: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var minUniformBufferOffsetAlignment: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var minStorageBufferOffsetAlignment: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxVertexBuffers: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var maxBufferSize: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxVertexAttributes: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxVertexBufferArrayStride: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxInterStageShaderComponents: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxInterStageShaderVariables: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxColorAttachments: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxColorAttachmentBytesPerSample: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxComputeWorkgroupStorageSize: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxComputeInvocationsPerWorkgroup: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxComputeWorkgroupSizeX: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxComputeWorkgroupSizeY: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxComputeWorkgroupSizeZ: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var maxComputeWorkgroupsPerDimension: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPULimits(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPULimits(pointer), Structure.ByValue
}

@Structure.FieldOrder("x", "y", "z")
open class WGPUOrigin3D : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var x: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var y: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var z: Int? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUOrigin3D(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUOrigin3D(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "bindGroupLayoutCount", "bindGroupLayouts")
open class WGPUPipelineLayoutDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var bindGroupLayoutCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUBindGroupLayout] = (Declared())*)*
	 */
	@JvmField
	var bindGroupLayouts: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUPipelineLayoutDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUPipelineLayoutDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "unclippedDepth")
open class WGPUPrimitiveDepthClipControl : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var unclippedDepth: WGPUBool = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUPrimitiveDepthClipControl(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUPrimitiveDepthClipControl(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "type", "count")
open class WGPUQuerySetDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	/**
	 * mapped from WGPUQueryType
	 */
	@JvmField
	var type: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var count: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUQuerySetDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUQuerySetDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUQueueDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUQueueDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUQueueDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPURenderBundleDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderBundleDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderBundleDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "colorFormatCount", "colorFormats",
	"depthStencilFormat", "sampleCount", "depthReadOnly", "stencilReadOnly"
)
open class WGPURenderBundleEncoderDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var colorFormatCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	var colorFormats: Pointer? = null

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	var depthStencilFormat: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var sampleCount: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var depthReadOnly: WGPUBool = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var stencilReadOnly: WGPUBool = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderBundleEncoderDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderBundleEncoderDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")
open class WGPURenderPassTimestampWrites : Structure {
	/**
	 * mapped from WGPUQuerySet
	 */
	@JvmField
	var querySet: WGPUQuerySet = WGPUQuerySetImpl()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var beginningOfPassWriteIndex: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var endOfPassWriteIndex: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPassTimestampWrites(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPassTimestampWrites(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "compatibleSurface", "powerPreference", "backendType",
	"forceFallbackAdapter"
)
open class WGPURequestAdapterOptions : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUSurface
	 */
	@JvmField
	var compatibleSurface: WGPUSurface = WGPUSurfaceImpl()

	/**
	 * mapped from WGPUPowerPreference
	 */
	@JvmField
	var powerPreference: Int = 0

	/**
	 * mapped from WGPUBackendType
	 */
	@JvmField
	var backendType: Int? = null

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var forceFallbackAdapter: WGPUBool = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPURequestAdapterOptions(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPURequestAdapterOptions(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "type")
open class WGPUSamplerBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUSamplerBindingType
	 */
	@JvmField
	var type: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSamplerBindingLayout(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSamplerBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "entryPoint", "layout")
open class WGPUShaderModuleCompilationHint : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var entryPoint: String? = null

	/**
	 * mapped from WGPUPipelineLayout
	 */
	@JvmField
	var layout: WGPUPipelineLayout = WGPUPipelineLayoutImpl()

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleCompilationHint(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleCompilationHint(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "codeSize", "code")
open class WGPUShaderModuleSPIRVDescriptor : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var codeSize: Int = 0

	/**
	 * mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout = i4))*
	 */
	@JvmField
	var code: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleSPIRVDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleSPIRVDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "code")
open class WGPUShaderModuleWGSLDescriptor : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var code: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleWGSLDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleWGSLDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "access", "format", "viewDimension")
open class WGPUStorageTextureBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUStorageTextureAccess
	 */
	@JvmField
	var access: Int = 0

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	var format: Int = 0

	/**
	 * mapped from WGPUTextureViewDimension
	 */
	@JvmField
	var viewDimension: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUStorageTextureBindingLayout(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUStorageTextureBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "formatCount", "formats", "presentModeCount", "presentModes",
	"alphaModeCount", "alphaModes"
)
open class WGPUSurfaceCapabilities : Structure {
	/**
	 * mapped from (typedef Optional[WGPUChainedStructOut] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var formatCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	var formats: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var presentModeCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[WGPUPresentMode] = Declared(i4))*
	 */
	@JvmField
	var presentModes: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var alphaModeCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[WGPUCompositeAlphaMode] = Declared(i4))*
	 */
	@JvmField
	var alphaModes: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceCapabilities(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceCapabilities(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "device", "format", "usage", "viewFormatCount", "viewFormats",
	"alphaMode", "width", "height", "presentMode"
)
open class WGPUSurfaceConfiguration : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUDevice
	 */
	@JvmField
	var device: WGPUDevice = WGPUDeviceImpl()

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	var format: Int = 0

	/**
	 * mapped from WGPUTextureUsageFlags
	 */
	@JvmField
	var usage: WGPUTextureUsageFlags = 0

	/**
	 * mapped from size_t
	 */
	@JvmField
	var viewFormatCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	var viewFormats: Pointer? = null

	/**
	 * mapped from WGPUCompositeAlphaMode
	 */
	@JvmField
	var alphaMode: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var width: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var height: Int = 0

	/**
	 * mapped from WGPUPresentMode
	 */
	@JvmField
	var presentMode: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfiguration(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfiguration(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUWindowSurfaceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: WGPUSurfaceDescriptorFromWindowsHWND.ByReference =
		WGPUSurfaceDescriptorFromWindowsHWND.ByReference()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUXlibWindowSurfaceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: WGPUSurfaceDescriptorFromXlibWindow.ByReference =
		WGPUSurfaceDescriptorFromXlibWindow.ByReference()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUDarwinSurfaceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: WGPUSurfaceDescriptorFromMetalLayer.ByReference =
		WGPUSurfaceDescriptorFromMetalLayer.ByReference()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
open class WGPUSurfaceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "window")
open class WGPUSurfaceDescriptorFromAndroidNativeWindow : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var window: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromAndroidNativeWindow(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromAndroidNativeWindow(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "selector")
open class WGPUSurfaceDescriptorFromCanvasHTMLSelector : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var selector: String? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromCanvasHTMLSelector(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromCanvasHTMLSelector(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "layer")
open class WGPUSurfaceDescriptorFromMetalLayer : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var layer: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromMetalLayer(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromMetalLayer(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "display", "surface")
open class WGPUSurfaceDescriptorFromWaylandSurface : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var display: Pointer? = null

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var surface: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWaylandSurface(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWaylandSurface(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "hinstance", "hwnd")
open class WGPUSurfaceDescriptorFromWindowsHWND : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var hinstance: Pointer? = null

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var hwnd: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWindowsHWND(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWindowsHWND(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "connection", "window")
open class WGPUSurfaceDescriptorFromXcbWindow : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var connection: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var window: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXcbWindow(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXcbWindow(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "display", "window")
open class WGPUSurfaceDescriptorFromXlibWindow : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	var display: Pointer? = null

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var window: Long = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXlibWindow(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXlibWindow(pointer), Structure.ByValue
}

@Structure.FieldOrder("texture", "suboptimal", "status")
open class WGPUSurfaceTexture : Structure {
	/**
	 * mapped from WGPUTexture
	 */
	@JvmField
	var texture: WGPUTexture = WGPUTextureImpl()

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var suboptimal: WGPUBool = 0

	/**
	 * mapped from WGPUSurfaceGetCurrentTextureStatus
	 */
	@JvmField
	var status: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceTexture(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceTexture(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "sampleType", "viewDimension", "multisampled")
open class WGPUTextureBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureSampleType
	 */
	@JvmField
	var sampleType: Int = 0

	/**
	 * mapped from WGPUTextureViewDimension
	 */
	@JvmField
	var viewDimension: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var multisampled: WGPUBool = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUTextureBindingLayout(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUTextureBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "offset", "bytesPerRow", "rowsPerImage")
open class WGPUTextureDataLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	var offset: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var bytesPerRow: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var rowsPerImage: Int = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUTextureDataLayout(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUTextureDataLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "binding", "visibility", "buffer", "sampler", "texture",
	"storageTexture"
)
open class WGPUBindGroupLayoutEntry : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var binding: Int = 0

	/**
	 * mapped from WGPUShaderStageFlags
	 */
	@JvmField
	var visibility: WGPUShaderStageFlags = 0

	/**
	 * mapped from WGPUBufferBindingLayout
	 */
	@JvmField
	var buffer: WGPUBufferBindingLayout = WGPUBufferBindingLayout()

	/**
	 * mapped from WGPUSamplerBindingLayout
	 */
	@JvmField
	var sampler: WGPUSamplerBindingLayout = WGPUSamplerBindingLayout()

	/**
	 * mapped from WGPUTextureBindingLayout
	 */
	@JvmField
	var texture: WGPUTextureBindingLayout = WGPUTextureBindingLayout()

	/**
	 * mapped from WGPUStorageTextureBindingLayout
	 */
	@JvmField
	var storageTexture: WGPUStorageTextureBindingLayout = WGPUStorageTextureBindingLayout()

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutEntry(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutEntry(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "messageCount", "messages")
open class WGPUCompilationInfo : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var messageCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUCompilationMessage] =
	 * Declared([a8(nextInChain):[*:b1]a8(message):[*:b1]i4(type)x4j8(lineNum)j8(linePos)j8(offset)j8(length)j8(utf16LinePos)j8(utf16Offset)j8(utf16Length)](WGPUCompilationMessage)))*
	 */
	@JvmField
	var messages: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUCompilationInfo(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUCompilationInfo(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "timestampWrites")
open class WGPUComputePassDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	/**
	 * mapped from (typedef Optional[const WGPUComputePassTimestampWrites] =
	 * Declared([a8(querySet):[*:b1]i4(beginningOfPassWriteIndex)i4(endOfPassWriteIndex)](WGPUComputePassTimestampWrites)))*
	 */
	@JvmField
	var timestampWrites: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUComputePassDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUComputePassDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "layout", "buffer")
open class WGPUImageCopyBuffer : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureDataLayout
	 */
	@JvmField
	var layout: WGPUTextureDataLayout = WGPUTextureDataLayout()

	/**
	 * mapped from WGPUBuffer
	 */
	@JvmField
	var buffer: WGPUBuffer = WGPUBufferImpl()

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUImageCopyBuffer(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUImageCopyBuffer(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "texture", "mipLevel", "origin", "aspect")
open class WGPUImageCopyTexture : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTexture
	 */
	@JvmField
	var texture: WGPUTexture? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	var mipLevel: Int? = null

	/**
	 * mapped from WGPUOrigin3D
	 */
	@JvmField
	var origin: WGPUOrigin3D = WGPUOrigin3D()

	/**
	 * mapped from WGPUTextureAspect
	 */
	@JvmField
	var aspect: Int? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUImageCopyTexture(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUImageCopyTexture(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "module", "entryPoint", "constantCount", "constants")
open class WGPUProgrammableStageDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUShaderModule
	 */
	@JvmField
	var module: WGPUShaderModule = WGPUShaderModuleImpl()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var entryPoint: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var constantCount: NativeLong = NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUConstantEntry] =
	 * Declared([a8(nextInChain):[*:b1]a8(key):[*:b1]d8(value)](WGPUConstantEntry)))*
	 */
	@JvmField
	var constants: Pointer? = null

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUProgrammableStageDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUProgrammableStageDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "hintCount", "hintsPtr")
open class WGPUShaderModuleDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	var nextInChain: WGPUShaderModuleWGSLDescriptor.ByReference? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	var hintCount: NativeLong? = null

	/**
	 * mapped from (typedef Optional[const WGPUShaderModuleCompilationHint] =
	 * Declared([a8(nextInChain):[*:b1]a8(entryPoint):[*:b1]a8(layout):[*:b1]](WGPUShaderModuleCompilationHint)))*
	 */
	@JvmField
	var hintsPtr: Pointer? = null

	var hints: Array<WGPUShaderModuleCompilationHint.ByReference>? = null

	override fun write() {
		hints?.forEach { it.write() }
		hintsPtr = hints?.firstOrNull()?.pointer
		hintCount = hints?.size?.toNativeLong()
		super.write()
	}

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleDescriptor(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleDescriptor(pointer), Structure.ByValue
}
