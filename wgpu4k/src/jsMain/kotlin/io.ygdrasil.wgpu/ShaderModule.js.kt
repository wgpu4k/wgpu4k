

package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPUShaderModule

actual class ShaderModule(internal val handler: GPUShaderModule) : AutoCloseable {
	actual override fun close() {
		// Nothing to do on JS
	}
}

