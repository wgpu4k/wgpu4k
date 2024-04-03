

package io.ygdrasil.wgpu

expect class Texture: AutoCloseable {

    val width: GPUIntegerCoordinateOut
    val height: GPUIntegerCoordinateOut
    val depthOrArrayLayers: GPUIntegerCoordinateOut
    val mipLevelCount: GPUIntegerCoordinateOut
    val sampleCount: GPUSize32Out
    val dimension: TextureDimension
    val format: TextureFormat
    val usage: GPUFlagsConstant

	fun createView(descriptor: TextureViewDescriptor? = null): TextureView
}

/**
 * @see https://www.w3.org/TR/webgpu/#gputexturedescriptor
 */
data class TextureDescriptor(
	var size: GPUExtent3DDictStrict,
	var format: TextureFormat,
	var usage: GPUTextureUsageFlags,
	/* Iterable<GPUIntegerCoordinate> | GPUExtent3DDictStrict */
	var mipLevelCount: GPUIntegerCoordinate = 1,

	var sampleCount: GPUSize32 = 1,
	var dimension: TextureDimension = TextureDimension._2d,
	/* "1d" | "2d" | "3d" */

	/* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */

	var viewFormats: Array<String?>? = null,
	/* "r8unorm" | "r8snorm" | "r8uint" | "r8sint" | "r16uint" | "r16sint" | "r16float" | "rg8unorm" | "rg8snorm" | "rg8uint" | "rg8sint" | "r32uint" | "r32sint" | "r32float" | "rg16uint" | "rg16sint" | "rg16float" | "rgba8unorm" | "rgba8unorm-srgb" | "rgba8snorm" | "rgba8uint" | "rgba8sint" | "bgra8unorm" | "bgra8unorm-srgb" | "rgb9e5ufloat" | "rgb10a2uint" | "rgb10a2unorm" | "rg11b10ufloat" | "rg32uint" | "rg32sint" | "rg32float" | "rgba16uint" | "rgba16sint" | "rgba16float" | "rgba32uint" | "rgba32sint" | "rgba32float" | "stencil8" | "depth16unorm" | "depth24plus" | "depth24plus-stencil8" | "depth32float" | "depth32float-stencil8" | "bc1-rgba-unorm" | "bc1-rgba-unorm-srgb" | "bc2-rgba-unorm" | "bc2-rgba-unorm-srgb" | "bc3-rgba-unorm" | "bc3-rgba-unorm-srgb" | "bc4-r-unorm" | "bc4-r-snorm" | "bc5-rg-unorm" | "bc5-rg-snorm" | "bc6h-rgb-ufloat" | "bc6h-rgb-float" | "bc7-rgba-unorm" | "bc7-rgba-unorm-srgb" | "etc2-rgb8unorm" | "etc2-rgb8unorm-srgb" | "etc2-rgb8a1unorm" | "etc2-rgb8a1unorm-srgb" | "etc2-rgba8unorm" | "etc2-rgba8unorm-srgb" | "eac-r11unorm" | "eac-r11snorm" | "eac-rg11unorm" | "eac-rg11snorm" | "astc-4x4-unorm" | "astc-4x4-unorm-srgb" | "astc-5x4-unorm" | "astc-5x4-unorm-srgb" | "astc-5x5-unorm" | "astc-5x5-unorm-srgb" | "astc-6x5-unorm" | "astc-6x5-unorm-srgb" | "astc-6x6-unorm" | "astc-6x6-unorm-srgb" | "astc-8x5-unorm" | "astc-8x5-unorm-srgb" | "astc-8x6-unorm" | "astc-8x6-unorm-srgb" | "astc-8x8-unorm" | "astc-8x8-unorm-srgb" | "astc-10x5-unorm" | "astc-10x5-unorm-srgb" | "astc-10x6-unorm" | "astc-10x6-unorm-srgb" | "astc-10x8-unorm" | "astc-10x8-unorm-srgb" | "astc-10x10-unorm" | "astc-10x10-unorm-srgb" | "astc-12x10-unorm" | "astc-12x10-unorm-srgb" | "astc-12x12-unorm" | "astc-12x12-unorm-srgb" */

	var label: String? = null
)

