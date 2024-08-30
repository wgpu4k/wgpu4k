package io.ygdrasil.wgpu.internal.jna

import java.lang.foreign.ValueLayout

object wgpu_h {


    @JvmField
    val C_BOOL: ValueLayout = ValueLayout.JAVA_BOOLEAN

    @JvmField
    val C_CHAR: ValueLayout = ValueLayout.JAVA_BYTE

    @JvmField
    val C_SHORT: ValueLayout = ValueLayout.JAVA_SHORT

    @JvmField
    val C_INT: ValueLayout = ValueLayout.JAVA_INT

    @JvmField
    val C_LONG_LONG: ValueLayout = ValueLayout.JAVA_LONG

    @JvmField
    val C_FLOAT: ValueLayout = ValueLayout.JAVA_FLOAT

    @JvmField
    val C_DOUBLE: ValueLayout = ValueLayout.JAVA_DOUBLE

    @JvmField
    val C_POINTER: ValueLayout = ValueLayout.ADDRESS

    @JvmField
    val C_LONG: ValueLayout = ValueLayout.JAVA_LONG

    private
    const val WGPUSType_DeviceExtras: Int = 196609L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_DeviceExtras = 196609
     * * }
     */
    fun WGPUSType_DeviceExtras(): Int {
        return WGPUSType_DeviceExtras
    }

    private
    const val WGPUSType_RequiredLimitsExtras: Int = 196610L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_RequiredLimitsExtras = 196610
     * * }
     */
    fun WGPUSType_RequiredLimitsExtras(): Int {
        return WGPUSType_RequiredLimitsExtras
    }

    private
    const val WGPUSType_PipelineLayoutExtras: Int = 196611L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_PipelineLayoutExtras = 196611
     * * }
     */
    fun WGPUSType_PipelineLayoutExtras(): Int {
        return WGPUSType_PipelineLayoutExtras
    }

    private
    const val WGPUSType_ShaderModuleGLSLDescriptor: Int = 196612L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_ShaderModuleGLSLDescriptor = 196612
     * * }
     */
    fun WGPUSType_ShaderModuleGLSLDescriptor(): Int {
        return WGPUSType_ShaderModuleGLSLDescriptor
    }

    private
    const val WGPUSType_SupportedLimitsExtras: Int = 196613L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_SupportedLimitsExtras = 196613
     * * }
     */
    fun WGPUSType_SupportedLimitsExtras(): Int {
        return WGPUSType_SupportedLimitsExtras
    }

    private
    const val WGPUSType_InstanceExtras: Int = 196614L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_InstanceExtras = 196614
     * * }
     */
    fun WGPUSType_InstanceExtras(): Int {
        return WGPUSType_InstanceExtras
    }

    private
    const val WGPUSType_BindGroupEntryExtras: Int = 196615L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_BindGroupEntryExtras = 196615
     * * }
     */
    fun WGPUSType_BindGroupEntryExtras(): Int {
        return WGPUSType_BindGroupEntryExtras
    }

    private
    const val WGPUSType_BindGroupLayoutEntryExtras: Int = 196616L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_BindGroupLayoutEntryExtras = 196616
     * * }
     */
    fun WGPUSType_BindGroupLayoutEntryExtras(): Int {
        return WGPUSType_BindGroupLayoutEntryExtras
    }

    private
    const val WGPUSType_QuerySetDescriptorExtras: Int = 196617L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_QuerySetDescriptorExtras = 196617
     * * }
     */
    fun WGPUSType_QuerySetDescriptorExtras(): Int {
        return WGPUSType_QuerySetDescriptorExtras
    }

    private
    const val WGPUSType_SurfaceConfigurationExtras: Int = 196618L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUSType_SurfaceConfigurationExtras = 196618
     * * }
     */
    fun WGPUSType_SurfaceConfigurationExtras(): Int {
        return WGPUSType_SurfaceConfigurationExtras
    }

    private
    const val WGPUNativeSType_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeSType.WGPUNativeSType_Force32 = 2147483647
     * * }
     */
    fun WGPUNativeSType_Force32(): Int {
        return WGPUNativeSType_Force32
    }

    private
    const val WGPUNativeFeature_PushConstants: Int = 196609L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_PushConstants = 196609
     * * }
     */
    fun WGPUNativeFeature_PushConstants(): Int {
        return WGPUNativeFeature_PushConstants
    }

    private
    const val WGPUNativeFeature_TextureAdapterSpecificFormatFeatures: Int = 196610L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_TextureAdapterSpecificFormatFeatures = 196610
     * * }
     */
    fun WGPUNativeFeature_TextureAdapterSpecificFormatFeatures(): Int {
        return WGPUNativeFeature_TextureAdapterSpecificFormatFeatures
    }

    private
    const val WGPUNativeFeature_MultiDrawIndirect: Int = 196611L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_MultiDrawIndirect = 196611
     * * }
     */
    fun WGPUNativeFeature_MultiDrawIndirect(): Int {
        return WGPUNativeFeature_MultiDrawIndirect
    }

    private
    const val WGPUNativeFeature_MultiDrawIndirectCount: Int = 196612L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_MultiDrawIndirectCount = 196612
     * * }
     */
    fun WGPUNativeFeature_MultiDrawIndirectCount(): Int {
        return WGPUNativeFeature_MultiDrawIndirectCount
    }

    private
    const val WGPUNativeFeature_VertexWritableStorage: Int = 196613L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_VertexWritableStorage = 196613
     * * }
     */
    fun WGPUNativeFeature_VertexWritableStorage(): Int {
        return WGPUNativeFeature_VertexWritableStorage
    }

    private
    const val WGPUNativeFeature_TextureBindingArray: Int = 196614L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_TextureBindingArray = 196614
     * * }
     */
    fun WGPUNativeFeature_TextureBindingArray(): Int {
        return WGPUNativeFeature_TextureBindingArray
    }

    private
    const val WGPUNativeFeature_SampledTextureAndStorageBufferArrayNonUniformIndexing: Int = 196615L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_SampledTextureAndStorageBufferArrayNonUniformIndexing = 196615
     * * }
     */
    fun WGPUNativeFeature_SampledTextureAndStorageBufferArrayNonUniformIndexing(): Int {
        return WGPUNativeFeature_SampledTextureAndStorageBufferArrayNonUniformIndexing
    }

    private
    const val WGPUNativeFeature_PipelineStatisticsQuery: Int = 196616L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_PipelineStatisticsQuery = 196616
     * * }
     */
    fun WGPUNativeFeature_PipelineStatisticsQuery(): Int {
        return WGPUNativeFeature_PipelineStatisticsQuery
    }

    private
    const val WGPUNativeFeature_StorageResourceBindingArray: Int = 196617L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_StorageResourceBindingArray = 196617
     * * }
     */
    fun WGPUNativeFeature_StorageResourceBindingArray(): Int {
        return WGPUNativeFeature_StorageResourceBindingArray
    }

    private
    const val WGPUNativeFeature_PartiallyBoundBindingArray: Int = 196618L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_PartiallyBoundBindingArray = 196618
     * * }
     */
    fun WGPUNativeFeature_PartiallyBoundBindingArray(): Int {
        return WGPUNativeFeature_PartiallyBoundBindingArray
    }

    private
    const val WGPUNativeFeature_TextureFormat16bitNorm: Int = 196619L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_TextureFormat16bitNorm = 196619
     * * }
     */
    fun WGPUNativeFeature_TextureFormat16bitNorm(): Int {
        return WGPUNativeFeature_TextureFormat16bitNorm
    }

    private
    const val WGPUNativeFeature_TextureCompressionAstcHdr: Int = 196620L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_TextureCompressionAstcHdr = 196620
     * * }
     */
    fun WGPUNativeFeature_TextureCompressionAstcHdr(): Int {
        return WGPUNativeFeature_TextureCompressionAstcHdr
    }

    private
    const val WGPUNativeFeature_MappablePrimaryBuffers: Int = 196622L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_MappablePrimaryBuffers = 196622
     * * }
     */
    fun WGPUNativeFeature_MappablePrimaryBuffers(): Int {
        return WGPUNativeFeature_MappablePrimaryBuffers
    }

    private
    const val WGPUNativeFeature_BufferBindingArray: Int = 196623L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_BufferBindingArray = 196623
     * * }
     */
    fun WGPUNativeFeature_BufferBindingArray(): Int {
        return WGPUNativeFeature_BufferBindingArray
    }

    private
    const val WGPUNativeFeature_UniformBufferAndStorageTextureArrayNonUniformIndexing: Int = 196624L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_UniformBufferAndStorageTextureArrayNonUniformIndexing = 196624
     * * }
     */
    fun WGPUNativeFeature_UniformBufferAndStorageTextureArrayNonUniformIndexing(): Int {
        return WGPUNativeFeature_UniformBufferAndStorageTextureArrayNonUniformIndexing
    }

    private
    const val WGPUNativeFeature_VertexAttribute64bit: Int = 196633L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_VertexAttribute64bit = 196633
     * * }
     */
    fun WGPUNativeFeature_VertexAttribute64bit(): Int {
        return WGPUNativeFeature_VertexAttribute64bit
    }

    private
    const val WGPUNativeFeature_TextureFormatNv12: Int = 196634L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_TextureFormatNv12 = 196634
     * * }
     */
    fun WGPUNativeFeature_TextureFormatNv12(): Int {
        return WGPUNativeFeature_TextureFormatNv12
    }

    private
    const val WGPUNativeFeature_RayTracingAccelerationStructure: Int = 196635L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_RayTracingAccelerationStructure = 196635
     * * }
     */
    fun WGPUNativeFeature_RayTracingAccelerationStructure(): Int {
        return WGPUNativeFeature_RayTracingAccelerationStructure
    }

    private
    const val WGPUNativeFeature_RayQuery: Int = 196636L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_RayQuery = 196636
     * * }
     */
    fun WGPUNativeFeature_RayQuery(): Int {
        return WGPUNativeFeature_RayQuery
    }

    private
    const val WGPUNativeFeature_ShaderF64: Int = 196637L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_ShaderF64 = 196637
     * * }
     */
    fun WGPUNativeFeature_ShaderF64(): Int {
        return WGPUNativeFeature_ShaderF64
    }

    private
    const val WGPUNativeFeature_ShaderI16: Int = 196638L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_ShaderI16 = 196638
     * * }
     */
    fun WGPUNativeFeature_ShaderI16(): Int {
        return WGPUNativeFeature_ShaderI16
    }

    private
    const val WGPUNativeFeature_ShaderPrimitiveIndex: Int = 196639L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_ShaderPrimitiveIndex = 196639
     * * }
     */
    fun WGPUNativeFeature_ShaderPrimitiveIndex(): Int {
        return WGPUNativeFeature_ShaderPrimitiveIndex
    }

    private
    const val WGPUNativeFeature_ShaderEarlyDepthTest: Int = 196640L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_ShaderEarlyDepthTest = 196640
     * * }
     */
    fun WGPUNativeFeature_ShaderEarlyDepthTest(): Int {
        return WGPUNativeFeature_ShaderEarlyDepthTest
    }

    private
    const val WGPUNativeFeature_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeFeature.WGPUNativeFeature_Force32 = 2147483647
     * * }
     */
    fun WGPUNativeFeature_Force32(): Int {
        return WGPUNativeFeature_Force32
    }

    private
    const val WGPULogLevel_Off: Int = 0L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Off = 0
     * * }
     */
    fun WGPULogLevel_Off(): Int {
        return WGPULogLevel_Off
    }

    private
    const val WGPULogLevel_Error: Int = 1L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Error = 1
     * * }
     */
    fun WGPULogLevel_Error(): Int {
        return WGPULogLevel_Error
    }

    private
    const val WGPULogLevel_Warn: Int = 2L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Warn = 2
     * * }
     */
    fun WGPULogLevel_Warn(): Int {
        return WGPULogLevel_Warn
    }

    private
    const val WGPULogLevel_Info: Int = 3L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Info = 3
     * * }
     */
    fun WGPULogLevel_Info(): Int {
        return WGPULogLevel_Info
    }

    private
    const val WGPULogLevel_Debug: Int = 4L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Debug = 4
     * * }
     */
    fun WGPULogLevel_Debug(): Int {
        return WGPULogLevel_Debug
    }

    private
    const val WGPULogLevel_Trace: Int = 5L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Trace = 5
     * * }
     */
    fun WGPULogLevel_Trace(): Int {
        return WGPULogLevel_Trace
    }

    private
    const val WGPULogLevel_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPULogLevel.WGPULogLevel_Force32 = 2147483647
     * * }
     */
    fun WGPULogLevel_Force32(): Int {
        return WGPULogLevel_Force32
    }

    private
    const val WGPUInstanceBackend_All: Int = 0L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_All = 0
     * * }
     */
    fun WGPUInstanceBackend_All(): Int {
        return WGPUInstanceBackend_All
    }

    private
    const val WGPUInstanceBackend_Vulkan: Int = 1L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_Vulkan = 1
     * * }
     */
    fun WGPUInstanceBackend_Vulkan(): Int {
        return WGPUInstanceBackend_Vulkan
    }

    private
    const val WGPUInstanceBackend_GL: Int = 2L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_GL = 2
     * * }
     */
    fun WGPUInstanceBackend_GL(): Int {
        return WGPUInstanceBackend_GL
    }

    private
    const val WGPUInstanceBackend_Metal: Int = 4L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_Metal = 4
     * * }
     */
    fun WGPUInstanceBackend_Metal(): Int {
        return WGPUInstanceBackend_Metal
    }

    private
    const val WGPUInstanceBackend_DX12: Int = 8L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_DX12 = 8
     * * }
     */
    fun WGPUInstanceBackend_DX12(): Int {
        return WGPUInstanceBackend_DX12
    }

    private
    const val WGPUInstanceBackend_DX11: Int = 16L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_DX11 = 16
     * * }
     */
    fun WGPUInstanceBackend_DX11(): Int {
        return WGPUInstanceBackend_DX11
    }

    private
    const val WGPUInstanceBackend_BrowserWebGPU: Int = 32L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_BrowserWebGPU = 32
     * * }
     */
    fun WGPUInstanceBackend_BrowserWebGPU(): Int {
        return WGPUInstanceBackend_BrowserWebGPU
    }

    private
    const val WGPUInstanceBackend_Primary: Int = 45L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_Primary = 45
     * * }
     */
    fun WGPUInstanceBackend_Primary(): Int {
        return WGPUInstanceBackend_Primary
    }

    private
    const val WGPUInstanceBackend_Secondary: Int = 18L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_Secondary = 18
     * * }
     */
    fun WGPUInstanceBackend_Secondary(): Int {
        return WGPUInstanceBackend_Secondary
    }

    private
    const val WGPUInstanceBackend_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceBackend.WGPUInstanceBackend_Force32 = 2147483647
     * * }
     */
    fun WGPUInstanceBackend_Force32(): Int {
        return WGPUInstanceBackend_Force32
    }
    /**
     * {@snippet lang=c :
     * * typedef WGPUFlags WGPUInstanceBackendFlags
     * * }
     */
    val WGPUInstanceBackendFlags: ValueLayout = C_INT

    private
    const val WGPUInstanceFlag_Default: Int = 0L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceFlag.WGPUInstanceFlag_Default = 0
     * * }
     */
    fun WGPUInstanceFlag_Default(): Int {
        return WGPUInstanceFlag_Default
    }

    private
    const val WGPUInstanceFlag_Debug: Int = 1L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceFlag.WGPUInstanceFlag_Debug = 1
     * * }
     */
    fun WGPUInstanceFlag_Debug(): Int {
        return WGPUInstanceFlag_Debug
    }

    private
    const val WGPUInstanceFlag_Validation: Int = 2L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceFlag.WGPUInstanceFlag_Validation = 2
     * * }
     */
    fun WGPUInstanceFlag_Validation(): Int {
        return WGPUInstanceFlag_Validation
    }

    private
    const val WGPUInstanceFlag_DiscardHalLabels: Int = 4L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceFlag.WGPUInstanceFlag_DiscardHalLabels = 4
     * * }
     */
    fun WGPUInstanceFlag_DiscardHalLabels(): Int {
        return WGPUInstanceFlag_DiscardHalLabels
    }

    private
    const val WGPUInstanceFlag_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUInstanceFlag.WGPUInstanceFlag_Force32 = 2147483647
     * * }
     */
    fun WGPUInstanceFlag_Force32(): Int {
        return WGPUInstanceFlag_Force32
    }
    /**
     * {@snippet lang=c :
     * * typedef WGPUFlags WGPUInstanceFlags
     * * }
     */

    val WGPUInstanceFlags: ValueLayout = C_INT

    private
    const val WGPUDx12Compiler_Undefined: Int = 0L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUDx12Compiler.WGPUDx12Compiler_Undefined = 0
     * * }
     */
    fun WGPUDx12Compiler_Undefined(): Int {
        return WGPUDx12Compiler_Undefined
    }

    private
    const val WGPUDx12Compiler_Fxc: Int = 1L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUDx12Compiler.WGPUDx12Compiler_Fxc = 1
     * * }
     */
    fun WGPUDx12Compiler_Fxc(): Int {
        return WGPUDx12Compiler_Fxc
    }

    private
    const val WGPUDx12Compiler_Dxc: Int = 2L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUDx12Compiler.WGPUDx12Compiler_Dxc = 2
     * * }
     */
    fun WGPUDx12Compiler_Dxc(): Int {
        return WGPUDx12Compiler_Dxc
    }

    private
    const val WGPUDx12Compiler_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUDx12Compiler.WGPUDx12Compiler_Force32 = 2147483647
     * * }
     */
    fun WGPUDx12Compiler_Force32(): Int {
        return WGPUDx12Compiler_Force32
    }

    private
    const val WGPUGles3MinorVersion_Automatic: Int = 0L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUGles3MinorVersion.WGPUGles3MinorVersion_Automatic = 0
     * * }
     */
    fun WGPUGles3MinorVersion_Automatic(): Int {
        return WGPUGles3MinorVersion_Automatic
    }

    private
    const val WGPUGles3MinorVersion_Version0: Int = 1L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUGles3MinorVersion.WGPUGles3MinorVersion_Version0 = 1
     * * }
     */
    fun WGPUGles3MinorVersion_Version0(): Int {
        return WGPUGles3MinorVersion_Version0
    }

    private
    const val WGPUGles3MinorVersion_Version1: Int = 2L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUGles3MinorVersion.WGPUGles3MinorVersion_Version1 = 2
     * * }
     */
    fun WGPUGles3MinorVersion_Version1(): Int {
        return WGPUGles3MinorVersion_Version1
    }

    private
    const val WGPUGles3MinorVersion_Version2: Int = 3L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUGles3MinorVersion.WGPUGles3MinorVersion_Version2 = 3
     * * }
     */
    fun WGPUGles3MinorVersion_Version2(): Int {
        return WGPUGles3MinorVersion_Version2
    }

    private
    const val WGPUGles3MinorVersion_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUGles3MinorVersion.WGPUGles3MinorVersion_Force32 = 2147483647
     * * }
     */
    fun WGPUGles3MinorVersion_Force32(): Int {
        return WGPUGles3MinorVersion_Force32
    }

    private
    const val WGPUPipelineStatisticName_VertexShaderInvocations: Int = 0L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUPipelineStatisticName.WGPUPipelineStatisticName_VertexShaderInvocations = 0
     * * }
     */
    fun WGPUPipelineStatisticName_VertexShaderInvocations(): Int {
        return WGPUPipelineStatisticName_VertexShaderInvocations
    }

    private
    const val WGPUPipelineStatisticName_ClipperInvocations: Int = 1L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUPipelineStatisticName.WGPUPipelineStatisticName_ClipperInvocations = 1
     * * }
     */
    fun WGPUPipelineStatisticName_ClipperInvocations(): Int {
        return WGPUPipelineStatisticName_ClipperInvocations
    }

    private
    const val WGPUPipelineStatisticName_ClipperPrimitivesOut: Int = 2L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUPipelineStatisticName.WGPUPipelineStatisticName_ClipperPrimitivesOut = 2
     * * }
     */
    fun WGPUPipelineStatisticName_ClipperPrimitivesOut(): Int {
        return WGPUPipelineStatisticName_ClipperPrimitivesOut
    }

    private
    const val WGPUPipelineStatisticName_FragmentShaderInvocations: Int = 3L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUPipelineStatisticName.WGPUPipelineStatisticName_FragmentShaderInvocations = 3
     * * }
     */
    fun WGPUPipelineStatisticName_FragmentShaderInvocations(): Int {
        return WGPUPipelineStatisticName_FragmentShaderInvocations
    }

    private
    const val WGPUPipelineStatisticName_ComputeShaderInvocations: Int = 4L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUPipelineStatisticName.WGPUPipelineStatisticName_ComputeShaderInvocations = 4
     * * }
     */
    fun WGPUPipelineStatisticName_ComputeShaderInvocations(): Int {
        return WGPUPipelineStatisticName_ComputeShaderInvocations
    }

    private
    const val WGPUPipelineStatisticName_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUPipelineStatisticName.WGPUPipelineStatisticName_Force32 = 2147483647
     * * }
     */
    fun WGPUPipelineStatisticName_Force32(): Int {
        return WGPUPipelineStatisticName_Force32
    }

    private
    const val WGPUNativeQueryType_PipelineStatistics: Int = 196608L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeQueryType.WGPUNativeQueryType_PipelineStatistics = 196608
     * * }
     */
    fun WGPUNativeQueryType_PipelineStatistics(): Int {
        return WGPUNativeQueryType_PipelineStatistics
    }

    private
    const val WGPUNativeQueryType_Force32: Int = 2147483647L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeQueryType.WGPUNativeQueryType_Force32 = 2147483647
     * * }
     */
    fun WGPUNativeQueryType_Force32(): Int {
        return WGPUNativeQueryType_Force32
    }
    /**
     * {@snippet lang=c :
     * * typedef uint64_t WGPUSubmissionIndex
     * * }
     */
    val WGPUSubmissionIndex: ValueLayout = C_LONG_LONG

    private
    const val WGPUNativeTextureFormat_R16Unorm: Int = 196609L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeTextureFormat.WGPUNativeTextureFormat_R16Unorm = 196609
     * * }
     */
    fun WGPUNativeTextureFormat_R16Unorm(): Int {
        return WGPUNativeTextureFormat_R16Unorm
    }

    private
    const val WGPUNativeTextureFormat_R16Snorm: Int = 196610L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeTextureFormat.WGPUNativeTextureFormat_R16Snorm = 196610
     * * }
     */
    fun WGPUNativeTextureFormat_R16Snorm(): Int {
        return WGPUNativeTextureFormat_R16Snorm
    }

    private
    const val WGPUNativeTextureFormat_Rg16Unorm: Int = 196611L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeTextureFormat.WGPUNativeTextureFormat_Rg16Unorm = 196611
     * * }
     */
    fun WGPUNativeTextureFormat_Rg16Unorm(): Int {
        return WGPUNativeTextureFormat_Rg16Unorm
    }

    private
    const val WGPUNativeTextureFormat_Rg16Snorm: Int = 196612L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeTextureFormat.WGPUNativeTextureFormat_Rg16Snorm = 196612
     * * }
     */
    fun WGPUNativeTextureFormat_Rg16Snorm(): Int {
        return WGPUNativeTextureFormat_Rg16Snorm
    }

    private
    const val WGPUNativeTextureFormat_Rgba16Unorm: Int = 196613L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeTextureFormat.WGPUNativeTextureFormat_Rgba16Unorm = 196613
     * * }
     */
    fun WGPUNativeTextureFormat_Rgba16Unorm(): Int {
        return WGPUNativeTextureFormat_Rgba16Unorm
    }

    private
    const val WGPUNativeTextureFormat_Rgba16Snorm: Int = 196614L.toInt()

    /**
     * {@snippet lang=c :
     * * enum WGPUNativeTextureFormat.WGPUNativeTextureFormat_Rgba16Snorm = 196614
     * * }
     */
    fun WGPUNativeTextureFormat_Rgba16Snorm(): Int {
        return WGPUNativeTextureFormat_Rgba16Snorm
    }

    private
    const val WGPUNativeTextureFormat_NV12: Int = 196615L.toInt()

}