package io.ygdrasil.wgpu.examples.headless

import io.ygdrasil.wgpu.WGPUContext

expect suspend fun getHeadlessContext(): WGPUContext
