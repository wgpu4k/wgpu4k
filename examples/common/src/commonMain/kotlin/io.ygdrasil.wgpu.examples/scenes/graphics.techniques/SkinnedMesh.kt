@file:OptIn(ExperimentalCoroutinesApi::class)

package io.ygdrasil.wgpu.examples.scenes.graphics.techniques

import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.BindGroupEntry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.RenderPassDescriptor.ColorAttachment
import io.ygdrasil.wgpu.examples.Application
import io.ygdrasil.wgpu.examples.autoClosableContext
import io.ygdrasil.wgpu.examples.helper.glb.ShaderCache
import io.ygdrasil.wgpu.examples.helper.glb.uploadGLBModel
import korlibs.io.async.async
import korlibs.math.geom.Angle
import korlibs.math.geom.Matrix4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlin.math.PI

class WhaleScene : Application.Scene() {

    internal var renderBundles: Array<RenderBundle>? = null
    internal lateinit var viewParamBuf: Buffer
    internal lateinit var projectionMatrix: Matrix4
    internal lateinit var renderPassDesc: RenderPassDescriptor
    internal lateinit var shaderCache: ShaderCache

    override fun Application.initialiaze() = with(autoClosableContext) {

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

        MainScope().async {
            val model = uploadGLBModel(device, boxMesh)

            renderBundles = model.buildRenderBundles(
                device,
                shaderCache,
                viewParamsLayout,
                viewParamsBindGroup,
                renderingContext.textureFormat.actualName,
            )
        }




        projectionMatrix = getProjectionMatrix(renderingContext.width, renderingContext.height)

    }

    override fun Application.render() = autoClosableContext {
        renderBundles?.let { renderBundles ->
            val renderPassDesc = renderPassDesc.copy(
                colorAttachments = arrayOf(
                    renderPassDesc.colorAttachments[0].copy(
                        view = renderingContext.getCurrentTexture().createView()
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

            val commandEncoder = device.createCommandEncoder()
            val renderPass = commandEncoder.beginRenderPass(renderPassDesc)
            renderPass.executeBundles(renderBundles)

            renderPass.end()
            device.queue.submit(arrayOf(commandEncoder.finish()))
        }

        renderingContext.present()
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