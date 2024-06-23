package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.examples.scenes.basic.*

suspend fun loadScenes(wgpuContext: WGPUContext): List<Scene> {
    val assetManager = genericAssetManager()
    return listOf(
        HelloTriangleScene(wgpuContext),
        HelloTriangleMSAAScene(wgpuContext),
        HelloTriangleRotatingScene(wgpuContext),
        RotatingCubeScene(wgpuContext),
        TwoCubesScene(wgpuContext),
        CubemapScene(wgpuContext, assetManager),
        FractalCubeScene(wgpuContext),
        InstancedCubeScene(wgpuContext),
        TexturedCubeScene(wgpuContext, assetManager),
        // TODO: Not working test on wgpu new releases ParticlesScene(),
        // TODO: fix it SkinnedMeshScene(),
    )
}

        abstract class Scene(
    private val context: WGPUContext,
) : AutoCloseable {

    var frame = 0

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