package io.ygdrasil.webgpu

expect class Texture : GPUTexture

// Todo double check this
fun TextureFormat.getBytesPerPixel(): GPUSize32 {
    return when (this) {
        // Formats with 1 byte per pixel
        TextureFormat.R8Unorm,
        TextureFormat.R8Snorm,
        TextureFormat.R8Uint,
        TextureFormat.R8Sint,
        TextureFormat.Stencil8,
            -> 1u

        // Formats with 2 bytes per pixel
        TextureFormat.RG8Unorm,
        TextureFormat.RG8Snorm,
        TextureFormat.RG8Uint,
        TextureFormat.RG8Sint,
        TextureFormat.R16Uint,
        TextureFormat.R16Sint,
        TextureFormat.Depth16Unorm,
        TextureFormat.R16Float,
            -> 2u

        // 24 bit depth is typically 3 bytes. But note that 'plus' might imply additional data
        TextureFormat.Depth24Plus -> 3u

        // Formats with 3 or 4 bytes per pixel
        TextureFormat.RGB10A2Uint,
        TextureFormat.RGB10A2Unorm,
        TextureFormat.R32Float,
        TextureFormat.R32Uint,
        TextureFormat.R32Sint,
        TextureFormat.RG16Float,
        TextureFormat.RGBA8Unorm,
        TextureFormat.RGBA8UnormSrgb,
        TextureFormat.RGBA8Snorm,
        TextureFormat.RGBA8Uint,
        TextureFormat.RGBA8Sint,
        TextureFormat.BGRA8Unorm,
        TextureFormat.BGRA8UnormSrgb,
        TextureFormat.Depth24PlusStencil8,
        TextureFormat.RG16Uint,
        TextureFormat.RG16Sint,
        TextureFormat.Depth32Float,
            -> 4u

        // 4 bytes for float, 1 byte for stencil
        TextureFormat.Depth32FloatStencil8 -> 5u

        // Formats with 6 bytes per pixel
        TextureFormat.RG11B10Ufloat,
        TextureFormat.RGB9E5Ufloat,
            -> 6u

        // Formats with 4 bytes per pixel (compressed)
        TextureFormat.ETC2RGB8Unorm,
        TextureFormat.ETC2RGB8UnormSrgb,
        TextureFormat.ETC2RGB8A1Unorm,
        TextureFormat.ETC2RGB8A1UnormSrgb,
        TextureFormat.ETC2RGBA8Unorm,
        TextureFormat.ETC2RGBA8UnormSrgb,

            // Formats with variable bytes per pixel (ASTC)
        TextureFormat.ASTC4x4Unorm,
        TextureFormat.ASTC4x4UnormSrgb,
        TextureFormat.ASTC5x4Unorm,
        TextureFormat.ASTC5x4UnormSrgb,
        TextureFormat.ASTC5x5Unorm,
        TextureFormat.ASTC5x5UnormSrgb,
        TextureFormat.ASTC6x5Unorm,
        TextureFormat.ASTC6x5UnormSrgb,
        TextureFormat.ASTC6x6Unorm,
        TextureFormat.ASTC6x6UnormSrgb,
        TextureFormat.ASTC8x5Unorm,
        TextureFormat.ASTC8x5UnormSrgb,
        TextureFormat.ASTC8x6Unorm,
        TextureFormat.ASTC8x6UnormSrgb,
        TextureFormat.ASTC8x8Unorm,
        TextureFormat.ASTC8x8UnormSrgb,
        TextureFormat.ASTC10x5Unorm,
        TextureFormat.ASTC10x5UnormSrgb,
        TextureFormat.ASTC10x6Unorm,
        TextureFormat.ASTC10x6UnormSrgb,
        TextureFormat.ASTC10x8Unorm,
        TextureFormat.ASTC10x8UnormSrgb,
        TextureFormat.ASTC10x10Unorm,
        TextureFormat.ASTC10x10UnormSrgb,
        TextureFormat.ASTC12x10Unorm,
        TextureFormat.ASTC12x10UnormSrgb,
        TextureFormat.ASTC12x12Unorm,
        TextureFormat.ASTC12x12UnormSrgb,
            -> {
            TODO("Handle ASTC compressed formats with variable bytes per pixel")
        }

        // Formats with 8 bytes per pixel
        TextureFormat.RG32Uint,
        TextureFormat.RG32Sint,
        TextureFormat.RG32Float,
        TextureFormat.RGBA16Uint,
        TextureFormat.RGBA16Sint,
        TextureFormat.RGBA16Float,
            -> 8u

        // Formats with 16 bytes per pixel
        TextureFormat.RGBA32Uint,
        TextureFormat.RGBA32Sint,
        TextureFormat.RGBA32Float,
            -> 16u

        // Compressed formats
        TextureFormat.BC1RGBAUnorm,
        TextureFormat.BC1RGBAUnormSrgb,
        TextureFormat.BC2RGBAUnorm,
        TextureFormat.BC2RGBAUnormSrgb,
        TextureFormat.BC3RGBAUnorm,
        TextureFormat.BC3RGBAUnormSrgb,
        TextureFormat.BC4RUnorm,
        TextureFormat.BC4RSnorm,
        TextureFormat.BC5RGUnorm,
        TextureFormat.BC5RGSnorm,
        TextureFormat.BC6HRGBUfloat,
        TextureFormat.BC6HRGBFloat,
        TextureFormat.BC7RGBAUnorm,
        TextureFormat.BC7RGBAUnormSrgb,
        TextureFormat.EACR11Unorm,
        TextureFormat.EACR11Snorm,
        TextureFormat.EACRG11Unorm,
        TextureFormat.EACRG11Snorm,
            -> 0u


        else -> error("Unknown TextureFormat $this")
    }
}