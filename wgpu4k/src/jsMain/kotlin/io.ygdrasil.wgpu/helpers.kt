package io.ygdrasil.wgpu

import io.ygdrasil.wgpu.internal.js.GPU

external object navigator {
	val gpu: GPU?
}

