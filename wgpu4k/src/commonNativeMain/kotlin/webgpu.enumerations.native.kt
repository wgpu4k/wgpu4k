// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

actual enum class AdapterType(val value: UInt) {
	DiscreteGPU(io.ygdrasil.wgpu.WGPUAdapterType_DiscreteGPU),
	IntegratedGPU(io.ygdrasil.wgpu.WGPUAdapterType_IntegratedGPU),
	CPU(io.ygdrasil.wgpu.WGPUAdapterType_CPU),
	Unknown(io.ygdrasil.wgpu.WGPUAdapterType_Unknown),
	;

	companion object {
		fun of(value: UInt): AdapterType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class AddressMode(val value: UInt) {
	ClampToEdge(io.ygdrasil.wgpu.WGPUAddressMode_ClampToEdge),
	Repeat(io.ygdrasil.wgpu.WGPUAddressMode_Repeat),
	MirrorRepeat(io.ygdrasil.wgpu.WGPUAddressMode_MirrorRepeat),
	;

	companion object {
		fun of(value: UInt): AddressMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BackendType(val value: UInt) {
	Null(io.ygdrasil.wgpu.WGPUBackendType_Null),
	WebGPU(io.ygdrasil.wgpu.WGPUBackendType_WebGPU),
	D3D11(io.ygdrasil.wgpu.WGPUBackendType_D3D11),
	D3D12(io.ygdrasil.wgpu.WGPUBackendType_D3D12),
	Metal(io.ygdrasil.wgpu.WGPUBackendType_Metal),
	Vulkan(io.ygdrasil.wgpu.WGPUBackendType_Vulkan),
	OpenGL(io.ygdrasil.wgpu.WGPUBackendType_OpenGL),
	OpenGLES(io.ygdrasil.wgpu.WGPUBackendType_OpenGLES),
	;

	companion object {
		fun of(value: UInt): BackendType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BlendFactor(val value: UInt) {
	Zero(io.ygdrasil.wgpu.WGPUBlendFactor_Zero),
	One(io.ygdrasil.wgpu.WGPUBlendFactor_One),
	Src(io.ygdrasil.wgpu.WGPUBlendFactor_Src),
	OneMinusSrc(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusSrc),
	SrcAlpha(io.ygdrasil.wgpu.WGPUBlendFactor_SrcAlpha),
	OneMinusSrcAlpha(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusSrcAlpha),
	Dst(io.ygdrasil.wgpu.WGPUBlendFactor_Dst),
	OneMinusDst(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusDst),
	DstAlpha(io.ygdrasil.wgpu.WGPUBlendFactor_DstAlpha),
	OneMinusDstAlpha(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusDstAlpha),
	SrcAlphaSaturated(io.ygdrasil.wgpu.WGPUBlendFactor_SrcAlphaSaturated),
	Constant(io.ygdrasil.wgpu.WGPUBlendFactor_Constant),
	OneMinusConstant(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusConstant),
	Src1(io.ygdrasil.wgpu.WGPUBlendFactor_Src1),
	OneMinusSrc1(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusSrc1),
	Src1Alpha(io.ygdrasil.wgpu.WGPUBlendFactor_Src1Alpha),
	OneMinusSrc1Alpha(io.ygdrasil.wgpu.WGPUBlendFactor_OneMinusSrc1Alpha),
	;

	companion object {
		fun of(value: UInt): BlendFactor? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BlendOperation(val value: UInt) {
	Add(io.ygdrasil.wgpu.WGPUBlendOperation_Add),
	Subtract(io.ygdrasil.wgpu.WGPUBlendOperation_Subtract),
	ReverseSubtract(io.ygdrasil.wgpu.WGPUBlendOperation_ReverseSubtract),
	Min(io.ygdrasil.wgpu.WGPUBlendOperation_Min),
	Max(io.ygdrasil.wgpu.WGPUBlendOperation_Max),
	;

	companion object {
		fun of(value: UInt): BlendOperation? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BufferBindingType(val value: UInt) {
	BindingNotUsed(io.ygdrasil.wgpu.WGPUBufferBindingType_BindingNotUsed),
	Uniform(io.ygdrasil.wgpu.WGPUBufferBindingType_Uniform),
	Storage(io.ygdrasil.wgpu.WGPUBufferBindingType_Storage),
	ReadOnlyStorage(io.ygdrasil.wgpu.WGPUBufferBindingType_ReadOnlyStorage),
	;

	companion object {
		fun of(value: UInt): BufferBindingType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class BufferMapState(val value: UInt) {
	Unmapped(io.ygdrasil.wgpu.WGPUBufferMapState_Unmapped),
	Pending(io.ygdrasil.wgpu.WGPUBufferMapState_Pending),
	Mapped(io.ygdrasil.wgpu.WGPUBufferMapState_Mapped),
	;

	companion object {
		fun of(value: UInt): BufferMapState? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CallbackMode(val value: UInt) {
	WaitAnyOnly(io.ygdrasil.wgpu.WGPUCallbackMode_WaitAnyOnly),
	AllowProcessEvents(io.ygdrasil.wgpu.WGPUCallbackMode_AllowProcessEvents),
	AllowSpontaneous(io.ygdrasil.wgpu.WGPUCallbackMode_AllowSpontaneous),
	;

	companion object {
		fun of(value: UInt): CallbackMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompareFunction(val value: UInt) {
	Never(io.ygdrasil.wgpu.WGPUCompareFunction_Never),
	Less(io.ygdrasil.wgpu.WGPUCompareFunction_Less),
	Equal(io.ygdrasil.wgpu.WGPUCompareFunction_Equal),
	LessEqual(io.ygdrasil.wgpu.WGPUCompareFunction_LessEqual),
	Greater(io.ygdrasil.wgpu.WGPUCompareFunction_Greater),
	NotEqual(io.ygdrasil.wgpu.WGPUCompareFunction_NotEqual),
	GreaterEqual(io.ygdrasil.wgpu.WGPUCompareFunction_GreaterEqual),
	Always(io.ygdrasil.wgpu.WGPUCompareFunction_Always),
	;

	companion object {
		fun of(value: UInt): CompareFunction? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompilationInfoRequestStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUCompilationInfoRequestStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPUCompilationInfoRequestStatus_InstanceDropped),
	Error(io.ygdrasil.wgpu.WGPUCompilationInfoRequestStatus_Error),
	Unknown(io.ygdrasil.wgpu.WGPUCompilationInfoRequestStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): CompilationInfoRequestStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompilationMessageType(val value: UInt) {
	Error(io.ygdrasil.wgpu.WGPUCompilationMessageType_Error),
	Warning(io.ygdrasil.wgpu.WGPUCompilationMessageType_Warning),
	Info(io.ygdrasil.wgpu.WGPUCompilationMessageType_Info),
	;

	companion object {
		fun of(value: UInt): CompilationMessageType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CompositeAlphaMode(val value: UInt) {
	Auto(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Auto),
	Opaque(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Opaque),
	Premultiplied(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Premultiplied),
	Unpremultiplied(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Unpremultiplied),
	Inherit(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Inherit),
	;

	companion object {
		fun of(value: UInt): CompositeAlphaMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CreatePipelineAsyncStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUCreatePipelineAsyncStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPUCreatePipelineAsyncStatus_InstanceDropped),
	ValidationError(io.ygdrasil.wgpu.WGPUCreatePipelineAsyncStatus_ValidationError),
	InternalError(io.ygdrasil.wgpu.WGPUCreatePipelineAsyncStatus_InternalError),
	Unknown(io.ygdrasil.wgpu.WGPUCreatePipelineAsyncStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): CreatePipelineAsyncStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class CullMode(val value: UInt) {
	None(io.ygdrasil.wgpu.WGPUCullMode_None),
	Front(io.ygdrasil.wgpu.WGPUCullMode_Front),
	Back(io.ygdrasil.wgpu.WGPUCullMode_Back),
	;

	companion object {
		fun of(value: UInt): CullMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class DeviceLostReason(val value: UInt) {
	Unknown(io.ygdrasil.wgpu.WGPUDeviceLostReason_Unknown),
	Destroyed(io.ygdrasil.wgpu.WGPUDeviceLostReason_Destroyed),
	InstanceDropped(io.ygdrasil.wgpu.WGPUDeviceLostReason_InstanceDropped),
	FailedCreation(io.ygdrasil.wgpu.WGPUDeviceLostReason_FailedCreation),
	;

	companion object {
		fun of(value: UInt): DeviceLostReason? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class ErrorFilter(val value: UInt) {
	Validation(io.ygdrasil.wgpu.WGPUErrorFilter_Validation),
	OutOfMemory(io.ygdrasil.wgpu.WGPUErrorFilter_OutOfMemory),
	Internal(io.ygdrasil.wgpu.WGPUErrorFilter_Internal),
	;

	companion object {
		fun of(value: UInt): ErrorFilter? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class ErrorType(val value: UInt) {
	NoError(io.ygdrasil.wgpu.WGPUErrorType_NoError),
	Validation(io.ygdrasil.wgpu.WGPUErrorType_Validation),
	OutOfMemory(io.ygdrasil.wgpu.WGPUErrorType_OutOfMemory),
	Internal(io.ygdrasil.wgpu.WGPUErrorType_Internal),
	Unknown(io.ygdrasil.wgpu.WGPUErrorType_Unknown),
	;

	companion object {
		fun of(value: UInt): ErrorType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FeatureLevel(val value: UInt) {
	Compatibility(io.ygdrasil.wgpu.WGPUFeatureLevel_Compatibility),
	Core(io.ygdrasil.wgpu.WGPUFeatureLevel_Core),
	;

	companion object {
		fun of(value: UInt): FeatureLevel? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FeatureName(val value: UInt) {
	DepthClipControl(io.ygdrasil.wgpu.WGPUFeatureName_DepthClipControl),
	Depth32FloatStencil8(io.ygdrasil.wgpu.WGPUFeatureName_Depth32FloatStencil8),
	TimestampQuery(io.ygdrasil.wgpu.WGPUFeatureName_TimestampQuery),
	TextureCompressionBC(io.ygdrasil.wgpu.WGPUFeatureName_TextureCompressionBC),
	TextureCompressionBCSliced3D(io.ygdrasil.wgpu.WGPUFeatureName_TextureCompressionBCSliced3D),
	TextureCompressionETC2(io.ygdrasil.wgpu.WGPUFeatureName_TextureCompressionETC2),
	TextureCompressionASTC(io.ygdrasil.wgpu.WGPUFeatureName_TextureCompressionASTC),
	TextureCompressionASTCSliced3D(io.ygdrasil.wgpu.WGPUFeatureName_TextureCompressionASTCSliced3D),
	IndirectFirstInstance(io.ygdrasil.wgpu.WGPUFeatureName_IndirectFirstInstance),
	ShaderF16(io.ygdrasil.wgpu.WGPUFeatureName_ShaderF16),
	RG11B10UfloatRenderable(io.ygdrasil.wgpu.WGPUFeatureName_RG11B10UfloatRenderable),
	BGRA8UnormStorage(io.ygdrasil.wgpu.WGPUFeatureName_BGRA8UnormStorage),
	Float32Filterable(io.ygdrasil.wgpu.WGPUFeatureName_Float32Filterable),
	Float32Blendable(io.ygdrasil.wgpu.WGPUFeatureName_Float32Blendable),
	ClipDistances(io.ygdrasil.wgpu.WGPUFeatureName_ClipDistances),
	DualSourceBlending(io.ygdrasil.wgpu.WGPUFeatureName_DualSourceBlending),
	;

	companion object {
		fun of(value: UInt): FeatureName? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FilterMode(val value: UInt) {
	Nearest(io.ygdrasil.wgpu.WGPUFilterMode_Nearest),
	Linear(io.ygdrasil.wgpu.WGPUFilterMode_Linear),
	;

	companion object {
		fun of(value: UInt): FilterMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class FrontFace(val value: UInt) {
	CCW(io.ygdrasil.wgpu.WGPUFrontFace_CCW),
	CW(io.ygdrasil.wgpu.WGPUFrontFace_CW),
	;

	companion object {
		fun of(value: UInt): FrontFace? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class IndexFormat(val value: UInt) {
	Uint16(io.ygdrasil.wgpu.WGPUIndexFormat_Uint16),
	Uint32(io.ygdrasil.wgpu.WGPUIndexFormat_Uint32),
	;

	companion object {
		fun of(value: UInt): IndexFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class LoadOp(val value: UInt) {
	Load(io.ygdrasil.wgpu.WGPULoadOp_Load),
	Clear(io.ygdrasil.wgpu.WGPULoadOp_Clear),
	;

	companion object {
		fun of(value: UInt): LoadOp? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class MapAsyncStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUMapAsyncStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPUMapAsyncStatus_InstanceDropped),
	Error(io.ygdrasil.wgpu.WGPUMapAsyncStatus_Error),
	Aborted(io.ygdrasil.wgpu.WGPUMapAsyncStatus_Aborted),
	Unknown(io.ygdrasil.wgpu.WGPUMapAsyncStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): MapAsyncStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class MipmapFilterMode(val value: UInt) {
	Nearest(io.ygdrasil.wgpu.WGPUMipmapFilterMode_Nearest),
	Linear(io.ygdrasil.wgpu.WGPUMipmapFilterMode_Linear),
	;

	companion object {
		fun of(value: UInt): MipmapFilterMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class OptionalBool(val value: UInt) {
	False(io.ygdrasil.wgpu.WGPUOptionalBool_False),
	True(io.ygdrasil.wgpu.WGPUOptionalBool_True),
	;

	companion object {
		fun of(value: UInt): OptionalBool? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PopErrorScopeStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUPopErrorScopeStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPUPopErrorScopeStatus_InstanceDropped),
	EmptyStack(io.ygdrasil.wgpu.WGPUPopErrorScopeStatus_EmptyStack),
	;

	companion object {
		fun of(value: UInt): PopErrorScopeStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PowerPreference(val value: UInt) {
	LowPower(io.ygdrasil.wgpu.WGPUPowerPreference_LowPower),
	HighPerformance(io.ygdrasil.wgpu.WGPUPowerPreference_HighPerformance),
	;

	companion object {
		fun of(value: UInt): PowerPreference? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PresentMode(val value: UInt) {
	Fifo(io.ygdrasil.wgpu.WGPUPresentMode_Fifo),
	FifoRelaxed(io.ygdrasil.wgpu.WGPUPresentMode_FifoRelaxed),
	Immediate(io.ygdrasil.wgpu.WGPUPresentMode_Immediate),
	Mailbox(io.ygdrasil.wgpu.WGPUPresentMode_Mailbox),
	;

	companion object {
		fun of(value: UInt): PresentMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PrimitiveTopology(val value: UInt) {
	PointList(io.ygdrasil.wgpu.WGPUPrimitiveTopology_PointList),
	LineList(io.ygdrasil.wgpu.WGPUPrimitiveTopology_LineList),
	LineStrip(io.ygdrasil.wgpu.WGPUPrimitiveTopology_LineStrip),
	TriangleList(io.ygdrasil.wgpu.WGPUPrimitiveTopology_TriangleList),
	TriangleStrip(io.ygdrasil.wgpu.WGPUPrimitiveTopology_TriangleStrip),
	;

	companion object {
		fun of(value: UInt): PrimitiveTopology? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class QueryType(val value: UInt) {
	Occlusion(io.ygdrasil.wgpu.WGPUQueryType_Occlusion),
	Timestamp(io.ygdrasil.wgpu.WGPUQueryType_Timestamp),
	;

	companion object {
		fun of(value: UInt): QueryType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class QueueWorkDoneStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUQueueWorkDoneStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPUQueueWorkDoneStatus_InstanceDropped),
	Error(io.ygdrasil.wgpu.WGPUQueueWorkDoneStatus_Error),
	Unknown(io.ygdrasil.wgpu.WGPUQueueWorkDoneStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): QueueWorkDoneStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class RequestAdapterStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPURequestAdapterStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPURequestAdapterStatus_InstanceDropped),
	Unavailable(io.ygdrasil.wgpu.WGPURequestAdapterStatus_Unavailable),
	Error(io.ygdrasil.wgpu.WGPURequestAdapterStatus_Error),
	Unknown(io.ygdrasil.wgpu.WGPURequestAdapterStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): RequestAdapterStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class RequestDeviceStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPURequestDeviceStatus_Success),
	InstanceDropped(io.ygdrasil.wgpu.WGPURequestDeviceStatus_InstanceDropped),
	Error(io.ygdrasil.wgpu.WGPURequestDeviceStatus_Error),
	Unknown(io.ygdrasil.wgpu.WGPURequestDeviceStatus_Unknown),
	;

	companion object {
		fun of(value: UInt): RequestDeviceStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SType(val value: UInt) {
	ShaderSourceSPIRV(io.ygdrasil.wgpu.WGPUSType_ShaderSourceSPIRV),
	ShaderSourceWGSL(io.ygdrasil.wgpu.WGPUSType_ShaderSourceWGSL),
	RenderPassMaxDrawCount(io.ygdrasil.wgpu.WGPUSType_RenderPassMaxDrawCount),
	SurfaceSourceMetalLayer(io.ygdrasil.wgpu.WGPUSType_SurfaceSourceMetalLayer),
	SurfaceSourceWindowsHWND(io.ygdrasil.wgpu.WGPUSType_SurfaceSourceWindowsHWND),
	SurfaceSourceXlibWindow(io.ygdrasil.wgpu.WGPUSType_SurfaceSourceXlibWindow),
	SurfaceSourceWaylandSurface(io.ygdrasil.wgpu.WGPUSType_SurfaceSourceWaylandSurface),
	SurfaceSourceAndroidNativeWindow(io.ygdrasil.wgpu.WGPUSType_SurfaceSourceAndroidNativeWindow),
	SurfaceSourceXCBWindow(io.ygdrasil.wgpu.WGPUSType_SurfaceSourceXCBWindow),
	;

	companion object {
		fun of(value: UInt): SType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SamplerBindingType(val value: UInt) {
	BindingNotUsed(io.ygdrasil.wgpu.WGPUSamplerBindingType_BindingNotUsed),
	Filtering(io.ygdrasil.wgpu.WGPUSamplerBindingType_Filtering),
	NonFiltering(io.ygdrasil.wgpu.WGPUSamplerBindingType_NonFiltering),
	Comparison(io.ygdrasil.wgpu.WGPUSamplerBindingType_Comparison),
	;

	companion object {
		fun of(value: UInt): SamplerBindingType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class Status(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUStatus_Success),
	Error(io.ygdrasil.wgpu.WGPUStatus_Error),
	;

	companion object {
		fun of(value: UInt): Status? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StencilOperation(val value: UInt) {
	Keep(io.ygdrasil.wgpu.WGPUStencilOperation_Keep),
	Zero(io.ygdrasil.wgpu.WGPUStencilOperation_Zero),
	Replace(io.ygdrasil.wgpu.WGPUStencilOperation_Replace),
	Invert(io.ygdrasil.wgpu.WGPUStencilOperation_Invert),
	IncrementClamp(io.ygdrasil.wgpu.WGPUStencilOperation_IncrementClamp),
	DecrementClamp(io.ygdrasil.wgpu.WGPUStencilOperation_DecrementClamp),
	IncrementWrap(io.ygdrasil.wgpu.WGPUStencilOperation_IncrementWrap),
	DecrementWrap(io.ygdrasil.wgpu.WGPUStencilOperation_DecrementWrap),
	;

	companion object {
		fun of(value: UInt): StencilOperation? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StorageTextureAccess(val value: UInt) {
	BindingNotUsed(io.ygdrasil.wgpu.WGPUStorageTextureAccess_BindingNotUsed),
	WriteOnly(io.ygdrasil.wgpu.WGPUStorageTextureAccess_WriteOnly),
	ReadOnly(io.ygdrasil.wgpu.WGPUStorageTextureAccess_ReadOnly),
	ReadWrite(io.ygdrasil.wgpu.WGPUStorageTextureAccess_ReadWrite),
	;

	companion object {
		fun of(value: UInt): StorageTextureAccess? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class StoreOp(val value: UInt) {
	Store(io.ygdrasil.wgpu.WGPUStoreOp_Store),
	Discard(io.ygdrasil.wgpu.WGPUStoreOp_Discard),
	;

	companion object {
		fun of(value: UInt): StoreOp? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class SurfaceGetCurrentTextureStatus(val value: UInt) {
	SuccessOptimal(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_SuccessOptimal),
	SuccessSuboptimal(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_SuccessSuboptimal),
	Timeout(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_Timeout),
	Outdated(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_Outdated),
	Lost(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_Lost),
	OutOfMemory(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_OutOfMemory),
	DeviceLost(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_DeviceLost),
	Error(io.ygdrasil.wgpu.WGPUSurfaceGetCurrentTextureStatus_Error),
	;

	companion object {
		fun of(value: UInt): SurfaceGetCurrentTextureStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureAspect(val value: UInt) {
	All(io.ygdrasil.wgpu.WGPUTextureAspect_All),
	StencilOnly(io.ygdrasil.wgpu.WGPUTextureAspect_StencilOnly),
	DepthOnly(io.ygdrasil.wgpu.WGPUTextureAspect_DepthOnly),
	;

	companion object {
		fun of(value: UInt): TextureAspect? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureDimension(val value: UInt) {
	OneD(io.ygdrasil.wgpu.WGPUTextureDimension_1D),
	TwoD(io.ygdrasil.wgpu.WGPUTextureDimension_2D),
	ThreeD(io.ygdrasil.wgpu.WGPUTextureDimension_3D),
	;

	companion object {
		fun of(value: UInt): TextureDimension? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureFormat(val value: UInt) {
	R8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_R8Unorm),
	R8Snorm(io.ygdrasil.wgpu.WGPUTextureFormat_R8Snorm),
	R8Uint(io.ygdrasil.wgpu.WGPUTextureFormat_R8Uint),
	R8Sint(io.ygdrasil.wgpu.WGPUTextureFormat_R8Sint),
	R16Uint(io.ygdrasil.wgpu.WGPUTextureFormat_R16Uint),
	R16Sint(io.ygdrasil.wgpu.WGPUTextureFormat_R16Sint),
	R16Float(io.ygdrasil.wgpu.WGPUTextureFormat_R16Float),
	RG8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_RG8Unorm),
	RG8Snorm(io.ygdrasil.wgpu.WGPUTextureFormat_RG8Snorm),
	RG8Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RG8Uint),
	RG8Sint(io.ygdrasil.wgpu.WGPUTextureFormat_RG8Sint),
	R32Float(io.ygdrasil.wgpu.WGPUTextureFormat_R32Float),
	R32Uint(io.ygdrasil.wgpu.WGPUTextureFormat_R32Uint),
	R32Sint(io.ygdrasil.wgpu.WGPUTextureFormat_R32Sint),
	RG16Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RG16Uint),
	RG16Sint(io.ygdrasil.wgpu.WGPUTextureFormat_RG16Sint),
	RG16Float(io.ygdrasil.wgpu.WGPUTextureFormat_RG16Float),
	RGBA8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA8Unorm),
	RGBA8UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA8UnormSrgb),
	RGBA8Snorm(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA8Snorm),
	RGBA8Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA8Uint),
	RGBA8Sint(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA8Sint),
	BGRA8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_BGRA8Unorm),
	BGRA8UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_BGRA8UnormSrgb),
	RGB10A2Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RGB10A2Uint),
	RGB10A2Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_RGB10A2Unorm),
	RG11B10Ufloat(io.ygdrasil.wgpu.WGPUTextureFormat_RG11B10Ufloat),
	RGB9E5Ufloat(io.ygdrasil.wgpu.WGPUTextureFormat_RGB9E5Ufloat),
	RG32Float(io.ygdrasil.wgpu.WGPUTextureFormat_RG32Float),
	RG32Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RG32Uint),
	RG32Sint(io.ygdrasil.wgpu.WGPUTextureFormat_RG32Sint),
	RGBA16Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA16Uint),
	RGBA16Sint(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA16Sint),
	RGBA16Float(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA16Float),
	RGBA32Float(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA32Float),
	RGBA32Uint(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA32Uint),
	RGBA32Sint(io.ygdrasil.wgpu.WGPUTextureFormat_RGBA32Sint),
	Stencil8(io.ygdrasil.wgpu.WGPUTextureFormat_Stencil8),
	Depth16Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_Depth16Unorm),
	Depth24Plus(io.ygdrasil.wgpu.WGPUTextureFormat_Depth24Plus),
	Depth24PlusStencil8(io.ygdrasil.wgpu.WGPUTextureFormat_Depth24PlusStencil8),
	Depth32Float(io.ygdrasil.wgpu.WGPUTextureFormat_Depth32Float),
	Depth32FloatStencil8(io.ygdrasil.wgpu.WGPUTextureFormat_Depth32FloatStencil8),
	BC1RGBAUnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC1RGBAUnorm),
	BC1RGBAUnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_BC1RGBAUnormSrgb),
	BC2RGBAUnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC2RGBAUnorm),
	BC2RGBAUnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_BC2RGBAUnormSrgb),
	BC3RGBAUnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC3RGBAUnorm),
	BC3RGBAUnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_BC3RGBAUnormSrgb),
	BC4RUnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC4RUnorm),
	BC4RSnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC4RSnorm),
	BC5RGUnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC5RGUnorm),
	BC5RGSnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC5RGSnorm),
	BC6HRGBUfloat(io.ygdrasil.wgpu.WGPUTextureFormat_BC6HRGBUfloat),
	BC6HRGBFloat(io.ygdrasil.wgpu.WGPUTextureFormat_BC6HRGBFloat),
	BC7RGBAUnorm(io.ygdrasil.wgpu.WGPUTextureFormat_BC7RGBAUnorm),
	BC7RGBAUnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_BC7RGBAUnormSrgb),
	ETC2RGB8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ETC2RGB8Unorm),
	ETC2RGB8UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ETC2RGB8UnormSrgb),
	ETC2RGB8A1Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ETC2RGB8A1Unorm),
	ETC2RGB8A1UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ETC2RGB8A1UnormSrgb),
	ETC2RGBA8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ETC2RGBA8Unorm),
	ETC2RGBA8UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ETC2RGBA8UnormSrgb),
	EACR11Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_EACR11Unorm),
	EACR11Snorm(io.ygdrasil.wgpu.WGPUTextureFormat_EACR11Snorm),
	EACRG11Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_EACRG11Unorm),
	EACRG11Snorm(io.ygdrasil.wgpu.WGPUTextureFormat_EACRG11Snorm),
	ASTC4x4Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC4x4Unorm),
	ASTC4x4UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC4x4UnormSrgb),
	ASTC5x4Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC5x4Unorm),
	ASTC5x4UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC5x4UnormSrgb),
	ASTC5x5Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC5x5Unorm),
	ASTC5x5UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC5x5UnormSrgb),
	ASTC6x5Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC6x5Unorm),
	ASTC6x5UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC6x5UnormSrgb),
	ASTC6x6Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC6x6Unorm),
	ASTC6x6UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC6x6UnormSrgb),
	ASTC8x5Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC8x5Unorm),
	ASTC8x5UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC8x5UnormSrgb),
	ASTC8x6Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC8x6Unorm),
	ASTC8x6UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC8x6UnormSrgb),
	ASTC8x8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC8x8Unorm),
	ASTC8x8UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC8x8UnormSrgb),
	ASTC10x5Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x5Unorm),
	ASTC10x5UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x5UnormSrgb),
	ASTC10x6Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x6Unorm),
	ASTC10x6UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x6UnormSrgb),
	ASTC10x8Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x8Unorm),
	ASTC10x8UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x8UnormSrgb),
	ASTC10x10Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x10Unorm),
	ASTC10x10UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC10x10UnormSrgb),
	ASTC12x10Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC12x10Unorm),
	ASTC12x10UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC12x10UnormSrgb),
	ASTC12x12Unorm(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC12x12Unorm),
	ASTC12x12UnormSrgb(io.ygdrasil.wgpu.WGPUTextureFormat_ASTC12x12UnormSrgb),
	;

	companion object {
		fun of(value: UInt): TextureFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureSampleType(val value: UInt) {
	BindingNotUsed(io.ygdrasil.wgpu.WGPUTextureSampleType_BindingNotUsed),
	Float(io.ygdrasil.wgpu.WGPUTextureSampleType_Float),
	UnfilterableFloat(io.ygdrasil.wgpu.WGPUTextureSampleType_UnfilterableFloat),
	Depth(io.ygdrasil.wgpu.WGPUTextureSampleType_Depth),
	Sint(io.ygdrasil.wgpu.WGPUTextureSampleType_Sint),
	Uint(io.ygdrasil.wgpu.WGPUTextureSampleType_Uint),
	;

	companion object {
		fun of(value: UInt): TextureSampleType? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class TextureViewDimension(val value: UInt) {
	OneD(io.ygdrasil.wgpu.WGPUTextureViewDimension_1D),
	TwoD(io.ygdrasil.wgpu.WGPUTextureViewDimension_2D),
	TwoDArray(io.ygdrasil.wgpu.WGPUTextureViewDimension_2DArray),
	Cube(io.ygdrasil.wgpu.WGPUTextureViewDimension_Cube),
	CubeArray(io.ygdrasil.wgpu.WGPUTextureViewDimension_CubeArray),
	ThreeD(io.ygdrasil.wgpu.WGPUTextureViewDimension_3D),
	;

	companion object {
		fun of(value: UInt): TextureViewDimension? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class VertexFormat(val value: UInt) {
	Uint8(io.ygdrasil.wgpu.WGPUVertexFormat_Uint8),
	Uint8x2(io.ygdrasil.wgpu.WGPUVertexFormat_Uint8x2),
	Uint8x4(io.ygdrasil.wgpu.WGPUVertexFormat_Uint8x4),
	Sint8(io.ygdrasil.wgpu.WGPUVertexFormat_Sint8),
	Sint8x2(io.ygdrasil.wgpu.WGPUVertexFormat_Sint8x2),
	Sint8x4(io.ygdrasil.wgpu.WGPUVertexFormat_Sint8x4),
	Unorm8(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm8),
	Unorm8x2(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm8x2),
	Unorm8x4(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm8x4),
	Snorm8(io.ygdrasil.wgpu.WGPUVertexFormat_Snorm8),
	Snorm8x2(io.ygdrasil.wgpu.WGPUVertexFormat_Snorm8x2),
	Snorm8x4(io.ygdrasil.wgpu.WGPUVertexFormat_Snorm8x4),
	Uint16(io.ygdrasil.wgpu.WGPUVertexFormat_Uint16),
	Uint16x2(io.ygdrasil.wgpu.WGPUVertexFormat_Uint16x2),
	Uint16x4(io.ygdrasil.wgpu.WGPUVertexFormat_Uint16x4),
	Sint16(io.ygdrasil.wgpu.WGPUVertexFormat_Sint16),
	Sint16x2(io.ygdrasil.wgpu.WGPUVertexFormat_Sint16x2),
	Sint16x4(io.ygdrasil.wgpu.WGPUVertexFormat_Sint16x4),
	Unorm16(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm16),
	Unorm16x2(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm16x2),
	Unorm16x4(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm16x4),
	Snorm16(io.ygdrasil.wgpu.WGPUVertexFormat_Snorm16),
	Snorm16x2(io.ygdrasil.wgpu.WGPUVertexFormat_Snorm16x2),
	Snorm16x4(io.ygdrasil.wgpu.WGPUVertexFormat_Snorm16x4),
	Float16(io.ygdrasil.wgpu.WGPUVertexFormat_Float16),
	Float16x2(io.ygdrasil.wgpu.WGPUVertexFormat_Float16x2),
	Float16x4(io.ygdrasil.wgpu.WGPUVertexFormat_Float16x4),
	Float32(io.ygdrasil.wgpu.WGPUVertexFormat_Float32),
	Float32x2(io.ygdrasil.wgpu.WGPUVertexFormat_Float32x2),
	Float32x3(io.ygdrasil.wgpu.WGPUVertexFormat_Float32x3),
	Float32x4(io.ygdrasil.wgpu.WGPUVertexFormat_Float32x4),
	Uint32(io.ygdrasil.wgpu.WGPUVertexFormat_Uint32),
	Uint32x2(io.ygdrasil.wgpu.WGPUVertexFormat_Uint32x2),
	Uint32x3(io.ygdrasil.wgpu.WGPUVertexFormat_Uint32x3),
	Uint32x4(io.ygdrasil.wgpu.WGPUVertexFormat_Uint32x4),
	Sint32(io.ygdrasil.wgpu.WGPUVertexFormat_Sint32),
	Sint32x2(io.ygdrasil.wgpu.WGPUVertexFormat_Sint32x2),
	Sint32x3(io.ygdrasil.wgpu.WGPUVertexFormat_Sint32x3),
	Sint32x4(io.ygdrasil.wgpu.WGPUVertexFormat_Sint32x4),
	Unorm1010102(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm1010102),
	Unorm8x4BGRA(io.ygdrasil.wgpu.WGPUVertexFormat_Unorm8x4BGRA),
	;

	companion object {
		fun of(value: UInt): VertexFormat? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class VertexStepMode(val value: UInt) {
	VertexBufferNotUsed(io.ygdrasil.wgpu.WGPUVertexStepMode_VertexBufferNotUsed),
	Vertex(io.ygdrasil.wgpu.WGPUVertexStepMode_Vertex),
	Instance(io.ygdrasil.wgpu.WGPUVertexStepMode_Instance),
	;

	companion object {
		fun of(value: UInt): VertexStepMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class WaitStatus(val value: UInt) {
	Success(io.ygdrasil.wgpu.WGPUWaitStatus_Success),
	TimedOut(io.ygdrasil.wgpu.WGPUWaitStatus_TimedOut),
	UnsupportedTimeout(io.ygdrasil.wgpu.WGPUWaitStatus_UnsupportedTimeout),
	UnsupportedCount(io.ygdrasil.wgpu.WGPUWaitStatus_UnsupportedCount),
	UnsupportedMixedSources(io.ygdrasil.wgpu.WGPUWaitStatus_UnsupportedMixedSources),
	;

	companion object {
		fun of(value: UInt): WaitStatus? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class WGSLLanguageFeatureName(val value: UInt) {
	ReadonlyAndReadwriteStorageTextures(io.ygdrasil.wgpu.WGPUWGSLLanguageFeatureName_ReadonlyAndReadwriteStorageTextures),
	Packed4x8IntegerDotProduct(io.ygdrasil.wgpu.WGPUWGSLLanguageFeatureName_Packed4x8IntegerDotProduct),
	UnrestrictedPointerParameters(io.ygdrasil.wgpu.WGPUWGSLLanguageFeatureName_UnrestrictedPointerParameters),
	PointerCompositeAccess(io.ygdrasil.wgpu.WGPUWGSLLanguageFeatureName_PointerCompositeAccess),
	;

	companion object {
		fun of(value: UInt): WGSLLanguageFeatureName? {
			return entries.find { it.value == value }
		}
	}
}

