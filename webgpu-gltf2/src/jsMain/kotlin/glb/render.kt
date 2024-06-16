@file:OptIn(ExperimentalJsExport::class, DelicateCoroutinesApi::class)

package glb

import GLBShaderCache
import getProjectionMatrix
import getTransformationMatrix
import io.ygdrasil.wgpu.*
import io.ygdrasil.wgpu.BindGroupDescriptor.BindGroupEntry
import io.ygdrasil.wgpu.BindGroupLayoutDescriptor.Entry
import io.ygdrasil.wgpu.RenderPassDescriptor.ColorAttachment
import korlibs.io.file.std.resourcesVfs
import korlibs.io.util.toInt8Array
import korlibs.math.geom.Matrix4
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.DelicateCoroutinesApi
import org.w3c.dom.HTMLCanvasElement

external fun setInterval(render: () -> Unit, updateInterval: Int)

// ~60 Frame per second
val UPDATE_INTERVAL = (1000.0 / 60.0).toInt()
val file = "./DamagedHelmet.glb"

suspend fun renderContext(): MyRenderContext {

    val fileBuffer = resourcesVfs[file].readBytes()
        .toInt8Array()

    val canvas: HTMLCanvasElement = document.getElementById("webgpu") as HTMLCanvasElement
    val devicePixelRatio = window.devicePixelRatio
    canvas.width = (canvas.clientWidth * devicePixelRatio).toInt()
    canvas.height = (canvas.clientHeight * devicePixelRatio).toInt()
    val adapter = requestAdapter() ?: error("No appropriate Adapter found.")
    val device = adapter.requestDevice() ?: error("No appropriate Device found.")
    val renderingContext = canvas.getRenderingContext() ?: error("fail to get context")
    val model = uploadGLBModel(device, fileBuffer.buffer)

    renderingContext.configure(
        CanvasConfiguration(
            device = device
        )
    )

    return MyRenderContext(
        device,
        model,
        renderingContext,
        canvas
    )
}

class MyRenderContext(
    val device: Device,
    val model: GLBModel,
    val renderingContext: RenderingContext,
    canvas: HTMLCanvasElement
) {

    internal var renderBundles: Array<RenderBundle>
    internal var viewParamBuf: Buffer
    internal var projectionMatrix: Matrix4
    internal var renderPassDesc: RenderPassDescriptor
    internal val shaderCache = GLBShaderCache(device)
    var frame = 0

    init {
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
                size = Size3D(width = canvas.width, height = canvas.height, depthOrArrayLayers = 1),
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

        var viewParamsLayout = device.createBindGroupLayout(
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


        renderBundles = model.buildRenderBundles(
            device,
            shaderCache,
            viewParamsLayout.handler,
            viewParamsBindGroup.handler,
            renderingContext.textureFormat.actualName,
        )

        projectionMatrix = getProjectionMatrix(canvas.width, canvas.height)

        setInterval({
            render()
        }, UPDATE_INTERVAL)
    }

    fun render() {
        frame += 1
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
}

var frame = 0

@JsExport
fun renderKt(_renderContext: dynamic) {
    val renderContext: MyRenderContext = _renderContext
    with(renderContext) {

        frame += 1
        val renderPassDesc = renderContext.renderPassDesc.copy(
            colorAttachments = arrayOf(
                renderContext.renderPassDesc.colorAttachments[0].copy(
                    view = renderContext.renderingContext.getCurrentTexture().createView()
                )
            )
        )

        var transformationMatrix = getTransformationMatrix(
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

        var commandEncoder = device.createCommandEncoder()
        var renderPass = commandEncoder.beginRenderPass(renderPassDesc)
        renderPass.executeBundles(renderBundles)

        renderPass.end()
        device.queue.submit(arrayOf(commandEncoder.finish()))


    }
}
