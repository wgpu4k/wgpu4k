package io.ygdrasil.wgpu.examples

import io.ygdrasil.wgpu.AutoClosableContext
import io.ygdrasil.wgpu.Device
import io.ygdrasil.wgpu.RenderingContext
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.TextureUsage
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.examples.scenes.basic.CubemapScene
import io.ygdrasil.wgpu.examples.scenes.basic.FractalCubeScene
import io.ygdrasil.wgpu.examples.scenes.basic.HelloTriangleMSAAScene
import io.ygdrasil.wgpu.examples.scenes.basic.HelloTriangleRotatingScene
import io.ygdrasil.wgpu.examples.scenes.basic.HelloTriangleScene
import io.ygdrasil.wgpu.examples.scenes.basic.InstancedCubeScene
import io.ygdrasil.wgpu.examples.scenes.basic.RotatingCubeScene
import io.ygdrasil.wgpu.examples.scenes.basic.TexturedCubeScene
import io.ygdrasil.wgpu.examples.scenes.basic.TwoCubesScene
import io.ygdrasil.wgpu.examples.scenes.graphics.techniques.SkinnedMeshScene

suspend fun loadScenes(wgpuContext: WGPUContext, resourceBasePath: String = ""): List<Scene> {
    val textureFormat = wgpuContext.renderingContext.textureFormat
    val assetManager = genericAssetManager(textureFormat, resourceBasePath)
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
        SkinnedMeshScene(wgpuContext, assetManager),
        // TODO: Not working test on wgpu new releases ParticlesScene(),
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
                usage = setOf(TextureUsage.renderAttachment),
            )
        ).also { with(autoClosableContext) { it.bind() } }
    }

    internal val device: Device
        get() = context.device

    internal val renderingContext: RenderingContext
        get() = context.renderingContext

    protected val autoClosableContext = AutoClosableContext()

    abstract suspend fun initialize()

    abstract suspend fun AutoClosableContext.render()

    override fun close() {
        autoClosableContext.close()
    }
}