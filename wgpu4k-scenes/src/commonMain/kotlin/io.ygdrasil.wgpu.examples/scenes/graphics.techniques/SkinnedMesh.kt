@file:OptIn(DelicateCoroutinesApi::class)

package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.AutoClosableContext
import io.ygdrasil.wgpu.BindGroupDescriptor
import io.ygdrasil.wgpu.BindGroupDescriptor.BindGroupEntry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.Buffer
import io.ygdrasil.wgpu.BufferBindingType
import io.ygdrasil.wgpu.BufferDescriptor
import io.ygdrasil.wgpu.BufferUsage
import io.ygdrasil.wgpu.Color
import io.ygdrasil.wgpu.LoadOp
import io.ygdrasil.wgpu.RenderBundle
import io.ygdrasil.wgpu.RenderPassDescriptor
import io.ygdrasil.wgpu.RenderPassDescriptor.ColorAttachment
import io.ygdrasil.wgpu.ShaderStage
import io.ygdrasil.wgpu.Size3D
import io.ygdrasil.wgpu.StoreOp
import io.ygdrasil.wgpu.TextureDescriptor
import io.ygdrasil.wgpu.TextureFormat
import io.ygdrasil.wgpu.TextureUsage
import io.ygdrasil.wgpu.WGPUContext
import io.ygdrasil.wgpu.beginRenderPass
import io.ygdrasil.wgpu.examples.AssetManager
import io.ygdrasil.wgpu.examples.Scene
import io.ygdrasil.wgpu.examples.helper.glb.ShaderCache
import io.ygdrasil.wgpu.examples.helper.glb.uploadGLBModel
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.math.PI

class SkinnedMeshScene(wgpuContext: WGPUContext, assetManager: AssetManager) : Scene(wgpuContext), AssetManager by assetManager {

    internal lateinit var renderBundles: List<RenderBundle>
    internal lateinit var viewParamBuf: Buffer
    internal lateinit var projectionMatrix: Matrix4
    internal lateinit var renderPassDesc: RenderPassDescriptor
    internal lateinit var shaderCache: ShaderCache

    override suspend fun initialize() = with(autoClosableContext) {

        shaderCache = ShaderCache(device)

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(
                    width = renderingContext.width,
                    height = renderingContext.height,
                    depthOrArrayLayers = 1u
                ),
                format = TextureFormat.depth24plusstencil8,
                usage = setOf(TextureUsage.renderAttachment)
            )
        ).bind()

        renderPassDesc = RenderPassDescriptor(
            colorAttachments = listOf(
                ColorAttachment(
                    view = dummyTexture.createView().bind(),
                    loadOp = LoadOp.clear,
                    clearValue = Color(0.3, 0.3, 0.3, 1.0),
                    storeOp = StoreOp.store
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.DepthStencilAttachment(
                view = depthTexture.createView().bind(),
                depthLoadOp = LoadOp.clear,
                depthClearValue = 1f,
                depthStoreOp = StoreOp.store,
                stencilLoadOp = LoadOp.clear,
                stencilClearValue = 0u,
                stencilStoreOp = StoreOp.store
            )
        )

        val viewParamsLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = listOf(
                    Entry(
                        binding = 0u,
                        visibility = setOf(ShaderStage.vertex),
                        bindingType = Entry.BufferBindingLayout(type = BufferBindingType.uniform)
                    )
                )
            )
        ).bind()

        viewParamBuf = device.createBuffer(
            BufferDescriptor(
                size = 4uL * 4uL * 4uL, usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
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

        val model = uploadGLBModel(device, boxMesh, renderingContext.textureFormat)

        renderBundles = model.buildRenderBundles(
            device,
            shaderCache,
            viewParamsLayout,
            viewParamsBindGroup,
            renderingContext.textureFormat.actualName,
        )

        projectionMatrix = getProjectionMatrix(renderingContext.width, renderingContext.height)
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