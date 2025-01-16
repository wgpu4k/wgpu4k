@file:OptIn(DelicateCoroutinesApi::class)

package io.ygdrasil.webgpu.examples.scenes.graphics.techniques

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupDescriptor.BindGroupEntry
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.webgpu.Buffer
import io.ygdrasil.webgpu.BufferBindingType
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.BufferUsage
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.LoadOp
import io.ygdrasil.webgpu.RenderBundle
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.RenderPassDescriptor.ColorAttachment
import io.ygdrasil.webgpu.ShaderStage
import io.ygdrasil.webgpu.Size3D
import io.ygdrasil.webgpu.StoreOp
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.TextureFormat
import io.ygdrasil.webgpu.TextureUsage
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.beginRenderPass
import io.ygdrasil.webgpu.examples.AssetManager
import io.ygdrasil.webgpu.examples.Scene
import io.ygdrasil.webgpu.examples.helper.glb.ShaderCache
import io.ygdrasil.webgpu.examples.helper.glb.uploadGLBModel
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.math.PI


class SkinnedMeshScene(wgpuContext: WGPUContext, assetManager: AssetManager) : Scene(wgpuContext), AssetManager by assetManager {

    private val logger = KotlinLogging.logger {}

    internal lateinit var renderBundles: List<RenderBundle>
    internal lateinit var viewParamBuf: Buffer
    internal lateinit var projectionMatrix: Matrix4
    internal lateinit var renderPassDesc: RenderPassDescriptor
    internal lateinit var shaderCache: ShaderCache

    override suspend fun initialize() = with(autoClosableContext) {
        logger.info { "Initializing SkinnedMeshScene" }
        shaderCache = ShaderCache(device)

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(
                    width = renderingContext.width,
                    height = renderingContext.height,
                    depthOrArrayLayers = 1u
                ),
                format = TextureFormat.Depth24PlusStencil8,
                usage = setOf(TextureUsage.RenderAttachment)
            )
        ).bind()

        renderPassDesc = RenderPassDescriptor(
            colorAttachments = listOf(
                ColorAttachment(
                    view = dummyTexture.createView().bind(),
                    loadOp = LoadOp.Clear,
                    clearValue = Color(0.3, 0.3, 0.3, 1.0),
                    storeOp = StoreOp.Store
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.DepthStencilAttachment(
                view = depthTexture.createView().bind(),
                depthLoadOp = LoadOp.Clear,
                depthClearValue = 1f,
                depthStoreOp = StoreOp.Store,
                stencilLoadOp = LoadOp.Clear,
                stencilClearValue = 0u,
                stencilStoreOp = StoreOp.Store
            )
        )

        val viewParamsLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = listOf(
                    Entry(
                        binding = 0u,
                        visibility = setOf(ShaderStage.Vertex),
                        bindingType = Entry.BufferBindingLayout(type = BufferBindingType.Uniform)
                    )
                )
            )
        ).bind()

        viewParamBuf = device.createBuffer(
            BufferDescriptor(
                size = 4uL * 4uL * 4uL, usage = setOf(BufferUsage.Uniform, BufferUsage.CopyDst)
            )
        ).bind()

        val viewParamsBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = viewParamsLayout,
                entries = listOf(
                    BindGroupEntry(
                        binding = 0u,
                        resource = BindGroupDescriptor.BufferBinding(buffer = viewParamBuf)
                    )
                )
            )
        ).bind()

        logger.debug { "Loading box mesh" }
        val model = uploadGLBModel(device, boxMesh, renderingContext.textureFormat)

        logger.debug { "Creating render bundles" }
        renderBundles = model.buildRenderBundles(
            device,
            shaderCache,
            viewParamsLayout,
            viewParamsBindGroup,
            renderingContext.textureFormat,
        )

        projectionMatrix = getProjectionMatrix(renderingContext.width, renderingContext.height)
        logger.info { "SkinnedMeshScene initialized" }
    }

    override suspend fun AutoClosableContext.render() {

        val renderPassDesc = renderPassDesc.copy(
            colorAttachments = listOf(
                renderPassDesc.colorAttachments[0].copy(
                    view = renderingContext.getCurrentTexture().createView().bind()
                )
            )
        )

        val transformationMatrix = getTransformationMatrix(
            frame / 120.0,
            projectionMatrix
        )

        device.queue.writeBuffer(
            viewParamBuf,
            0uL,
            transformationMatrix,
            0uL,
            transformationMatrix.size.toULong()
        )

        val commandEncoder = device.createCommandEncoder().bind()

        commandEncoder.beginRenderPass(renderPassDesc) {
            executeBundles(renderBundles)
            end()
        }

        device.queue.submit(listOf(commandEncoder.finish()))

    }


    fun getProjectionMatrix(width: UInt, height: UInt): Matrix4 {
        val aspect = width.toDouble() / height.toDouble()
        val fox = Angle.fromRadians((2 * PI) / 5)
        return Matrix4.perspective(fox, aspect, 1.0, 100.0)
    }

    fun getTransformationMatrix(angle: Double, projectionMatrix: Matrix4): FloatArray {
        var viewMatrix = Matrix4.IDENTITY
        viewMatrix = viewMatrix.translated(0, 0, -4)

        viewMatrix = viewMatrix.rotated(
            Angle.fromRadians(Angle.fromRadians(angle).sine),
            Angle.fromRadians(Angle.fromRadians(angle).cosine),
            Angle.fromRadians(0)
        )

        return (projectionMatrix * viewMatrix).copyToColumns()
    }

}