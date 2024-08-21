package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPUAdapterRequestDeviceCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import io.ygdrasil.wgpu.mapper.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.lang.foreign.MemorySegment

actual class Adapter(internal val handler: MemorySegment) : AutoCloseable {

	actual suspend fun requestDevice(descriptor: DeviceDescriptor): Device? = confined { arena ->
		val deviceState = MutableStateFlow<MemorySegment?>(null)

		val handleRequestAdapter = WGPUAdapterRequestDeviceCallback.allocate( { statusAsInt, device, message, param4 ->
			if (statusAsInt == wgpu_h.WGPURequestDeviceStatus_Success()) {
				deviceState.update { device }
			} else {
				println("request_device status=${WGPURequestDeviceStatus.of(statusAsInt)} message=${message.getString(0)}")
			}
		}, arena)

		wgpu_h.wgpuAdapterRequestDevice(
			handler,
			arena.map(descriptor),
			handleRequestAdapter,
			MemorySegment.NULL
		)

		deviceState.value?.let { Device(it) }
	}

	actual override fun close() {
		wgpu_h.wgpuAdapterRelease(handler)
	}
}


internal enum class WGPURequestDeviceStatus(
	val `value`: Int,
) {
	WGPURequestDeviceStatus_Success(0),
	WGPURequestDeviceStatus_Error(1),
	WGPURequestDeviceStatus_Unknown(2);

	companion object {
		internal fun of(`value`: Int): WGPURequestDeviceStatus? = entries.find {
			it.value == value
		}
	}
}

