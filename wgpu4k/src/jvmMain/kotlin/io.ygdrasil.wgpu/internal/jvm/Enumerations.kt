package io.ygdrasil.wgpu.`internal`.jvm

public enum class WGPUAdapterType(
	public val `value`: Int,
) {
	WGPUAdapterType_DiscreteGPU(0),
	WGPUAdapterType_IntegratedGPU(1),
	WGPUAdapterType_CPU(2),
	WGPUAdapterType_Unknown(3),
	WGPUAdapterType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUAdapterType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUAdapterType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUAddressMode(
	public val `value`: Int,
) {
	WGPUAddressMode_Repeat(0),
	WGPUAddressMode_MirrorRepeat(1),
	WGPUAddressMode_ClampToEdge(2),
	WGPUAddressMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUAddressMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUAddressMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBackendType(
	public val `value`: Int,
) {
	WGPUBackendType_Undefined(0),
	WGPUBackendType_Null(1),
	WGPUBackendType_WebGPU(2),
	WGPUBackendType_D3D11(3),
	WGPUBackendType_D3D12(4),
	WGPUBackendType_Metal(5),
	WGPUBackendType_Vulkan(6),
	WGPUBackendType_OpenGL(7),
	WGPUBackendType_OpenGLES(8),
	WGPUBackendType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBackendType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBackendType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBlendFactor(
	public val `value`: Int,
) {
	WGPUBlendFactor_Zero(0),
	WGPUBlendFactor_One(1),
	WGPUBlendFactor_Src(2),
	WGPUBlendFactor_OneMinusSrc(3),
	WGPUBlendFactor_SrcAlpha(4),
	WGPUBlendFactor_OneMinusSrcAlpha(5),
	WGPUBlendFactor_Dst(6),
	WGPUBlendFactor_OneMinusDst(7),
	WGPUBlendFactor_DstAlpha(8),
	WGPUBlendFactor_OneMinusDstAlpha(9),
	WGPUBlendFactor_SrcAlphaSaturated(10),
	WGPUBlendFactor_Constant(11),
	WGPUBlendFactor_OneMinusConstant(12),
	WGPUBlendFactor_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBlendFactor): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBlendFactor? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBlendOperation(
	public val `value`: Int,
) {
	WGPUBlendOperation_Add(0),
	WGPUBlendOperation_Subtract(1),
	WGPUBlendOperation_ReverseSubtract(2),
	WGPUBlendOperation_Min(3),
	WGPUBlendOperation_Max(4),
	WGPUBlendOperation_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBlendOperation): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBlendOperation? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBufferBindingType(
	public val `value`: Int,
) {
	WGPUBufferBindingType_Undefined(0),
	WGPUBufferBindingType_Uniform(1),
	WGPUBufferBindingType_Storage(2),
	WGPUBufferBindingType_ReadOnlyStorage(3),
	WGPUBufferBindingType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBufferBindingType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBufferBindingType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBufferMapAsyncStatus(
	public val `value`: Int,
) {
	WGPUBufferMapAsyncStatus_Success(0),
	WGPUBufferMapAsyncStatus_ValidationError(1),
	WGPUBufferMapAsyncStatus_Unknown(2),
	WGPUBufferMapAsyncStatus_DeviceLost(3),
	WGPUBufferMapAsyncStatus_DestroyedBeforeCallback(4),
	WGPUBufferMapAsyncStatus_UnmappedBeforeCallback(5),
	WGPUBufferMapAsyncStatus_MappingAlreadyPending(6),
	WGPUBufferMapAsyncStatus_OffsetOutOfRange(7),
	WGPUBufferMapAsyncStatus_SizeOutOfRange(8),
	WGPUBufferMapAsyncStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBufferMapAsyncStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBufferMapAsyncStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBufferMapState(
	public val `value`: Int,
) {
	WGPUBufferMapState_Unmapped(0),
	WGPUBufferMapState_Pending(1),
	WGPUBufferMapState_Mapped(2),
	WGPUBufferMapState_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBufferMapState): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBufferMapState? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUCompareFunction(
	public val `value`: Int,
) {
	WGPUCompareFunction_Undefined(0),
	WGPUCompareFunction_Never(1),
	WGPUCompareFunction_Less(2),
	WGPUCompareFunction_LessEqual(3),
	WGPUCompareFunction_Greater(4),
	WGPUCompareFunction_GreaterEqual(5),
	WGPUCompareFunction_Equal(6),
	WGPUCompareFunction_NotEqual(7),
	WGPUCompareFunction_Always(8),
	WGPUCompareFunction_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUCompareFunction): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUCompareFunction? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUCompilationInfoRequestStatus(
	public val `value`: Int,
) {
	WGPUCompilationInfoRequestStatus_Success(0),
	WGPUCompilationInfoRequestStatus_Error(1),
	WGPUCompilationInfoRequestStatus_DeviceLost(2),
	WGPUCompilationInfoRequestStatus_Unknown(3),
	WGPUCompilationInfoRequestStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUCompilationInfoRequestStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUCompilationInfoRequestStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUCompilationMessageType(
	public val `value`: Int,
) {
	WGPUCompilationMessageType_Error(0),
	WGPUCompilationMessageType_Warning(1),
	WGPUCompilationMessageType_Info(2),
	WGPUCompilationMessageType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUCompilationMessageType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUCompilationMessageType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUCompositeAlphaMode(
	public val `value`: Int,
) {
	WGPUCompositeAlphaMode_Auto(0),
	WGPUCompositeAlphaMode_Opaque(1),
	WGPUCompositeAlphaMode_Premultiplied(2),
	WGPUCompositeAlphaMode_Unpremultiplied(3),
	WGPUCompositeAlphaMode_Inherit(4),
	WGPUCompositeAlphaMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUCompositeAlphaMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUCompositeAlphaMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUCreatePipelineAsyncStatus(
	public val `value`: Int,
) {
	WGPUCreatePipelineAsyncStatus_Success(0),
	WGPUCreatePipelineAsyncStatus_ValidationError(1),
	WGPUCreatePipelineAsyncStatus_InternalError(2),
	WGPUCreatePipelineAsyncStatus_DeviceLost(3),
	WGPUCreatePipelineAsyncStatus_DeviceDestroyed(4),
	WGPUCreatePipelineAsyncStatus_Unknown(5),
	WGPUCreatePipelineAsyncStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUCreatePipelineAsyncStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUCreatePipelineAsyncStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUCullMode(
	public val `value`: Int,
) {
	WGPUCullMode_None(0),
	WGPUCullMode_Front(1),
	WGPUCullMode_Back(2),
	WGPUCullMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUCullMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUCullMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUDeviceLostReason(
	public val `value`: Int,
) {
	WGPUDeviceLostReason_Undefined(0),
	WGPUDeviceLostReason_Destroyed(1),
	WGPUDeviceLostReason_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUDeviceLostReason): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUDeviceLostReason? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUErrorFilter(
	public val `value`: Int,
) {
	WGPUErrorFilter_Validation(0),
	WGPUErrorFilter_OutOfMemory(1),
	WGPUErrorFilter_Internal(2),
	WGPUErrorFilter_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUErrorFilter): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUErrorFilter? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUErrorType(
	public val `value`: Int,
) {
	WGPUErrorType_NoError(0),
	WGPUErrorType_Validation(1),
	WGPUErrorType_OutOfMemory(2),
	WGPUErrorType_Internal(3),
	WGPUErrorType_Unknown(4),
	WGPUErrorType_DeviceLost(5),
	WGPUErrorType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUErrorType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUErrorType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUFeatureName(
	public val `value`: Int,
) {
	WGPUFeatureName_Undefined(0),
	WGPUFeatureName_DepthClipControl(1),
	WGPUFeatureName_Depth32FloatStencil8(2),
	WGPUFeatureName_TimestampQuery(3),
	WGPUFeatureName_TextureCompressionBC(4),
	WGPUFeatureName_TextureCompressionETC2(5),
	WGPUFeatureName_TextureCompressionASTC(6),
	WGPUFeatureName_IndirectFirstInstance(7),
	WGPUFeatureName_ShaderF16(8),
	WGPUFeatureName_RG11B10UfloatRenderable(9),
	WGPUFeatureName_BGRA8UnormStorage(10),
	WGPUFeatureName_Float32Filterable(11),
	WGPUFeatureName_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUFeatureName): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUFeatureName? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUFilterMode(
	public val `value`: Int,
) {
	WGPUFilterMode_Nearest(0),
	WGPUFilterMode_Linear(1),
	WGPUFilterMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUFilterMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUFilterMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUFrontFace(
	public val `value`: Int,
) {
	WGPUFrontFace_CCW(0),
	WGPUFrontFace_CW(1),
	WGPUFrontFace_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUFrontFace): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUFrontFace? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUIndexFormat(
	public val `value`: Int,
) {
	WGPUIndexFormat_Undefined(0),
	WGPUIndexFormat_Uint16(1),
	WGPUIndexFormat_Uint32(2),
	WGPUIndexFormat_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUIndexFormat): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUIndexFormat? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPULoadOp(
	public val `value`: Int,
) {
	WGPULoadOp_Undefined(0),
	WGPULoadOp_Clear(1),
	WGPULoadOp_Load(2),
	WGPULoadOp_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPULoadOp): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPULoadOp? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUMipmapFilterMode(
	public val `value`: Int,
) {
	WGPUMipmapFilterMode_Nearest(0),
	WGPUMipmapFilterMode_Linear(1),
	WGPUMipmapFilterMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUMipmapFilterMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUMipmapFilterMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUPowerPreference(
	public val `value`: Int,
) {
	WGPUPowerPreference_Undefined(0),
	WGPUPowerPreference_LowPower(1),
	WGPUPowerPreference_HighPerformance(2),
	WGPUPowerPreference_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUPowerPreference): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUPowerPreference? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUPresentMode(
	public val `value`: Int,
) {
	WGPUPresentMode_Fifo(0),
	WGPUPresentMode_FifoRelaxed(1),
	WGPUPresentMode_Immediate(2),
	WGPUPresentMode_Mailbox(3),
	WGPUPresentMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUPresentMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUPresentMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUPrimitiveTopology(
	public val `value`: Int,
) {
	WGPUPrimitiveTopology_PointList(0),
	WGPUPrimitiveTopology_LineList(1),
	WGPUPrimitiveTopology_LineStrip(2),
	WGPUPrimitiveTopology_TriangleList(3),
	WGPUPrimitiveTopology_TriangleStrip(4),
	WGPUPrimitiveTopology_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUPrimitiveTopology): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUPrimitiveTopology? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUQueryType(
	public val `value`: Int,
) {
	WGPUQueryType_Occlusion(0),
	WGPUQueryType_Timestamp(1),
	WGPUQueryType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUQueryType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUQueryType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUQueueWorkDoneStatus(
	public val `value`: Int,
) {
	WGPUQueueWorkDoneStatus_Success(0),
	WGPUQueueWorkDoneStatus_Error(1),
	WGPUQueueWorkDoneStatus_Unknown(2),
	WGPUQueueWorkDoneStatus_DeviceLost(3),
	WGPUQueueWorkDoneStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUQueueWorkDoneStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUQueueWorkDoneStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPURequestAdapterStatus(
	public val `value`: Int,
) {
	WGPURequestAdapterStatus_Success(0),
	WGPURequestAdapterStatus_Unavailable(1),
	WGPURequestAdapterStatus_Error(2),
	WGPURequestAdapterStatus_Unknown(3),
	WGPURequestAdapterStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPURequestAdapterStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPURequestAdapterStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPURequestDeviceStatus(
	public val `value`: Int,
) {
	WGPURequestDeviceStatus_Success(0),
	WGPURequestDeviceStatus_Error(1),
	WGPURequestDeviceStatus_Unknown(2),
	WGPURequestDeviceStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPURequestDeviceStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPURequestDeviceStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUSType(
	public val `value`: Int,
) {
	WGPUSType_Invalid(0),
	WGPUSType_SurfaceDescriptorFromMetalLayer(1),
	WGPUSType_SurfaceDescriptorFromWindowsHWND(2),
	WGPUSType_SurfaceDescriptorFromXlibWindow(3),
	WGPUSType_SurfaceDescriptorFromCanvasHTMLSelector(4),
	WGPUSType_ShaderModuleSPIRVDescriptor(5),
	WGPUSType_ShaderModuleWGSLDescriptor(6),
	WGPUSType_PrimitiveDepthClipControl(7),
	WGPUSType_SurfaceDescriptorFromWaylandSurface(8),
	WGPUSType_SurfaceDescriptorFromAndroidNativeWindow(9),
	WGPUSType_SurfaceDescriptorFromXcbWindow(10),
	WGPUSType_RenderPassDescriptorMaxDrawCount(15),
	WGPUSType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUSType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUSType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUSamplerBindingType(
	public val `value`: Int,
) {
	WGPUSamplerBindingType_Undefined(0),
	WGPUSamplerBindingType_Filtering(1),
	WGPUSamplerBindingType_NonFiltering(2),
	WGPUSamplerBindingType_Comparison(3),
	WGPUSamplerBindingType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUSamplerBindingType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUSamplerBindingType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUStencilOperation(
	public val `value`: Int,
) {
	WGPUStencilOperation_Keep(0),
	WGPUStencilOperation_Zero(1),
	WGPUStencilOperation_Replace(2),
	WGPUStencilOperation_Invert(3),
	WGPUStencilOperation_IncrementClamp(4),
	WGPUStencilOperation_DecrementClamp(5),
	WGPUStencilOperation_IncrementWrap(6),
	WGPUStencilOperation_DecrementWrap(7),
	WGPUStencilOperation_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUStencilOperation): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUStencilOperation? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUStorageTextureAccess(
	public val `value`: Int,
) {
	WGPUStorageTextureAccess_Undefined(0),
	WGPUStorageTextureAccess_WriteOnly(1),
	WGPUStorageTextureAccess_ReadOnly(2),
	WGPUStorageTextureAccess_ReadWrite(3),
	WGPUStorageTextureAccess_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUStorageTextureAccess): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUStorageTextureAccess? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUStoreOp(
	public val `value`: Int,
) {
	WGPUStoreOp_Undefined(0),
	WGPUStoreOp_Store(1),
	WGPUStoreOp_Discard(2),
	WGPUStoreOp_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUStoreOp): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUStoreOp? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUSurfaceGetCurrentTextureStatus(
	public val `value`: Int,
) {
	WGPUSurfaceGetCurrentTextureStatus_Success(0),
	WGPUSurfaceGetCurrentTextureStatus_Timeout(1),
	WGPUSurfaceGetCurrentTextureStatus_Outdated(2),
	WGPUSurfaceGetCurrentTextureStatus_Lost(3),
	WGPUSurfaceGetCurrentTextureStatus_OutOfMemory(4),
	WGPUSurfaceGetCurrentTextureStatus_DeviceLost(5),
	WGPUSurfaceGetCurrentTextureStatus_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUSurfaceGetCurrentTextureStatus): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUSurfaceGetCurrentTextureStatus? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUTextureAspect(
	public val `value`: Int,
) {
	WGPUTextureAspect_All(0),
	WGPUTextureAspect_StencilOnly(1),
	WGPUTextureAspect_DepthOnly(2),
	WGPUTextureAspect_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUTextureAspect): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUTextureAspect? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUTextureDimension(
	public val `value`: Int,
) {
	WGPUTextureDimension_1D(0),
	WGPUTextureDimension_2D(1),
	WGPUTextureDimension_3D(2),
	WGPUTextureDimension_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUTextureDimension): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUTextureDimension? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUTextureFormat(
	public val `value`: Int,
) {
	WGPUTextureFormat_Undefined(0),
	WGPUTextureFormat_R8Unorm(1),
	WGPUTextureFormat_R8Snorm(2),
	WGPUTextureFormat_R8Uint(3),
	WGPUTextureFormat_R8Sint(4),
	WGPUTextureFormat_R16Uint(5),
	WGPUTextureFormat_R16Sint(6),
	WGPUTextureFormat_R16Float(7),
	WGPUTextureFormat_RG8Unorm(8),
	WGPUTextureFormat_RG8Snorm(9),
	WGPUTextureFormat_RG8Uint(10),
	WGPUTextureFormat_RG8Sint(11),
	WGPUTextureFormat_R32Float(12),
	WGPUTextureFormat_R32Uint(13),
	WGPUTextureFormat_R32Sint(14),
	WGPUTextureFormat_RG16Uint(15),
	WGPUTextureFormat_RG16Sint(16),
	WGPUTextureFormat_RG16Float(17),
	WGPUTextureFormat_RGBA8Unorm(18),
	WGPUTextureFormat_RGBA8UnormSrgb(19),
	WGPUTextureFormat_RGBA8Snorm(20),
	WGPUTextureFormat_RGBA8Uint(21),
	WGPUTextureFormat_RGBA8Sint(22),
	WGPUTextureFormat_BGRA8Unorm(23),
	WGPUTextureFormat_BGRA8UnormSrgb(24),
	WGPUTextureFormat_RGB10A2Uint(25),
	WGPUTextureFormat_RGB10A2Unorm(26),
	WGPUTextureFormat_RG11B10Ufloat(27),
	WGPUTextureFormat_RGB9E5Ufloat(28),
	WGPUTextureFormat_RG32Float(29),
	WGPUTextureFormat_RG32Uint(30),
	WGPUTextureFormat_RG32Sint(31),
	WGPUTextureFormat_RGBA16Uint(32),
	WGPUTextureFormat_RGBA16Sint(33),
	WGPUTextureFormat_RGBA16Float(34),
	WGPUTextureFormat_RGBA32Float(35),
	WGPUTextureFormat_RGBA32Uint(36),
	WGPUTextureFormat_RGBA32Sint(37),
	WGPUTextureFormat_Stencil8(38),
	WGPUTextureFormat_Depth16Unorm(39),
	WGPUTextureFormat_Depth24Plus(40),
	WGPUTextureFormat_Depth24PlusStencil8(41),
	WGPUTextureFormat_Depth32Float(42),
	WGPUTextureFormat_Depth32FloatStencil8(43),
	WGPUTextureFormat_BC1RGBAUnorm(44),
	WGPUTextureFormat_BC1RGBAUnormSrgb(45),
	WGPUTextureFormat_BC2RGBAUnorm(46),
	WGPUTextureFormat_BC2RGBAUnormSrgb(47),
	WGPUTextureFormat_BC3RGBAUnorm(48),
	WGPUTextureFormat_BC3RGBAUnormSrgb(49),
	WGPUTextureFormat_BC4RUnorm(50),
	WGPUTextureFormat_BC4RSnorm(51),
	WGPUTextureFormat_BC5RGUnorm(52),
	WGPUTextureFormat_BC5RGSnorm(53),
	WGPUTextureFormat_BC6HRGBUfloat(54),
	WGPUTextureFormat_BC6HRGBFloat(55),
	WGPUTextureFormat_BC7RGBAUnorm(56),
	WGPUTextureFormat_BC7RGBAUnormSrgb(57),
	WGPUTextureFormat_ETC2RGB8Unorm(58),
	WGPUTextureFormat_ETC2RGB8UnormSrgb(59),
	WGPUTextureFormat_ETC2RGB8A1Unorm(60),
	WGPUTextureFormat_ETC2RGB8A1UnormSrgb(61),
	WGPUTextureFormat_ETC2RGBA8Unorm(62),
	WGPUTextureFormat_ETC2RGBA8UnormSrgb(63),
	WGPUTextureFormat_EACR11Unorm(64),
	WGPUTextureFormat_EACR11Snorm(65),
	WGPUTextureFormat_EACRG11Unorm(66),
	WGPUTextureFormat_EACRG11Snorm(67),
	WGPUTextureFormat_ASTC4x4Unorm(68),
	WGPUTextureFormat_ASTC4x4UnormSrgb(69),
	WGPUTextureFormat_ASTC5x4Unorm(70),
	WGPUTextureFormat_ASTC5x4UnormSrgb(71),
	WGPUTextureFormat_ASTC5x5Unorm(72),
	WGPUTextureFormat_ASTC5x5UnormSrgb(73),
	WGPUTextureFormat_ASTC6x5Unorm(74),
	WGPUTextureFormat_ASTC6x5UnormSrgb(75),
	WGPUTextureFormat_ASTC6x6Unorm(76),
	WGPUTextureFormat_ASTC6x6UnormSrgb(77),
	WGPUTextureFormat_ASTC8x5Unorm(78),
	WGPUTextureFormat_ASTC8x5UnormSrgb(79),
	WGPUTextureFormat_ASTC8x6Unorm(80),
	WGPUTextureFormat_ASTC8x6UnormSrgb(81),
	WGPUTextureFormat_ASTC8x8Unorm(82),
	WGPUTextureFormat_ASTC8x8UnormSrgb(83),
	WGPUTextureFormat_ASTC10x5Unorm(84),
	WGPUTextureFormat_ASTC10x5UnormSrgb(85),
	WGPUTextureFormat_ASTC10x6Unorm(86),
	WGPUTextureFormat_ASTC10x6UnormSrgb(87),
	WGPUTextureFormat_ASTC10x8Unorm(88),
	WGPUTextureFormat_ASTC10x8UnormSrgb(89),
	WGPUTextureFormat_ASTC10x10Unorm(90),
	WGPUTextureFormat_ASTC10x10UnormSrgb(91),
	WGPUTextureFormat_ASTC12x10Unorm(92),
	WGPUTextureFormat_ASTC12x10UnormSrgb(93),
	WGPUTextureFormat_ASTC12x12Unorm(94),
	WGPUTextureFormat_ASTC12x12UnormSrgb(95),
	WGPUTextureFormat_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUTextureFormat): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUTextureFormat? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUTextureSampleType(
	public val `value`: Int,
) {
	WGPUTextureSampleType_Undefined(0),
	WGPUTextureSampleType_Float(1),
	WGPUTextureSampleType_UnfilterableFloat(2),
	WGPUTextureSampleType_Depth(3),
	WGPUTextureSampleType_Sint(4),
	WGPUTextureSampleType_Uint(5),
	WGPUTextureSampleType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUTextureSampleType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUTextureSampleType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUTextureViewDimension(
	public val `value`: Int,
) {
	WGPUTextureViewDimension_Undefined(0),
	WGPUTextureViewDimension_1D(1),
	WGPUTextureViewDimension_2D(2),
	WGPUTextureViewDimension_2DArray(3),
	WGPUTextureViewDimension_Cube(4),
	WGPUTextureViewDimension_CubeArray(5),
	WGPUTextureViewDimension_3D(6),
	WGPUTextureViewDimension_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUTextureViewDimension): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUTextureViewDimension? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUVertexFormat(
	public val `value`: Int,
) {
	WGPUVertexFormat_Undefined(0),
	WGPUVertexFormat_Uint8x2(1),
	WGPUVertexFormat_Uint8x4(2),
	WGPUVertexFormat_Sint8x2(3),
	WGPUVertexFormat_Sint8x4(4),
	WGPUVertexFormat_Unorm8x2(5),
	WGPUVertexFormat_Unorm8x4(6),
	WGPUVertexFormat_Snorm8x2(7),
	WGPUVertexFormat_Snorm8x4(8),
	WGPUVertexFormat_Uint16x2(9),
	WGPUVertexFormat_Uint16x4(10),
	WGPUVertexFormat_Sint16x2(11),
	WGPUVertexFormat_Sint16x4(12),
	WGPUVertexFormat_Unorm16x2(13),
	WGPUVertexFormat_Unorm16x4(14),
	WGPUVertexFormat_Snorm16x2(15),
	WGPUVertexFormat_Snorm16x4(16),
	WGPUVertexFormat_Float16x2(17),
	WGPUVertexFormat_Float16x4(18),
	WGPUVertexFormat_Float32(19),
	WGPUVertexFormat_Float32x2(20),
	WGPUVertexFormat_Float32x3(21),
	WGPUVertexFormat_Float32x4(22),
	WGPUVertexFormat_Uint32(23),
	WGPUVertexFormat_Uint32x2(24),
	WGPUVertexFormat_Uint32x3(25),
	WGPUVertexFormat_Uint32x4(26),
	WGPUVertexFormat_Sint32(27),
	WGPUVertexFormat_Sint32x2(28),
	WGPUVertexFormat_Sint32x3(29),
	WGPUVertexFormat_Sint32x4(30),
	WGPUVertexFormat_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUVertexFormat): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUVertexFormat? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUVertexStepMode(
	public val `value`: Int,
) {
	WGPUVertexStepMode_Vertex(0),
	WGPUVertexStepMode_Instance(1),
	WGPUVertexStepMode_VertexBufferNotUsed(2),
	WGPUVertexStepMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUVertexStepMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUVertexStepMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUBufferUsage(
	public val `value`: Int,
) {
	WGPUBufferUsage_None(0),
	WGPUBufferUsage_MapRead(1),
	WGPUBufferUsage_MapWrite(2),
	WGPUBufferUsage_CopySrc(4),
	WGPUBufferUsage_CopyDst(8),
	WGPUBufferUsage_Index(16),
	WGPUBufferUsage_Vertex(32),
	WGPUBufferUsage_Uniform(64),
	WGPUBufferUsage_Storage(128),
	WGPUBufferUsage_Indirect(256),
	WGPUBufferUsage_QueryResolve(512),
	WGPUBufferUsage_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUBufferUsage): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUBufferUsage? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUColorWriteMask(
	public val `value`: Int,
) {
	WGPUColorWriteMask_None(0),
	WGPUColorWriteMask_Red(1),
	WGPUColorWriteMask_Green(2),
	WGPUColorWriteMask_Blue(4),
	WGPUColorWriteMask_Alpha(8),
	WGPUColorWriteMask_All(15),
	WGPUColorWriteMask_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUColorWriteMask): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUColorWriteMask? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUMapMode(
	public val `value`: Int,
) {
	WGPUMapMode_None(0),
	WGPUMapMode_Read(1),
	WGPUMapMode_Write(2),
	WGPUMapMode_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUMapMode): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUMapMode? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUShaderStage(
	public val `value`: Int,
) {
	WGPUShaderStage_None(0),
	WGPUShaderStage_Vertex(1),
	WGPUShaderStage_Fragment(2),
	WGPUShaderStage_Compute(4),
	WGPUShaderStage_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUShaderStage): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUShaderStage? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUTextureUsage(
	public val `value`: Int,
) {
	WGPUTextureUsage_None(0),
	WGPUTextureUsage_CopySrc(1),
	WGPUTextureUsage_CopyDst(2),
	WGPUTextureUsage_TextureBinding(4),
	WGPUTextureUsage_StorageBinding(8),
	WGPUTextureUsage_RenderAttachment(16),
	WGPUTextureUsage_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUTextureUsage): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUTextureUsage? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUNativeSType(
	public val `value`: Int,
) {
	WGPUSType_DeviceExtras(196_609),
	WGPUSType_RequiredLimitsExtras(196_610),
	WGPUSType_PipelineLayoutExtras(196_611),
	WGPUSType_ShaderModuleGLSLDescriptor(196_612),
	WGPUSType_SupportedLimitsExtras(196_613),
	WGPUSType_InstanceExtras(196_614),
	WGPUSType_BindGroupEntryExtras(196_615),
	WGPUSType_BindGroupLayoutEntryExtras(196_616),
	WGPUSType_QuerySetDescriptorExtras(196_617),
	WGPUSType_SurfaceConfigurationExtras(196_618),
	WGPUNativeSType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUNativeSType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUNativeSType? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUNativeFeature(
	public val `value`: Int,
) {
	WGPUNativeFeature_PushConstants(196_609),
	WGPUNativeFeature_TextureAdapterSpecificFormatFeatures(196_610),
	WGPUNativeFeature_MultiDrawIndirect(196_611),
	WGPUNativeFeature_MultiDrawIndirectCount(196_612),
	WGPUNativeFeature_VertexWritableStorage(196_613),
	WGPUNativeFeature_TextureBindingArray(196_614),
	WGPUNativeFeature_SampledTextureAndStorageBufferArrayNonUniformIndexing(196_615),
	WGPUNativeFeature_PipelineStatisticsQuery(196_616),
	WGPUNativeFeature_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUNativeFeature): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUNativeFeature? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPULogLevel(
	public val `value`: Int,
) {
	WGPULogLevel_Off(0),
	WGPULogLevel_Error(1),
	WGPULogLevel_Warn(2),
	WGPULogLevel_Info(3),
	WGPULogLevel_Debug(4),
	WGPULogLevel_Trace(5),
	WGPULogLevel_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPULogLevel): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPULogLevel? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUInstanceBackend(
	public val `value`: Int,
) {
	WGPUInstanceBackend_All(0),
	WGPUInstanceBackend_Vulkan(1),
	WGPUInstanceBackend_GL(2),
	WGPUInstanceBackend_Metal(4),
	WGPUInstanceBackend_DX12(8),
	WGPUInstanceBackend_DX11(16),
	WGPUInstanceBackend_BrowserWebGPU(32),
	WGPUInstanceBackend_Primary(45),
	WGPUInstanceBackend_Secondary(18),
	WGPUInstanceBackend_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUInstanceBackend): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUInstanceBackend? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUInstanceFlag(
	public val `value`: Int,
) {
	WGPUInstanceFlag_Default(0),
	WGPUInstanceFlag_Debug(1),
	WGPUInstanceFlag_Validation(2),
	WGPUInstanceFlag_DiscardHalLabels(4),
	WGPUInstanceFlag_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUInstanceFlag): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUInstanceFlag? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUDx12Compiler(
	public val `value`: Int,
) {
	WGPUDx12Compiler_Undefined(0),
	WGPUDx12Compiler_Fxc(1),
	WGPUDx12Compiler_Dxc(2),
	WGPUDx12Compiler_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUDx12Compiler): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUDx12Compiler? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUGles3MinorVersion(
	public val `value`: Int,
) {
	WGPUGles3MinorVersion_Automatic(0),
	WGPUGles3MinorVersion_Version0(1),
	WGPUGles3MinorVersion_Version1(2),
	WGPUGles3MinorVersion_Version2(3),
	WGPUGles3MinorVersion_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUGles3MinorVersion): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUGles3MinorVersion? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUPipelineStatisticName(
	public val `value`: Int,
) {
	WGPUPipelineStatisticName_VertexShaderInvocations(0),
	WGPUPipelineStatisticName_ClipperInvocations(1),
	WGPUPipelineStatisticName_ClipperPrimitivesOut(2),
	WGPUPipelineStatisticName_FragmentShaderInvocations(3),
	WGPUPipelineStatisticName_ComputeShaderInvocations(4),
	WGPUPipelineStatisticName_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUPipelineStatisticName): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUPipelineStatisticName? = entries.find {
			it.value == value
		}
	}
}

public enum class WGPUNativeQueryType(
	public val `value`: Int,
) {
	WGPUNativeQueryType_PipelineStatistics(196_608),
	WGPUNativeQueryType_Force32(2_147_483_647),
	;

	public infix fun or(other: Int): Int = value or other

	public infix fun or(other: WGPUNativeQueryType): Int = value or other.value

	public companion object {
		public fun of(`value`: Int): WGPUNativeQueryType? = entries.find {
			it.value == value
		}
	}
}
