package io.ygdrasil.webgpu

expect suspend fun GPUDevice.poll(): Result<Unit>