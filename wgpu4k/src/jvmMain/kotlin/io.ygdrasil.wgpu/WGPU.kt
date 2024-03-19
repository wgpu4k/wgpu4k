package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class WGPU(private val handler: WGPUInstance) : AutoCloseable {
	override fun close() {
		wgpuInstanceRelease(handler)
	}

	suspend fun requestAdapter(
		renderingContext: RenderingContext,
		powerPreference: WGPUPowerPreference = WGPUPowerPreference.WGPUPowerPreference_Undefined
	): Adapter? {

		val options = WGPURequestAdapterOptions().also {
			it.compatibleSurface = renderingContext.handler
			it.powerPreference = powerPreference.value
		}

		val adapterState = MutableStateFlow<WGPUAdapterImpl?>(null)

		val handleRequestAdapter = object : WGPURequestAdapterCallback {
			override fun invoke(statusAsInt: Int, adapter: WGPUAdapterImpl, message: String?, param4: Pointer?) {
				val status = WGPURequestAdapterStatus.of(statusAsInt)
				if (status == WGPURequestAdapterStatus.WGPURequestAdapterStatus_Success) {
					adapterState.update { adapter }
				} else {
					println("request_adapter status=%.8X message=%s\n".format(status, message))
				}
			}
		}
		wgpuInstanceRequestAdapter(handler, options, handleRequestAdapter, null)

		return adapterState.value?.let { Adapter(it) }
	}

	fun getSurfaceFromMetalLayer(layer: Pointer): WGPUSurface? {
		val surfaceDescriptor = WGPUDarwinSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { metalLayerDescriptor ->
			metalLayerDescriptor.chain.sType = WGPUSType.WGPUSType_SurfaceDescriptorFromMetalLayer.value
			metalLayerDescriptor.layer = layer
		}

		return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
	}

	fun getSurfaceFromXlibWindow(display: Pointer, window: Long): WGPUSurface? {
		val surfaceDescriptor = WGPUXlibWindowSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { metalLayerDescriptor ->
			metalLayerDescriptor.chain.sType = WGPUSType.WGPUSType_SurfaceDescriptorFromXlibWindow.value
			metalLayerDescriptor.display = display
			metalLayerDescriptor.window = window
		}

		return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
	}

	fun getSurfaceFromWindows(hinstance: Pointer, hwnd: Pointer): WGPUSurface? {
		val surfaceDescriptor = WGPUWindowSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { metalLayerDescriptor ->
			metalLayerDescriptor.chain.sType = WGPUSType.WGPUSType_SurfaceDescriptorFromWindowsHWND.value
			metalLayerDescriptor.hinstance = display
			metalLayerDescriptor.hwnd = window
		}

		return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
	}

	companion object {
		fun createInstance() = wgpuCreateInstance(null)
			?.let { WGPU(it) }
	}
}