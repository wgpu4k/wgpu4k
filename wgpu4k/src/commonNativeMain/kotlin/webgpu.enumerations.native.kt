// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

actual enum class AdapterType(val value: UInt) {
	DiscreteGPU(webgpu.WGPUAdapterType_DiscreteGPU),
	IntegratedGPU(webgpu.WGPUAdapterType_IntegratedGPU),
	CPU(webgpu.WGPUAdapterType_CPU),
	Unknown(webgpu.WGPUAdapterType_Unknown),
	;

	companion object {
		fun of(value: UInt): AdapterType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class AddressMode(val value: UInt) {
	ClampToEdge(webgpu.WGPUAddressMode_ClampToEdge),
	Repeat(webgpu.WGPUAddressMode_Repeat),
	MirrorRepeat(webgpu.WGPUAddressMode_MirrorRepeat),
	;

	companion object {
		fun of(value: UInt): AddressMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BackendType(val value: UInt) {
	Null(webgpu.WGPUBackendType_Null),
	WebGPU(webgpu.WGPUBackendType_WebGPU),
	D3D11(webgpu.WGPUBackendType_D3D11),
	D3D12(webgpu.WGPUBackendType_D3D12),
	Metal(webgpu.WGPUBackendType_Metal),
	Vulkan(webgpu.WGPUBackendType_Vulkan),
	OpenGL(webgpu.WGPUBackendType_OpenGL),
	OpenGLES(webgpu.WGPUBackendType_OpenGLES),
	;

	companion object {
		fun of(value: UInt): BackendType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BlendFactor(val value: UInt) {
	Zero(webgpu.WGPUBlendFactor_Zero),
	One(webgpu.WGPUBlendFactor_One),
	Src(webgpu.WGPUBlendFactor_Src),
	OneMinusSrc(webgpu.WGPUBlendFactor_OneMinusSrc),
	SrcAlpha(webgpu.WGPUBlendFactor_SrcAlpha),
	OneMinusSrcAlpha(webgpu.WGPUBlendFactor_OneMinusSrcAlpha),
	Dst(webgpu.WGPUBlendFactor_Dst),
	OneMinusDst(webgpu.WGPUBlendFactor_OneMinusDst),
	DstAlpha(webgpu.WGPUBlendFactor_DstAlpha),
	OneMinusDstAlpha(webgpu.WGPUBlendFactor_OneMinusDstAlpha),
	SrcAlphaSaturated(webgpu.WGPUBlendFactor_SrcAlphaSaturated),
	Constant(webgpu.WGPUBlendFactor_Constant),
	OneMinusConstant(webgpu.WGPUBlendFactor_OneMinusConstant),
	Src1(webgpu.WGPUBlendFactor_Src1),
	OneMinusSrc1(webgpu.WGPUBlendFactor_OneMinusSrc1),
	Src1Alpha(webgpu.WGPUBlendFactor_Src1Alpha),
	OneMinusSrc1Alpha(webgpu.WGPUBlendFactor_OneMinusSrc1Alpha),
	;

	companion object {
		fun of(value: UInt): BlendFactor? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BlendOperation(val value: UInt) {
	Add(webgpu.WGPUBlendOperation_Add),
	Subtract(webgpu.WGPUBlendOperation_Subtract),
	ReverseSubtract(webgpu.WGPUBlendOperation_ReverseSubtract),
	Min(webgpu.WGPUBlendOperation_Min),
	Max(webgpu.WGPUBlendOperation_Max),
	;

	companion object {
		fun of(value: UInt): BlendOperation? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BufferBindingType(val value: UInt) {
	BindingNotUsed(webgpu.WGPUBufferBindingType_BindingNotUsed),
	Uniform(webgpu.WGPUBufferBindingType_Uniform),
	Storage(webgpu.WGPUBufferBindingType_Storage),
	ReadOnlyStorage(webgpu.WGPUBufferBindingType_ReadOnlyStorage),
	;

	companion object {
		fun of(value: UInt): BufferBindingType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BufferMapState(val value: UInt) {
	Unmapped(webgpu.WGPUBufferMapState_Unmapped),
	Pending(webgpu.WGPUBufferMapState_Pending),
	Mapped(webgpu.WGPUBufferMapState_Mapped),
	;

	companion object {
		fun of(value: UInt): BufferMapState? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CallbackMode(val value: UInt) {
	WaitAnyOnly(webgpu.WGPUCallbackMode_WaitAnyOnly),
	AllowProcessEvents(webgpu.WGPUCallbackMode_AllowProcessEvents),
	AllowSpontaneous(webgpu.WGPUCallbackMode_AllowSpontaneous),
	;

	companion object {
		fun of(value: UInt): CallbackMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompareFunction(val value: UInt) {
	Never(webgpu.WGPUCompareFunction_Never),
	Less(webgpu.WGPUCompareFunction_Less),
	Equal(webgpu.WGPUCompareFunction_Equal),
	LessEqual(webgpu.WGPUCompareFunction_LessEqual),
	Greater(webgpu.WGPUCompareFunction_Greater),
	NotEqual(webgpu.WGPUCompareFunction_NotEqual),
	GreaterEqual(webgpu.WGPUCompareFunction_GreaterEqual),
	Always(webgpu.WGPUCompareFunction_Always),
	;

	companion object {
		fun of(value: UInt): CompareFunction? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompilationInfoRequestStatus(val value: UInt) {
	Success(webgpu.WGPUCompilationInfoRequestStatus_Success),
	InstanceDropped(webgpu.WGPUCompilationInfoRequestStatus_InstanceDropped),
	Error(webgpu.WGPUCompilationInfoRequestStatus_Error),
	Unknown(webgpu.WGPUCompilationInfoRequestStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): CompilationInfoRequestStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompilationMessageType(val value: UInt) {
	Error(webgpu.WGPUCompilationMessageType_Error),
	Warning(webgpu.WGPUCompilationMessageType_Warning),
	Info(webgpu.WGPUCompilationMessageType_Info),
	;

	companion object {
		fun of(value: UInt): CompilationMessageType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompositeAlphaMode(val value: UInt) {
	Auto(webgpu.WGPUCompositeAlphaMode_Auto),
	Opaque(webgpu.WGPUCompositeAlphaMode_Opaque),
	Premultiplied(webgpu.WGPUCompositeAlphaMode_Premultiplied),
	Unpremultiplied(webgpu.WGPUCompositeAlphaMode_Unpremultiplied),
	Inherit(webgpu.WGPUCompositeAlphaMode_Inherit),
	;

	companion object {
		fun of(value: UInt): CompositeAlphaMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CreatePipelineAsyncStatus(val value: UInt) {
	Success(webgpu.WGPUCreatePipelineAsyncStatus_Success),
	InstanceDropped(webgpu.WGPUCreatePipelineAsyncStatus_InstanceDropped),
	ValidationError(webgpu.WGPUCreatePipelineAsyncStatus_ValidationError),
	InternalError(webgpu.WGPUCreatePipelineAsyncStatus_InternalError),
	Unknown(webgpu.WGPUCreatePipelineAsyncStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): CreatePipelineAsyncStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CullMode(val value: UInt) {
	None(webgpu.WGPUCullMode_None),
	Front(webgpu.WGPUCullMode_Front),
	Back(webgpu.WGPUCullMode_Back),
	;

	companion object {
		fun of(value: UInt): CullMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class DeviceLostReason(val value: UInt) {
	Unknown(webgpu.WGPUDeviceLostReason_Unknown),
	Destroyed(webgpu.WGPUDeviceLostReason_Destroyed),
	InstanceDropped(webgpu.WGPUDeviceLostReason_InstanceDropped),
	FailedCreation(webgpu.WGPUDeviceLostReason_FailedCreation),
	;

	companion object {
		fun of(value: UInt): DeviceLostReason? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class ErrorFilter(val value: UInt) {
	Validation(webgpu.WGPUErrorFilter_Validation),
	OutOfMemory(webgpu.WGPUErrorFilter_OutOfMemory),
	Internal(webgpu.WGPUErrorFilter_Internal),
	;

	companion object {
		fun of(value: UInt): ErrorFilter? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class ErrorType(val value: UInt) {
	NoError(webgpu.WGPUErrorType_NoError),
	Validation(webgpu.WGPUErrorType_Validation),
	OutOfMemory(webgpu.WGPUErrorType_OutOfMemory),
	Internal(webgpu.WGPUErrorType_Internal),
	Unknown(webgpu.WGPUErrorType_Unknown),
	;

	companion object {
		fun of(value: UInt): ErrorType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FeatureLevel(val value: UInt) {
	Compatibility(webgpu.WGPUFeatureLevel_Compatibility),
	Core(webgpu.WGPUFeatureLevel_Core),
	;

	companion object {
		fun of(value: UInt): FeatureLevel? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FeatureName(val value: UInt) {
	DepthClipControl(webgpu.WGPUFeatureName_DepthClipControl),
	Depth32FloatStencil8(webgpu.WGPUFeatureName_Depth32FloatStencil8),
	TimestampQuery(webgpu.WGPUFeatureName_TimestampQuery),
	TextureCompressionBC(webgpu.WGPUFeatureName_TextureCompressionBC),
	TextureCompressionBCSliced3D(webgpu.WGPUFeatureName_TextureCompressionBCSliced3D),
	TextureCompressionETC2(webgpu.WGPUFeatureName_TextureCompressionETC2),
	TextureCompressionASTC(webgpu.WGPUFeatureName_TextureCompressionASTC),
	TextureCompressionASTCSliced3D(webgpu.WGPUFeatureName_TextureCompressionASTCSliced3D),
	IndirectFirstInstance(webgpu.WGPUFeatureName_IndirectFirstInstance),
	ShaderF16(webgpu.WGPUFeatureName_ShaderF16),
	RG11B10UfloatRenderable(webgpu.WGPUFeatureName_RG11B10UfloatRenderable),
	BGRA8UnormStorage(webgpu.WGPUFeatureName_BGRA8UnormStorage),
	Float32Filterable(webgpu.WGPUFeatureName_Float32Filterable),
	Float32Blendable(webgpu.WGPUFeatureName_Float32Blendable),
	ClipDistances(webgpu.WGPUFeatureName_ClipDistances),
	DualSourceBlending(webgpu.WGPUFeatureName_DualSourceBlending),
	;

	companion object {
		fun of(value: UInt): FeatureName? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FilterMode(val value: UInt) {
	Nearest(webgpu.WGPUFilterMode_Nearest),
	Linear(webgpu.WGPUFilterMode_Linear),
	;

	companion object {
		fun of(value: UInt): FilterMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FrontFace(val value: UInt) {
	CCW(webgpu.WGPUFrontFace_CCW),
	CW(webgpu.WGPUFrontFace_CW),
	;

	companion object {
		fun of(value: UInt): FrontFace? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class IndexFormat(val value: UInt) {
	Uint16(webgpu.WGPUIndexFormat_Uint16),
	Uint32(webgpu.WGPUIndexFormat_Uint32),
	;

	companion object {
		fun of(value: UInt): IndexFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class LoadOp(val value: UInt) {
	Load(webgpu.WGPULoadOp_Load),
	Clear(webgpu.WGPULoadOp_Clear),
	;

	companion object {
		fun of(value: UInt): LoadOp? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class MapAsyncStatus(val value: UInt) {
	Success(webgpu.WGPUMapAsyncStatus_Success),
	InstanceDropped(webgpu.WGPUMapAsyncStatus_InstanceDropped),
	Error(webgpu.WGPUMapAsyncStatus_Error),
	Aborted(webgpu.WGPUMapAsyncStatus_Aborted),
	Unknown(webgpu.WGPUMapAsyncStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): MapAsyncStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class MipmapFilterMode(val value: UInt) {
	Nearest(webgpu.WGPUMipmapFilterMode_Nearest),
	Linear(webgpu.WGPUMipmapFilterMode_Linear),
	;

	companion object {
		fun of(value: UInt): MipmapFilterMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class OptionalBool(val value: UInt) {
	False(webgpu.WGPUOptionalBool_False),
	True(webgpu.WGPUOptionalBool_True),
	;

	companion object {
		fun of(value: UInt): OptionalBool? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PopErrorScopeStatus(val value: UInt) {
	Success(webgpu.WGPUPopErrorScopeStatus_Success),
	InstanceDropped(webgpu.WGPUPopErrorScopeStatus_InstanceDropped),
	EmptyStack(webgpu.WGPUPopErrorScopeStatus_EmptyStack),
	;

	companion object {
		fun of(value: UInt): PopErrorScopeStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PowerPreference(val value: UInt) {
	LowPower(webgpu.WGPUPowerPreference_LowPower),
	HighPerformance(webgpu.WGPUPowerPreference_HighPerformance),
	;

	companion object {
		fun of(value: UInt): PowerPreference? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PresentMode(val value: UInt) {
	Fifo(webgpu.WGPUPresentMode_Fifo),
	FifoRelaxed(webgpu.WGPUPresentMode_FifoRelaxed),
	Immediate(webgpu.WGPUPresentMode_Immediate),
	Mailbox(webgpu.WGPUPresentMode_Mailbox),
	;

	companion object {
		fun of(value: UInt): PresentMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PrimitiveTopology(val value: UInt) {
	PointList(webgpu.WGPUPrimitiveTopology_PointList),
	LineList(webgpu.WGPUPrimitiveTopology_LineList),
	LineStrip(webgpu.WGPUPrimitiveTopology_LineStrip),
	TriangleList(webgpu.WGPUPrimitiveTopology_TriangleList),
	TriangleStrip(webgpu.WGPUPrimitiveTopology_TriangleStrip),
	;

	companion object {
		fun of(value: UInt): PrimitiveTopology? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class QueryType(val value: UInt) {
	Occlusion(webgpu.WGPUQueryType_Occlusion),
	Timestamp(webgpu.WGPUQueryType_Timestamp),
	;

	companion object {
		fun of(value: UInt): QueryType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class QueueWorkDoneStatus(val value: UInt) {
	Success(webgpu.WGPUQueueWorkDoneStatus_Success),
	InstanceDropped(webgpu.WGPUQueueWorkDoneStatus_InstanceDropped),
	Error(webgpu.WGPUQueueWorkDoneStatus_Error),
	Unknown(webgpu.WGPUQueueWorkDoneStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): QueueWorkDoneStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class RequestAdapterStatus(val value: UInt) {
	Success(webgpu.WGPURequestAdapterStatus_Success),
	InstanceDropped(webgpu.WGPURequestAdapterStatus_InstanceDropped),
	Unavailable(webgpu.WGPURequestAdapterStatus_Unavailable),
	Error(webgpu.WGPURequestAdapterStatus_Error),
	Unknown(webgpu.WGPURequestAdapterStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): RequestAdapterStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class RequestDeviceStatus(val value: UInt) {
	Success(webgpu.WGPURequestDeviceStatus_Success),
	InstanceDropped(webgpu.WGPURequestDeviceStatus_InstanceDropped),
	Error(webgpu.WGPURequestDeviceStatus_Error),
	Unknown(webgpu.WGPURequestDeviceStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): RequestDeviceStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SType(val value: UInt) {
	ShaderSourceSPIRV(webgpu.WGPUSType_ShaderSourceSPIRV),
	ShaderSourceWGSL(webgpu.WGPUSType_ShaderSourceWGSL),
	RenderPassMaxDrawCount(webgpu.WGPUSType_RenderPassMaxDrawCount),
	SurfaceSourceMetalLayer(webgpu.WGPUSType_SurfaceSourceMetalLayer),
	SurfaceSourceWindowsHWND(webgpu.WGPUSType_SurfaceSourceWindowsHWND),
	SurfaceSourceXlibWindow(webgpu.WGPUSType_SurfaceSourceXlibWindow),
	SurfaceSourceWaylandSurface(webgpu.WGPUSType_SurfaceSourceWaylandSurface),
	SurfaceSourceAndroidNativeWindow(webgpu.WGPUSType_SurfaceSourceAndroidNativeWindow),
	SurfaceSourceXCBWindow(webgpu.WGPUSType_SurfaceSourceXCBWindow),
	;

	companion object {
		fun of(value: UInt): SType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SamplerBindingType(val value: UInt) {
	BindingNotUsed(webgpu.WGPUSamplerBindingType_BindingNotUsed),
	Filtering(webgpu.WGPUSamplerBindingType_Filtering),
	NonFiltering(webgpu.WGPUSamplerBindingType_NonFiltering),
	Comparison(webgpu.WGPUSamplerBindingType_Comparison),
	;

	companion object {
		fun of(value: UInt): SamplerBindingType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class Status(val value: UInt) {
	Success(webgpu.WGPUStatus_Success),
	Error(webgpu.WGPUStatus_Error),
	;

	companion object {
		fun of(value: UInt): Status? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StencilOperation(val value: UInt) {
	Keep(webgpu.WGPUStencilOperation_Keep),
	Zero(webgpu.WGPUStencilOperation_Zero),
	Replace(webgpu.WGPUStencilOperation_Replace),
	Invert(webgpu.WGPUStencilOperation_Invert),
	IncrementClamp(webgpu.WGPUStencilOperation_IncrementClamp),
	DecrementClamp(webgpu.WGPUStencilOperation_DecrementClamp),
	IncrementWrap(webgpu.WGPUStencilOperation_IncrementWrap),
	DecrementWrap(webgpu.WGPUStencilOperation_DecrementWrap),
	;

	companion object {
		fun of(value: UInt): StencilOperation? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StorageTextureAccess(val value: UInt) {
	BindingNotUsed(webgpu.WGPUStorageTextureAccess_BindingNotUsed),
	WriteOnly(webgpu.WGPUStorageTextureAccess_WriteOnly),
	ReadOnly(webgpu.WGPUStorageTextureAccess_ReadOnly),
	ReadWrite(webgpu.WGPUStorageTextureAccess_ReadWrite),
	;

	companion object {
		fun of(value: UInt): StorageTextureAccess? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StoreOp(val value: UInt) {
	Store(webgpu.WGPUStoreOp_Store),
	Discard(webgpu.WGPUStoreOp_Discard),
	;

	companion object {
		fun of(value: UInt): StoreOp? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SurfaceGetCurrentTextureStatus(val value: UInt) {
	SuccessOptimal(webgpu.WGPUSurfaceGetCurrentTextureStatus_SuccessOptimal),
	SuccessSuboptimal(webgpu.WGPUSurfaceGetCurrentTextureStatus_SuccessSuboptimal),
	Timeout(webgpu.WGPUSurfaceGetCurrentTextureStatus_Timeout),
	Outdated(webgpu.WGPUSurfaceGetCurrentTextureStatus_Outdated),
	Lost(webgpu.WGPUSurfaceGetCurrentTextureStatus_Lost),
	OutOfMemory(webgpu.WGPUSurfaceGetCurrentTextureStatus_OutOfMemory),
	DeviceLost(webgpu.WGPUSurfaceGetCurrentTextureStatus_DeviceLost),
	Error(webgpu.WGPUSurfaceGetCurrentTextureStatus_Error),
	;

	companion object {
		fun of(value: UInt): SurfaceGetCurrentTextureStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureAspect(val value: UInt) {
	All(webgpu.WGPUTextureAspect_All),
	StencilOnly(webgpu.WGPUTextureAspect_StencilOnly),
	DepthOnly(webgpu.WGPUTextureAspect_DepthOnly),
	;

	companion object {
		fun of(value: UInt): TextureAspect? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureDimension(val value: UInt) {
	OneD(webgpu.WGPUTextureDimension_1D),
	TwoD(webgpu.WGPUTextureDimension_2D),
	ThreeD(webgpu.WGPUTextureDimension_3D),
	;

	companion object {
		fun of(value: UInt): TextureDimension? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureFormat(val value: UInt) {
	R8Unorm(webgpu.WGPUTextureFormat_R8Unorm),
	R8Snorm(webgpu.WGPUTextureFormat_R8Snorm),
	R8Uint(webgpu.WGPUTextureFormat_R8Uint),
	R8Sint(webgpu.WGPUTextureFormat_R8Sint),
	R16Uint(webgpu.WGPUTextureFormat_R16Uint),
	R16Sint(webgpu.WGPUTextureFormat_R16Sint),
	R16Float(webgpu.WGPUTextureFormat_R16Float),
	RG8Unorm(webgpu.WGPUTextureFormat_RG8Unorm),
	RG8Snorm(webgpu.WGPUTextureFormat_RG8Snorm),
	RG8Uint(webgpu.WGPUTextureFormat_RG8Uint),
	RG8Sint(webgpu.WGPUTextureFormat_RG8Sint),
	R32Float(webgpu.WGPUTextureFormat_R32Float),
	R32Uint(webgpu.WGPUTextureFormat_R32Uint),
	R32Sint(webgpu.WGPUTextureFormat_R32Sint),
	RG16Uint(webgpu.WGPUTextureFormat_RG16Uint),
	RG16Sint(webgpu.WGPUTextureFormat_RG16Sint),
	RG16Float(webgpu.WGPUTextureFormat_RG16Float),
	RGBA8Unorm(webgpu.WGPUTextureFormat_RGBA8Unorm),
	RGBA8UnormSrgb(webgpu.WGPUTextureFormat_RGBA8UnormSrgb),
	RGBA8Snorm(webgpu.WGPUTextureFormat_RGBA8Snorm),
	RGBA8Uint(webgpu.WGPUTextureFormat_RGBA8Uint),
	RGBA8Sint(webgpu.WGPUTextureFormat_RGBA8Sint),
	BGRA8Unorm(webgpu.WGPUTextureFormat_BGRA8Unorm),
	BGRA8UnormSrgb(webgpu.WGPUTextureFormat_BGRA8UnormSrgb),
	RGB10A2Uint(webgpu.WGPUTextureFormat_RGB10A2Uint),
	RGB10A2Unorm(webgpu.WGPUTextureFormat_RGB10A2Unorm),
	RG11B10Ufloat(webgpu.WGPUTextureFormat_RG11B10Ufloat),
	RGB9E5Ufloat(webgpu.WGPUTextureFormat_RGB9E5Ufloat),
	RG32Float(webgpu.WGPUTextureFormat_RG32Float),
	RG32Uint(webgpu.WGPUTextureFormat_RG32Uint),
	RG32Sint(webgpu.WGPUTextureFormat_RG32Sint),
	RGBA16Uint(webgpu.WGPUTextureFormat_RGBA16Uint),
	RGBA16Sint(webgpu.WGPUTextureFormat_RGBA16Sint),
	RGBA16Float(webgpu.WGPUTextureFormat_RGBA16Float),
	RGBA32Float(webgpu.WGPUTextureFormat_RGBA32Float),
	RGBA32Uint(webgpu.WGPUTextureFormat_RGBA32Uint),
	RGBA32Sint(webgpu.WGPUTextureFormat_RGBA32Sint),
	Stencil8(webgpu.WGPUTextureFormat_Stencil8),
	Depth16Unorm(webgpu.WGPUTextureFormat_Depth16Unorm),
	Depth24Plus(webgpu.WGPUTextureFormat_Depth24Plus),
	Depth24PlusStencil8(webgpu.WGPUTextureFormat_Depth24PlusStencil8),
	Depth32Float(webgpu.WGPUTextureFormat_Depth32Float),
	Depth32FloatStencil8(webgpu.WGPUTextureFormat_Depth32FloatStencil8),
	BC1RGBAUnorm(webgpu.WGPUTextureFormat_BC1RGBAUnorm),
	BC1RGBAUnormSrgb(webgpu.WGPUTextureFormat_BC1RGBAUnormSrgb),
	BC2RGBAUnorm(webgpu.WGPUTextureFormat_BC2RGBAUnorm),
	BC2RGBAUnormSrgb(webgpu.WGPUTextureFormat_BC2RGBAUnormSrgb),
	BC3RGBAUnorm(webgpu.WGPUTextureFormat_BC3RGBAUnorm),
	BC3RGBAUnormSrgb(webgpu.WGPUTextureFormat_BC3RGBAUnormSrgb),
	BC4RUnorm(webgpu.WGPUTextureFormat_BC4RUnorm),
	BC4RSnorm(webgpu.WGPUTextureFormat_BC4RSnorm),
	BC5RGUnorm(webgpu.WGPUTextureFormat_BC5RGUnorm),
	BC5RGSnorm(webgpu.WGPUTextureFormat_BC5RGSnorm),
	BC6HRGBUfloat(webgpu.WGPUTextureFormat_BC6HRGBUfloat),
	BC6HRGBFloat(webgpu.WGPUTextureFormat_BC6HRGBFloat),
	BC7RGBAUnorm(webgpu.WGPUTextureFormat_BC7RGBAUnorm),
	BC7RGBAUnormSrgb(webgpu.WGPUTextureFormat_BC7RGBAUnormSrgb),
	ETC2RGB8Unorm(webgpu.WGPUTextureFormat_ETC2RGB8Unorm),
	ETC2RGB8UnormSrgb(webgpu.WGPUTextureFormat_ETC2RGB8UnormSrgb),
	ETC2RGB8A1Unorm(webgpu.WGPUTextureFormat_ETC2RGB8A1Unorm),
	ETC2RGB8A1UnormSrgb(webgpu.WGPUTextureFormat_ETC2RGB8A1UnormSrgb),
	ETC2RGBA8Unorm(webgpu.WGPUTextureFormat_ETC2RGBA8Unorm),
	ETC2RGBA8UnormSrgb(webgpu.WGPUTextureFormat_ETC2RGBA8UnormSrgb),
	EACR11Unorm(webgpu.WGPUTextureFormat_EACR11Unorm),
	EACR11Snorm(webgpu.WGPUTextureFormat_EACR11Snorm),
	EACRG11Unorm(webgpu.WGPUTextureFormat_EACRG11Unorm),
	EACRG11Snorm(webgpu.WGPUTextureFormat_EACRG11Snorm),
	ASTC4x4Unorm(webgpu.WGPUTextureFormat_ASTC4x4Unorm),
	ASTC4x4UnormSrgb(webgpu.WGPUTextureFormat_ASTC4x4UnormSrgb),
	ASTC5x4Unorm(webgpu.WGPUTextureFormat_ASTC5x4Unorm),
	ASTC5x4UnormSrgb(webgpu.WGPUTextureFormat_ASTC5x4UnormSrgb),
	ASTC5x5Unorm(webgpu.WGPUTextureFormat_ASTC5x5Unorm),
	ASTC5x5UnormSrgb(webgpu.WGPUTextureFormat_ASTC5x5UnormSrgb),
	ASTC6x5Unorm(webgpu.WGPUTextureFormat_ASTC6x5Unorm),
	ASTC6x5UnormSrgb(webgpu.WGPUTextureFormat_ASTC6x5UnormSrgb),
	ASTC6x6Unorm(webgpu.WGPUTextureFormat_ASTC6x6Unorm),
	ASTC6x6UnormSrgb(webgpu.WGPUTextureFormat_ASTC6x6UnormSrgb),
	ASTC8x5Unorm(webgpu.WGPUTextureFormat_ASTC8x5Unorm),
	ASTC8x5UnormSrgb(webgpu.WGPUTextureFormat_ASTC8x5UnormSrgb),
	ASTC8x6Unorm(webgpu.WGPUTextureFormat_ASTC8x6Unorm),
	ASTC8x6UnormSrgb(webgpu.WGPUTextureFormat_ASTC8x6UnormSrgb),
	ASTC8x8Unorm(webgpu.WGPUTextureFormat_ASTC8x8Unorm),
	ASTC8x8UnormSrgb(webgpu.WGPUTextureFormat_ASTC8x8UnormSrgb),
	ASTC10x5Unorm(webgpu.WGPUTextureFormat_ASTC10x5Unorm),
	ASTC10x5UnormSrgb(webgpu.WGPUTextureFormat_ASTC10x5UnormSrgb),
	ASTC10x6Unorm(webgpu.WGPUTextureFormat_ASTC10x6Unorm),
	ASTC10x6UnormSrgb(webgpu.WGPUTextureFormat_ASTC10x6UnormSrgb),
	ASTC10x8Unorm(webgpu.WGPUTextureFormat_ASTC10x8Unorm),
	ASTC10x8UnormSrgb(webgpu.WGPUTextureFormat_ASTC10x8UnormSrgb),
	ASTC10x10Unorm(webgpu.WGPUTextureFormat_ASTC10x10Unorm),
	ASTC10x10UnormSrgb(webgpu.WGPUTextureFormat_ASTC10x10UnormSrgb),
	ASTC12x10Unorm(webgpu.WGPUTextureFormat_ASTC12x10Unorm),
	ASTC12x10UnormSrgb(webgpu.WGPUTextureFormat_ASTC12x10UnormSrgb),
	ASTC12x12Unorm(webgpu.WGPUTextureFormat_ASTC12x12Unorm),
	ASTC12x12UnormSrgb(webgpu.WGPUTextureFormat_ASTC12x12UnormSrgb),
	;

	companion object {
		fun of(value: UInt): TextureFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureSampleType(val value: UInt) {
	BindingNotUsed(webgpu.WGPUTextureSampleType_BindingNotUsed),
	Float(webgpu.WGPUTextureSampleType_Float),
	UnfilterableFloat(webgpu.WGPUTextureSampleType_UnfilterableFloat),
	Depth(webgpu.WGPUTextureSampleType_Depth),
	Sint(webgpu.WGPUTextureSampleType_Sint),
	Uint(webgpu.WGPUTextureSampleType_Uint),
	;

	companion object {
		fun of(value: UInt): TextureSampleType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureViewDimension(val value: UInt) {
	OneD(webgpu.WGPUTextureViewDimension_1D),
	TwoD(webgpu.WGPUTextureViewDimension_2D),
	TwoDArray(webgpu.WGPUTextureViewDimension_2DArray),
	Cube(webgpu.WGPUTextureViewDimension_Cube),
	CubeArray(webgpu.WGPUTextureViewDimension_CubeArray),
	ThreeD(webgpu.WGPUTextureViewDimension_3D),
	;

	companion object {
		fun of(value: UInt): TextureViewDimension? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class VertexFormat(val value: UInt) {
	Uint8(webgpu.WGPUVertexFormat_Uint8),
	Uint8x2(webgpu.WGPUVertexFormat_Uint8x2),
	Uint8x4(webgpu.WGPUVertexFormat_Uint8x4),
	Sint8(webgpu.WGPUVertexFormat_Sint8),
	Sint8x2(webgpu.WGPUVertexFormat_Sint8x2),
	Sint8x4(webgpu.WGPUVertexFormat_Sint8x4),
	Unorm8(webgpu.WGPUVertexFormat_Unorm8),
	Unorm8x2(webgpu.WGPUVertexFormat_Unorm8x2),
	Unorm8x4(webgpu.WGPUVertexFormat_Unorm8x4),
	Snorm8(webgpu.WGPUVertexFormat_Snorm8),
	Snorm8x2(webgpu.WGPUVertexFormat_Snorm8x2),
	Snorm8x4(webgpu.WGPUVertexFormat_Snorm8x4),
	Uint16(webgpu.WGPUVertexFormat_Uint16),
	Uint16x2(webgpu.WGPUVertexFormat_Uint16x2),
	Uint16x4(webgpu.WGPUVertexFormat_Uint16x4),
	Sint16(webgpu.WGPUVertexFormat_Sint16),
	Sint16x2(webgpu.WGPUVertexFormat_Sint16x2),
	Sint16x4(webgpu.WGPUVertexFormat_Sint16x4),
	Unorm16(webgpu.WGPUVertexFormat_Unorm16),
	Unorm16x2(webgpu.WGPUVertexFormat_Unorm16x2),
	Unorm16x4(webgpu.WGPUVertexFormat_Unorm16x4),
	Snorm16(webgpu.WGPUVertexFormat_Snorm16),
	Snorm16x2(webgpu.WGPUVertexFormat_Snorm16x2),
	Snorm16x4(webgpu.WGPUVertexFormat_Snorm16x4),
	Float16(webgpu.WGPUVertexFormat_Float16),
	Float16x2(webgpu.WGPUVertexFormat_Float16x2),
	Float16x4(webgpu.WGPUVertexFormat_Float16x4),
	Float32(webgpu.WGPUVertexFormat_Float32),
	Float32x2(webgpu.WGPUVertexFormat_Float32x2),
	Float32x3(webgpu.WGPUVertexFormat_Float32x3),
	Float32x4(webgpu.WGPUVertexFormat_Float32x4),
	Uint32(webgpu.WGPUVertexFormat_Uint32),
	Uint32x2(webgpu.WGPUVertexFormat_Uint32x2),
	Uint32x3(webgpu.WGPUVertexFormat_Uint32x3),
	Uint32x4(webgpu.WGPUVertexFormat_Uint32x4),
	Sint32(webgpu.WGPUVertexFormat_Sint32),
	Sint32x2(webgpu.WGPUVertexFormat_Sint32x2),
	Sint32x3(webgpu.WGPUVertexFormat_Sint32x3),
	Sint32x4(webgpu.WGPUVertexFormat_Sint32x4),
	Unorm1010102(webgpu.WGPUVertexFormat_Unorm1010102),
	Unorm8x4BGRA(webgpu.WGPUVertexFormat_Unorm8x4BGRA),
	;

	companion object {
		fun of(value: UInt): VertexFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class VertexStepMode(val value: UInt) {
	VertexBufferNotUsed(webgpu.WGPUVertexStepMode_VertexBufferNotUsed),
	Vertex(webgpu.WGPUVertexStepMode_Vertex),
	Instance(webgpu.WGPUVertexStepMode_Instance),
	;

	companion object {
		fun of(value: UInt): VertexStepMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class WaitStatus(val value: UInt) {
	Success(webgpu.WGPUWaitStatus_Success),
	TimedOut(webgpu.WGPUWaitStatus_TimedOut),
	UnsupportedTimeout(webgpu.WGPUWaitStatus_UnsupportedTimeout),
	UnsupportedCount(webgpu.WGPUWaitStatus_UnsupportedCount),
	UnsupportedMixedSources(webgpu.WGPUWaitStatus_UnsupportedMixedSources),
	;

	companion object {
		fun of(value: UInt): WaitStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class WGSLLanguageFeatureName(val value: UInt) {
	ReadonlyAndReadwriteStorageTextures(webgpu.WGPUWGSLLanguageFeatureName_ReadonlyAndReadwriteStorageTextures),
	Packed4x8IntegerDotProduct(webgpu.WGPUWGSLLanguageFeatureName_Packed4x8IntegerDotProduct),
	UnrestrictedPointerParameters(webgpu.WGPUWGSLLanguageFeatureName_UnrestrictedPointerParameters),
	PointerCompositeAccess(webgpu.WGPUWGSLLanguageFeatureName_PointerCompositeAccess),
	;

	companion object {
		fun of(value: UInt): WGSLLanguageFeatureName? {
			return entries.find { it.value == value }
		}
	}
}

