package io.ygdrasil.wgpu

interface EnumerationWithValue {
	public val value: Int
}

public enum class AdapterType(
	public val `value`: Int,
) {
	discretegpu(0),
	integratedgpu(1),
	cpu(2),
	unknown(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: AdapterType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): AdapterType? = entries.find {
			it.value == value
		}
	}
}

public enum class AddressMode(
	public val `value`: Int,
) {
	repeat(0),
	mirrorrepeat(1),
	clamptoedge(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: AddressMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): AddressMode? = entries.find {
			it.value == value
		}
	}
}

public enum class BackendType(
	public val `value`: Int,
) {
	undefined(0),
	`null`(1),
	webgpu(2),
	d3d11(3),
	d3d12(4),
	metal(5),
	vulkan(6),
	opengl(7),
	opengles(8),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BackendType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BackendType? = entries.find {
			it.value == value
		}
	}
}

public enum class BlendFactor(
	public val `value`: Int,
) {
	zero(0),
	one(1),
	src(2),
	oneminussrc(3),
	srcalpha(4),
	oneminussrcalpha(5),
	dst(6),
	oneminusdst(7),
	dstalpha(8),
	oneminusdstalpha(9),
	srcalphasaturated(10),
	constant(11),
	oneminusconstant(12),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BlendFactor): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BlendFactor? = entries.find {
			it.value == value
		}
	}
}

public enum class BlendOperation(
	public val `value`: Int,
) {
	add(0),
	subtract(1),
	reversesubtract(2),
	min(3),
	max(4),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BlendOperation): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BlendOperation? = entries.find {
			it.value == value
		}
	}
}

public enum class BufferBindingType(
	public val `value`: Int,
) {
	undefined(0),
	uniform(1),
	storage(2),
	readonlystorage(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BufferBindingType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BufferBindingType? = entries.find {
			it.value == value
		}
	}
}

public enum class BufferMapAsyncStatus(
	public val `value`: Int,
) {
	success(0),
	validationerror(1),
	unknown(2),
	devicelost(3),
	destroyedbeforecallback(4),
	unmappedbeforecallback(5),
	mappingalreadypending(6),
	offsetoutofrange(7),
	sizeoutofrange(8),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BufferMapAsyncStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BufferMapAsyncStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class BufferMapState(
	public val `value`: Int,
) {
	unmapped(0),
	pending(1),
	mapped(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BufferMapState): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BufferMapState? = entries.find {
			it.value == value
		}
	}
}

public enum class CompareFunction(
	public val `value`: Int,
) {
	undefined(0),
	never(1),
	less(2),
	lessequal(3),
	greater(4),
	greaterequal(5),
	equal(6),
	notequal(7),
	always(8),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: CompareFunction): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): CompareFunction? = entries.find {
			it.value == value
		}
	}
}

public enum class CompilationInfoRequestStatus(
	public val `value`: Int,
) {
	success(0),
	error(1),
	devicelost(2),
	unknown(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: CompilationInfoRequestStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): CompilationInfoRequestStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class CompilationMessageType(
	public val `value`: Int,
) {
	error(0),
	warning(1),
	info(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: CompilationMessageType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): CompilationMessageType? = entries.find {
			it.value == value
		}
	}
}

public enum class CompositeAlphaMode(
	public val `value`: Int,
) {
	auto(0),
	opaque(1),
	premultiplied(2),
	unpremultiplied(3),
	inherit(4),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: CompositeAlphaMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): CompositeAlphaMode? = entries.find {
			it.value == value
		}
	}
}

public enum class CreatePipelineAsyncStatus(
	public val `value`: Int,
) {
	success(0),
	validationerror(1),
	internalerror(2),
	devicelost(3),
	devicedestroyed(4),
	unknown(5),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: CreatePipelineAsyncStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): CreatePipelineAsyncStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class CullMode(
	public val `value`: Int,
) {
	none(0),
	front(1),
	back(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: CullMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): CullMode? = entries.find {
			it.value == value
		}
	}
}

