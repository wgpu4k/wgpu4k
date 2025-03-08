// This file has been generated DO NOT EDIT !!!
package io.ygdrasil.webgpu

actual enum class CompositeAlphaMode(val value: UInt) {
	Auto(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Auto),
	Opaque(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Opaque),
	Premultiplied(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Premultiplied),
	Unpremultiplied(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Unpremultiplied),
	Inherit(io.ygdrasil.wgpu.WGPUCompositeAlphaMode_Inherit),
	;

	companion object {
		fun of(value: UInt): CompositeAlphaMode? {
			return entries.find { it.value == value }
		}
	}
}

actual enum class PresentMode(val value: UInt) {
	Fifo(io.ygdrasil.wgpu.WGPUPresentMode_Fifo),
	FifoRelaxed(io.ygdrasil.wgpu.WGPUPresentMode_FifoRelaxed),
	Immediate(io.ygdrasil.wgpu.WGPUPresentMode_Immediate),
	Mailbox(io.ygdrasil.wgpu.WGPUPresentMode_Mailbox),
	;

	companion object {
		fun of(value: UInt): PresentMode? {
			return entries.find { it.value == value }
		}
	}
}