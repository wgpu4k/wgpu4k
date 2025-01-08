// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

expect enum class RequestAdapterStatus {
	Success,
	Unavailable,
	Error,
	Unknown,
}
expect enum class AdapterType {
	DiscreteGPU,
	IntegratedGPU,
	CPU,
	Unknown,
}
expect enum class AddressMode {
	Repeat,
	MirrorRepeat,
	ClampToEdge,
}
expect enum class BackendType {
	Null,
	WebGPU,
	D3D11,
	D3D12,
	Metal,
	Vulkan,
	OpenGL,
	OpenGLES,
}
expect enum class BufferBindingType {
	Uniform,
	Storage,
	ReadOnlyStorage,
}
expect enum class SamplerBindingType {
	Filtering,
	NonFiltering,
	Comparison,
}
expect enum class TextureSampleType {
	Float,
	UnfilterableFloat,
	Depth,
	Sint,
	Uint,
}
expect enum class StorageTextureAccess {
	WriteOnly,
	ReadOnly,
	ReadWrite,
}
expect enum class BlendFactor {
	Zero,
	One,
	Src,
	OneMinusSrc,
	SrcAlpha,
	OneMinusSrcAlpha,
	Dst,
	OneMinusDst,
	DstAlpha,
	OneMinusDstAlpha,
	SrcAlphaSaturated,
	Constant,
	OneMinusConstant,
}
expect enum class BlendOperation {
	Add,
	Subtract,
	ReverseSubtract,
	Min,
	Max,
}
expect enum class BufferMapAsyncStatus {
	Success,
	ValidationError,
	Unknown,
	DeviceLost,
	DestroyedBeforeCallback,
	UnmappedBeforeCallback,
	MappingAlreadyPending,
	OffsetOutOfRange,
	SizeOutOfRange,
}
expect enum class BufferMapState {
	Unmapped,
	Pending,
	Mapped,
}
expect enum class CompareFunction {
	Never,
	Less,
	LessEqual,
	Greater,
	GreaterEqual,
	Equal,
	NotEqual,
	Always,
}
expect enum class CompilationInfoRequestStatus {
	Success,
	Error,
	DeviceLost,
	Unknown,
}
expect enum class CompilationMessageType {
	Error,
	Warning,
	Info,
}
expect enum class CompositeAlphaMode {
	Auto,
	Opaque,
	Premultiplied,
	Unpremultiplied,
	Inherit,
}
expect enum class CreatePipelineAsyncStatus {
	Success,
	ValidationError,
	InternalError,
	DeviceLost,
	DeviceDestroyed,
	Unknown,
}
expect enum class CullMode {
	None,
	Front,
	Back,
}
expect enum class DeviceLostReason {
	Unknown,
	Destroyed,
}
expect enum class ErrorFilter {
	Validation,
	OutOfMemory,
	Internal,
}
expect enum class ErrorType {
	NoError,
	Validation,
	OutOfMemory,
	Internal,
	Unknown,
	DeviceLost,
}
expect enum class FeatureName {
	DepthClipControl,
	Depth32FloatStencil8,
	TimestampQuery,
	TextureCompressionBC,
	TextureCompressionETC2,
	TextureCompressionASTC,
	IndirectFirstInstance,
	ShaderF16,
	RG11B10UfloatRenderable,
	BGRA8UnormStorage,
	Float32Filterable,
}
expect enum class FilterMode {
	Nearest,
	Linear,
}
expect enum class FrontFace {
	CCW,
	CW,
}
expect enum class IndexFormat {
	Uint16,
	Uint32,
}
expect enum class VertexStepMode {
	Vertex,
	Instance,
	VertexBufferNotUsed,
}
expect enum class LoadOp {
	Clear,
	Load,
}
expect enum class MipmapFilterMode {
	Nearest,
	Linear,
}
expect enum class StoreOp {
	Store,
	Discard,
}
expect enum class PowerPreference {
	LowPower,
	HighPerformance,
}
expect enum class PresentMode {
	Fifo,
	FifoRelaxed,
	Immediate,
	Mailbox,
}
expect enum class PrimitiveTopology {
	PointList,
	LineList,
	LineStrip,
	TriangleList,
	TriangleStrip,
}
expect enum class QueryType {
	Occlusion,
	Timestamp,
}
expect enum class QueueWorkDoneStatus {
	Success,
	Error,
	Unknown,
	DeviceLost,
}
expect enum class RequestDeviceStatus {
	Success,
	Error,
	Unknown,
}
expect enum class StencilOperation {
	Keep,
	Zero,
	Replace,
	Invert,
	IncrementClamp,
	DecrementClamp,
	IncrementWrap,
	DecrementWrap,
}
expect enum class SType {
	Invalid,
	SurfaceDescriptorFromMetalLayer,
	SurfaceDescriptorFromWindowsHWND,
	SurfaceDescriptorFromXlibWindow,
	SurfaceDescriptorFromCanvasHTMLSelector,
	ShaderModuleSPIRVDescriptor,
	ShaderModuleWGSLDescriptor,
	PrimitiveDepthClipControl,
	SurfaceDescriptorFromWaylandSurface,
	SurfaceDescriptorFromAndroidNativeWindow,
	SurfaceDescriptorFromXcbWindow,
	RenderPassDescriptorMaxDrawCount,
}
expect enum class SurfaceGetCurrentTextureStatus {
	Success,
	Timeout,
	Outdated,
	Lost,
	OutOfMemory,
	DeviceLost,
}
expect enum class TextureAspect {
	All,
	StencilOnly,
	DepthOnly,
}
expect enum class TextureDimension {
	OneD,
	TwoD,
	ThreeD,
}
expect enum class TextureFormat {
	R8Unorm,
	R8Snorm,
	R8Uint,
	R8Sint,
	R16Uint,
	R16Sint,
	R16Float,
	RG8Unorm,
	RG8Snorm,
	RG8Uint,
	RG8Sint,
	R32Float,
	R32Uint,
	R32Sint,
	RG16Uint,
	RG16Sint,
	RG16Float,
	RGBA8Unorm,
	RGBA8UnormSrgb,
	RGBA8Snorm,
	RGBA8Uint,
	RGBA8Sint,
	BGRA8Unorm,
	BGRA8UnormSrgb,
	RGB10A2Uint,
	RGB10A2Unorm,
	RG11B10Ufloat,
	RGB9E5Ufloat,
	RG32Float,
	RG32Uint,
	RG32Sint,
	RGBA16Uint,
	RGBA16Sint,
	RGBA16Float,
	RGBA32Float,
	RGBA32Uint,
	RGBA32Sint,
	Stencil8,
	Depth16Unorm,
	Depth24Plus,
	Depth24PlusStencil8,
	Depth32Float,
	Depth32FloatStencil8,
	BC1RGBAUnorm,
	BC1RGBAUnormSrgb,
	BC2RGBAUnorm,
	BC2RGBAUnormSrgb,
	BC3RGBAUnorm,
	BC3RGBAUnormSrgb,
	BC4RUnorm,
	BC4RSnorm,
	BC5RGUnorm,
	BC5RGSnorm,
	BC6HRGBUfloat,
	BC6HRGBFloat,
	BC7RGBAUnorm,
	BC7RGBAUnormSrgb,
	ETC2RGB8Unorm,
	ETC2RGB8UnormSrgb,
	ETC2RGB8A1Unorm,
	ETC2RGB8A1UnormSrgb,
	ETC2RGBA8Unorm,
	ETC2RGBA8UnormSrgb,
	EACR11Unorm,
	EACR11Snorm,
	EACRG11Unorm,
	EACRG11Snorm,
	ASTC4x4Unorm,
	ASTC4x4UnormSrgb,
	ASTC5x4Unorm,
	ASTC5x4UnormSrgb,
	ASTC5x5Unorm,
	ASTC5x5UnormSrgb,
	ASTC6x5Unorm,
	ASTC6x5UnormSrgb,
	ASTC6x6Unorm,
	ASTC6x6UnormSrgb,
	ASTC8x5Unorm,
	ASTC8x5UnormSrgb,
	ASTC8x6Unorm,
	ASTC8x6UnormSrgb,
	ASTC8x8Unorm,
	ASTC8x8UnormSrgb,
	ASTC10x5Unorm,
	ASTC10x5UnormSrgb,
	ASTC10x6Unorm,
	ASTC10x6UnormSrgb,
	ASTC10x8Unorm,
	ASTC10x8UnormSrgb,
	ASTC10x10Unorm,
	ASTC10x10UnormSrgb,
	ASTC12x10Unorm,
	ASTC12x10UnormSrgb,
	ASTC12x12Unorm,
	ASTC12x12UnormSrgb,
}
expect enum class TextureViewDimension {
	OneD,
	TwoD,
	TwoDArray,
	Cube,
	CubeArray,
	ThreeD,
}
expect enum class VertexFormat {
	Uint8x2,
	Uint8x4,
	Sint8x2,
	Sint8x4,
	Unorm8x2,
	Unorm8x4,
	Snorm8x2,
	Snorm8x4,
	Uint16x2,
	Uint16x4,
	Sint16x2,
	Sint16x4,
	Unorm16x2,
	Unorm16x4,
	Snorm16x2,
	Snorm16x4,
	Float16x2,
	Float16x4,
	Float32,
	Float32x2,
	Float32x3,
	Float32x4,
	Uint32,
	Uint32x2,
	Uint32x3,
	Uint32x4,
	Sint32,
	Sint32x2,
	Sint32x3,
	Sint32x4,
}
expect enum class WGSLFeatureName {
	ReadonlyAndReadwriteStorageTextures,
	Packed4x8IntegerDotProduct,
	UnrestrictedPointerParameters,
	PointerCompositeAccess,
}