public enum class DeviceLostReason(
	public val `value`: Int,
) {
	undefined(0),
	destroyed(1),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: DeviceLostReason): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): DeviceLostReason? = entries.find {
			it.value == value
		}
	}
}

public enum class ErrorFilter(
	public val `value`: Int,
) {
	validation(0),
	outofmemory(1),
	`internal`(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: ErrorFilter): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): ErrorFilter? = entries.find {
			it.value == value
		}
	}
}

public enum class ErrorType(
	public val `value`: Int,
) {
	noerror(0),
	validation(1),
	outofmemory(2),
	`internal`(3),
	unknown(4),
	devicelost(5),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: ErrorType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): ErrorType? = entries.find {
			it.value == value
		}
	}
}

public enum class FeatureName(
	public val `value`: Int,
) {
	undefined(0),
	depthclipcontrol(1),
	depth32floatstencil8(2),
	timestampquery(3),
	texturecompressionbc(4),
	texturecompressionetc2(5),
	texturecompressionastc(6),
	indirectfirstinstance(7),
	shaderf16(8),
	rg11b10ufloatrenderable(9),
	bgra8unormstorage(10),
	float32filterable(11),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: FeatureName): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): FeatureName? = entries.find {
			it.value == value
		}
	}
}

public enum class FilterMode(
	public val `value`: Int,
) {
	nearest(0),
	linear(1),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: FilterMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): FilterMode? = entries.find {
			it.value == value
		}
	}
}

public enum class FrontFace(
	public val `value`: Int,
) {
	ccw(0),
	cw(1),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: FrontFace): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): FrontFace? = entries.find {
			it.value == value
		}
	}
}

public enum class IndexFormat(
	public val `value`: Int,
) {
	undefined(0),
	uint16(1),
	uint32(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: IndexFormat): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): IndexFormat? = entries.find {
			it.value == value
		}
	}
}

public enum class LoadOp(
	public val `value`: Int,
) {
	undefined(0),
	clear(1),
	load(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: LoadOp): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): LoadOp? = entries.find {
			it.value == value
		}
	}
}

public enum class MipmapFilterMode(
	public val `value`: Int,
) {
	nearest(0),
	linear(1),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: MipmapFilterMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): MipmapFilterMode? = entries.find {
			it.value == value
		}
	}
}

public enum class PowerPreference(
	public val `value`: Int,
) {
	undefined(0),
	lowpower(1),
	highperformance(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: PowerPreference): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): PowerPreference? = entries.find {
			it.value == value
		}
	}
}

public enum class PresentMode(
	public val `value`: Int,
) {
	fifo(0),
	fiforelaxed(1),
	immediate(2),
	mailbox(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: PresentMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): PresentMode? = entries.find {
			it.value == value
		}
	}
}

public enum class PrimitiveTopology(
	public val `value`: Int,
	public val stringValue: String,
) {
	pointlist(0, "point-list"),
	linelist(1, "line-list"),
	linestrip(2, "line-strip"),
	trianglelist(3, "triangle-list"),
	trianglestrip(4, "triangle-strip"),
	force32(2_147_483_647, "force32"),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: PrimitiveTopology): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): PrimitiveTopology? = entries.find {
			it.value == value
		}
	}
}

public enum class QueryType(
	public val `value`: Int,
) {
	occlusion(0),
	timestamp(1),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: QueryType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): QueryType? = entries.find {
			it.value == value
		}
	}
}

public enum class QueueWorkDoneStatus(
	public val `value`: Int,
) {
	success(0),
	error(1),
	unknown(2),
	devicelost(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: QueueWorkDoneStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): QueueWorkDoneStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class RequestAdapterStatus(
	public val `value`: Int,
) {
	success(0),
	unavailable(1),
	error(2),
	unknown(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: RequestAdapterStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): RequestAdapterStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class RequestDeviceStatus(
	public val `value`: Int,
) {
	success(0),
	error(1),
	unknown(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: RequestDeviceStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): RequestDeviceStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class SType(
	public val `value`: Int,
) {
	invalid(0),
	surfacedescriptorfrommetallayer(1),
	surfacedescriptorfromwindowshwnd(2),
	surfacedescriptorfromxlibwindow(3),
	surfacedescriptorfromcanvashtmlselector(4),
	shadermodulespirvdescriptor(5),
	shadermodulewgsldescriptor(6),
	primitivedepthclipcontrol(7),
	surfacedescriptorfromwaylandsurface(8),
	surfacedescriptorfromandroidnativewindow(9),
	surfacedescriptorfromxcbwindow(10),
	renderpassdescriptormaxdrawcount(15),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: SType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): SType? = entries.find {
			it.value == value
		}
	}
}

public enum class SamplerBindingType(
	public val `value`: Int,
) {
	undefined(0),
	filtering(1),
	nonfiltering(2),
	comparison(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: SamplerBindingType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): SamplerBindingType? = entries.find {
			it.value == value
		}
	}
}

