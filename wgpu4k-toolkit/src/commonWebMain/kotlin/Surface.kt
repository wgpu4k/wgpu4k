@file:OptIn(ExperimentalWasmJsInterop::class)

package io.ygdrasil.webgpu

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.unsafeCast

actual class Surface(private val handler: WGPUCanvasContext) : AutoCloseable {
    actual val width: UInt
        get() = handler.canvas.unsafeCast<HTMLCanvasElement>().width.asUInt()
    actual val height: UInt
        get() = handler.canvas.unsafeCast<HTMLCanvasElement>().height.asUInt()

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

    val canvas = htmlCanvas ?: createcCanvas("canvas", deferredRendering)

    val devicePixelRatio = window.devicePixelRatio.asInt()
    if (width != null) canvas.width = width.asJsNumber() else canvas.width = (canvas.clientWidth.asInt() * devicePixelRatio).asJsNumber()
    if (height != null) canvas.height = height.asJsNumber() else canvas.height = (canvas.clientHeight.asInt() * devicePixelRatio).asJsNumber()

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
    viewFormats = input.viewFormats.mapJsArray { it.value.asJsString() }
    colorSpace = input.colorSpace.value.asJsString()
    toneMapping = createJsObject<WGPUCanvasToneMapping>().apply {
        // TODO add the capability to change this value
        // GPUCanvasToneMappingMode.Standard is the default value on specification
        mode = GPUCanvasToneMappingMode.Standard.value.asJsString()
    }
    alphaMode = input.alphaMode.value.asJsString()
}


internal expect fun createcCanvas(name: String, isHidden: Boolean): HTMLCanvasElement
