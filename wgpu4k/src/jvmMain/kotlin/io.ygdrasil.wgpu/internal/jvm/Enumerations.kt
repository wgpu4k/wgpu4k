package io.ygdrasil.wgpu.`internal`.jvm

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