public enum class StencilOperation(
	public val `value`: Int,
) {
	keep(0),
	zero(1),
	replace(2),
	invert(3),
	incrementclamp(4),
	decrementclamp(5),
	incrementwrap(6),
	decrementwrap(7),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: StencilOperation): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): StencilOperation? = entries.find {
			it.value == value
		}
	}
}

public enum class StorageTextureAccess(
	public val `value`: Int,
) {
	undefined(0),
	writeonly(1),
	readonly(2),
	readwrite(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: StorageTextureAccess): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): StorageTextureAccess? = entries.find {
			it.value == value
		}
	}
}

public enum class StoreOp(
	public val `value`: Int,
) {
	undefined(0),
	store(1),
	discard(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: StoreOp): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): StoreOp? = entries.find {
			it.value == value
		}
	}
}

public enum class SurfaceGetCurrentTextureStatus(
	public val `value`: Int,
) {
	success(0),
	timeout(1),
	outdated(2),
	lost(3),
	outofmemory(4),
	devicelost(5),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: SurfaceGetCurrentTextureStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): SurfaceGetCurrentTextureStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class TextureAspect(
	public val `value`: Int,
	public val stringValue: String,
) {
	all(0, "all"),
	stencilonly(1, "stencil-only"),
	depthonly(2, "depth-only"),
	force32(2_147_483_647, "force32"),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: TextureAspect): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): TextureAspect? = entries.find {
			it.value == value
		}
	}
}

public enum class TextureDimension(
	public override val `value`: Int,
	public val stringValue: String
) : EnumerationWithValue {
	`_1d`(0, "1d"),
	`_2d`(1, "2d"),
	`_3d`(2, "3d"),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: TextureDimension): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): TextureDimension? = entries.find {
			it.value == value
		}
	}
}

