@file:OptIn(DelicateCoroutinesApi::class)

package io.ygdrasil.webgpu.examples.scenes.graphics.techniques

import io.github.oshai.kotlinlogging.KotlinLogging
import io.ygdrasil.webgpu.AutoClosableContext
import io.ygdrasil.webgpu.BindGroupDescriptor
import io.ygdrasil.webgpu.BindGroupEntry
import io.ygdrasil.webgpu.BindGroupLayoutDescriptor
import io.ygdrasil.webgpu.BindGroupLayoutEntry
import io.ygdrasil.webgpu.BufferBinding
import io.ygdrasil.webgpu.BufferBindingLayout
import io.ygdrasil.webgpu.BufferDescriptor
import io.ygdrasil.webgpu.Color
import io.ygdrasil.webgpu.Extent3D
import io.ygdrasil.webgpu.GPUBuffer
import io.ygdrasil.webgpu.GPUBufferBindingType
import io.ygdrasil.webgpu.GPUBufferUsage
import io.ygdrasil.webgpu.GPULoadOp
import io.ygdrasil.webgpu.GPURenderBundle
import io.ygdrasil.webgpu.GPURenderPassDescriptor
import io.ygdrasil.webgpu.GPUShaderStage
import io.ygdrasil.webgpu.GPUStoreOp
import io.ygdrasil.webgpu.GPUTextureFormat
import io.ygdrasil.webgpu.GPUTextureUsage
import io.ygdrasil.webgpu.RenderPassColorAttachment
import io.ygdrasil.webgpu.RenderPassDepthStencilAttachment
import io.ygdrasil.webgpu.RenderPassDescriptor
import io.ygdrasil.webgpu.TextureDescriptor
import io.ygdrasil.webgpu.WGPUContext
import io.ygdrasil.webgpu.asArraybuffer
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

    internal lateinit var renderBundles: List<GPURenderBundle>
    internal lateinit var viewParamBuf: GPUBuffer
    internal lateinit var projectionMatrix: Matrix4
    internal lateinit var renderPassDesc: GPURenderPassDescriptor
    internal lateinit var shaderCache: ShaderCache

    override suspend fun initialize() = with(autoClosableContext) {
        logger.info { "Initializing SkinnedMeshScene" }
        shaderCache = ShaderCache(device)

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Extent3D(
                    width = renderingContext.width,
                    height = renderingContext.height,
                    depthOrArrayLayers = 1u
                ),
                format = GPUTextureFormat.Depth24PlusStencil8,
                usage = setOf(GPUTextureUsage.RenderAttachment)
            )
        ).bind()

        renderPassDesc = RenderPassDescriptor(
            colorAttachments = listOf(
                RenderPassColorAttachment(
                    view = dummyTexture.createView().bind(),
                    loadOp = GPULoadOp.Clear,
                    clearValue = Color(0.3, 0.3, 0.3, 1.0),
                    storeOp = GPUStoreOp.Store
                )
            ),
            depthStencilAttachment = RenderPassDepthStencilAttachment(
                view = depthTexture.createView().bind(),
                depthLoadOp = GPULoadOp.Clear,
                depthClearValue = 1f,
                depthStoreOp = GPUStoreOp.Store,
                stencilLoadOp = GPULoadOp.Clear,
                stencilClearValue = 0u,
                stencilStoreOp = GPUStoreOp.Store
            )
        )

        val viewParamsLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = listOf(
                    BindGroupLayoutEntry(
                        binding = 0u,
                        visibility = setOf(GPUShaderStage.Vertex),
                        buffer = BufferBindingLayout(type = GPUBufferBindingType.Uniform)
                    )
                )
            )
        ).bind()

        viewParamBuf = device.createBuffer(
            BufferDescriptor(
                size = 4uL * 4uL * 4uL, usage = setOf(GPUBufferUsage.Uniform, GPUBufferUsage.CopyDst)
            )
        ).bind()

        val viewParamsBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = viewParamsLayout,
                entries = listOf(
                    BindGroupEntry(
                        binding = 0u,
                        resource = BufferBinding(buffer = viewParamBuf)
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

        val renderPassDesc = (renderPassDesc as RenderPassDescriptor).copy(
            colorAttachments = listOf(
                (renderPassDesc.colorAttachments[0] as RenderPassColorAttachment).copy(
                    view = renderingContext.getCurrentTexture().createView().bind()
                )
            )
        )

        val transformationMatrix = getTransformationMatrix(
            frame / 120.0,
            projectionMatrix
        )

        transformationMatrix.asArraybuffer {
            device.queue.writeBuffer(viewParamBuf, 0uL, it)
        }

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