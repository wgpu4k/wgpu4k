package io.ygdrasil.webgpu

data class SurfaceConfiguration(
    val device: GPUDevice,
    val format: GPUTextureFormat,
    val usage: GPUTextureUsage = GPUTextureUsage.RenderAttachment,
    val viewFormats: Set<GPUTextureFormat> = setOf(),
    val colorSpace: PredefinedColorSpace = PredefinedColorSpace.srgb,
    val alphaMode: CompositeAlphaMode = CompositeAlphaMode.Opaque,
    val presentMode: PresentMode = PresentMode.Fifo
)

data class SurfaceTexture(
    val texture: GPUTexture,
    val status: SurfaceTextureStatus
)


enum class PredefinedColorSpace(val value: String) {
    srgb("srgb"),
    displayp3("display-p3")
}