public enum class TextureFormat(
	public override val value: Int,
) : EnumerationWithValue {
	undefined(0),
	r8unorm(1),
	r8snorm(2),
	r8uint(3),
	r8sint(4),
	r16uint(5),
	r16sint(6),
	r16float(7),
	rg8unorm(8),
	rg8snorm(9),
	rg8uint(10),
	rg8sint(11),
	r32float(12),
	r32uint(13),
	r32sint(14),
	rg16uint(15),
	rg16sint(16),
	rg16float(17),
	rgba8unorm(18),
	rgba8unormsrgb(19),
	rgba8snorm(20),
	rgba8uint(21),
	rgba8sint(22),
	bgra8unorm(23),
	bgra8unormsrgb(24),
	rgb10a2uint(25),
	rgb10a2unorm(26),
	rg11b10ufloat(27),
	rgb9e5ufloat(28),
	rg32float(29),
	rg32uint(30),
	rg32sint(31),
	rgba16uint(32),
	rgba16sint(33),
	rgba16float(34),
	rgba32float(35),
	rgba32uint(36),
	rgba32sint(37),
	stencil8(38),
	depth16unorm(39),
	depth24plus(40),
	depth24plusstencil8(41),
	depth32float(42),
	depth32floatstencil8(43),
	bc1rgbaunorm(44),
	bc1rgbaunormsrgb(45),
	bc2rgbaunorm(46),
	bc2rgbaunormsrgb(47),
	bc3rgbaunorm(48),
	bc3rgbaunormsrgb(49),
	bc4runorm(50),
	bc4rsnorm(51),
	bc5rgunorm(52),
	bc5rgsnorm(53),
	bc6hrgbufloat(54),
	bc6hrgbfloat(55),
	bc7rgbaunorm(56),
	bc7rgbaunormsrgb(57),
	etc2rgb8unorm(58),
	etc2rgb8unormsrgb(59),
	etc2rgb8a1unorm(60),
	etc2rgb8a1unormsrgb(61),
	etc2rgba8unorm(62),
	etc2rgba8unormsrgb(63),
	eacr11unorm(64),
	eacr11snorm(65),
	eacrg11unorm(66),
	eacrg11snorm(67),
	astc4x4unorm(68),
	astc4x4unormsrgb(69),
	astc5x4unorm(70),
	astc5x4unormsrgb(71),
	astc5x5unorm(72),
	astc5x5unormsrgb(73),
	astc6x5unorm(74),
	astc6x5unormsrgb(75),
	astc6x6unorm(76),
	astc6x6unormsrgb(77),
	astc8x5unorm(78),
	astc8x5unormsrgb(79),
	astc8x6unorm(80),
	astc8x6unormsrgb(81),
	astc8x8unorm(82),
	astc8x8unormsrgb(83),
	astc10x5unorm(84),
	astc10x5unormsrgb(85),
	astc10x6unorm(86),
	astc10x6unormsrgb(87),
	astc10x8unorm(88),
	astc10x8unormsrgb(89),
	astc10x10unorm(90),
	astc10x10unormsrgb(91),
	astc12x10unorm(92),
	astc12x10unormsrgb(93),
	astc12x12unorm(94),
	astc12x12unormsrgb(95),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: TextureFormat): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): TextureFormat? = entries.find {
			it.value == value
		}

		fun of(value: String): TextureFormat? = entries.find {
			it.name == value
		}
	}
}

public enum class TextureSampleType(
	public val `value`: Int,
) {
	undefined(0),
	float(1),
	unfilterablefloat(2),
	depth(3),
	sint(4),
	uint(5),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: TextureSampleType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): TextureSampleType? = entries.find {
			it.value == value
		}
	}
}

public enum class TextureViewDimension(
	public val `value`: Int,
) {
	undefined(0),
	`_1d`(1),
	`_2d`(2),
	`_2darray`(3),
	cube(4),
	cubearray(5),
	`_3d`(6),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: TextureViewDimension): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): TextureViewDimension? = entries.find {
			it.value == value
		}
	}
}

public enum class VertexFormat(
	public val `value`: Int,
) {
	undefined(0),
	uint8x2(1),
	uint8x4(2),
	sint8x2(3),
	sint8x4(4),
	unorm8x2(5),
	unorm8x4(6),
	snorm8x2(7),
	snorm8x4(8),
	uint16x2(9),
	uint16x4(10),
	sint16x2(11),
	sint16x4(12),
	unorm16x2(13),
	unorm16x4(14),
	snorm16x2(15),
	snorm16x4(16),
	float16x2(17),
	float16x4(18),
	float32(19),
	float32x2(20),
	float32x3(21),
	float32x4(22),
	uint32(23),
	uint32x2(24),
	uint32x3(25),
	uint32x4(26),
	sint32(27),
	sint32x2(28),
	sint32x3(29),
	sint32x4(30),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: VertexFormat): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): VertexFormat? = entries.find {
			it.value == value
		}
	}
}

public enum class VertexStepMode(
	public val `value`: Int,
) {
	vertex(0),
	instance(1),
	vertexbuffernotused(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: VertexStepMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): VertexStepMode? = entries.find {
			it.value == value
		}
	}
}

public enum class BufferUsage(
	public val `value`: Int,
) {
	none(0),
	mapread(1),
	mapwrite(2),
	copysrc(4),
	copydst(8),
	index(16),
	vertex(32),
	uniform(64),
	storage(128),
	indirect(256),
	queryresolve(512),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: BufferUsage): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): BufferUsage? = entries.find {
			it.value == value
		}
	}
}

