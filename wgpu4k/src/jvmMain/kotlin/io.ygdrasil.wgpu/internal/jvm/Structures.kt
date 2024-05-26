import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.PointerType
import com.sun.jna.Structure
import com.sun.jna.ptr.PointerByReference
import io.ygdrasil.wgpu.internal.jvm.WGPUBool

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

class WGPUDeviceImpl : PointerType {
	constructor() : super()

	constructor(pointer: Pointer?) : super(pointer)

	class ByReference : PointerByReference {
		constructor() : super()

		constructor(pointer: Pointer?) : super(pointer)
	}
}


@Structure.FieldOrder("chain", "desiredMaximumFrameLatency")
open class WGPUSurfaceConfigurationExtras : Structure {
	/**
	 * mapped from WGPUChainedStruct
	 */
	@JvmField
	var chain: WGPUChainedStruct = WGPUChainedStruct()

	/**
	 * mapped from WGPUBool
	 */
	@JvmField
	var desiredMaximumFrameLatency: WGPUBool = 0

	constructor(pointer: Pointer?) : super(pointer)

	constructor()

	class ByReference(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfigurationExtras(pointer), Structure.ByReference

	class ByValue(
		pointer: Pointer? = null,
	) : WGPUSurfaceConfigurationExtras(pointer), Structure.ByValue
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