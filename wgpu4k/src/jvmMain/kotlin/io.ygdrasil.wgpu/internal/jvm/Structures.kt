import com.sun.jna.NativeLong
import com.sun.jna.Pointer
import com.sun.jna.Structure

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

