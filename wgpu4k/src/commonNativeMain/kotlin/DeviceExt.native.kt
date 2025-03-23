package io.ygdrasil.webgpu

import io.ygdrasil.wgpu.wgpuDevicePoll

actual suspend fun GPUDevice.poll(): Result<Unit> {
    wgpuDevicePoll((this as Device).handler, true, null)

    return Result.success(Unit)
}