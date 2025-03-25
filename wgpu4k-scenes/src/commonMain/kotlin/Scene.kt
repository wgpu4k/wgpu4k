package io.ygdrasil.webgpu.examples

import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.Extent3D
import io.ygdrasil.webgpu.GPUDevice
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.RenderingContext
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.examples.scenes.basic.CubemapScene
import io.ygdrasil.webgpu.examples.scenes.basic.FractalCubeScene
import io.ygdrasil.webgpu.examples.scenes.basic.HelloTriangleMSAAScene
import io.ygdrasil.webgpu.examples.scenes.basic.HelloTriangleRotatingScene
import io.ygdrasil.webgpu.examples.scenes.basic.HelloTriangleScene
import io.ygdrasil.webgpu.examples.scenes.basic.InstancedCubeScene
import io.ygdrasil.webgpu.examples.scenes.basic.RotatingCubeScene
import io.ygdrasil.webgpu.examples.scenes.basic.TexturedCubeScene
import io.ygdrasil.webgpu.examples.scenes.basic.TwoCubesScene
import io.ygdrasil.webgpu.examples.scenes.graphics.techniques.SkinnedMeshScene

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
                size = Extent3D(1u, 1u),
                format = GPUTextureFormat.Depth24Plus,
                usage = setOf(GPUTextureUsage.RenderAttachment),
            )
        ).also { with(autoClosableContext) { it.bind() } }
    }

    internal val device: GPUDevice
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