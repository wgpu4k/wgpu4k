package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.Architecture
import io.ygdrasil.wgpu.internal.jvm.Os
import io.ygdrasil.wgpu.internal.jvm.Platform
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUChainedStruct
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceDescriptor
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUSurfaceDescriptorFromMetalLayer
import io.ygdrasil.wgpu.internal.jvm.panama.webgpu_h
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.io.InputStream
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption

class WGPU(private val handler: MemorySegment) : AutoCloseable {

	val handler2: io.ygdrasil.wgpu.internal.jvm.WGPUInstanceImpl = io.ygdrasil.wgpu.internal.jvm.WGPUInstanceImpl(Pointer(handler.address()))

	override fun close() {
		webgpu_h.wgpuInstanceRelease(handler)
	}

	suspend fun requestAdapter(
		renderingContext: RenderingContext,
		powerPreference: io.ygdrasil.wgpu.internal.jvm.WGPUPowerPreference = io.ygdrasil.wgpu.internal.jvm.WGPUPowerPreference.WGPUPowerPreference_Undefined
	): Adapter? {

		val options = io.ygdrasil.wgpu.internal.jvm.WGPURequestAdapterOptions().also {
			it.compatibleSurface = renderingContext.handler
			it.powerPreference = powerPreference.value
		}

		val adapterState = MutableStateFlow<io.ygdrasil.wgpu.internal.jvm.WGPUAdapterImpl?>(null)

		val handleRequestAdapter = object : io.ygdrasil.wgpu.internal.jvm.WGPURequestAdapterCallback {
			override fun invoke(statusAsInt: Int, adapter: io.ygdrasil.wgpu.internal.jvm.WGPUAdapterImpl, message: String?, param4: Pointer?) {
				val status = io.ygdrasil.wgpu.internal.jvm.WGPURequestAdapterStatus.of(statusAsInt)
				if (status == io.ygdrasil.wgpu.internal.jvm.WGPURequestAdapterStatus.WGPURequestAdapterStatus_Success) {
					adapterState.update { adapter }
				} else {
					println("request_adapter status=%.8X message=%s\n".format(status, message))
				}
			}
		}
		io.ygdrasil.wgpu.internal.jvm.wgpuInstanceRequestAdapter(handler2, options, handleRequestAdapter, null)

		return adapterState.value?.let { Adapter(it) }
	}

	fun getSurfaceFromMetalLayer(layer: MemorySegment): MemorySegment = confined { arena ->
		WGPUSurfaceDescriptor.allocate(arena).let { surfaceDescriptor ->
			WGPUSurfaceDescriptor.nextInChain(surfaceDescriptor, WGPUSurfaceDescriptorFromMetalLayer.allocate(arena).also { nextInChain ->
				WGPUSurfaceDescriptorFromMetalLayer.chain(nextInChain, WGPUChainedStruct.allocate(arena).also { chain ->
					WGPUChainedStruct.sType(chain, webgpu_h.WGPUSType_SurfaceDescriptorFromMetalLayer())
				})
				WGPUSurfaceDescriptorFromMetalLayer.layer(nextInChain, layer)
			})

			webgpu_h.wgpuInstanceCreateSurface(handler, surfaceDescriptor)
		}
	}

	fun getSurfaceFromX11Window(display: Pointer, window: Long): io.ygdrasil.wgpu.internal.jvm.WGPUSurface? {
		val surfaceDescriptor = io.ygdrasil.wgpu.internal.jvm.WGPUXlibWindowSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { x11SurfaceDescriptor ->
			x11SurfaceDescriptor.chain.sType = io.ygdrasil.wgpu.internal.jvm.WGPUSType.WGPUSType_SurfaceDescriptorFromXlibWindow.value
			x11SurfaceDescriptor.display = display
			x11SurfaceDescriptor.window = window
		}

		return io.ygdrasil.wgpu.internal.jvm.wgpuInstanceCreateSurface(handler2, surfaceDescriptor)
	}

	fun getSurfaceFromWindows(hinstance: Pointer, hwnd: Pointer): io.ygdrasil.wgpu.internal.jvm.WGPUSurface? {
		val surfaceDescriptor = io.ygdrasil.wgpu.internal.jvm.WGPUWindowSurfaceDescriptor()
		surfaceDescriptor.nextInChain.let { windowSurfaceDescriptor ->
			windowSurfaceDescriptor.chain.sType = io.ygdrasil.wgpu.internal.jvm.WGPUSType.WGPUSType_SurfaceDescriptorFromWindowsHWND.value
			windowSurfaceDescriptor.hinstance = hinstance
			windowSurfaceDescriptor.hwnd = hwnd
		}

		return io.ygdrasil.wgpu.internal.jvm.wgpuInstanceCreateSurface(handler2, surfaceDescriptor)
	}

	companion object {
		fun loadLibrary() {
			val libraryPath = findLibraryPath()
			val libraryFile = extractResourceToTemp(libraryPath)?.toFile() ?: error("fail to find webgpu library")
			System.load(libraryFile.absolutePath)
		}

		private fun findLibraryPath(): String {
			val os = when (Platform.os) {
				Os.Linux -> "linux"
				Os.Windows -> "win32"
				Os.MacOs -> "darwin"
			}

			val libraryName = when (Platform.os) {
				Os.Linux -> "libWGPU.so"
				Os.Windows -> "WGPU.dll"
				Os.MacOs -> "libWGPU.dylib"
			}

			val architecture = when(Platform.architecture) {
				Architecture.X86_64 -> "x86-64"
				Architecture.AARCH64 -> when (Platform.os) {
					Os.Windows -> error("aarch64 not supported on windows")
					else -> "aarch64"
				}
			}

			return "/$os-$architecture/$libraryName"
		}

		private fun extractResourceToTemp(fileOnClasspath: String): Path? {
			// fetch file from the classpath
			val resourceAsStream: InputStream? = WGPU::class.java.getResourceAsStream(fileOnClasspath)

			if (resourceAsStream == null) {
				println("Could not find file $fileOnClasspath on the classpath")
				return null
			}

			// create a temp file
			val tempFile = Files.createTempFile("wgpu", "lib")
			println(tempFile.toFile().absolutePath)
			tempFile.toFile().deleteOnExit()

			// copy the file to the temp directory
			Files.copy(resourceAsStream, tempFile, StandardCopyOption.REPLACE_EXISTING)

			return tempFile
		}

		fun createInstance(backend: WGPUInstanceBackend? = null): WGPU? {
			val arena = Arena.ofConfined()
			val descriptor = io.ygdrasil.wgpu.internal.jvm.panama.WGPUInstanceDescriptor.allocate(arena)
			return webgpu_h.wgpuCreateInstance(descriptor)
				?.let { WGPU(it) }

			//return wgpuCreateInstance(getDescriptor(backend))
			//	?.let { WGPU(it) }
		}

		private fun getDescriptor(backend: WGPUInstanceBackend?): io.ygdrasil.wgpu.internal.jvm.WGPUInstanceDescriptor? {
			if (backend == null) return null

			val descriptor = io.ygdrasil.wgpu.internal.jvm.WGPUInstanceDescriptor()
			descriptor.nextInChain = io.ygdrasil.wgpu.internal.jvm.WGPUInstanceExtras.ByReference().also {
				it.chain.sType = io.ygdrasil.wgpu.internal.jvm.WGPUNativeSType.WGPUSType_InstanceExtras.value
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

