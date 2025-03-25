package io.ygdrasil.webgpu

expect class Texture : GPUTexture {
    override val depthOrArrayLayers: GPUIntegerCoordinateOut
    override val dimension: GPUTextureDimension
    override val format: GPUTextureFormat
    override val height: GPUIntegerCoordinateOut
    override val mipLevelCount: GPUIntegerCoordinateOut
    override val sampleCount: GPUSize32Out
    override val usage: Set<GPUTextureUsage>
    override val width: GPUIntegerCoordinateOut
    override var label: String
    override fun createView(descriptor: GPUTextureViewDescriptor?): GPUTextureView
    override fun close()
}

// Todo double check this
fun GPUTextureFormat.getBytesPerPixel(): GPUSize32 {
    return when (this) {
        // Formats with 1 byte per pixel
        GPUTextureFormat.R8Unorm,
        GPUTextureFormat.R8Snorm,
        GPUTextureFormat.R8Uint,
        GPUTextureFormat.R8Sint,
        GPUTextureFormat.Stencil8,
            -> 1u

        // Formats with 2 bytes per pixel
        GPUTextureFormat.RG8Unorm,
        GPUTextureFormat.RG8Snorm,
        GPUTextureFormat.RG8Uint,
        GPUTextureFormat.RG8Sint,
        GPUTextureFormat.R16Uint,
        GPUTextureFormat.R16Sint,
        GPUTextureFormat.Depth16Unorm,
        GPUTextureFormat.R16Float,
            -> 2u

        // 24 bit depth is typically 3 bytes. But note that 'plus' might imply additional data
        GPUTextureFormat.Depth24Plus -> 3u

        // Formats with 3 or 4 bytes per pixel
        GPUTextureFormat.RGB10A2Uint,
        GPUTextureFormat.RGB10A2Unorm,
        GPUTextureFormat.R32Float,
        GPUTextureFormat.R32Uint,
        GPUTextureFormat.R32Sint,
        GPUTextureFormat.RG16Float,
        GPUTextureFormat.RGBA8Unorm,
        GPUTextureFormat.RGBA8UnormSrgb,
        GPUTextureFormat.RGBA8Snorm,
        GPUTextureFormat.RGBA8Uint,
        GPUTextureFormat.RGBA8Sint,
        GPUTextureFormat.BGRA8Unorm,
        GPUTextureFormat.BGRA8UnormSrgb,
        GPUTextureFormat.Depth24PlusStencil8,
        GPUTextureFormat.RG16Uint,
        GPUTextureFormat.RG16Sint,
        GPUTextureFormat.Depth32Float,
            -> 4u

        // 4 bytes for float, 1 byte for stencil
        GPUTextureFormat.Depth32FloatStencil8 -> 5u

        // Formats with 6 bytes per pixel
        GPUTextureFormat.RG11B10Ufloat,
        GPUTextureFormat.RGB9E5Ufloat,
            -> 6u

        // Formats with 4 bytes per pixel (compressed)
        GPUTextureFormat.ETC2RGB8Unorm,
        GPUTextureFormat.ETC2RGB8UnormSrgb,
        GPUTextureFormat.ETC2RGB8A1Unorm,
        GPUTextureFormat.ETC2RGB8A1UnormSrgb,
        GPUTextureFormat.ETC2RGBA8Unorm,
        GPUTextureFormat.ETC2RGBA8UnormSrgb,

            // Formats with variable bytes per pixel (ASTC)
        GPUTextureFormat.ASTC4x4Unorm,
        GPUTextureFormat.ASTC4x4UnormSrgb,
        GPUTextureFormat.ASTC5x4Unorm,
        GPUTextureFormat.ASTC5x4UnormSrgb,
        GPUTextureFormat.ASTC5x5Unorm,
        GPUTextureFormat.ASTC5x5UnormSrgb,
        GPUTextureFormat.ASTC6x5Unorm,
        GPUTextureFormat.ASTC6x5UnormSrgb,
        GPUTextureFormat.ASTC6x6Unorm,
        GPUTextureFormat.ASTC6x6UnormSrgb,
        GPUTextureFormat.ASTC8x5Unorm,
        GPUTextureFormat.ASTC8x5UnormSrgb,
        GPUTextureFormat.ASTC8x6Unorm,
        GPUTextureFormat.ASTC8x6UnormSrgb,
        GPUTextureFormat.ASTC8x8Unorm,
        GPUTextureFormat.ASTC8x8UnormSrgb,
        GPUTextureFormat.ASTC10x5Unorm,
        GPUTextureFormat.ASTC10x5UnormSrgb,
        GPUTextureFormat.ASTC10x6Unorm,
        GPUTextureFormat.ASTC10x6UnormSrgb,
        GPUTextureFormat.ASTC10x8Unorm,
        GPUTextureFormat.ASTC10x8UnormSrgb,
        GPUTextureFormat.ASTC10x10Unorm,
        GPUTextureFormat.ASTC10x10UnormSrgb,
        GPUTextureFormat.ASTC12x10Unorm,
        GPUTextureFormat.ASTC12x10UnormSrgb,
        GPUTextureFormat.ASTC12x12Unorm,
        GPUTextureFormat.ASTC12x12UnormSrgb,
            -> {
            TODO("Handle ASTC compressed formats with variable bytes per pixel")
        }

        // Formats with 8 bytes per pixel
        GPUTextureFormat.RG32Uint,
        GPUTextureFormat.RG32Sint,
        GPUTextureFormat.RG32Float,
        GPUTextureFormat.RGBA16Uint,
        GPUTextureFormat.RGBA16Sint,
        GPUTextureFormat.RGBA16Float,
            -> 8u

        // Formats with 16 bytes per pixel
        GPUTextureFormat.RGBA32Uint,
        GPUTextureFormat.RGBA32Sint,
        GPUTextureFormat.RGBA32Float,
            -> 16u

        // Compressed formats
        GPUTextureFormat.BC1RGBAUnorm,
        GPUTextureFormat.BC1RGBAUnormSrgb,
        GPUTextureFormat.BC2RGBAUnorm,
        GPUTextureFormat.BC2RGBAUnormSrgb,
        GPUTextureFormat.BC3RGBAUnorm,
        GPUTextureFormat.BC3RGBAUnormSrgb,
        GPUTextureFormat.BC4RUnorm,
        GPUTextureFormat.BC4RSnorm,
        GPUTextureFormat.BC5RGUnorm,
        GPUTextureFormat.BC5RGSnorm,
        GPUTextureFormat.BC6HRGBUfloat,
        GPUTextureFormat.BC6HRGBFloat,
        GPUTextureFormat.BC7RGBAUnorm,
        GPUTextureFormat.BC7RGBAUnormSrgb,
        GPUTextureFormat.EACR11Unorm,
        GPUTextureFormat.EACR11Snorm,
        GPUTextureFormat.EACRG11Unorm,
        GPUTextureFormat.EACRG11Snorm,
            -> 0u


        else -> error("Unknown GPUTextureFormat $this")
    }
}