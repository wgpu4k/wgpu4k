@file:OptIn(DelicateCoroutinesApi::class)

package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.BindGroupEntry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.RenderPassDescriptor.ColorAttachment
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.GenericAssetManager
import io.ygdrasil.wgpu.examples.helper.glb.ShaderCache
import io.ygdrasil.wgpu.examples.helper.glb.uploadGLBModel
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlin.math.PI

class SkinnedMeshScene(wgpuContext: WGPUContext, assetManager: GenericAssetManager) : Application.Scene(wgpuContext, assetManager) {

    internal lateinit var renderBundles: Array<RenderBundle>
    internal lateinit var viewParamBuf: Buffer
    internal lateinit var projectionMatrix: Matrix4
    internal lateinit var renderPassDesc: RenderPassDescriptor
    internal lateinit var shaderCache: ShaderCache

    override suspend fun initialize() = with(autoClosableContext) {

        shaderCache = ShaderCache(device)

        val dummyTexture by lazy {
            device.createTexture(
                TextureDescriptor(
                    size = Size3D(1, 1),
                    format = TextureFormat.depth24plus,
                    usage = setOf(TextureUsage.renderattachment),
                )
            )
        }

        val depthTexture = device.createTexture(
            TextureDescriptor(
                size = Size3D(width = renderingContext.width, height = renderingContext.height, depthOrArrayLayers = 1),
                format = TextureFormat.depth24plusstencil8,
                usage = setOf(TextureUsage.renderattachment)
            )
        )

        renderPassDesc = RenderPassDescriptor(
            colorAttachments = arrayOf(
                ColorAttachment(
                    view = dummyTexture.createView(),
                    loadOp = LoadOp.clear,
                    clearValue = arrayOf(0.3, 0.3, 0.3, 1),
                    storeOp = StoreOp.store
                )
            ),
            depthStencilAttachment = RenderPassDescriptor.RenderPassDepthStencilAttachment(
                view = depthTexture.createView(),
                depthLoadOp = LoadOp.clear,
                depthClearValue = 1f,
                depthStoreOp = StoreOp.store,
                stencilLoadOp = LoadOp.clear,
                stencilClearValue = 0,
                stencilStoreOp = StoreOp.store
            )
        )

        val viewParamsLayout = device.createBindGroupLayout(
            BindGroupLayoutDescriptor(
                entries = arrayOf(
                    Entry(
                        binding = 0,
                        visibility = setOf(ShaderStage.vertex),
                        bindingType = Entry.BufferBindingLayout(type = BufferBindingType.uniform)
                    )
                )
            )
        )

        viewParamBuf = device.createBuffer(
            BufferDescriptor(
                size = 4 * 4 * 4, usage = setOf(BufferUsage.uniform, BufferUsage.copydst)
            )
        )

        val viewParamsBindGroup = device.createBindGroup(
            BindGroupDescriptor(
                layout = viewParamsLayout,
                entries = arrayOf(
                    BindGroupEntry(
                        binding = 0,
                        resource = BindGroupDescriptor.BufferBinding(buffer = viewParamBuf)
                    )
                )
            )
        )

        val model = uploadGLBModel(device, boxMesh)

        renderBundles = model.buildRenderBundles(
            device,
            shaderCache,
            viewParamsLayout,
            viewParamsBindGroup,
            renderingContext.textureFormat.actualName,
        )

        projectionMatrix = getProjectionMatrix(renderingContext.width, renderingContext.height)
    }

    override fun render() = autoClosableContext {

        val renderPassDesc = renderPassDesc.copy(
            colorAttachments = arrayOf(
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
            0L,
            transformationMatrix,
            0L,
            transformationMatrix.size.toLong()
        )

        val commandEncoder = device.createCommandEncoder().bind()
        val renderPass = commandEncoder.beginRenderPass(renderPassDesc).bind()
        renderPass.executeBundles(renderBundles)

        renderPass.end()
        device.queue.submit(arrayOf(commandEncoder.finish()))

    }


    fun getProjectionMatrix(width: Int, height: Int): Matrix4 {
        val aspect = width / height.toDouble()
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