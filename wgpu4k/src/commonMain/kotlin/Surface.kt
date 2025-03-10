package io.ygdrasil.webgpu

data class SurfaceConfiguration(
    val device: Device,
    val format: TextureFormat,
    val usage: Set<TextureUsage> = setOf(TextureUsage.RenderAttachment),
    val viewFormats: Set<TextureFormat> = setOf(),
    val colorSpace: PredefinedColorSpace = PredefinedColorSpace.srgb,
    val alphaMode: CompositeAlphaMode = CompositeAlphaMode.Opaque,
    val presentMode: PresentMode = PresentMode.Fifo
)

data class SurfaceTexture(
    val texture: Texture,
    val status: SurfaceTextureStatus
)


enum class PredefinedColorSpace(val value: String) {
    srgb("srgb"),
    displayp3("display-p3")
}

