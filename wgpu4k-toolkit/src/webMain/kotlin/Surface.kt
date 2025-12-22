@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import web.dom.document
import web.html.HTMLCanvasElement
import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.toInt
import kotlin.js.toJsString
import kotlin.js.unsafeCast

actual class Surface(private val handler: WGPUCanvasContext) : AutoCloseable {
    actual val width: UInt
        get() = handler.canvas.unsafeCast<HTMLCanvasElement>().width.toUInt()
    actual val height: UInt
        get() = handler.canvas.unsafeCast<HTMLCanvasElement>().height.toUInt()

    // @see https://gpuweb.github.io/gpuweb/#canvas-configuration
    actual val supportedFormats: Set<GPUTextureFormat> =
        setOf(GPUTextureFormat.BGRA8Unorm, GPUTextureFormat.RGBA8Unorm, GPUTextureFormat.RGBA16Float)
    actual val supportedAlphaMode: Set<CompositeAlphaMode> =
        setOf(CompositeAlphaMode.Opaque, CompositeAlphaMode.Premultiplied)

    actual fun getCurrentTexture(): SurfaceTexture {
        return handler.getCurrentTexture()
            .let { Texture(it, canBeDestroy = false)}
            .let { SurfaceTexture(it, SurfaceTextureStatus.success) }
    }

    actual fun present() { /* does not exists on Web */ }

    actual fun configure(surfaceConfiguration: SurfaceConfiguration) {
        handler.configure(map(surfaceConfiguration))
    }

    actual override fun close() { /* does not exists on Web */ }
}

suspend fun canvasContextRenderer(
    htmlCanvas: HTMLCanvasElement? = null,
    deferredRendering: Boolean = false,
    width: Int? = null, height: Int? = null,
    onUncapturedError: GPUUncapturedErrorCallback? = null
): CanvasContext {

    val canvas = htmlCanvas ?: createCanvas("canvas", deferredRendering)

    val devicePixelRatio = window.devicePixelRatio.toInt()
    if (width != null) canvas.width = width else canvas.width = canvas.clientWidth * devicePixelRatio
    if (height != null) canvas.height = height else canvas.height = canvas.clientHeight * devicePixelRatio

    val adapter = requestAdapter()
        .getOrThrow()
    val device = adapter.requestDevice(DeviceDescriptor(
        onUncapturedError = onUncapturedError
    )).getOrThrow()
    val canvasSurface = canvas.getCanvasSurface()
    val surface = Surface(canvasSurface)

    val renderingContext = when (deferredRendering) {
        true -> TextureRenderingContext(256u, 256u, GPUTextureFormat.RGBA8Unorm, device)
        false -> SurfaceRenderingContext(surface, navigator.gpu?.getPreferredCanvasFormat()?.let { GPUTextureFormat.of(it) ?: error("format not found $it") } ?: error("WebGPU not supported"))
    }

    return CanvasContext(
        canvas,
        WGPUContext(
            surface,
            adapter,
            device,
            renderingContext,
        )
    )
}

class CanvasContext(
    val canvas: HTMLCanvasElement,
    val wgpuContext: WGPUContext,
) : AutoCloseable {

    override fun close() {
        wgpuContext.close()
    }
}

fun map(input: SurfaceConfiguration) = createJsObject<WGPUCanvasConfiguration>().apply {
    device = (input.device as Device).handler
    format = input.format.value
    usage = input.usage.toFlagInt().asJsNumber()
    viewFormats = input.viewFormats.mapJsArray { it.value.toJsString() }
    colorSpace = input.colorSpace.value.toJsString()
    toneMapping = createJsObject<WGPUCanvasToneMapping>().apply {
        // TODO add the capability to change this value
        // GPUCanvasToneMappingMode.Standard is the default value on specification
        mode = GPUCanvasToneMappingMode.Standard.value.toJsString()
    }
    alphaMode = input.alphaMode.value.toJsString()
}


internal fun createCanvas(name: String, isHidden: Boolean): HTMLCanvasElement =
    (document.createElement("canvas") as HTMLCanvasElement).also {
        document.body.appendChild(it)
        it.hidden = isHidden
    }.unsafeCast<HTMLCanvasElement>()
