package io.ygdrasil.webgpu

expect class Surface: AutoCloseable {

    val width: UInt
    val height: UInt

    val preferredCanvasFormat: TextureFormat?

    val supportedFormats: Set<TextureFormat>
    val supportedAlphaMode: Set<CompositeAlphaMode>

    fun getCurrentTexture(): SurfaceTexture

    fun present()

    fun configure(surfaceConfiguration: SurfaceConfiguration)

    override fun close()
}