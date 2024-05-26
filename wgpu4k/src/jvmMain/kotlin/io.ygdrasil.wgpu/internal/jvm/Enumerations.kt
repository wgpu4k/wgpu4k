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
