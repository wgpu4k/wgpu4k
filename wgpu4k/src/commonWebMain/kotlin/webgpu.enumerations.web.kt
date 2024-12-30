// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

actual enum class AdapterType(val value: String) {
	DiscreteGPU("discrete-gpu"),
	IntegratedGPU("integrated-gpu"),
	CPU("cpu"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): AdapterType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class AddressMode(val value: String) {
	ClampToEdge("clamp-to-edge"),
	Repeat("repeat"),
	MirrorRepeat("mirror-repeat"),
	;

	companion object {
		fun of(value: String): AddressMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BackendType(val value: String) {
	Null("null"),
	WebGPU("webgpu"),
	D3D11("d3d11"),
	D3D12("d3d12"),
	Metal("metal"),
	Vulkan("vulkan"),
	OpenGL("opengl"),
	OpenGLES("opengles"),
	;

	companion object {
		fun of(value: String): BackendType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BlendFactor(val value: String) {
	Zero("zero"),
	One("one"),
	Src("src"),
	OneMinusSrc("one-minus-src"),
	SrcAlpha("src-alpha"),
	OneMinusSrcAlpha("one-minus-src-alpha"),
	Dst("dst"),
	OneMinusDst("one-minus-dst"),
	DstAlpha("dst-alpha"),
	OneMinusDstAlpha("one-minus-dst-alpha"),
	SrcAlphaSaturated("src-alpha-saturated"),
	Constant("constant"),
	OneMinusConstant("one-minus-constant"),
	Src1("src1"),
	OneMinusSrc1("one-minus-src1"),
	Src1Alpha("src1-alpha"),
	OneMinusSrc1Alpha("one-minus-src1-alpha"),
	;

	companion object {
		fun of(value: String): BlendFactor? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BlendOperation(val value: String) {
	Add("add"),
	Subtract("subtract"),
	ReverseSubtract("reverse-subtract"),
	Min("min"),
	Max("max"),
	;

	companion object {
		fun of(value: String): BlendOperation? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BufferBindingType(val value: String) {
	BindingNotUsed("binding-not-used"),
	Uniform("uniform"),
	Storage("storage"),
	ReadOnlyStorage("read-only-storage"),
	;

	companion object {
		fun of(value: String): BufferBindingType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BufferMapState(val value: String) {
	Unmapped("unmapped"),
	Pending("pending"),
	Mapped("mapped"),
	;

	companion object {
		fun of(value: String): BufferMapState? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CallbackMode(val value: String) {
	WaitAnyOnly("wait-any-only"),
	AllowProcessEvents("allow-process-events"),
	AllowSpontaneous("allow-spontaneous"),
	;

	companion object {
		fun of(value: String): CallbackMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompareFunction(val value: String) {
	Never("never"),
	Less("less"),
	Equal("equal"),
	LessEqual("less-equal"),
	Greater("greater"),
	NotEqual("not-equal"),
	GreaterEqual("greater-equal"),
	Always("always"),
	;

	companion object {
		fun of(value: String): CompareFunction? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompilationInfoRequestStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	Error("error"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): CompilationInfoRequestStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompilationMessageType(val value: String) {
	Error("error"),
	Warning("warning"),
	Info("info"),
	;

	companion object {
		fun of(value: String): CompilationMessageType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompositeAlphaMode(val value: String) {
	Auto("auto"),
	Opaque("opaque"),
	Premultiplied("premultiplied"),
	Unpremultiplied("unpremultiplied"),
	Inherit("inherit"),
	;

	companion object {
		fun of(value: String): CompositeAlphaMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CreatePipelineAsyncStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	ValidationError("validation-error"),
	InternalError("internal-error"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): CreatePipelineAsyncStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CullMode(val value: String) {
	None("none"),
	Front("front"),
	Back("back"),
	;

	companion object {
		fun of(value: String): CullMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class DeviceLostReason(val value: String) {
	Unknown("unknown"),
	Destroyed("destroyed"),
	InstanceDropped("instance-dropped"),
	FailedCreation("failed-creation"),
	;

	companion object {
		fun of(value: String): DeviceLostReason? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class ErrorFilter(val value: String) {
	Validation("validation"),
	OutOfMemory("out-of-memory"),
	Internal("internal"),
	;

	companion object {
		fun of(value: String): ErrorFilter? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class ErrorType(val value: String) {
	NoError("no-error"),
	Validation("validation"),
	OutOfMemory("out-of-memory"),
	Internal("internal"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): ErrorType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FeatureLevel(val value: String) {
	Compatibility("compatibility"),
	Core("core"),
	;

	companion object {
		fun of(value: String): FeatureLevel? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FeatureName(val value: String) {
	DepthClipControl("depth-clip-control"),
	Depth32FloatStencil8("depth32-float-stencil8"),
	TimestampQuery("timestamp-query"),
	TextureCompressionBC("texture-compression-bc"),
	TextureCompressionBCSliced3D("texture-compression-bc-sliced-3d"),
	TextureCompressionETC2("texture-compression-etc2"),
	TextureCompressionASTC("texture-compression-astc"),
	TextureCompressionASTCSliced3D("texture-compression-astc-sliced-3d"),
	IndirectFirstInstance("indirect-first-instance"),
	ShaderF16("shader-f16"),
	RG11B10UfloatRenderable("rg11b10-ufloat-renderable"),
	BGRA8UnormStorage("bgra8-unorm-storage"),
	Float32Filterable("float32-filterable"),
	Float32Blendable("float32-blendable"),
	ClipDistances("clip-distances"),
	DualSourceBlending("dual-source-blending"),
	;

	companion object {
		fun of(value: String): FeatureName? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FilterMode(val value: String) {
	Nearest("nearest"),
	Linear("linear"),
	;

	companion object {
		fun of(value: String): FilterMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FrontFace(val value: String) {
	CCW("ccw"),
	CW("cw"),
	;

	companion object {
		fun of(value: String): FrontFace? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class IndexFormat(val value: String) {
	Uint16("uint16"),
	Uint32("uint32"),
	;

	companion object {
		fun of(value: String): IndexFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class LoadOp(val value: String) {
	Load("load"),
	Clear("clear"),
	;

	companion object {
		fun of(value: String): LoadOp? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class MapAsyncStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	Error("error"),
	Aborted("aborted"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): MapAsyncStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class MipmapFilterMode(val value: String) {
	Nearest("nearest"),
	Linear("linear"),
	;

	companion object {
		fun of(value: String): MipmapFilterMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class OptionalBool(val value: String) {
	False("false"),
	True("true"),
	;

	companion object {
		fun of(value: String): OptionalBool? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PopErrorScopeStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	EmptyStack("empty-stack"),
	;

	companion object {
		fun of(value: String): PopErrorScopeStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PowerPreference(val value: String) {
	LowPower("low-power"),
	HighPerformance("high-performance"),
	;

	companion object {
		fun of(value: String): PowerPreference? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PresentMode(val value: String) {
	Fifo("fifo"),
	FifoRelaxed("fifo-relaxed"),
	Immediate("immediate"),
	Mailbox("mailbox"),
	;

	companion object {
		fun of(value: String): PresentMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PrimitiveTopology(val value: String) {
	PointList("point-list"),
	LineList("line-list"),
	LineStrip("line-strip"),
	TriangleList("triangle-list"),
	TriangleStrip("triangle-strip"),
	;

	companion object {
		fun of(value: String): PrimitiveTopology? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class QueryType(val value: String) {
	Occlusion("occlusion"),
	Timestamp("timestamp"),
	;

	companion object {
		fun of(value: String): QueryType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class QueueWorkDoneStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	Error("error"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): QueueWorkDoneStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class RequestAdapterStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	Unavailable("unavailable"),
	Error("error"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): RequestAdapterStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class RequestDeviceStatus(val value: String) {
	Success("success"),
	InstanceDropped("instance-dropped"),
	Error("error"),
	Unknown("unknown"),
	;

	companion object {
		fun of(value: String): RequestDeviceStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SType(val value: String) {
	ShaderSourceSPIRV("shader-source-spirv"),
	ShaderSourceWGSL("shader-source-wgsl"),
	RenderPassMaxDrawCount("render-pass-max-draw-count"),
	SurfaceSourceMetalLayer("surface-source-metal-layer"),
	SurfaceSourceWindowsHWND("surface-source-windows-hwnd"),
	SurfaceSourceXlibWindow("surface-source-xlib-window"),
	SurfaceSourceWaylandSurface("surface-source-wayland-surface"),
	SurfaceSourceAndroidNativeWindow("surface-source-android-native-window"),
	SurfaceSourceXCBWindow("surface-source-xcb-window"),
	;

	companion object {
		fun of(value: String): SType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SamplerBindingType(val value: String) {
	BindingNotUsed("binding-not-used"),
	Filtering("filtering"),
	NonFiltering("non-filtering"),
	Comparison("comparison"),
	;

	companion object {
		fun of(value: String): SamplerBindingType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class Status(val value: String) {
	Success("success"),
	Error("error"),
	;

	companion object {
		fun of(value: String): Status? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StencilOperation(val value: String) {
	Keep("keep"),
	Zero("zero"),
	Replace("replace"),
	Invert("invert"),
	IncrementClamp("increment-clamp"),
	DecrementClamp("decrement-clamp"),
	IncrementWrap("increment-wrap"),
	DecrementWrap("decrement-wrap"),
	;

	companion object {
		fun of(value: String): StencilOperation? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StorageTextureAccess(val value: String) {
	BindingNotUsed("binding-not-used"),
	WriteOnly("write-only"),
	ReadOnly("read-only"),
	ReadWrite("read-write"),
	;

	companion object {
		fun of(value: String): StorageTextureAccess? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StoreOp(val value: String) {
	Store("store"),
	Discard("discard"),
	;

	companion object {
		fun of(value: String): StoreOp? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SurfaceGetCurrentTextureStatus(val value: String) {
	SuccessOptimal("success-optimal"),
	SuccessSuboptimal("success-suboptimal"),
	Timeout("timeout"),
	Outdated("outdated"),
	Lost("lost"),
	OutOfMemory("out-of-memory"),
	DeviceLost("device-lost"),
	Error("error"),
	;

	companion object {
		fun of(value: String): SurfaceGetCurrentTextureStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureAspect(val value: String) {
	All("all"),
	StencilOnly("stencil-only"),
	DepthOnly("depth-only"),
	;

	companion object {
		fun of(value: String): TextureAspect? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureDimension(val value: String) {
	OneD("1d"),
	TwoD("2d"),
	ThreeD("3d"),
	;

	companion object {
		fun of(value: String): TextureDimension? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureFormat(val value: String) {
	R8Unorm("r8-unorm"),
	R8Snorm("r8-snorm"),
	R8Uint("r8-uint"),
	R8Sint("r8-sint"),
	R16Uint("r16-uint"),
	R16Sint("r16-sint"),
	R16Float("r16-float"),
	RG8Unorm("rg8-unorm"),
	RG8Snorm("rg8-snorm"),
	RG8Uint("rg8-uint"),
	RG8Sint("rg8-sint"),
	R32Float("r32-float"),
	R32Uint("r32-uint"),
	R32Sint("r32-sint"),
	RG16Uint("rg16-uint"),
	RG16Sint("rg16-sint"),
	RG16Float("rg16-float"),
	RGBA8Unorm("rgba8-unorm"),
	RGBA8UnormSrgb("rgba8-unorm-srgb"),
	RGBA8Snorm("rgba8-snorm"),
	RGBA8Uint("rgba8-uint"),
	RGBA8Sint("rgba8-sint"),
	BGRA8Unorm("bgra8-unorm"),
	BGRA8UnormSrgb("bgra8-unorm-srgb"),
	RGB10A2Uint("rgb10-a2-uint"),
	RGB10A2Unorm("rgb10-a2-unorm"),
	RG11B10Ufloat("rg11-b10-ufloat"),
	RGB9E5Ufloat("rgb9-e5-ufloat"),
	RG32Float("rg32-float"),
	RG32Uint("rg32-uint"),
	RG32Sint("rg32-sint"),
	RGBA16Uint("rgba16-uint"),
	RGBA16Sint("rgba16-sint"),
	RGBA16Float("rgba16-float"),
	RGBA32Float("rgba32-float"),
	RGBA32Uint("rgba32-uint"),
	RGBA32Sint("rgba32-sint"),
	Stencil8("stencil8"),
	Depth16Unorm("depth16-unorm"),
	Depth24Plus("depth24-plus"),
	Depth24PlusStencil8("depth24-plus-stencil8"),
	Depth32Float("depth32-float"),
	Depth32FloatStencil8("depth32-float-stencil8"),
	BC1RGBAUnorm("bc1-rgba-unorm"),
	BC1RGBAUnormSrgb("bc1-rgba-unorm-srgb"),
	BC2RGBAUnorm("bc2-rgba-unorm"),
	BC2RGBAUnormSrgb("bc2-rgba-unorm-srgb"),
	BC3RGBAUnorm("bc3-rgba-unorm"),
	BC3RGBAUnormSrgb("bc3-rgba-unorm-srgb"),
	BC4RUnorm("bc4-r-unorm"),
	BC4RSnorm("bc4-r-snorm"),
	BC5RGUnorm("bc5-rg-unorm"),
	BC5RGSnorm("bc5-rg-snorm"),
	BC6HRGBUfloat("bc6h-rgb-ufloat"),
	BC6HRGBFloat("bc6h-rgb-float"),
	BC7RGBAUnorm("bc7-rgba-unorm"),
	BC7RGBAUnormSrgb("bc7-rgba-unorm-srgb"),
	ETC2RGB8Unorm("etc2-rgb8-unorm"),
	ETC2RGB8UnormSrgb("etc2-rgb8-unorm-srgb"),
	ETC2RGB8A1Unorm("etc2-rgb8a1-unorm"),
	ETC2RGB8A1UnormSrgb("etc2-rgb8a1-unorm-srgb"),
	ETC2RGBA8Unorm("etc2-rgba8-unorm"),
	ETC2RGBA8UnormSrgb("etc2-rgba8-unorm-srgb"),
	EACR11Unorm("eac-r11-unorm"),
	EACR11Snorm("eac-r11-snorm"),
	EACRG11Unorm("eac-rg11-unorm"),
	EACRG11Snorm("eac-rg11-snorm"),
	ASTC4x4Unorm("astc-4x4-unorm"),
	ASTC4x4UnormSrgb("astc-4x4-unorm-srgb"),
	ASTC5x4Unorm("astc-5x4-unorm"),
	ASTC5x4UnormSrgb("astc-5x4-unorm-srgb"),
	ASTC5x5Unorm("astc-5x5-unorm"),
	ASTC5x5UnormSrgb("astc-5x5-unorm-srgb"),
	ASTC6x5Unorm("astc-6x5-unorm"),
	ASTC6x5UnormSrgb("astc-6x5-unorm-srgb"),
	ASTC6x6Unorm("astc-6x6-unorm"),
	ASTC6x6UnormSrgb("astc-6x6-unorm-srgb"),
	ASTC8x5Unorm("astc-8x5-unorm"),
	ASTC8x5UnormSrgb("astc-8x5-unorm-srgb"),
	ASTC8x6Unorm("astc-8x6-unorm"),
	ASTC8x6UnormSrgb("astc-8x6-unorm-srgb"),
	ASTC8x8Unorm("astc-8x8-unorm"),
	ASTC8x8UnormSrgb("astc-8x8-unorm-srgb"),
	ASTC10x5Unorm("astc-10x5-unorm"),
	ASTC10x5UnormSrgb("astc-10x5-unorm-srgb"),
	ASTC10x6Unorm("astc-10x6-unorm"),
	ASTC10x6UnormSrgb("astc-10x6-unorm-srgb"),
	ASTC10x8Unorm("astc-10x8-unorm"),
	ASTC10x8UnormSrgb("astc-10x8-unorm-srgb"),
	ASTC10x10Unorm("astc-10x10-unorm"),
	ASTC10x10UnormSrgb("astc-10x10-unorm-srgb"),
	ASTC12x10Unorm("astc-12x10-unorm"),
	ASTC12x10UnormSrgb("astc-12x10-unorm-srgb"),
	ASTC12x12Unorm("astc-12x12-unorm"),
	ASTC12x12UnormSrgb("astc-12x12-unorm-srgb"),
	;

	companion object {
		fun of(value: String): TextureFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureSampleType(val value: String) {
	BindingNotUsed("binding-not-used"),
	Float("float"),
	UnfilterableFloat("unfilterable-float"),
	Depth("depth"),
	Sint("sint"),
	Uint("uint"),
	;

	companion object {
		fun of(value: String): TextureSampleType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureViewDimension(val value: String) {
	OneD("1d"),
	TwoD("2d"),
	TwoDArray("2d-array"),
	Cube("cube"),
	CubeArray("cube-array"),
	ThreeD("3d"),
	;

	companion object {
		fun of(value: String): TextureViewDimension? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class VertexFormat(val value: String) {
	Uint8("uint8"),
	Uint8x2("uint8x2"),
	Uint8x4("uint8x4"),
	Sint8("sint8"),
	Sint8x2("sint8x2"),
	Sint8x4("sint8x4"),
	Unorm8("unorm8"),
	Unorm8x2("unorm8x2"),
	Unorm8x4("unorm8x4"),
	Snorm8("snorm8"),
	Snorm8x2("snorm8x2"),
	Snorm8x4("snorm8x4"),
	Uint16("uint16"),
	Uint16x2("uint16x2"),
	Uint16x4("uint16x4"),
	Sint16("sint16"),
	Sint16x2("sint16x2"),
	Sint16x4("sint16x4"),
	Unorm16("unorm16"),
	Unorm16x2("unorm16x2"),
	Unorm16x4("unorm16x4"),
	Snorm16("snorm16"),
	Snorm16x2("snorm16x2"),
	Snorm16x4("snorm16x4"),
	Float16("float16"),
	Float16x2("float16x2"),
	Float16x4("float16x4"),
	Float32("float32"),
	Float32x2("float32x2"),
	Float32x3("float32x3"),
	Float32x4("float32x4"),
	Uint32("uint32"),
	Uint32x2("uint32x2"),
	Uint32x3("uint32x3"),
	Uint32x4("uint32x4"),
	Sint32("sint32"),
	Sint32x2("sint32x2"),
	Sint32x3("sint32x3"),
	Sint32x4("sint32x4"),
	Unorm1010102("unorm10--10--10--2"),
	Unorm8x4BGRA("unorm8x4-b-g-r-a"),
	;

	companion object {
		fun of(value: String): VertexFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class VertexStepMode(val value: String) {
	VertexBufferNotUsed("vertex-buffer-not-used"),
	Vertex("vertex"),
	Instance("instance"),
	;

	companion object {
		fun of(value: String): VertexStepMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class WaitStatus(val value: String) {
	Success("success"),
	TimedOut("timed-out"),
	UnsupportedTimeout("unsupported-timeout"),
	UnsupportedCount("unsupported-count"),
	UnsupportedMixedSources("unsupported-mixed-sources"),
	;

	companion object {
		fun of(value: String): WaitStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class WGSLLanguageFeatureName(val value: String) {
	ReadonlyAndReadwriteStorageTextures("readonly-and-readwrite-storage-textures"),
	Packed4x8IntegerDotProduct("packed4x8-integer-dot-product"),
	UnrestrictedPointerParameters("unrestricted-pointer-parameters"),
	PointerCompositeAccess("pointer-composite-access"),
	;

	companion object {
		fun of(value: String): WGSLLanguageFeatureName? {
			return entries.find { it.value == value }
		}
	}
}

