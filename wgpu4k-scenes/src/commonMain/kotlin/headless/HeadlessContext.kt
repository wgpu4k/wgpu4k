package io.ygdrasil.webgpu.examples.headless

import io.ygdrasil.webgpu.WGPUContext

expect suspend fun getHeadlessContext(): WGPUContext
