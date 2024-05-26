package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.jvm.WGPURequestDeviceStatus
import io.ygdrasil.wgpu.internal.jvm.confined
import io.ygdrasil.wgpu.internal.jvm.panama.WGPURequestDeviceCallback
import io.ygdrasil.wgpu.internal.jvm.panama.wgpu_h
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.lang.foreign.MemorySegment

actual class Adapter(internal val handler: MemorySegment) : AutoCloseable {

	actual suspend fun requestDevice(): Device? = confined { arena ->
		val deviceState = MutableStateFlow<MemorySegment?>(null)

		val handleRequestAdapter = WGPURequestDeviceCallback.allocate( { statusAsInt, device, message, param4 ->
			if (statusAsInt == wgpu_h.WGPURequestDeviceStatus_Success()) {
				deviceState.update { device }
			} else {
				println("request_device status=${WGPURequestDeviceStatus.of(statusAsInt)} message=${message.getString(0)}")
			}
		}, arena)


		wgpu_h.wgpuAdapterRequestDevice(handler, MemorySegment.NULL, handleRequestAdapter, MemorySegment.NULL)

		deviceState.value?.let { Device(it) }
	}

    actual override fun close() {
		wgpu_h.wgpuAdapterRelease(handler)
	}
}
