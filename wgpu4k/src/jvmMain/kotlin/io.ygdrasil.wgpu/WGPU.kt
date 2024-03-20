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

	fun getSurfaceFromX11Window(display: Pointer, window: Long): WGPUSurface? {
		val surfaceDescriptor = WGPUXlibWindowSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { x11SurfaceDescriptor ->
			x11SurfaceDescriptor.chain.sType = WGPUSType.WGPUSType_SurfaceDescriptorFromXlibWindow.value
			x11SurfaceDescriptor.display = display
			x11SurfaceDescriptor.window = window
		}

		return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
	}

	fun getSurfaceFromWindows(hinstance: Pointer, hwnd: Pointer): WGPUSurface? {
		val surfaceDescriptor = WGPUWindowSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { windowSurfaceDescriptor ->
			windowSurfaceDescriptor.chain.sType = WGPUSType.WGPUSType_SurfaceDescriptorFromWindowsHWND.value
			windowSurfaceDescriptor.hinstance = hinstance
			windowSurfaceDescriptor.hwnd = hwnd
		}

		return wgpuInstanceCreateSurface(handler, surfaceDescriptor)
	}

	companion object {
		fun createInstance(backend: WGPUInstanceBackend? = null) = wgpuCreateInstance(getDescriptor(backend))
			?.let { WGPU(it) }

		private fun getDescriptor(backend: WGPUInstanceBackend?): WGPUInstanceDescriptor? {
			if (backend == null) return null

			val descriptor = WGPUInstanceDescriptor()
			descriptor.nextInChain = WGPUInstanceExtras.ByReference().also {
				it.chain.sType = WGPUNativeSType.WGPUSType_InstanceExtras.value
				it.backends = backend.value
			}

			return descriptor
		}
	}
}

enum class WGPUInstanceBackend(val value: Int) {

	Vulkan(1 shl 1),
	GL(1 shl 5),
	Metal(1 shl 2),
	DX12(1 shl 3),
	DX11(1 shl 4),
	BrowserWebGPU(1 shl 6),
	Primary(Vulkan.value or Metal.value or DX12.value or BrowserWebGPU.value),
	Secondary(GL.value or DX11.value),
	None(0x00000000),
	Force32(0x7FFFFFFF);

	companion object {
		fun fromValue(value: Int): WGPUInstanceBackend? {
			return values().firstOrNull { it.value == value }
		}
	}
}