package io.ygdrasil.wgpu

expect class Surface : AutoCloseable {

    val width: Int
    val height: Int

    /**
     * Only supported on browser, not using it *could* lead to bad performance
     */
    val preferredCanvasFormat: TextureFormat?
    val supportedFormats:Set<TextureFormat>
    val supportedAlphaMode: Set<CompositeAlphaMode>

    fun getCurrentTexture(): SurfaceTexture

    /**
     * Schedule this texture to be presented on the owning surface.
     *
     * Needs to be called after any work on the texture is scheduled via Queue::submit.
     *
     * Platform dependent behavior
     * On Wayland, present will attach a wl_buffer to the underlying wl_surface and commit the new surface state. If it is desired to do things such as request a frame callback, scale the surface using the viewporter or synchronize other double buffered state, then these operations should be done before the call to present.
     */
    fun present()

    fun configure(surfaceConfiguration: SurfaceConfiguration)

    override fun close()
}

data class SurfaceConfiguration(
    val device: Device,
    val format: TextureFormat,
    val usage: Set<TextureUsage> = setOf(TextureUsage.renderAttachment),
    val viewFormats: Set<TextureFormat> = setOf(),
    val colorSpace: PredefinedColorSpace = PredefinedColorSpace.srgb,
    val alphaMode: CompositeAlphaMode = CompositeAlphaMode.opaque,
)

data class SurfaceTexture(
    val texture: Texture,
    val status: SurfaceTextureStatus
)