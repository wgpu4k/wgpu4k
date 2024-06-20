package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*

abstract class Scene(
    private val context: WGPUContext,
) : AutoCloseable {

    internal var frame = 0

    val dummyTexture by lazy {
        device.createTexture(
            TextureDescriptor(
                size = Size3D(1, 1),
                format = TextureFormat.depth24plus,
                usage = setOf(TextureUsage.renderattachment),
            )
        ).also { with(autoClosableContext) { it.bind() } }
    }

    internal val device: Device
        get() = context.device

    internal val renderingContext: RenderingContext
        get() = context.renderingContext

    protected val autoClosableContext = AutoClosableContext()

    abstract suspend fun initialize()

    abstract fun AutoClosableContext.render()

    override fun close() {
        autoClosableContext.close()
    }
}