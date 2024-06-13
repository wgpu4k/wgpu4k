

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

    override fun close()
}

/**
 * @see https://www.w3.org/TR/webgpu/#gputexturedescriptor
 */
data class TextureDescriptor(
    var size: Size3D,
    var format: TextureFormat,
    var usage: Set<TextureUsage>,
    var mipLevelCount: GPUIntegerCoordinate = 1,
    var sampleCount: GPUSize32 = 1,
    var dimension: TextureDimension = TextureDimension._2d,
    var viewFormats: Array<TextureFormat> = arrayOf(),
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