public enum class ColorWriteMask(
	public val `value`: Int,
) {
	none(0),
	red(1),
	green(2),
	blue(4),
	alpha(8),
	all(15),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: ColorWriteMask): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): ColorWriteMask? = entries.find {
			it.value == value
		}
	}
}

public enum class MapMode(
	public val `value`: Int,
) {
	none(0),
	read(1),
	write(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: MapMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): MapMode? = entries.find {
			it.value == value
		}
	}
}

public enum class ShaderStage(
	public val `value`: Int,
) {
	none(0),
	vertex(1),
	fragment(2),
	compute(4),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: ShaderStage): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): ShaderStage? = entries.find {
			it.value == value
		}
	}
}

public enum class TextureUsage(
	public val `value`: Int,
) {
	none(0),
	copysrc(1),
	copydst(2),
	texturebinding(4),
	storagebinding(8),
	renderattachment(16),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: TextureUsage): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): TextureUsage? = entries.find {
			it.value == value
		}
	}
}

public infix fun Int.or(other: TextureUsage): Int = this or other.value

public enum class NativeSType(
	public val `value`: Int,
) {
	stype_deviceextras(196_609),
	stype_requiredlimitsextras(196_610),
	stype_pipelinelayoutextras(196_611),
	stype_shadermoduleglsldescriptor(196_612),
	stype_supportedlimitsextras(196_613),
	stype_instanceextras(196_614),
	stype_bindgroupentryextras(196_615),
	stype_bindgrouplayoutentryextras(196_616),
	stype_querysetdescriptorextras(196_617),
	stype_surfaceconfigurationextras(196_618),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: NativeSType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): NativeSType? = entries.find {
			it.value == value
		}
	}
}

public enum class NativeFeature(
	public val `value`: Int,
) {
	pushconstants(196_609),
	textureadapterspecificformatfeatures(196_610),
	multidrawindirect(196_611),
	multidrawindirectcount(196_612),
	vertexwritablestorage(196_613),
	texturebindingarray(196_614),
	sampledtextureandstoragebufferarraynonuniformindexing(196_615),
	pipelinestatisticsquery(196_616),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: NativeFeature): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): NativeFeature? = entries.find {
			it.value == value
		}
	}
}

public enum class LogLevel(
	public val `value`: Int,
) {
	off(0),
	error(1),
	warn(2),
	info(3),
	debug(4),
	trace(5),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: LogLevel): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): LogLevel? = entries.find {
			it.value == value
		}
	}
}

public enum class InstanceBackend(
	public val `value`: Int,
) {
	all(0),
	vulkan(1),
	gl(2),
	metal(4),
	dx12(8),
	dx11(16),
	browserwebgpu(32),
	primary(45),
	secondary(18),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: InstanceBackend): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): InstanceBackend? = entries.find {
			it.value == value
		}
	}
}

public enum class InstanceFlag(
	public val `value`: Int,
) {
	default(0),
	debug(1),
	validation(2),
	discardhallabels(4),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: InstanceFlag): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): InstanceFlag? = entries.find {
			it.value == value
		}
	}
}

public enum class Dx12Compiler(
	public val `value`: Int,
) {
	undefined(0),
	fxc(1),
	dxc(2),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: Dx12Compiler): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): Dx12Compiler? = entries.find {
			it.value == value
		}
	}
}

public enum class Gles3MinorVersion(
	public val `value`: Int,
) {
	automatic(0),
	version0(1),
	version1(2),
	version2(3),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: Gles3MinorVersion): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): Gles3MinorVersion? = entries.find {
			it.value == value
		}
	}
}

public enum class PipelineStatisticName(
	public val `value`: Int,
) {
	vertexshaderinvocations(0),
	clipperinvocations(1),
	clipperprimitivesout(2),
	fragmentshaderinvocations(3),
	computeshaderinvocations(4),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: PipelineStatisticName): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): PipelineStatisticName? = entries.find {
			it.value == value
		}
	}
}

public enum class NativeQueryType(
	public val `value`: Int,
) {
	pipelinestatistics(196_608),
	force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: NativeQueryType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): NativeQueryType? = entries.find {
			it.value == value
		}
	}
}
