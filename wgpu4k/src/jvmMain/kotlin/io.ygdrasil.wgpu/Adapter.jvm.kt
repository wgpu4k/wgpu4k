package io.ygdrasil.wgpu

import com.sun.jna.Pointer
import io.ygdrasil.wgpu.internal.jvm.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

actual class Adapter(internal val handler: WGPUAdapterImpl) : AutoCloseable {

	actual suspend fun requestDevice(): Device? {
		val deviceState = MutableStateFlow<WGPUDeviceImpl?>(null)

		val handleRequestDevice = object : WGPURequestDeviceCallback {
			override fun invoke(statusAsInt: Int, device: WGPUDeviceImpl, message: String?, param4: Pointer?) {
				val status = WGPURequestDeviceStatus.of(statusAsInt)
				if (status == WGPURequestDeviceStatus.WGPURequestDeviceStatus_Success) {
					deviceState.update { device }
				} else {
					println(" request_device status=%#.8x message=%s\n".format(status, message))
				}
			}
		}

		wgpuAdapterRequestDevice(handler, null, handleRequestDevice, null)

		return deviceState.value?.let { Device(it) }
	}

	override fun close() {
		wgpuAdapterRelease(handler)
	}
}