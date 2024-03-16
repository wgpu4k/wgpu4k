package io.ygdrasil.wgpu.`internal`.jvm

import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.PointerType
import com.sun.jna.Structure
import com.sun.jna.ptr.PointerByReference

public class WGPUAdapterImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUBindGroupImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUBindGroupLayoutImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUBufferImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUCommandBufferImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUCommandEncoderImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUComputePassEncoderImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUComputePipelineImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUDeviceImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUInstanceImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUPipelineLayoutImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUQuerySetImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUQueueImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPURenderBundleImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPURenderBundleEncoderImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPURenderPassEncoderImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPURenderPipelineImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUSamplerImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUShaderModuleImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUSurfaceImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUTextureImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

public class WGPUTextureViewImpl : PointerType {
	public constructor() : super()

	public constructor(pointer: Pointer?) : super(pointer)

	public class ByReference : PointerByReference {
		public constructor() : super()

		public constructor(pointer: Pointer?) : super(pointer)
	}
}

@Structure.FieldOrder("next", "sType")
public open class WGPUChainedStruct : Structure {
	/**
	 * mapped from (Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var next: Pointer? = null

	/**
	 * mapped from WGPUSType
	 */
	@JvmField
	public var sType: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUChainedStruct(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUChainedStruct(pointer), Structure.ByValue
}

@Structure.FieldOrder("next", "sType")
public open class WGPUChainedStructOut : Structure {
	/**
	 * mapped from (Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	public var next: Pointer? = null

	/**
	 * mapped from WGPUSType
	 */
	@JvmField
	public var sType: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUChainedStructOut(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUChainedStructOut(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "vendorID", "vendorName", "architecture", "deviceID", "name",
	"driverDescription", "adapterType", "backendType"
)
public open class WGPUAdapterProperties : Structure {
	/**
	 * mapped from (typedef Optional[WGPUChainedStructOut] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var vendorID: Int = 0

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var vendorName: String? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var architecture: String? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var deviceID: Int = 0

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var name: String? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var driverDescription: String? = null

	/**
	 * mapped from WGPUAdapterType
	 */
	@JvmField
	public var adapterType: Int = 0

	/**
	 * mapped from WGPUBackendType
	 */
	@JvmField
	public var backendType: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUAdapterProperties(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUAdapterProperties(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "binding", "buffer", "offset", "size", "sampler",
	"textureView"
)
public open class WGPUBindGroupEntry : Structure {

	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var binding: Int? = null

	/**
	 * mapped from WGPUBuffer
	 */
	@JvmField
	public var buffer: WGPUBuffer? = null

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var offset: Long? = null

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var size: Long? = null

	/**
	 * mapped from WGPUSampler
	 */
	@JvmField
	public var sampler: WGPUSampler? = null

	/**
	 * mapped from WGPUTextureView
	 */
	@JvmField
	public var textureView: WGPUTextureView? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupEntry(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupEntry(pointer), Structure.ByValue
}

@Structure.FieldOrder("operation", "srcFactor", "dstFactor")
public open class WGPUBlendComponent : Structure {
	/**
	 * mapped from WGPUBlendOperation
	 */
	@JvmField
	public var operation: Int = 0

	/**
	 * mapped from WGPUBlendFactor
	 */
	@JvmField
	public var srcFactor: Int = 0

	/**
	 * mapped from WGPUBlendFactor
	 */
	@JvmField
	public var dstFactor: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBlendComponent(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBlendComponent(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "type", "hasDynamicOffset", "minBindingSize")
public open class WGPUBufferBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUBufferBindingType
	 */
	@JvmField
	public var type: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var hasDynamicOffset: WGPUBool = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var minBindingSize: Long = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBufferBindingLayout(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBufferBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "usage", "size", "mappedAtCreation")
public open class WGPUBufferDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUBufferUsageFlags
	 */
	@JvmField
	public var usage: WGPUBufferUsageFlags = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var size: Long = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var mappedAtCreation: WGPUBool? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBufferDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBufferDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("r", "g", "b", "a")
public open class WGPUColor : Structure {
	/**
	 * mapped from double
	 */
	@JvmField
	public var r: Double = 0.0

	/**
	 * mapped from double
	 */
	@JvmField
	public var g: Double = 0.0

	/**
	 * mapped from double
	 */
	@JvmField
	public var b: Double = 0.0

	/**
	 * mapped from double
	 */
	@JvmField
	public var a: Double = 0.0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUColor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUColor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
public open class WGPUCommandBufferDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUCommandBufferDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUCommandBufferDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
public open class WGPUCommandEncoderDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUCommandEncoderDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUCommandEncoderDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "message", "type", "lineNum", "linePos", "offset", "length",
	"utf16LinePos", "utf16Offset", "utf16Length"
)
public open class WGPUCompilationMessage : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var message: String? = null

	/**
	 * mapped from WGPUCompilationMessageType
	 */
	@JvmField
	public var type: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var lineNum: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var linePos: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var offset: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var length: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var utf16LinePos: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var utf16Offset: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var utf16Length: Long = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUCompilationMessage(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUCompilationMessage(pointer), Structure.ByValue
}

@Structure.FieldOrder("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")
public open class WGPUComputePassTimestampWrites : Structure {
	/**
	 * mapped from WGPUQuerySet
	 */
	@JvmField
	public var querySet: WGPUQuerySet = WGPUQuerySetImpl()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var beginningOfPassWriteIndex: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var endOfPassWriteIndex: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUComputePassTimestampWrites(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUComputePassTimestampWrites(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "key", "value")
public open class WGPUConstantEntry : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var key: String? = null

	/**
	 * mapped from double
	 */
	@JvmField
	public var `value`: Double = 0.0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUConstantEntry(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUConstantEntry(pointer), Structure.ByValue
}

@Structure.FieldOrder("width", "height", "depthOrArrayLayers")
public open class WGPUExtent3D : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var width: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var height: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var depthOrArrayLayers: Int? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUExtent3D(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUExtent3D(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain")
public open class WGPUInstanceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUInstanceDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUInstanceDescriptor(pointer), Structure.ByValue
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
public open class WGPULimits : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxTextureDimension1D: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxTextureDimension2D: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxTextureDimension3D: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxTextureArrayLayers: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxBindGroups: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxBindGroupsPlusVertexBuffers: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxBindingsPerBindGroup: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxDynamicUniformBuffersPerPipelineLayout: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxDynamicStorageBuffersPerPipelineLayout: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxSampledTexturesPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxSamplersPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxStorageBuffersPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxStorageTexturesPerShaderStage: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxUniformBuffersPerShaderStage: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var maxUniformBufferBindingSize: Long = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var maxStorageBufferBindingSize: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var minUniformBufferOffsetAlignment: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var minStorageBufferOffsetAlignment: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxVertexBuffers: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var maxBufferSize: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxVertexAttributes: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxVertexBufferArrayStride: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxInterStageShaderComponents: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxInterStageShaderVariables: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxColorAttachments: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxColorAttachmentBytesPerSample: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxComputeWorkgroupStorageSize: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxComputeInvocationsPerWorkgroup: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxComputeWorkgroupSizeX: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxComputeWorkgroupSizeY: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxComputeWorkgroupSizeZ: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxComputeWorkgroupsPerDimension: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPULimits(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPULimits(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "count", "mask", "alphaToCoverageEnabled")
public open class WGPUMultisampleState : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var count: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var mask: Int? = null

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var alphaToCoverageEnabled: WGPUBool? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUMultisampleState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUMultisampleState(pointer), Structure.ByValue
}

@Structure.FieldOrder("x", "y", "z")
public open class WGPUOrigin3D : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var x: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var y: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var z: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUOrigin3D(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUOrigin3D(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "bindGroupLayoutCount", "bindGroupLayouts")
public open class WGPUPipelineLayoutDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var bindGroupLayoutCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUBindGroupLayout] = (Declared())*)*
	 */
	@JvmField
	public var bindGroupLayouts: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUPipelineLayoutDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUPipelineLayoutDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "unclippedDepth")
public open class WGPUPrimitiveDepthClipControl : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var unclippedDepth: WGPUBool = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUPrimitiveDepthClipControl(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUPrimitiveDepthClipControl(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "topology", "stripIndexFormat", "frontFace", "cullMode")
public open class WGPUPrimitiveState : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUPrimitiveTopology
	 */
	@JvmField
	public var topology: Int? = null

	/**
	 * mapped from WGPUIndexFormat
	 */
	@JvmField
	public var stripIndexFormat: Int? = null

	/**
	 * mapped from WGPUFrontFace
	 */
	@JvmField
	public var frontFace: Int? = null

	/**
	 * mapped from WGPUCullMode
	 */
	@JvmField
	public var cullMode: Int? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUPrimitiveState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUPrimitiveState(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "type", "count")
public open class WGPUQuerySetDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUQueryType
	 */
	@JvmField
	public var type: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var count: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUQuerySetDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUQuerySetDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
public open class WGPUQueueDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUQueueDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUQueueDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
public open class WGPURenderBundleDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderBundleDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderBundleDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "colorFormatCount", "colorFormats",
	"depthStencilFormat", "sampleCount", "depthReadOnly", "stencilReadOnly"
)
public open class WGPURenderBundleEncoderDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var colorFormatCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	public var colorFormats: Pointer? = null

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var depthStencilFormat: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var sampleCount: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var depthReadOnly: WGPUBool = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var stencilReadOnly: WGPUBool = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderBundleEncoderDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderBundleEncoderDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"view", "depthLoadOp", "depthStoreOp", "depthClearValue", "depthReadOnly",
	"stencilLoadOp", "stencilStoreOp", "stencilClearValue", "stencilReadOnly"
)
public open class WGPURenderPassDepthStencilAttachment : Structure {
	/**
	 * mapped from WGPUTextureView
	 */
	@JvmField
	public var view: WGPUTextureView = WGPUTextureViewImpl()

	/**
	 * mapped from WGPULoadOp
	 */
	@JvmField
	public var depthLoadOp: Int = 0

	/**
	 * mapped from WGPUStoreOp
	 */
	@JvmField
	public var depthStoreOp: Int = 0

	/**
	 * mapped from float
	 */
	@JvmField
	public var depthClearValue: Float = 0.0f

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var depthReadOnly: WGPUBool = 0

	/**
	 * mapped from WGPULoadOp
	 */
	@JvmField
	public var stencilLoadOp: Int = 0

	/**
	 * mapped from WGPUStoreOp
	 */
	@JvmField
	public var stencilStoreOp: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var stencilClearValue: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var stencilReadOnly: WGPUBool = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPassDepthStencilAttachment(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPassDepthStencilAttachment(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "maxDrawCount")
public open class WGPURenderPassDescriptorMaxDrawCount : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var maxDrawCount: Long = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPassDescriptorMaxDrawCount(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPassDescriptorMaxDrawCount(pointer), Structure.ByValue
}

@Structure.FieldOrder("querySet", "beginningOfPassWriteIndex", "endOfPassWriteIndex")
public open class WGPURenderPassTimestampWrites : Structure {
	/**
	 * mapped from WGPUQuerySet
	 */
	@JvmField
	public var querySet: WGPUQuerySet = WGPUQuerySetImpl()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var beginningOfPassWriteIndex: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var endOfPassWriteIndex: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPassTimestampWrites(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPassTimestampWrites(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "compatibleSurface", "powerPreference", "backendType",
	"forceFallbackAdapter"
)
public open class WGPURequestAdapterOptions : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUSurface
	 */
	@JvmField
	public var compatibleSurface: WGPUSurface = WGPUSurfaceImpl()

	/**
	 * mapped from WGPUPowerPreference
	 */
	@JvmField
	public var powerPreference: Int = 0

	/**
	 * mapped from WGPUBackendType
	 */
	@JvmField
	public var backendType: Int? = null

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var forceFallbackAdapter: WGPUBool = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURequestAdapterOptions(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURequestAdapterOptions(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "type")
public open class WGPUSamplerBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUSamplerBindingType
	 */
	@JvmField
	public var type: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSamplerBindingLayout(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSamplerBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "addressModeU", "addressModeV", "addressModeW",
	"magFilter", "minFilter", "mipmapFilter", "lodMinClamp", "lodMaxClamp", "compare",
	"maxAnisotropy"
)
public open class WGPUSamplerDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUAddressMode
	 */
	@JvmField
	public var addressModeU: Int = 0

	/**
	 * mapped from WGPUAddressMode
	 */
	@JvmField
	public var addressModeV: Int = 0

	/**
	 * mapped from WGPUAddressMode
	 */
	@JvmField
	public var addressModeW: Int = 0

	/**
	 * mapped from WGPUFilterMode
	 */
	@JvmField
	public var magFilter: Int = 0

	/**
	 * mapped from WGPUFilterMode
	 */
	@JvmField
	public var minFilter: Int = 0

	/**
	 * mapped from WGPUMipmapFilterMode
	 */
	@JvmField
	public var mipmapFilter: Int = 0

	/**
	 * mapped from float
	 */
	@JvmField
	public var lodMinClamp: Float = 0.0f

	/**
	 * mapped from float
	 */
	@JvmField
	public var lodMaxClamp: Float = 0.0f

	/**
	 * mapped from WGPUCompareFunction
	 */
	@JvmField
	public var compare: Int = 0

	/**
	 * mapped from uint16_t
	 */
	@JvmField
	public var maxAnisotropy: Byte = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSamplerDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSamplerDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "entryPoint", "layout")
public open class WGPUShaderModuleCompilationHint : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var entryPoint: String? = null

	/**
	 * mapped from WGPUPipelineLayout
	 */
	@JvmField
	public var layout: WGPUPipelineLayout = WGPUPipelineLayoutImpl()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleCompilationHint(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleCompilationHint(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "codeSize", "code")
public open class WGPUShaderModuleSPIRVDescriptor : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var codeSize: Int = 0

	/**
	 * mapped from (typedef Optional[const uint32_t] = UNSIGNED = Int(layout = i4))*
	 */
	@JvmField
	public var code: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleSPIRVDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleSPIRVDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "code")
public open class WGPUShaderModuleWGSLDescriptor : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var code: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleWGSLDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleWGSLDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("compare", "failOp", "depthFailOp", "passOp")
public open class WGPUStencilFaceState : Structure {
	/**
	 * mapped from WGPUCompareFunction
	 */
	@JvmField
	public var compare: Int = 0

	/**
	 * mapped from WGPUStencilOperation
	 */
	@JvmField
	public var failOp: Int = 0

	/**
	 * mapped from WGPUStencilOperation
	 */
	@JvmField
	public var depthFailOp: Int = 0

	/**
	 * mapped from WGPUStencilOperation
	 */
	@JvmField
	public var passOp: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUStencilFaceState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUStencilFaceState(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "access", "format", "viewDimension")
public open class WGPUStorageTextureBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUStorageTextureAccess
	 */
	@JvmField
	public var access: Int = 0

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var format: Int = 0

	/**
	 * mapped from WGPUTextureViewDimension
	 */
	@JvmField
	public var viewDimension: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUStorageTextureBindingLayout(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUStorageTextureBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "formatCount", "formats", "presentModeCount", "presentModes",
	"alphaModeCount", "alphaModes"
)
public open class WGPUSurfaceCapabilities : Structure {
	/**
	 * mapped from (typedef Optional[WGPUChainedStructOut] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var formatCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	public var formats: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var presentModeCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[WGPUPresentMode] = Declared(i4))*
	 */
	@JvmField
	public var presentModes: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var alphaModeCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[WGPUCompositeAlphaMode] = Declared(i4))*
	 */
	@JvmField
	public var alphaModes: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceCapabilities(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceCapabilities(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "device", "format", "usage", "viewFormatCount", "viewFormats",
	"alphaMode", "width", "height", "presentMode"
)
public open class WGPUSurfaceConfiguration : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUDevice
	 */
	@JvmField
	public var device: WGPUDevice = WGPUDeviceImpl()

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var format: Int = 0

	/**
	 * mapped from WGPUTextureUsageFlags
	 */
	@JvmField
	public var usage: WGPUTextureUsageFlags = 0

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var viewFormatCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	public var viewFormats: Pointer? = null

	/**
	 * mapped from WGPUCompositeAlphaMode
	 */
	@JvmField
	public var alphaMode: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var width: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var height: Int = 0

	/**
	 * mapped from WGPUPresentMode
	 */
	@JvmField
	public var presentMode: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfiguration(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfiguration(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
public open class WGPUDarwinSurfaceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: WGPUSurfaceDescriptorFromMetalLayer.ByReference =
		WGPUSurfaceDescriptorFromMetalLayer.ByReference()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label")
public open class WGPUSurfaceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "window")
public open class WGPUSurfaceDescriptorFromAndroidNativeWindow : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var window: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromAndroidNativeWindow(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromAndroidNativeWindow(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "selector")
public open class WGPUSurfaceDescriptorFromCanvasHTMLSelector : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var selector: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromCanvasHTMLSelector(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromCanvasHTMLSelector(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "layer")
public open class WGPUSurfaceDescriptorFromMetalLayer : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var layer: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromMetalLayer(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromMetalLayer(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "display", "surface")
public open class WGPUSurfaceDescriptorFromWaylandSurface : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var display: Pointer? = null

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var surface: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWaylandSurface(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWaylandSurface(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "hinstance", "hwnd")
public open class WGPUSurfaceDescriptorFromWindowsHWND : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var hinstance: Pointer? = null

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var hwnd: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWindowsHWND(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromWindowsHWND(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "connection", "window")
public open class WGPUSurfaceDescriptorFromXcbWindow : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var connection: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var window: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXcbWindow(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXcbWindow(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "display", "window")
public open class WGPUSurfaceDescriptorFromXlibWindow : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var display: Pointer? = null

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var window: Long = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXlibWindow(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceDescriptorFromXlibWindow(pointer), Structure.ByValue
}

@Structure.FieldOrder("texture", "suboptimal", "status")
public open class WGPUSurfaceTexture : Structure {
	/**
	 * mapped from WGPUTexture
	 */
	@JvmField
	public var texture: WGPUTexture = WGPUTextureImpl()

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var suboptimal: WGPUBool = 0

	/**
	 * mapped from WGPUSurfaceGetCurrentTextureStatus
	 */
	@JvmField
	public var status: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceTexture(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceTexture(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "sampleType", "viewDimension", "multisampled")
public open class WGPUTextureBindingLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureSampleType
	 */
	@JvmField
	public var sampleType: Int = 0

	/**
	 * mapped from WGPUTextureViewDimension
	 */
	@JvmField
	public var viewDimension: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var multisampled: WGPUBool = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUTextureBindingLayout(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUTextureBindingLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "offset", "bytesPerRow", "rowsPerImage")
public open class WGPUTextureDataLayout : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var offset: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var bytesPerRow: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var rowsPerImage: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUTextureDataLayout(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUTextureDataLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "format", "dimension", "baseMipLevel",
	"mipLevelCount", "baseArrayLayer", "arrayLayerCount", "aspect"
)
public open class WGPUTextureViewDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var format: Int = 0

	/**
	 * mapped from WGPUTextureViewDimension
	 */
	@JvmField
	public var dimension: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var baseMipLevel: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var mipLevelCount: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var baseArrayLayer: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var arrayLayerCount: Int = 0

	/**
	 * mapped from WGPUTextureAspect
	 */
	@JvmField
	public var aspect: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUTextureViewDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUTextureViewDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("format", "offset", "shaderLocation")
public open class WGPUVertexAttribute : Structure {
	/**
	 * mapped from WGPUVertexFormat
	 */
	@JvmField
	public var format: Int = 0

	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var offset: Long = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var shaderLocation: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUVertexAttribute(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUVertexAttribute(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "layout", "entryCount", "entriesPtr")
public open class WGPUBindGroupDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUBindGroupLayout
	 */
	@JvmField
	public var layout: WGPUBindGroupLayout? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var entryCount: NativeLong? = null

	/**
	 * mapped from (typedef Optional[const WGPUBindGroupEntry] =
	 * Declared([a8(nextInChain):[*:b1]i4(binding)x4a8(buffer):[*:b1]j8(offset)j8(size)a8(sampler):[*:b1]a8(textureView):[*:b1]](WGPUBindGroupEntry)))*
	 */
	@JvmField
	public var entriesPtr: Pointer? = null

	public var entries: Array<WGPUBindGroupEntry.ByReference>? = null

	override fun write() {
		entries?.forEach { it.write() }
		entryCount = NativeLong(entries?.size?.toLong() ?: 0L)
		entriesPtr = entries?.getOrNull(0)?.pointer
		super.write()
	}

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "binding", "visibility", "buffer", "sampler", "texture",
	"storageTexture"
)
public open class WGPUBindGroupLayoutEntry : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var binding: Int = 0

	/**
	 * mapped from WGPUShaderStageFlags
	 */
	@JvmField
	public var visibility: WGPUShaderStageFlags = 0

	/**
	 * mapped from WGPUBufferBindingLayout
	 */
	@JvmField
	public var buffer: WGPUBufferBindingLayout = WGPUBufferBindingLayout()

	/**
	 * mapped from WGPUSamplerBindingLayout
	 */
	@JvmField
	public var sampler: WGPUSamplerBindingLayout = WGPUSamplerBindingLayout()

	/**
	 * mapped from WGPUTextureBindingLayout
	 */
	@JvmField
	public var texture: WGPUTextureBindingLayout = WGPUTextureBindingLayout()

	/**
	 * mapped from WGPUStorageTextureBindingLayout
	 */
	@JvmField
	public var storageTexture: WGPUStorageTextureBindingLayout = WGPUStorageTextureBindingLayout()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutEntry(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutEntry(pointer), Structure.ByValue
}

@Structure.FieldOrder("color", "alpha")
public open class WGPUBlendState : Structure {
	/**
	 * mapped from WGPUBlendComponent
	 */
	@JvmField
	public var color: WGPUBlendComponent = WGPUBlendComponent()

	/**
	 * mapped from WGPUBlendComponent
	 */
	@JvmField
	public var alpha: WGPUBlendComponent = WGPUBlendComponent()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBlendState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBlendState(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "messageCount", "messages")
public open class WGPUCompilationInfo : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var messageCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUCompilationMessage] =
	 * Declared([a8(nextInChain):[*:b1]a8(message):[*:b1]i4(type)x4j8(lineNum)j8(linePos)j8(offset)j8(length)j8(utf16LinePos)j8(utf16Offset)j8(utf16Length)](WGPUCompilationMessage)))*
	 */
	@JvmField
	public var messages: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUCompilationInfo(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUCompilationInfo(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "timestampWrites")
public open class WGPUComputePassDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from (typedef Optional[const WGPUComputePassTimestampWrites] =
	 * Declared([a8(querySet):[*:b1]i4(beginningOfPassWriteIndex)i4(endOfPassWriteIndex)](WGPUComputePassTimestampWrites)))*
	 */
	@JvmField
	public var timestampWrites: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUComputePassDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUComputePassDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "format", "depthWriteEnabled", "depthCompare", "stencilFront",
	"stencilBack", "stencilReadMask", "stencilWriteMask", "depthBias", "depthBiasSlopeScale",
	"depthBiasClamp"
)
public open class WGPUDepthStencilState : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var format: Int = 0

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var depthWriteEnabled: WGPUBool = 0

	/**
	 * mapped from WGPUCompareFunction
	 */
	@JvmField
	public var depthCompare: Int = 0

	/**
	 * mapped from WGPUStencilFaceState
	 */
	@JvmField
	public var stencilFront: WGPUStencilFaceState = WGPUStencilFaceState()

	/**
	 * mapped from WGPUStencilFaceState
	 */
	@JvmField
	public var stencilBack: WGPUStencilFaceState = WGPUStencilFaceState()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var stencilReadMask: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var stencilWriteMask: Int = 0

	/**
	 * mapped from int32_t
	 */
	@JvmField
	public var depthBias: Int = 0

	/**
	 * mapped from float
	 */
	@JvmField
	public var depthBiasSlopeScale: Float = 0.0f

	/**
	 * mapped from float
	 */
	@JvmField
	public var depthBiasClamp: Float = 0.0f

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUDepthStencilState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUDepthStencilState(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "layout", "buffer")
public open class WGPUImageCopyBuffer : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureDataLayout
	 */
	@JvmField
	public var layout: WGPUTextureDataLayout = WGPUTextureDataLayout()

	/**
	 * mapped from WGPUBuffer
	 */
	@JvmField
	public var buffer: WGPUBuffer = WGPUBufferImpl()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUImageCopyBuffer(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUImageCopyBuffer(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "texture", "mipLevel", "origin", "aspect")
public open class WGPUImageCopyTexture : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTexture
	 */
	@JvmField
	public var texture: WGPUTexture = WGPUTextureImpl()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var mipLevel: Int? = null

	/**
	 * mapped from WGPUOrigin3D
	 */
	@JvmField
	public var origin: WGPUOrigin3D? = null

	/**
	 * mapped from WGPUTextureAspect
	 */
	@JvmField
	public var aspect: Int? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUImageCopyTexture(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUImageCopyTexture(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "module", "entryPoint", "constantCount", "constants")
public open class WGPUProgrammableStageDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUShaderModule
	 */
	@JvmField
	public var module: WGPUShaderModule = WGPUShaderModuleImpl()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var entryPoint: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var constantCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUConstantEntry] =
	 * Declared([a8(nextInChain):[*:b1]a8(key):[*:b1]d8(value)](WGPUConstantEntry)))*
	 */
	@JvmField
	public var constants: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUProgrammableStageDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUProgrammableStageDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "view", "resolveTarget", "loadOp", "storeOp", "clearValue")
public open class WGPURenderPassColorAttachment : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureView
	 */
	@JvmField
	public var view: WGPUTextureView = WGPUTextureViewImpl()

	/**
	 * mapped from WGPUTextureView
	 */
	@JvmField
	public var resolveTarget: WGPUTextureView? = null

	/**
	 * mapped from WGPULoadOp
	 */
	@JvmField
	public var loadOp: Int = 0

	/**
	 * mapped from WGPUStoreOp
	 */
	@JvmField
	public var storeOp: Int = 0

	/**
	 * mapped from WGPUColor
	 */
	@JvmField
	public var clearValue: WGPUColor? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPassColorAttachment(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPassColorAttachment(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "limits")
public open class WGPURequiredLimits : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPULimits
	 */
	@JvmField
	public var limits: WGPULimits = WGPULimits()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURequiredLimits(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURequiredLimits(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "hintCount", "hints")
public open class WGPUShaderModuleDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: WGPUShaderModuleWGSLDescriptor.ByReference? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var hintCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUShaderModuleCompilationHint] =
	 * Declared([a8(nextInChain):[*:b1]a8(entryPoint):[*:b1]a8(layout):[*:b1]](WGPUShaderModuleCompilationHint)))*
	 */
	@JvmField
	public var hints: Array<WGPUShaderModuleCompilationHint.ByReference>? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "limits")
public open class WGPUSupportedLimits : Structure {
	/**
	 * mapped from (typedef Optional[WGPUChainedStructOut] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStructOut)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPULimits
	 */
	@JvmField
	public var limits: WGPULimits = WGPULimits()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSupportedLimits(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSupportedLimits(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "usage", "dimension", "size", "format",
	"mipLevelCount", "sampleCount", "viewFormatCount", "viewFormats"
)
public open class WGPUTextureDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUTextureUsageFlags
	 */
	@JvmField
	public var usage: WGPUTextureUsageFlags? = null

	/**
	 * mapped from WGPUTextureDimension
	 */
	@JvmField
	public var dimension: Int? = null

	/**
	 * mapped from WGPUExtent3D
	 */
	@JvmField
	public var size: WGPUExtent3D? = null

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var format: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var mipLevelCount: Int? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var sampleCount: Int? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var viewFormatCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUTextureFormat] = Declared(i4))*
	 */
	@JvmField
	public var viewFormats: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUTextureDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUTextureDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("arrayStride", "stepMode", "attributeCount", "attributes")
public open class WGPUVertexBufferLayout : Structure {
	/**
	 * mapped from uint64_t
	 */
	@JvmField
	public var arrayStride: Long = 0

	/**
	 * mapped from WGPUVertexStepMode
	 */
	@JvmField
	public var stepMode: Int? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var attributeCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUVertexAttribute] =
	 * Declared([i4(format)x4j8(offset)i4(shaderLocation)x4](WGPUVertexAttribute)))*
	 */
	@JvmField
	public var attributes: Array<WGPUVertexAttribute.ByReference>? = arrayOf(WGPUVertexAttribute.ByReference())

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUVertexBufferLayout(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUVertexBufferLayout(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "entryCount", "entries")
public open class WGPUBindGroupLayoutDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var entryCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUBindGroupLayoutEntry] =
	 * Declared([a8(nextInChain):[*:b1]i4(binding)i4(visibility)[a8(nextInChain):[*:b1]i4(type)i4(hasDynamicOffset)j8(minBindingSize)](buffer)[a8(nextInChain):[*:b1]i4(type)x4](sampler)[a8(nextInChain):[*:b1]i4(sampleType)i4(viewDimension)i4(multisampled)x4](texture)[a8(nextInChain):[*:b1]i4(access)i4(format)i4(viewDimension)x4](storageTexture)](WGPUBindGroupLayoutEntry)))*
	 */
	@JvmField
	public var entries: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "format", "blend", "writeMask")
public open class WGPUColorTargetState : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUTextureFormat
	 */
	@JvmField
	public var format: Int = 0

	/**
	 * mapped from (typedef Optional[const WGPUBlendState] =
	 * Declared([[i4(operation)i4(srcFactor)i4(dstFactor)](color)[i4(operation)i4(srcFactor)i4(dstFactor)](alpha)](WGPUBlendState)))*
	 */
	@JvmField
	public var blend: Pointer? = null

	/**
	 * mapped from WGPUColorWriteMaskFlags
	 */
	@JvmField
	public var writeMask: WGPUColorWriteMaskFlags? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUColorTargetState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUColorTargetState(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "label", "layout", "compute")
public open class WGPUComputePipelineDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUPipelineLayout
	 */
	@JvmField
	public var layout: WGPUPipelineLayout = WGPUPipelineLayoutImpl()

	/**
	 * mapped from WGPUProgrammableStageDescriptor
	 */
	@JvmField
	public var compute: WGPUProgrammableStageDescriptor = WGPUProgrammableStageDescriptor()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUComputePipelineDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUComputePipelineDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "requiredFeatureCount", "requiredFeatures",
	"requiredLimits", "defaultQueue", "deviceLostCallback", "deviceLostUserdata"
)
public open class WGPUDeviceDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var requiredFeatureCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUFeatureName] = Declared(i4))*
	 */
	@JvmField
	public var requiredFeatures: Pointer? = null

	/**
	 * mapped from (typedef Optional[const WGPURequiredLimits] =
	 * Declared([a8(nextInChain):[*:b1][i4(maxTextureDimension1D)i4(maxTextureDimension2D)i4(maxTextureDimension3D)i4(maxTextureArrayLayers)i4(maxBindGroups)i4(maxBindGroupsPlusVertexBuffers)i4(maxBindingsPerBindGroup)i4(maxDynamicUniformBuffersPerPipelineLayout)i4(maxDynamicStorageBuffersPerPipelineLayout)i4(maxSampledTexturesPerShaderStage)i4(maxSamplersPerShaderStage)i4(maxStorageBuffersPerShaderStage)i4(maxStorageTexturesPerShaderStage)i4(maxUniformBuffersPerShaderStage)j8(maxUniformBufferBindingSize)j8(maxStorageBufferBindingSize)i4(minUniformBufferOffsetAlignment)i4(minStorageBufferOffsetAlignment)i4(maxVertexBuffers)x4j8(maxBufferSize)i4(maxVertexAttributes)i4(maxVertexBufferArrayStride)i4(maxInterStageShaderComponents)i4(maxInterStageShaderVariables)i4(maxColorAttachments)i4(maxColorAttachmentBytesPerSample)i4(maxComputeWorkgroupStorageSize)i4(maxComputeInvocationsPerWorkgroup)i4(maxComputeWorkgroupSizeX)i4(maxComputeWorkgroupSizeY)i4(maxComputeWorkgroupSizeZ)i4(maxComputeWorkgroupsPerDimension)](limits)](WGPURequiredLimits)))*
	 */
	@JvmField
	public var requiredLimits: Pointer? = null

	/**
	 * mapped from WGPUQueueDescriptor
	 */
	@JvmField
	public var defaultQueue: WGPUQueueDescriptor = WGPUQueueDescriptor()

	/**
	 * mapped from WGPUDeviceLostCallback
	 */
	@JvmField
	public var deviceLostCallback: WGPUDeviceLostCallback? = null

	/**
	 * mapped from (Void)*
	 */
	@JvmField
	public var deviceLostUserdata: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUDeviceDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUDeviceDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "colorAttachmentCount", "colorAttachments",
	"depthStencilAttachment", "occlusionQuerySet", "timestampWrites"
)
public open class WGPURenderPassDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var colorAttachmentCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPURenderPassColorAttachment] =
	 * Declared([a8(nextInChain):[*:b1]a8(view):[*:b1]a8(resolveTarget):[*:b1]i4(loadOp)i4(storeOp)[d8(r)d8(g)d8(b)d8(a)](clearValue)](WGPURenderPassColorAttachment)))*
	 */
	@JvmField
	public var colorAttachments: Array<WGPURenderPassColorAttachment.ByReference>? = null

	/**
	 * mapped from (typedef Optional[const WGPURenderPassDepthStencilAttachment] =
	 * Declared([a8(view):[*:b1]i4(depthLoadOp)i4(depthStoreOp)f4(depthClearValue)i4(depthReadOnly)i4(stencilLoadOp)i4(stencilStoreOp)i4(stencilClearValue)i4(stencilReadOnly)](WGPURenderPassDepthStencilAttachment)))*
	 */
	@JvmField
	public var depthStencilAttachment: Pointer? = null

	/**
	 * mapped from WGPUQuerySet
	 */
	@JvmField
	public var occlusionQuerySet: WGPUQuerySet = WGPUQuerySetImpl()

	/**
	 * mapped from (typedef Optional[const WGPURenderPassTimestampWrites] =
	 * Declared([a8(querySet):[*:b1]i4(beginningOfPassWriteIndex)i4(endOfPassWriteIndex)](WGPURenderPassTimestampWrites)))*
	 */
	@JvmField
	public var timestampWrites: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPassDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPassDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "module", "entryPoint", "constantCount", "constants",
	"bufferCount", "buffers"
)
public open class WGPUVertexState : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUShaderModule
	 */
	@JvmField
	public var module: WGPUShaderModule = WGPUShaderModuleImpl()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var entryPoint: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var constantCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUConstantEntry] =
	 * Declared([a8(nextInChain):[*:b1]a8(key):[*:b1]d8(value)](WGPUConstantEntry)))*
	 */
	@JvmField
	public var constants: Array<WGPUConstantEntry.ByReference>? = arrayOf(WGPUConstantEntry.ByReference())

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var bufferCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUVertexBufferLayout] =
	 * Declared([j8(arrayStride)i4(stepMode)x4j8(attributeCount)a8(attributes):[*:b1]](WGPUVertexBufferLayout)))*
	 */
	@JvmField
	public var buffers: Array<WGPUVertexBufferLayout.ByReference>? = arrayOf(WGPUVertexBufferLayout.ByReference())

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUVertexState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUVertexState(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "module", "entryPoint", "constantCount", "constants",
	"targetCount", "targets"
)
public open class WGPUFragmentState : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUShaderModule
	 */
	@JvmField
	public var module: WGPUShaderModule = WGPUShaderModuleImpl()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var entryPoint: String? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var constantCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUConstantEntry] =
	 * Declared([a8(nextInChain):[*:b1]a8(key):[*:b1]d8(value)](WGPUConstantEntry)))*
	 */
	@JvmField
	public var constants: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var targetCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUColorTargetState] =
	 * Declared([a8(nextInChain):[*:b1]i4(format)x4a8(blend):[*:b1]i4(writeMask)x4](WGPUColorTargetState)))*
	 */
	@JvmField
	public var targets: Array<WGPUColorTargetState.ByReference>? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUFragmentState(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUFragmentState(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"nextInChain", "label", "layout", "vertex", "primitive", "depthStencil",
	"multisample", "fragment"
)
public open class WGPURenderPipelineDescriptor : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var label: String? = null

	/**
	 * mapped from WGPUPipelineLayout
	 */
	@JvmField
	public var layout: WGPUPipelineLayout? = null

	/**
	 * mapped from WGPUVertexState
	 */
	@JvmField
	public var vertex: WGPUVertexState? = null

	/**
	 * mapped from WGPUPrimitiveState
	 */
	@JvmField
	public var primitive: WGPUPrimitiveState? = null

	/**
	 * mapped from (typedef Optional[const WGPUDepthStencilState] =
	 * Declared([a8(nextInChain):[*:b1]i4(format)i4(depthWriteEnabled)i4(depthCompare)[i4(compare)i4(failOp)i4(depthFailOp)i4(passOp)](stencilFront)[i4(compare)i4(failOp)i4(depthFailOp)i4(passOp)](stencilBack)i4(stencilReadMask)i4(stencilWriteMask)i4(depthBias)f4(depthBiasSlopeScale)f4(depthBiasClamp)](WGPUDepthStencilState)))*
	 */
	@JvmField
	public var depthStencil: Pointer? = null

	/**
	 * mapped from WGPUMultisampleState
	 */
	@JvmField
	public var multisample: WGPUMultisampleState? = null

	/**
	 * mapped from (typedef Optional[const WGPUFragmentState] =
	 * Declared([a8(nextInChain):[*:b1]a8(module):[*:b1]a8(entryPoint):[*:b1]j8(constantCount)a8(constants):[*:b1]j8(targetCount)a8(targets):[*:b1]](WGPUFragmentState)))*
	 */
	@JvmField
	public var fragment: WGPUFragmentState.ByReference? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURenderPipelineDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURenderPipelineDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"chain", "backends", "flags", "dx12ShaderCompiler", "gles3MinorVersion",
	"dxilPath", "dxcPath"
)
public open class WGPUInstanceExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUInstanceBackendFlags
	 */
	@JvmField
	public var backends: WGPUInstanceBackendFlags = 0

	/**
	 * mapped from WGPUInstanceFlags
	 */
	@JvmField
	public var flags: WGPUInstanceFlags = 0

	/**
	 * mapped from WGPUDx12Compiler
	 */
	@JvmField
	public var dx12ShaderCompiler: Int = 0

	/**
	 * mapped from WGPUGles3MinorVersion
	 */
	@JvmField
	public var gles3MinorVersion: Int = 0

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var dxilPath: String? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var dxcPath: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUInstanceExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUInstanceExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "tracePath")
public open class WGPUDeviceExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var tracePath: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUDeviceExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUDeviceExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("maxPushConstantSize", "maxNonSamplerBindings")
public open class WGPUNativeLimits : Structure {
	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxPushConstantSize: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var maxNonSamplerBindings: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUNativeLimits(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUNativeLimits(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "limits")
public open class WGPURequiredLimitsExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUNativeLimits
	 */
	@JvmField
	public var limits: WGPUNativeLimits = WGPUNativeLimits()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURequiredLimitsExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURequiredLimitsExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "limits")
public open class WGPUSupportedLimitsExtras : Structure {
	/**
	 * mapped from WGPUChainedStructOut
	 */
	@JvmField
	public var chain: WGPUChainedStructOut = WGPUChainedStructOut()

	/**
	 * mapped from WGPUNativeLimits
	 */
	@JvmField
	public var limits: WGPUNativeLimits = WGPUNativeLimits()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSupportedLimitsExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSupportedLimitsExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("stages", "start", "end")
public open class WGPUPushConstantRange : Structure {
	/**
	 * mapped from WGPUShaderStageFlags
	 */
	@JvmField
	public var stages: WGPUShaderStageFlags = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var start: Int = 0

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var end: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUPushConstantRange(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUPushConstantRange(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "pushConstantRangeCount", "pushConstantRanges")
public open class WGPUPipelineLayoutExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var pushConstantRangeCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUPushConstantRange] =
	 * Declared([i4(stages)i4(start)i4(end)](WGPUPushConstantRange)))*
	 */
	@JvmField
	public var pushConstantRanges: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUPipelineLayoutExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUPipelineLayoutExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("queue", "submissionIndex")
public open class WGPUWrappedSubmissionIndex : Structure {
	/**
	 * mapped from WGPUQueue
	 */
	@JvmField
	public var queue: WGPUQueue = WGPUQueueImpl()

	/**
	 * mapped from WGPUSubmissionIndex
	 */
	@JvmField
	public var submissionIndex: WGPUSubmissionIndex = com.sun.jna.NativeLong(0)

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUWrappedSubmissionIndex(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUWrappedSubmissionIndex(pointer), Structure.ByValue
}

@Structure.FieldOrder("name", "value")
public open class WGPUShaderDefine : Structure {
	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var name: String? = null

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var `value`: String? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderDefine(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderDefine(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "stage", "code", "defineCount", "defines")
public open class WGPUShaderModuleGLSLDescriptor : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUShaderStage
	 */
	@JvmField
	public var stage: Int = 0

	/**
	 * mapped from (Char(layout = b1))*
	 */
	@JvmField
	public var code: String? = null

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var defineCount: Int = 0

	/**
	 * mapped from (typedef Optional[WGPUShaderDefine] =
	 * Declared([a8(name):[*:b1]a8(value):[*:b1]](WGPUShaderDefine)))*
	 */
	@JvmField
	public var defines: Pointer? = null

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUShaderModuleGLSLDescriptor(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUShaderModuleGLSLDescriptor(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"numAllocated", "numKeptFromUser", "numReleasedFromUser", "numError",
	"elementSize"
)
public open class WGPURegistryReport : Structure {
	/**
	 * mapped from size_t
	 */
	@JvmField
	public var numAllocated: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var numKeptFromUser: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var numReleasedFromUser: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var numError: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var elementSize: NativeLong = com.sun.jna.NativeLong(0)

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPURegistryReport(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPURegistryReport(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"adapters", "devices", "queues", "pipelineLayouts", "shaderModules",
	"bindGroupLayouts", "bindGroups", "commandBuffers", "renderBundles", "renderPipelines",
	"computePipelines", "querySets", "buffers", "textures", "textureViews", "samplers"
)
public open class WGPUHubReport : Structure {
	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var adapters: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var devices: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var queues: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var pipelineLayouts: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var shaderModules: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var bindGroupLayouts: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var bindGroups: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var commandBuffers: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var renderBundles: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var renderPipelines: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var computePipelines: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var querySets: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var buffers: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var textures: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var textureViews: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var samplers: WGPURegistryReport = WGPURegistryReport()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUHubReport(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUHubReport(pointer), Structure.ByValue
}

@Structure.FieldOrder("surfaces", "backendType", "vulkan", "metal", "dx12", "gl")
public open class WGPUGlobalReport : Structure {
	/**
	 * mapped from WGPURegistryReport
	 */
	@JvmField
	public var surfaces: WGPURegistryReport = WGPURegistryReport()

	/**
	 * mapped from WGPUBackendType
	 */
	@JvmField
	public var backendType: Int = 0

	/**
	 * mapped from WGPUHubReport
	 */
	@JvmField
	public var vulkan: WGPUHubReport = WGPUHubReport()

	/**
	 * mapped from WGPUHubReport
	 */
	@JvmField
	public var metal: WGPUHubReport = WGPUHubReport()

	/**
	 * mapped from WGPUHubReport
	 */
	@JvmField
	public var dx12: WGPUHubReport = WGPUHubReport()

	/**
	 * mapped from WGPUHubReport
	 */
	@JvmField
	public var gl: WGPUHubReport = WGPUHubReport()

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUGlobalReport(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUGlobalReport(pointer), Structure.ByValue
}

@Structure.FieldOrder("nextInChain", "backends")
public open class WGPUInstanceEnumerateAdapterOptions : Structure {
	/**
	 * mapped from (typedef Optional[const WGPUChainedStruct] =
	 * Declared([a8(next):[*:b1]i4(sType)x4](WGPUChainedStruct)))*
	 */
	@JvmField
	public var nextInChain: Pointer? = null

	/**
	 * mapped from WGPUInstanceBackendFlags
	 */
	@JvmField
	public var backends: WGPUInstanceBackendFlags = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUInstanceEnumerateAdapterOptions(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUInstanceEnumerateAdapterOptions(pointer), Structure.ByValue
}

@Structure.FieldOrder(
	"chain", "buffers", "bufferCount", "samplers", "samplerCount", "textureViews",
	"textureViewCount"
)
public open class WGPUBindGroupEntryExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (typedef Optional[const WGPUBuffer] = (Declared())*)*
	 */
	@JvmField
	public var buffers: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var bufferCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUSampler] = (Declared())*)*
	 */
	@JvmField
	public var samplers: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var samplerCount: NativeLong = com.sun.jna.NativeLong(0)

	/**
	 * mapped from (typedef Optional[const WGPUTextureView] = (Declared())*)*
	 */
	@JvmField
	public var textureViews: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var textureViewCount: NativeLong = com.sun.jna.NativeLong(0)

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupEntryExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupEntryExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "count")
public open class WGPUBindGroupLayoutEntryExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from uint32_t
	 */
	@JvmField
	public var count: Int = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutEntryExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUBindGroupLayoutEntryExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "pipelineStatistics", "pipelineStatisticCount")
public open class WGPUQuerySetDescriptorExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from (typedef Optional[const WGPUPipelineStatisticName] = Declared(i4))*
	 */
	@JvmField
	public var pipelineStatistics: Pointer? = null

	/**
	 * mapped from size_t
	 */
	@JvmField
	public var pipelineStatisticCount: NativeLong = com.sun.jna.NativeLong(0)

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUQuerySetDescriptorExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUQuerySetDescriptorExtras(pointer), Structure.ByValue
}

@Structure.FieldOrder("chain", "desiredMaximumFrameLatency")
public open class WGPUSurfaceConfigurationExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	public var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	public var desiredMaximumFrameLatency: WGPUBool = 0

	public constructor(pointer: Pointer?) : super(pointer)

	public constructor()

	public class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfigurationExtras(pointer), Structure.ByReference

	public class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfigurationExtras(pointer), Structure.ByValue
}