// Todo double check this
fun TextureFormat.getBytesPerPixel(): Int {
    return when (this) {
        // Formats with 1 byte per pixel
        TextureFormat.r8unorm,
        TextureFormat.r8snorm,
        TextureFormat.r8uint,
        TextureFormat.r8sint,
        TextureFormat.stencil8 -> 1

        // Formats with 2 bytes per pixel
        TextureFormat.rg8unorm,
        TextureFormat.rg8snorm,
        TextureFormat.rg8uint,
        TextureFormat.rg8sint,
        TextureFormat.r16uint,
        TextureFormat.r16sint,
        TextureFormat.depth16unorm,
        TextureFormat.r16float -> 2

        // 24 bit depth is typically 3 bytes. But note that 'plus' might imply additional data
        TextureFormat.depth24plus -> 3

        // Formats with 3 or 4 bytes per pixel
        TextureFormat.rgb10a2uint,
        TextureFormat.rgb10a2unorm,
        TextureFormat.r32float,
        TextureFormat.r32uint,
        TextureFormat.r32sint,
        TextureFormat.rg16float,
        TextureFormat.rgba8unorm,
        TextureFormat.rgba8unormsrgb,
        TextureFormat.rgba8snorm,
        TextureFormat.rgba8uint,
        TextureFormat.rgba8sint,
        TextureFormat.bgra8unorm,
        TextureFormat.bgra8unormsrgb,
        TextureFormat.depth24plusstencil8,
        TextureFormat.rg16uint,
        TextureFormat.rg16sint,
        TextureFormat.depth32float
        -> 4

        // 4 bytes for float, 1 byte for stencil
        TextureFormat.depth32floatstencil8 -> 5

        // Formats with 6 bytes per pixel
        TextureFormat.rg11b10ufloat,
        TextureFormat.rgb9e5ufloat -> 6

        // Formats with 4 bytes per pixel (compressed)
        TextureFormat.etc2rgb8unorm,
        TextureFormat.etc2rgb8unormsrgb,
        TextureFormat.etc2rgb8a1unorm,
        TextureFormat.etc2rgb8a1unormsrgb,
        TextureFormat.etc2rgba8unorm,
        TextureFormat.etc2rgba8unormsrgb,

            // Formats with variable bytes per pixel (ASTC)
        TextureFormat.astc4x4unorm,
        TextureFormat.astc4x4unormsrgb,
        TextureFormat.astc5x4unorm,
        TextureFormat.astc5x4unormsrgb,
        TextureFormat.astc5x5unorm,
        TextureFormat.astc5x5unormsrgb,
        TextureFormat.astc6x5unorm,
        TextureFormat.astc6x5unormsrgb,
        TextureFormat.astc6x6unorm,
        TextureFormat.astc6x6unormsrgb,
        TextureFormat.astc8x5unorm,
        TextureFormat.astc8x5unormsrgb,
        TextureFormat.astc8x6unorm,
        TextureFormat.astc8x6unormsrgb,
        TextureFormat.astc8x8unorm,
        TextureFormat.astc8x8unormsrgb,
        TextureFormat.astc10x5unorm,
        TextureFormat.astc10x5unormsrgb,
        TextureFormat.astc10x6unorm,
        TextureFormat.astc10x6unormsrgb,
        TextureFormat.astc10x8unorm,
        TextureFormat.astc10x8unormsrgb,
        TextureFormat.astc10x10unorm,
        TextureFormat.astc10x10unormsrgb,
        TextureFormat.astc12x10unorm,
        TextureFormat.astc12x10unormsrgb,
        TextureFormat.astc12x12unorm,
        TextureFormat.astc12x12unormsrgb,
        -> {
            // ASTC compressed formats have variable bytes per pixel depending on the block size and compression ratio.
            // You might need to handle this differently based on your specific needs (e.g., return a range or an approximation).
            TODO("Handle ASTC compressed formats with variable bytes per pixel")
        }

        // Formats with 8 bytes per pixel
        TextureFormat.rg32uint,
        TextureFormat.rg32sint,
        TextureFormat.rg32float,
        TextureFormat.rgba16uint,
        TextureFormat.rgba16sint,
        TextureFormat.rgba16float -> 8

        // Formats with 16 bytes per pixel
        TextureFormat.rgba32uint,
        TextureFormat.rgba32sint,
        TextureFormat.rgba32float -> 16

        // Compressed formats
        TextureFormat.bc1rgbaunorm,
        TextureFormat.bc1rgbaunormsrgb,
        TextureFormat.bc2rgbaunorm,
        TextureFormat.bc2rgbaunormsrgb,
        TextureFormat.bc3rgbaunorm,
        TextureFormat.bc3rgbaunormsrgb,
        TextureFormat.bc4runorm,
        TextureFormat.bc4rsnorm,
        TextureFormat.bc5rgunorm,
        TextureFormat.bc5rgsnorm,
        TextureFormat.bc6hrgbufloat,
        TextureFormat.bc6hrgbfloat,
        TextureFormat.bc7rgbaunorm,
        TextureFormat.bc7rgbaunormsrgb,
        TextureFormat.eacr11unorm,
        TextureFormat.eacr11snorm,
        TextureFormat.eacrg11unorm,
        TextureFormat.eacrg11snorm,
        -> -1


    }
}