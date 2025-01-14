package io.ygdrasil.webgpu

import org.w3c.dom.HTMLCanvasElement


@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
fun HTMLCanvasElement.getCanvasSurface() = (getContext("webgpu") as? io.ygdrasil.webgpu.internal.web.GPUCanvasContext)?.let { CanvasSurface(it) }

